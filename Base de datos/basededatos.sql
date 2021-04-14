-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema VeterinariaPony
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `VeterinariaPony` ;

-- -----------------------------------------------------
-- Schema VeterinariaPony
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `VeterinariaPony` DEFAULT CHARACTER SET utf8 ;
USE `VeterinariaPony` ;

-- -----------------------------------------------------
-- Table `VeterinariaPony`.`categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`categoria` (
  `cat_id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`cat_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`unidad`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`unidad` (
  `uni_id` INT NOT NULL AUTO_INCREMENT,
  `nombre` CHAR(20) NOT NULL,
  PRIMARY KEY (`uni_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`articulo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`articulo` (
  `art_id` INT NOT NULL AUTO_INCREMENT,
  `clave` VARCHAR(120) NOT NULL,
  `descripcion` VARCHAR(300) NOT NULL,
  `precioCom` DECIMAL(20,3) NOT NULL DEFAULT 0.0,
  `existencia` DECIMAL(20,3) NOT NULL DEFAULT 0.0,
  `inventarioMin` DECIMAL(20,3) NOT NULL DEFAULT 0.0,
  `inventarioMax` DECIMAL(20,3) NOT NULL DEFAULT 0.0,
  `lote` TINYINT(1) NOT NULL DEFAULT 0,
  `granel` TINYINT(1) NOT NULL DEFAULT 0,
  `factor` DECIMAL(20,3) NOT NULL DEFAULT 1.0,
  `precioSal` DECIMAL(20,3) NOT NULL DEFAULT 0.0,
  `caracteristicas` TEXT NULL,
  `cat_id` INT NOT NULL DEFAULT 1,
  `unidadCompra` INT NOT NULL DEFAULT 1,
  `unidadSalida` INT NOT NULL DEFAULT 1,
  `localizacion` VARCHAR(100) NULL,
  `imagen` VARCHAR(200) NULL,
  `fechaMod` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  UNIQUE INDEX `clave_UNIQUE` (`clave` ASC),
  PRIMARY KEY (`art_id`),
  INDEX `fk_articulo_categoria1_idx` (`cat_id` ASC),
  INDEX `fk_articulo_unidad1_idx` (`unidadCompra` ASC),
  INDEX `fk_articulo_unidad2_idx` (`unidadSalida` ASC),
  CONSTRAINT `fk_articulo_categoria1`
    FOREIGN KEY (`cat_id`)
    REFERENCES `VeterinariaPony`.`categoria` (`cat_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulo_unidad1`
    FOREIGN KEY (`unidadCompra`)
    REFERENCES `VeterinariaPony`.`unidad` (`uni_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulo_unidad2`
    FOREIGN KEY (`unidadSalida`)
    REFERENCES `VeterinariaPony`.`unidad` (`uni_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`veterinario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`veterinario` (
  `vet_id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(200) NOT NULL,
  `telefono` VARCHAR(45) NULL,
  `direccion` VARCHAR(200) NULL,
  PRIMARY KEY (`vet_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`salida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`salida` (
  `sal_id` BIGINT NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total` DECIMAL NOT NULL DEFAULT 0.0,
  `vet_id` INT NOT NULL,
  PRIMARY KEY (`sal_id`),
  INDEX `fk_salida_veterinario1_idx` (`vet_id` ASC),
  CONSTRAINT `fk_salida_veterinario1`
    FOREIGN KEY (`vet_id`)
    REFERENCES `VeterinariaPony`.`veterinario` (`vet_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`proveedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`proveedor` (
  `pro_id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(120) NOT NULL,
  `representante` VARCHAR(120) NULL,
  `alias` VARCHAR(120) NULL,
  `rfc` VARCHAR(45) NULL,
  `curp` VARCHAR(45) NULL,
  `telefono` VARCHAR(45) NULL,
  `celular` VARCHAR(45) NULL,
  `pais` VARCHAR(120) NULL DEFAULT 'MÉXICO',
  `estado` VARCHAR(120) NULL DEFAULT 'HIDALGO',
  `municipio` VARCHAR(120) NULL,
  `colonia` VARCHAR(120) NULL,
  `localidad` VARCHAR(120) NULL,
  `domicilio` VARCHAR(120) NULL,
  `codigoPostal` VARCHAR(45) NULL,
  `numInt` VARCHAR(10) NULL DEFAULT 'S/N',
  `numExt` VARCHAR(10) NULL DEFAULT 'S/N',
  `email` VARCHAR(45) NULL,
  `foto` LONGBLOB NULL,
  PRIMARY KEY (`pro_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`pedido` (
  `ped_id` BIGINT NOT NULL AUTO_INCREMENT,
  `fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `estado` TINYINT NOT NULL DEFAULT 0,
  `total` DECIMAL(20,3) NOT NULL DEFAULT 0,
  `pro_id` INT NOT NULL,
  `fechaRecibido` TIMESTAMP NULL,
  PRIMARY KEY (`ped_id`),
  INDEX `fk_Pedidos_Proveedores1_idx` (`pro_id` ASC),
  CONSTRAINT `fk_Pedidos_Proveedores1`
    FOREIGN KEY (`pro_id`)
    REFERENCES `VeterinariaPony`.`proveedor` (`pro_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`lote`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`lote` (
  `art_id` INT NOT NULL,
  `lot_id` INT NOT NULL AUTO_INCREMENT,
  `numLote` VARCHAR(45) NOT NULL,
  `fechaCad` DATE NOT NULL,
  `exisInicial` DECIMAL(20,3) NOT NULL DEFAULT 0.0,
  `exisActual` DECIMAL(20,3) NOT NULL DEFAULT 0.0,
  `estado` TINYINT(1) NOT NULL DEFAULT 0,
  PRIMARY KEY (`lot_id`),
  INDEX `fk_lotes_articulos1_idx` (`art_id` ASC),
  CONSTRAINT `fk_lotes_articulos1`
    FOREIGN KEY (`art_id`)
    REFERENCES `VeterinariaPony`.`articulo` (`art_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`salida_lista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`salida_lista` (
  `art_id` INT NOT NULL,
  `sal_id` BIGINT NOT NULL,
  `cantidad` DECIMAL(20,3) NOT NULL DEFAULT 0.0,
  PRIMARY KEY (`art_id`, `sal_id`),
  INDEX `fk_articulos_has_salida_salida1_idx` (`sal_id` ASC),
  INDEX `fk_articulos_has_salida_articulos1_idx` (`art_id` ASC),
  CONSTRAINT `fk_articulos_has_salida_articulos1`
    FOREIGN KEY (`art_id`)
    REFERENCES `VeterinariaPony`.`articulo` (`art_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulos_has_salida_salida1`
    FOREIGN KEY (`sal_id`)
    REFERENCES `VeterinariaPony`.`salida` (`sal_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`compra`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`compra` (
  `com_id` BIGINT NOT NULL AUTO_INCREMENT,
  `folio` VARCHAR(250) NOT NULL,
  `fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total` DECIMAL(20,3) NOT NULL DEFAULT 0.0,
  PRIMARY KEY (`com_id`),
  UNIQUE INDEX `folio_UNIQUE` (`folio` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`compra_lista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`compra_lista` (
  `art_id` INT NOT NULL,
  `com_id` BIGINT NOT NULL,
  `cantidad` DECIMAL(20,3) NOT NULL DEFAULT 0.0,
  PRIMARY KEY (`art_id`, `com_id`),
  INDEX `fk_articulo_has_compra_compra1_idx` (`com_id` ASC),
  INDEX `fk_articulo_has_compra_articulo1_idx` (`art_id` ASC),
  CONSTRAINT `fk_articulo_has_compra_articulo1`
    FOREIGN KEY (`art_id`)
    REFERENCES `VeterinariaPony`.`articulo` (`art_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulo_has_compra_compra1`
    FOREIGN KEY (`com_id`)
    REFERENCES `VeterinariaPony`.`compra` (`com_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`empresa`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`empresa` (
  `emp_id` INT NOT NULL AUTO_INCREMENT,
  `representante` VARCHAR(120) NOT NULL,
  `nombre` VARCHAR(120) NOT NULL,
  `descripcion` TEXT NULL,
  `domicilio` VARCHAR(255) NULL,
  `numExt` VARCHAR(10) NULL DEFAULT 'S/N',
  `numInt` VARCHAR(10) NULL DEFAULT 'S/N',
  `localidad` VARCHAR(120) NULL,
  `pais` VARCHAR(120) NULL DEFAULT 'MÉXICO',
  `municipio` VARCHAR(120) NULL,
  `estado` VARCHAR(120) NULL DEFAULT 'HIDALGO',
  `codigoPostal` CHAR(12) NULL,
  `telefono` CHAR(12) NULL,
  `celular` CHAR(12) NULL,
  `email` VARCHAR(45) NULL,
  `logo` LONGBLOB NULL,
  `colonia` VARCHAR(120) NULL,
  PRIMARY KEY (`emp_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `VeterinariaPony`.`pedido_lista`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`pedido_lista` (
  `art_id` INT NOT NULL,
  `ped_id` BIGINT NOT NULL,
  `cantidad` DECIMAL(20,3) NOT NULL DEFAULT 0.0,
  PRIMARY KEY (`art_id`, `ped_id`),
  INDEX `fk_articulo_has_pedido_pedido1_idx` (`ped_id` ASC),
  INDEX `fk_articulo_has_pedido_articulo1_idx` (`art_id` ASC),
  CONSTRAINT `fk_articulo_has_pedido_articulo1`
    FOREIGN KEY (`art_id`)
    REFERENCES `VeterinariaPony`.`articulo` (`art_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_articulo_has_pedido_pedido1`
    FOREIGN KEY (`ped_id`)
    REFERENCES `VeterinariaPony`.`pedido` (`ped_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `VeterinariaPony` ;

-- -----------------------------------------------------
-- Placeholder table for view `VeterinariaPony`.`ReportePedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `VeterinariaPony`.`ReportePedido` (`pedidoID` INT, `pedidoFecha` INT, `pedidoEstado` INT, `pedidoTotal` INT, `proveedorID` INT, `clave` INT, `descripcion` INT, `cantidad` INT, `precioUnitario` INT, `importe` INT, `nombre` INT, `representante` INT, `email` INT, `telefono` INT, `rfc` INT, `proveedorDomicilio` INT, `proveedorDomicilio2` INT, `proveedorDomicilio3` INT, `nombreEmpresa` INT, `representanteEmpresa` INT, `correoEmpresa` INT, `telefonoEmpresa` INT, `celuarEmpresa` INT, `empresaDomicilio` INT, `empresaDomicilio2` INT, `empresaDomicilio3` INT, `logo` INT, `empresaDescripcion` INT);

-- -----------------------------------------------------
-- View `VeterinariaPony`.`ReportePedido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `VeterinariaPony`.`ReportePedido`;
USE `VeterinariaPony`;
CREATE  OR REPLACE VIEW `ReportePedido` AS
SELECT 
ped.ped_id AS pedidoID,
DATE_FORMAT(ped.fecha, "%e %b %Y") AS pedidoFecha,
IF(ped.estado = 0, "En proceso de entrega", IF(ped.estado = 1, "Recibido", "Cancelado")) AS pedidoEstado,
FORMAT(ped.total, 2) AS pedidoTotal,
ped.pro_id AS proveedorID,
art.clave, 
art.descripcion, 
FORMAT(lis.cantidad, 2) AS cantidad, 
FORMAT(art.precioCom, 2) AS precioUnitario,
FORMAT(art.precioCom * lis.cantidad, 2) AS importe,
prov.nombre, prov.representante, prov.email, prov.telefono, prov.rfc, 
CONCAT(prov.domicilio, " #", prov.numExt, " CP. ", prov.codigoPostal) AS proveedorDomicilio,
CONCAT(prov.colonia, " ", prov.localidad, ", ", prov.municipio) AS proveedorDomicilio2,
CONCAT(prov.estado, ", ",prov.pais) AS proveedorDomicilio3, 
emp.nombre as nombreEmpresa, emp.representante as representanteEmpresa, emp.email as correoEmpresa, emp.telefono as telefonoEmpresa, emp.celular as celuarEmpresa, 
CONCAT(emp.domicilio, " #", emp.numExt, " CP. ", emp.codigoPostal) AS empresaDomicilio,
CONCAT(emp.colonia, ", ", emp.localidad, ", ", emp.municipio) AS empresaDomicilio2,
CONCAT(emp.estado, ", ",emp.pais) AS empresaDomicilio3,
emp.logo, emp.descripcion as empresaDescripcion
FROM empresa AS emp, pedido AS ped
JOIN pedido_lista AS lis ON (ped.ped_id = lis.ped_id)
JOIN articulo AS art ON (lis.art_id = art.art_id)
JOIN proveedor AS prov ON(ped.pro_id = prov.pro_id);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
