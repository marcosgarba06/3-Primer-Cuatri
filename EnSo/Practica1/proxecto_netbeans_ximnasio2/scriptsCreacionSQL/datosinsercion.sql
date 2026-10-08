-- Script de datos de proba

INSERT INTO XIMNASIO (nome, direccion, telefono)
VALUES ('Ximnasio Central', 'Rua Principal 123, Santiago', '600123123');

INSERT INTO SALA (aforoMaximo, nomeXimnasio)
VALUES
    (20, 'Ximnasio Central'),
    (15, 'Ximnasio Central'),
    (30, 'Ximnasio Central');

INSERT INTO CLASE (modalidade, aforoMaximo, descripcion)
VALUES
    ('Yoga', 20, 'Clase de relaxación e estiramentos'),
    ('Spinning', 15, 'Clase intensiva en bicicleta'),
    ('Crossfit', 25, 'Entrenamento funcional de alta intensidade');

-- USUARIO
-- Contraseñas de ejemplo:
-- 11111111A -> "hash1"
-- 22222222B -> "hash2"
-- 33333333C -> "hash3"
-- 44444444D -> "hash4"
-- 55555555E -> "hash5"
-- 66666666F -> "hash6"
--
-- Las contraseñas estan calculadas para que coincida con aplicacion.UtilidadesSeguridade.sha256(...)
-- para obntener el hash de las mismas se usa el programa GenerarHashes.java que usa el mismo método
-- para generar los hashes que la propia aplicacion

INSERT INTO USUARIO
(Dni, password_hash, nome, apelido1, apelido2, direccion, movil, email, data_nacemento, nomeXimnasio, tipo_usuario)
VALUES
    ('11111111A', 'af316ecb91a8ee7ae99210702b2d4758f30cdde3bf61e3d8e787d74681f90a6e', 'Ana', 'Lopez', 'Perez', 'Rua 1', '600111111', 'ana@mail.com', '2000-05-10', 'Ximnasio Central', 'CLIENTE'),
    ('22222222B', 'e7bf382f6e5915b3f88619b866223ebf1d51c4c5321cccde2e9ff700a3259086', 'Luis', 'Garcia', 'Diaz', 'Rua 2', '600222222', 'luis@mail.com', '1998-03-02', 'Ximnasio Central', 'CLIENTE'),
    ('33333333C', '42caa4abb7b60f8f914e5bfb8e6511d7d9bd9817de719b74251755d97fe97bf1', 'Maria', 'Fernandez', 'Lopez', 'Rua 3', '600333333', 'maria@mail.com', '1990-01-01', 'Ximnasio Central', 'TRABALLADOR'),
    ('44444444D', '1c27099b3b84b13d0e3fbd299ba93ae7853ec1d0d3a4e5daa89e68b7ad59d7cb', 'Carlos', 'Perez', 'Santos', 'Rua 4', '600444444', 'carlos@mail.com', '1985-06-15', 'Ximnasio Central', 'TRABALLADOR'),
    ('55555555E', '7da450ab64a26820e56dd73cd346950d656e60a20dba00bd4be9ced75ba7cdef', 'Lucia', 'Martinez', 'Gomez', 'Rua 5', '600555555', 'lucia@mail.com', '1992-09-20', 'Ximnasio Central', 'TRABALLADOR'),
    ('66666666F', 'f1a378f474d1b799d77111258db6989a9a1e1a8cdfe047ca64a78dc2a7e6a6f2', 'Paula', 'Sanchez', 'Varela', 'Rua 6', '600666666', 'paula@mail.com', '1994-02-12', 'Ximnasio Central', 'TRABALLADOR');

INSERT INTO CLIENTE (Dni, estado_membresia)
VALUES
    ('11111111A', 'ACTIVO'),
    ('22222222B', 'ACTIVO');

INSERT INTO TRABALLADOR (Dni, soldo, horario, tipo_traballador)
VALUES
    ('33333333C', 1500.00, 'Mañana', 'MONITOR'),
    ('44444444D', 1800.00, 'Tarde', 'ADMINISTRADOR'),
    ('55555555E', 1200.00, 'Mañana', 'LIMPEZA'),
    ('66666666F', 1200.00, 'Tarde', 'LIMPEZA');

INSERT INTO MONITOR (Dni, especialidade)
VALUES ('33333333C', 'Fitness');

INSERT INTO ADMINISTRADOR (Dni, cargo)
VALUES ('44444444D', 'Xefe');

INSERT INTO LIMPEZA (Dni)
VALUES
    ('55555555E'),
    ('66666666F');

INSERT INTO SESION (modalidade, dia, slotHorario)
VALUES
    ('Yoga', '2026-05-01', '10:00'),
    ('Spinning', '2026-05-01', '11:00'),
    ('Crossfit', '2026-05-02', '12:00');

INSERT INTO UBICASE (idSala, modalidade)
VALUES
    (1, 'Yoga'),
    (2, 'Spinning'),
    (3, 'Crossfit');

INSERT INTO TUTOREA (DniMonitor, modalidade, dia, slotHorario)
VALUES
    ('33333333C', 'Yoga', '2026-05-01', '10:00'),
    ('33333333C', 'Spinning', '2026-05-01', '11:00');

INSERT INTO ASISTE (DniCliente, modalidade, dia, slotHorario)
VALUES
    ('11111111A', 'Yoga', '2026-05-01', '10:00'),
    ('22222222B', 'Spinning', '2026-05-01', '11:00');

INSERT INTO FACTURA (CodigoFactura, dniCliente, importe, metodoPago)
VALUES
    ('F001', '11111111A', 30.00, 'TARXETA'),
    ('F002', '22222222B', 30.00, 'EFECTIVO');

INSERT INTO MATERIAL (codigoIdentificador, nome, estado, idSala, tipo_material)
VALUES
    ('MAT-001', 'Mancuernas', 'OPERATIVO', 1, 'PESO_LIBRE'),
    ('MAT-002', 'Cinta correr', 'OPERATIVO', 2, 'MAQUINA'),
    ('MAT-003', 'Saco boxeo', 'OPERATIVO', 3, 'SACO'),
    ('MAT-004', 'Guantes boxeo', 'OPERATIVO', 3, 'GUANTE');

INSERT INTO MATERIAL_PESO_LIBRE (codigoIdentificador, tipo, peso)
VALUES ('MAT-001', 'Mancuerna', 10.0);

INSERT INTO MAQUINA (codigoIdentificador, tipo)
VALUES ('MAT-002', 'Cardio');

INSERT INTO SACOS (codigoIdentificador, material_fabrica, peso)
VALUES ('MAT-003', 'Cuero', 20.0);

INSERT INTO GUANTES (codigoIdentificador, tipo)
VALUES ('MAT-004', 'Boxeo');

INSERT INTO LIMPIA (DniTraballador, idSala)
VALUES ('55555555E', 1);

INSERT INTO NECESITAN (modalidade, codigoIdentificador)
VALUES
    ('Yoga', 'MAT-001'),
    ('Spinning', 'MAT-002'),
    ('Crossfit', 'MAT-003');
