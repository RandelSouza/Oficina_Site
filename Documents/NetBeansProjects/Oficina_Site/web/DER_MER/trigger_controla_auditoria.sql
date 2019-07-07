create or replace function inserir_controla_auditoria()
	returns trigger as $$
declare
  nome_funcionario varchar(100);
  cpf_funcionario varchar(15);
  nome_peca varchar(100);
  preco_peca decimal;
  valor_total_peca decimal;
  unidade_peca integer;
  login_usuario varchar(100);
  descricao_auditoria varchar(10000);
    
begin

	/*insert into Controla(U_ID, P_ID, C_DATA, C_HORA, C_ACAO)
	VALUES();*/

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

	descricao_auditoria := CONCAT('Nome do funcionario: ', nome_funcionario, ' \n ',
	       'CPF: ', cpf_funcionario, ' \n ',
	       'Ação: Peça Inserida (', new.c_acao, ')', ' \n ',
	       'Nome de Usuário: ', login_usuario, ' \n ', 
	       'Nome da Peça: ', nome_peca, ' \n ',
	       'Preço da Peça: ', cast(preco_peca as varchar(100)), ' \n ',
	       'Unidades da Peça: ', cast(unidade_peca as varchar(100)), ' \n ',
	       'Valor Total Peça: ', cast(valor_total_peca as varchar(100)), ' \n ',
	       'Data: ', cast(CURRENT_DATE as varchar(100)), ' \n ',
	       'Hora: ', cast(CURRENT_TIME as varchar(100)));											
	
	insert into Auditoria(P_ID, A_DESCRICAO)
	values( new.P_ID, descricao_auditoria);	
	
	return new;
end;

$$ language plpgsql;

create trigger trigger_inserir_controla_auditoria
after insert on Controla
for each row
execute procedure inserir_controla_auditoria();




/*insert into peca(p_nome, p_preco, p_unidade) values('teste_trigger_auditoria', 10, 2);

INSERT INTO Controla(U_ID, P_ID, C_DATA, C_ACAO) VALUES(1, 41, CURRENT_DATE, 'INSERT');	

select CONCAT('DADS ', CAST(12 AS VARCHAR(10)));

delete from auditoria a where a.p_id = a.p_id;
delete from controla a where a.p_id = a.p_id;

*/


INSERT INTO Controla(U_ID, P_ID, C_DATA, C_ACAO) VALUES(1, 41, CURRENT_DATE, 'INSERT');	


select * from auditoria

