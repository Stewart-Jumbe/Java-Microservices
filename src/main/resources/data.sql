insert into USER_INFO(ID,BIRTH_DATE,NAME)
values(10001,current_date(),'James');


insert into USER_INFO(ID,BIRTH_DATE,NAME)
values(10002,current_date(),'Donald');


insert into USER_INFO(ID,BIRTH_DATE,NAME)
values(10003,current_date(),'Kendrick');

insert into POST(ID,DESCRIPTION,USER_ID)
values(1001,'Just been to the park',10001);

insert into POST(ID,DESCRIPTION,USER_ID)
values(1002,'Just seen a chicken eating grass!',10002);

insert into POST(ID,DESCRIPTION,USER_ID)
values(1003,'Worms do big poos',10003);