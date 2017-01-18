DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id INTEGER IDENTITY PRIMARY KEY,
  username VARCHAR(64),
  password VARCHAR(64),
  enabled TINYINT,
  CONSTRAINT username UNIQUE(username)
);

