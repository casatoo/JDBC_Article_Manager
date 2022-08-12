DROP DATABASE IF EXISTS article_manager;

CREATE DATABASE article_manager;

USE article_manager;

DROP TABLE IF EXISTS article;

CREATE TABLE article(
id INT(10) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
title VARCHAR(100) NOT NULL,
`body` TEXT NOT NULL);

DESC article;

INSERT INTO article SET 
regDate = NOW(),
updateDate = NOW(), 
title = CONCAT('제목1',RAND()),
`body`=CONCAT('내용',RAND());

SELECT * FROM article;

UPDATE article SET 
title = '제목',
`body` = '내용'
WHERE id = 3;

SELECT COUNT(*) FROM article WHERE id = 9;

DROP TABLE IF EXISTS`member`;

CREATE TABLE `member`(
id INT UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
regDate DATETIME NOT NULL,
updateDate DATETIME NOT NULL,
loginId VARCHAR(100) NOT NULL UNIQUE,
loginPw VARCHAR(100) NOT NULL,
`name` VARCHAR(100) NOT NULL UNIQUE);

INSERT INTO `member`(regDate,updateDate,loginId,loginPw,`name`)VALUE
(NOW(),NOW(),'id1','pw1','홍길동'),
(NOW(),NOW(),'id2','pw2','김철수'),
(NOW(),NOW(),'id3','pw3','김영희');

INSERT INTO `member` SET 
regDate = NOW(),
updateDate = NOW(),
loginId = 'id4',
loginPw = 'pw4',
`name` = '아무개';

SELECT * FROM `member`;

SELECT COUNT(*) FROM `member` WHERE loginId= 'id1';
SELECT COUNT(*) FROM `member` WHERE `name` LIKE '홍길동%';

