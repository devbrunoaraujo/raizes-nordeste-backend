CREATE TABLE movimentacao_pontos (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_conta_fidelidade BIGINT NOT NULL,
    id_pedido BIGINT NULL,
    quantidade INT NOT NULL,
    tipo_operacao VARCHAR(30) NOT NULL,
    criado_em DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),

    CONSTRAINT pk_movimentacao_pontos PRIMARY KEY (id),

    CONSTRAINT fk_mov_pontos_conta
        FOREIGN KEY (id_conta_fidelidade)
        REFERENCES conta_fidelidade (id),

    CONSTRAINT fk_mov_pontos_pedido
        FOREIGN KEY (id_pedido) REFERENCES pedido (id),

    CONSTRAINT ck_mov_pontos_quantidade
        CHECK (quantidade > 0),

    CONSTRAINT ck_mov_pontos_tipo
        CHECK (
            tipo_operacao IN (
                'CREDITO', 'RESERVA', 'LIBERACAO_RESERVA',
                'RESGATE', 'DEVOLUCAO', 'PERDA_SAIDA_PROGRAMA'
            )
        ),

    CONSTRAINT ck_mov_pontos_origem
        CHECK (
            (
                tipo_operacao = 'PERDA_SAIDA_PROGRAMA'
                AND id_pedido IS NULL
            )
            OR
            (
                tipo_operacao <> 'PERDA_SAIDA_PROGRAMA'
                AND id_pedido IS NOT NULL
            )
        )
);