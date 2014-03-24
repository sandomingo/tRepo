数据库设计：
t_website:
delimiter $$

CREATE TABLE `t_website` (
  `ws_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `status` tinyint(4) DEFAULT NULL,
  `author` varchar(45) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL COMMENT '每个website的模板的基础信息',
  PRIMARY KEY (`ws_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8$$

t_field_data
delimiter $$

CREATE TABLE `t_field_data` (
  `fd_id` int(11) NOT NULL AUTO_INCREMENT,
  `website_id` int(11) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `occur` varchar(45) DEFAULT NULL,
  `trace` varchar(45) DEFAULT NULL,
  `multi_value` varchar(45) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `comment` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fd_id`),
  KEY `website_id_idx` (`website_id`),
  CONSTRAINT `field_owner` FOREIGN KEY (`website_id`) REFERENCES `t_website` (`ws_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8$$

t_template_data
delimiter $$

CREATE TABLE `t_template_data` (
  `td_id` int(11) NOT NULL AUTO_INCREMENT,
  `field_id` int(11) DEFAULT NULL,
  `expression` text,
  `regular_match` text,
  `script` text,
  `range_start` varchar(45) DEFAULT NULL,
  `range_end` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`td_id`),
  KEY `fieldID_idx` (`field_id`),
  CONSTRAINT `fieldID` FOREIGN KEY (`field_id`) REFERENCES `t_field_data` (`fd_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8$$


t_search_seed:
websiteID, url, encoding, retry, comment


t_site_seed:
websiteID, url, fetch_interval, type, comment

t_url_pattern:
delimiter $$

CREATE TABLE `t_url_pattern` (
  `up_id` int(11) NOT NULL AUTO_INCREMENT,
  `website_id` int(11) DEFAULT NULL,
  `url_pattern` varchar(255) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`up_id`),
  KEY `ws_id_idx` (`website_id`),
  CONSTRAINT `url_owner` FOREIGN KEY (`website_id`) REFERENCES `t_website` (`ws_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8$$







t_testcase:
websiteID, url, comment