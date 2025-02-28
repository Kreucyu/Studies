alunos-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2024-09-11 22:40:31.49

-- tables
-- Table: alunos
CREATE TABLE alunos (
    cd_aluno int  NOT NULL,
    nm_aluno varchar(50)  NOT NULL,
    id_aluno int  NOT NULL,
    dt_nascimento date  NOT NULL,
    d_genero varchar(15)  NOT NULL,
    n_telefone varchar(15)  NOT NULL,
    d_peso decimal(5,2)  NOT NULL,
    d_altura decimal(4,2)  NOT NULL,
    id_cartao int  NOT NULL,
    atividade_profissional_cd_atividade int  NOT NULL,
    escolaridade_cd_escolaridade int  NOT NULL,
    enderecos_cd_endereco int  NOT NULL,
    plano_cd_plano int  NOT NULL,
    CONSTRAINT alunos_pk PRIMARY KEY (cd_aluno)
);

-- Table: atividade_profissional
CREATE TABLE atividade_profissional (
    cd_atividade int  NOT NULL,
    nm_area varchar(100)  NOT NULL,
    carga_horaria time  NOT NULL,
    tempo_de_contrato date  NOT NULL,
    CONSTRAINT atividade_profissional_pk PRIMARY KEY (cd_atividade)
);

-- Table: enderecos
CREATE TABLE enderecos (
    cd_endereco int  NOT NULL,
    nm_rua varchar(50)  NOT NULL,
    nm_bairro varchar(30)  NOT NULL,
    nm_complemento varchar(100)  NOT NULL,
    cd_cep varchar(15)  NOT NULL,
    n_casa int  NOT NULL,
    nm_uf varchar(30)  NOT NULL,
    CONSTRAINT enderecos_pk PRIMARY KEY (cd_endereco)
);

-- Table: escolaridade
CREATE TABLE escolaridade (
    cd_escolaridade int  NOT NULL,
    nm_escola varchar(50)  NOT NULL,
    d_situacao_escolaridade varchar(100)  NOT NULL,
    dt_formacao date  NOT NULL,
    CONSTRAINT escolaridade_pk PRIMARY KEY (cd_escolaridade)
);

-- Table: instrutores
CREATE TABLE instrutores (
    cd_instrutor int  NOT NULL,
    nm_instrutor varchar(50)  NOT NULL,
    n_telefone varchar(15)  NOT NULL,
    id_instrutor int  NOT NULL,
    nm_email varchar(50)  NOT NULL,
    dt_nascimento date  NOT NULL,
    endereco_instrutores_cd_endereco int  NOT NULL,
    CONSTRAINT instrutores_pk PRIMARY KEY (cd_instrutor)
);

-- Table: matricula
CREATE TABLE matricula (
    turmas_cd_turma int  NOT NULL,
    alunos_cd_aluno int  NOT NULL,
    dt_matricula date  NOT NULL,
    CONSTRAINT matricula_pk PRIMARY KEY (turmas_cd_turma,alunos_cd_aluno)
);

-- Table: modaldiades_instrutores
CREATE TABLE modaldiades_instrutores (
    modalidade_cd_modalidade int  NOT NULL,
    instrutores_cd_instrutor int  NOT NULL,
    nm_instrutor varchar(50)  NOT NULL,
    CONSTRAINT modaldiades_instrutores_pk PRIMARY KEY (modalidade_cd_modalidade,instrutores_cd_instrutor)
);

-- Table: modalidade
CREATE TABLE modalidade (
    cd_modalidade int  NOT NULL,
    nm_modalidade varchar(50)  NOT NULL,
    hr_duracao time  NOT NULL,
    hr_inicio time  NOT NULL,
    qt_vagas int  NOT NULL,
    dt_dia date  NOT NULL,
    turmas_cd_turma int  NOT NULL,
    CONSTRAINT modalidade_pk PRIMARY KEY (cd_modalidade)
);

-- Table: plano
CREATE TABLE plano (
    cd_plano int  NOT NULL,
    nm_plano varchar(50)  NOT NULL,
    ds_plano varchar(200)  NOT NULL,
    vl_mensal decimal(6,2)  NOT NULL,
    vl_trimestral decimal(6,2)  NOT NULL,
    vl_semestral decimal(6,2)  NOT NULL,
    vl_anual decimal(6,2)  NOT NULL,
    qt_turmas int  NOT NULL,
    CONSTRAINT plano_pk PRIMARY KEY (cd_plano)
);

-- Table: registro
CREATE TABLE registro (
    cd_registro int  NOT NULL,
    dt_registro date  NOT NULL,
    hr_registro time  NOT NULL,
    matricula_turmas_cd_turma int  NOT NULL,
    matricula_alunos_cd_aluno int  NOT NULL,
    CONSTRAINT registro_pk PRIMARY KEY (cd_registro)
);

-- Table: turmas
CREATE TABLE turmas (
    hr_duracao time  NOT NULL,
    hr_inicio time  NOT NULL,
    qt_vagas int  NOT NULL,
    dt_dia date  NOT NULL,
    modalidade_turma int  NOT NULL,
    instrutor_turma int  NOT NULL,
    ds_turma varchar(200)  NOT NULL,
    cd_turma int  NOT NULL,
    CONSTRAINT turmas_pk PRIMARY KEY (cd_turma)
);

-- foreign keys
-- Reference: alunos_atividade_profissional (table: alunos)
ALTER TABLE alunos ADD CONSTRAINT alunos_atividade_profissional FOREIGN KEY alunos_atividade_profissional (atividade_profissional_cd_atividade)
    REFERENCES atividade_profissional (cd_atividade);

-- Reference: alunos_enderecos (table: alunos)
ALTER TABLE alunos ADD CONSTRAINT alunos_enderecos FOREIGN KEY alunos_enderecos (enderecos_cd_endereco)
    REFERENCES enderecos (cd_endereco);

-- Reference: alunos_escolaridade (table: alunos)
ALTER TABLE alunos ADD CONSTRAINT alunos_escolaridade FOREIGN KEY alunos_escolaridade (escolaridade_cd_escolaridade)
    REFERENCES escolaridade (cd_escolaridade);

-- Reference: alunos_plano (table: alunos)
ALTER TABLE alunos ADD CONSTRAINT alunos_plano FOREIGN KEY alunos_plano (plano_cd_plano)
    REFERENCES plano (cd_plano);

-- Reference: instrutores_endereco_instrutores (table: instrutores)
ALTER TABLE instrutores ADD CONSTRAINT instrutores_endereco_instrutores FOREIGN KEY instrutores_endereco_instrutores (endereco_instrutores_cd_endereco)
    REFERENCES enderecos (cd_endereco);

-- Reference: matricula_alunos (table: matricula)
ALTER TABLE matricula ADD CONSTRAINT matricula_alunos FOREIGN KEY matricula_alunos (alunos_cd_aluno)
    REFERENCES alunos (cd_aluno);

-- Reference: matricula_turmas (table: matricula)
ALTER TABLE matricula ADD CONSTRAINT matricula_turmas FOREIGN KEY matricula_turmas (turmas_cd_turma)
    REFERENCES turmas (cd_turma);

-- Reference: modaldiades_instrutores_instrutores (table: modaldiades_instrutores)
ALTER TABLE modaldiades_instrutores ADD CONSTRAINT modaldiades_instrutores_instrutores FOREIGN KEY modaldiades_instrutores_instrutores (instrutores_cd_instrutor)
    REFERENCES instrutores (cd_instrutor);

-- Reference: modaldiades_instrutores_modalidade (table: modaldiades_instrutores)
ALTER TABLE modaldiades_instrutores ADD CONSTRAINT modaldiades_instrutores_modalidade FOREIGN KEY modaldiades_instrutores_modalidade (modalidade_cd_modalidade)
    REFERENCES modalidade (cd_modalidade);

-- Reference: registro_matricula (table: registro)
ALTER TABLE registro ADD CONSTRAINT registro_matricula FOREIGN KEY registro_matricula (matricula_turmas_cd_turma,matricula_alunos_cd_aluno)
    REFERENCES matricula (turmas_cd_turma,alunos_cd_aluno);

-- Reference: turmas_modaldiades_instrutores (table: turmas)
ALTER TABLE turmas ADD CONSTRAINT turmas_modaldiades_instrutores FOREIGN KEY turmas_modaldiades_instrutores (modalidade_turma,instrutor_turma)
    REFERENCES modaldiades_instrutores (modalidade_cd_modalidade,instrutores_cd_instrutor);

-- End of file.

