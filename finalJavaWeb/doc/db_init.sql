CREATE DATABASE `Killers of the Three Kingdoms`

CREATE TABLE IF NOT EXISTS `role`(
   `rolename` VARCHAR(10) NOT NULL,
   `strength` FLOAT UNSIGNED NOT NULL,
   `country` VARCHAR(10) NOT NULL,
   `skill` VARCHAR(500) NOT NULL,
   `describe` VARCHAR(500) NOT NULL,
   `picture` VARCHAR(200) NOT NULL,
   PRIMARY KEY ( `rolename` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `user`(
   `user` VARCHAR(10) NOT NULL,
   `username` VARCHAR(50) NOT NULL,
   `password` VARCHAR(50) NOT NULL,
   `UUID` VARCHAR(200) NOT NULL,
   PRIMARY KEY ( `UUID` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;