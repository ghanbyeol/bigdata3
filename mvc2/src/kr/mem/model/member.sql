select * from EMPLOYEES;
--È¸¿ø°¡ÀÔ
create table tblMem(
	num number primary key,
	name varchar2(20) not null,
	phone varchar2(20) not null,
	addr varchar2(50),
	lat number(16,12),
	lng number(16,12)
)
drop table tblMem;

create sequence seq_num; 
insert into tblMem
values(seq_num.nextval,'È«±æµ¿','010-1234-1234','±¤ÁÖ±¤¿ª½Ã ºÏ±¸ È£µ¿·Î123',
35.1257699845615, 126.852047602507);

select * from tblMem;





