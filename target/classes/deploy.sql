/*
DROP DATABASE IF EXISTS deploy;
CREATE DATABASE deploy;
*/

USE deploy;

DROP TABLE IF EXISTS deploy_users;
CREATE TABLE deploy_users (

id INT AUTO_INCREMENT COMMENT '流水編號',
name VARCHARACTER(30)  COMMENT '使用者名稱',
account varchar(40) COMMENT '帳號',
password varchar(40) COMMENT '密碼',
salt varchar(40) COMMENT 'Salt',
phone varchar(20) COMMENT '電話',

create_time TIMESTAMP  COMMENT '建立日期',
create_user VARCHARACTER(30)  COMMENT '建立者',
update_time TIMESTAMP  COMMENT '更新日期',
update_user VARCHARACTER(30)  COMMENT '更新者',
PRIMARY KEY (id),
UNIQUE KEY `account_unique` (account)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='使用者' AUTO_INCREMENT=1;

#輸入測試資料。不等同真實系統運作
INSERT deploy_users(name)
VALUE ('小美'),('小王'),('小菜'),('大明'),('小瓜');
SELECT* FROM deploy_users;


DROP TABLE IF EXISTS deploy_authority;
CREATE TABLE deploy_authority (

id INT AUTO_INCREMENT COMMENT '流水編號',
uid INT COMMENT '使用者編號',
name VARCHARACTER(30)  COMMENT '功能名稱',
create_time TIMESTAMP  COMMENT '建立日期',
create_user VARCHARACTER(30)  COMMENT '建立者',
update_time TIMESTAMP  COMMENT '更新日期',
update_user VARCHARACTER(30)  COMMENT '更新者',
PRIMARY KEY (id),
UNIQUE KEY `authority_name_unique` (name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='權限' AUTO_INCREMENT=1;

#輸入測試資料。不等同真實系統運作
INSERT deploy_authority(name)
VALUE ('首頁功能'), ('群組功能') ,('使用者功能') ;
SELECT * FROM deploy_authority;



DROP TABLE IF EXISTS deploy_groups;
CREATE TABLE deploy_groups (

id INT AUTO_INCREMENT COMMENT '流水編號',
uid INT COMMENT '使用者編號',
name VARCHARACTER(30)  COMMENT '群組名稱',
create_time TIMESTAMP  COMMENT '建立日期',
create_user VARCHARACTER(30)  COMMENT '建立者',
update_time TIMESTAMP  COMMENT '更新日期',
update_user VARCHARACTER(30)  COMMENT '更新者',
PRIMARY KEY (id),
UNIQUE KEY `group_name_unique` (name)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='群組' AUTO_INCREMENT=1;

#輸入測試資料。不等同真實系統運作
INSERT deploy_groups(name)
VALUE ('Admin群組'),('一般群組'),('訪客群組');
SELECT* FROM deploy_groups;

#多對多
DROP TABLE IF EXISTS rel_groups_authority;
CREATE TABLE rel_groups_authority (

id INT AUTO_INCREMENT COMMENT '流水編號',
group_rid int not null COMMENT '群組參照編號',
user_rid int not null COMMENT '使用者參照編號',
authority_rid int not null COMMENT '權限參照編號',
group_read bool default false COMMENT '讀取權限',
group_update bool default false COMMENT '修改權限',
group_insert bool default false COMMENT '新增權限',
group_delete bool default false COMMENT '刪除權限',
create_time TIMESTAMP  COMMENT '建立日期',
create_user VARCHARACTER(30)  COMMENT '建立者',
update_time TIMESTAMP  COMMENT '更新日期',
update_user VARCHARACTER(30)  COMMENT '更新者',
PRIMARY KEY (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='關聯表_群組_權限' AUTO_INCREMENT=1;

#設定Admin群組權限
INSERT rel_groups_authority(group_rid,user_rid,authority_rid,group_read,group_update,group_insert,group_delete)
VALUE (1,1,1,true,true,true,true),(1,1,2,true,true,true,true),(1,1,3,true,true,true,true),(1,3,1,true,true,true,true),(1,3,2,true,true,true,true),(1,3,3,true,true,true,true);
SELECT * FROM rel_groups_authority WHERE group_rid = 1;

#設定一般群組權限
INSERT rel_groups_authority(group_rid,user_rid,authority_rid,group_read)
VALUE (2,2,1,true);
INSERT rel_groups_authority(group_rid,user_rid,authority_rid,group_read,group_update)
VALUE (2,2,2,true,true);
SELECT * FROM rel_groups_authority WHERE group_rid = 2;

#設定訪客群組權限
INSERT rel_groups_authority(group_rid,user_rid,authority_rid,group_read)
VALUE (3,4,1,true),(3,5,1,true);
SELECT * FROM rel_groups_authority WHERE group_rid = 3;


#將有 ([改] 的 [群組]) 功能的[使用者] SQL 查詢出來 
#以此關聯表設計，可明確記錄新增及修改的流水編號 及 將各表不必要的資料與運算邏輯分出來
SELECT rel_groups_authority.user_rid,rel_groups_authority.group_rid
FROM rel_groups_authority 
WHERE rel_groups_authority.group_read = true;