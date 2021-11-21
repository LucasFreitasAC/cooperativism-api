CREATE TABLE `cooperativism_db`.`topics` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `cooperativism_db`.`sessions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `close_date` datetime NOT NULL,
  `open_date` datetime NOT NULL,
  `topic_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rn7otfesgfgkr2ukphmkew8mc` (`topic_id`),
  CONSTRAINT `FK68ua4g485vc71jtyogqadf8r4` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

CREATE TABLE `cooperativism_db`.`vote` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `document` varchar(255) NOT NULL,
  `vote` bit(1) NOT NULL,
  `topic_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4c1gpdjlmi98q1500nnn1wy8` (`topic_id`),
  CONSTRAINT `FK4c1gpdjlmi98q1500nnn1wy8` FOREIGN KEY (`topic_id`) REFERENCES `topics` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;