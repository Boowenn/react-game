/*
 Navicat MySQL Data Transfer

 Source Server         : gameshopping
 Source Server Type    : MySQL
 Source Server Version : 80027 (8.0.27)
 Source Host           : localhost:3306
 Source Schema         : game

 Target Server Type    : MySQL
 Target Server Version : 80027 (8.0.27)
 File Encoding         : 65001

 Date: 13/01/2023 20:45:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_address
-- ----------------------------
DROP TABLE IF EXISTS `t_address`;
CREATE TABLE `t_address` (
  `aid` int NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `uid` int DEFAULT NULL COMMENT '归属的用户id',
  `name` varchar(20) DEFAULT NULL COMMENT '收货人姓名',
  `province_name` varchar(15) DEFAULT NULL COMMENT '省-名称',
  `province_code` char(6) DEFAULT NULL COMMENT '省-行政代号',
  `city_name` varchar(15) DEFAULT NULL COMMENT '市-名称',
  `city_code` char(6) DEFAULT NULL COMMENT '市-行政代号',
  `area_name` varchar(15) DEFAULT NULL COMMENT '区-名称',
  `area_code` char(6) DEFAULT NULL COMMENT '区-行政代号',
  `zip` char(6) DEFAULT NULL COMMENT '邮政编码',
  `address` varchar(50) DEFAULT NULL COMMENT '详细地址',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机',
  `tel` varchar(20) DEFAULT NULL COMMENT '固话',
  `tag` varchar(6) DEFAULT NULL COMMENT '标签',
  `is_default` int DEFAULT NULL COMMENT '是否默认：0-不默认，1-默认',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_address
-- ----------------------------
BEGIN;
INSERT INTO `t_address` (`aid`, `uid`, `name`, `province_name`, `province_code`, `city_name`, `city_code`, `area_name`, `area_code`, `zip`, `address`, `phone`, `tel`, `tag`, `is_default`, `created_user`, `created_time`, `modified_user`, `modified_time`) VALUES (1, 2, '小明', '北京市', '110000', '市辖区', '110100', '东城区', '110101', '12333', '123123123', '123123', NULL, NULL, 1, 'panghu', '2023-01-10 17:38:49', 'panghu', '2023-01-10 17:38:49');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
