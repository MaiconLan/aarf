-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.8.2
-- PostgreSQL version: 9.5
-- Project Site: pgmodeler.com.br
-- Model Author: ---


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: aarf | type: DATABASE --
-- -- DROP DATABASE IF EXISTS aarf;
-- CREATE DATABASE aarf
-- ;
-- -- ddl-end --
--

-- object: cadastro | type: SCHEMA --
-- DROP SCHEMA IF EXISTS cadastro CASCADE;
CREATE SCHEMA cadastro;
-- ddl-end --
ALTER SCHEMA cadastro OWNER TO postgres;
-- ddl-end --

-- object: matricula | type: SCHEMA --
-- DROP SCHEMA IF EXISTS matricula CASCADE;
CREATE SCHEMA matricula;
-- ddl-end --
ALTER SCHEMA matricula OWNER TO postgres;
-- ddl-end --

-- object: publico | type: SCHEMA --
-- DROP SCHEMA IF EXISTS publico CASCADE;
CREATE SCHEMA publico;
-- ddl-end --
ALTER SCHEMA publico OWNER TO postgres;
-- ddl-end --

-- object: financeiro | type: SCHEMA --
-- DROP SCHEMA IF EXISTS financeiro CASCADE;
CREATE SCHEMA financeiro;
-- ddl-end --
ALTER SCHEMA financeiro OWNER TO postgres;
-- ddl-end --

SET search_path TO pg_catalog,public,cadastro,matricula,publico,financeiro;
-- ddl-end --

-- object: cadastro.endereco | type: TABLE --
-- DROP TABLE IF EXISTS cadastro.endereco CASCADE;
CREATE TABLE cadastro.endereco(
	id_endereco serial NOT NULL,
	id_pessoa integer,
	cep character varying(8) NOT NULL,
	logradouro character varying NOT NULL,
	complemento character varying,
	bairro character varying,
	CONSTRAINT id_endereco_pk PRIMARY KEY (id_endereco)

);
-- ddl-end --
ALTER TABLE cadastro.endereco OWNER TO postgres;
-- ddl-end --

-- object: cadastro.pessoa | type: TABLE --
-- DROP TABLE IF EXISTS cadastro.pessoa CASCADE;
CREATE TABLE cadastro.pessoa(
	id_pessoa serial NOT NULL,
	nome character varying NOT NULL,
	nascimento date NOT NULL,
	cpf character varying(11) NOT NULL,
	rg character varying(7) NOT NULL,
	email character varying NOT NULL,
	genero character NOT NULL,
	telefone character varying(10) NOT NULL,
	celular character varying(11) NOT NULL,
	CONSTRAINT id_pessoa_pk PRIMARY KEY (id_pessoa)

);
-- ddl-end --
ALTER TABLE cadastro.pessoa OWNER TO postgres;
-- ddl-end --

-- object: cadastro.usuario | type: TABLE --
-- DROP TABLE IF EXISTS cadastro.usuario CASCADE;
CREATE TABLE cadastro.usuario(
	id_usuario serial NOT NULL,
	login character varying NOT NULL,
	senha character varying NOT NULL,
	CONSTRAINT id_usuario_pk PRIMARY KEY (id_usuario)

);
-- ddl-end --
ALTER TABLE cadastro.usuario OWNER TO postgres;
-- ddl-end --

-- object: cadastro.associado | type: TABLE --
-- DROP TABLE IF EXISTS cadastro.associado CASCADE;
CREATE TABLE cadastro.associado(
	id_associado serial NOT NULL,
	id_pessoa integer,
	id_usuario integer,
	cargo character varying NOT NULL,
	CONSTRAINT id_associado PRIMARY KEY (id_associado)

);
-- ddl-end --
ALTER TABLE cadastro.associado OWNER TO postgres;
-- ddl-end --

-- object: cadastro.estudante | type: TABLE --
-- DROP TABLE IF EXISTS cadastro.estudante CASCADE;
CREATE TABLE cadastro.estudante(
	id_estudante serial NOT NULL,
	id_pessoa integer,
	id_instituicao integer,
	id_usuario integer,
	CONSTRAINT id_estudante PRIMARY KEY (id_estudante)

);
-- ddl-end --
ALTER TABLE cadastro.estudante OWNER TO postgres;
-- ddl-end --

-- object: pessoa_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.associado DROP CONSTRAINT IF EXISTS pessoa_fk CASCADE;
ALTER TABLE cadastro.associado ADD CONSTRAINT pessoa_fk FOREIGN KEY (id_pessoa)
REFERENCES cadastro.pessoa (id_pessoa) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: pessoa_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.estudante DROP CONSTRAINT IF EXISTS pessoa_fk CASCADE;
ALTER TABLE cadastro.estudante ADD CONSTRAINT pessoa_fk FOREIGN KEY (id_pessoa)
REFERENCES cadastro.pessoa (id_pessoa) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: cadastro.instituicao | type: TABLE --
-- DROP TABLE IF EXISTS cadastro.instituicao CASCADE;
CREATE TABLE cadastro.instituicao(
	id_instituicao serial NOT NULL,
	nome character varying NOT NULL,
	tipo character varying NOT NULL,
	CONSTRAINT id_instituicao PRIMARY KEY (id_instituicao),
	CONSTRAINT ck_tipo CHECK (tipo IN ('Educacao', 'Financeira'))

);
-- ddl-end --
ALTER TABLE cadastro.instituicao OWNER TO postgres;
-- ddl-end --

-- object: instituicao_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.estudante DROP CONSTRAINT IF EXISTS instituicao_fk CASCADE;
ALTER TABLE cadastro.estudante ADD CONSTRAINT instituicao_fk FOREIGN KEY (id_instituicao)
REFERENCES cadastro.instituicao (id_instituicao) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: cadastro.historico_instituicao | type: TABLE --
-- DROP TABLE IF EXISTS cadastro.historico_instituicao CASCADE;
CREATE TABLE cadastro.historico_instituicao(
	id_historico_instituicao serial NOT NULL,
	id_estudante integer,
	id_instituicao integer,
	termino date NOT NULL,
	CONSTRAINT id_historico_instituicao_pk PRIMARY KEY (id_historico_instituicao)

);
-- ddl-end --
ALTER TABLE cadastro.historico_instituicao OWNER TO postgres;
-- ddl-end --

-- object: estudante_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.historico_instituicao DROP CONSTRAINT IF EXISTS estudante_fk CASCADE;
ALTER TABLE cadastro.historico_instituicao ADD CONSTRAINT estudante_fk FOREIGN KEY (id_estudante)
REFERENCES cadastro.estudante (id_estudante) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: matricula.matricula | type: TABLE --
-- DROP TABLE IF EXISTS matricula.matricula CASCADE;
CREATE TABLE matricula.matricula(
	id_matricula serial NOT NULL,
	id_edital integer,
	id_estudante integer,
	inscricao timestamp NOT NULL,
	confirmacao timestamp,
	CONSTRAINT id_matricula_pk PRIMARY KEY (id_matricula)

);
-- ddl-end --
ALTER TABLE matricula.matricula OWNER TO postgres;
-- ddl-end --

-- object: matricula.edital | type: TABLE --
-- DROP TABLE IF EXISTS matricula.edital CASCADE;
CREATE TABLE matricula.edital(
	id_edital serial NOT NULL,
	titulo character varying NOT NULL,
	inicio date NOT NULL,
	termino date NOT NULL,
	finalizado boolean,
	CONSTRAINT id_edital_pk PRIMARY KEY (id_edital)

);
-- ddl-end --
ALTER TABLE matricula.edital OWNER TO postgres;
-- ddl-end --

-- object: estudante_fk | type: CONSTRAINT --
-- ALTER TABLE matricula.matricula DROP CONSTRAINT IF EXISTS estudante_fk CASCADE;
ALTER TABLE matricula.matricula ADD CONSTRAINT estudante_fk FOREIGN KEY (id_estudante)
REFERENCES cadastro.estudante (id_estudante) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: edital_fk | type: CONSTRAINT --
-- ALTER TABLE matricula.matricula DROP CONSTRAINT IF EXISTS edital_fk CASCADE;
ALTER TABLE matricula.matricula ADD CONSTRAINT edital_fk FOREIGN KEY (id_edital)
REFERENCES matricula.edital (id_edital) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: matricula.cancelamento | type: TABLE --
-- DROP TABLE IF EXISTS matricula.cancelamento CASCADE;
CREATE TABLE matricula.cancelamento(
	id_cancelamento serial NOT NULL,
	id_matricula integer,
	cancelamento timestamp NOT NULL,
	motivo character varying NOT NULL,
	CONSTRAINT id_cancelamento_pk PRIMARY KEY (id_cancelamento)

);
-- ddl-end --
ALTER TABLE matricula.cancelamento OWNER TO postgres;
-- ddl-end --

-- object: matricula_fk | type: CONSTRAINT --
-- ALTER TABLE matricula.cancelamento DROP CONSTRAINT IF EXISTS matricula_fk CASCADE;
ALTER TABLE matricula.cancelamento ADD CONSTRAINT matricula_fk FOREIGN KEY (id_matricula)
REFERENCES matricula.matricula (id_matricula) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: cancelamento_uq | type: CONSTRAINT --
-- ALTER TABLE matricula.cancelamento DROP CONSTRAINT IF EXISTS cancelamento_uq CASCADE;
ALTER TABLE matricula.cancelamento ADD CONSTRAINT cancelamento_uq UNIQUE (id_matricula);
-- ddl-end --

-- object: pessoa_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.endereco DROP CONSTRAINT IF EXISTS pessoa_fk CASCADE;
ALTER TABLE cadastro.endereco ADD CONSTRAINT pessoa_fk FOREIGN KEY (id_pessoa)
REFERENCES cadastro.pessoa (id_pessoa) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: endereco_uq | type: CONSTRAINT --
-- ALTER TABLE cadastro.endereco DROP CONSTRAINT IF EXISTS endereco_uq CASCADE;
ALTER TABLE cadastro.endereco ADD CONSTRAINT endereco_uq UNIQUE (id_pessoa);
-- ddl-end --

-- object: publico.noticia | type: TABLE --
-- DROP TABLE IF EXISTS publico.noticia CASCADE;
CREATE TABLE publico.noticia(
	id_noticia serial NOT NULL,
	id_associado integer,
	titulo character varying NOT NULL,
	publicacao date NOT NULL,
	severidade character varying,
	abrangencia character varying NOT NULL,
	conteudo character varying(280) NOT NULL,
	CONSTRAINT id_noticia_pk PRIMARY KEY (id_noticia),
	CONSTRAINT ck_severidade CHECK (severidade IN ('Baixa', 'Alta', 'Urgente')),
	CONSTRAINT ck_abrangencia CHECK (abrangencia IN ('Local', 'Geral'))

);
-- ddl-end --
ALTER TABLE publico.noticia OWNER TO postgres;
-- ddl-end --

-- object: associado_fk | type: CONSTRAINT --
-- ALTER TABLE publico.noticia DROP CONSTRAINT IF EXISTS associado_fk CASCADE;
ALTER TABLE publico.noticia ADD CONSTRAINT associado_fk FOREIGN KEY (id_associado)
REFERENCES cadastro.associado (id_associado) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: usuario_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.associado DROP CONSTRAINT IF EXISTS usuario_fk CASCADE;
ALTER TABLE cadastro.associado ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario)
REFERENCES cadastro.usuario (id_usuario) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: associado_uq | type: CONSTRAINT --
-- ALTER TABLE cadastro.associado DROP CONSTRAINT IF EXISTS associado_uq CASCADE;
ALTER TABLE cadastro.associado ADD CONSTRAINT associado_uq UNIQUE (id_usuario);
-- ddl-end --

-- object: usuario_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.estudante DROP CONSTRAINT IF EXISTS usuario_fk CASCADE;
ALTER TABLE cadastro.estudante ADD CONSTRAINT usuario_fk FOREIGN KEY (id_usuario)
REFERENCES cadastro.usuario (id_usuario) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: estudante_uq | type: CONSTRAINT --
-- ALTER TABLE cadastro.estudante DROP CONSTRAINT IF EXISTS estudante_uq CASCADE;
ALTER TABLE cadastro.estudante ADD CONSTRAINT estudante_uq UNIQUE (id_usuario);
-- ddl-end --

-- object: financeiro.apoio | type: TABLE --
-- DROP TABLE IF EXISTS financeiro.apoio CASCADE;
CREATE TABLE financeiro.apoio(
	id_apoio serial NOT NULL,
	id_instituicao integer,
	valor numeric NOT NULL,
	recebimento date,
	CONSTRAINT id_apoio_pk PRIMARY KEY (id_apoio)

);
-- ddl-end --
ALTER TABLE financeiro.apoio OWNER TO postgres;
-- ddl-end --

-- object: cadastro.anexo | type: TABLE --
-- DROP TABLE IF EXISTS cadastro.anexo CASCADE;
CREATE TABLE cadastro.anexo(
	id_anexo serial NOT NULL,
	nome character varying NOT NULL,
	caminho character varying NOT NULL,
	tipo character varying,
	CONSTRAINT id_anexo_pk PRIMARY KEY (id_anexo)

);
-- ddl-end --
ALTER TABLE cadastro.anexo OWNER TO postgres;
-- ddl-end --

-- object: financeiro.boleto | type: TABLE --
-- DROP TABLE IF EXISTS financeiro.boleto CASCADE;
CREATE TABLE financeiro.boleto(
	id_boleto serial NOT NULL,
	id_parcela integer,
	vencimento date NOT NULL,
	pagamento timestamp,
	CONSTRAINT id_boleto_pk PRIMARY KEY (id_boleto)

);
-- ddl-end --
ALTER TABLE financeiro.boleto OWNER TO postgres;
-- ddl-end --

-- object: instituicao_fk | type: CONSTRAINT --
-- ALTER TABLE financeiro.apoio DROP CONSTRAINT IF EXISTS instituicao_fk CASCADE;
ALTER TABLE financeiro.apoio ADD CONSTRAINT instituicao_fk FOREIGN KEY (id_instituicao)
REFERENCES cadastro.instituicao (id_instituicao) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: financeiro.parcela | type: TABLE --
-- DROP TABLE IF EXISTS financeiro.parcela CASCADE;
CREATE TABLE financeiro.parcela(
	id_parcela serial NOT NULL,
	id_matricula integer,
	numero integer NOT NULL,
	valor numeric NOT NULL,
	CONSTRAINT id_parcela_pk PRIMARY KEY (id_parcela)

);
-- ddl-end --
ALTER TABLE financeiro.parcela OWNER TO postgres;
-- ddl-end --

-- object: matricula_fk | type: CONSTRAINT --
-- ALTER TABLE financeiro.parcela DROP CONSTRAINT IF EXISTS matricula_fk CASCADE;
ALTER TABLE financeiro.parcela ADD CONSTRAINT matricula_fk FOREIGN KEY (id_matricula)
REFERENCES matricula.matricula (id_matricula) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: financeiro.investimento | type: TABLE --
-- DROP TABLE IF EXISTS financeiro.investimento CASCADE;
CREATE TABLE financeiro.investimento(
	id_investimento serial NOT NULL,
	id_apoio integer,
	valor numeric,
	pagamento date,
	CONSTRAINT id_investimento PRIMARY KEY (id_investimento)

);
-- ddl-end --
ALTER TABLE financeiro.investimento OWNER TO postgres;
-- ddl-end --

-- object: apoio_fk | type: CONSTRAINT --
-- ALTER TABLE financeiro.investimento DROP CONSTRAINT IF EXISTS apoio_fk CASCADE;
ALTER TABLE financeiro.investimento ADD CONSTRAINT apoio_fk FOREIGN KEY (id_apoio)
REFERENCES financeiro.apoio (id_apoio) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: instituicao_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.historico_instituicao DROP CONSTRAINT IF EXISTS instituicao_fk CASCADE;
ALTER TABLE cadastro.historico_instituicao ADD CONSTRAINT instituicao_fk FOREIGN KEY (id_instituicao)
REFERENCES cadastro.instituicao (id_instituicao) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: cadastro.instituicoes_noticias | type: TABLE --
-- DROP TABLE IF EXISTS cadastro.instituicoes_noticias CASCADE;
CREATE TABLE cadastro.instituicoes_noticias(
	id_instituicao integer,
	id_noticia_noticia integer,
	CONSTRAINT instituicoes_noticias_pk PRIMARY KEY (id_instituicao,id_noticia_noticia)

);
-- ddl-end --

-- object: instituicao_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.instituicoes_noticias DROP CONSTRAINT IF EXISTS instituicao_fk CASCADE;
ALTER TABLE cadastro.instituicoes_noticias ADD CONSTRAINT instituicao_fk FOREIGN KEY (id_instituicao)
REFERENCES cadastro.instituicao (id_instituicao) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: noticia_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.instituicoes_noticias DROP CONSTRAINT IF EXISTS noticia_fk CASCADE;
ALTER TABLE cadastro.instituicoes_noticias ADD CONSTRAINT noticia_fk FOREIGN KEY (id_noticia_noticia)
REFERENCES publico.noticia (id_noticia) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: parcela_fk | type: CONSTRAINT --
-- ALTER TABLE financeiro.boleto DROP CONSTRAINT IF EXISTS parcela_fk CASCADE;
ALTER TABLE financeiro.boleto ADD CONSTRAINT parcela_fk FOREIGN KEY (id_parcela)
REFERENCES financeiro.parcela (id_parcela) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: cadastro.matriculas_anexos | type: TABLE --
-- DROP TABLE IF EXISTS cadastro.matriculas_anexos CASCADE;
CREATE TABLE cadastro.matriculas_anexos(
	id_anexo integer,
	id_matricula_matricula integer,
	CONSTRAINT matriculas_anexos_pk PRIMARY KEY (id_anexo,id_matricula_matricula)

);
-- ddl-end --

-- object: anexo_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.matriculas_anexos DROP CONSTRAINT IF EXISTS anexo_fk CASCADE;
ALTER TABLE cadastro.matriculas_anexos ADD CONSTRAINT anexo_fk FOREIGN KEY (id_anexo)
REFERENCES cadastro.anexo (id_anexo) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: matricula_fk | type: CONSTRAINT --
-- ALTER TABLE cadastro.matriculas_anexos DROP CONSTRAINT IF EXISTS matricula_fk CASCADE;
ALTER TABLE cadastro.matriculas_anexos ADD CONSTRAINT matricula_fk FOREIGN KEY (id_matricula_matricula)
REFERENCES matricula.matricula (id_matricula) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --


