DROP DATABASE IF EXISTS `playground`;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `playground` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `playground`;

DROP TABLE IF EXISTS `UserConnection`;
CREATE TABLE UserConnection (
    userId varchar(200) not null,
    providerId varchar(50) not null,
    providerUserId varchar(50),
    rank int not null,
    displayName varchar(255),
    profileUrl varchar(512),
    imageUrl varchar(512),
    accessToken varchar(255) not null,                  
    secret varchar(255),
    refreshToken varchar(255),
    expireTime bigint,
    primary key (userId, providerId, providerUserId));
CREATE UNIQUE INDEX UserConnectionRank on UserConnection(userId, providerId, rank);

DROP TABLE IF EXISTS `playground_users`;
CREATE TABLE playground_users (
    user_id bigint(20) NOT NULL AUTO_INCREMENT,
    first_name varchar(150),
    last_name varchar(150),
    username varchar(150),
    email varchar(150),
    primary key (user_id));
CREATE UNIQUE INDEX playground_users_username on playground_users(username);

DROP TABLE IF EXISTS `registration`;
CREATE TABLE registration (
    registration_id bigint(20) NOT NULL AUTO_INCREMENT,
    first_name varchar(150),
    last_name varchar(150),
    ip varchar(150),
    email varchar(150),
    PRIMARY KEY (registration_id));
CREATE UNIQUE INDEX registration_email on registration(email);