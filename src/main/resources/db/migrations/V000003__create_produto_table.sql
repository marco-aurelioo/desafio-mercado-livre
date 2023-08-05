CREATE TABLE produtos(
    id SERIAL PRIMARY KEY ,
    nome VARCHAR(100) NOT NULL,
    valor DECIMAL(8,2) NOT NULL,
    quantidade_disponivel INT NOT NUll,
    descricao VARCHAR(1000) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    categoria_id INT,
    usuario_id INT,
    FOREIGN KEY (categoria_id) REFERENCES category (id),
    FOREIGN KEY (usuario_id) REFERENCES users (id)
);

CREATE TABLE grupo_caracteristicas (
    id SERIAL PRIMARY KEY ,
    grupo_caracteristicas VARCHAR(255)
);


CREATE TABLE produto_grupo_caracteristicas (
    produto_id INT,
    grupo_caracteristica_id INT,
    PRIMARY KEY (produto_id, grupo_caracteristica_id),
    FOREIGN KEY (produto_id) REFERENCES produtos (id),
    FOREIGN KEY (grupo_caracteristica_id) REFERENCES grupo_caracteristicas (id)
);

CREATE TABLE grupo_caracteristica (
     id SERIAL PRIMARY KEY ,
    grupo_caracteristicas VARCHAR(255)
);

-- Tabela CaracteristicaEntity
CREATE TABLE caracteristicas (
    id SERIAL PRIMARY KEY ,
    chave VARCHAR(255),
    valor VARCHAR(255),
    grupo_caracteristica_id INT,
    FOREIGN KEY (grupo_caracteristica_id) REFERENCES grupo_caracteristica(id)
);