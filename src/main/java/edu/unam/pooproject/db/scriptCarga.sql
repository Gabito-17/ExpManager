-- Insertar personas adicionales que serán miembros o involucradas en expedientes
INSERT INTO persona (dni, nombre, apellido, fechanacimiento, esmiembro, email)
VALUES
    ('67890123', 'Laura', 'Gómez', '1994-09-18', true, 'laura@example.com'),
    ('78901234', 'Gabriel', 'Díaz', '1996-02-22', false, 'gabriel@example.com'),
    ('89012345', 'Sofía', 'Rodríguez', '1991-06-30', true, 'sofia@example.com'),
    ('90123456', 'Javier', 'Pérez', '1999-04-05', true, 'javier@example.com'),
    ('01234567', 'Luis', 'Hernández', '1992-10-12', false, 'luis@example.com');

-- Insertar expedientes adicionales relacionados con las personas
INSERT INTO expediente (titulo, nota, fechaingreso, estado, iniciante_id)
VALUES
    ('Proyecto de Desarrollo de Software', 'Desarrollar una aplicación web para gestionar tareas.', CURRENT_DATE - INTERVAL '30' DAY * (random() * 365), true, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    ('Investigación en Ciencias Sociales', 'Realizar un estudio cualitativo sobre la percepción del cambio climático en la sociedad.', CURRENT_DATE - INTERVAL '30' DAY * (random() * 365), false, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    ('Presentación de Proyecto de Marketing', 'Elaborar una propuesta de estrategia de marketing para un nuevo producto.', CURRENT_DATE - INTERVAL '30' DAY * (random() * 365), true, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    ('Investigación en Medicina', 'Estudiar los efectos de un medicamento recientemente desarrollado en pacientes con una enfermedad específica.', CURRENT_DATE - INTERVAL '30' DAY * (random() * 365), false, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    ('Proyecto de Ingeniería Civil', 'Diseñar un puente para mejorar la conectividad vial en una región determinada.', CURRENT_DATE - INTERVAL '30' DAY * (random() * 365), true, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1));

-- Insertar reuniones adicionales relacionadas con las personas y los expedientes
INSERT INTO reunion (fecha, detalles, lugar, horaInicio, horaFin, estado)
VALUES
    ('2024-04-12', 'Reunión para revisar avances en el Proyecto de Desarrollo de Software', 'Sala de reuniones D', '14:00', '16:00', CASE WHEN '2024-04-12' < CURRENT_DATE THEN true ELSE false END),
    ('2024-04-13', 'Reunión para planificar la Investigación en Ciencias Sociales', 'Sala de conferencias E', '15:00', '17:00', CASE WHEN '2024-04-13' < CURRENT_DATE THEN true ELSE false END),
    ('2024-04-14', 'Reunión para discutir la Presentación de Proyecto de Marketing', 'Sala de juntas F', '16:00', '18:00', CASE WHEN '2024-04-14' < CURRENT_DATE THEN true ELSE false END),
    ('2024-04-15', 'Reunión para evaluar la Investigación en Medicina', 'Sala de conferencias G', '17:00', '19:00', CASE WHEN '2024-04-15' < CURRENT_DATE THEN true ELSE false END),
    ('2024-04-16', 'Reunión para revisar avances en el Proyecto de Ingeniería Civil', 'Sala de reuniones H', '18:00', '20:00', CASE WHEN '2024-04-16' < CURRENT_DATE THEN true ELSE false END);

-- Insertar relaciones adicionales entre expedientes y personas involucradas
INSERT INTO expediente_involucrado (expediente_id, involucrado_id)
VALUES
    (1, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    (1, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    (2, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    (2, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    (3, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    (3, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    (4, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    (4, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    (5, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    (5, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    (5, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1)),
    (5, (SELECT dni FROM persona WHERE esmiembro = true ORDER BY random() LIMIT 1));

-- Insertar relaciones adicionales entre expedientes y reuniones
INSERT INTO reunion_expediente (expediente_id, reunion_id)
VALUES
    (1, 2),
    (2, 5),
    (3, 1),
    (2, 5),
    (4, 3),
    (5, 4);

-- Insertar relaciones adicionales entre reuniones y personas sin repetir un miembro en una reunión
INSERT INTO reunion_miembro (reunion_id, miembro_id)
VALUES
    (1, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 3) ORDER BY random() LIMIT 1)),
    (1, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 3) ORDER BY random() LIMIT 1)),
    (2, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 4) ORDER BY random() LIMIT 1)),
    (2, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 4) ORDER BY random() LIMIT 1)),
    (3, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 5) ORDER BY random() LIMIT 1)),
    (3, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 5) ORDER BY random() LIMIT 1)),
    (4, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 6) ORDER BY random() LIMIT 1)),
    (4, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 6) ORDER BY random() LIMIT 1)),
    (5, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 7) ORDER BY random() LIMIT 1)),
    (5, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 7) ORDER BY random() LIMIT 1)),
    (5, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 8) ORDER BY random() LIMIT 1)),
    (3, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 8) ORDER BY random() LIMIT 1)),
    (3, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 9) ORDER BY random() LIMIT 1)),
    (4, (SELECT dni FROM persona WHERE esmiembro = true AND dni NOT IN (SELECT miembro_id FROM reunion_miembro WHERE reunion_id = 9) ORDER BY random() LIMIT 1));
-- Insertar asistencia de los miembros a las reuniones con valores aleatorios para 'asiste'
INSERT INTO asistencia (asiste, reunion_id, miembro_id)
SELECT
    CASE WHEN random() < 0.5 THEN true ELSE false END AS asiste,
    reunion_id,
    miembro_id
FROM
    reunion_miembro;
