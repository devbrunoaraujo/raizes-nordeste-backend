CREATE TABLE usuario (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(254) NOT NULL,
    senha_hash VARCHAR(255) NOT NULL,
    perfil VARCHAR(20) NOT NULL,
    id_unidade BIGINT NULL,
    status VARCHAR(10) NOT NULL DEFAULT 'ATIVO',
    criado_em DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),

    participa_fidelidade BOOLEAN NOT NULL DEFAULT FALSE,
    aceita_marketing BOOLEAN NOT NULL DEFAULT FALSE,
    fidelidade_aceita_em DATETIME(3) NULL,
    marketing_aceito_em DATETIME(3) NULL,

    CONSTRAINT pk_usuario PRIMARY KEY (id),

    CONSTRAINT uk_usuario_email UNIQUE (email),

    CONSTRAINT fk_usuario_unidade
        FOREIGN KEY (id_unidade) REFERENCES unidade (id),

    CONSTRAINT ck_usuario_perfil
        CHECK (
            perfil IN (
                'CLIENTE', 'ATENDENTE', 'COZINHA', 'GERENTE', 'MATRIZ'
            )
        ),

    CONSTRAINT ck_usuario_status
        CHECK (status IN ('ATIVO', 'INATIVO')),

    CONSTRAINT ck_usuario_vinculo_unidade
        CHECK (
            (
                perfil IN ('ATENDENTE', 'COZINHA', 'GERENTE')
                AND id_unidade IS NOT NULL
            )
            OR
            (
                perfil IN ('CLIENTE', 'MATRIZ')
                AND id_unidade IS NULL
            )
        )
);