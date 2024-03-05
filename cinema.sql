/*
 Navicat Premium Data Transfer

 Source Server         : xampp
 Source Server Type    : MySQL
 Source Server Version : 100427
 Source Host           : localhost:3306
 Source Schema         : cinema

 Target Server Type    : MySQL
 Target Server Version : 100427
 File Encoding         : 65001

 Date: 01/06/2023 01:15:04
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for arrange
-- ----------------------------
DROP TABLE IF EXISTS `arrange`;
CREATE TABLE `arrange`  (
  `movid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hallid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `stime` datetime NOT NULL,
  INDEX `movarr`(`movid` ASC) USING BTREE,
  INDEX `hallarr`(`hallid` ASC) USING BTREE,
  CONSTRAINT `hallarr` FOREIGN KEY (`hallid`) REFERENCES `hall` (`hallid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `movarr` FOREIGN KEY (`movid`) REFERENCES `movie` (`movid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of arrange
-- ----------------------------

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `cid` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cname` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`cid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('c00', '恐怖');
INSERT INTO `category` VALUES ('c01', '喜剧');
INSERT INTO `category` VALUES ('c02', '爱情');
INSERT INTO `category` VALUES ('c03', '科幻');
INSERT INTO `category` VALUES ('c04', '亲情');
INSERT INTO `category` VALUES ('c05', '文艺');

-- ----------------------------
-- Table structure for hall
-- ----------------------------
DROP TABLE IF EXISTS `hall`;
CREATE TABLE `hall`  (
  `hallid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hallsize` smallint NOT NULL,
  PRIMARY KEY (`hallid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hall
-- ----------------------------
INSERT INTO `hall` VALUES ('h01', 80);
INSERT INTO `hall` VALUES ('h02', 100);
INSERT INTO `hall` VALUES ('h03', 80);
INSERT INTO `hall` VALUES ('h04', 20);

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `movid` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `movname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `cid` varchar(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `movdate` date NOT NULL,
  `movtime` smallint NOT NULL,
  PRIMARY KEY (`movid`) USING BTREE,
  INDEX `movc`(`cid` ASC) USING BTREE,
  CONSTRAINT `movc` FOREIGN KEY (`cid`) REFERENCES `category` (`cid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES ('m01', '泡面', 'c03', '2023-01-01', 120);
INSERT INTO `movie` VALUES ('m02', '电风扇', 'c00', '2023-05-31', 120);
INSERT INTO `movie` VALUES ('m03', '行李箱', 'c01', '2023-05-20', 120);
INSERT INTO `movie` VALUES ('m09', '金木水火土', 'c00', '2023-09-01', 100);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `account` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pw` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`account`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('admin', 'admin');
INSERT INTO `users` VALUES ('Fiii', '20021005');

-- ----------------------------
-- Table structure for viphall
-- ----------------------------
DROP TABLE IF EXISTS `viphall`;
CREATE TABLE `viphall`  (
  `hallid` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `vhstyle` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  INDEX `hallid`(`hallid` ASC) USING BTREE,
  CONSTRAINT `hallid` FOREIGN KEY (`hallid`) REFERENCES `hall` (`hallid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of viphall
-- ----------------------------
INSERT INTO `viphall` VALUES ('h04', '双人座');

-- ----------------------------
-- View structure for arr
-- ----------------------------
DROP VIEW IF EXISTS `arr`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `arr` AS (select movname,cname,hallid,stime,arrange.movid from arrange left join movie on arrange.movid=movie.movid left join category on movie.cid=category.cid) ;

-- ----------------------------
-- View structure for movc
-- ----------------------------
DROP VIEW IF EXISTS `movc`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `movc` AS (select movid,movname,movie.cid,cname,movdate,movtime from movie left join category on movie.cid=category.cid) ;

-- ----------------------------
-- Procedure structure for addmov
-- ----------------------------
DROP PROCEDURE IF EXISTS `addmov`;
delimiter ;;
CREATE PROCEDURE `addmov`(in mymovid varchar(5),
	in mymovname varchar(20),
	in mycid varchar(5),
	in mymovdate DATE,
	in mymovtime SMALLINT,
	in mycname varchar(10),
	out rtn int)
BEGIN
	if EXISTS (select * from category where cid=mycid) then 
		BEGIN
			if mycname NOT in (select cname from category where cid=mycid) then 
				BEGIN
					set rtn=0;
				END;
			ELSE
				BEGIN
					if EXISTS (select * from movie where movid=mymovid) then
						BEGIN
							if mymovname in (select movname from movie where movid=mymovid) then
								BEGIN
									UPDATE movie set cid=mycid,movdate=mymovdate,movtime=mymovtime where movid=mymovid;
									set rtn=1;
								END;
							ELSE
								BEGIN
									set rtn=2;
								END;
							end if;
						END;
					ELSE
						BEGIN
							INSERT into movie values (mymovid,mymovname,mycid,mymovdate,mymovtime);
							set rtn=3;
						END;
					end if;
				END;
			END if;
		END;
	ELSE
		BEGIN
			insert into category values (mycid,mycname);
			if EXISTS (select * from movie where movid=mymovid) then
				BEGIN
					if mymovname in (select movname from movie where movid=mymovid) then
						BEGIN
							UPDATE movie set cid=mycid,movdate=mymovdate,movtime=mymovtime where movid=mymovid;
							set rtn=1;
						END;
					ELSE
						BEGIN
							set rtn=2;
						END;
					end if;
				END;
			ELSE
				BEGIN
					INSERT into movie values (mymovid,mymovname,mycid,mymovdate,mymovtime);
					set rtn=3;
				END;
			end if;
		END;
	end if;
END
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table arrange
-- ----------------------------
DROP TRIGGER IF EXISTS `addarr`;
delimiter ;;
CREATE TRIGGER `addarr` BEFORE INSERT ON `arrange` FOR EACH ROW BEGIN
if NEW.movid not in (select movid from movie)
or NEW.hallid not in (select hallid from hall)
or EXISTS (select * from (select arrange.movid,hallid,stime,(DATE_ADD(stime,INTERVAL movtime MINUTE)) as etime from arrange left join movie on arrange.movid=movie.movid) t1 where new.stime BETWEEN t1.stime and t1.etime) THEN SIGNAL SQLSTATE 'HY000';
end if;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table movie
-- ----------------------------
DROP TRIGGER IF EXISTS `movctrigger`;
delimiter ;;
CREATE TRIGGER `movctrigger` BEFORE INSERT ON `movie` FOR EACH ROW begin
if NEW.cid not in (select cid from category) or NEW.movid = '' or NEW.movname = '' THEN SIGNAL SQLSTATE 'HY000';
end if;
end
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
