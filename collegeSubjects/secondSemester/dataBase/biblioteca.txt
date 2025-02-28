CREATE TABLE obra (
cd_obra INT NOT NULL,
nm_titulo VARCHAR(100) NOT NULL,
ds_edicao VARCHAR(20) NOT NULL,
dt_publicacao DATE NOT NULL,
ds_sinopse VARCHAR(255) NOT NULL,
CONSTRAINT obra_pk PRIMARY KEY (cd_obra)
);

CREATE TABLE situacao (
cd_situacao INT NOT NULL,
ds_situacao VARCHAR(50) NOT NULL,
CONSTRAINT situacao_pk PRIMARY KEY (cd_situacao)
);

CREATE TABLE exemplar (
nr_exemplar INT NOT NULL,
dt_inclusao DATE NOT NULL,
cd_obra INT NOT NULL,
cd_situacao INT NOT NULL,
CONSTRAINT exemplar_pk PRIMARY KEY (nr_exemplar)
);

CREATE TABLE emprestimo_exemplar (
nr_emprestimo INT NOT NULL,
nr_exemplar INT NOT NULL,
dt_prevista_devolucao DATE NOT NULL,
dt_devolucao DATE NULL,
CONSTRAINT emprestimo_exemplar PRIMARY KEY (nr_emprestimo,nr_exemplar)
);

CREATE TABLE emprestimo (
nr_emprestimo INT NOT NULL,
cd_usuario INT NOT NULL,
dt_emprestimo DATE NOT NULL,
CONSTRAINT emprestimo PRIMARY KEY (nr_emprestimo)
);

CREATE TABLE usuario (
cd_usuario INT NOT NULL,
nm_usuario VARCHAR(50) NOT NULL,
dt_nascimento DATE NOT NULL,
ds_email VARCHAR(100) NOT NULL,
ds_senha VARCHAR(100) NOT NULL,
tp_categoria INT NOT NULL,
CONSTRAINT usuario PRIMARY KEY (cd_usuario)
);

CREATE TABLE categoria (
tp_categoria INT NOT NULL,
ds_categoria VARCHAR(30) NOT NULL,
nr_exemplares INT NOT NULL,
CONSTRAINT categoria PRIMARY KEY (tp_categoria),
CONSTRAINT unicidade_categoria UNIQUE (ds_categoria)
);

ALTER TABLE exemplar ADD CONSTRAINT exemplar_obra_fk FOREIGN KEY exemplar_obra (cd_obra)
REFERENCES obra (cd_obra);

ALTER TABLE exemplar ADD CONSTRAINT exemplar_situacao_fk FOREIGN KEY exemplar_situacao (cd_situacao)
REFERENCES situacao (cd_situacao);

ALTER TABLE emprestimo_exemplar ADD CONSTRAINT exemplar_fk FOREIGN KEY exemplar (nr_exemplar)
REFERENCES exemplar (nr_exemplar);

ALTER TABLE emprestimo_exemplar ADD CONSTRAINT emprestimo_fk FOREIGN KEY emprestimo (nr_emprestimo)
REFERENCES emprestimo (nr_emprestimo);

ALTER TABLE emprestimo ADD CONSTRAINT usuario_fk FOREIGN KEY usuario (cd_usuario)
REFERENCES usuario (cd_usuario);

ALTER TABLE usuario ADD CONSTRAINT categoria_fk FOREIGN KEY categoria (tp_categoria)
REFERENCES categoria (tp_categoria);

-- Inserindo os dados

INSERT INTO situacao (cd_situacao, ds_situacao) VALUES
(1, 'Emprestado'),
(2, 'Disponível'),
(3, 'Não localizado');

INSERT INTO obra (cd_obra, nm_titulo, ds_edicao, dt_publicacao, ds_sinopse) VALUES
(1, 'Outsider', '1ª edição', '2014-07-15', 'Um crime indescritível. Uma investigação inexplicável. Uma das histórias mais perturbadoras de Stephen King dos últimos tempos.'),
(2, 'O Iluminado', '3ª edição', '2002-04-11', 'Um escritor, Jack Torrance, aceita cuidar de um hotel isolado durante o inverno. Enquanto nevascas os isolam, forças sobrenaturais o levam à loucura, ameaçando sua família e o dom psíquico do filho, Danny.'),
(3, 'Carrie a Estranha', '2ª edição', '1995-02-28', 'Carrie, uma adolescente tímida e vítima de bullying, descobre poderes telecinéticos. Após uma humilhação cruel no baile da escola, ela libera sua fúria em um ato de vingança devastador e trágico.'),
(4, 'It a Coisa', '5ª edição', '2018-03-06', 'Em Derry, um grupo de amigos enfrenta um ser maligno que assume formas de seus maiores medos, especialmente o palhaço Pennywise. Anos depois, eles voltam para cumprir a promessa de derrotá-lo.'),
(5, 'O Cemitério', '2ª edição', '2013-09-17', 'Uma família se muda para uma casa perto de um cemitério indígena com poderes sombrios. Quando a tragédia os atinge, o desejo de trazer os mortos de volta revela um horror inimaginável.');

INSERT INTO categoria (tp_categoria, ds_categoria, nr_exemplares) VALUES
(1, 'estudante', 7),
(2, 'idoso(a)', 5),
(3, 'comunidade geral', 3);

INSERT INTO usuario (cd_usuario, nm_usuario, dt_nascimento, ds_email, ds_senha, tp_categoria) VALUES
(1, 'Jonathan Santos', '2004-09-10', 'JonathanSantos@gmail.com', 'Jonathan123@', 1),
(2, 'Terezinha Kormann', '1943-09-11', 'TerezinhaDK@outlook.com', 'Terezinha123@', 2),
(3, 'Rosita Pereira', '1970-05-12', 'RositaBP@yahoo.com', 'Rosita123@', 3);

INSERT INTO exemplar (nr_exemplar, cd_obra, cd_situacao, dt_inclusao) VALUES
(1, 1, 1, '2015-03-12'),
(2, 2, 2, '2018-03-17'),
(3, 5, 1, '2023-05-12'),
(4, 3, 3, '2010-11-11'),
(5, 4, 2, '2020-05-20'),
(6, 1, 1, '2022-09-12'),
(7, 2, 3, '2024-03-25'),
(8, 4, 2, '2024-09-17'),
(9, 5, 1, '2019-05-25'),
(10, 3, 1, '2014-05-26');

INSERT INTO emprestimo (nr_emprestimo, cd_usuario, dt_emprestimo) VALUES
(1, 1, '2024-11-01'),
(2, 2, '2024-11-02'),
(3, 3, '2024-11-03'),
(4, 1, '2024-11-04'),
(5, 2, '2024-11-05'),
(6, 1, '2024-11-06'),
(7, 1, '2024-11-07'),
(8, 3, '2024-11-08'),
(9, 2, '2024-11-09'),
(10, 1, '2024-11-10');


INSERT INTO emprestimo_exemplar (nr_emprestimo, nr_exemplar, dt_prevista_devolucao, dt_devolucao) VALUES
(1, 1, '2024-11-11', '2024-11-10'),
(2, 2, '2024-11-12', NULL),
(3, 3, '2024-11-13', '2024-11-12'),
(4, 4, '2024-11-14', '2024-11-10'),
(5, 5, '2024-11-15', '2024-11-14'),
(6, 6, '2024-11-16', NULL),
(7, 7, '2024-11-17', '2024-11-16'),
(8, 8, '2024-11-18', '2024-11-17'),
(9, 9, '2024-11-19', '2024-11-15'),
(10, 10, '2024-11-20', NULL);

-- Listando dados

--emprestimos

SELECT
	e.nr_emprestimo,
	u.nm_usuario,
	o.nm_titulo
FROM
	emprestimo e 
JOIN
	emprestimo_exemplar ee ON e.nr_emprestimo = ee.nr_emprestimo
JOIN
	exemplar ex ON ee.nr_exemplar = ex.nr_exemplar
JOIN 
	obra o ON ex.cd_obra = o.cd_obra
JOIN 
	usuario u ON e.cd_usuario = u.cd_usuario;

--devolução nula

SELECT 
	u.nm_usuario,
	o.nm_titulo
FROM 
	emprestimo_exemplar ee
JOIN 
	exemplar ex ON ee.nr_exemplar = ex.nr_exemplar
JOIN 
	obra o ON ex.cd_obra = o.cd_obra
JOIN 
	emprestimo e ON ee.nr_emprestimo = e.nr_emprestimo
JOIN 
	usuario u ON e.cd_usuario = u.cd_usuario
WHERE
	ee.dt_devolucao IS NULL;
	
--numero de exemplares

SELECT
	u.nm_usuario,
	c.nr_exemplares AS qtd_maxima
FROM
	usuario u 
JOIN 
	categoria c ON u.tp_categoria = c.tp_categoria;
	
--listando obras

SELECT
	o.nm_titulo,
	ex.nr_exemplar
FROM 
	exemplar ex
JOIN 
	obra o ON ex.cd_obra = o.cd_obra
WHERE
	ex.cd_situacao = 2;
