DROP TABLE IF EXISTS financeiro.apoio CASCADE;
 DROP TABLE IF EXISTS financeiro.investimento CASCADE;
 DROP TABLE IF EXISTS financeiro.prestacao_conta CASCADE;

 CREATE TABLE financeiro.prestacao_conta
(
	id_prestacao_conta serial NOT NULL,
	id_instituicao integer,
	recebimento date,
	valor numeric,
	CONSTRAINT id_prestacao_conta_pk PRIMARY KEY (id_prestacao_conta),
	CONSTRAINT id_instituicao_fk FOREIGN KEY (id_instituicao)
		REFERENCES cadastro.instituicao (id_instituicao) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
)
	WITH (
	OIDS=FALSE
			 );

CREATE TABLE financeiro.pagamento
(
	id_pagamento serial NOT NULL,
	id_prestacao_conta integer,
	pagamento date,
	valor numeric,
	CONSTRAINT id_pagamento_pk PRIMARY KEY (id_pagamento),
	CONSTRAINT id_prestacao_conta_fk FOREIGN KEY (id_prestacao_conta)
		REFERENCES financeiro.prestacao_conta (id_prestacao_conta) MATCH SIMPLE
		ON UPDATE NO ACTION ON DELETE NO ACTION
)
	WITH (
	OIDS=FALSE
			 );

CREATE TABLE financeiro.prestacao_conta_anexo
(
	id_prestacao_conta integer,
	id_anexo integer,
	CONSTRAINT prestacao_conta_anexo_pk PRIMARY KEY (id_anexo, id_prestacao_conta)
)
	WITH (
	OIDS=FALSE
			 );