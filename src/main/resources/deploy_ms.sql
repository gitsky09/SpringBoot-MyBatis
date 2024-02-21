-- 創建資料庫
USE master;
IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'deploy')
BEGIN
    CREATE DATABASE deploy;
END
GO

-- 使用 deploy 資料庫
USE deploy;
GO

-- 創建 deploy_users 表
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

-- 插入 deploy_users 測試數據
INSERT INTO deploy_users (name, account) 
VALUES 
('小美', 'mei@mail.com'),
('小王', 'wang@mail.com'),
('小菜', 'cai@mail.com'),
('大明', 'ming@mail.com'),
('小瓜', 'gua@mail.com');
SELECT * FROM deploy_users;

-- 創建 deploy_authority 表
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

-- 插入 deploy_authority 測試數據
INSERT INTO deploy_authority (name) VALUES ('首頁功能'), ('群組功能') ,('使用者功能');
SELECT * FROM deploy_authority;

-- 創建 deploy_groups 表
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

-- 插入 deploy_groups 測試數據
INSERT INTO deploy_groups (name) VALUES ('Admin群組'),('一般群組'),('訪客群組');
SELECT * FROM deploy_groups;

-- 創建 rel_groups_authority 表
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

-- 設置Admin群組權限
INSERT INTO rel_groups_authority (group_rid, user_rid, authority_rid, group_read, group_update, group_insert, group_delete)
VALUES 
(1,1,1,1,1,1,1),
(1,1,2,1,1,1,1),
(1,1,3,1,1,1,1),
(1,3,1,1,1,1,1),
(1,3,2,1,1,1,1),
(1,3,3,1,1,1,1);

-- 設置一般群組權限
INSERT INTO rel_groups_authority (group_rid, user_rid, authority_rid, group_read)
VALUES 
(2,2,1,1),
(2,2,2,1);

-- 設置訪客群組權限
INSERT INTO rel_groups_authority (group_rid, user_rid, authority_rid, group_read)
VALUES 
(3,4,1,1),
(3,5,1,1);

-- 查詢具有 [群組] 功能的 [使用者] 流水編號
SELECT DISTINCT user_rid, group_rid
FROM rel_groups_authority
WHERE group_read = 1;
