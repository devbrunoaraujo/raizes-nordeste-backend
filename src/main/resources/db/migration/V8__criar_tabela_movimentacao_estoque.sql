CREATE TABLE movimentacao_estoque (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_estoque_unidade BIGINT NOT NULL,
    id_funcionario BIGINT NULL,
    id_pedido BIGINT NULL,
    quantidade INT NOT NULL,
    tipo_movimentacao VARCHAR(30) NOT NULL,
    motivo VARCHAR(255) NOT NULL,
    criado_em DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),

    CONSTRAINT pk_movimentacao_estoque PRIMARY KEY (id),

    CONSTRAINT fk_mov_estoque_saldo
        FOREIGN KEY (id_estoque_unidade)
        REFERENCES estoque_unidade (id),

    CONSTRAINT fk_mov_estoque_funcionario
        FOREIGN KEY (id_funcionario) REFERENCES usuario (id),

    CONSTRAINT fk_mov_estoque_pedido
        FOREIGN KEY (id_pedido) REFERENCES pedido (id),

    CONSTRAINT ck_mov_estoque_quantidade
        CHECK (quantidade > 0),

    CONSTRAINT ck_mov_estoque_tipo
        CHECK (
            tipo_movimentacao IN (
                'ENTRADA', 'SAIDA_PEDIDO', 'AVARIA',
                'AJUSTE_ENTRADA', 'AJUSTE_SAIDA'
            )
        ),

    CONSTRAINT ck_mov_estoque_origem
        CHECK (
            (
                tipo_movimentacao = 'SAIDA_PEDIDO'
                AND id_pedido IS NOT NULL
                AND id_funcionario IS NULL
            )
            OR
            (
                tipo_movimentacao IN (
                    'ENTRADA', 'AVARIA',
                    'AJUSTE_ENTRADA', 'AJUSTE_SAIDA'
                )
                AND id_funcionario IS NOT NULL
            )
        )
);