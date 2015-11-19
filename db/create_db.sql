

drop table if exists sessions;
drop table if exists calculations;

create table calculations
(
	history_id int not null ,
	input varchar(100) not null,
	result varchar(100) not null,
	session_id varchar(50) not null
);
