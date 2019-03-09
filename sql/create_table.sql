/*年级grade*/

drop table if exists `grade`;

create table `grade` (
  `grade_id` int(11) not null auto_increment,
  `grade_number` varchar(40) not null,
  `grade_name` varchar(40) not null,
  primary key (`grade_id`),
  unique (`grade_number`, `grade_name`)
) engine=innodb auto_increment=2 default charset=utf8;

/*学科subject*/

drop table if exists `subject`;

create table `subject` (
  `subject_id` int(11) not null auto_increment,
  `subject_number` varchar(40) not null,
  `subject_name` varchar(40) not null,
  `grade_id` int(11) not null default 0,
  primary key (`subject_id`),
  unique (`subject_number`),
  foreign key (grade_id) references grade(grade_id) on delete cascade on update cascade
) engine=innodb auto_increment=2 default charset=utf8;

/*教师teacher*/

drop table if exists `teacher`;

create table `teacher` (
  `teacher_id` int(11) not null auto_increment,
  `teacher_number` varchar(40) not null,
  `teacher_name` varchar(40) not null,
  `sex` char(1) not null comment '0:男 1:女',
  `born` date not null,
  `home` varchar(100) not null,
  `contact` varchar(40) not null,
  `entry_date` date not null,
  `subject_id` int(11) not null default 0,
  primary key (`teacher_id`),
  unique (`teacher_number`, `teacher_name`, `contact`),
  foreign key (subject_id) references subject(subject_id) on delete cascade on update cascade
) engine=innodb auto_increment=2 default charset=utf8;

/*班级clazz*/

drop table if exists `clazz`;

create table `clazz` (
  `clazz_id` int(11) not null auto_increment,
  `clazz_number` varchar(40) not null,
  `clazz_name` varchar(40) not null,
  `grade_id` int(11) not null default 0,
  `teacher_id` int(11) not null default 0,
  primary key (`clazz_id`),
  unique (`clazz_number`),
  foreign key (grade_id) references grade(grade_id) on delete cascade on update cascade,
  foreign key (teacher_id) references teacher(teacher_id) on delete cascade on update cascade
) engine=innodb auto_increment=2 default charset=utf8;


/*学生student*/

drop table if exists `student`;

create table `student` (
  `student_id` int(11) not null auto_increment,
  `student_number` varchar(40) not null,
  `student_name` varchar(40) not null,
  `sex` char(1) not null comment '0:男 1:女',
  `born` date not null,
  `home` varchar(100) not null,
  `home_name` varchar(40) not null,
  `home_contact` varchar(40) not null,
  `admission_date` date not null,
  `clazz_id` int(11) not null default 0,
  primary key (`student_id`),
  unique (`student_number`, `student_name`, `home_contact`),
  foreign key (clazz_id) references clazz(clazz_id) on delete cascade on update cascade
) engine=innodb auto_increment=2 default charset=utf8;

/*考试test*/

drop table if exists `test`;

create table `test` (
  `test_id` int(11) not null auto_increment,
  `test_number` varchar(40) not null,
  `test_name` varchar(40) not null,
  `subject_id` int(11) not null default 0,
  primary key (`test_id`),
  unique (`test_number`),
  foreign key (subject_id) references subject(subject_id) on delete cascade on update cascade
) engine=innodb auto_increment=2 default charset=utf8;

/*成绩results*/

drop table if exists `results`;

create table `results` (
  `results_id` int(11) not null auto_increment,
  `results_number` varchar(40) not null,
  `test_id` int(11) not null,
  `student_id` int(11) not null default 0,
  `score` int(3) not null,
  primary key (`results_id`),
  unique (`results_number`),
  foreign key (student_id) references student(student_id) on delete cascade on update cascade
) engine=innodb auto_increment=2 default charset=utf8;