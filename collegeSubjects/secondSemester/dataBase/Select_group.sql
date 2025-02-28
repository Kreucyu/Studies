-- 1 - Contar pequeno porte

SELECT COUNT(*) as animais
FROM animal
WHERE tp_porte = 'P';

-- 2 - Valor total por mês

SELECT MONTH(dt_emissao) as MES, YEAR(dt_emissao) as ANO, SUM(vl_total) as TOTAL
FROM nota_fiscal
GROUP BY YEAR(dt_emissao), MONTH(dt_emissao);

-- 3 - Listar agendamentos por prestador

SELECT cd_prestador as prestador, COUNT(*) as agendamentos
FROM agendamento
GROUP BY cd_prestador;

-- 3 - forma mais completa com nomes:

SELECT ps.cd_prestador as codigo, ps.nm_prestador as prestador, COUNT(a.nr_agendamento) as agendamentos
FROM agendamento a
JOIN prestador_servico ps ON a.cd_prestador = ps.cd_prestador
GROUP BY ps.cd_prestador, ps.nm_prestador;

-- 4 - Somar valor total de cada agendamento

SELECT nr_agendamento as numero_agendamento, SUM(vl_servico) as valor_servico
FROM agendamento_servico
GROUP BY nr_agendamento;

-- 5 - Calcular média de idade (não tenho certeza se esta certa a média)

SELECT cd_raca as raca, AVG(TIMESTAMPDIFF(YEAR, dt_nascimento, CURDATE())) as idade_media
FROM animal
GROUP BY cd_raca;

-- 6 - Listar produtos por categoria

SELECT cd_categoria as categoria, COUNT(*) as quantidade
FROM produto
GROUP BY cd_categoria;

-- 6 - com nomes das categorias:

SELECT c.cd_categoria as categoria, c.ds_categoria as nome, COUNT(p.cd_categoria) as quantidade
FROM produto p 
JOIN categoria c ON p.cd_categoria = c.cd_categoria
GROUP BY c.cd_categoria, c.ds_categoria;

-- 7 - Listar cidades com clientes

SELECT cd_endereco as endereco, COUNT(*) as total_de_clientes
FROM cliente
GROUP BY cd_endereco
ORDER BY total_de_clientes DESC;

-- 8 - Calcular o valor total por cliente

SELECT cd_cliente as cliente, SUM(vl_total) as total
FROM nota_fiscal
GROUP BY cd_cliente;

-- 8 - Com nomes dos clientes:

SELECT c.cd_cliente as cliente, c.nm_cliente as nome, SUM(nf.vl_total) as total
FROM nota_fiscal nf
JOIN cliente c ON nf.cd_cliente = c.cd_cliente
GROUP BY c.cd_cliente, c.nm_cliente;

-- 9 - Nome prestador e agendamentos

SELECT ps.cd_prestador as codigo, ps.nm_prestador as prestador, COUNT(a.nr_agendamento) as total
FROM prestador_servico ps
LEFT JOIN agendamento a ON ps.cd_prestador = a.cd_prestador
GROUP BY ps.cd_prestador, ps.nm_prestador 
ORDER BY total DESC;

-- 10 - Calcular a receita

SELECT ps.cd_prestador as codigo, ps.nm_prestador as prestador, SUM(asv.qt_servico * asv.vl_servico) AS receita_total
FROM prestador_servico ps
JOIN agendamento a ON ps.cd_prestador = a.cd_prestador
JOIN agendamento_servico asv ON a.nr_agendamento = asv.nr_agendamento
GROUP BY ps.cd_prestador, ps.nm_prestador
ORDER BY receita_total DESC;

-- 11 - Valor medio por categoria

SELECT c.ds_categoria as categoria, AVG(inf.vl_produto) as media
FROM item_nota_fiscal inf
JOIN produto p ON inf.cd_produto = p.cd_produto
JOIN categoria c ON p.cd_categoria = c.cd_categoria
GROUP BY c.ds_categoria
ORDER BY media DESC;

-- 12 - Listar clientes com mais de 5 agendamentos

SELECT  c.cd_cliente as codigo, c.nm_cliente as nome, COUNT(a.nr_agendamento) as quantidade
FROM agendamento a
JOIN prestador_servico ps ON a.cd_prestador = ps.cd_prestador
JOIN endereco e ON ps.cd_endereco = e.cd_endereco
JOIN cliente c ON e.cd_endereco = c.cd_endereco
GROUP BY c.cd_cliente, c.nm_cliente
HAVING quantidade > 5
ORDER BY quantidade DESC;

-- 13 - Receita media por prestador de servicos

SELECT ps.cd_prestador as codigo, ps.nm_prestador as prestador, SUM(asv.vl_servico / asv.nr_agendamento) as receita_media
FROM agendamento_servico asv 
JOIN agendamento a ON asv.nr_agendamento = a.nr_agendamento
JOIN prestador_servico ps ON a.cd_prestador = ps.cd_prestador
GROUP BY ps.cd_prestador, ps.nm_prestador
ORDER BY receita_media DESC;

-- 14 - Listar espécies com mais de 3 raças

SELECT e.cd_especie as codigo, e.nm_especie as nome, COUNT(r.cd_raca) as quantidade
FROM raca r 
JOIN especie e ON r.cd_especie = e.cd_especie
GROUP BY e.cd_especie, e.nm_especie
HAVING quantidade > 3
ORDER BY quantidade DESC;

-- 15 - Listar os clientes com mais de 1 animal cadastrado

SELECT c.cd_cliente as codigo, c.nm_cliente as cliente, COUNT(a.cd_animal) as animais
FROM animal a
JOIN cliente c ON a.cd_cliente = c.cd_cliente
GROUP BY c.cd_cliente, c.nm_cliente
HAVING animais > 1
ORDER BY animais DESC;



