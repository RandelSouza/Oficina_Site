create or replace function update_valor_total()
	returns trigger as $$
declare
	novo_valor decimal;
	preco decimal;
   
begin
	select p.p_preco into preco 
	from peca as p
	where p.p_id = new.p_id;
	
	
	novo_valor := preco * (old.P_UNIDADE+new.P_UNIDADE);
	update peca set P_VALOR_TOTAL=novo_valor, P_UNIDADE=new.P_UNIDADE where P_ID = new.P_ID;
	return new;
end;

$$ language plpgsql;


create trigger trigger_update_valor_total
after update on Peca
for each row
WHEN (pg_trigger_depth() = 0) 
execute procedure update_valor_total();


update peca set P_UNIDADE=0 where P_ID = 39;

