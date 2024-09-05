use master if exists(
select * from SYS.databases where name = 'myCafeteria2f')
drop database myCafeteria2f
go
create database myCafeteria2f
go
use myCafeteria2f



create table categorias(
id bigint identity primary key,
categoria varchar(30),

)
create table cardapios (
id bigint  identity primary key,
nome varchar(20),
foto varbinary(max),
descricao varchar(200),
valor decimal(4,2),
dataDoCadastro date,
codStatusCardapio bit,
id_categoria bigint,
Constraint fk_categoria foreign key (id_categoria) references categorias(id)

)


create table adms(
id bigint identity primary key,
usuario varchar(20),
senha int,

)

insert into adms values('Mateus', 1234456 )
insert into adms values('Gisely', 6543211)
insert into adms values('Matheus', 250706)