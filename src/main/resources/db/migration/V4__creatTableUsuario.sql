create table usuario(
	id bigint not null auto_increment,
	login varchar(100) not null,
	senha varchar(250) not null,
	
	primary key(id)
 );