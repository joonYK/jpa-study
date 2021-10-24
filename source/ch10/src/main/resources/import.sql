--team
insert into team(name) values('team1');
insert into team(name) values('team2');

--member
insert into member(id, age, username, team_id) values('member1', 10, '유저1', 1);
insert into member(id, age, username, team_id) values('member2', 15, '유저2', 1);
insert into member(id, age, username, team_id) values('member3', 17, '유저3', 1);
insert into member(id, age, username, team_id) values('member4', 20, '유저4', 2);

--product
insert into product(name, price, stockamount) values('상품1', 1000, 5);
insert into product(name, price, stockamount) values('상품2', 2000, 5);
insert into product(name, price, stockamount) values('상품3', 10000, 10);
insert into product(name, price, stockamount) values('상품4', 1200, 7);
insert into product(name, price, stockamount) values('상품5', 1550, 3);
insert into product(name, price, stockamount) values('상품6', 2000, 1);
insert into product(name, price, stockamount) values('상품7', 3000, 1);
insert into product(name, price, stockamount) values('상품8', 4000, 2);
insert into product(name, price, stockamount) values('상품9', 5000, 3);
insert into product(name, price, stockamount) values('상품10', 20000, 11);

--order
insert into orders(city, street, zipcode, orderamount, member_id, product_id) values('city1', 'street1', 'zipcode1',  1, 'member1', 1);
insert into orders(city, street, zipcode, orderamount, member_id, product_id) values('city1', 'street1', 'zipcode1',  5, 'member1', 3);
insert into orders(city, street, zipcode, orderamount, member_id, product_id) values('city2', 'street2', 'zipcode2',  2, 'member2', 1);
insert into orders(city, street, zipcode, orderamount, member_id, product_id) values('city1', 'street1', 'zipcode11',  1, 'member1', 2);
insert into orders(city, street, zipcode, orderamount, member_id, product_id) values('city3', 'street4', 'zipcode13',  1, 'member3', 3);
insert into orders(city, street, zipcode, orderamount, member_id, product_id) values('city4', 'street7', 'zipcode41',  1, 'member4', 3);
insert into orders(city, street, zipcode, orderamount, member_id, product_id) values('city5', 'street2', 'zipcode51',  1, 'member4', 6);
insert into orders(city, street, zipcode, orderamount, member_id, product_id) values('city7', 'street3', 'zipcode61',  1, 'member2', 10);
