CREATE TABLE pedido (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_cliente BIGINT NULL,
    id_unidade BIGINT NOT NULL,
    id_atendente BIGINT NULL,
    canal_pedido VARCHAR(10) NOT NULL,
    status VARCHAR(30) NOT NULL DEFAULT 'AGUARDANDO_PAGAMENTO',
    criado_em DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    expira_em DATETIME(3) NOT NULL,
    valor_total DECIMAL(12,2) NOT NULL,
    desconto DECIMAL(12,2) NOT NULL DEFAULT 0,
    subtotal DECIMAL(12,2) NOT NULL,

    CONSTRAINT pk_pedido PRIMARY KEY (id),

    CONSTRAINT fk_pedido_cliente
        FOREIGN KEY (id_cliente) REFERENCES usuario (id),

    CONSTRAINT fk_pedido_unidade
        FOREIGN KEY (id_unidade) REFERENCES unidade (id),

    CONSTRAINT fk_pedido_atendente
        FOREIGN KEY (id_atendente) REFERENCES usuario (id),

    CONSTRAINT ck_pedido_canal
        CHECK (
            canal_pedido IN ('APP', 'TOTEM', 'BALCAO', 'PICKUP', 'WEB')
        ),

    CONSTRAINT ck_pedido_status
        CHECK (
            status IN (
                'AGUARDANDO_PAGAMENTO',
                'EM_PREPARO',
                'PRONTO_PARA_RETIRADA',
                'ENTREGUE',
                'CANCELADO'
            )
        ),

    CONSTRAINT ck_pedido_valores
        CHECK (
            subtotal >= 0
            AND desconto >= 0
            AND desconto <= subtotal
            AND valor_total = subtotal - desconto
        ),

    CONSTRAINT ck_pedido_prazo
        CHECK (expira_em > criado_em)
);