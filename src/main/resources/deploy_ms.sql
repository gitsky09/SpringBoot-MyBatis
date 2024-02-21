-- �Ыظ�Ʈw
USE master;
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'deploy')
BEGIN
    CREATE DATABASE deploy;
END
GO

-- �ϥ� deploy ��Ʈw
USE deploy;
GO

-- �Ы� deploy_users ��
IF OBJECT_ID('deploy_users', 'U') IS NULL
BEGIN
    CREATE TABLE deploy_users (
        id INT IDENTITY(1,1) PRIMARY KEY,
        name VARCHAR(30),
        account VARCHAR(40) UNIQUE,
        password VARCHAR(40),
        salt VARCHAR(40),
        phone VARCHAR(20),
        create_time DATETIME,
        create_user VARCHAR(30),
        update_time DATETIME,
        update_user VARCHAR(30)
    );
END
GO

-- ���J deploy_users ���ռƾ�
INSERT INTO deploy_users (name, account) 
VALUES 
('�p��', 'mei@mail.com'),
('�p��', 'wang@mail.com'),
('�p��', 'cai@mail.com'),
('�j��', 'ming@mail.com'),
('�p��', 'gua@mail.com');
SELECT * FROM deploy_users;

-- �Ы� deploy_authority ��
IF OBJECT_ID('deploy_authority', 'U') IS NULL
BEGIN
    CREATE TABLE deploy_authority (
        id INT IDENTITY(1,1) PRIMARY KEY,
        uid INT,
        name VARCHAR(30) UNIQUE,
        create_time DATETIME,
        create_user VARCHAR(30),
        update_time DATETIME,
        update_user VARCHAR(30)
    );
END
GO

-- ���J deploy_authority ���ռƾ�
INSERT INTO deploy_authority (name) VALUES ('�����\��'), ('�s�ե\��') ,('�ϥΪ̥\��');
SELECT * FROM deploy_authority;

-- �Ы� deploy_groups ��
IF OBJECT_ID('deploy_groups', 'U') IS NULL
BEGIN
    CREATE TABLE deploy_groups (
        id INT IDENTITY(1,1) PRIMARY KEY,
        uid INT,
        name VARCHAR(30) UNIQUE,
        create_time DATETIME,
        create_user VARCHAR(30),
        update_time DATETIME,
        update_user VARCHAR(30)
    );
END
GO

-- ���J deploy_groups ���ռƾ�
INSERT INTO deploy_groups (name) VALUES ('Admin�s��'),('�@��s��'),('�X�ȸs��');
SELECT * FROM deploy_groups;

-- �Ы� rel_groups_authority ��
IF OBJECT_ID('rel_groups_authority', 'U') IS NULL
BEGIN
    CREATE TABLE rel_groups_authority (
        id INT IDENTITY(1,1) PRIMARY KEY,
        group_rid INT NOT NULL,
        user_rid INT NOT NULL,
        authority_rid INT NOT NULL,
        group_read BIT DEFAULT 0,
        group_update BIT DEFAULT 0,
        group_insert BIT DEFAULT 0,
        group_delete BIT DEFAULT 0,
        create_time DATETIME,
        create_user VARCHAR(30),
        update_time DATETIME,
        update_user VARCHAR(30)
    );
END
GO

-- �]�mAdmin�s���v��
INSERT INTO rel_groups_authority (group_rid, user_rid, authority_rid, group_read, group_update, group_insert, group_delete)
VALUES 
(1,1,1,1,1,1,1),
(1,1,2,1,1,1,1),
(1,1,3,1,1,1,1),
(1,3,1,1,1,1,1),
(1,3,2,1,1,1,1),
(1,3,3,1,1,1,1);

-- �]�m�@��s���v��
INSERT INTO rel_groups_authority (group_rid, user_rid, authority_rid, group_read)
VALUES 
(2,2,1,1),
(2,2,2,1);

-- �]�m�X�ȸs���v��
INSERT INTO rel_groups_authority (group_rid, user_rid, authority_rid, group_read)
VALUES 
(3,4,1,1),
(3,5,1,1);

-- �d�ߨ㦳 [�s��] �\�઺ [�ϥΪ�] �y���s��
SELECT DISTINCT user_rid, group_rid
FROM rel_groups_authority
WHERE group_read = 1;
