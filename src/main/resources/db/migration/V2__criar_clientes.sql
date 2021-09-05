create table if not exists clientes

(
	id uuid primary key,
	nome varchar(100) not null unique,
	cpf varchar(11) not null
);