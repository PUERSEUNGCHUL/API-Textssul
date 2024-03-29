

DROP TABLE TB_COMMENT;
DROP TABLE TMP_ARTICLE_LIKE;
DROP TABLE TB_ARTICLE;
DROP TABLE TB_MEMBER;
CREATE TABLE TB_MEMBER(
	UID int AUTO_INCREMENT,
	EMAIL varchar(255),
	NICKNAME varchar(16),
	PASSWORD varchar(255),
	ROLE int,
	CREATE_UID int,
	CREATE_DT datetime,
	CREATE_IP varchar(15),
	UPDATE_UID int,
	UPDATE_DT datetime,
	UPDATE_IP varchar(15),

	PRIMARY KEY (UID)
);
CREATE TABLE TB_ARTICLE(
	ARTICLE_ID int AUTO_INCREMENT,
	ARTICLE_TYPE_ID tinyint,
	ARTICLE_CATEGORY_ID tinyint,
	ARTICLE_TITLE varchar(255),
	CONTENT longtext,
	VIEW_CNT int DEFAULT 0,
	CREATE_UID int,
	CREATE_DT datetime,
	CREATE_IP varchar(15),
	UPDATE_UID int,
	UPDATE_DT datetime,
	UPDATE_IP varchar(15),
	PRIMARY KEY (ARTICLE_ID)
);
CREATE TABLE TB_COMMENT(
	COMMENT_ID int AUTO_INCREMENT,
	ARTICLE_ID int,
	COMMENT longtext,
	CREATE_UID int,
	CREATE_DT datetime,
	CREATE_IP varchar(15),
	UPDATE_UID int,
	UPDATE_DT datetime,
	UPDATE_IP varchar(15),
	PRIMARY KEY (COMMENT_ID)
);
CREATE TABLE TMP_ARTICLE_LIKE(
	ARTICLE_ID int,
	CREATE_UID int,
	CREATE_DT datetime,
	CREATE_IP varchar(15),
	UPDATE_UID int,
	UPDATE_DT datetime,
	UPDATE_IP varchar(15),
	PRIMARY KEY (ARTICLE_ID, CREATE_UID)
);
CREATE TABLE TMP_COMMENT_LIKE(
     COMMENT_ID int,
     CREATE_UID int,
     CREATE_DT datetime,
     CREATE_IP varchar(15),
     UPDATE_UID int,
     UPDATE_DT datetime,
     UPDATE_IP varchar(15),
 PRIMARY KEY (COMMENT_ID, CREATE_UID)
);


CREATE TABLE TB_IMAGE(
     IMAGE_ID INT AUTO_INCREMENT,
     ARTICLE_ID INT,
     SORT_ORDER INT,
     IMAGE_PATH VARCHAR(100),
     IMAGE_NM_ORIGIN VARCHAR(200),
     IMAGE_NM VARCHAR(13),
     IMAGE_EXT VARCHAR(4),
     IMAGE_SIZE INT,
     CREATE_UID int,
     CREATE_DT datetime,
     CREATE_IP varchar(15),
     UPDATE_UID int,
     UPDATE_DT datetime,
     UPDATE_IP varchar(15),
     PRIMARY KEY (IMAGE_ID)

);