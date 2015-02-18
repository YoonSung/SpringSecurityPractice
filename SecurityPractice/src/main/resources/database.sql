-- Only Design for Resource url pattern

USE securityPractice;

CREATE TABLE IF NOT EXISTS tbl_member (
	id VARCHAR(50),
	password VARCHAR(200),
	name VARCHAR(30),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tbl_authority (
	authority VARCHAR(50),
	authority_name VARCHAR (50),
	PRIMARY KEY (authority)
);

CREATE TABLE IF NOT EXISTS tbl_member_authority (
	id VARCHAR(50),
	authority VARCHAR(50),
	FOREIGN KEY (id) REFERENCES tbl_member(id),
	FOREIGN KEY (authority) REFERENCES tbl_authority(authority)
);

CREATE TABLE IF NOT EXISTS tbl_member_role (
	id VARCHAR(50),
	role VARCHAR(50),
	FOREIGN KEY (id) REFERENCES tbl_member (id)
);

CREATE TABLE IF NOT EXISTS tbl_group (
	id INT,
	group_name VARCHAR(50),
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tbl_group_member (
	group_id INT,
	member_id VARCHAR(50),
	FOREIGN KEY (group_id) REFERENCES tbl_group(id),
	FOREIGN KEY (member_id) REFERENCES tbl_member(id)	
);

CREATE TABLE IF NOT EXISTS tbl_group_authority (
	group_id INT,
	authority VARCHAR(50),
	FOREIGN KEY (group_id) REFERENCES tbl_group(id),
	FOREIGN KEY (authority) REFERENCES tbl_authority(authority)
);

CREATE TABLE IF NOT EXISTS tbl_secured_resource (
	id VARCHAR(10),
	name VARCHAR(50),
	pattern VARCHAR(100),
	type VARCHAR(10),
	sort_order INT,
	PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS tbl_secured_resource_authority (
	resource_id VARCHAR(10),
	authority VARCHAR(50),
	name VARCHAR(30),
	FOREIGN KEY (resource_id) REFERENCES tbl_secured_resource(id),
	FOREIGN KEY (authority) REFERENCES tbl_authority(authority)
);

INSERT INTO tbl_member VALUES("test", "testest", "yoonsung");
INSERT INTO tbl_member_role VALUES("test", "admin");