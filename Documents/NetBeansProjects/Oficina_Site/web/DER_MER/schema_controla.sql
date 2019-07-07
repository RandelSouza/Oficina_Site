create table controla(
	u_id integer not null,
	p_id integer not null,
	c_data date,
	c_hora time,
	c_acao varchar(10),

	constraint controla_pkey primary key (u_id, p_id, c_data, c_hora),
	constraint controla_fkey foreign key (u_id) references Usuario(u_id)
	
);


