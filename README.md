# Shinhan-SimpleBoard

차민건 - ListController, FrontController

안세현 - UpdateController

윤민혁 - DeleteController

천희찬 - WriterController

변수명은 snake 표기법

### DB 테이블

bno(number), writer(varchar2 50), writedate(date), title(varchar2 50), contents(varchar2 200)

create table SimpleBoard(
    bno number primary key,
    writer varchar2(50),
    writedate date,
    title varchar2(50),
    contents varchar2(200)
);

create sequence bno;
