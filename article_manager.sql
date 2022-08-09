DROP DATABASE IF EXISTS article_manager;

CREATE DATABASE article_manager;

USE article_manager;

CREATE TABLE article(
id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
title VARCHAR(100) NOT NULL,
`body` TEXT NOT NULL);

DESC article;

INSERT INTO article SET regDate = NOW(),updateDate = NOW(), title = CONCAT('제목1',RAND()),`body`=CONCAT('내용',RAND());

SELECT * FROM article;

UPDATE article SET 
title = '제목',
`body` = '내용'
WHERE id = 3;