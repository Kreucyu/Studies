CREATE TABLE disciplina (
  cd_disciplina INTEGER  NOT NULL  ,
  nm_disciplina VARCHAR(100)  NOT NULL  ,
  ds_objetivos VARCHAR(200)  NOT NULL  ,
  ds_conteudo VARCHAR(200)  NOT NULL  ,
  nr_horas INTEGER  NOT NULL    ,
PRIMARY KEY(cd_disciplina));



CREATE TABLE estudante (
  cd_estudante INTEGER  NOT NULL  ,
  nm_estudante VARCHAR(50)  NOT NULL  ,
  ds_email VARCHAR(50)  NOT NULL  ,
  dt_nascimento DATE  NOT NULL  ,
  nr_telefone VARCHAR(15)  NOT NULL    ,
PRIMARY KEY(cd_estudante));



CREATE TABLE area (
  cd_area INTEGER  NOT NULL  ,
  nm_area VARCHAR(50)  NOT NULL    ,
PRIMARY KEY(cd_area));



CREATE TABLE professor (
  cd_professor INTEGER  NOT NULL  ,
  cd_area INTEGER  NOT NULL  ,
  nm_professor VARCHAR(50)  NOT NULL  ,
  ds_email VARCHAR(50)  NOT NULL  ,
  dt_nascimento DATE  NOT NULL    ,
PRIMARY KEY(cd_professor),
  FOREIGN KEY(cd_area)
    REFERENCES area(cd_area)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE curso (
  cd_curso INTEGER  NOT NULL  ,
  cd_professor INTEGER  NOT NULL  ,
  cd_area INTEGER  NOT NULL  ,
  nm_curso VARCHAR(100)  NOT NULL  ,
  ds_objetivos VARCHAR(200)  NOT NULL  ,
  ds_perfil VARCHAR(100)  NOT NULL    ,
PRIMARY KEY(cd_curso),
  FOREIGN KEY(cd_area)
    REFERENCES area(cd_area)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(cd_professor)
    REFERENCES professor(cd_professor)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE curso_disciplina (
  cd_curso INTEGER  NOT NULL  ,
  cd_disciplina INTEGER  NOT NULL    ,
PRIMARY KEY(cd_curso, cd_disciplina),
  FOREIGN KEY(cd_curso)
    REFERENCES curso(cd_curso)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(cd_disciplina)
    REFERENCES disciplina(cd_disciplina)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE turma (
  cd_turma INTEGER  NOT NULL  ,
  cd_disciplina INTEGER  NOT NULL  ,
  cd_curso INTEGER  NOT NULL  ,
  cd_professor INTEGER  NOT NULL  ,
  fl_turno_oferta CHAR(1)  NOT NULL  ,
  dt_inicio DATE  NOT NULL  ,
  dt_termino DATE  NOT NULL    ,
PRIMARY KEY(cd_turma),
  FOREIGN KEY(cd_professor)
    REFERENCES professor(cd_professor)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(cd_curso, cd_disciplina)
    REFERENCES curso_disciplina(cd_curso, cd_disciplina)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE curso_estudante (
  cd_curso INTEGER  NOT NULL  ,
  cd_estudante INTEGER  NOT NULL    ,
PRIMARY KEY(cd_curso, cd_estudante),
  FOREIGN KEY(cd_curso)
    REFERENCES curso(cd_curso)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(cd_estudante)
    REFERENCES estudante(cd_estudante)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE historico_estudante (
  cd_estudante INTEGER  NOT NULL  ,
  cd_turma INTEGER  NOT NULL  ,
  fl_frequencia CHAR(1)  NULL  ,
  vl_media DECIMAL(3,1)  NULL    ,
PRIMARY KEY(cd_estudante, cd_turma),
  FOREIGN KEY(cd_estudante)
    REFERENCES estudante(cd_estudante)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(cd_turma)
    REFERENCES turma(cd_turma)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);




