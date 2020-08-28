Employee Management System Project
Introduction
Using SSM(springMVC + Springboot + Mybatis) frame
Using MySQL as database
Using Thymeleaf to render pages
Using Druid data source
Using Shiro security frame
Using Swagger2 to detect API building



2020-07-18 
-Start the Employee Management System Project.
-Select and download static resources
-Draw class diagram and design attributes
-Design database: create tables and insert data

2020-07-19 Version 0.0.1
-Built the SSM frame
-Import static resources
-Add entities class: User, Authority
-Add mapper and service class

2020-07-21 Version 0.0.2
-Add entities class: Department, Position, Employee
-Add mapper and service class
-Import static resources
-Add index.html, user-login.html

2020-07-22 Version 0.0.3
-Add user login function
-Add Interceptor function
-Add commons.html
-Extract common code from all page

2020-07-24 Version 0.0.4
-Add query user list function
-Add update user function

2020-07-26 Version 0.0.5
-Add upload avatar function
-Add user-register.html
-Add user register function

2020-07-27 Version 0.0.6
-Optimize user list displaying
-Add delete user function
-Add 404 and 500 page
-Add user logout function

2020-07-27 Version 0.0.7
-Add employee list page
-Add query employee list function

2020-08-05 Version 0.0.8
-Add employee-add and employee-edit page
-Add curd for Employee
-Deploy Druid data source and log4j 

2020-08-07 Version 0.0.9
-Add a role table and modify user table containing role instead of authority
-Add a Role_Authority table to describe the relationship of roles and authorities
-Add curd for Role

2020-08-08 Version 0.0.10
-Add manager role and permission function

2020-08-14 Version 0.0.11
-optimize role and permission function
-optimize router

2020-08-14 Version 0.1.0
-Add shiro security frame
    -Login, Logout, remember me
    -Displaying pages and buttons base on user role and permissions

2020-08-21 Version 0.1.1
-Add swagger2 for detecting API development


2020-08-26 Version 0.1.2
-Add salt password encoding

2020-08-26 Version 0.1.3
-Added regular expression validation for username and password



