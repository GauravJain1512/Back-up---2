insert into roles(role_id, role_name) 
values(1,'ADMIN'),(2,'USER'),(3,'ASSOCIATE'),(4,'TEAM_LEAD');

insert into authorities(authority_id, authority_name) values
(1,'VIEW'),
(2,'EDIT'),
(3,'CREATE'),
(4,'DELETE'),
(5, 'EVALUATE');

insert into ROLES_AUTHORITIES(role_id, authority_id) values
(1,1),(1,2),(1,3),(1,4),(1,5),(2,1),(3,1),(4,1),(4,2),(4,3),(4,4);

insert into team_lead
(TEAM_LEAD_ID,BASE_BRANCH,DEPUTE_BRANCH,EMAIL,EMPLOYEE_NUMBER,JOINING_DATE,MOBILE_NUMBER,NUMBER_OF_EXPERIENCE,TEAM_LEAD_NAME,PASSWORD) values
(1,'Hydrabad','Hydrabad','gopal@gmail.com', 234567, '2010-05-05', 9856321478, 12.9, 'Gopal Patil','password1'),
(2,'Pune','Pune','rahul@gmail.com', 789654, '2011-07-01', 9512367406, 11.6, 'Rahul Patil','password2');

insert into TEAM_LEAD_ROLES(TEAM_LEAD_ID,ROLE_ID) values
(1,4),
(2,1),
(2,4);


insert into associate
(ASSOCIATE_ID,ASSOCIATE_EMAIL,ASSOCIATE_NAME,BASE_BRANCH,DEPUTE_BRANCH,EMPLOYEE_NUMBER,JOINING_DATE,NUMBER_OF_COURSES_COMPLETED,
NUMBER_OF_COURSES_IN_PROGRESS, YEAR_OF_EXPERIENCE, TEAM_LEAD_ID, PASSWORD) values
(1, 'dj@agmail.com', 'Dhananjay Jain', 'Indore','Indore', 2338677, '2022-03-16', 197, 36,  1.1, 1,'password1'),
(2, 'gaurav@amil.com', 'Gaurav Jain', 'Pune','Pune', 2338676, '2022-03-03', 264, 02,  1.1, 2,'password2');

insert into ASSOCIATE_ROLES(ASSOCIATE_ID, ROLE_ID) values
(1,3),
(2,3);





