This is an Eclipse workspace dump.

Apache Tomcat is required  (8.5 was used in our testing purposes)


MySQL server is required, a DB named mydbs should be created, then you can import the dump provided.


In DBUpload.java the username and password for MYSQL have to be configured as in your case.

If you want to use "admin" (without quotes) as mysql username and password a prebuilt war file is provided here: https://github.com/Gruppo12-Unicampania/mmp/blob/main/mmp/target/mmp-1.0-SNAPSHOT.war 


For having a working war package you'll need to do the following steps: 
Maven->Update project


Run as->Maven generate sources


Run as-> Maven build


Run as-> Maven install


Run on server(apache tomcat)
