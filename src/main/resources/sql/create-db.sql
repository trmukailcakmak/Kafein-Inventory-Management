
CREATE TABLE WAREHOUSE
(
    ID               INTEGER      NOT NULL,
    NAME             VARCHAR(32)  NOT NULL,
    AREA             VARCHAR(32)  NOT NULL,
    CITY             VARCHAR(32)  NOT NULL,
    DISTRICT         VARCHAR(32)  NOT NULL,
    ADDRESS          VARCHAR(128) NOT NULL,
    DESC             VARCHAR(64)  NOT NULL,
    DELETED          BOOL NOT NULL  DEFAULT false,
    CREATED_USER        VARCHAR(32)  NOT NULL,
    UPDATE_USER VARCHAR(32)  NOT NULL,
    CREATE_DATE      DATE,
    UPDATE_DATE      DATE,
    PRIMARY KEY (ID)
);

CREATE TABLE WAREHOUSE_CATALOG_RELEATED
(
    ID               INTEGER     NOT NULL,
    WAREHOUSE_ID     VARCHAR(32) NOT NULL,
    CATALOG_ID       VARCHAR(32),
    DELETED          BOOL NOT NULL  DEFAULT false,
    CREATED_USER        VARCHAR(32)  NOT NULL,
    UPDATE_USER VARCHAR(32)  NOT NULL,
    CREATE_DATE      DATE,
    UPDATE_DATE      DATE,
    PRIMARY KEY (ID)
);

CREATE TABLE CATALOG
(
    ID               INTEGER     NOT NULL,
    NAME             VARCHAR(32) NOT NULL,
    DESC             VARCHAR(32),
    DELETED          BOOL NOT NULL  DEFAULT false,
    CREATED_USER        VARCHAR(32)  NOT NULL,
    UPDATE_USER VARCHAR(32)  NOT NULL,
    CREATE_DATE      DATE,
    UPDATE_DATE      DATE,
    PRIMARY KEY (ID)
);
CREATE TABLE PRODUCT
(
    ID               INTEGER     NOT NULL,
    NAME             VARCHAR(32) NOT NULL,
    DESC             VARCHAR(32),
    WAREHOUSE_ID     INTEGER     NOT NULL,
    CATALOG_ID       INTEGER     NOT NULL,
    STOCK_SIZE       INTEGER     NOT NULL,
    STOCK_DOWN_LIMIT INTEGER     NOT NULL,
    DELETED          BOOL NOT NULL  DEFAULT false,
    CREATED_USER        VARCHAR(32)  NOT NULL,
    UPDATE_USER VARCHAR(32)  NOT NULL,
    CREATE_DATE      DATE,
    UPDATE_DATE      DATE,
    PRIMARY KEY (ID)
);

CREATE  TABLE HISTORY
(
    ID               INTEGER     NOT NULL,
    ActionName             VARCHAR(32) NOT NULL,
    DESC             VARCHAR(32),
    EFECTED_ID INTEGER NOT NULL,
    RELATED_TABLE VARCHAR(32) NOT NULL,
    DELETED          BOOL NOT NULL  DEFAULT false,
    CREATED_USER        VARCHAR(32)  NOT NULL,
    UPDATE_USER VARCHAR(32)  NOT NULL,
    CREATE_DATE      DATE,
    UPDATE_DATE      DATE,
    PRIMARY KEY (ID)
);

CREATE SEQUENCE CATALOG_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999999999999
    NOCYCLE ;


CREATE SEQUENCE PRODUCT_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999999999999
    NOCYCLE ;

CREATE  SEQUENCE WAREHOUSE_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999999999999
    NOCYCLE ;

CREATE  SEQUENCE HISTORY_SEQ
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    MAXVALUE 999999999999999
    NOCYCLE ;