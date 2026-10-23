CREATE TABLE produto (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome_produto VARCHAR(100) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    preco DECIMAL(12,2) NOT NULL,
    status VARCHAR(10) NOT NULL DEFAULT 'ATIVO',

    CONSTRAINT pk_produto PRIMARY KEY (id),
    CONSTRAINT ck_produto_preco CHECK (preco > 0),
    CONSTRAINT ck_produto_status
        CHECK (status IN ('ATIVO', 'INATIVO'))
);