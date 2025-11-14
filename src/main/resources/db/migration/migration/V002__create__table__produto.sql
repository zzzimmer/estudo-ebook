create table produto (
    id int not null auto_increment,
    nome varchar(50) not null ,
    descricao varchar(200) not null ,
    preco DECIMAL(10,2) not null,
    categoria_id int not null,

    primary key (id),

    constraint fk_produto_categoria
    foreign key (categoria_id) references categoria(id)
);

