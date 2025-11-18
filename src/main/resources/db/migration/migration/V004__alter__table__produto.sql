alter table produto add fornecedor_id int not null;

alter table produto
    add constraint fk_produto_fornecedor_id
        FOREIGN KEY (fornecedor_id) references fornecedor(id);