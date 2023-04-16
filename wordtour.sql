
use worldtourgamedb;

create table nationInit
(
nation varchar(20),
color varchar(10),
buildingPrice int,
villaPrice int,
hotelPrice int,
primary key (nation)
);

create table board
(id int,#-2 시작끝, 1~100 일반도시 200~황금열쇠 300~무인도 400~사회복지기금 500 여객기
ordernum int,
name varchar(10),
nation varchar(20),
/*color varchar(10),

buildingPrice int,
villaPrice int,
hotelPrice int,*/
tollPrice int,
tollFee int,
buildingFee int,
villaFee int,
hotelFee int,
primary key (id,name,ordernum));
#foreign key(nation,color,buildingPrice,villaPrice,hotelPrice)
#references nationInit(nation,color,buildingPrice,villaPrice,hotelPrice));

insert into nationInit (nation,color, buildingPrice,villaPrice,hotelPrice)
values('아시아','Yellow',15,5,25);
insert into nationInit (nation,color, buildingPrice,villaPrice,hotelPrice)
values('아프리카','Yellow',15,5,25);
insert into nationInit (nation,color, buildingPrice,villaPrice,hotelPrice)
values('유럽','SkyBlue',30,10,50);
insert into nationInit (nation,color, buildingPrice,villaPrice,hotelPrice)
values('북아메리카','SkyBlue',30,10,50);
insert into nationInit (nation,color, buildingPrice,villaPrice,hotelPrice)
values('명승지','DarkBlue',45,15,75);
insert into nationInit (nation,color, buildingPrice,villaPrice,hotelPrice)
values('대도시','Red',60,20,100);
insert into nationInit (nation)
values('특수부지');

drop table nationInit;
drop table board;

insert into board (id,ordernum,name,nation)
values (-2,0,'시작','특수부지');

insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (1,1,'타이베이','아시아',50000,2000,10000,90000,250000);

insert into board (id,ordernum,name,nation)
values (200,2,'황금열쇠','특수부지');

insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (2,3,'홍콩','아시아',80000,4000,180000,20000,450000);

insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (3,4,'마닐라','아시아',80000,4000,180000,20000,450000);

insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (4,5,'제주도','아시아',200000,300000,null,null,null);

insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (5,6,'싱가폴','아시아',100000,6000,30000,270000,550000);

insert into board (id,ordernum,name,nation)
values (201,7,'황금열쇠','특수부지');

insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (6,8,'카이로','아시아',100000,6000,30000,270000,550000);

insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (7,9,'이스탄불','아시아',120000,8000,40000,270000,550000);

insert into board (id,ordernum,name,nation)
values (300,10,'무인도','특수부지');


insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (8,11,'아테네','유럽',140000,10000,50000,450000,750000);
insert into board (id,ordernum,name,nation)
values (202,12,'황금열쇠','특수부지');
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (9,13,'코펜하겐','유럽',160000,12000,60000,500000,900000);
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (10,14,'스톡홀름','유럽',160000,12000,60000,500000,900000);
insert into board (id,ordernum,name,nation)
values (500,15,'콩코드여객기','특수부지');
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (11,16,'취리히','유럽',180000,14000,70000,500000,950000);
insert into board (id,ordernum,name,nation)
values (203,17,'황금열쇠','특수부지');
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (12,18,'베를린','유럽',180000,14000,70000,500000,950000);
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (13,19,'몬트리올','북아메리카',200000,16000,80000,550000,1000000);
insert into board (id,ordernum,name,nation)
values (400,20,'사회복지기금','특수부지');

insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (14,21,'부에노스 아이레스','명승지',220000,18000,90000,700000,1050000);
insert into board (id,ordernum,name,nation)
values (204,22,'황금열쇠','특수부지');
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (15,23,'상파울루','명승지',240000,20000,100000,750000,1100000);
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (16,24,'시드니','명승지',240000,20000,100000,750000,1100000);
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (17,25,'부산','아시아',500000,600000,null,null,null);
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (18,26,'하와이','명승지',260000,22000,110000,800000,1150000);
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (19,27,'리스본','명승지',260000,22000,110000,800000,1150000);
insert into board (id,ordernum,name,nation)
values (501,28,'퀸엘리자베스호','특수부지');
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (20,29,'마드리드','명승지',260000,22000,110000,800000,1150000);
insert into board (id,ordernum,name,nation)
values (502,30,'우주여행','특수부지');

insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (21,31,'도쿄','대도시',300000,26000,130000,900000,1270000);
insert into board (id,ordernum,name,nation)
values (503,32,'콜롬비아호','특수부지');
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (22,33,'파리','대도시',320000,28000,150000,1000000,8000000);
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (23,34,'로마','대도시',320000,28000,150000,1000000,8000000);
insert into board (id,ordernum,name,nation)
values (205,35,'황금열쇠','특수부지');
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (24,36,'런던','대도시',350000,35000,170000,1100000,1500000);
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (25,37,'뉴욕','대도시',350000,35000,170000,1100000,1500000);
insert into board (id,ordernum,name,nation)
values (401,38,'사회복지기금','특수부지');
insert into board (id,ordernum,name,nation, tollPrice,tollFee,buildingFee,villaFee,hotelFee)
values (26,39,'서울','아시아',1000000,2000000,null,null,null);

select *
from board natural join nationInit
ORDER BY board.ordernum;

insert into board (id,ordernum,name,nation)
values (500,15,'콩코드여객기','특수부지');

insert into board (id,ordernum,name,nation)
values (503,32,'콜롬비아호','특수부지');

insert into board (id,ordernum,name,nation)
values (501,28,'퀸엘리자베스호','특수부지');

UPDATE board  
SET tollPrice = 200000, tollFee=300000
where id=500;

UPDATE board  
SET tollPrice = 300000, tollFee=250000
where id=501;

UPDATE board  
SET tollPrice = 450000, tollFee=400000
where id=503;