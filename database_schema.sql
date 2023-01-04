-- DDL for student.student_login:-

CREATE TABLE `student_login` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id_UNIQUE` (`user_id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


-- DDL for student.student_report:-

CREATE TABLE `student_report` (
  `id` int NOT NULL AUTO_INCREMENT,
  `lname` varchar(45) DEFAULT NULL,
  `fname` varchar(45) NOT NULL,
  `dob` datetime NOT NULL,
  `ph_no` bigint NOT NULL,
  `email` varchar(45) NOT NULL,
  `qual_type` varchar(45) NOT NULL,
  `special` varchar(45) DEFAULT NULL,
  `yop` year NOT NULL,
  `institute` varchar(80) NOT NULL,
  `pass_marks` int NOT NULL,
  `obtain_marks` int NOT NULL,
  `total_marks` int NOT NULL,
  `exp` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `ph_no_UNIQUE` (`ph_no`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci