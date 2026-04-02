-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: medlinker
-- ------------------------------------------------------
-- Server version	8.0.43

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
-- Table structure for table `candidaturas`
--

DROP TABLE IF EXISTS `candidaturas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `candidaturas` (
  `id` int NOT NULL AUTO_INCREMENT,
  `plantao_id` int NOT NULL,
  `medico_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `plantao_id` (`plantao_id`,`medico_id`),
  KEY `medico_id` (`medico_id`),
  CONSTRAINT `candidaturas_ibfk_1` FOREIGN KEY (`plantao_id`) REFERENCES `plantoes` (`id`),
  CONSTRAINT `candidaturas_ibfk_2` FOREIGN KEY (`medico_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `candidaturas`
--

LOCK TABLES `candidaturas` WRITE;
/*!40000 ALTER TABLE `candidaturas` DISABLE KEYS */;
/*!40000 ALTER TABLE `candidaturas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hospitais`
--

DROP TABLE IF EXISTS `hospitais`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hospitais` (
  `id` int NOT NULL AUTO_INCREMENT,
  `usuario_id` int NOT NULL,
  `nome` varchar(120) NOT NULL,
  `cidade` varchar(80) NOT NULL,
  `estado` char(2) NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `usuario_id` (`usuario_id`),
  CONSTRAINT `hospitais_ibfk_1` FOREIGN KEY (`usuario_id`) REFERENCES `usuarios` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hospitais`
--

LOCK TABLES `hospitais` WRITE;
/*!40000 ALTER TABLE `hospitais` DISABLE KEYS */;
INSERT INTO `hospitais` VALUES (6,1,'João Silva','São Paulo','SP','2026-02-24 19:48:58'),(7,2,'Maria Oliveira','Rio de Janeiro','RJ','2026-02-24 19:48:58'),(8,3,'Carlos Pereira','Belo Horizonte','MG','2026-02-24 19:48:58'),(9,4,'Ana Souza','Curitiba','PR','2026-02-24 19:48:58'),(10,1,'Hospital Teste','Londrina','PR','2026-03-30 20:07:22');
/*!40000 ALTER TABLE `hospitais` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plantoes`
--

DROP TABLE IF EXISTS `plantoes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `plantoes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `hospital_id` int NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `especialidade` varchar(80) NOT NULL,
  `data_plantao` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `hora_fim` time NOT NULL,
  `valor` decimal(10,2) NOT NULL,
  `status` enum('aberto','preenchido') DEFAULT 'aberto',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `hospital_id` (`hospital_id`),
  CONSTRAINT `plantoes_ibfk_1` FOREIGN KEY (`hospital_id`) REFERENCES `hospitais` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plantoes`
--

LOCK TABLES `plantoes` WRITE;
/*!40000 ALTER TABLE `plantoes` DISABLE KEYS */;
INSERT INTO `plantoes` VALUES (11,7,'Plantão Noturno UTI','UTI','2026-03-01','19:00:00','07:00:00',1800.00,'preenchido','2026-02-24 19:52:03'),(12,8,'Plantão Diurno Clínica','Clínica Médica','2026-03-02','07:00:00','19:00:00',1500.00,'preenchido','2026-02-24 19:52:03'),(13,9,'Plantão Emergência','Emergência','2026-03-03','08:00:00','20:00:00',2000.00,'aberto','2026-02-24 19:52:03'),(14,6,'Plantão Pediatria','Pediatria','2026-03-04','07:00:00','19:00:00',1600.00,'preenchido','2026-02-24 19:52:03'),(15,7,'Plantão Ortopedia','Ortopedia','2026-03-05','19:00:00','07:00:00',1900.00,'preenchido','2026-02-24 19:52:03'),(20,6,'João Silva','teste','2026-03-31','15:40:12','15:40:12',500.00,'preenchido','2026-03-31 18:40:23');
/*!40000 ALTER TABLE `plantoes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `usuario` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL,
  `admin` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Iago Barbosa','iago.barbosa','132224',1),(2,'Joao de Santo Cristo','santo.cristo','123456',0),(3,'Edward Elrick','ed.elrick','123456',0),(4,'Gon Freecss','hunter.gon','123456',0),(7,'Pessoa Teste','User','123456',0),(8,'Isis','isis.doutora','123456',0);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'medlinker'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-04-02 14:07:44
