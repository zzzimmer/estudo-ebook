create table estoque(
                        produto_id int not null references produto(id),
                        quantidade int not null default 0,
                        qtd_max int default 0,
                        qtd_min int default 0,
                        situacao ENUM('ATIVO','INATIVO','BLOQUEADO') not null DEFAULT 'ATIVO',

                        primary key (produto_id)
);

alter table estoque add constraint fk_estoque_produto
    foreign key (produto_id)
        references produto(id) on update cascade on delete cascade;
