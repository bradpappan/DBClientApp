DBCLientApp - The purpose of this program is to record and display customer information and the appointments associated with them. It has a main login page where a user is authenticated
in a database. You are then able to see a customer records page, which shows all the active customers. You are able to edit/delete/add customers to this table view. This page also
holds the report section which shows three reports, total number of customer appointments by type and month, a schedule for each contact in your organization that includes appointment ID, title, type and description,
start date and time, end date and time, and customer ID, and a total count of active customers. From the customer records screen you are able to select a customer and open appointments
associated with that customer, which you can also edit/delete/add appointments as well. There are filters on this screen which show all appointments for the current week and month.
This shows all appointments, not just the current selected customer, which is intentional to show all upcoming appointments so the user doesn't have to go back and check several customers.

Author - Brad Pappan, brad.pappan2@gmail.com, 5/3/2022

IDE Version - IntelliJ IDEA 2021.3 (Community Edition), JDK Runtime version: 11.0.13+7-b1751.19 amd64, JavaFX-SDK-17.0.1

Student application version 1.1

You will need to install all the listed IDE and JDK's (IntelliJ, JDK 11, and JavaFX 17), you will also have to set up a MySQL database with the following user information
listed in the JDBC class,or adjust the data accordingly. The program can be run by using the "Run" button in IntelliJ after installing all of that and setting up your paths to your SDKs.

The additional report I made was to count all listed customers in the database and displays the number in the textarea.

mysql-connector-java-8.0.25
