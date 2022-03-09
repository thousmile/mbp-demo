/*
 Navicat Premium Data Transfer

 Source Server         : ML-MYSQL8
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : rm-wz9u8t02hg3s52482io.mysql.rds.aliyuncs.com:3306
 Source Schema         : jpa_demo

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 09/03/2022 12:29:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ml_area
-- ----------------------------
DROP TABLE IF EXISTS `ml_area`;
CREATE TABLE `ml_area`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '区域id',
  `tenant_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '租户ID',
  `area_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '区域名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '[ 租户 ] 区域表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ml_area
-- ----------------------------
INSERT INTO `ml_area` VALUES (1, '4b7aab', '林海山庄');
INSERT INTO `ml_area` VALUES (2, '4b7aab', '香槟花园');
INSERT INTO `ml_area` VALUES (3, '4b7aab', '保利世贸中心');
INSERT INTO `ml_area` VALUES (6, 'cb63622', '林海三号');
INSERT INTO `ml_area` VALUES (7, 'cb63622', '世贸中心');

SET FOREIGN_KEY_CHECKS = 1;
