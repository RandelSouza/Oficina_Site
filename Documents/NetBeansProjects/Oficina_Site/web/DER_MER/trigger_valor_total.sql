
create function atualiza_valor_total()
	returns trigger as $$
begin
	new.p_valor_total := new.P_PRECO * new.P_UNIDADE;
	update peca set P_VALOR_TOTAL=new.p_valor_total where P_ID = new.P_ID;
	return new;
end;

$$ language plpgsql;


create trigger trigger_atualiza_valor_total
before insert on Peca
for each row
execute procedure atualiza_valor_total();


