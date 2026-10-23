CREATE TABLE estoque_unidade (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_produto BIGINT NOT NULL,
    id_unidade BIGINT NOT NULL,
    estoque_total INT NOT NULL DEFAULT 0,
    estoque_reservado INT NOT NULL DEFAULT 0,

    CONSTRAINT pk_estoque_unidade PRIMARY KEY (id),

    CONSTRAINT fk_estoque_produto
        FOREIGN KEY (id_produto) REFERENCES produto (id),

    CONSTRAINT fk_estoque_unidade
        FOREIGN KEY (id_unidade) REFERENCES unidade (id),

    CONSTRAINT uk_estoque_produto_unidade
        UNIQUE (id_unidade, id_produto),

    CONSTRAINT ck_estoque_total
        CHECK (estoque_total >= 0),

    CONSTRAINT ck_estoque_reservado
        CHECK (estoque_reservado >= 0),

    CONSTRAINT ck_estoque_limite_reserva
        CHECK (estoque_reservado <= estoque_total)
);