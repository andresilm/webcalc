

drop table if exists sessions;
drop table if exists calculations;

create table sessions
(
	id int primary key not null 
);

create table calculations
(
	id int PRIMARY KEY not null ,
	input varchar(100) not null,
	result varchar(100) not null,
	session_id int not null,
	foreign key(session_id) references sessions(id)		
);
