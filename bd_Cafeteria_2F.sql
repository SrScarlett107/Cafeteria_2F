use master if exists(
select * from SYS.databases where name = 'bd_Cafeteria_2F')
drop database bd_Cafeteria_2F
go
create database bd_Cafeteria_2F
go
use bd_Cafeteria_2F

create table Tabela1(
id int identity,
nome varchar(100) not null,
primary key (id),
)
insert Tabela1 (nome) values ('valor 1')

create table Tabela2(
id int identity,
nome varchar(100) not null,
statusItem varchar(20) not null,
tabela_id int not null,

primary key (id),
foreign key(tabela_id) references Tabela1(id)
)
insert Tabela2 (nome, statusItem, tabela_id) values ('valor 2','Ativo', 1)

create table Usuario(
id int identity,
nome varchar(100) not null,
email varchar(100) unique not null,
senha varchar(100) not null,
nivelAcesso varchar(10) null,
foto varbinary(MAX) null,
statusUsuario varchar(20) not null,

primary key (id)
)
insert Usuario( nome, email, senha, nivelAcesso, foto, statusUsuario) values('ana sá', 'ana@gmail.com', '123', 'ADMIN', null, 'ATIVO')

create table Categoria
(
id int identity,
nomeCat varchar(100) not null,

primary key (id)
)


create table Produto
(
id int identity,
nome varchar(100) not null,
descricao varchar(400)  not null,
preco decimal(10,2) not null,
categoria_id int null,
foto varbinary(MAX) null,
statusProduto varchar(20) not null,

primary key (id),
foreign key (categoria_id) references Categoria(id)
)
