/*
 Navicat MySQL Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : store

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 12/01/2023 18:17:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_product
-- ----------------------------
DROP TABLE IF EXISTS `t_product`;
CREATE TABLE `t_product` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `category_id` int DEFAULT NULL COMMENT '分类id',
  `item_type` varchar(100) DEFAULT NULL COMMENT '商品系列',
  `title` varchar(100) DEFAULT NULL COMMENT '商品标题',
  `sell_point` varchar(150) DEFAULT NULL COMMENT '商品卖点',
  `price` bigint DEFAULT NULL COMMENT '商品单价',
  `num` int DEFAULT NULL COMMENT '库存数量',
  `image` varchar(500) DEFAULT NULL COMMENT '图片路径',
  `status` int DEFAULT '1' COMMENT '商品状态  1：上架   2：下架   3：删除',
  `priority` int DEFAULT NULL COMMENT '显示优先级',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modified_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `created_user` varchar(50) DEFAULT NULL COMMENT '创建人',
  `modified_user` varchar(50) DEFAULT NULL COMMENT '最后修改人',
  `image_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '主图片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000023 DEFAULT CHARSET=utf8mb3;

SET FOREIGN_KEY_CHECKS = 1;
