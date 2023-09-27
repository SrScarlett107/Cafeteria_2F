use master if exists(
select * from SYS.databases where name = 'myCafeteria2f')
drop database myCafeteria2f
go
create database myCafeteria2f
go
use myCafeteria2f


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
