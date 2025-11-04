create table t_tdspw_doce (
    cd_doce number(6,0) primary key,
    nm_doce varchar2(80) not null,
    vl_doce number(8,2) not null,
    vl_peso number(5,2),
    dt_validade date not null
);

create sequence sq_tdspw_doce start with 1 increment by 1 nocache;

create table t_tdspw_avaliacao (
    cd_avaliacao number(6,0) primary key,
    ds_avaliacao varchar2(200),
    dt_cadastro date,
    vl_nota number(4,2),
    cd_doce number(6,0) not null,
    constraint fk_cd_doce foreign key (cd_doce) references t_tdspw_doce (cd_doce)
);

create sequence sq_tdspw_avaliacao start with 1 increment by 1 nocache;