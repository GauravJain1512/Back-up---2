insert into user(USER_ID, USER_NAME, EMAIL, PASSWORD, EMPLOYEE_ID) values
(10001, 'Dhananjay Jain', 'dj2@gmail.com','123654789',2338677),
(10002, 'Mukesh Parihar', 'mp2@gmail.com', '987456321',2448677),
(10003, 'Dheeraj Pawar', 'dp2@gmail.com', '0123654789',2558677);

insert into role(ROLE_ID, ROLE_NAME) values
(101,'ADMIN'),
(102, 'SELLER'),
(103, 'CONSUMER');

insert into user_roles(USER_ID, ROLE_ID) values
(10001,101),
(10002,102),
(10003,103);


INSERT INTO CATEGORY (CATEGORY_NAME) VALUES
  ('Fashion'),
  ('Electronics'),
  ('Books'),
  ('Groceries'),
  ('Medicines');
  
INSERT INTO CART (TOTAL_AMOUNT ,USER_ID) VALUES
  (20,10002),
  (0,10002);
  
INSERT INTO PRODUCT (price, PRODUCT_NAME, CATEGORY_ID, USER_ID) VALUES
  (29190, 'Apple iPad 10.2 8th Gen WiFi iOS Tablet', 2, 10002),
  (10, 'Crocin pain relief tablet', 5, 10002);

INSERT INTO CART_PRODUCT (CART_ID, PRODUCT_ID, quantity) VALUES
  (1, 2, 2);