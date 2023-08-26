create table opniao(
    id SERIAL PRIMARY KEY ,
    titulo VARCHAR(255) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    nota INTEGER NOT NULL,
    usuario_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produtos (id),
    FOREIGN KEY (usuario_id) REFERENCES users (id)
);