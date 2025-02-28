CREATE TABLE pessoa	
(cd_pessoa INTEGER,
nm_pessoa VARCHAR(50) NOT NULL,
ds_email VARCHAR(50) UNIQUE,
dt_nascimento DATE,
PRIMARY KEY (cd_pessoa)
);

INSERT INTO pessoa
	VALUES (1, 'pessoa 1', 'pessoa1@gmail.com', '2000-01-31')
    
INSERT INTO pessoa (cd_pessoa, nm_pessoa, ds_email)
	VALUES (2, 'pessoa 2', 'pessoa2@gmail.com')    
    
INSERT INTO pessoa
	VALUES (4, 'pessoa 3', 'pessoa3@gmail.com', '2000-01-31')

ALTER TABLE pessoa	
MODIFY COLUMN cd_pessoa INTEGER AUTO_INCREMENT; 
    DESC pessoa;
    
ALTER TABLE pessoa 
ADD COLUMN fl_ativo CHAR(1) DEFAULT 'S'

ALTER TABLE pessoa
ADD CONSTRAINT pessoa_fl_ativo_ck
		CHECK (fl_ativo IN ('S', 'N'));
        
ALTER TABLE pessoa 
ADD CONSTRAINT pessoa_fl_ativo_ck
		UNIQUE KEY(nm_pessoa);
        
	SELECT * FROM pessoa;

-- alteração exclusivamente para a variavel onde esta setada o WHERE 
-- (de prefenrencia a variavel de PK), se envolver mais pessoas pode ser usado (IN ou OR)
UPDATE pessoa
SET nm_pessoa = 'pessoa 6'
WHERE cd_pessoa = 6;


SELECT * FROM pessoa;
