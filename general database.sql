CREATE DATABASE `general`;

USE `general`;

/*******************
department
*********************/

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
`id` INT(3) NOT NULL,
`name` VARCHAR(30) NOT NULL,
`description` VARCHAR(50) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT  INTO `department`(`id`,`name`) 
VALUES (1,'Strategy'),
(2,'Marketing'),
(3,'Finance'),
(4,'Human resources'),
(5,'Technology and equipment'),
(6,'Operations');


/*******************
position
*********************/

DROP TABLE IF EXISTS `position`;

CREATE TABLE `position` (
`id` INT(3) NOT NULL,
`name` VARCHAR(30) NOT NULL,
`description` VARCHAR(50) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT  INTO `position`(`id`,`name`) 
VALUES (1,'CEO'),
(2,'Manager'),
(3,'Sale Representative'),
(4,'Bookkeeper'),
(5,'Technicist');


/*******************
employee
*********************/

DROP TABLE IF EXISTS `employee`;

CREATE TABLE `employee` (
`id` INT(5) PRIMARY KEY AUTO_INCREMENT,
`firstName` VARCHAR(30) NOT NULL,
`lastName` VARCHAR(30) NOT NULL,
`email` VARCHAR(40) NOT NULL UNIQUE,
`phone` VARCHAR(15) NOT NULL UNIQUE,
`address` VARCHAR(50) NOT NULL,
`gender` INT(1) NOT NULL,
`birth` DATE DEFAULT NULL,
`hired` DATE DEFAULT NULL,
`SIN` VARCHAR(15) NOT NULL UNIQUE,
`departmentid` INT(3) NOT NULL,
`positionid` INT(3) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT  INTO `employee`(`id`,`firstName`,`lastName`,`email`,`phone`,`address`,
`gender`,`birth`,`hired`,`SIN`,`departmentid`,`positionid`)
VALUES (1,'Clark','Yang','A12345678@gmail.com','6478889999','101 Yonge St, Toronto',1,'1988-11-16','2020-07-01','222255555',1,1),
(2,'Sam','Stanley','B12345678@gmail.com','6471112222','102 Yonge St, Toronto',0,'1990-01-12','2020-07-03','111222222',2,3),
(3,'Mary','Buckley','C12345678@gmail.com','6472223333','103 Yonge St, Toronto',1,'1970-08-17','2020-07-05','333344444',3,4),
(4,'Delilah','Moon','D12345678@gmail.com','6472224444','104 Yonge St, Toronto',0,'1994-03-16','2020-07-10','555566666',5,5),
(5,'Hillary','Rasmussen','E12345678@gmail.com','6472225555','105 Yonge St, Toronto',1,'1999-12-06','2020-07-12','666667777',1,2);


/*******************
role
*********************/

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
`id` INT(3) NOT NULL,
`name` VARCHAR(30) NOT NULL UNIQUE,
`description` VARCHAR(50) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT  INTO `role`(`id`,`name`) 
VALUES (1,'Super Admin'),
(2,'Admin'),
(3,'Staff');

/*******************
authority
*********************/

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
`id` INT(3) NOT NULL,
`name` VARCHAR(30) NOT NULL,
`description` VARCHAR(50) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT  INTO `authority`(`id`,`name`) 
VALUES (1,'employee:create'),
(2,'employee:read'),
(3,'employee:update'),
(4,'employee:delete'),
(5,'sysuser:create'),
(6,'sysuser:read'),
(7,'sysuser:update'),
(8,'sysuser:delete'),
(9,'role:create'),
(10,'role:read'),
(11,'role:update'),
(12,'role:delete');


/*******************
role_authority
*********************/

DROP TABLE IF EXISTS `role_authority`;

CREATE TABLE `role_authority` (
`roleid` INT(3) NOT NULL,
`authorityid` INT(3) NOT NULL,
PRIMARY KEY (`roleid`,`authorityid`)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT  INTO `role_authority`(`roleid`,`authorityid`) 
VALUES (1,1),
(1,2),
(1,3),
(1,4),
(2,1),
(2,2),
(2,3),
(2,4),
(3,2);


/*******************
user
*********************/

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
`id` INT(5) PRIMARY KEY AUTO_INCREMENT,
`username` VARCHAR(30) NOT NULL UNIQUE,
`password` VARCHAR(40) NOT NULL,
`nickname` VARCHAR(30) NOT NULL,
`roleid` INT(3) NOT NULL,
`employeeid` INT(5) NOT NULL UNIQUE,
`email` VARCHAR(40) NOT NULL UNIQUE,
`salt` VARCHAR(10) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

INSERT  INTO `user`(`id`,`username`,`password`,`nickname`,`roleid`,`employeeid`,`email`,`salt`) 
VALUES (1,'Clark','ef91d8275a79216f32fe11db44544b20','Clark',1,1,'A12345678@gmail.com','lLQ^Hmjf'),
(2,'Sam','aa302c7cc5ddec42cb8421a138b4557f','Sam',2,2,'B12345678@gmail.com','eV(Eqnm@'),
(3,'Mary','e0786dd472bf809cd03680e8a00c929a','Mary',3,3,'C12345678@gmail.com','WRvz$MJk');


/*******************
system query
*********************/

ALTER TABLE USER ADD COLUMN salt VARCHAR(10) NOT NULL;


SELECT a.id, a.name, a.description 
FROM authority a 
LEFT JOIN role_authority ra ON a.id = ra.authorityid
LEFT JOIN ROLE r ON r.id = ra.roleid
LEFT JOIN USER u ON u.roleid = r.id
WHERE u.username = 'Clark';


/*******************

*********************/




