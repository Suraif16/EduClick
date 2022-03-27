-- MySQL dump 10.13  Distrib 8.0.26, for Win64 (x86_64)
--
-- Host: localhost    Database: educlickdb
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `add_friends`
--

DROP TABLE IF EXISTS `add_friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `add_friends` (
  `UserID` bigint NOT NULL,
  `Friend_UserID` bigint NOT NULL,
  PRIMARY KEY (`UserID`,`Friend_UserID`),
  KEY `Friend_UserID` (`Friend_UserID`),
  CONSTRAINT `add_friends_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `add_friends_ibfk_2` FOREIGN KEY (`Friend_UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `add_friends`
--

LOCK TABLES `add_friends` WRITE;
/*!40000 ALTER TABLE `add_friends` DISABLE KEYS */;
INSERT INTO `add_friends` VALUES (2,1),(4,1),(6,1),(1,2),(1,4),(1,6);
/*!40000 ALTER TABLE `add_friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `EmailID` varchar(400) NOT NULL,
  `Password` char(64) NOT NULL,
  `AdminName` varchar(50) NOT NULL,
  PRIMARY KEY (`EmailID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES ('rahuram666@gmail.com','dfe338b7c702ebc9fac2339f130510539ee7049d7106c612bba48e5ef7d9d4e7','Rahuram');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `admin_post_system_updates`
--

DROP TABLE IF EXISTS `admin_post_system_updates`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin_post_system_updates` (
  `SysPostID` bigint NOT NULL AUTO_INCREMENT,
  `APTextMsg` varchar(8000) DEFAULT NULL,
  `APPhoto` bigint DEFAULT NULL,
  `APTime` time NOT NULL,
  `APDate` date NOT NULL,
  PRIMARY KEY (`SysPostID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin_post_system_updates`
--

LOCK TABLES `admin_post_system_updates` WRITE;
/*!40000 ALTER TABLE `admin_post_system_updates` DISABLE KEYS */;
/*!40000 ALTER TABLE `admin_post_system_updates` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer` (
  `AnswerID` bigint NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `Marks` int DEFAULT NULL,
  PRIMARY KEY (`AnswerID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'2022-03-25','23:50:05',90),(2,'2022-03-25','23:51:16',100),(3,'2022-03-25','23:51:44',84),(4,'2022-03-26','08:42:29',90),(5,'2022-03-26','08:44:21',NULL),(6,'2022-03-26','08:44:38',NULL),(7,'2022-03-26','08:49:00',NULL),(8,'2022-03-26','08:49:26',NULL),(9,'2022-03-26','12:39:21',100);
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `answer_student_post_relationship`
--

DROP TABLE IF EXISTS `answer_student_post_relationship`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `answer_student_post_relationship` (
  `S_UserID` bigint NOT NULL,
  `AnswerID` bigint NOT NULL,
  `EPostID` bigint NOT NULL,
  PRIMARY KEY (`S_UserID`,`AnswerID`,`EPostID`),
  KEY `AnswerID` (`AnswerID`),
  KEY `EPostID` (`EPostID`),
  CONSTRAINT `answer_student_post_relationship_ibfk_1` FOREIGN KEY (`S_UserID`) REFERENCES `student` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_student_post_relationship_ibfk_2` FOREIGN KEY (`AnswerID`) REFERENCES `answer` (`AnswerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `answer_student_post_relationship_ibfk_3` FOREIGN KEY (`EPostID`) REFERENCES `educationalpost` (`EPostID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer_student_post_relationship`
--

LOCK TABLES `answer_student_post_relationship` WRITE;
/*!40000 ALTER TABLE `answer_student_post_relationship` DISABLE KEYS */;
INSERT INTO `answer_student_post_relationship` VALUES (3,9,8);
/*!40000 ALTER TABLE `answer_student_post_relationship` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bell_icon`
--

DROP TABLE IF EXISTS `bell_icon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bell_icon` (
  `UserID` bigint NOT NULL,
  `Click_Date` date DEFAULT NULL,
  `Click_Time` time DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `bell_icon_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bell_icon`
--

LOCK TABLES `bell_icon` WRITE;
/*!40000 ALTER TABLE `bell_icon` DISABLE KEYS */;
INSERT INTO `bell_icon` VALUES (1,'2022-03-27','12:59:16'),(2,'2022-03-25','23:04:13'),(3,'2022-03-25','23:08:05'),(4,'2022-03-25','23:34:26'),(5,'2022-03-26','07:43:41'),(6,'2022-03-26','11:32:52'),(7,'2022-03-26','11:48:24'),(8,'2022-03-26','15:34:04'),(9,'2022-03-27','10:45:13');
/*!40000 ALTER TABLE `bell_icon` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `classroom`
--

DROP TABLE IF EXISTS `classroom`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classroom` (
  `ClassroomID` bigint NOT NULL AUTO_INCREMENT,
  `CR_Name` varchar(50) NOT NULL,
  `YearOfExamination` varchar(4) DEFAULT NULL,
  `Grade` varchar(20) DEFAULT NULL,
  `Subject` varchar(20) DEFAULT NULL,
  `UserID` bigint NOT NULL,
  PRIMARY KEY (`ClassroomID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `classroom_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `teacher` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (2,'Headway Institute','2024','12','Physics',2),(4,'Headway','2025','12','ICT',1),(5,'Alpha','2023','11','maths',1);
/*!40000 ALTER TABLE `classroom` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educationalpost`
--

DROP TABLE IF EXISTS `educationalpost`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `educationalpost` (
  `EPostID` bigint NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `EPtype` enum('EducationalWork','MCQ') NOT NULL,
  `Type` enum('Question','Messages') NOT NULL,
  `ClassroomID` bigint NOT NULL,
  PRIMARY KEY (`EPostID`),
  KEY `ClassroomID` (`ClassroomID`),
  CONSTRAINT `educationalpost_ibfk_1` FOREIGN KEY (`ClassroomID`) REFERENCES `classroom` (`ClassroomID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationalpost`
--

LOCK TABLES `educationalpost` WRITE;
/*!40000 ALTER TABLE `educationalpost` DISABLE KEYS */;
INSERT INTO `educationalpost` VALUES (8,'2022-03-26','12:22:41','MCQ','Question',4),(9,'2022-03-27','11:43:27','EducationalWork','Question',4),(10,'2022-03-27','12:15:06','EducationalWork','Question',4);
/*!40000 ALTER TABLE `educationalpost` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `educationalwork`
--

DROP TABLE IF EXISTS `educationalwork`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `educationalwork` (
  `EPostID` bigint NOT NULL,
  `ImageStatus` enum('true','false') DEFAULT NULL,
  `Caption` varchar(8000) DEFAULT NULL,
  PRIMARY KEY (`EPostID`),
  CONSTRAINT `educationalwork_ibfk_1` FOREIGN KEY (`EPostID`) REFERENCES `educationalpost` (`EPostID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationalwork`
--

LOCK TABLES `educationalwork` WRITE;
/*!40000 ALTER TABLE `educationalwork` DISABLE KEYS */;
INSERT INTO `educationalwork` VALUES (9,'true',''),(10,'true','');
/*!40000 ALTER TABLE `educationalwork` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edw_answers`
--

DROP TABLE IF EXISTS `edw_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `edw_answers` (
  `AnswerID` bigint NOT NULL,
  `Content` varchar(8000) DEFAULT NULL,
  `ImageStatus` enum('true','false') DEFAULT NULL,
  PRIMARY KEY (`AnswerID`),
  CONSTRAINT `edw_answers_ibfk_1` FOREIGN KEY (`AnswerID`) REFERENCES `answer` (`AnswerID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edw_answers`
--

LOCK TABLES `edw_answers` WRITE;
/*!40000 ALTER TABLE `edw_answers` DISABLE KEYS */;
INSERT INTO `edw_answers` VALUES (2,'The teacher in this picture is hot...','false'),(3,'5','false'),(5,'root 5','false'),(6,'','true'),(7,'root 7','false'),(8,'','true');
/*!40000 ALTER TABLE `edw_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enroll`
--

DROP TABLE IF EXISTS `enroll`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enroll` (
  `UserID` bigint NOT NULL,
  `ClassroomID` bigint NOT NULL,
  `Status` enum('Enable','Disable') NOT NULL,
  PRIMARY KEY (`UserID`,`ClassroomID`),
  KEY `ClassroomID` (`ClassroomID`),
  CONSTRAINT `enroll_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `enroll_ibfk_2` FOREIGN KEY (`ClassroomID`) REFERENCES `classroom` (`ClassroomID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enroll`
--

LOCK TABLES `enroll` WRITE;
/*!40000 ALTER TABLE `enroll` DISABLE KEYS */;
INSERT INTO `enroll` VALUES (3,4,'Enable');
/*!40000 ALTER TABLE `enroll` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `enroll_request`
--

DROP TABLE IF EXISTS `enroll_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `enroll_request` (
  `From_UserID` bigint NOT NULL,
  `To_ClassroomID` bigint NOT NULL,
  `Req_Time` time NOT NULL,
  `Req_Date` date NOT NULL,
  PRIMARY KEY (`From_UserID`,`To_ClassroomID`),
  KEY `To_ClassroomID` (`To_ClassroomID`),
  CONSTRAINT `enroll_request_ibfk_1` FOREIGN KEY (`From_UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `enroll_request_ibfk_2` FOREIGN KEY (`To_ClassroomID`) REFERENCES `classroom` (`ClassroomID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `enroll_request`
--

LOCK TABLES `enroll_request` WRITE;
/*!40000 ALTER TABLE `enroll_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `enroll_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `follows`
--

DROP TABLE IF EXISTS `follows`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `follows` (
  `S_UserID` bigint NOT NULL,
  `T_UserID` bigint NOT NULL,
  PRIMARY KEY (`S_UserID`,`T_UserID`),
  KEY `T_UserID` (`T_UserID`),
  CONSTRAINT `follows_ibfk_1` FOREIGN KEY (`S_UserID`) REFERENCES `student` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `follows_ibfk_2` FOREIGN KEY (`T_UserID`) REFERENCES `teacher` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `follows`
--

LOCK TABLES `follows` WRITE;
/*!40000 ALTER TABLE `follows` DISABLE KEYS */;
INSERT INTO `follows` VALUES (3,1),(5,1),(5,2),(3,4),(5,4);
/*!40000 ALTER TABLE `follows` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friend_request`
--

DROP TABLE IF EXISTS `friend_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friend_request` (
  `From_UserID` bigint NOT NULL,
  `To_UserID` bigint NOT NULL,
  `Req_Time` time NOT NULL,
  `Req_Date` date NOT NULL,
  PRIMARY KEY (`From_UserID`,`To_UserID`),
  KEY `To_UserID` (`To_UserID`),
  CONSTRAINT `friend_request_ibfk_1` FOREIGN KEY (`From_UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `friend_request_ibfk_2` FOREIGN KEY (`To_UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friend_request`
--

LOCK TABLES `friend_request` WRITE;
/*!40000 ALTER TABLE `friend_request` DISABLE KEYS */;
INSERT INTO `friend_request` VALUES (1,8,'15:33:56','2022-03-26'),(5,3,'07:45:30','2022-03-26');
/*!40000 ALTER TABLE `friend_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `UserID` bigint NOT NULL,
  `NFPostID` bigint NOT NULL,
  PRIMARY KEY (`UserID`,`NFPostID`),
  KEY `NFPostID` (`NFPostID`),
  CONSTRAINT `likes_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `likes_ibfk_2` FOREIGN KEY (`NFPostID`) REFERENCES `newsfeeds` (`NFPostID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1,6),(3,6);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `IncrementLikeCount` AFTER INSERT ON `likes` FOR EACH ROW BEGIN
UPDATE NewsFeeds
SET LikeCount = LikeCount + 1
WHERE NFPostID = NEW.NFPostID;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = cp850 */ ;
/*!50003 SET character_set_results = cp850 */ ;
/*!50003 SET collation_connection  = cp850_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `DecrementLikeCount` AFTER DELETE ON `likes` FOR EACH ROW BEGIN
UPDATE NewsFeeds
SET LikeCount = LikeCount - 1
WHERE NFPostID = OLD.NFPostID;
end */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `login` (
  `EmailID` varchar(400) NOT NULL,
  `Password` char(64) NOT NULL,
  `SaltingKey` varchar(20) NOT NULL,
  `LoginDate` date NOT NULL,
  `LoginTime` time NOT NULL,
  `UserID` bigint NOT NULL,
  `EmailConfirmation` enum('True','False') DEFAULT NULL,
  `PasswordIncorrect` enum('True','False') DEFAULT NULL,
  PRIMARY KEY (`EmailID`),
  KEY `UserID` (`UserID`),
  CONSTRAINT `login_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `login`
--

LOCK TABLES `login` WRITE;
/*!40000 ALTER TABLE `login` DISABLE KEYS */;
INSERT INTO `login` VALUES ('farzanBlaze123@gmail.com','21e6c3ef5fe7dcaaff33914b41659c0e704cf0a5f15f4523c08948eed216a5dc','qZmt0B2nk1cdDk','2022-03-26','15:33:46',8,'True','False'),('farzanroxz123@gmail.com','0c768e7653dfd0535678f02152268334040326f40d8e68763438e22151bd36ef','saBpX49o1/Mt=vt','2022-03-27','23:57:42',1,'True','False'),('jeewa@gmail.com','75bfa87b09ecfebc8d94eb4f74fd1346ea3666d8e9b6107648d7fb14e8b36854','mg5iQBRK92qqQh*E!o5','2022-03-27','22:08:39',3,'True','False'),('m.farzan.rizwan@gmail.com','47e868523aa780b3f876e62cb9e197b7b7cc341247aeb930ffda200f5d47214f','WbMmN>s2lHkLtUy/x7','2022-03-25','23:34:26',4,'True','False'),('primethinker123@gmail.com','874b3cd942c4f3e898760b33e499b0981bdc7462f037b9cfc863676084d5ca90','!gw8fTcB2r0a$_Q&%','2022-03-26','11:48:24',7,'True','False'),('rahuram123@gmail.com','0e17d9d6bf85b9f687f6ebc226a785905319304b28df26cfd65666d86d8f7644','t5.y+W#zXz<ABS+GB%3','2022-03-26','08:48:22',5,'True','False'),('shawnfrost123@gmail.com','e55cfca32433cffbe275c2c9e948da094d6e44aec00bfb82257ca6043db679b8','7dVk@+qK=sE6TRX88g.','2022-03-27','10:45:09',9,'True','False'),('venushka123@gmail.com','7a722d2ebbe46d392fa8efdc73580e0d4151444721bcdc9a57c3a11888df0a73','qMdng@xAW6G','2022-03-26','07:55:57',2,'True','False'),('zahul123@gmail.com','5ca41585dba53ce37ecbe3430e31b567ccc4db1018e3a595f76fe505a70e1c0b','J/NDC_E*vv8>C7gau>','2022-03-26','11:32:52',6,'True','False');
/*!40000 ALTER TABLE `login` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mcq_answers`
--

DROP TABLE IF EXISTS `mcq_answers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mcq_answers` (
  `AnswerID` bigint NOT NULL,
  `Choice` enum('1','2','3','4') NOT NULL,
  `QuestionID` bigint NOT NULL,
  KEY `AnswerID` (`AnswerID`),
  KEY `QuestionID` (`QuestionID`),
  CONSTRAINT `mcq_answers_ibfk_1` FOREIGN KEY (`AnswerID`) REFERENCES `answer` (`AnswerID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `mcq_answers_ibfk_2` FOREIGN KEY (`QuestionID`) REFERENCES `question` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mcq_answers`
--

LOCK TABLES `mcq_answers` WRITE;
/*!40000 ALTER TABLE `mcq_answers` DISABLE KEYS */;
INSERT INTO `mcq_answers` VALUES (9,'1',21),(9,'2',22),(9,'3',23),(9,'4',24),(9,'1',25),(9,'2',26),(9,'3',27),(9,'4',28),(9,'1',29),(9,'2',30);
/*!40000 ALTER TABLE `mcq_answers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `news_feed_image`
--

DROP TABLE IF EXISTS `news_feed_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `news_feed_image` (
  `ImagePath` bigint NOT NULL AUTO_INCREMENT,
  `NFPostID` bigint NOT NULL,
  PRIMARY KEY (`ImagePath`),
  KEY `NFPostID` (`NFPostID`),
  CONSTRAINT `news_feed_image_ibfk_1` FOREIGN KEY (`NFPostID`) REFERENCES `newsfeeds` (`NFPostID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_feed_image`
--

LOCK TABLES `news_feed_image` WRITE;
/*!40000 ALTER TABLE `news_feed_image` DISABLE KEYS */;
INSERT INTO `news_feed_image` VALUES (1,1),(2,2),(3,3),(4,4),(5,5),(6,6),(7,7),(8,8),(9,9),(10,10),(11,11),(12,12),(13,13),(14,14),(15,15),(16,16),(17,17);
/*!40000 ALTER TABLE `news_feed_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `newsfeeds`
--

DROP TABLE IF EXISTS `newsfeeds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `newsfeeds` (
  `NFPostID` bigint NOT NULL AUTO_INCREMENT,
  `Date` date NOT NULL,
  `Time` time NOT NULL,
  `Caption` varchar(8000) DEFAULT NULL,
  `LikeCount` bigint DEFAULT NULL,
  `ShareCount` bigint DEFAULT NULL,
  `ImageStatus` enum('true','false') DEFAULT NULL,
  PRIMARY KEY (`NFPostID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newsfeeds`
--

LOCK TABLES `newsfeeds` WRITE;
/*!40000 ALTER TABLE `newsfeeds` DISABLE KEYS */;
INSERT INTO `newsfeeds` VALUES (1,'2022-03-25','23:14:28','We would like if everyone would join this seminar...',0,0,'true'),(2,'2022-03-25','23:14:52','We would like it if everyone would join this seminar...',0,0,'true'),(3,'2022-03-25','23:16:43','hope this works',0,0,'true'),(4,'2022-03-25','23:17:03','hope this works 1234',0,0,'true'),(5,'2022-03-25','23:18:36','Just having fun...',0,0,'true'),(6,'2022-03-26','00:05:16','Everyone let\'s join this seminar to improve our knowledge...',2,0,'true'),(7,'2022-03-26','07:48:32','',0,0,'true'),(8,'2022-03-26','07:50:55','',0,0,'true'),(9,'2022-03-26','07:51:49','',0,0,'true'),(10,'2022-03-26','07:52:00','',0,0,'true'),(11,'2022-03-26','07:52:20','',0,0,'true'),(12,'2022-03-26','07:52:49','',0,0,'true'),(13,'2022-03-26','07:53:12','',0,0,'true'),(14,'2022-03-26','07:53:39','Consider this...',0,0,'true'),(15,'2022-03-26','07:53:47','Consider this...',0,0,'true'),(16,'2022-03-26','07:56:25','Nothing...',0,0,'true'),(17,'2022-03-26','07:57:35','I don\'t wanna talk about it...',0,0,'true');
/*!40000 ALTER TABLE `newsfeeds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `NotifierID` bigint NOT NULL,
  `NotifieeID` bigint NOT NULL,
  `ContentID` bigint NOT NULL,
  `PostedType` enum('Shared','Posted','Educational Post','Answer','Friend Request','Classroom Request') DEFAULT NULL,
  `Notification_Date` date NOT NULL,
  `Notification_Time` time NOT NULL,
  KEY `NotifierID` (`NotifierID`),
  KEY `NotifieeID` (`NotifieeID`),
  CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`NotifierID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `notifications_ibfk_2` FOREIGN KEY (`NotifieeID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
INSERT INTO `notifications` VALUES (2,1,0,'Friend Request','2022-03-25','23:05:10'),(3,1,0,'Classroom Request','2022-03-25','23:10:32'),(4,1,0,'Friend Request','2022-03-25','23:37:28'),(3,1,0,'Classroom Request','2022-03-25','23:39:58'),(1,3,1,'Educational Post','2022-03-25','23:43:28'),(1,3,2,'Educational Post','2022-03-25','23:44:00'),(1,3,3,'Educational Post','2022-03-25','23:44:42'),(1,3,4,'Educational Post','2022-03-25','23:47:19'),(3,1,3,'Answer','2022-03-25','23:51:16'),(3,1,2,'Answer','2022-03-25','23:51:44'),(5,3,0,'Friend Request','2022-03-26','07:45:29'),(5,1,0,'Classroom Request','2022-03-26','07:47:29'),(1,3,5,'Educational Post','2022-03-26','07:59:41'),(1,5,5,'Educational Post','2022-03-26','07:59:41'),(1,3,6,'Educational Post','2022-03-26','07:59:56'),(1,5,6,'Educational Post','2022-03-26','07:59:56'),(1,3,7,'Educational Post','2022-03-26','08:40:51'),(1,5,7,'Educational Post','2022-03-26','08:40:51'),(3,1,6,'Answer','2022-03-26','08:44:21'),(3,1,5,'Answer','2022-03-26','08:44:38'),(5,1,6,'Answer','2022-03-26','08:49:00'),(5,1,5,'Answer','2022-03-26','08:49:26'),(3,1,0,'Classroom Request','2022-03-26','11:29:25'),(3,1,0,'Classroom Request','2022-03-26','11:29:26'),(6,1,0,'Friend Request','2022-03-26','11:33:16'),(6,4,0,'Friend Request','2022-03-26','11:33:18'),(6,4,0,'Friend Request','2022-03-26','11:40:59'),(1,3,8,'Educational Post','2022-03-26','12:22:42'),(1,8,0,'Friend Request','2022-03-26','15:33:55'),(1,3,9,'Educational Post','2022-03-27','11:43:28'),(1,3,10,'Educational Post','2022-03-27','12:15:07');
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `posts`
--

DROP TABLE IF EXISTS `posts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `posts` (
  `T_UserID` bigint NOT NULL,
  `UserID` bigint NOT NULL,
  `NFPostID` bigint NOT NULL,
  PRIMARY KEY (`T_UserID`,`UserID`,`NFPostID`),
  KEY `UserID` (`UserID`),
  KEY `NFPostID` (`NFPostID`),
  CONSTRAINT `posts_ibfk_1` FOREIGN KEY (`T_UserID`) REFERENCES `teacher` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `posts_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `posts_ibfk_3` FOREIGN KEY (`NFPostID`) REFERENCES `newsfeeds` (`NFPostID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `posts`
--

LOCK TABLES `posts` WRITE;
/*!40000 ALTER TABLE `posts` DISABLE KEYS */;
INSERT INTO `posts` VALUES (2,1,5),(2,1,16),(2,1,17),(1,2,1),(1,2,2),(1,2,3),(1,2,4),(1,2,6),(1,2,7),(1,2,8),(1,2,9),(1,2,10),(1,2,11),(1,2,12),(1,2,13),(1,2,14),(1,2,15),(1,3,1),(1,3,2),(1,3,3),(1,3,4),(1,3,6),(1,3,7),(1,3,8),(1,3,9),(1,3,10),(1,3,11),(1,3,12),(1,3,13),(1,3,14),(1,3,15),(1,4,6),(1,4,7),(1,4,8),(1,4,9),(1,4,10),(1,4,11),(1,4,12),(1,4,13),(1,4,14),(1,4,15),(1,5,7),(1,5,8),(1,5,9),(1,5,10),(1,5,11),(1,5,12),(1,5,13),(1,5,14),(1,5,15),(2,5,16),(2,5,17);
/*!40000 ALTER TABLE `posts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `qualifications`
--

DROP TABLE IF EXISTS `qualifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `qualifications` (
  `UserID` bigint NOT NULL,
  `Qualification` varchar(200) NOT NULL,
  PRIMARY KEY (`UserID`,`Qualification`),
  CONSTRAINT `qualifications_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `teacher` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `qualifications`
--

LOCK TABLES `qualifications` WRITE;
/*!40000 ALTER TABLE `qualifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `qualifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `QuestionID` bigint NOT NULL AUTO_INCREMENT,
  `Question` varchar(8000) DEFAULT NULL,
  `Correct_answers` varchar(8000) DEFAULT NULL,
  `EPostID` bigint NOT NULL,
  PRIMARY KEY (`QuestionID`),
  KEY `EPostID` (`EPostID`),
  CONSTRAINT `question_ibfk_1` FOREIGN KEY (`EPostID`) REFERENCES `educationalpost` (`EPostID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (21,'answer answer answer answer','1',8),(22,'answer answer answer answer','2',8),(23,'answer answer','3',8),(24,'answer answer answer answer answer answer','4',8),(25,'answer answer answer answer answer answer answer answer','1',8),(26,'answer answer answer answer answer answer','2',8),(27,'answer answer answer answer answer answer answer answer answer answer','3',8),(28,'answer answer','4',8),(29,'answer answer','1',8),(30,'answer answer answer answer','2',8);
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question_answer_value`
--

DROP TABLE IF EXISTS `question_answer_value`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question_answer_value` (
  `QuestionID` bigint NOT NULL,
  `Answer_no` enum('1','2','3','4') NOT NULL,
  `Answer` text NOT NULL,
  PRIMARY KEY (`QuestionID`,`Answer_no`),
  CONSTRAINT `question_answer_value_ibfk_1` FOREIGN KEY (`QuestionID`) REFERENCES `question` (`QuestionID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question_answer_value`
--

LOCK TABLES `question_answer_value` WRITE;
/*!40000 ALTER TABLE `question_answer_value` DISABLE KEYS */;
INSERT INTO `question_answer_value` VALUES (21,'1','answer answer'),(21,'2','answer answer'),(21,'3','answer answer'),(21,'4','answer answer'),(22,'1','answer answer'),(22,'2','answer answer answer answer'),(22,'3','answer answer'),(22,'4','answer answer'),(23,'1','answer answer answer answer answer answer'),(23,'2','answer answer'),(23,'3','answer answer'),(23,'4','answer answer'),(24,'1','answer answer'),(24,'2','answer answer'),(24,'3','answer'),(24,'4','answer answer'),(25,'1','answer answer'),(25,'2','answer answer'),(25,'3','answer answer'),(25,'4','answer answer'),(26,'1','answer answer'),(26,'2','answer answer'),(26,'3','answer answer'),(26,'4','answer answer'),(27,'1','answer answer'),(27,'2','answer answer'),(27,'3','answer answer'),(27,'4','answer answer'),(28,'1','answer answer'),(28,'2','answer answer'),(28,'3','answer answer'),(28,'4','answer answer'),(29,'1','answer answer'),(29,'2','answer answer'),(29,'3','answer answer'),(29,'4','answer answer'),(30,'1','answer answer answer answer answer answer answer answer answer answer'),(30,'2','answer answer'),(30,'3','answer answer'),(30,'4','answer answer answer answer answer answer');
/*!40000 ALTER TABLE `question_answer_value` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `report`
--

DROP TABLE IF EXISTS `report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `report` (
  `ReportID` bigint NOT NULL AUTO_INCREMENT,
  `Count` int NOT NULL,
  `Report_delete_flag` tinyint(1) NOT NULL,
  `UserID` bigint DEFAULT NULL,
  `AnswerID` bigint DEFAULT NULL,
  `NF_postID` bigint DEFAULT NULL,
  `EpostID` bigint DEFAULT NULL,
  PRIMARY KEY (`ReportID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `report`
--

LOCK TABLES `report` WRITE;
/*!40000 ALTER TABLE `report` DISABLE KEYS */;
/*!40000 ALTER TABLE `report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `share`
--

DROP TABLE IF EXISTS `share`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `share` (
  `ShareID` bigint NOT NULL AUTO_INCREMENT,
  `Time` time DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `UserID` bigint DEFAULT NULL,
  `ReceiveUserID` bigint DEFAULT NULL,
  `NFPostID` bigint DEFAULT NULL,
  PRIMARY KEY (`ShareID`),
  KEY `UserID` (`UserID`),
  KEY `ReceiveUserID` (`ReceiveUserID`),
  KEY `NFPostID` (`NFPostID`),
  CONSTRAINT `share_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `share_ibfk_2` FOREIGN KEY (`ReceiveUserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `share_ibfk_3` FOREIGN KEY (`NFPostID`) REFERENCES `newsfeeds` (`NFPostID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `share`
--

LOCK TABLES `share` WRITE;
/*!40000 ALTER TABLE `share` DISABLE KEYS */;
INSERT INTO `share` VALUES (1,'02:46:40','2022-03-26',1,3,6),(2,'02:46:40','2022-03-26',1,2,6),(3,'02:46:40','2022-03-26',1,4,6);
/*!40000 ALTER TABLE `share` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `UserID` bigint NOT NULL,
  `School` varchar(50) DEFAULT NULL,
  `GRADE` int DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (3,NULL,NULL),(5,NULL,NULL);
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject`
--

DROP TABLE IF EXISTS `subject`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject` (
  `UserID` bigint NOT NULL,
  `Subject` varchar(20) NOT NULL,
  PRIMARY KEY (`UserID`,`Subject`),
  CONSTRAINT `subject_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `teacher` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject`
--

LOCK TABLES `subject` WRITE;
/*!40000 ALTER TABLE `subject` DISABLE KEYS */;
/*!40000 ALTER TABLE `subject` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `UserID` bigint NOT NULL,
  `CurrentWorkingPlace` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`UserID`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'Readway Collage'),(2,NULL),(4,NULL),(6,NULL),(7,NULL),(8,NULL),(9,NULL);
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_makes_report`
--

DROP TABLE IF EXISTS `user_makes_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_makes_report` (
  `UserID` bigint NOT NULL,
  `ReportID` bigint NOT NULL,
  PRIMARY KEY (`UserID`,`ReportID`),
  CONSTRAINT `user_makes_report_ibfk_1` FOREIGN KEY (`UserID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_makes_report`
--

LOCK TABLES `user_makes_report` WRITE;
/*!40000 ALTER TABLE `user_makes_report` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_makes_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `UserID` bigint NOT NULL AUTO_INCREMENT,
  `FirstName` varchar(20) NOT NULL,
  `LastName` varchar(20) NOT NULL,
  `ProfilePic` bigint DEFAULT NULL,
  `DOB` date NOT NULL,
  `MobileNum` varchar(20) DEFAULT NULL,
  `UserType` enum('Student','Teacher') NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `RegistrationDate` date NOT NULL,
  `RegistrationTime` time NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Farzan','Rizwan',1,'1998-01-18','940752569360','Teacher','Male','Sri Lanka','Negombo','2022-03-25','23:01:24'),(2,'venushka','chandraraththna',NULL,'1998-01-18','940752569360','Teacher','Male','Sri Lanka','Negombo','2022-03-25','23:04:13'),(3,'jeewa','chamalka',NULL,'1998-01-18','940752569360','Student','Male','Sri Lanka','Negombo','2022-03-25','23:08:04'),(4,'Farzan','Rizwan',NULL,'1998-01-18','940752569360','Teacher','Male','Sri Lanka','Negombo','2022-03-25','23:34:26'),(5,'rahuram','srimohan',NULL,'1998-01-18','940752569360','Student','Male','Sri Lanka','negombo','2022-03-26','07:43:37'),(6,'zahul','hameed',NULL,'1998-01-18','940752569360','Teacher','Male','Sri Lanka','negombo','2022-03-26','11:32:52'),(7,'Prime','Thinker',NULL,'1998-01-18','940752569360','Teacher','Male','Sri Lanka','Negombo','2022-03-26','11:48:24'),(8,'Axcel','Blaze',NULL,'1998-01-18','940752569360','Teacher','Male','Sri Lanka','Negombo','2022-03-26','12:13:48'),(9,'shawn','frost',NULL,'1998-01-18','940752569360','Teacher','Male','Sri Lanka','negombo','2022-03-27','10:45:09');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-28  0:09:59
