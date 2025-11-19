create table estoque(
                        produto_id int not null references produto(id),
                        quantidade int,
                        quantidade_maxima int not null,
                        quantidade_minima int not null,

                        primary key (produto_id)
);

alter table estoque add constraint fk_estoque_produto
    foreign key (produto_id)
        references produto(id) on update cascade on delete cascade;
