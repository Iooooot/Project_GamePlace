create database gamerPlace;

use gamerPlace;
create table tab_category(
    cid int primary key auto_increment,
    cname varchar(100) unique not null
);

insert into tab_category (cname) values('角色扮演'),('动作游戏'),('体育竞技'),('策略战棋'),('赛车竞速'),('冒险解密'),('第一人称射击'),('第三人称射击');

create table tab_user(
    uid int primary key auto_increment,
    username varchar(100) unique not null ,
    password varchar(32),
    name varchar(100),
    birthday date,
    sex char(1),
    telephone varchar(11),
    email varchar(100),
    status char(1),
    code varchar(50) unique not null
);

create table tab_game(
    gid int primary key auto_increment,
    gname varchar(500),
    price double,
    gameIntroduce varchar(1000),
    gflag char(1),
    count int,
    cid int,
    gimage varchar(200),
    foreign key (cid) references tab_category(cid)
);

insert into tab_game(gname, price, gameIntroduce, gflag, count, cid, gimage)values('《漫威复联》首位黑人超级英雄黑豹出现 为瓦坎达而战',199,'《漫威复仇者联盟》是由《古墓丽影》系列开发商水晶动力和《杀出重围》开发商Eidos Montreal联合打造。游戏拥有一个原创故事，包含大家所喜爱的角色。',1,0,1,'images/search_01.png');

use gamerPlace;
create table tab_game_img(
    imgid int primary key auto_increment,
    gid int,
    bigPic varchar(200),
    smallPic varchar(200),
    foreign key (gid) references tab_game(gid)
);




use gamerPlace;
create table tab_favorite(
    gid int,
    date date,
    uid int,
    foreign key (gid) references tab_game(gid),
    foreign key (uid) references tab_user(uid),
    primary key (uid,gid)
);



delimiter $$
create procedure insertImg()
begin
	declare i int default 113;
	declare j int default 0;
	while i<=598 do
        update tab_game_img set gid = i where imgid in (select t.imgid from (select * from tab_game_img limit j,3)as t);
	    set j = j+3;
		set i = i+1;
	end while;
end
$$
delimiter ;
call insertImg();

delimiter $$
create procedure changeCount()
begin
	declare i int default 146;
	declare j int default 30;
	while i<=572 do
        update tab_game set count = j where gid = i;
	    set j = j+2;
		set i = i+1;
	end while;
end
$$
delimiter ;

call changeCount();


