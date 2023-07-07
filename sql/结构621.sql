/*
SQLyog Professional v12.14 (64 bit)
MySQL - 5.7.37-log : Database - to_parking
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`to_parking` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `to_parking`;

/*Table structure for table `t_admin` */

DROP TABLE IF EXISTS `t_admin`;

CREATE TABLE `t_admin` (
  `id` bigint(19) NOT NULL COMMENT '主键',
  `admin` varchar(50) NOT NULL COMMENT '管理员账号',
  `admin_password` varchar(100) NOT NULL COMMENT '管理员密码',
  `role` varchar(50) NOT NULL COMMENT '角色',
  `locked` tinyint(1) DEFAULT '1' COMMENT '判断账户密码是否未过期',
  `check_enabled` tinyint(1) DEFAULT '1' COMMENT '判断账户是否可用',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_stall` */

DROP TABLE IF EXISTS `t_stall`;

CREATE TABLE `t_stall` (
  `stall_id` bigint(19) NOT NULL COMMENT '主键',
  `car_park_id` bigint(19) NOT NULL COMMENT '停车场ID',
  `province` varchar(100) NOT NULL COMMENT '省份',
  `city` varchar(100) NOT NULL COMMENT '城市',
  `address` varchar(500) NOT NULL COMMENT '车位地址',
  `longitude` varchar(20) NOT NULL COMMENT '经度',
  `latitude` varchar(20) NOT NULL COMMENT '纬度',
  `is_book` tinyint(1) DEFAULT '0' COMMENT '车位是否已经被预约',
  `is_use` tinyint(1) DEFAULT '0' COMMENT '车位是否在使用中',
  `url` varchar(500) DEFAULT NULL COMMENT '照片链接',
  `price` int(11) DEFAULT '0' COMMENT '价格',
  `category` varchar(100) DEFAULT NULL COMMENT '停车位类型',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '车位注册时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '车位信息更新时间',
  PRIMARY KEY (`stall_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_stall_use` */

DROP TABLE IF EXISTS `t_stall_use`;

CREATE TABLE `t_stall_use` (
  `id` bigint(19) NOT NULL COMMENT '主键',
  `user_id` bigint(19) NOT NULL COMMENT '预约用户ID',
  `stall_id` bigint(19) NOT NULL COMMENT '预约停车位Id',
  `is_book` bigint(20) DEFAULT '0' COMMENT '预约状态（判断是否取消）',
  `book_time` datetime DEFAULT NULL COMMENT '预约时间',
  `cancel_book` tinyint(1) DEFAULT '0' COMMENT '判断是否取消预约',
  `cancel_book_time` datetime DEFAULT NULL COMMENT '取消预约时间',
  `is_start_use` tinyint(1) DEFAULT '0' COMMENT '使用状态（判断是否开始使用）',
  `use_start_time` datetime DEFAULT NULL COMMENT '开始使用时间',
  `is_end_use` tinyint(1) DEFAULT NULL COMMENT '判断是否已经结束使用',
  `use_end_time` datetime DEFAULT NULL COMMENT '结束使用时间',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '信息创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '信息修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `user_id` bigint(19) NOT NULL COMMENT '主键(雪花算法)',
  `nickname` varchar(50) NOT NULL COMMENT '用户昵称',
  `phone` varchar(20) NOT NULL COMMENT '用户手机号',
  `password` varchar(100) NOT NULL COMMENT '用户密码',
  `gender` varchar(10) NOT NULL COMMENT '性别',
  `age` int(3) DEFAULT NULL COMMENT '年龄',
  `address` varchar(100) DEFAULT NULL COMMENT '用户所在省份',
  `balance` double(18,2) DEFAULT '0.00' COMMENT '余额（元）',
  `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `t_car` */

DROP TABLE IF EXISTS `t_car`;

CREATE TABLE `t_car` (
    `car_id` bigint(19) NOT NULL COMMENT '主键',
    `name` varchar(50) NOT NULL COMMENT '车位名',
    `state` tinyint(1) DEFAULT '0' COMMENT '使用状态（判断是否开始使用）',
    `start_date` varchar(10) NOT NULL COMMENT '性别',
    `end_date` datetime DEFAULT NULL COMMENT '结束使用时间',
    `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
    `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`car_id`)

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `t_car_park`;

CREATE TABLE `t_car_park` (
   `car_park_id` bigint(19) NOT NULL COMMENT '主键',
   `province` varchar(100) NOT NULL COMMENT '省份',
   `city` varchar(100) NOT NULL COMMENT '城市',
   `address` varchar(500) NOT NULL COMMENT '停车场地址',
   `longitude` varchar(20) NOT NULL COMMENT '经度',
   `latitude` varchar(20) NOT NULL COMMENT '纬度',
   `car_park_name` varchar(100) DEFAULT NULL COMMENT '停车场名字',
   `gmt_create` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '停车场注册时间',
   `gmt_modified` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '停车场信息更新时间',
   PRIMARY KEY (`car_park_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
