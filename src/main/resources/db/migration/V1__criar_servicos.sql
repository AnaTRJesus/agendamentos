create table if not exists servicos

(
	id uuid primary key,
	descricao varchar(255) not null unique,
	valor decimal(26,8) not null
);

insert into servicos (id, descricao, valor) values ('82084d66-4163-42bf-b6f9-d680ddf0352d', 'Serviço de cabeleleiro', 500);
insert into servicos (id, descricao, valor) values ('102dfc22-e7a3-4095-97cc-b343908e7840', 'Serviçoes de manicure', 200);

