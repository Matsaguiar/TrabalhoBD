create database trabalho;
use trabalho;

#C:\Users\Matheus Aguiar\Documents\teste.txt
#------------------------------------------------------
create table if not exists tipo_esporte(
 id_esporte int auto_increment,
 nome_esporte varchar(40),
 PRIMARY KEY(id_esporte)
);

create table if not exists partida(
 id_partida int,
 nome_partida varchar(30),
 id_esporte int,
 data_partida varchar(20),
 horario_partida varchar(20),
 id_estadio int,
 valor_ingresso int,
 PRIMARY KEY (id_partida),
 FOREIGN KEY (id_esporte) REFERENCES tipo_esporte(id_esporte),
 FOREIGN KEY (id_estadio) REFERENCES estadio(id_estadio)
);

create table if not exists usuario(
 id_usuario int auto_increment,
 cpf varchar(15),
 senha varchar(6),
 nome varchar(10),
 idade int,
 PRIMARY KEY (id_usuario)
);

create table if not exists gerente(
 id_usuario int,
 id_partida int,
 PRIMARY KEY (id_partida),
 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario),
 FOREIGN KEY (id_partida) REFERENCES partida(id_partida)
);

create table if not exists ingresso(
 id_ingresso int auto_increment,
 id_partida int,
 ingresso_disp int,
 id_estadio int,
 PRIMARY KEY (id_ingresso),
 FOREIGN KEY (id_partida) REFERENCES partida(id_partida),
 FOREIGN KEY (id_estadio) REFERENCES estadio(id_estadio)
);

create table if not exists estadio(
 id_estadio int auto_increment,
 nome_estadio varchar(15),
 quant_ingresso int,
 estado varchar (2),
 cidade varchar (10),
 PRIMARY KEY (id_estadio)
);

create table if not exists telefone(
 id_usuario int,
 fone varchar(16),
 PRIMARY KEY (fone, id_usuario),
 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);

create table if not exists cartao_credito(
 id_usuario int,
 id_cartao int auto_increment,
 numero varchar(16),
 validade varchar(5),
 cod_seguranca int,
 PRIMARY KEY (id_cartao, id_usuario),
 FOREIGN KEY (id_usuario) REFERENCES usuario(id_usuario)
);


##################################################################
#################     VIEW'S and PROCEDURE			##############
##################################################################


create view gerenteJogos as 
select distinct partida.id_partida, partida.nome_partida, partida.data_partida, estadio.nome_estadio, partida.valor_ingresso
  from gerente, partida, estadio, usuario
  where gerente.id_partida = partida.id_partida and 
        partida.id_estadio = estadio.id_estadio and 
        gerente.id_usuario = 1;


create view todasPartidas as
select distinct id_partida, nome_partida, data_partida, nome_estadio, valor_ingresso 
  from partida, estadio
  where partida.id_estadio = estadio.id_estadio;

#create view partidaEsporte as
select id_partida, nome_partida, data_partida, nome_estadio, valor_ingresso 
  from partida, estadio, tipo_esporte 
  where partida.id_esporte = tipo_esporte.id_esporte 
    and partida.id_estadio = estadio.id_estadio 
    and tipo_esporte.nome_esporte = "futamericano";


select id_partida, nome_partida, data_partida, nome_estadio, valor_ingresso 
  from partida, estadio, tipo_esporte 
  where partida.id_esporte = tipo_esporte.id_esporte 
    and partida.id_estadio = estadio.id_estadio 
    and estadio.estado = "rj";



DELIMITER $$
create procedure select_partidaEstadio (out quantidade int)
begin 
select nome_partida, data_partida, valor_ingresso, nome_esporte
  from partida, estadio, tipo_esporte 
   where partida.id_estadio = estadio.id_estadio 
     and partida.id_esporte=tipo_esporte.id_esporte
     and estadio.nome_estadio = "O2_arena";
  
end $$
DELIMITER 
call quant_tipoEsporte(@toy);
select @toy;

select nome_partida, nome_esporte, data_partida, nome_estadio, valor_ingresso
 from partida, tipo_esporte, estadio
  where partida.id_esporte = tipo_esporte.id_esporte and partida.id_estadio = estadio.id_estadio;
  
select cpf, nome, nome_esporte
 from usuario, gerente, tipo_esporte, partida
  where usuario.id_usuario=gerente.id_usuario 
   and  partida.id_partida=gerente.id_partida 
   and partida.id_esporte=tipo_esporte.id_esporte;

