CREATE TABLE disciplinas (
  id_disciplina INTEGER  NOT NULL  ,
  nm_disciplina VARCHAR(50)  NULL  ,
  ds_objetivos VARCHAR(500)  NULL  ,
  ds_conteudos VARCHAR(500)  NULL  ,
  vl_horas_total INTEGER  NULL    ,
PRIMARY KEY(id_disciplina));



CREATE TABLE estudantes (
  id_estudante INTEGER  NOT NULL  ,
  nm_estudante VARCHAR(50)  NULL  ,
  dt_nascimento DATE  NULL  ,
  ds_email VARCHAR(50)  NULL    ,
PRIMARY KEY(id_estudante));



CREATE TABLE historico_estudante (
  vl_frequencia DECIMAL(4,1)  NOT NULL  ,
  vl_notas DECIMAL(4,1)  NULL  );



CREATE TABLE turmas (
  cd_turma INTEGER  NOT NULL  ,
  fl_turno CHAR(1)  NULL  ,
  dt_inicio DATE  NULL  ,
  dt_termino DATE  NULL    ,
PRIMARY KEY(cd_turma));



CREATE TABLE areas (
  id_area INTEGER  NOT NULL  ,
  nm_area INTEGER  NULL    ,
PRIMARY KEY(id_area));



CREATE TABLE professores (
  id_professor INTEGER  NOT NULL  ,
  id_area INTEGER  NOT NULL  ,
  nm_professor VARCHAR(50)  NULL  ,
  ds_email VARCHAR(50)  NULL  ,
  dt_nascimento DATE  NULL    ,
PRIMARY KEY(id_professor)  ,
INDEX professores_FKIndex1(id_area),
  FOREIGN KEY(id_area)
    REFERENCES areas(id_area)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE curso (
  id_curso INTEGER  NOT NULL  ,
  id_area INTEGER  NOT NULL  ,
  nm_curso VARCHAR(50)  NULL  ,
  ds_horas_total INTEGER  NULL    ,
PRIMARY KEY(id_curso)  ,
INDEX curso_FKIndex1(id_area),
  FOREIGN KEY(id_area)
    REFERENCES areas(id_area)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE curso_disciplinas (
  id_curso INTEGER  NOT NULL  ,
  id_disciplina INTEGER  NOT NULL    ,
PRIMARY KEY(id_curso, id_disciplina)  ,
INDEX curso_has_disciplinas_FKIndex1(id_curso)  ,
INDEX curso_has_disciplinas_FKIndex2(id_disciplina),
  FOREIGN KEY(id_curso)
    REFERENCES curso(id_curso)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_disciplina)
    REFERENCES disciplinas(id_disciplina)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE curso_estudantes (
  id_curso INTEGER  NOT NULL  ,
  id_estudante INTEGER  NOT NULL    ,
PRIMARY KEY(id_curso, id_estudante)  ,
INDEX curso_has_estudantes_FKIndex1(id_curso)  ,
INDEX curso_has_estudantes_FKIndex2(id_estudante),
  FOREIGN KEY(id_curso)
    REFERENCES curso(id_curso)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION,
  FOREIGN KEY(id_estudante)
    REFERENCES estudantes(id_estudante)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);



CREATE TABLE coordenador (
  id_coordenador INTEGER  NOT NULL  ,
  id_professor INTEGER  NOT NULL    ,
PRIMARY KEY(id_coordenador)  ,
INDEX coordenador_FKIndex1(id_professor),
  FOREIGN KEY(id_professor)
    REFERENCES professores(id_professor)
      ON DELETE NO ACTION
      ON UPDATE NO ACTION);




