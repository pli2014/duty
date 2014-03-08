-- ==============================================================
--  mysql
-- ==============================================================

CREATE DATABASE /*!32312 IF NOT EXISTS*/`duty` /*!40100 DEFAULT CHARACTER SET utf8 */;
use duty;
-- ==============================================================
--  Table: c_duty(考勤表)
-- ==============================================================

create table c_duty
(
   c_user_id        VARCHAR(20)     not null,
   c_on_duty        DATETIME,
   c_off_duty       DATETIME,
   c_time           DATETIME,
   c_department_id      VARCHAR(10),
   c_task       VARCHAR(40),
   c_finish_id      VARCHAR(10),
   c_late       SMALLINT,
   c_leave      SMALLINT,
   c_message        VARCHAR(100),
   c_approve        VARCHAR(20)
);


-- ==============================================================
--  Table: c_ employee(员工表)
-- ==============================================================

create table c_employee
(
   c_user_id        VARCHAR(20)     not null,
   c_user_name      VARCHAR(20),
   c_password       VARCHAR(20),
   c_sex        VARCHAR(4),
   c_department_id  VARCHAR(10),
   c_address        VARCHAR(100),
   c_telephone          VARCHAR(20),    
   c_msn        VARCHAR(40),
   c_role_id            VARCHAR(10),
   c_admin_flag         VARCHAR(2)
);


-- ==============================================================
--  Table: c_role(角色表)
-- ==============================================================

create table c_role
(
   c_role_id            VARCHAR(10)     not null,
   c_role_name          VARCHAR(20)
);


-- ==============================================================
--  Table: c_department(部门表)
-- ==============================================================
 
create table c_department
(
   c_department_id      VARCHAR(10)     not null,
   c_department_name    VARCHAR(20)
);


-- ==============================================================
--  Table: c_statistic(统计表)
-- ==============================================================

create table c_statistic
(
   c_user_id        VARCHAR(20)     not null,
   c_late       SMALLINT,
   c_leave      SMALLINT,
   c_time       DATETIME
);


-- ==============================================================
--  Table: c_finish(完成情况)
-- ==============================================================

create table c_finish
(
   c_finish_id      VARCHAR(10)     not null,
   c_content        VARCHAR(20)
);
