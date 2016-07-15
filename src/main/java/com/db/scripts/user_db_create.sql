/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`user_db` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `user_db`;

/*Table structure for table `account_status` */

DROP TABLE IF EXISTS `account_status`;

CREATE TABLE `account_status` (
  `id` tinyint(3) NOT NULL AUTO_INCREMENT,
  `status_name` varchar(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `account_status` */

INSERT  INTO `account_status`(`id`,`status_name`) values
  (1,'active'),
  (2,'deleted');

/*Table structure for table `user_password` */

DROP TABLE IF EXISTS `user_password`;

CREATE TABLE `user_password` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `password` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`,`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `social_networks` */

DROP TABLE IF EXISTS `social_networks`;

CREATE TABLE `social_networks` (
  `id` bigint(2) NOT NULL,
  `network_type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `social_networks` */

insert  into `social_networks`(`id`,`network_type`) values
(1,'vkontakte'),
(2,'facebook'),
(3,'instagram'),
(4,'github'),
(5,'twitter'),
(6,'google_plus'),
(7,'forsquare');

/*Table structure for table `user_in_social_network` */

DROP TABLE IF EXISTS `user_in_social_network`;

CREATE TABLE `user_in_social_network` (
  `ss_user_id` bigint(20) DEFAULT NULL,
  `social_network_id` bigint(20) DEFAULT NULL,
  `social_network_type` bigint(2) DEFAULT NULL,
  KEY `social_network_type` (`social_network_type`),
  CONSTRAINT `user_in_social_network_ibfk_1` FOREIGN KEY (`social_network_type`) REFERENCES `social_networks` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `email` VARCHAR(20) NULL,
  `last_login_ts` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' ON UPDATE CURRENT_TIMESTAMP,
  `user_created_ts` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `account_status_id` tinyint(3) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
