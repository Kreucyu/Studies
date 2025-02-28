INSERT INTO municipio(cd_municipio, nm_municipio, sg_uf)
VALUES(1, 'guabiruba', 'sc'),
	  (2, 'brusque', 'sc'),
      (3, 'blumenau', 'sc'),
      (4, 'joinvile', 'sc'),
      (5, 'penha', 'sc');
      
INSERT INTO endereco(cd_endereco, ds_logadouro, ds_complemento, nm_bairro, nr_cep, cd_municipio)
VALUES(1, 'rua 10 de junho', 'n-1273', 'centro', '88360000', 1),
	  (2, 'rua carlos kohler', 'n-950', 'aymore', '88360000', 1),
      (3, 'rua imigrantes', 'n-650', 'imigrantes', '88360000', 1),
      (4, 'rua brusque', 'n-1550', 'centro', '88360000', 1),
      (5, 'rua antonio schaefer', 'n-2200', 'centro', '88350000', 2);
      
INSERT INTO prestador_servico(cd_prestador, nm_prestador, nr_telefone, ds_email, cd_endereco)
VALUES(1, 'jefferson santos', '4786502340', 'jefSantos@gmail.com', 3),
	  (2, 'lucia silva', '4754650934', 'ls2005@gmail.com', 4),
      (3, 'lucas ferreira', '47997332908', 'lucasFer@outlook.com', 1),
      (4, 'marcia santos', '4933540594', 'marciaSan@hotmail.com', 2),
      (5, 'carla costa', '4785669343', 'carlaC@yahoo.com', 5);
      
INSERT INTO cliente(cd_cliente, mn_cliente, nr_telefone, ds_email, dt_nascimento, cd_endereco)
VALUES(1, 'camily pereira', '47999911111', 'camilyp@gmail.com', '2006-05-26', 1),
	  (2, 'jonathan felipe', '47991234305', 'jonathanF@hotmail.com', '2004-05-05', 2),
      (3, 'felipe silva', '4792223434', 'felipeS@outlook.com', '2002-03-12', 3),
      (4, 'maria costa', '4798752000', 'mariaC@gmail.com', '1995-12-23', 4),
      (5, 'lucas fagundes', '4755442309', 'lucasf@yahoo.com', '1998-11-24', 5);
      
INSERT INTO especie(cd_especie, mn_especie)
VALUES(1, 'cachorro'),
	  (2, 'gato'),
      (3, 'papagaio'),
      (4, 'macaco'),
      (5, 'tartaruga');
      
INSERT INTO raca(cd_raca, mn_raca, cd_especie)
VALUES(1, 'golden retriever', 1),
	  (2, 'siamês', 2),
      (3, 'bordercolie', 1),
      (4, 'papagaio-verdadeiro', 3),
      (5, 'salsicha', 1);
      
INSERT INTO animal (cd_animal, ds_animal, mn_animal, dt_nascimento, tp_porte, cd_raca, cd_cliente)
VALUES(1, 'cachorro, 2 anos de idade', 'paçoca', '2022-10-12', 'M', 1, 1),
	  (2, 'gato, 4 anos de idade', 'georgia', '2020-09-13', 'P', 2, 2),
      (3, 'cachorro, 5 anos de idade', 'dora', '2019-03-12', 'M', 1, 3),
      (4, 'gato, 2 anos de idade', 'lince', '2022-04-23', 'P', 4, 2),
      (5, 'papagaio, 1 ano de idade', 'robson', '2023-12-12', 'P', 3, 5);

INSERT INTO agendamento (cd_agendamento, dt_agendamento, hr_agendamento, cd_prestador)
VALUES (1, '2023-10-17', '09:30:00', 1),
	   (2, '2023-10-12', '08:30:00', 1),
       (3, '2022-11-11', '10:30:00', 2),
       (4, '2024-09-17', '12:00:00', 2),
       (5, '2023-10-13', '11:30:00', 1);
       
INSERT INTO servico(cd_servico, ds_servico, vl_servico)
VALUES(1, 'banho e tosa', 350.00),
      (2, 'corte de unhas', 120.00),
      (3, 'vacinação', 150.00),
      (4, 'castração', 750.00),
      (5, 'tosa', 130.00);
      
INSERT INTO servico_prestador(cd_prestador, cd_servico)
VALUES(1, 1),
	  (2, 2),
      (3, 3),
      (4, 4),
      (5, 5);
    
INSERT INTO agendamento_servico (cd_agendamento, cd_servico, qt_servico, vl_servico)
VALUES(1, 1, 3, 1200.00),
      (2, 2, 1, 300.00),
      (3, 3, 2, 450.00),
      (4, 4, 4, 1500.00),
      (5, 5, 2, 500.00);
            
INSERT INTO nota_fiscal(nr_nota_fiscal, dt_emissao, vl_total, cd_cliente)
VALUES(1, '2024-05-23', 1200.00, 1),
	  (2, '2024-03-12', 1500.00, 2),
      (3, '2024-12-11', 850.00, 3),
      (4, '2024-09-17', 950.00, 4),
      (5, '2024-06-12', 450.00, 5);
      
INSERT INTO categoria (cd_categoria, ds_categoria)
VALUES(1, 'banho'),
	  (2, 'tosa'),
      (3, 'vacinação'),
      (4, 'ração'),
      (5, 'brinquedos');
      
INSERT INTO produto(cd_produto, mn_produto, ds_produto, vl_custo, vl_venda, qt_estoque, cd_categoria)
VALUES(1, 'shampoo', 'adequado para cães', 12.00, 25.00, 125, 1),
	  (2, 'tesoura', 'adequado para tosagem', 15.00, 35.00, 55, 2),
      (3, 'vacina anti-raiva', 'uso exclusivamente canino', 10.00, 22.00, 35, 3),
      (4, 'ração Whiskas', 'indicado para gatos', 5.00, 15.00, 300, 4),
	  (5, 'brinquedo de morder', 'indicado para cães com no mínimo 2 anos', 3.00, 10.00, 170, 5);
      
INSERT INTO item_nota_fiscal(nr_nota_fiscal, cd_produto, qt_produto, vl_produto)
VALUES(1, 1, 5, 1500.00),
	  (2, 2, 6, 1300.00),
      (3, 3, 2, 850.00),
      (4, 4, 3, 990.00),
      (5, 5, 5, 1450.00);	