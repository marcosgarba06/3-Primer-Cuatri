-- =========================================================
-- Script de eliminación da base de datos
-- =========================================================

-- ---------------------------------------------------------
-- IMPORTANTE:
-- Se eliminan primero las tablas con más dependencias
-- (relaciones N:M y tablas hijas)
-- ---------------------------------------------------------

-- =========================
-- RELACIÓNS (N:M)
-- =========================

-- Relación entre clases y material
DROP TABLE IF EXISTS NECESITAN CASCADE;

-- Relación entre limpeza e salas
DROP TABLE IF EXISTS LIMPIA CASCADE;

-- Relación cliente - sesión (reservas)
DROP TABLE IF EXISTS ASISTE CASCADE;

-- Relación monitor - sesión
DROP TABLE IF EXISTS TUTOREA CASCADE;

-- Relación clase - sala
DROP TABLE IF EXISTS UBICASE CASCADE;


-- =========================
-- TÁBOAS DEPENDENTES
-- =========================

-- Sesión depende de CLASE
DROP TABLE IF EXISTS SESION CASCADE;

-- Factura depende de CLIENTE
DROP TABLE IF EXISTS FACTURA CASCADE;


-- =========================
-- SUBTIPOS DE MATERIAL
-- =========================

DROP TABLE IF EXISTS GUANTES CASCADE;
DROP TABLE IF EXISTS SACOS CASCADE;
DROP TABLE IF EXISTS MAQUINA CASCADE;
DROP TABLE IF EXISTS MATERIAL_PESO_LIBRE CASCADE;


-- =========================
-- MATERIAL (PADRE)
-- =========================

DROP TABLE IF EXISTS MATERIAL CASCADE;


-- =========================
-- SUBTIPOS DE TRABALLADOR
-- =========================

DROP TABLE IF EXISTS ADMINISTRADOR CASCADE;
DROP TABLE IF EXISTS MONITOR CASCADE;
DROP TABLE IF EXISTS LIMPEZA CASCADE;


-- =========================
-- TRABALLADOR E CLIENTE
-- =========================

DROP TABLE IF EXISTS TRABALLADOR CASCADE;
DROP TABLE IF EXISTS CLIENTE CASCADE;


-- =========================
-- USUARIO (PADRE)
-- =========================

DROP TABLE IF EXISTS USUARIO CASCADE;


-- =========================
-- TÁBOAS PRINCIPAIS
-- =========================

DROP TABLE IF EXISTS CLASE CASCADE;
DROP TABLE IF EXISTS SALA CASCADE;
DROP TABLE IF EXISTS XIMNASIO CASCADE;


-- =========================
-- TIPOS ENUM (DOMINIOS)
-- =========================

-- Se eliminan al final porque pueden estar usados por tablas

DROP TYPE IF EXISTS metodo_pago_enum CASCADE;
DROP TYPE IF EXISTS tipo_material_enum CASCADE;
DROP TYPE IF EXISTS estado_material_enum CASCADE;
DROP TYPE IF EXISTS tipo_traballador_enum CASCADE;
DROP TYPE IF EXISTS estado_membresia_enum CASCADE;
DROP TYPE IF EXISTS tipo_usuario_enum CASCADE;
 