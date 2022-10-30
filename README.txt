Auto Repair Scheduling Application

The purpose of the Auto Repair Scheduling application is to provide a GUI based appointment scheduling application for a
local auto repair shop.  The password protected application gives the advisor the ability to create/update/delete customers
and appointments from the company's chosen database system.  The application gives warning to an advisor if there is an
appointment within 15 minutes of the advisor's login, and shows them the appointment information should an appointment exist.
The advisor is also able to view reports about the number of customers, the number of appointments a given tech has, and
how many appointments of a certain type are in a chosen month. The appointment scheduler provides various error checking
and confirmations throughout, including if form fields are not fully filled out or confirming before a delete takes place.

Author Information:
Author: Ashley Jensen
Version: 1.1.0
Date: September 8, 2022

IDE/JDK/JavaFX and MySQL Connector Versions:
IDE: IntelliJ Community 2021.1
JDK: Java Version 17.0.1
JavaFX: JavaFX-SDK-17.0.1
MySQL Connector: mysql-connector-java-8.0.25

Directions:
1. Unzip files and open in IntelliJ
2. Launch application and login using the following credentials:
    Username: test      Password: test
3. View/Add/Update/Delete appointments from Appointments Form by clicking Appointments button
4. View/Add/Update/Delete customers from Customers Form by clicking Customers button
5. View reports from Reports Form by clicking Reports Button
6. View recorded log-in attempts by viewing login_activity.txt in program files in IntelliJ
7. Exit program by clicking Exit button and confirming exit, or by clicking X in upper right-hand corner


Below is the class diagram I created for the model componant of the MVC framework used to build the application:


