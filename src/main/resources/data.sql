insert into candidate(name)  values
('Alice'),
('Bob'),
('Charlie');

insert into voter(name) values
('voter1'),
('voter2'),
('voter3'),
('voter4'),
('voter5'),
('voter6');

insert into vote(candidate_id, voter_id, vote_type) values
(1,1,'VALID'),
(1,2,'VALID'),
(2,3,'VALID'),
(2,4,'BLANK'),
(NULL,5,'BLANK'),
(3,6,'NULL');
