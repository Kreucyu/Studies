-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-10-15 12:55:08.201

-- tables
-- Table: agendamento
CREATE TABLE agendamento (
    cd_agendamento int  NOT NULL,
    dt_agendamento date  NOT NULL,
    hr_agendamento time  NOT NULL,
    cd_prestador int  NOT NULL,
    CONSTRAINT agendamento_pk PRIMARY KEY (cd_agendamento)
);

-- Table: agendamento_servico
CREATE TABLE agendamento_servico (
    cd_agendamento int  NOT NULL,
    cd_servico int  NOT NULL,
    qt_servico int  NOT NULL,
    vl_servico decimal(6,2)  NOT NULL,
    CONSTRAINT agendamento_servico_pk PRIMARY KEY (cd_agendamento,cd_servico)
);

-- Table: animal
CREATE TABLE animal (
    cd_animal integer  NOT NULL,
    ds_animal varchar(50)  NOT NULL,
    mn_animal varchar(50)  NOT NULL,
    dt_nascimento date  NOT NULL,
    tp_porte char(1)  NOT NULL,
    cd_raca integer  NOT NULL,
    cd_cliente integer  NOT NULL,
    CONSTRAINT animal_pk PRIMARY KEY (cd_animal)
);

-- Table: categoria
CREATE TABLE categoria (
    cd_categoria integer  NOT NULL,
    ds_categoria varchar(50)  NOT NULL,
    CONSTRAINT categoria_pk PRIMARY KEY (cd_categoria)
);

-- Table: cliente
CREATE TABLE cliente (
    cd_cliente integer  NOT NULL,
    mn_cliente varchar(50)  NOT NULL,
    nr_telefone varchar(15)  NOT NULL,
    ds_email varchar(50)  NOT NULL,
    dt_nascimento date  NOT NULL,
    cd_endereco int  NOT NULL,
    CONSTRAINT cliente_pk PRIMARY KEY (cd_cliente)
);

-- Table: endereco
CREATE TABLE endereco (
    cd_endereco int  NOT NULL,
    ds_logadouro varchar(50)  NOT NULL,
    ds_complemento varchar(50)  NOT NULL,
    nm_bairro varchar(30)  NOT NULL,
    nr_cep char(8)  NOT NULL,
    cd_municipio int  NOT NULL,
    CONSTRAINT endereco_pk PRIMARY KEY (cd_endereco)
);

-- Table: especie
CREATE TABLE especie (
    cd_especie integer  NOT NULL,
    mn_especie varchar(30)  NOT NULL,
    CONSTRAINT especie_pk PRIMARY KEY (cd_especie)
);

-- Table: item_nota_fiscal
CREATE TABLE item_nota_fiscal (
    nr_nota_fiscal integer  NOT NULL,
    cd_produto integer  NOT NULL,
    qt_produto integer  NOT NULL,
    vl_produto decimal(8,2)  NOT NULL,
    CONSTRAINT item_nota_fiscal_pk PRIMARY KEY (nr_nota_fiscal,cd_produto)
);

-- Table: municipio
CREATE TABLE municipio (
    cd_municipio int  NOT NULL,
    nm_municipio varchar(50)  NOT NULL,
    sg_uf char(2)  NOT NULL,
    CONSTRAINT municipio_pk PRIMARY KEY (cd_municipio)
);

-- Table: nota_fiscal
CREATE TABLE nota_fiscal (
    nr_nota_fiscal integer  NOT NULL,
    dt_emissao date  NOT NULL,
    vl_total decimal(8,2)  NOT NULL,
    cd_cliente integer  NOT NULL,
    CONSTRAINT nota_fiscal_pk PRIMARY KEY (nr_nota_fiscal)
);

-- Table: prestador_servico
CREATE TABLE prestador_servico (
    cd_prestador int  NOT NULL,
    nm_prestador varchar(50)  NOT NULL,
    nr_telefone varchar(15)  NOT NULL,
    ds_email varchar(50)  NOT NULL,
    cd_endereco int  NOT NULL,
    CONSTRAINT prestador_servico_pk PRIMARY KEY (cd_prestador)
);

-- Table: produto
CREATE TABLE produto (
    cd_produto integer  NOT NULL,
    mn_produto varchar(50)  NOT NULL,
    ds_produto varchar(50)  NOT NULL,
    vl_custo decimal(8,2)  NOT NULL,
    vl_venda decimal(8,2)  NOT NULL,
    qt_estoque integer  NOT NULL,
    cd_categoria integer  NOT NULL,
    CONSTRAINT produto_pk PRIMARY KEY (cd_produto)
);

-- Table: raca
CREATE TABLE raca (
    cd_raca integer  NOT NULL,
    mn_raca varchar(50)  NOT NULL,
    cd_especie integer  NOT NULL,
    CONSTRAINT raca_pk PRIMARY KEY (cd_raca)
);

-- Table: servico
CREATE TABLE servico (
    cd_servico int  NOT NULL,
    ds_servico varchar(50)  NOT NULL,
    vl_servico decimal(6,2)  NOT NULL,
    CONSTRAINT servico_pk PRIMARY KEY (cd_servico)
);

-- Table: servico_prestador
CREATE TABLE servico_prestador (
    cd_prestador int  NOT NULL,
    cd_servico int  NOT NULL,
    CONSTRAINT servico_prestador_pk PRIMARY KEY (cd_prestador,cd_servico)
);

-- foreign keys
-- Reference: FK_4 (table: item_nota_fiscal)
ALTER TABLE item_nota_fiscal ADD CONSTRAINT FK_4 FOREIGN KEY FK_4 (nr_nota_fiscal)
    REFERENCES nota_fiscal (nr_nota_fiscal);

-- Reference: FK_5 (table: item_nota_fiscal)
ALTER TABLE item_nota_fiscal ADD CONSTRAINT FK_5 FOREIGN KEY FK_5 (cd_produto)
    REFERENCES produto (cd_produto);

-- Reference: Table_15_prestador_servico (table: servico_prestador)
ALTER TABLE servico_prestador ADD CONSTRAINT Table_15_prestador_servico FOREIGN KEY Table_15_prestador_servico (cd_prestador)
    REFERENCES prestador_servico (cd_prestador);

-- Reference: Table_15_servico (table: servico_prestador)
ALTER TABLE servico_prestador ADD CONSTRAINT Table_15_servico FOREIGN KEY Table_15_servico (cd_servico)
    REFERENCES servico (cd_servico);

-- Reference: agendamento_prestador_servico (table: agendamento)
ALTER TABLE agendamento ADD CONSTRAINT agendamento_prestador_servico FOREIGN KEY agendamento_prestador_servico (cd_prestador)
    REFERENCES prestador_servico (cd_prestador);

-- Reference: agendamento_servico_agendamento (table: agendamento_servico)
ALTER TABLE agendamento_servico ADD CONSTRAINT agendamento_servico_agendamento FOREIGN KEY agendamento_servico_agendamento (cd_agendamento)
    REFERENCES agendamento (cd_agendamento);

-- Reference: agendamento_servico_servico (table: agendamento_servico)
ALTER TABLE agendamento_servico ADD CONSTRAINT agendamento_servico_servico FOREIGN KEY agendamento_servico_servico (cd_servico)
    REFERENCES servico (cd_servico);

-- Reference: animal_cliente (table: animal)
ALTER TABLE animal ADD CONSTRAINT animal_cliente FOREIGN KEY animal_cliente (cd_cliente)
    REFERENCES cliente (cd_cliente);

-- Reference: animal_raca (table: animal)
ALTER TABLE animal ADD CONSTRAINT animal_raca FOREIGN KEY animal_raca (cd_raca)
    REFERENCES raca (cd_raca);

-- Reference: cliente_endereco (table: cliente)
ALTER TABLE cliente ADD CONSTRAINT cliente_endereco FOREIGN KEY cliente_endereco (cd_endereco)
    REFERENCES endereco (cd_endereco);

-- Reference: endereco_municipio (table: endereco)
ALTER TABLE endereco ADD CONSTRAINT endereco_municipio FOREIGN KEY endereco_municipio (cd_municipio)
    REFERENCES municipio (cd_municipio);

-- Reference: nota_fiscal_cliente (table: nota_fiscal)
ALTER TABLE nota_fiscal ADD CONSTRAINT nota_fiscal_cliente FOREIGN KEY nota_fiscal_cliente (cd_cliente)
    REFERENCES cliente (cd_cliente);

-- Reference: prestador_servico_endereco (table: prestador_servico)
ALTER TABLE prestador_servico ADD CONSTRAINT prestador_servico_endereco FOREIGN KEY prestador_servico_endereco (cd_endereco)
    REFERENCES endereco (cd_endereco);

-- Reference: produto_categoria (table: produto)
ALTER TABLE produto ADD CONSTRAINT produto_categoria FOREIGN KEY produto_categoria (cd_categoria)
    REFERENCES categoria (cd_categoria);

-- Reference: raca_especie (table: raca)
ALTER TABLE raca ADD CONSTRAINT raca_especie FOREIGN KEY raca_especie (cd_especie)
    REFERENCES especie (cd_especie);

-- End of file.

