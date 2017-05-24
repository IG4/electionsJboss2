CREATE DATABASE elections;
use elections;
grant usage on *.* to adm@localhost identified by 'adm'; 
grant all privileges on elections.* to adm@localhost;