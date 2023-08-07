-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: records-db
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `non_pathological_personal_history`
--

DROP TABLE IF EXISTS `non_pathological_personal_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `non_pathological_personal_history` (
  `id` bigint NOT NULL,
  `alcoholism` varchar(255) DEFAULT NULL,
  `bathroom` varchar(255) DEFAULT NULL,
  `civil_status` varchar(255) DEFAULT NULL,
  `feeding` varchar(255) DEFAULT NULL,
  `patient_id` bigint NOT NULL,
  `personal_habits` varchar(255) DEFAULT NULL,
  `physical_activity` varchar(255) DEFAULT NULL,
  `room` varchar(255) DEFAULT NULL,
  `smoking` varchar(255) DEFAULT NULL,
  `vaccines` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `non_pathological_personal_history`
--

LOCK TABLES `non_pathological_personal_history` WRITE;
/*!40000 ALTER TABLE `non_pathological_personal_history` DISABLE KEYS */;
INSERT INTO `non_pathological_personal_history` VALUES (1,'Occasional drinker.','Uses bathroom twice a day.','Married','Balanced diet with adequate protein and vegetables.',102,'Avoids fast food and eats fruits regularly.','Engages in regular exercise.','Sleeps in a well-ventilated room.','Non-smoker.','Up to date with required vaccinations.');
/*!40000 ALTER TABLE `non_pathological_personal_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-08-06 19:03:16
