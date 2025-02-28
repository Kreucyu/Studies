-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-09-04 22:40:28.087

-- tables
-- Table: categoria
CREATE TABLE categoria (
    cd_categoria int  NOT NULL,
    CONSTRAINT categoria_pk PRIMARY KEY (cd_categoria)
);

-- Table: cliente
CREATE TABLE cliente (
    cd_cliente int  NOT NULL,
    nm_cliente varchar(50)  NOT NULL,
    dt_nascimento date  NOT NULL,
    n_telefone varchar(50)  NOT NULL,
    id_cliente int  NOT NULL,
    nm_email varchar(50)  NOT NULL,
    CONSTRAINT cliente_pk PRIMARY KEY (cd_cliente)
);

-- Table: endereco
CREATE TABLE endereco (
    cd_endereco int  NOT NULL,
    nm_rua varchar(50)  NOT NULL,
    nr_casa int  NOT NULL,
    nm_bairro varchar(50)  NOT NULL,
    nm_cidade varchar(50)  NOT NULL,
    cd_cep varchar(50)  NOT NULL,
    nm_uf varchar(100)  NOT NULL,
    cliente_cd_cliente int  NOT NULL,
    CONSTRAINT endereco_pk PRIMARY KEY (cd_endereco)
);

-- Table: item_pedido
CREATE TABLE item_pedido (
    pedido_cd_pedido int  NOT NULL,
    vl_produto decimal(8,2)  NOT NULL,
    qt_produto int  NOT NULL,
    produto_cd_prod int  NOT NULL,
    CONSTRAINT item_pedido_pk PRIMARY KEY (pedido_cd_pedido)
);

-- Table: pedido
CREATE TABLE pedido (
    cd_pedido int  NOT NULL,
    dt_pedido datetime  NOT NULL,
    cliente_cd_cliente int  NOT NULL,
    CONSTRAINT pedido_pk PRIMARY KEY (cd_pedido)
);

-- Table: produto
CREATE TABLE produto (
    nm_prod varchar(50)  NOT NULL,
    nm_cat_prod varchar(50)  NOT NULL,
    qt_estoque decimal(8,2)  NOT NULL,
    pr_prod decimal(8,2)  NOT NULL,
    categoria_cd_categoria int  NOT NULL,
    cd_prod int  NOT NULL,
    CONSTRAINT produto_pk PRIMARY KEY (cd_prod)
);

-- foreign keys
-- Reference: endereco_cliente (table: endereco)
ALTER TABLE endereco ADD CONSTRAINT endereco_cliente FOREIGN KEY endereco_cliente (cliente_cd_cliente)
    REFERENCES cliente (cd_cliente);

-- Reference: item_pedido_pedido (table: item_pedido)
ALTER TABLE item_pedido ADD CONSTRAINT item_pedido_pedido FOREIGN KEY item_pedido_pedido (pedido_cd_pedido)
    REFERENCES pedido (cd_pedido);

-- Reference: item_pedido_produto (table: item_pedido)
ALTER TABLE item_pedido ADD CONSTRAINT item_pedido_produto FOREIGN KEY item_pedido_produto (produto_cd_prod)
    REFERENCES produto (cd_prod);

-- Reference: pedido_cliente (table: pedido)
ALTER TABLE pedido ADD CONSTRAINT pedido_cliente FOREIGN KEY pedido_cliente (cliente_cd_cliente)
    REFERENCES cliente (cd_cliente);

-- Reference: produto_categoria (table: produto)
ALTER TABLE produto ADD CONSTRAINT produto_categoria FOREIGN KEY produto_categoria (categoria_cd_categoria)
    REFERENCES categoria (cd_categoria);

-- End of file.

