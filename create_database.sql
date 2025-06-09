-- Script para crear la base de datos healthydb en MySQL
-- Ejecutar este script cuando quieras usar MySQL en lugar de H2

-- Crear la base de datos
CREATE DATABASE IF NOT EXISTS healthydb 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE healthydb;

-- Verificar que se creó correctamente
SHOW TABLES;

-- La tabla 'habits' se creará automáticamente por Hibernate cuando ejecutes la aplicación

PRINT 'Base de datos healthydb creada exitosamente!'; 