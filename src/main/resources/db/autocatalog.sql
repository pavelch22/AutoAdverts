-- MySQL dump 10.13  Distrib 5.7.13, for linux-glibc2.5 (x86_64)
--
-- Host: 127.0.0.1    Database: auto_ads
-- ------------------------------------------------------
-- Server version	5.7.17

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand` DISABLE KEYS */;
INSERT INTO `brand` VALUES (1,'Audi'),(2,'BMW'),(3,'Mercedes'),(4,'Nissan'),(5,'Renault'),(6,'Skoda'),(7,'Subaru'),(8,'Toyota'),(9,'Volkswagen'),(10,'Volvo');
/*!40000 ALTER TABLE `brand` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `model`
--

LOCK TABLES `model` WRITE;
/*!40000 ALTER TABLE `model` DISABLE KEYS */;
INSERT INTO `model` VALUES (1,1,'a1'),(2,1,'a2'),(3,1,'a3'),(4,1,'a4'),(5,1,'a5'),(6,1,'a6'),(7,1,'a7'),(8,1,'a8'),(9,2,'1-series'),(14,3,'c-class'),(15,3,'e-class'),(16,3,'s-class'),(17,2,'3-series'),(18,2,'5-series'),(19,2,'7-series'),(20,1,'q3'),(21,1,'q5'),(22,1,'q7'),(25,2,'M5'),(26,1,'rs7'),(28,3,'ML'),(29,4,'GTR'),(30,5,'Logan'),(31,5,'Duster'),(32,7,'Forester'),(33,7,'Legacy'),(34,7,'Outback'),(35,7,'WRX STI'),(36,8,'Camry'),(37,8,'Corolla'),(38,8,'Hilux'),(39,8,'Land Cruiser 200'),(40,10,'S60'),(41,10,'S80'),(42,10,'V40 Cross Country'),(43,10,'V60 Cross Country'),(44,10,'XC90'),(45,9,'Golf'),(46,9,'Passat'),(47,9,'Polo'),(48,9,'Tuareg'),(49,6,'Octavia'),(50,6,'Superb');
/*!40000 ALTER TABLE `model` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-12-31 21:18:19
