use duty;

-- ==============================================================
--  Table: c_role(角色表)
-- ==============================================================
insert into c_role(c_role_id,c_role_name) values('0','超级管理员');
insert into c_role(c_role_id,c_role_name) values('1','员工');
insert into c_role(c_role_id,c_role_name) values('2','部门经理');
insert into c_role(c_role_id,c_role_name) values('3','财务经理');
insert into c_role(c_role_id,c_role_name) values('4','人事经理');
insert into c_role(c_role_id,c_role_name) values('5','总经理');
insert into c_role(c_role_id,c_role_name) values('6','考勤员');


-- ==============================================================
--  Table: c_department(部门表)
-- ==============================================================

insert into c_department(c_department_id,c_department_name) values('1','市场部');
insert into c_department(c_department_id,c_department_name) values('2','研发部');
insert into c_department(c_department_id,c_department_name) values('3','推广部');
insert into c_department(c_department_id,c_department_name) values('4','技术部');
insert into c_department(c_department_id,c_department_name) values('5','人事部');
insert into c_department(c_department_id,c_department_name) values('6','财务部');

-- ==============================================================
--  Table: c_finish(完成情况表)
-- ==============================================================
insert into c_finish(c_finish_id,c_content) values('0','未完成');
insert into c_finish(c_finish_id,c_content) values('1','完  成');


-- ==============================================================
--  Table: c_duty(考勤表)
-- ==============================================================

insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000010','2007-12-01 08:50:00','2007-12-01 17:35:00','2007-12-01 17:32:03','1','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000011','2007-12-02 08:50:00','2007-12-02 17:35:00','2007-12-02 17:32:03','1','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000012','2007-12-03 08:50:00','2007-12-03 17:30:00','2007-12-03 17:32:03','1','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000013','2007-12-04 08:50:00','2007-12-04 17:32:00','2007-12-04 17:32:03','1','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000014','2007-12-05 08:50:00','2007-12-05 17:30:00','2007-12-05 17:32:03','1','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000015','2007-12-06 08:50:00','2007-12-06 17:35:00','2007-12-06 17:32:03','2','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000016','2007-12-07 08:50:00','2007-12-07 17:36:00','2007-12-07 17:32:03','2','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000017','2007-12-08 08:50:00','2007-12-08 17:37:00','2007-12-08 17:32:03','2','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000018','2007-12-09 08:50:00','2007-12-09 17:38:00','2007-12-09 17:32:03','2','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000019','2007-12-10 08:50:00','2007-12-10 17:40:00','2007-12-10 17:32:03','2','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000020','2007-12-11 08:50:00','2007-12-11 17:50:00','2007-12-11 17:32:03','2','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000021','2007-12-12 08:50:00','2007-12-12 17:50:00','2007-12-12 17:32:03','3','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000022','2007-12-13 08:50:00','2007-12-13 17:30:00','2007-12-13 17:32:03','3','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000023','2007-12-14 08:50:00','2007-12-14 17:34:00','2007-12-14 17:32:03','3','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000024','2007-12-15 08:50:00','2007-12-15 17:36:00','2007-12-15 17:32:03','3','任务1','1','0','0');
insert into c_duty(c_user_id,c_on_duty,c_off_duty,c_time,c_department_id,c_task,c_finish_id,c_late,c_leave) values('0000000000025','2007-12-16 08:50:00','2007-12-16 17:37:00','2007-12-16 17:32:03','3','任务1','1','0','0');

-- ==============================================================
--  Table: c_ employee(员工表)
-- ==============================================================

insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000000','admin','123','男','','北京市','12345678','s1@hotmail.com','0','Y');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000001','总经理A','123','男','','北京市','12345678','s1@hotmail.com','5','Y');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000002','人事经理A','123','男','','北京市','12345678','s1@hotmail.com','4','Y');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000003','财务经理A','123','男','','北京市','12345678','s1@hotmail.com','3','Y');

insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000004','部门经理A','123','男','1','北京市','12345678','s1@hotmail.com','2','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000010','员工01','123','男','1','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000011','员工02','123','男','1','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000012','员工03','123','男','1','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000013','员工04','123','男','1','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000014','员工05','123','男','1','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000015','员工06','123','男','2','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000016','员工07','123','男','2','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000017','员工08','123','男','2','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000018','员工09','123','男','2','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000019','员工10','123','男','2','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000020','员工11','123','男','2','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000021','员工12','123','男','3','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000022','员工13','123','男','3','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000023','员工14','123','男','3','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000024','员工15','123','男','3','北京市','12345678','s1@hotmail.com','6','N');
insert into c_employee(c_user_id,c_user_name,c_password,c_sex,c_department_id,c_address,c_telephone,c_msn,c_role_id,c_admin_flag) values('0000000000025','员工16','123','男','3','北京市','12345678','s1@hotmail.com','6','N');