DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS news;

CREATE TABLE users (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    email varchar(255) UNIQUE NOT NULL,
    email_verified_at datetime NULL DEFAULT NULL,
    password varchar(255) NOT NULL,
    remember_token varchar(100) DEFAULT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NULL DEFAULT NULL
);

CREATE TABLE news (
    id bigint PRIMARY KEY AUTO_INCREMENT,
    title varchar(255) NOT NULL,
    body TEXT NOT NULL,
    image_path varchar(255) DEFAULT NULL,
    created_at datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at datetime NULL DEFAULT NULL
);
