-- --------------------------------------------------------
-- Servidor:                     localhost
-- Versão do servidor:           11.3.2-MariaDB - mariadb.org binary distribution
-- OS do Servidor:               Win64
-- HeidiSQL Versão:              12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Copiando estrutura do banco de dados para mecanica
CREATE DATABASE IF NOT EXISTS `mecanica` /*!40100 DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci */;
USE `mecanica`;

-- Copiando estrutura para tabela mecanica.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `celular` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Copiando dados para a tabela mecanica.clientes: ~3 rows (aproximadamente)
INSERT IGNORE INTO `clientes` (`id`, `nome`, `email`, `celular`) VALUES
	(1, 'Helton Jose', 'helton@gmail.com', '8483487'),
	(4, 'MARIA SILVA', 'maria@gmail.com', '74398293'),
	(6, 'debora maysa', 'debora@gmail.com', '87981245');
	
-- Copiando estrutura para tabela mecanica.servico
CREATE TABLE IF NOT EXISTS `servico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `descricao` varchar(255) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `data` DATE NOT NULL DEFAULT CURRENT_DATE,
  `valor` decimal(10,2) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Copiando dados para a tabela mecanica.servico: ~2 rows (aproximadamente)
INSERT IGNORE INTO `servico` (`id`, `descricao`, `nome`, `data`, `valor`) VALUES
	(1, 'Lavar carro', 'lavar carro', '2024-11-12', 45.00),
	(2, 'Troca de oleo', 'Troca de oleo', '2024-11-14', 98.00);

-- Copiando estrutura para tabela mecanica.veiculo
CREATE TABLE IF NOT EXISTS `veiculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `modelo` varchar(255) NOT NULL,
  `placa` varchar(20) NOT NULL,
  `ano` int(11) NOT NULL,
  `tipo` varchar(25) NOT NULL DEFAULT '',
  `cliente_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `cliente_id` (`cliente_id`),
  CONSTRAINT `veiculo_ibfk_1` FOREIGN KEY (`cliente_id`) REFERENCES `clientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Copiando dados para a tabela mecanica.veiculo: ~3 rows (aproximadamente)
INSERT IGNORE INTO `veiculo` (`id`, `modelo`, `placa`, `ano`, `tipo`, `cliente_id`) VALUES
	(6, 'TITAN', 'sad12', 2023, 'moto', 1),
	(8, 'GOL', '34fee', 2021, 'carro', 4),
	(9, 'as45', 'SCANIA', 2015, 'caminhao', 1);
	
	
-- Copiando estrutura para tabela mecanica.ordem_servico
CREATE TABLE IF NOT EXISTS `ordem_servico` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_cliente` int(11) DEFAULT 0,
  `id_veiculo` int(11) DEFAULT 0,
  `id_servico` int(11) DEFAULT 0,
  `data_criacao` DATE DEFAULT CURRENT_DATE,
  `desconto` float DEFAULT NULL,
  `valor_final` float DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ordem_servico_clientes` (`id_cliente`),
  KEY `FK_ordem_servico_servico` (`id_servico`),
  KEY `FK_ordem_servico_veiculo` (`id_veiculo`),
  CONSTRAINT `FK_ordem_servico_clientes` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_ordem_servico_servico` FOREIGN KEY (`id_servico`) REFERENCES `servico` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION,
  CONSTRAINT `FK_ordem_servico_veiculo` FOREIGN KEY (`id_veiculo`) REFERENCES `veiculo` (`id`) ON DELETE CASCADE ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

-- Copiando dados para a tabela mecanica.ordem_servico: ~1 rows (aproximadamente)
INSERT IGNORE INTO `ordem_servico` (`id`, `id_cliente`, `id_veiculo`, `id_servico`, `data_criacao`, `desconto`, `valor_final`) VALUES
	(4, 1, 6, 1, '2024-11-13', 2.25, 42.75);

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
