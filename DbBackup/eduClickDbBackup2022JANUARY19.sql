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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
INSERT INTO `answer` VALUES (1,'2021-12-20','19:19:16',NULL);
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
INSERT INTO `answer_student_post_relationship` VALUES (2,1,8);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classroom`
--

LOCK TABLES `classroom` WRITE;
/*!40000 ALTER TABLE `classroom` DISABLE KEYS */;
INSERT INTO `classroom` VALUES (1,'ALPHA','2022','11','MATHS',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `educationalpost`
--

LOCK TABLES `educationalpost` WRITE;
/*!40000 ALTER TABLE `educationalpost` DISABLE KEYS */;
INSERT INTO `educationalpost` VALUES (1,'2021-12-19','16:12:04','MCQ','Question',1),(2,'2021-12-19','17:41:33','MCQ','Question',1),(3,'2021-12-19','17:42:05','EducationalWork','Question',1),(4,'2021-12-19','17:54:37','MCQ','Question',1),(5,'2021-12-19','17:56:49','MCQ','Question',1),(6,'2021-12-19','18:33:08','MCQ','Question',1),(8,'2021-12-20','19:10:35','EducationalWork','Question',1),(9,'2021-12-20','19:12:33','MCQ','Question',1);
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
INSERT INTO `educationalwork` VALUES (3,'true','hi '),(8,'true','hi ');
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
INSERT INTO `edw_answers` VALUES (1,'answer...','false');
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
INSERT INTO `enroll` VALUES (2,1,'Enable'),(3,1,'Enable');
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
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

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
INSERT INTO `login` VALUES ('farzan123@gmail.com','034637f145adb9f2dbfcc256a67de32b50a4d44a610d23f931d482bd7da78d72','ehd2VTu#gXk2WydP','2022-01-17','18:38:26',3,'True','False'),('farzanroxz123@gmail.com','a8c94a78a867c96b464ecf02f75998134fdc6794f7bb2949c8470cb0bc716f52','nWnXN_RnY2V=2IW','2022-01-18','23:40:43',1,'True','False'),('m.farzan.rizwan@gmail.com','cecd90f614b0155c80032969c26c1280bbc65e62599a0f41157facef8355be9b','uQxagCU7O%4<e7','2022-01-17','18:37:00',2,'True','False');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `news_feed_image`
--

LOCK TABLES `news_feed_image` WRITE;
/*!40000 ALTER TABLE `news_feed_image` DISABLE KEYS */;
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
  PRIMARY KEY (`NFPostID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `newsfeeds`
--

LOCK TABLES `newsfeeds` WRITE;
/*!40000 ALTER TABLE `newsfeeds` DISABLE KEYS */;
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
  `PostedType` enum('Shared','Posted','Educational Post','Answer') DEFAULT NULL,
  `Notification_Date` date NOT NULL,
  `Notification_Time` time NOT NULL,
  KEY `NotifierID` (`NotifierID`),
  KEY `NotifieeID` (`NotifieeID`),
  KEY `ContentID` (`ContentID`),
  CONSTRAINT `notifications_ibfk_1` FOREIGN KEY (`NotifierID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `notifications_ibfk_2` FOREIGN KEY (`NotifieeID`) REFERENCES `users` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `notifications_ibfk_3` FOREIGN KEY (`ContentID`) REFERENCES `posts` (`NFPostID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (1,'Question','1',1),(2,'Question','2',1),(3,'Question','3',1),(4,'Question','4',1),(5,'Question','1',1),(6,'Question','2',1),(7,'Question','3',1),(8,'Question','4',1),(9,'Question','1',1),(10,'Question','2',1),(11,'answer','1',2),(12,'answer','2',2),(13,'answer','3',2),(14,'answer','4',2),(15,'answer','1',2),(16,'answer','2',2),(17,'answer','3',2),(18,'answer','4',2),(19,'answer','1',2),(20,'answer','2',2),(21,'    flex: 1;','1',4),(22,'    flex: 1;','2',4),(23,'    flex: 1;','3',4),(24,'    flex: 1;','4',4),(25,'    flex: 1;','1',4),(26,'    flex: 1;','2',4),(27,'    flex: 1;','3',4),(28,'    flex: 1;','4',4),(29,'    flex: 1;','1',4),(30,'    flex: 1;','2',4),(31,'answer','1',5),(32,'answer','2',5),(33,'answer','3',5),(34,'answer','4',5),(35,'answer','1',5),(36,'answer','2',5),(37,'answer','3',5),(38,'answer','4',5),(39,'answer','1',5),(40,'answer','2',5),(41,'answer','1',6),(42,'answer','2',6),(43,'answer','3',6),(44,'answer','4',6),(45,'answer','1',6),(46,'answer','2',6),(47,'answer','3',6),(48,'answer','4',6),(49,'answer','1',6),(50,'answer','2',6),(61,'farzan','1',9),(62,'farzan','2',9),(63,'farzan','3',9),(64,'farzan','4',9),(65,'farzan','1',9),(66,'farzan','2',9),(67,'farzan','2',9),(68,'farzan','4',9),(69,'farzan','1',9),(70,'farzan','2',9);
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
INSERT INTO `question_answer_value` VALUES (1,'1','answer'),(1,'2','answer'),(1,'3','answer'),(1,'4','answer'),(2,'1','answer'),(2,'2','answer'),(2,'3','answer'),(2,'4','answer'),(3,'1','answer'),(3,'2','answer'),(3,'3','answer'),(3,'4','answer'),(4,'1','answer'),(4,'2','answer'),(4,'3','answer'),(4,'4','answer'),(5,'1','answer'),(5,'2','answer'),(5,'3','answer'),(5,'4','answer'),(6,'1','answer'),(6,'2','answer'),(6,'3','answer'),(6,'4','answer'),(7,'1','answer'),(7,'2','answer'),(7,'3','answer'),(7,'4','answer'),(8,'1','answer'),(8,'2','answer'),(8,'3','answer'),(8,'4','answer'),(9,'1','answer'),(9,'2','answer'),(9,'3','answer'),(9,'4','answer'),(10,'1','answer'),(10,'2','answer'),(10,'3','answer'),(10,'4','answer'),(11,'1','answer'),(11,'2','answer'),(11,'3','answer'),(11,'4','answer'),(12,'1','answer'),(12,'2','answer'),(12,'3','answer'),(12,'4','answer'),(13,'1','answer'),(13,'2','answer'),(13,'3','answer'),(13,'4','answer'),(14,'1','answer'),(14,'2','answer'),(14,'3','answer'),(14,'4','answer'),(15,'1','answer'),(15,'2','answer'),(15,'3','answer'),(15,'4','answer'),(16,'1','answer'),(16,'2','answer'),(16,'3','answer'),(16,'4','answer'),(17,'1','answer'),(17,'2','answer'),(17,'3','answer'),(17,'4','answer'),(18,'1','answer'),(18,'2','answer'),(18,'3','answer'),(18,'4','answer'),(19,'1','answer'),(19,'2','answer'),(19,'3','answer'),(19,'4','answer'),(20,'1','answer'),(20,'2','answer'),(20,'3','answer'),(20,'4','answer'),(21,'1','    flex: 1;'),(21,'2','    flex: 1;'),(21,'3','    flex: 1;'),(21,'4','    flex: 1;'),(22,'1','    flex: 1;'),(22,'2','    flex: 1;'),(22,'3','    flex: 1;'),(22,'4','    flex: 1;'),(23,'1','    flex: 1;'),(23,'2','    flex: 1;'),(23,'3','    flex: 1;'),(23,'4','    flex: 1;'),(24,'1','    flex: 1;'),(24,'2','    flex: 1;'),(24,'3','    flex: 1;'),(24,'4','    flex: 1;'),(25,'1','    flex: 1;'),(25,'2','    flex: 1;'),(25,'3','    flex: 1;'),(25,'4','    flex: 1;'),(26,'1','    flex: 1;'),(26,'2','    flex: 1;'),(26,'3','    flex: 1;'),(26,'4','    flex: 1;'),(27,'1','    flex: 1;'),(27,'2','    flex: 1;'),(27,'3','    flex: 1;'),(27,'4','    flex: 1;'),(28,'1','    flex: 1;'),(28,'2','    flex: 1;'),(28,'3','    flex: 1;'),(28,'4','    flex: 1;'),(29,'1','    flex: 1;'),(29,'2','    flex: 1;'),(29,'3','    flex: 1;'),(29,'4','    flex: 1;'),(30,'1','    flex: 1;'),(30,'2','    flex: 1;'),(30,'3','    flex: 1;'),(30,'4','    flex: 1;'),(31,'1','answer'),(31,'2','answer'),(31,'3','answer'),(31,'4','answer'),(32,'1','answer'),(32,'2','answer'),(32,'3','answer'),(32,'4','answer'),(33,'1','answer'),(33,'2','answer'),(33,'3','answer'),(33,'4','answer'),(34,'1','answer'),(34,'2','answer'),(34,'3','answer'),(34,'4','answer'),(35,'1','answer'),(35,'2','answer'),(35,'3','answer'),(35,'4','answer'),(36,'1','answer'),(36,'2','answer'),(36,'3','answer'),(36,'4','answer'),(37,'1','answer'),(37,'2','answer'),(37,'3','answer'),(37,'4','answer'),(38,'1','answer'),(38,'2','answer'),(38,'3','answer'),(38,'4','answer'),(39,'1','answer'),(39,'2','answer'),(39,'3','answer'),(39,'4','answer'),(40,'1','answer'),(40,'2','answer'),(40,'3','answer'),(40,'4','answer'),(41,'1','answer'),(41,'2','answer'),(41,'3','answer'),(41,'4','answer'),(42,'1','answer'),(42,'2','answer'),(42,'3','answer'),(42,'4','answer'),(43,'1','answer'),(43,'2','answer'),(43,'3','answer'),(43,'4','answer'),(44,'1','answer'),(44,'2','answer'),(44,'3','answer'),(44,'4','answer'),(45,'1','answer'),(45,'2','answer'),(45,'3','answer'),(45,'4','answer'),(46,'1','answer'),(46,'2','answer'),(46,'3','answer'),(46,'4','answer'),(47,'1','answer'),(47,'2','answer'),(47,'3','answer'),(47,'4','answer'),(48,'1','answer'),(48,'2','answer'),(48,'3','answer'),(48,'4','answer'),(49,'1','answer'),(49,'2','answer'),(49,'3','answer'),(49,'4','answer'),(50,'1','answer'),(50,'2','answer'),(50,'3','answer'),(50,'4','answer'),(61,'1','farzan'),(61,'2','farzan'),(61,'3','farzan'),(61,'4','farzan'),(62,'1','farzan'),(62,'2','farzan'),(62,'3','farzan'),(62,'4','farzan'),(63,'1','farzan'),(63,'2','farzan'),(63,'3','farzan'),(63,'4','farzan'),(64,'1','farzan'),(64,'2','farzan'),(64,'3','farzan'),(64,'4','farzan'),(65,'1','farzan'),(65,'2','farzan'),(65,'3','farzan'),(65,'4','farzan'),(66,'1','farzan'),(66,'2','farzan'),(66,'3','farzan'),(66,'4','farzan'),(67,'1','farzan'),(67,'2','farzan'),(67,'3','farzan'),(67,'4','farzan'),(68,'1','farzan'),(68,'2','farzan'),(68,'3','farzan'),(68,'4','farzan'),(69,'1','farzanfarzan'),(69,'2','farzan'),(69,'3','farzan'),(69,'4','farzan'),(70,'1','farzan'),(70,'2','farzan'),(70,'3','farzan'),(70,'4','farzan');
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
  CONSTRAINT `share_ibfk_3` FOREIGN KEY (`NFPostID`) REFERENCES `educationalpost` (`EPostID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `share`
--

LOCK TABLES `share` WRITE;
/*!40000 ALTER TABLE `share` DISABLE KEYS */;
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
INSERT INTO `student` VALUES (2,NULL,NULL),(3,NULL,NULL);
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
INSERT INTO `teacher` VALUES (1,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'Farzan','Rizwan',NULL,'1998-01-18','940752569360','Teacher','Male','Sri Lanka','Negombo','2021-12-19','16:04:57'),(2,'Frosty','Farzan',NULL,'1998-01-18','940752569360','Student','Male','Sri Lanka','Negombo','2021-12-20','18:48:09'),(3,'zahul','hameed',NULL,'1998-01-18','940752569360','Student','Male','Sri Lanka','Negombo','2021-12-20','18:49:54');
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

-- Dump completed on 2022-01-19  3:09:18
