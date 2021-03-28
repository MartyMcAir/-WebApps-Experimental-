CREATE SEQUENCE dept_seq START WITH 1;

------------ T_USER ------------
CREATE TABLE T_USER (
  ID           		NUMBER(32)    PRIMARY KEY NOT NULL,
  USERNAME  VARCHAR2(10)  NOT NULL,
  PASSWORD  VARCHAR2(100)  NOT NULL,
  ROLE  			NUMBER(1)  NOT NULL DEFAULT 0,
  ISAMDIN		NUMBER(1)  NOT NULL DEFAULT 0,
  EMAIL			VARCHAR2(100),
  FULL_NAME	VARCHAR2(100),
  ADDRESS		VARCHAR2(200)
) ;

create trigger TRG_USER_ID
    before insert on T_USER
    for each row
  begin
    select dept_seq.nextval
       into :new.ID
        from dual;
  end;
  
 ---------- COURSE -----------
 CREATE TABLE T_COURSE (
  ID           		NUMBER(32)    PRIMARY KEY NOT NULL,
  COURSE_NAME  VARCHAR2(1000)  NOT NULL
) ;

create trigger TRG_COURSE_ID
    before insert on T_COURSE
    for each row
  begin
    select dept_seq.nextval
       into :new.ID
        from dual;
  end;
  
  ----------- USER_COURSE -------------
CREATE TABLE T_USER_COURSE (
  USER_ID           		NUMBER(32)  NOT NULL,
  COURSE_ID  			NUMBER(32)  NOT NULL
) ;

------------ T_CLASS ------------
 CREATE TABLE T_CLASS (
  ID           		NUMBER(32)    PRIMARY KEY NOT NULL,
  CLASS_NAME  VARCHAR2(1000)  NOT NULL
) ;

create trigger TRG_CLASS_ID
    before insert on T_CLASS
    for each row
  begin
    select dept_seq.nextval
       into :new.ID
        from dual;
  end;
