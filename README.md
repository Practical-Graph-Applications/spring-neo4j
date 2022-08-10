# spring-neo4j

## Get the Data

https://dbops-public.s3.amazonaws.com/data.zip

## Steps
1. Install Neo4j Desktop version 1.4.15 https://neo4j.com/download/
2. Add "Local Database" using version 4.3.12  Call it whatever you want, e.g. KCDC
3. set your password
4. click the "create" and wait a minute for the database to build
4. Open the config folder
5. edit memory settings 
6. Start the DB
7. Click create database and add a new database called "kcdc" (without quotes)
8. Open the import folder, then copy and paste the contents of the data zip into the folder 
9. Open the Terminal from  Run the import command and check the "import" directory to make sure the files are there

```
// for linux/mac
bin/neo4j-admin import --nodes import/csvs/nodes/Category.csv --nodes import/csvs/nodes/Product.csv --nodes import/csvs/nodes/Review.csv --nodes import/csvs/nodes/User.csv --relationships import/csvs/rels/CC.csv --relationships import/csvs/rels/PC.csv --relationships import/csvs/rels/PP.csv --relationships import/csvs/rels/PR.csv --relationships import/csvs/rels/RC.csv --database=kcdc --skip-bad-relationships=true --force
```


```
// for windows
bin/neo4j-admin import --nodes import/csvs/nodes/Category.csv --nodes import/csvs/nodes/Product.csv --nodes import/csvs/nodes/Review.csv --nodes import/csvs/nodes/User.csv --relationships import/csvs/rels/CC.csv --relationships import/csvs/rels/PC.csv --relationships import/csvs/rels/PP.csv --relationships import/csvs/rels/PR.csv --relationships import/csvs/rels/RC.csv --database=kcdc --skip-bad-relationships=true --force
```

2. 
