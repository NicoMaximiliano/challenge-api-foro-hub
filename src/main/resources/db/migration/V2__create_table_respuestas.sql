CREATE TABLE respuestas (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    solucion BOOLEAN NOT NULL,
    topico_id BIGINT,
    usuario_id BIGINT
);