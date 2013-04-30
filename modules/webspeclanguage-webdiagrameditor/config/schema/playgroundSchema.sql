USE `playground`;

DROP TABLE IF EXISTS `diagrams`;

CREATE TABLE diagrams (
    diagram_id bigint(20) NOT NULL AUTO_INCREMENT,
    diagram_type varchar(50),
    save_time DATETIME,
    name varchar(150),
    jsonRepresentation TEXT,
    imageBytes LONGBLOB,
    owner_id bigint(20) NOT NULL,
    primary key (diagram_id),
    CONSTRAINT `FKCACDCE063E726F41` FOREIGN KEY (`owner_id`) REFERENCES `playground_users` (user_id)
);

DROP TABLE IF EXISTS `project`;
CREATE TABLE `project` (
 `project_id` bigint(20) NOT NULL AUTO_INCREMENT,
  name varchar(150),
  PRIMARY KEY (`project_id`)
) ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS `project_user`;
CREATE TABLE `project_user` (
 `project_id` bigint(20) NOT NULL,
  user_id bigint(20) NOT NULL,
  PRIMARY KEY (`project_id`, user_id)
) ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS `mockup`;
CREATE TABLE `mockup` (
  `mockup_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_project_id` bigint(20),
  `parent_user_story_id` bigint(20),
  `parent_sprint_id` bigint(20),
   name varchar(150),
  `idx_mockup` int(11) DEFAULT NULL,
  PRIMARY KEY (`mockup_id`)
) ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS `user_story`;
CREATE TABLE `user_story` (
  `user_story_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_sprint_id` bigint(20),
   title varchar(150),
  `idx_user_story` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_story_id`)
) ENGINE=InnoDB CHARSET=utf8;

DROP TABLE IF EXISTS `sprint`;
CREATE TABLE `sprint` (
  `sprint_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `number` bigint(20) NOT NULL,
  `parent_project_id` bigint(20) NOT NULL,
  `init_date` datetime NOT NULL,
  `end_date` datetime NOT NULL,
   title varchar(150),
  `idx_sprint` int(11) DEFAULT NULL,
  PRIMARY KEY (`sprint_id`)
) ENGINE=InnoDB CHARSET=utf8;