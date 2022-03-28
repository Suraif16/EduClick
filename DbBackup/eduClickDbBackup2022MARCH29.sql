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
INSERT INTO `add_friends` VALUES (2,1),(5,1),(1,2),(1,5);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
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
INSERT INTO `bell_icon` VALUES (1,'2022-03-28','23:50:17'),(2,'2022-03-28','22:30:02'),(3,'2022-03-28','09:03:59'),(4,'2022-03-28','22:39:46'),(5,'2022-03-28','23:49:38'),(6,'2022-03-28','23:33:48');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1,'alpha','2023','11','Maths',1),(2,'Alpha','2024','9','ICT',5);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationalpost`
--

LOCK TABLES `educationalpost` WRITE;
/*!40000 ALTER TABLE `educationalpost` DISABLE KEYS */;
INSERT INTO `educationalpost` VALUES (1,'2022-03-28','11:29:53','EducationalWork','Messages',1),(2,'2022-03-28','11:54:07','EducationalWork','Messages',1),(3,'2022-03-28','12:22:04','EducationalWork','Question',1);
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
INSERT INTO `educationalwork` VALUES (1,'true','This is me... If you haven\'t seen me before...'),(2,'false','hi everyone...'),(3,'false','what is the square root of 25?');
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
INSERT INTO `enroll` VALUES (3,1,'Enable');
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
INSERT INTO `enroll_request` VALUES (4,1,'22:50:23','2022-03-28');
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
INSERT INTO `follows` VALUES (3,1),(4,1),(3,2);
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
INSERT INTO `likes` VALUES (2,1);
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
INSERT INTO `login` VALUES ('farzanroxz123@gmail.com','22d34692a14ce4c1cef3f21bceafb6a1903be49e634513a9afc30f1d3b87cb04','LwNsXjgQ+/Y6/zkQU$M','2022-03-28','23:19:57',1,'True','False'),('jeewa@gmail.com','49e2d55313bb880709911870e716b19ef285d3cdcd79cb10a80185cb033e0696','H%3LXb$U6@','2022-03-28','22:39:45',4,'True','False'),('rahuram123@gmail.com','78d8641846ad515f1860caf7ac1e9d7b4deaee7739c5309b989da769f78cba5b','3$QFG#>GZkqfd5mf','2022-03-28','23:41:50',5,'True','False'),('shawnfrost123@gmail.com','e12680400527dd9c6caa9dd317bd177c750febd52cdca25068195e383afdd419','CgtCquh+^ts','2022-03-28','23:33:47',6,'True','False'),('venushka123@gmail.com','da3af6fd96ddd0e93b60598e66af3789196a480e2e681f1e23bcfa5090cf3c80','I$pkym1K-@#YJA','2022-03-28','23:33:04',3,'True','False'),('zahul123@gmail.com','6af34228b175d82915845db8570de8206c65477d55caaa78bb88a2e5ecee1888','c.A0>zumdha','2022-03-28','22:15:29',2,'True','False');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_feed_image`
--

LOCK TABLES `news_feed_image` WRITE;
/*!40000 ALTER TABLE `news_feed_image` DISABLE KEYS */;
INSERT INTO `news_feed_image` VALUES (1,1),(2,2),(3,3);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newsfeeds`
--

LOCK TABLES `newsfeeds` WRITE;
/*!40000 ALTER TABLE `newsfeeds` DISABLE KEYS */;
INSERT INTO `newsfeeds` VALUES (1,'2022-03-28','09:01:49','HI, how are you?',1,0,'true'),(2,'2022-03-28','23:13:28','This is me...',0,0,'true'),(3,'2022-03-28','23:49:55','Hi everyOne',0,0,'true');
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
INSERT INTO `notifications` VALUES (1,2,0,'Friend Request','2022-03-28','08:57:01'),(1,3,0,'Classroom Request','2022-03-28','08:59:03'),(1,3,0,'Classroom Request','2022-03-28','08:59:52'),(1,3,1,'Posted','2022-03-28','09:01:50'),(1,2,1,'Posted','2022-03-28','09:01:51'),(2,1,1,'Shared','2022-03-28','09:02:25'),(1,3,1,'Shared','2022-03-28','09:02:51'),(1,2,1,'Shared','2022-03-28','09:02:51'),(1,3,1,'Educational Post','2022-03-28','11:29:53'),(1,3,1,'Educational Post','2022-03-28','11:54:07'),(1,3,1,'Educational Post','2022-03-28','12:22:04'),(1,4,0,'Classroom Request','2022-03-28','22:44:16'),(1,3,2,'Posted','2022-03-28','23:13:29'),(1,4,2,'Posted','2022-03-28','23:13:29'),(1,2,2,'Posted','2022-03-28','23:13:30'),(5,1,0,'Friend Request','2022-03-28','23:49:40'),(5,1,3,'Posted','2022-03-28','23:49:56');
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
INSERT INTO `posts` VALUES (5,1,3),(1,2,1),(1,2,2),(1,3,1),(1,3,2),(1,4,2);
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
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
INSERT INTO `share` VALUES (1,'02:46:40','2022-03-28',2,1,1),(2,'02:46:40','2022-03-28',1,3,1),(3,'02:46:40','2022-03-28',1,2,1);
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
INSERT INTO `student` VALUES (3,NULL,NULL),(4,NULL,NULL);
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
INSERT INTO `teacher` VALUES (1,'Readway Collage'),(2,''),(5,NULL),(6,NULL);
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
  `CountryCode` varchar(10) DEFAULT NULL,
  `UserType` enum('Student','Teacher') NOT NULL,
  `Gender` enum('Male','Female') NOT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `City` varchar(50) DEFAULT NULL,
  `RegistrationDate` date NOT NULL,
  `RegistrationTime` time NOT NULL,
  PRIMARY KEY (`UserID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Farzan','Pheonix',1,'1998-01-18','0752569360','94','Teacher','Male','Sri Lanka','Colombo','2022-03-28','02:22:43'),(2,'zahul','hameed',2,'1998-01-18','0752569360','94','Teacher','Male','Sri Lanka','negombo','2022-03-28','08:52:27'),(3,'venushka','chandrarathna',NULL,'1998-01-18','0752569360','94','Student','Male','Sri Lanka','Colombo','2022-03-28','08:56:09'),(4,'jeewanthi','chamalka',NULL,'1998-01-18','0752569360','94','Student','Female','Sri Lanka','negombo','2022-03-28','22:39:45'),(5,'rahuram','srimohan',NULL,'1998-01-18','0752569360','94','Teacher','Male','Sri Lanka','negombo','2022-03-28','22:55:06'),(6,'shawn','frost',NULL,'1998-01-18','0752569360','94','Teacher','Male','Sri Lanka','negombo','2022-03-28','23:33:47');
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

-- Dump completed on 2022-03-29  0:05:13
