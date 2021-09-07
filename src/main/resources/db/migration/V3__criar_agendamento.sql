create table if not exists agendamentos

(
	id uuid primary key,
	observacao varchar(100) not null,
	data_hora Timestamp not null,
	cliente_id uuid not null references clientes(id),
	servico_id uuid not null references servicos(id) 	
);

insert into agendamentos (id, observacao, data_hora, cliente_id, servico_id)  values ('ac172876-d7db-4d3a-b914-ac56e797b9b2', 'Servico ok', '2011-10-02 18:48:05.123', '242fdde9-63ea-4a54-af15-862bec47ea98', '82084d66-4163-42bf-b6f9-d680ddf0352d');

insert into agendamentos (id, observacao, data_hora, cliente_id, servico_id)  values ('3ca5fafa-02ab-4e39-b67a-7b9679746381', 'Servico ok', '2011-10-02 18:48:05.123', '242fdde9-63ea-4a54-af15-862bec47ea98', '102dfc22-e7a3-4095-97cc-b343908e7840');