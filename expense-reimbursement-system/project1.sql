SET sql_mode = ORACLE;

DROP TABLE IF EXISTS reimbursements;
DROP TABLE IF EXISTS reimbursement_status;
DROP TABLE IF EXISTS reimbursement_types;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;

DELIMITER //

CREATE OR REPLACE PROCEDURE add_user
( p_username VARCHAR2
, p_password VARCHAR2
, p_email VARCHAR2
, p_first_name VARCHAR2
, p_last_name VARCHAR2
, p_user_role CHARACTER
) AS
BEGIN
    INSERT INTO users (username, password, email, first_name, last_name, user_role)
        VALUES (p_username, p_password, p_email, p_first_name, p_last_name, p_user_role);
END add_user;
//

CREATE OR REPLACE PROCEDURE add_reimbursement
( p_amount NUMBER
, p_description VARCHAR2
, p_author NUMBER
, p_status NUMBER
, p_type NUMBER
) AS
BEGIN
    INSERT INTO reimbursements (amount, description, author, status, `type`)
        VALUES (p_amount, p_description, p_author, p_status, p_type);
END add_reimbursement;
//

CREATE OR REPLACE PROCEDURE add_reimbursement_with_receipt
( p_amount NUMBER
, p_description VARCHAR2
, p_author NUMBER
, p_status_id NUMBER
, p_type_id NUMBER
, p_receipt BLOB
) AS
BEGIN 
    INSERT INTO reimbursements (amount, description, receipt, author, status_id, type_id)
        VALUES (p_amount, p_description, p_receipt, p_author, p_status_id, p_type_id);
END add_reimbursement_with_receipt;
//

CREATE OR REPLACE PROCEDURE complete_reimbursement
( p_reimbursement_id NUMBER
, p_resolver NUMBER
, p_status_id NUMBER
)
AS
BEGIN
    
    IF (SELECT user_role FROM users WHERE user_id = p_resolver) = 2 THEN
        UPDATE reimbursements
        SET resolver = p_resolver, status_id = p_status_id, resolved_at = NOW()
        WHERE reimbursement_id = p_reimbursement_id;
    END IF;
END complete_reimbursement;
//

DELIMITER ;
/*
CREATE TABLE reimbursement_status
( status_id NUMBER PRIMARY KEY AUTO_INCREMENT
, status VARCHAR2(10) NOT NULL
);
INSERT INTO reimbursement_status (status)
VALUES ('pending'), ('denied'), ('approved');

CREATE TABLE reimbursement_types
( type_id NUMBER PRIMARY KEY AUTO_INCREMENT
, `type` VARCHAR2(10) NOT NULL
);
INSERT INTO reimbursement_types (`type`)
VALUES ('lodging'), ('travel'), ('food'), ('other');

CREATE TABLE user_roles
( role_id CHAR(1) PRIMARY KEY AUTO_INCREMENT
, user_role VARCHAR2(10) NOT NULL
);
INSERT INTO user_roles (user_role)
VALUES ('user'), ('manager');
*/

CREATE TABLE users
( user_id NUMBER PRIMARY KEY AUTO_INCREMENT
, username VARCHAR2(50) UNIQUE NOT NULL
, password VARCHAR2(74) NOT NULL
, email VARCHAR2(250) UNIQUE NOT NULL
, first_name VARCHAR2(40) NOT NULL
, last_name VARCHAR2(40) NOT NULL
, user_role ENUM('USER', 'MANAGER') NOT NULL
);
CALL add_user('username', 'passsessassa', 'email@test.com', 'fn', 'ln', 1);
CALL add_user('username2', 'passsessassa', 'email2@test.com', 'fn', 'ln', 1);
CALL add_user('username3', 'passsessassa', 'email3@test.com', 'fn', 'ln', 2);

CREATE TABLE reimbursements
( reimbursement_id NUMBER PRIMARY KEY AUTO_INCREMENT
, amount NUMBER NOT NULL
, submitted_at TIMESTAMP DEFAULT NOW()
, resolved_at TIMESTAMP
, description VARCHAR2(250) NOT NULL
, receipt BLOB
, author NUMBER NOT NULL
, resolver NUMBER
, status ENUM('PENDING', 'APPROVED', 'DENIED') NOT NULL
, `type` ENUM('LODGING', 'TRAVEL', 'FOOD', 'OTHER') NOT NULL
, CONSTRAINT author_cannot_equal_resolver
	CHECK (author != resolver)
, FOREIGN KEY (author) REFERENCES users(user_id)
, FOREIGN KEY (resolver) REFERENCES users(user_id)
);
CALL add_reimbursement(223.544, 'a description is all I need', 3, 1, 1);
CALL complete_reimbursement(1, 2, 3);


-- DELETE FROM users WHERE user_id = 1;
-- UPDATE reimbursements SET status_id = 2 WHERE reimbursement_id = 1;
-- UPDATE user_roles SET role_id = 3 WHERE user_role = 'manager';

SELECT * FROM users;
SELECT * FROM reimbursements;
