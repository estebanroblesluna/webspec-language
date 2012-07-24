USE `playground`;

DROP TABLE IF EXISTS `diagrams`;

CREATE TABLE diagrams (
    diagram_id bigint(20) NOT NULL AUTO_INCREMENT,
    diagram_type varchar(50),
    save_time DATETIME,
    name varchar(150),
    jsonRepresentation TEXT,
    owner_id bigint(20) NOT NULL,
    primary key (diagram_id),
    CONSTRAINT `FKCACDCE063E726F41` FOREIGN KEY (`owner_id`) REFERENCES `playground_users` (user_id)
);
