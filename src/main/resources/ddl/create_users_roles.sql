DROP TABLE IF EXISTS users_roles;
CREATE TABLE users_roles (
        id INTEGER IDENTITY PRIMARY KEY,
        user_id INTEGER NOT NULL,
        role_name VARCHAR(64) NOT NULL,
        CONSTRAINT role_name UNIQUE(user_id,role_name)
        );