/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : minemanage

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 02/02/2021 16:25:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_b_mine_uniapp_apk
-- ----------------------------
DROP TABLE IF EXISTS `t_b_mine_uniapp_apk`;
CREATE TABLE `t_b_mine_uniapp_apk`  (
  `ID` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'ID',
  `is_silent` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否静默下载  0：否  1：是',
  `is_force` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否强制安装  0：否  1：是',
  `is_auto_install` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否下载完成后自动安装   0：否  1：是',
  `is_ignorable` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否可忽略该版本   0：否  1：是',
  `version_code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本号编码',
  `version_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '版本号名称',
  `update_content` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '本版更新内容',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'apk存放地址',
  `md5` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'apk的md5',
  `size` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'apk文件大小',
  `is_delete` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否删除    0：否  1：是',
  `update_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '修改人id',
  `create_name` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人',
  `create_date` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建人id',
  `mine_id` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '矿井ID',
  `is_current_version` varchar(10) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '0' COMMENT '是否当前版本 0：否  1：是',
  PRIMARY KEY (`ID`) USING BTREE,
  INDEX `FK_knnm3wb0bembwvm0il7tf6686`(`is_force`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_b_mine_uniapp_apk
-- ----------------------------
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('40287181772813940177288fadf40011', NULL, NULL, NULL, NULL, '菜单', '', '菜单', NULL, '菜单', '传', NULL, NULL, NULL, NULL, '管理员', '2021-01-22 13:27:20', 'admin', '402883f36394afa3016394d093140018', '0');
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('402871817728a5e2017728aec74d0003', NULL, NULL, NULL, NULL, '', '', '', NULL, '', '', NULL, NULL, NULL, NULL, '管理员', '2021-01-22 14:01:18', 'admin', '402883f36394afa3016394d093140018', '0');
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('402871817728a5e2017728afb83a0007', NULL, NULL, NULL, NULL, '测试一下', '测试一下', '测试一下', NULL, '', '', NULL, NULL, NULL, NULL, '管理员', '2021-01-22 14:02:20', 'admin', '402883f36394afa3016394d093140018', '0');
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('402871817728a5e2017728b6ba21000b', NULL, NULL, NULL, NULL, '成都市', '是是大Vad的', '但是', NULL, '', '', NULL, NULL, NULL, NULL, '管理员', '2021-01-22 14:09:59', 'admin', '402883f36394afa3016394d093140018', '0');
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('402871817728bb32017728bc372b0001', NULL, NULL, NULL, NULL, '打成', '代收点', '颠倒是非', 'commonController.do?viewFile&fileid=402871817728bb32017728bc91830003', '', '79.37', NULL, '管理员', '2021-01-22 14:17:03', 'admin', '管理员', '2021-01-22 14:15:59', 'admin', '402883f36394afa3016394d093140018', '1');
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('402871817728bb32017728f553da0006', NULL, NULL, NULL, NULL, '', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '管理员', '2021-01-22 15:18:22', 'admin', '402883f36394afa3016394d093140018', '0');
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('402871817728bb32017729018150000a', NULL, NULL, NULL, NULL, '分为区分', '', '', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '管理员', '2021-01-22 15:31:40', 'admin', '402883f36394afa3016394d093140018', '0');
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('40287181773710790177373da7770003', NULL, NULL, NULL, NULL, '1.0.0', '最新版本', '最新版本', NULL, NULL, NULL, NULL, NULL, NULL, NULL, '管理员', '2021-01-25 09:52:03', 'admin', '402883f36394afa3016394d093140018', '0');
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('40287181773752a70177375419000001', NULL, NULL, NULL, NULL, '1.2.0', 'dsadDSF', 'DFAS公司打广告股份收到货和煽风点火', 'commonController.do?viewFile&fileid=402871817728bb32017728bc91830003', NULL, NULL, NULL, NULL, NULL, NULL, '管理员', '2021-01-25 10:16:34', 'admin', '402883f36394afa3016394d093140018', '0');
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('40287181773c2b8701773c3d54f00003', NULL, NULL, NULL, NULL, '1.0.0', '第一版', '第一版：矿上项目  安全系统', 'commonController.do?viewFile&fileid=40287181773c2b8701773c3da85a0005', NULL, '79.37', NULL, '管理员', '2021-01-26 09:10:28', 'admin', '管理员', '2021-01-26 09:09:48', 'admin', '402883f36394afa3016394d1c067001a', '0');
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('40287181773cbd7d01773d86d7a70007', NULL, NULL, NULL, NULL, '1.0.1', '第二版', '添加版本控制功能', 'commonController.do?viewFile&fileid=40287181773cbd7d01773d86e4180009', NULL, '79.37', NULL, '管理员', '2021-01-26 15:09:47', 'admin', '管理员', '2021-01-26 15:09:43', 'admin', '402883f36394afa3016394d1c067001a', '1');
INSERT INTO `t_b_mine_uniapp_apk` VALUES ('4028718177467b67017746bc23870007', NULL, NULL, NULL, NULL, '1.0.0', '新巨龙第一版', '方得分为', 'commonController.do?viewFile&fileid=4028718177467b67017746bc80450009', NULL, '79.37', NULL, '管理员', '2021-01-28 10:04:55', 'admin', '管理员', '2021-01-28 10:04:30', 'admin', '402883f36394afa3016394d49fca001c', '1');

SET FOREIGN_KEY_CHECKS = 1;
