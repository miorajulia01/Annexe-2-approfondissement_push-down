create database annexe2_push_down;

create user candidat with password 'election';
alter user candidat with superuser;


GRANT ALL PRIVILEGES ON DATABASE annexe2_push_down TO candidat;
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO candidat;
GRANT ALL ON SCHEMA public TO candidat;
GRANT ALL PRIVILEGES ON ALL SEQUENCES IN SCHEMA public TO candidat;