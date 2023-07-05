create table member (
	member_num number(10),
	id varchar2(30) not null,
	pwd varchar2(30),
	name varchar2(30),
	phone varchar2(30),
	email varchar2(50),
	zip_num varchar2(50),
	address1 varchar2(50),
	address2 varchar2(50),
	point number(10) DEFAULT 0,
	indate date default sysdate,
	useyn char(1) DEFAULT 'Y',
	birth varchar2(15),
	gender char(1),
	primary key(member_num)
);

create sequence member_seq increment by 1 start with 1;
drop table member;
select * from member;

alter table member modify pwd varchar2(50) null;

create table board (
	board_num number(6),
	title varchar2(100),
	content varchar2(3000),
	indate date default sysdate,
	primary key(board_num)
);


select * from board;

create sequence board_seq increment by 1 start with 1;

insert into board(board_num, title, content) values(board_seq.nextval, '테스트입니다 1', '내용 테스트입니다123');
insert into board(board_num, title, content) values(board_seq.nextval, '테스트입니다 1', '내용 테스트입니다123');
insert into board(board_num, title, content) values(board_seq.nextval, '테스트입니다 1', '내용 테스트입니다123');
insert into board(board_num, title, content) values(board_seq.nextval, '테스트입니다 1', '내용 테스트입니다123');

alter table board add image varchar2(100) null;

select * from worker;

create table admin(
	id varchar2(30),
	pwd varchar2(30)
);


insert into admin values('admin', 'admin');




