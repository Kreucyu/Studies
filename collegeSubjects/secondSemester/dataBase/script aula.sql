CREATE TABLE Table_03 (
  cd_software INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  nm_software VARCHAR(50)  NULL    ,
PRIMARY KEY(cd_software));



CREATE TABLE Table_01 (
  id_num INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  nm_sala VARCHAR(20)  NULL  ,
  qt_metros DECIMAL(4,1)  NULL    ,
PRIMARY KEY(id_num));



CREATE TABLE Table_02 (
  id_equipamento INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  Table_01_id_num INTEGER UNSIGNED  NOT NULL  ,
  ds_configuracao VARCHAR(50)  NULL  ,
  ds_aquisicao DATE  NULL    ,
PRIMARY KEY(id_equipamento)  ,
INDEX Table_02_FKIndex1(Table_01_id_num),
  FOREIGN KEY(Table_01_id_num)
    REFERENCES Table_01(id_num)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE Table_04 (
  id_equipamento INTEGER UNSIGNED  NOT NULL   AUTO_INCREMENT,
  cd_software INTEGER UNSIGNED  NOT NULL  ,
  dt_instalacao DATE  NULL    ,
PRIMARY KEY(id_equipamento)  ,
INDEX Table_04_FKIndex1(cd_software)  ,
INDEX Table_04_FKIndex2(id_equipamento),
  FOREIGN KEY(cd_software)
    REFERENCES Table_03(cd_software)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_equipamento)
    REFERENCES Table_02(id_equipamento)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);




