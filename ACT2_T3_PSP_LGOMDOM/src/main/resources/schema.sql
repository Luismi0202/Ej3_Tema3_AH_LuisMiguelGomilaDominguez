CREATE TABLE IF NOT EXISTS paises (
                                      id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                      nombre VARCHAR(100) NOT NULL,
    codigo_iso VARCHAR(3) NOT NULL,
    capital VARCHAR(100),
    poblacion BIGINT NOT NULL,
    superficie_km2 DOUBLE,
    moneda VARCHAR(50),
    idioma_principal VARCHAR(50),
    continente VARCHAR(30)
    );