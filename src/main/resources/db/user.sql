create user pairings
    identified by pairings
    default tablespace users
    temporary tablespace temp
    quota unlimited on users;

GRANT create session TO pairings;
GRANT create table TO pairings;
GRANT create view TO pairings;
GRANT create any trigger TO pairings;
GRANT create any procedure TO pairings;
GRANT create sequence TO pairings;
GRANT create synonym TO pairings;
