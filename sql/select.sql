/*年级grade*/
select `grade_id`, `grade_number`, `grade_name` from `grade`;

/*学科subject*/
select `subject_id`, `subject_number`, `subject_name`, `grade_id` from `subject`;

/*教师ʦteacher*/
select `teacher_id`, `teacher_number`, `teacher_name`, `sex`, `born`, `home`, `contact`, `entry_date`, `subject_id` from `teacher`;

/*班级clazz*/
select `clazz_id`, `clazz_number`, `clazz_name`, `grade_id`, `teacher_id` from `clazz`;

/*学生student*/
select `student_id`, `student_number`, `student_name`, `sex`, `born`, `home`, `home_name`, `home_contact`, `admission_date`, `clazz_id` from `student`;

/*考试test*/
select `test_id`, `test_number`, `test_name`, `subject_id` from `test`;

/*成绩results*/
select `results_id`, `results_number`, `test_id`, `student_id`, `score` from `results`;
