//Run this in the browser

CREATE INDEX ON :Category(categoryId);
CREATE INDEX ON :Category(categoryName);
CREATE INDEX ON :Location(locationId);
CREATE INDEX ON :Location(zip);
CREATE INDEX ON :Purchase(purchaseId);
CREATE INDEX ON :Product(productId);
CREATE INDEX ON :Status(statusId);
CREATE INDEX ON :User(userId);
CREATE INDEX ON :User(username);


***************
LOAD CSV WITH HEADERS FROM 'file:///Users.csv' AS row
CREATE (u:User { userId:row.userId, username:row.username,firstname:row.firstname,lastname:row.lastname })
***************
LOAD CSV WITH HEADERS FROM 'file:///Purchase.csv' AS row
CREATE (p:Purchase { purchaseId:row.purchaseId })
***************
LOAD CSV WITH HEADERS FROM 'file:///Purchase.csv' AS row
MATCH (u:User { userId: row.userId } ),(p:Purchase { purchaseId: row.purchaseId  } )
MERGE (u)-[:MADE_PURCHASE]->(p)
***************
LOAD CSV WITH HEADERS FROM 'file:///Purchase.csv' AS row
MATCH (p:Purchase { purchaseId: row.purchaseId  } ), (product:Product { productId: row.productId } )
MERGE (p)-[:PURCHASE_CONTAINS]->(product)
***************
MATCH p=(u:User {userId: "1"})-[:MADE_PURCHASE]->(pro)-[r:PURCHASE_CONTAINS]->() RETURN p LIMIT 2
***************
/// OH NO! forgot to add the timestamp field for when the purchase occured
LOAD CSV WITH HEADERS FROM 'file:///Purchase.csv' AS row
MATCH (p:Purchase { purchaseId: row.purchaseId  } )
SET p.timestamp=toInteger(row.purchaseDateAsLong)
***************
LOAD CSV WITH HEADERS FROM 'file:///CurrentStatus.csv' AS row
CREATE (s:Status { statusId:row.statusId, userIdStatusId:row.userIdStatusId, statusText:row.statusText, timestamp:toInteger(row.timestamp) })
***************
LOAD CSV WITH HEADERS FROM 'file:///nextstatus1.csv' AS row
CREATE (s:Status { statusId:row.statusId, userIdStatusId:row.userIdStatusId, statusText:row.statusText, timestamp:toInteger(row.timestamp) })
***************
LOAD CSV WITH HEADERS FROM 'file:///nextstatus2.csv' AS row
CREATE (s:Status { statusId:row.statusId, userIdStatusId:row.userIdStatusId, statusText:row.statusText, timestamp:toInteger(row.timestamp) })
***************
LOAD CSV WITH HEADERS FROM 'file:///CurrentStatus.csv' AS row
MATCH (u:User { userId: row.userId  } ), (s:Status { statusId:row.statusId} )
MERGE (u)-[:CURRENT_STATUS]->(s)
***************
LOAD CSV WITH HEADERS FROM 'file:///nextstatus1.csv' AS row
MATCH (s:Status { statusId:row.lastStatusId} ), (ns:Status { statusId:row.statusId} )
MERGE (s)-[:NEXT_STATUS]->(ns)
***************
LOAD CSV WITH HEADERS FROM 'file:///nextstatus2.csv' AS row
MATCH (s:Status { statusId:row.nextToLastStatusId} ), (ns:Status { statusId:row.statusId} )
MERGE (s)-[:NEXT_STATUS]->(ns)
***************
:auto USING PERIODIC COMMIT 10000
LOAD CSV WITH HEADERS FROM 'file:///follows.csv' AS row
MATCH (user:User { userId: row.userId  } ), (userToFollow:User { userId: row.followingid  } )
MERGE (user)-[:FOLLOWS]->(userToFollow)
***************
MATCH (u:User {userId: "1" })
WITH u
MATCH (u)-[:FOLLOWS*0..1]->(f)
RETURN f
***************
MATCH (u:User {userId: "1" })
WITH u
MATCH (u)-[:FOLLOWS*0..1]->(f)
WITH DISTINCT f,u
MATCH (f)-[:CURRENT_STATUS]->(ls)-[:NEXT_STATUS*0..3]->(s)
RETURN s.statusId as statusId, s.statusText as statusText, s.timestamp as timestamp, f.username as username, f=u as owner
  ORDER BY s.timestamp DESC
