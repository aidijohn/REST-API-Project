Task: 1
{A REST API that implements a simple money transfer between accounts}

How to set up the project
-------------------------
1. Download the project using the github link provided.
2. Open Java IDE (Preferably Intelli-J - which I used for this project).
3. On intelli-J, Click File > Open, navigate to project location and select parent folder 'REST-API-Project' to import.
4. Wait for the project to load.
5. Setting up the database;
	-MySQL used for this project.
	-Open MySQL workbench and create a new database 'accounts'.

	NOTE: MySQL is running on port 3000, in case your db is running on a different port, you can update the port on
	the application.properties file as shown below.

	-spring.datasource.url=jdbc:mysql://localhost:3000/accounts

6. Run the Project using Intellij Idea.
7. Open Postman.
8. Navigate to directory: REST-API-Pro > src > test and locate the Postman endpoints file. Import this file in postman.
9. Run the respective endpoints.
	-THE application is running on port 8081. This can be updated in application.properties file;
	
	server.port=8081

NOTE: Avoid using port 8080 as it could clash with port being used by MySQL.

NOTE: Log files are created in the parent folder for every request execution made. (accountsLog.log)
 
