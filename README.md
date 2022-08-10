# spring-neo4j

## Get the Data

https://dbops-public.s3.amazonaws.com/data.zip

## Steps
1. Install Neo4j Desktop version 1.4.15, which is located at https://neo4j.com/download/
2. Open Neo4j Desktop and create a new project called "Spring Neo4j"

![Alt text](readme-images/create-project.png?raw=true "Create Project")

3. Add "Local Database" using version 4.3.12 named "DBOps" (without quotes)

![Alt text](readme-images/add-db.png?raw=true "Add Local DB")

4. Set your password

![Alt text](readme-images/add-pw.png?raw=true "Set Password")

5. Click the "create" and wait a minute for the database to build
6. Open the "Settings"

![Alt text](readme-images/add-pw.png?raw=true "Settings")

7. If you have avaialble membory, edit memory settings to use 2G and click "apply" and then "close"

![Alt text](readme-images/settings-memory.png?raw=true "Memory")

8. Start the DB
9. Click create database and add a new database called "dbops" (without quotes)

![Alt text](readme-images/create-dbops-db.png?raw=true "create dbops db")

10. Open the "import" folder, then copy and paste the contents of the data zip into the folder 

![Alt text](readme-images/open-import.png?raw=true "Open Terminal")

11. check the "import" directory to make sure the files are there
12. Open the Terminal and run the following based on your OS

![Alt text](readme-images/terminal.png?raw=true "terminal")

// for linux/mac
```
bin/neo4j-admin import --nodes import/csvs/nodes/Category.csv --nodes import/csvs/nodes/Product.csv --nodes import/csvs/nodes/Review.csv --nodes import/csvs/nodes/User.csv --relationships import/csvs/rels/CC.csv --relationships import/csvs/rels/PC.csv --relationships import/csvs/rels/PP.csv --relationships import/csvs/rels/PR.csv --relationships import/csvs/rels/RC.csv --database=dbops --skip-bad-relationships=true --force
```

// for windows
```
bin/neo4j-admin import --nodes import/csvs/nodes/Category.csv --nodes import/csvs/nodes/Product.csv --nodes import/csvs/nodes/Review.csv --nodes import/csvs/nodes/User.csv --relationships import/csvs/rels/CC.csv --relationships import/csvs/rels/PC.csv --relationships import/csvs/rels/PP.csv --relationships import/csvs/rels/PR.csv --relationships import/csvs/rels/RC.csv --database=dbops --skip-bad-relationships=true --force
```

13. Restart the DBOps database
14. Go to http://localhost:7474/browser/ and login
15. Make sure "Enable multi statement query editor"
16. Run the cypher statements in the cypher folder
