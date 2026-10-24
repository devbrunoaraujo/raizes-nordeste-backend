CREATE TABLE tentativa_pagamento (
    id BIGINT NOT NULL AUTO_INCREMENT,
    id_pedido BIGINT NOT NULL,
    forma_pagamento VARCHAR(20) NOT NULL,
    valor DECIMAL(12,2) NOT NULL,
    status_pagamento VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    criado_em DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    respondido_em DATETIME(3) NULL,

    status_estorno VARCHAR(20) NOT NULL DEFAULT 'NAO_SOLICITADO',
    motivo_estorno VARCHAR(255) NULL,
    estorno_solicitado_em DATETIME(3) NULL,
    estorno_concluido_em DATETIME(3) NULL,

    CONSTRAINT pk_tentativa_pagamento PRIMARY KEY (id),

    CONSTRAINT fk_tentativa_pagamento_pedido
        FOREIGN KEY (id_pedido) REFERENCES pedido (id),

    CONSTRAINT ck_tentativa_pagamento_valor
        CHECK (valor > 0),

    CONSTRAINT ck_tentativa_pagamento_status
        CHECK (
            status_pagamento IN ('PENDENTE', 'APROVADO', 'RECUSADO')
        ),

    CONSTRAINT ck_tentativa_estorno_status
        CHECK (
            status_estorno IN (
                'NAO_SOLICITADO', 'SOLICITADO', 'CONCLUIDO', 'FALHOU'
            )
        ),

    CONSTRAINT ck_tentativa_estorno_aprovacao
        CHECK (
            status_estorno = 'NAO_SOLICITADO'
            OR status_pagamento = 'APROVADO'
        )
);