
/*年级grade_t*/
insert  into `grade`
(`grade_number`, `grade_name`)
values
('001','高一'),
('002','高二'),
('003','高三');

/*学科subject*/
insert  into `subject`
(`subject_number`, `subject_name`, `grade_id`)
values
('001', '语文', 2),
('002', '数学', 2),
('003', '英语', 2),
('004', '语文', 3),
('005', '数学', 3),
('006', '英语', 3),
('007', '语文', 4),
('008', '数学', 4),
('009', '英语', 4);

/*教师teacher*/
insert  into `teacher`
(`teacher_number`, `teacher_name`, `sex`, `born`, `home`, `contact`, `entry_date`, `subject_id`)
values
('2019001','小老弟','男',now(),'上海','13800000001',now(),6),
('2019002','老大哥','男',now(),'北京','13800000002',now(),3);

/*班级clazz*/
insert  into `clazz`
(`clazz_number`, `clazz_name`, `grade_id`, `teacher_id`)
values
('2019001','1班',3,2),
('2019002','2班',3,3);

/*学生student*/
insert  into `student`
(`student_number`, `student_name`, `sex`, `born`, `home`, `home_name`, `home_contact`, `admission_date`, `clazz_id`)
values
('201910101','张三','女',now(),'广东广州','张三老爸','13800000000',now(),2),
('201910102','李四','男',now(),'广东深圳','李四老爹','13800000005',now(),2);

/*考试test*/
insert  into `test`
(`test_number`, `test_name`, `subject_id`)
values
('2019012701', '2018下学期期初', 5),
('2019012702', '2018下学期期中', 5);

/*成绩results*/
insert  into `results`
(`results_number`, `test_id`, `student_id`, `score`)
values
('20190127010001',2,2,80),
('20190127010002',2,3,85);
