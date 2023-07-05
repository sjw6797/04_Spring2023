select * from tab;

select * from airLine;

create sequence airLine_seq increment by 1;

insert into airLine( airLine_num,airLine_name,image,business_sit,prestige_sit) values(airLine_seq.nextval,'아시아나항공',null,250,50);
insert into airLine( airLine_num,airLine_name,image,business_sit,prestige_sit) values(airLine_seq.nextval,'에어부산',null,250,40);
insert into airLine( airLine_num,airLine_name,image,business_sit,prestige_sit) values(airLine_seq.nextval,'이스타항공',null,250,40);
insert into airLine( airLine_num,airLine_name,image,business_sit,prestige_sit) values(airLine_seq.nextval,'제주항공',null,250,40);
insert into airLine( airLine_num,airLine_name,image,business_sit,prestige_sit) values(airLine_seq.nextval,'진에어',null,250,50);
insert into airLine( airLine_num,airLine_name,image,business_sit,prestige_sit) values(airLine_seq.nextval,'대한항공',null,250,50);
insert into airLine( airLine_num,airLine_name,image,business_sit,prestige_sit) values(airLine_seq.nextval,'티웨이항공',null,250,40);

create table airLine(
	airLine_num number(6),
	airLine_name varchar2(30),
	image varchar2(100),
	business_sit number(5),
	prestige_sit number(5),
	primary key(airLine_name)
);

alter table RESERVATION add vihicleId varchar2(10);

select * from view_reservation;
select * from view_reservation_remain;

update reservation set vihicleId='OZ8141' where vihicleId is null;

select * from view_reservation ;

-- reservation view
create or replace view view_reservation as 
select r.*,a.*,concat(r.vihicleId,r.dep_time) as identity,
		(business_sit+prestige_sit) as total_sit
from reservation r left join airLine a 
on r.airplane_name = a.airline_name
-- 남은 좌석 
create or replace view view_reservation_remain as
select identity,count(identity) as reserved
from view_reservation r
group by r.identity
having count(r.identity) >=1;
--
create or replace view view_qna_replace as 
select r.arrivals,count(r.arrivals) as reserved
from reservation r
group by r.arrivals
having count(r.arrivals) >1;
--
select identity,count(identity) as reserved
from view_reservation
group by identity
having count(identity) >1;


update qna set passcheck='Y' where passcheck is null 'N';
select * from qna;
select * from view_qna;

--qna + member view
create or replace view view_qna as 
select q.qna_num,q.mnum,m.id,m.name,m.email,q.title,q.content,q.indate,q.result,q.reply,q.readcount,q.imgfilename,q.pass,q.passcheck
from QNA q left join MEMBER m on q.mnum=m.member_num order by q.qna_num desc;


create table qna (
	qna_num number(10),
	mnum number(6),
	title varchar2(255) not null,
	content varchar2(1000) not null,
	indate date default sysdate,
	result char(1) default 'N',
	reply varchar2(1000),
	
	constraint fk30 foreign key (mnum) references member(member_num),
	primary key(qna_num)
);

update qna set readcount=0 where readcount is null;
alter table qna add passcheck char(1) default 'N';
alter table qna add passPlusreadcount varchar2(100);
alter table qna add readcount number(4);
alter table qna add imgfilename varchar2(100);

alter table qna modify readcount number(4) default 0;
alter table qna modify imgfilename varchar2(100) default '';

create sequence qna_seq start with 50000;



select * from qna;
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다2','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다3','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다4','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다5','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다6','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다7','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다8','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다9','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다0','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다-','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다=','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다1','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다2','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다3','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다4','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다5','테스트용입니다');
insert into qna(qna_num,mnum,title,content) values(qna_seq.nextval,25,'테스트입니다6','테스트용입니다');

--------------------------------------------------------------------------------------------
--국가 테이블 만들기
CREATE SEQUENCE country_seq INCREMENT BY 1 START WITH 80000;
create table country(
	country_num number(6) ,	-- seq번호
	country_kor varchar2(30) ,	-- 국가이름(한글명)
	country_eng varchar2(30),	-- 국가이름(영문명)

	primary key(country_kor)
);

insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'브라질','Brazil');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'콩고 민주공화국','Democratic Republic of Congo');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'덴마크','Denmark');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'노르웨이','Norway');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'이란','Iran');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'러시아','Russia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'캐나다','Canada');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'쿠바','Cuba');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'사우디아라비아','Saudi Arabia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'미국','United States');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'이탈리아','Italy');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'UAE','United Arab Emirates');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'이집트','Egypt');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'나이지리아','Nigeria');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'콜롬비아','Colombia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'인도네시아','Indonesia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'인도','India');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'터키','Turkey');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'오스트레일리아','Australia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'프랑스','France');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'아르헨티나','Argentina');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'일본','Japan');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'프랑스령폴리네시아','French Plynesia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'알제리','Algeria');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'중국','China');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'그리스','Greece');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'아이슬란드','Iceland');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'모로코','Morocco');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'파라과이','Paraguay');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'북마케도니아','North Macedonia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'페루','Peru');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'필리핀','Philippines');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'파키스탄','Pakistan');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'카자흐스탄','Kazakhstan');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'멕시코','Mexico');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'마다가스카르','Madagascar');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'카보베르데','Cabo Verde');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'요르단','Jordan');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'스웨덴','Sweden');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'캄보디아','Cambodia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'버마','Myanmar');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'괌','Guam');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'푸에르토리코','Puerto Rico');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'벨기에','Belgium');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'루마니아','Romania');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'도미니카공화국','Dominican Republic');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'칠레','Chile');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'탄자니아','Tanzania');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'사모아','Samoa');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'뉴질랜드','New Zealand');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'니카라과','Nicaragua');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'쿡 군도','Cook Islands');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'폴란드','Poland');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'영국','United Kingdom');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'바하마','Bahamas');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'과들루프','Guadeloupe');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'에콰도르','Ecuador');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'카메룬','Cameroon');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'스리랑카','Sri Lanka');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'중앙아프리카공화국','Central African Republic');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'보스니아 헤르체고비나','Bosnia and Herzegovina');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'슬로바키아','Slovakia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'말레이시아','Malaysia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'조지아','Georgia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'대만','Chinese Taipei');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'모잠비크','Mozambique');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'레바논','Lebanon');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'포르투갈','Portugal');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'세르비아','Serbia and Montenegro');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'스위스','Switzerland');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'이스라엘','Israel');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'독일','Germany');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'세네갈','Senegal');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'남아프리카','South Africa');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'오스트리아','Austria');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'이디오피아','Ethiopia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'부르키나 파소','Burkina Faso');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'우크라이나','Ukraine');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'슬로베니아','Slovenia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'부룬디','Burundi');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'우즈베키스탄','Uzbekistan');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'불가리아','Bulgaria');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'태국','Thailand');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'베트남','Viet Nam');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'앙골라','Angola');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'베냉','Benin');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'홍콩','Hong Kong');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'우루과이','Uruguay');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'싱가포르','Singapore');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'가이아나','Guyana');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'대한민국','Republic of Korea');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'몽고','Mongolia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'몰도바','Moldova');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'코코스제도','Cocos Island');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'엘살바드로','El Salvador');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'기니','Guinea');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'아일랜드','Ireland');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'트리니다드토바고','Trinidad And Tobago');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'마카오','Macao');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'코스타리카','Costa Rica');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'니제르','Niger');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'카타르','Qatar');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'크로아티아','Croatia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'오만','Oman');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'마요트','Mayotte');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'네덜란드','Netherlands');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'볼리비아','Bolivia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'튀니지','Tunisia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'케냐','Kenya');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'핀란드','Finland');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'우간다','Uganda');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'이라크','Iraq');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'나미비아','Namibia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'프랑스령기아나','French Guiana');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'코트디부아르','Cote dIvoire');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'헝가리','Hungary');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'네덜란드령 카리브','Netherlands Antilles');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'보츠와나','Botswana');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'통가','Tonga');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'아제르바이잔','Azerbaijan');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'세인트루시아','Saint Lucia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'온두라스','Honduras');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'세인트마틴','Saint Martin ');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'바베이도스','Barbados');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'짐바브웨','Zimbabwe');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'퀴라소','Curacao');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'왈리스푸투나','Wallis and Futuna');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'체코 공화국','Czech Republic');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'리투아니아','Lithuania');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'수단','Sudan');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'르완다','Rwanda');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'에스와티니','Eswatini');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'뉴칼레도니아','New Caledonia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'가나','Ghana');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'쿠웨이트','Kuwait');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'버뮤다제도','Bermuda');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'과테말라','Guatemala');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'모리타니아','Mauritania');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'키프러스','Cyprus');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'에스토니아','Estonia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'가봉','Gabon');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'말라위','Malawi');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'잠비아','Zambia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'토고','Togo');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'시에라리온','Sierra Leone');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'룩셈부르크','Luxembourg');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'몰디브','Maldives');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'몰타','Malta');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'키르기스스탄','Kyrgyzstan');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'그라나다','Grenada');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'벨로루시','Belarus');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'생피에르미클롱','Saint Pierre and Miquelon');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'모나코','Monaco');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'피지','Fiji');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'자메이카','Jamaica');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'기니비사우','Guinea Bissau');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'케이맨제도(영국)','Cayman Islands');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'부탄','Bhutan');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'에위니옹','Reunion');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'모리셔스','Mauritius');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'몬테네그로','Montenegro');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'코모로','Comoros');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'신트마르턴','Sint Maarten ');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'상투메프린시페','Sao Tome and Principe');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'코소보','Kosovo');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'아루바','Aruba');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'라트비아','Latvia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'북마리아나제도','Northern Mariana Islands');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'세인트헬레나(영국령)','Saint Helena');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'생바르텔레미','Saint Barthelemy');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'적도 기니','Equatorial Guinea');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'말리','Mali');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'세이셸','Seychelles');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'아르메니아','Armenia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'베네수엘라','Venezuela');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'알바니아','Albania');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'파나마','Panama');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'네팔','Nepal');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'페로 제도','Faroe Islands');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'감비아','Gambia');
insert into country(country_num,country_kor,country_eng) values(country_seq.nextVal,'수리남','Suriname');

--------------------------------------------------------------------------------------------
