insert into user_details(id, birth_date, name)
values (10001, CURRENT_DATE, 'Junes');

insert into user_details(id, birth_date, name)
values (10002, CURRENT_DATE, 'Ranga');

insert into user_details(id, birth_date, name)
values (10003, CURRENT_DATE, 'Stash');

insert into post(id, description, user_id)
values (20001, 'i want to learn AWS', 10001);

insert into post(id, description, user_id)
values (20002, 'i want to learn DEV', 10001);

insert into post(id, description, user_id)
values (20003, 'i want to learn CERTY', 10002);

insert into post(id, description, user_id)
values (20004, 'i want to learn CLOUD', 10002);
