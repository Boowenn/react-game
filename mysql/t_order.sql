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

 Date: 13/01/2023 20:45:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `oid` int NOT NULL AUTO_INCREMENT COMMENT '订单id',
  `uid` int NOT NULL COMMENT '用户id',
  `recv_name` varchar(20) NOT NULL COMMENT '收货人姓名',
  `recv_phone` varchar(20) DEFAULT NULL COMMENT '收货人电话',
  `recv_province` varchar(15) DEFAULT NULL COMMENT '收货人所在省',
  `recv_city` varchar(15) DEFAULT NULL COMMENT '收货人所在市',
  `recv_area` varchar(15) DEFAULT NULL COMMENT '收货人所在区',
  `recv_address` varchar(50) DEFAULT NULL COMMENT '收货详细地址',
  `total_price` bigint DEFAULT NULL COMMENT '总价',
  `status` int DEFAULT NULL COMMENT '状态：0-未支付，1-已支付，2-已取消，3-已关闭，4-已完成',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '支付时间',
  `created_user` varchar(20) DEFAULT NULL COMMENT '创建人',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_user` varchar(20) DEFAULT NULL COMMENT '修改人',
  `modified_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
