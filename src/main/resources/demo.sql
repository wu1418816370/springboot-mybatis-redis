-- Database export via SQLPro (https://www.sqlprostudio.com/allapps.html)
-- Exported by wutianrui at 01-04-2021 13:40.
-- WARNING: This file may contain descructive statements such as DROPs.
-- Please ensure that you are running the script at the proper location.


-- BEGIN TABLE user
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) COLLATE utf8_bin NOT NULL COMMENT '用户名称',
  `age` int NOT NULL COMMENT '年龄',
  `score` varchar(10) COLLATE utf8_bin NOT NULL COMMENT '分数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- Table `user` contains no data. No inserts have been genrated.
-- Inserting 0 rows into `user`


-- END TABLE user

