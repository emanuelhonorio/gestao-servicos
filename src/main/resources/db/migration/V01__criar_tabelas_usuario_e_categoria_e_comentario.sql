CREATE TABLE `categoria` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `foto` varchar(255) NOT NULL,
  `content_type` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `usuario` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `email` varchar(255) NOT NULL UNIQUE,
  `senha` varchar(50) NOT NULL,
  `first_name` varchar(50) NOT NULL,
  `last_name` varchar(50) NOT NULL,
  `telefone` varchar(50)  NOT NULL,
  `tipo_pessoa` varchar(50) NOT NULL,
  `cpf_ou_cnpj` varchar(50) NOT NULL,
  `id_categoria` bigint(20) DEFAULT NULL,
  `foto` varchar(100) DEFAULT NULL,
  `content_type` varchar(50) DEFAULT NULL,
  FOREIGN KEY (`id_categoria`) REFERENCES `categoria`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `comentario` (
  `id` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `mensagem` varchar(255) NOT NULL,
  `id_destinatario` bigint(20) NOT NULL,
  `id_remetente` bigint(20) NOT NULL,
  FOREIGN KEY (`id_destinatario`) REFERENCES `usuario`(`id`),
  FOREIGN KEY (`id_remetente`) REFERENCES `usuario`(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `categoria` VALUES (0, 'Advogado', 'advogado.jpg', 'image/jpg');
INSERT INTO `categoria` VALUES (0, 'Comida', 'comida.jpg', 'image/jpg');
INSERT INTO `categoria` VALUES (0, 'Desenvolvedor', 'desenvolvedor.jpg', 'image/jpg');
INSERT INTO `categoria` VALUES (0, 'Designer', 'design.jpeg', 'image/jpeg');
INSERT INTO `categoria` VALUES (0, 'Serviços Domésticos', 'domestico.jpg', 'image/jpg');
INSERT INTO `categoria` VALUES (0, 'Gás De Cozinha', 'gas.jpg', 'image/jpg');
INSERT INTO `categoria` VALUES (0, 'Obras', 'obras.jpg', 'image/jpg');
INSERT INTO `categoria` VALUES (0, 'Paisagismo', 'paisagismo.jpg', 'image/jpg');
INSERT INTO `categoria` VALUES (0, 'Professor Particular', 'professor.jpg', 'image/jpg');