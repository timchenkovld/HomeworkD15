--CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
--
--CREATE TABLE note(
--id UUID DEFAULT uuid_generate_v4() PRIMARY KEY,
--title VARCHAR(15) NOT NULL CHECK(LENGTH(title)>=1 AND LENGTH(title)<=15),
--content VARCHAR(255) NOT NULL
--);

--ALTER TABLE note ADD CONSTRAINT note_user_fk FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE;

CREATE TABLE note (
id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
title VARCHAR(15) NOT NULL CHECK(LENGTH(title) >= 3 AND LENGTH(title) <= 150),
content VARCHAR(255) NOT NULL
);