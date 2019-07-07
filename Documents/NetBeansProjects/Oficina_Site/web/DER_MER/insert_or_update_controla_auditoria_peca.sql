create or replace function insert_or_update_controla_auditoria_peca()
	returns trigger as $$
		
declare
	nome_funcionario varchar(100);
	cpf_funcionario varchar(15);
	nome_peca varchar(100);
	preco_peca decimal;
	valor_total_peca decimal;
	unidade_peca integer;
	login_usuario varchar(100);
	descricao_auditoria_insert varchar(10000);
	descricao_auditoria_update varchar(10000);
	descricao_auditoria_delete varchar(10000);
    
begin	
	
	select u.u_login into login_usuario
	from Usuario as u
	where u.u_id = new.u_id;

	select into nome_peca, preco_peca, unidade_peca,  valor_total_peca
	p.p_nome , p.p_preco , p.p_unidade, (p.p_unidade * p.p_preco)
	from Peca as p
	where p.p_id = new.p_id;	

	select into nome_funcionario, cpf_funcionario
	f.f_nome, f.f_cpf 
	from Funcionario as f, Usuario as u
	where u.u_id = new.u_id
	and f.f_id = u.f_id;

	CASE new.c_acao 
		WHEN 'INSERT' THEN
			descricao_auditoria_insert := CONCAT('Nome do funcionario: ', nome_funcionario, ' \n ',
		       'CPF: ', cpf_funcionario, ' \n ',
		       'Ação: Peça Inserida (', new.c_acao, ')', ' \n ',
		       'Nome de Usuário: ', login_usuario, ' \n ', 
		       'Nome da Peça: ', nome_peca, ' \n ',
		       'Preço da Peça: ', cast(preco_peca as varchar(100)), ' \n ',
		       'Unidades da Peça: ', cast(unidade_peca as varchar(100)), ' \n ',
		       'Valor Total Peça: ', cast(valor_total_peca as varchar(100)), ' \n ',
		       'Data: ', cast(CURRENT_DATE as varchar(100)), ' \n ',
		       'Hora: ', cast(CURRENT_TIME as varchar(100)));											

			insert into Auditoria(P_ID, A_DESCRICAO) values( new.P_ID, descricao_auditoria_insert);				
			return new;

		WHEN 'UPDATE' THEN
			descricao_auditoria_update := CONCAT('Nome do funcionario: ', nome_funcionario, ' \n ',
		       'CPF: ', cpf_funcionario, ' \n ',
		       'Ação: Peça Atualizada (', new.c_acao, ')', ' \n ',
		       'Nome de Usuário: ', login_usuario, ' \n ', 
		       'Nome da Peça: ', nome_peca, ' \n ',
		       'Preço da Peça: ', cast(preco_peca as varchar(100)), ' \n ',
		       'Unidades da Peça: ', cast(unidade_peca as varchar(100)), ' \n ',
		       'Valor Total Peça: ', cast(valor_total_peca as varchar(100)), ' \n ',
		       'Data: ', cast(CURRENT_DATE as varchar(100)), ' \n ',
		       'Hora: ', cast(CURRENT_TIME as varchar(100)));											

			insert into Auditoria(P_ID, A_DESCRICAO) values( new.P_ID, descricao_auditoria_update);						
			return new;
			
		WHEN 'DELETE' THEN
			descricao_auditoria_delete := CONCAT('Nome do funcionario: ', nome_funcionario, ' \n ',
		       'CPF: ', cpf_funcionario, ' \n ',
		       'Ação: Peça Excluida (', new.c_acao, ')', ' \n ',
		       'Nome de Usuário: ', login_usuario, ' \n ', 
		       'Nome da Peça: ', nome_peca, ' \n ',
		       'Preço da Peça: ', cast(preco_peca as varchar(100)), ' \n ',
		       'Unidades da Peça: ', cast(unidade_peca as varchar(100)), ' \n ',
		       'Valor Total Peça: ', cast(valor_total_peca as varchar(100)), ' \n ',
		       'Data: ', cast(CURRENT_DATE as varchar(100)), ' \n ',
		       'Hora: ', cast(CURRENT_TIME as varchar(100)));											
			insert into Auditoria(P_ID, A_DESCRICAO) values(new.P_ID, descricao_auditoria_delete); 
			return new;
	AND CASE;

	return null;

end;
$$ language plpgsql;

create trigger trigger_insert_or_update_controla_auditoria_peca
after insert on Controla
for each row 
execute procedure insert_or_update_controla_auditoria_peca();



