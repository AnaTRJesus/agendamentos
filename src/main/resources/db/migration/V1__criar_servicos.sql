create table if not exists servicos

(
	id uuid primary key,
	descricao varchar(255) not null unique,
	valor decimal(26,8) not null
);

