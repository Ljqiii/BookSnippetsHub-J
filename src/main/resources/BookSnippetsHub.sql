-- MySQL dump 10.13  Distrib 8.0.16, for macos10.14 (x86_64)
--
-- Host: localhost    Database: BookSnippetsHub
-- ------------------------------------------------------
-- Server version	8.0.16

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `BackgroundImage`
--

DROP TABLE IF EXISTS `BackgroundImage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `BackgroundImage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `filename` varchar(50) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `BackgroundImage`
--

LOCK TABLES `BackgroundImage` WRITE;
/*!40000 ALTER TABLE `BackgroundImage` DISABLE KEYS */;
INSERT INTO `BackgroundImage` VALUES (1,'default','default.png',NULL),(2,'1','11.png',NULL),(4,'red','red.png',NULL),(5,'sd','22.png',NULL),(6,'sdf','33.png',NULL),(7,'sadf','33.png',NULL),(8,'sdaf','33.png',NULL);
/*!40000 ALTER TABLE `BackgroundImage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `book`
--

DROP TABLE IF EXISTS `book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `bookcoverimg` varchar(50) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES (4,'一个人的好天气','11.png','新中国成立70年来成就辉煌，离不开千千万万人的接续奋斗。他们中，有一辈子坚守初心、不改本色的张富清，有无怨无悔守岛卫国32年的王继才，有司法改革“燃灯者”邹碧华，有始终保持艰苦奋斗精神的龚全珍，还有万米高空完成生死迫降的川航“中国民航英雄机组”……sd'),(5,'等风的人','11.png','b555'),(6,'切尔诺贝利的祭祷',NULL,NULL);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `booklike`
--

DROP TABLE IF EXISTS `booklike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `booklike` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(40) DEFAULT NULL,
  `bookid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `booklike`
--

LOCK TABLES `booklike` WRITE;
/*!40000 ALTER TABLE `booklike` DISABLE KEYS */;
INSERT INTO `booklike` VALUES (13,'owh1O5ckqseCFh8ZDmT6mpA7zpPQ',3),(14,'owh1O5ckqseCFh8ZDmT6mpA7zpPQ',2),(15,'owh1O5ckqseCFh8ZDmT6mpA7zpPQ',4);
/*!40000 ALTER TABLE `booklike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `feed` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(40) NOT NULL,
  `backgroundimageid` int(11) DEFAULT NULL,
  `bookcontent` text,
  `bookcomment` text,
  `feedtime` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `fromopenid` varchar(30) DEFAULT NULL,
  `bookid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
INSERT INTO `feed` VALUES (25,'owh1O5ckqseCFh8ZDmT6mpA7zpPQ',1,'我喜欢在细雨绵绵的天气里行走，雨丝打在脸上，没有油纸伞，没有青石板，但是有你在我心里那便够了\n','遇见这本书很幸运，像极了我青涩时光里遇见了你','2019-05-28 09:01:42','owh1O5ckqseCFh8ZDmT6mpA7zpPQ',4),(26,'owh1O5YVpWdai2Yig0cAVT6sXwvU',6,'他闭上眼，继续向前走。身体忽然下沉，他下意识地拨动双脚，在水中立泳。真累啊，他轻声嘟囔着笑了。\n　　沉下去试试？\n　　就在这时，他忽然感到脚被什么东西吸住了，身体迅速下沉。呼吸困难，双手在水中扑腾起来，可伸出的手脚什么也触碰不到。\n　　他双脚拼命向上蹬，有一瞬间，头露出了海面，能够呼吸了。可是身体立刻又被吸入水中。在这之前，他明明觉得死了也无所谓，可是身体却自作主张地动起来，为了获得空气在海水里挣扎。然而他被某种力量吸着卷着，离水面越来越远。','累了。总之就是累了。让身体随波逐流，获得解脱吧。\n很多时候都会有这种感觉，怎样才能解脱啊，想过死但是又怕死不想死。想死只是想解脱，不想压力不想没完没了的烦心事。不想死是没活够，还有好多美好在等着我。难怪我一点都不怕坐过山车和跳楼机，还经常想着哪天去蹦极或者来个高空跳伞。兴许就是能体验死亡那一瞬间的解脱吧。别的什么也不想，就想着能活下来真好，人活在世没什么比生死更大的事，没有什么过不了的坎。','2019-05-28 09:01:57','owh1O5YVpWdai2Yig0cAVT6sXwvU',5),(27,'owh1O5YVpWdai2Yig0cAVT6sXwvU',5,'      遇见我的人都很诧异……无法理解……“你对自己的孩子做了什么，你要杀死他们吗？你这是在自杀。”我不是要杀死他们，我在拯救他们。我才四十岁，头发就已经全白了——才四十岁啊！一次，他们领回家一个德国记者，他问我：“你会把孩子带到一个有鼠疫或霍乱的地方吗？”鼠疫或霍乱……我不知道这里有这些可怕的东西。我没有看到。它也不在我的记忆里……\n       我害怕人……怕带枪的人……','切尔诺贝利没有坏人没有官僚没有战争没有种族差异……除了辐射外，什么坏问题都没有，人与人之间团结有爱彼此照顾。正是因为有辐射，人人避之不及，所以切尔诺贝利才成为天堂般的存在，才成了为躲避战争背井离乡的难民的安乐窝。人们畏惧战争胜于辐射，战争时朝不保夕，所以更愿意在辐射的环境下过安稳日子。真的很讽刺，曾经的不毛之地会成为人间乐土。这些逃难来到切尔诺贝利的人从某种意义上来说也是“逆行者”。','2019-05-28 09:14:28','owh1O5YVpWdai2Yig0cAVT6sXwvU',6);
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedcomment`
--

DROP TABLE IF EXISTS `feedcomment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `feedcomment` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `feedid` int(11) DEFAULT NULL,
  `openid` varchar(40) DEFAULT NULL,
  `comment` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=90 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedcomment`
--

LOCK TABLES `feedcomment` WRITE;
/*!40000 ALTER TABLE `feedcomment` DISABLE KEYS */;
INSERT INTO `feedcomment` VALUES (79,1,'owh1O5ckqseCFh8ZDmT6mpA7zpPQ','test comment'),(80,26,'54d5ae59-0821-41f6-8e07-307bef4537c6','好');
/*!40000 ALTER TABLE `feedcomment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedlike`
--

DROP TABLE IF EXISTS `feedlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `feedlike` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `feedid` int(11) DEFAULT NULL,
  `openid` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedlike`
--

LOCK TABLES `feedlike` WRITE;
/*!40000 ALTER TABLE `feedlike` DISABLE KEYS */;
INSERT INTO `feedlike` VALUES (82,34,'54d5ae59-0821-41f6-8e07-307bef4537c6'),(83,23,'54d5ae59-0821-41f6-8e07-307bef4537c6'),(104,23,'54d5ae59-0821-41f6-8e07-307bef4537c6'),(106,29,'83f9e43e-7a9a-4326-a90c-9b98bfc979fc');
/*!40000 ALTER TABLE `feedlike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follow`
--

DROP TABLE IF EXISTS `follow`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `follow` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openid` varchar(40) NOT NULL,
  `followopenid` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=78 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follow`
--

LOCK TABLES `follow` WRITE;
/*!40000 ALTER TABLE `follow` DISABLE KEYS */;
INSERT INTO `follow` VALUES (76,'54d5ae59-0821-41f6-8e07-307bef4537c6','owh1O5YVpWdai2Yig0cAVT6sXwvU'),(77,'f7b5bb2c-e919-47fa-92e5-7129761a3a41','owh1O5YVpWdai2Yig0cAVT6sXwvU');
/*!40000 ALTER TABLE `follow` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification`
--

DROP TABLE IF EXISTS `notification`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `notification` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `fromopenid` varchar(40) NOT NULL,
  `toopenid` varchar(40) NOT NULL,
  `msg` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=113 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification`
--

LOCK TABLES `notification` WRITE;
/*!40000 ALTER TABLE `notification` DISABLE KEYS */;
INSERT INTO `notification` VALUES (1,'0','83f9e43e-7a9a-4326-a90c-9b98bfc979fc','注册成功，欢迎您'),(67,'owh1O5ckqseCFh8ZDmT6mpA7zpPQ','owh1O5ckqseCFh8ZDmT6mpA7zpPQ','雨过天晴、彩虹不在喜欢了你的分享');
/*!40000 ALTER TABLE `notification` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wxaccount`
--

DROP TABLE IF EXISTS `wxaccount`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `wxaccount` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `openId` varchar(40) NOT NULL,
  `session_key` varchar(25) DEFAULT NULL,
  `nickname` varchar(20) DEFAULT NULL,
  `gender` varchar(2) DEFAULT NULL,
  `city` varchar(20) DEFAULT NULL,
  `province` varchar(20) DEFAULT NULL,
  `country` varchar(20) DEFAULT NULL,
  `avatarUrl` varchar(300) DEFAULT NULL,
  `encodedPassword` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `openId` (`openId`)
) ENGINE=InnoDB AUTO_INCREMENT=128 DEFAULT CHARSET=utf8mb4 ;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wxaccount`
--

LOCK TABLES `wxaccount` WRITE;
/*!40000 ALTER TABLE `wxaccount` DISABLE KEYS */;
INSERT INTO `wxaccount` VALUES (16,'owh1O5ckqseCFh8ZDmT6mpA7zpPQ','','雨过天晴、彩虹不在','1','Chengde','Hebei','China','https://wx.qlogo.cn/mmopen/vi_32/DYAIOgq83eq7jPmQqBsraAtkzlJ3HicM5Occ95LNjiapOJKvAJHeZhwOhgsq0XNIfKfgiamgGfLfysaIwwDaeUygg/132',NULL),(127,'83f9e43e-7a9a-4326-a90c-9b98bfc979fc',NULL,'1111',NULL,NULL,NULL,NULL,NULL,'$2a$10$pkdFZ/DVs66NoIXk6KohDuSQxQDBJ66oBuMTYuRRUIjWa5BuecrN2');
/*!40000 ALTER TABLE `wxaccount` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-18  5:27:26
