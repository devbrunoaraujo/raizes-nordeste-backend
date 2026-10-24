CREATE TABLE conta_fidelidade (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_cliente BIGINT NOT NULL,
    saldo_total INT NOT NULL DEFAULT 0,
    saldo_reservado INT NOT NULL DEFAULT 0,

    CONSTRAINT pk_conta_fidelidade PRIMARY KEY (id),

    CONSTRAINT uk_conta_fidelidade_cliente UNIQUE (id_cliente),

    CONSTRAINT fk_conta_fidelidade_cliente
        FOREIGN KEY (id_cliente) REFERENCES usuario (id),

    CONSTRAINT ck_conta_fidelidade_total
        CHECK (saldo_total >= 0),

    CONSTRAINT ck_conta_fidelidade_reservado
        CHECK (saldo_reservado >= 0),

    CONSTRAINT ck_conta_fidelidade_limite
        CHECK (saldo_reservado <= saldo_total)
);