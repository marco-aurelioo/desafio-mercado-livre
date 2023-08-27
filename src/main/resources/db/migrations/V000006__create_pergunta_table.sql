create table perguntas(
    id SERIAL PRIMARY KEY ,
    titulo VARCHAR(255) NOT NULL,
    pergunta VARCHAR(1000) NOT NULL,
    user_owner_id INTEGER NOT NULL,
    produto_id INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    FOREIGN KEY (produto_id) REFERENCES produtos (id),
    FOREIGN KEY (user_owner_id) REFERENCES users (id)
);