insert into user(USER_ID, USERNAME, FIRST_NAME, LAST_NAME,CITY,PASSWORD) values
(10001, 'jn2@gmail.com', 'Gaurav', 'Jain','Pune','123654789'),
(10002, 'dj2@gmail.com', 'Dhananjay', 'Jain','Indore','9874566321');

insert into role(ROLE_ID,ROLE_NAME) values 
(101,'SELLER'),
(102,'CONSUMER');

insert into user_roles(ROLE_ID,USER_ID) values
(101,10001),
(102,10002);