CREATE TABLE unidade (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         nome VARCHAR(100) NOT NULL,
                         endereco VARCHAR(255) NOT NULL,
                         cidade VARCHAR(100) NOT NULL,
                         estado CHAR(2) NOT NULL,
                         status VARCHAR(10) NOT NULL DEFAULT 'ATIVA',

                         CONSTRAINT pk_unidade PRIMARY KEY (id),
                         CONSTRAINT ck_unidade_status
                             CHECK (status IN ('ATIVA', 'INATIVA'))
);