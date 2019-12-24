CREATE TABLE financeiro.contrato
(
    id_contrato serial            NOT NULL,
    clausula    character varying NOT NULL,
    inicio      date              NOT NULL,
    termino     date              NOT NULL,
    CONSTRAINT id_contrato_pk PRIMARY KEY (id_contrato)
);

ALTER TABLE cadastro.estudante
    ADD COLUMN id_contrato integer;

ALTER TABLE cadastro.estudante
    ADD CONSTRAINT contrato_fk FOREIGN KEY (id_contrato)
        REFERENCES financeiro.contrato (id_contrato) MATCH FULL
        ON DELETE SET NULL ON UPDATE CASCADE;