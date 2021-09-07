create table if not exists clientes

(
	id uuid primary key,
	nome varchar(100) not null unique,
	cpf varchar(11) not null
);

insert into clientes (id, nome, cpf) values ('242fdde9-63ea-4a54-af15-862bec47ea98', 'Ana Tais Ribeiro de Jesus', '41849940835');