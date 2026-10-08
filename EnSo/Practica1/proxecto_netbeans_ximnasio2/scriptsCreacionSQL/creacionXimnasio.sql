
-- TIPOS ENUM (DOMINIOS PECHADOS)

CREATE TYPE tipo_usuario_enum AS ENUM (
    'CLIENTE',
    'TRABALLADOR'
);

CREATE TYPE estado_membresia_enum AS ENUM (
    'ACTIVO',
    'INACTIVO',
    'MANTEMENTO',
    'BAIXA_PROGRAMADA'
);

CREATE TYPE tipo_traballador_enum AS ENUM (
    'ADMINISTRADOR',
    'MONITOR',
    'LIMPEZA'
);

CREATE TYPE estado_material_enum AS ENUM (
    'OPERATIVO',
    'ROTO',
    'MANTEMENTO'
);

CREATE TYPE tipo_material_enum AS ENUM (
    'PESO_LIBRE',
    'MAQUINA',
    'SACO',
    'GUANTE'
);

CREATE TYPE metodo_pago_enum AS ENUM (
	'EFECTIVO', 
	'TARXETA', 
	'TRANSFERENCIA'
);

-- ------------------------------------------------------------
-- XIMNASIO
-- ------------------------------------------------------------
CREATE TABLE XIMNASIO (
    nome       VARCHAR(100) NOT NULL,
    direccion  VARCHAR(200),
    telefono   VARCHAR(20),
    CONSTRAINT PK_XIMNASIO PRIMARY KEY (nome)
);

-- ------------------------------------------------------------
-- SALA
-- ------------------------------------------------------------
CREATE TABLE SALA (
    idSala        SERIAL,
    aforoMaximo   INTEGER      NOT NULL CHECK (aforoMaximo > 0),
    nomeXimnasio  VARCHAR(100) NOT NULL,
    CONSTRAINT PK_SALA PRIMARY KEY (idSala),
    CONSTRAINT FK_SALA_XIMNASIO FOREIGN KEY (nomeXimnasio)
        REFERENCES XIMNASIO(nome) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- CLASE
-- ------------------------------------------------------------
CREATE TABLE CLASE (
    modalidade   VARCHAR(100) NOT NULL,
    aforoMaximo  INTEGER     NOT NULL CHECK (aforoMaximo > 0),
    descripcion  TEXT,
    CONSTRAINT PK_CLASE PRIMARY KEY (modalidade)
);

-- ------------------------------------------------------------
-- USUARIO
-- ------------------------------------------------------------
CREATE TABLE USUARIO (
    Dni             VARCHAR(20)         NOT NULL,
    password_hash   VARCHAR(255)        NOT NULL,
    nome            VARCHAR(100)        NOT NULL,
    apelido1        VARCHAR(100)        NOT NULL,
    apelido2        VARCHAR(100),
    direccion       VARCHAR(200),
    movil           VARCHAR(20),
    email           VARCHAR(150)        NOT NULL UNIQUE,
    data_nacemento  DATE                NOT NULL,
    nomeXimnasio    VARCHAR(100)        NOT NULL,
    tipo_usuario    tipo_usuario_enum   NOT NULL,
    
    CONSTRAINT PK_USUARIO PRIMARY KEY (Dni),
    CONSTRAINT FK_USUARIO_XIMNASIO FOREIGN KEY (nomeXimnasio)
        REFERENCES XIMNASIO(nome) ON DELETE RESTRICT ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- CLIENTE
-- ------------------------------------------------------------
CREATE TABLE CLIENTE (
    Dni              VARCHAR(20)            NOT NULL,
    estado_membresia estado_membresia_enum  NOT NULL DEFAULT 'INACTIVO',
    
    CONSTRAINT PK_CLIENTE PRIMARY KEY (Dni),
    CONSTRAINT FK_CLIENTE_USUARIO FOREIGN KEY (Dni)
        REFERENCES USUARIO(Dni) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- TRABALLADOR
-- ------------------------------------------------------------
CREATE TABLE TRABALLADOR (
    Dni               VARCHAR(20)            NOT NULL,
    soldo             NUMERIC(10,2)         NOT NULL CHECK (soldo >= 0),
    horario           VARCHAR(100),
    tipo_traballador  tipo_traballador_enum  NOT NULL,
    
    CONSTRAINT PK_TRABALLADOR PRIMARY KEY (Dni),
    CONSTRAINT FK_TRABALLADOR_USUARIO FOREIGN KEY (Dni)
        REFERENCES USUARIO(Dni) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- ADMINISTRADOR
-- ------------------------------------------------------------
CREATE TABLE ADMINISTRADOR (
    Dni    VARCHAR(20)  NOT NULL,
    cargo  VARCHAR(100),
    
    CONSTRAINT PK_ADMINISTRADOR PRIMARY KEY (Dni),
    CONSTRAINT FK_ADMINISTRADOR_TRABALLADOR FOREIGN KEY (Dni)
        REFERENCES TRABALLADOR(Dni) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- LIMPEZA
-- ------------------------------------------------------------
CREATE TABLE LIMPEZA (
    Dni  VARCHAR(20) NOT NULL,
    
    CONSTRAINT PK_LIMPEZA PRIMARY KEY (Dni),
    CONSTRAINT FK_LIMPEZA_TRABALLADOR FOREIGN KEY (Dni)
        REFERENCES TRABALLADOR(Dni) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- MONITOR
-- ------------------------------------------------------------
CREATE TABLE MONITOR (
    Dni           VARCHAR(20)  NOT NULL,
    especialidade VARCHAR(100) NOT NULL, 
    
    CONSTRAINT PK_MONITOR PRIMARY KEY (Dni),
    CONSTRAINT FK_MONITOR_TRABALLADOR FOREIGN KEY (Dni)
        REFERENCES TRABALLADOR(Dni) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- MATERIAL
-- ------------------------------------------------------------
CREATE TABLE MATERIAL (
    codigoIdentificador  VARCHAR(50)           NOT NULL,
    nome                 VARCHAR(150)          NOT NULL,
    estado               estado_material_enum  NOT NULL DEFAULT 'OPERATIVO',
    idSala               INTEGER,
    tipo_material        tipo_material_enum    NOT NULL,
    
    CONSTRAINT PK_MATERIAL PRIMARY KEY (codigoIdentificador),
    CONSTRAINT FK_MATERIAL_SALA FOREIGN KEY (idSala)
        REFERENCES SALA(idSala) ON DELETE SET NULL ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- MATERIAL_PESO_LIBRE
-- ------------------------------------------------------------
CREATE TABLE MATERIAL_PESO_LIBRE (
    codigoIdentificador  VARCHAR(50)  NOT NULL,
    tipo                 VARCHAR(100),
    peso                 NUMERIC(6,2) NOT NULL CHECK (peso > 0),
   
    CONSTRAINT PK_PESO_LIBRE PRIMARY KEY (codigoIdentificador),
    CONSTRAINT FK_PESO_LIBRE_MATERIAL FOREIGN KEY (codigoIdentificador)
        REFERENCES MATERIAL(codigoIdentificador) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- MAQUINA
-- ------------------------------------------------------------
CREATE TABLE MAQUINA (
    codigoIdentificador  VARCHAR(50)  NOT NULL,
    tipo                 VARCHAR(100) NOT NULL,
    
    CONSTRAINT PK_MAQUINA PRIMARY KEY (codigoIdentificador),
    CONSTRAINT FK_MAQUINA_MATERIAL FOREIGN KEY (codigoIdentificador)
        REFERENCES MATERIAL(codigoIdentificador) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- SACOS
-- ------------------------------------------------------------
CREATE TABLE SACOS (
    codigoIdentificador  VARCHAR(50)  NOT NULL,
    material_fabrica     VARCHAR(100),
    peso                 NUMERIC(6,2) NOT NULL CHECK (peso > 0),
    
    CONSTRAINT PK_SACOS PRIMARY KEY (codigoIdentificador),
    CONSTRAINT FK_SACOS_MATERIAL FOREIGN KEY (codigoIdentificador)
        REFERENCES MATERIAL(codigoIdentificador) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- GUANTES
-- ------------------------------------------------------------
CREATE TABLE GUANTES (
    codigoIdentificador  VARCHAR(50)  NOT NULL,
    tipo                 VARCHAR(100) NOT NULL,
    
    CONSTRAINT PK_GUANTES PRIMARY KEY (codigoIdentificador),
    CONSTRAINT FK_GUANTES_MATERIAL FOREIGN KEY (codigoIdentificador)
        REFERENCES MATERIAL(codigoIdentificador) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- FACTURA
-- ------------------------------------------------------------
CREATE TABLE FACTURA (
    CodigoFactura  VARCHAR(50)       NOT NULL,
    dniCliente     VARCHAR(20)       NOT NULL,
    importe        NUMERIC(10,2)     NOT NULL CHECK (importe >= 0),
    fecha          DATE              NOT NULL DEFAULT CURRENT_DATE,
    metodoPago     metodo_pago_enum  NOT NULL,
    
    CONSTRAINT PK_FACTURA PRIMARY KEY (CodigoFactura, dniCliente),
    CONSTRAINT FK_FACTURA_CLIENTE FOREIGN KEY (dniCliente)
        REFERENCES CLIENTE(Dni) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- SESION
-- ------------------------------------------------------------
CREATE TABLE SESION (
    modalidade   VARCHAR(100) NOT NULL,
    dia          DATE         NOT NULL,
    slotHorario  VARCHAR(20)  NOT NULL,
    
    CONSTRAINT PK_SESION PRIMARY KEY (modalidade, dia, slotHorario),
    CONSTRAINT FK_SESION_CLASE FOREIGN KEY (modalidade)
        REFERENCES CLASE(modalidade) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- UBICASE  (Clase <-> Sala)
-- ------------------------------------------------------------
CREATE TABLE UBICASE (
    idSala      INTEGER      NOT NULL,
    modalidade  VARCHAR(100) NOT NULL,
    
    CONSTRAINT PK_UBICASE PRIMARY KEY (idSala, modalidade),
    CONSTRAINT FK_UBICASE_SALA FOREIGN KEY (idSala)
        REFERENCES SALA(idSala) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_UBICASE_CLASE FOREIGN KEY (modalidade)
        REFERENCES CLASE(modalidade) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- TUTOREA 
-- ------------------------------------------------------------
CREATE TABLE TUTOREA (
    DniMonitor   VARCHAR(20)  NOT NULL,
    modalidade   VARCHAR(100) NOT NULL,
    dia          DATE         NOT NULL,
    slotHorario  VARCHAR(20)  NOT NULL,
    
    CONSTRAINT PK_TUTOREA PRIMARY KEY (DniMonitor, modalidade, dia, slotHorario),
    CONSTRAINT FK_TUTOREA_MONITOR FOREIGN KEY (DniMonitor)
        REFERENCES MONITOR(Dni) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_TUTOREA_SESION FOREIGN KEY (modalidade, dia, slotHorario)
        REFERENCES SESION(modalidade, dia, slotHorario) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- ASISTE
-- ------------------------------------------------------------
CREATE TABLE ASISTE (
    DniCliente   VARCHAR(20)  NOT NULL,
    modalidade   VARCHAR(100) NOT NULL,
    dia          DATE         NOT NULL,
    slotHorario  VARCHAR(20)  NOT NULL,
    
    CONSTRAINT PK_ASISTE PRIMARY KEY (DniCliente, modalidade, dia, slotHorario),
    CONSTRAINT FK_ASISTE_CLIENTE FOREIGN KEY (DniCliente)
        REFERENCES CLIENTE(Dni) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_ASISTE_SESION FOREIGN KEY (modalidade, dia, slotHorario)
        REFERENCES SESION(modalidade, dia, slotHorario) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- LIMPIA  (Traballador de limpeza → Sala)
-- ------------------------------------------------------------
CREATE TABLE LIMPIA (
    DniTraballador  VARCHAR(20) NOT NULL,
    idSala          INTEGER     NOT NULL,
    CONSTRAINT PK_LIMPIA PRIMARY KEY (DniTraballador, idSala),
    CONSTRAINT FK_LIMPIA_LIMPEZA FOREIGN KEY (DniTraballador)
        REFERENCES LIMPEZA(Dni) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_LIMPIA_SALA FOREIGN KEY (idSala)
        REFERENCES SALA(idSala) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ------------------------------------------------------------
-- NECESITAN  (Clase necesita Material)
-- ------------------------------------------------------------
CREATE TABLE NECESITAN (
    modalidade           VARCHAR(100) NOT NULL,
    codigoIdentificador  VARCHAR(50)  NOT NULL,
    
    CONSTRAINT PK_NECESITAN PRIMARY KEY (modalidade, codigoIdentificador),
    CONSTRAINT FK_NECESITAN_CLASE FOREIGN KEY (modalidade)
        REFERENCES CLASE(modalidade) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_NECESITAN_MATERIAL FOREIGN KEY (codigoIdentificador)
        REFERENCES MATERIAL(codigoIdentificador) ON DELETE CASCADE ON UPDATE CASCADE
);

-- =========================================================
-- TRIGGERS DE COHERENCIA DE HERENCIA / SUBTIPOS
-- =========================================================

-- ---------------------------------------------------------
-- CLIENTE -> debe ser USUARIO de tipo CLIENTE
-- ---------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_validar_cliente_usuario()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM USUARIO u
        WHERE u.Dni = NEW.Dni
          AND u.tipo_usuario = 'CLIENTE'
    ) THEN
        RAISE EXCEPTION
        'Erro: o DNI % non corresponde a un USUARIO de tipo CLIENTE', NEW.Dni;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_validar_cliente_usuario ON CLIENTE;

CREATE TRIGGER trg_validar_cliente_usuario
BEFORE INSERT OR UPDATE ON CLIENTE
FOR EACH ROW
EXECUTE FUNCTION fn_validar_cliente_usuario();


-- ---------------------------------------------------------
-- TRABALLADOR -> debe ser USUARIO de tipo TRABALLADOR
-- ---------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_validar_traballador_usuario()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM USUARIO u
        WHERE u.Dni = NEW.Dni
          AND u.tipo_usuario = 'TRABALLADOR'
    ) THEN
        RAISE EXCEPTION
        'Erro: o DNI % non corresponde a un USUARIO de tipo TRABALLADOR', NEW.Dni;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_validar_traballador_usuario ON TRABALLADOR;

CREATE TRIGGER trg_validar_traballador_usuario
BEFORE INSERT OR UPDATE ON TRABALLADOR
FOR EACH ROW
EXECUTE FUNCTION fn_validar_traballador_usuario();


-- ---------------------------------------------------------
-- ADMINISTRADOR -> debe ser TRABALLADOR de tipo ADMINISTRADOR
-- ---------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_validar_administrador()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM TRABALLADOR t
        WHERE t.Dni = NEW.Dni
          AND t.tipo_traballador = 'ADMINISTRADOR'
    ) THEN
        RAISE EXCEPTION
        'Erro: o DNI % non corresponde a un TRABALLADOR de tipo ADMINISTRADOR', NEW.Dni;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_validar_administrador ON ADMINISTRADOR;

CREATE TRIGGER trg_validar_administrador
BEFORE INSERT OR UPDATE ON ADMINISTRADOR
FOR EACH ROW
EXECUTE FUNCTION fn_validar_administrador();


-- ---------------------------------------------------------
-- MONITOR -> debe ser TRABALLADOR de tipo MONITOR
-- ---------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_validar_monitor()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM TRABALLADOR t
        WHERE t.Dni = NEW.Dni
          AND t.tipo_traballador = 'MONITOR'
    ) THEN
        RAISE EXCEPTION
        'Erro: o DNI % non corresponde a un TRABALLADOR de tipo MONITOR', NEW.Dni;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_validar_monitor ON MONITOR;

CREATE TRIGGER trg_validar_monitor
BEFORE INSERT OR UPDATE ON MONITOR
FOR EACH ROW
EXECUTE FUNCTION fn_validar_monitor();


-- ---------------------------------------------------------
-- LIMPEZA -> debe ser TRABALLADOR de tipo LIMPEZA
-- ---------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_validar_limpeza()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM TRABALLADOR t
        WHERE t.Dni = NEW.Dni
          AND t.tipo_traballador = 'LIMPEZA'
    ) THEN
        RAISE EXCEPTION
        'Erro: o DNI % non corresponde a un TRABALLADOR de tipo LIMPEZA', NEW.Dni;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_validar_limpeza ON LIMPEZA;

CREATE TRIGGER trg_validar_limpeza
BEFORE INSERT OR UPDATE ON LIMPEZA
FOR EACH ROW
EXECUTE FUNCTION fn_validar_limpeza();


-- ---------------------------------------------------------
-- MATERIAL_PESO_LIBRE -> debe ser MATERIAL de tipo PESO_LIBRE
-- ---------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_validar_material_peso_libre()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM MATERIAL m
        WHERE m.codigoIdentificador = NEW.codigoIdentificador
          AND m.tipo_material = 'PESO_LIBRE'
    ) THEN
        RAISE EXCEPTION
        'Erro: o material % non corresponde ao tipo PESO_LIBRE', NEW.codigoIdentificador;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_validar_material_peso_libre ON MATERIAL_PESO_LIBRE;

CREATE TRIGGER trg_validar_material_peso_libre
BEFORE INSERT OR UPDATE ON MATERIAL_PESO_LIBRE
FOR EACH ROW
EXECUTE FUNCTION fn_validar_material_peso_libre();


-- ---------------------------------------------------------
-- MAQUINA -> debe ser MATERIAL de tipo MAQUINA
-- ---------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_validar_maquina()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM MATERIAL m
        WHERE m.codigoIdentificador = NEW.codigoIdentificador
          AND m.tipo_material = 'MAQUINA'
    ) THEN
        RAISE EXCEPTION
        'Erro: o material % non corresponde ao tipo MAQUINA', NEW.codigoIdentificador;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_validar_maquina ON MAQUINA;

CREATE TRIGGER trg_validar_maquina
BEFORE INSERT OR UPDATE ON MAQUINA
FOR EACH ROW
EXECUTE FUNCTION fn_validar_maquina();


-- ---------------------------------------------------------
-- SACOS -> debe ser MATERIAL de tipo SACO
-- ---------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_validar_sacos()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM MATERIAL m
        WHERE m.codigoIdentificador = NEW.codigoIdentificador
          AND m.tipo_material = 'SACO'
    ) THEN
        RAISE EXCEPTION
        'Erro: o material % non corresponde ao tipo SACO', NEW.codigoIdentificador;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_validar_sacos ON SACOS;

CREATE TRIGGER trg_validar_sacos
BEFORE INSERT OR UPDATE ON SACOS
FOR EACH ROW
EXECUTE FUNCTION fn_validar_sacos();


-- ---------------------------------------------------------
-- GUANTES -> debe ser MATERIAL de tipo GUANTE
-- ---------------------------------------------------------
CREATE OR REPLACE FUNCTION fn_validar_guantes()
RETURNS TRIGGER AS $$
BEGIN
    IF NOT EXISTS (
        SELECT 1
        FROM MATERIAL m
        WHERE m.codigoIdentificador = NEW.codigoIdentificador
          AND m.tipo_material = 'GUANTE'
    ) THEN
        RAISE EXCEPTION
        'Erro: o material % non corresponde ao tipo GUANTE', NEW.codigoIdentificador;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

DROP TRIGGER IF EXISTS trg_validar_guantes ON GUANTES;

CREATE TRIGGER trg_validar_guantes
BEFORE INSERT OR UPDATE ON GUANTES
FOR EACH ROW
EXECUTE FUNCTION fn_validar_guantes();
