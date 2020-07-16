use sistema1;
--
-- Table structure for table `user`
--
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `usuario` varchar(45) NOT NULL,
  `password` varchar(45) DEFAULT NULL,
  `roles_idrol` int(11) DEFAULT NULL,
  `auth_token` text,
  `remember_token` text,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--
LOCK TABLES `user` WRITE;
INSERT INTO `user` VALUES (1,'xxx@xxx.com',NULL,1,'','');
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--
DROP TABLE IF EXISTS `cliente`;

CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `documento` char(11) NOT NULL,
  `nombrecompleto` varchar(100) NOT NULL,
  `direccion` varchar(60) DEFAULT NULL,
  `telefono` varchar(12) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcliente`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--
LOCK TABLES `cliente` WRITE;
INSERT INTO `cliente` 
VALUES (1,'73223177','Giancarlo Chinchay Guerrero','Av. Universitaria 6227 Villa Sol Los Olivos','948110940','giachigue95@gmail.com',1),
(2,'71217915','Jair Tarazona','Av. La Costanera 345 Chorrillos','3252562','tarazona.jair@gmail.com',1),
(3,'72398840','Juan Carlos Cuba','Calle 124 lote 17, Los Olivos de pro.','987485784','juan.carlos.cuba@gmail.com',1);
UNLOCK TABLES;

--
-- Table structure for table `producto`
--
DROP TABLE IF EXISTS `producto`;
CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `preciocompra` decimal(10,2) NOT NULL,
  `precioventa` decimal(10,2) DEFAULT NULL,
  `stock` int(11) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`idproducto`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `producto`
--
LOCK TABLES `producto` WRITE;
INSERT INTO `producto` 
VALUES (1,'ESCOBA ESCOBON',12.00,12.30,1000,3),
(2,'ESCOBA ESCOBESTIA',14.00,14.50,984,3),
(3,'LAVATODO HUDE',10.00,10.50,954,3),
(4,'VENENO KILLER',13.00,13.50,1465,4),
(5,'INSECTICIDA SECRETO DE LA ABUELA',5.20,5.70,1000,3);
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--
DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE `proveedor` (
  `idproveedor` int(11) NOT NULL AUTO_INCREMENT,
  `razonsocial` varchar(100) NOT NULL,
  `rucproveedor` char(11) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefono` varchar(12) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  PRIMARY KEY (`idproveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `proveedor`
--
LOCK TABLES `proveedor` WRITE;
INSERT INTO `proveedor` 
VALUES (1,'CORPORACION ALTIPLANO S.A.','20394857471','contacto@altiplano.com.pe','95112399',5),
(2,'SOLARIS S.A.','98875123','contacto@solaris.com','91231239',5),
(3,'HUDE S.A.C.','20493817121','ventas.hude@hude.com.pe','5348976',5);
UNLOCK TABLES;

--
-- Table structure for table `factura`
--

DROP TABLE IF EXISTS `factura`;
CREATE TABLE `factura` (
  `idfactura` int(11) NOT NULL AUTO_INCREMENT,
  `numero` char(11) NOT NULL,
  `fechaemision` datetime NOT NULL,
  `fechavencimiento` datetime DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `igv` decimal(10,2) DEFAULT NULL,
  `total` decimal(10,2) DEFAULT NULL,
  `cliente_idcliente` int(11) NOT NULL,
  PRIMARY KEY (`idfactura`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

--
-- Table structure for table `detalle_factura`
--

DROP TABLE IF EXISTS `detalle_factura`;
CREATE TABLE `detalle_factura` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `producto_idproducto` int(11) DEFAULT NULL,
  `cantidad` varchar(45) DEFAULT NULL,
  `precio` decimal(10,2) NOT NULL,
  `factura_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

--
-- Table structure for table `estado`
--
DROP TABLE IF EXISTS `estado`;
CREATE TABLE `estado` (
  `idestado` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) DEFAULT NULL,
  `tabla` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idestado`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `estado`
--
LOCK TABLES `estado` WRITE;
INSERT INTO `estado` 
VALUES 
(1,'Habilitado','cliente'),
(2,'No Habilitado','cliente'),
(3,'Disponible','producto'),
(4,'No Disponible','producto'),
(5,'Habilitado','proveedor'),
(6,'No Habilitado','proveedor');
UNLOCK TABLES;

--
-- Table structure for table `rol`
--
DROP TABLE IF EXISTS `rol`;
CREATE TABLE `rol` (
  `idrol` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(45) NOT NULL,
  `modulos` text,
  PRIMARY KEY (`idrol`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rol`
--
LOCK TABLES `rol` WRITE;
INSERT INTO `rol` 
VALUES (1,'Administrador de sistema','all'),
(2,'Jefe de ventas','producto-cliente'),
(3,'Almacenero','producto-proveedor');
UNLOCK TABLES;