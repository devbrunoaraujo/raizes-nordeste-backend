CREATE TABLE item_pedido (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_produto BIGINT NOT NULL,
    id_pedido BIGINT NOT NULL,
    quantidade INT NOT NULL,
    preco_unitario DECIMAL(12,2) NOT NULL,

    CONSTRAINT pk_item_pedido PRIMARY KEY (id),

    CONSTRAINT fk_item_pedido_produto
        FOREIGN KEY (id_produto) REFERENCES produto (id),

    CONSTRAINT fk_item_pedido_pedido
        FOREIGN KEY (id_pedido) REFERENCES pedido (id),

    CONSTRAINT ck_item_pedido_quantidade
        CHECK (quantidade > 0),

    CONSTRAINT ck_item_pedido_preco
        CHECK (preco_unitario > 0)
);