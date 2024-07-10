-- MySQL dump 10.13  Distrib 8.0.37, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: exam
-- ------------------------------------------------------
-- Server version	8.0.37

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `COURSE` varchar(30) NOT NULL,
  PRIMARY KEY (`COURSE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('Java'),('Mysql');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `options`
--

DROP TABLE IF EXISTS `options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `options` (
  `option` varchar(1) NOT NULL,
  PRIMARY KEY (`option`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `options`
--

LOCK TABLES `options` WRITE;
/*!40000 ALTER TABLE `options` DISABLE KEYS */;
INSERT INTO `options` VALUES ('A'),('B'),('C'),('D');
/*!40000 ALTER TABLE `options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `question` (
  `COURSE` varchar(100) NOT NULL,
  `QNO` int NOT NULL,
  `QNAME` varchar(500) DEFAULT NULL,
  `ANS1` varchar(225) DEFAULT NULL,
  `ANS2` varchar(225) DEFAULT NULL,
  `ANS3` varchar(225) DEFAULT NULL,
  `ANS4` varchar(225) DEFAULT NULL,
  `ANSWER` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`COURSE`,`QNO`),
  KEY `question_options_option_fk` (`ANSWER`),
  CONSTRAINT `question_course_COURSE_fk` FOREIGN KEY (`COURSE`) REFERENCES `course` (`COURSE`),
  CONSTRAINT `question_options_option_fk` FOREIGN KEY (`ANSWER`) REFERENCES `options` (`option`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES ('Java',1,'Which of the following command is used to reconnect the database?','Connect','delimiter','quit','status','A'),('Java',2,'Which one of the following component is used for planning the execution of query processing?','Query parser','query preprocessor','query optimizer','execution engine','C'),('Java',3,'Which of the following given command is used to find out the details of the database?','Connect','delimiter','quit','status','D'),('Java',4,'Which data type only allows characters to be entered?','int','double','varchar','date','C'),('Java',5,'Jack, a database developer no longer wants to keep the table in the database, Identify the right SQL command to enable him remove the table from the database?','truncate','alter','insert','drop','D'),('Java',6,'Which of the following command is use to add column table?','truncate','alter','insert','drop','B'),('Java',7,'A student named \'Tom\' has just left the school, hence, jack, a database administrator need to ensure that tom\'s details are removed from the student table. Identify the correct SQL statement to enable jack remove tom\'s data from the student table.','delete table student name=\'Tom\'','delete from student where student_name=\'Tom\'','delete student where name=\'Tom\'','delete \'Tom\'','B'),('Java',8,'What will be output of following query ASCII(\'ABC\')?','Ascii value of ABC letters','Ascii value of letter B','Ascii value of letter A','Ascii value of letter C','C'),('Java',9,'Which one of the following operator is used to eliminate the certain rows from group by results?','Group by','Order by','Having','Where','C'),('Java',10,'John Manager, want to display the first name and last name of employee together Help him to find out string function is used to join two strings','Substring','Reverse','Left','Concat','D'),('Java',11,'Mike, an accountant would like to display details of maximum salary of employees for each department. Identify the correct SQL Statements to display the same.','Select max(salary) of employee group by department','Select max(salary) from employee group by department','Select salary from employee','Select salary from employee group by department','B'),('Java',12,'The clause that filters JOIN results is called _________','WHERE','SORT','GROUP','GROUP BY','A'),('Java',13,'In inner join, result is produced by matching rows in one table with rows in another table.','true','false','Not complete true','None of these','A'),('Java',14,'In which join all the rows from the left table appear in the output and matching of right table appear?','Inner Join','Natural Join','Left Join','Right Join','C'),('Java',15,'Which of these operators perform similar operations like ALL and ANY?','SOME','MANY','SELECT','GROUP','A'),('Java',16,'Jack, the database developer has created view on table. Now he wants display the view. Which command he should use to display the view?','Create','Update','Select','DROP','C'),('Java',17,'Jack ,the database developer has created product table. He has also created indexes in table. Which command he should used to show indexes','Show','Select','drop','All of these','A'),('Java',18,'Which index is used to improve the speed of searches made on string value________','Unique Index','Regular Index','Full-text','Primary Key Index','C'),('Java',19,'A view can refer to multiple tables via __________','UNION','JOIN','GROUP','SELECT','B'),('Java',20,'Which one of the following is also known as a virtual table?','SCHEMA','DATABASE','JOIN','VIEW','D'),('Java',21,'Jack ,the database developer has created product table. Which contain Product_id,Product_Name,Product_Description. Help the developer to create primary key index on Product Table.','alter index product add index(product_id);','alter table product add primary key(product_id);','add index product (product_id);','None of these','B'),('Java',22,'Which command is used to assign value to variable in Procedures?','Create','Variable','Declare','Set','D'),('Java',23,'Which command is used to call procedure?','select','connect','call','None of these','C'),('Java',24,'Jack, the database developer has created table student. Now he wants to declare a trigger to make sure that in every inserted record, student age must be greater than 0. Which command he should use to declare trigger','create','show','declare','connect','A'),('Java',25,'In compound statement declaration start and end with______','Start End Block','Begin End Block','Open Bracket and Close Bracket','None of these','B'),('Java',26,'Jack, the database developer has created two tables student. Now he wants display students using procedure. Help him to identify correct procedure.','delimiter // create procedure proc1() begin select from student','delimiter // create procedure proc1() begin select *from student end// delimiter ;','begin select *from student end//','None of these','B'),('Java',27,'A _________ consists of a sequence of query and/or update statements.','Transaction','Commit','Rollback','Flashback','A'),('Java',28,'For which of the following are triggers not supported?','delete','update','insert','views','D'),('Java',29,'In order to undo the work of transaction after last commit which one should be used?','View','Commit','Rollback','Flashback','D'),('Java',30,'What is abc in the following MySQL statement? CREATE TRIGGER abc (...) (...) ON def FOR EACH ROW ghi;','trigger name','table name','trigger statement','update statement','A'),('Java',31,'Jack, database developer has created table of Student. He wants to export the data into xls file. Identify correct SQL Statement','select *from student into outfile \'D:\\student.xls\';','select *from student into \'D:\\student.xls\';','select *from student = \'D:\\student.xls\';','None of these','A'),('Java',32,'Which one of the following options can be used to execute SQL statements and MySQL commands from within a text file without invoking the command line client?','SOURCE','LOAD DATA INFILE','Mysqlimport','DUMPFILE','A'),('Java',33,'The index in which column value can contain duplicate and null is called as_____','Unique Index','Regular Index','Full-text','Primary Key Index','B'),('Java',34,'What is xyz in the following MySQL statement? CREATE VIEW xyz AS SELECT a, b FROM t;','Table','Column','View','Database','C'),('Java',35,'Which command is used to declare variable in Procedure?','Create','Variable','Declare','Set','C'),('Java',36,'Which command is used to assign value to variable in Procedures?','Create','Variable','Declare','Set','D'),('Java',37,'Jack, the database developer has created table student. Now he wants to remove a procedure. Which command he should use to this task','REMOVE','DELETE','CLEAR','DROP','D'),('Java',38,'Which of following are characteristics of stored procedure?','Deterministic','Contain SQL and Non SQL Statement','Read SQL Data','All of These','D'),('Java',39,'Consider the following Statements: DELIMITER// Create Procedure getAvgMark(OUT mark DOUBLE) BEGIN Select Avg(marks) into mark from student; END // DELIMITER; Which one of the following statements would you use to execute the preceding procedure?','CALL getAvgMark(a); Select a;','DECLARE a; CALL getAvgMark(a); SELECT a;','CALL getAvgMark(@a); SELECT @a;','DECLARE @a; CALL getAvgMark(@a); SELECT @a;','C'),('Java',40,'Jack, the database developer has created table student. Now he has created a trigger to make sure that in every inserted record, student age must be greater than 0. Which command he should use to remove trigger','REMOVE','DELETE','CLEAR','DROP','D'),('Java',41,'Which of the following makes the transaction permanent in the database?','View','Commit','Rollback','Flashback','B'),('Java',42,'For which of the following are triggers not supported?','delete','update','insert','views','D'),('Java',43,'A __________ consists of a sequence of query and/or update statements.','Transaction','Commit','Rollback','Flashback','A'),('Java',44,'What is abc in the following MySQL statement? CREATE TRIGGER abc (...) (...) ON def FOR EACH ROW ghi;','trigger name','table name','trigger statement','update statement','A'),('Java',45,'What is xyz in the following MySQL statement? CREATE VIEW xyz AS SELECT a, b FROM t;','Table','Column','View','Database','C'),('Java',46,'Which command is used to declare variable in Procedure?','Create','Variable','Declare','Set','C'),('Java',47,'Which command is used to assign value to variable in Procedures?','Create','Variable','Declare','Set','D'),('Java',48,'Jack, the database developer has created table student. Now he wants to remove a procedure. Which command he should use to this task','REMOVE','DELETE','CLEAR','DROP','D'),('Java',49,'Consider the following Statements: DELIMITER// Create Procedure getAvgMark(OUT mark DOUBLE) BEGIN Select Avg(marks) into mark from student; END // DELIMITER; Which one of the following statements would you use to execute the preceding procedure?','CALL getAvgMark(a); Select a;','DECLARE a; CALL getAvgMark(a); SELECT a;','CALL getAvgMark(@a); SELECT @a;','DECLARE @a; CALL getAvgMark(@a); SELECT @a;','C'),('Java',50,'Jack, the database developer has created table student. Now he has created a trigger to make sure that in every inserted record, student age must be greater than 0. Which command he should use to remove trigger','REMOVE','DELETE','CLEAR','DROP','D'),('Java',51,'Which of the following makes the transaction permanent in the database?','View','Commit','Rollback','Flashback','B'),('Java',52,'For which of the following are triggers not supported?','delete','update','insert','views','D'),('Java',53,'How are you?','5','6','7','8','A'),('Mysql',1,'Jack has enrolled to understand Mysql and needs to also learn SQL, what does SQL means?','Structured Query Language','Structureless Question Language','Static Query Language','None of these','A'),('Mysql',2,'Which of the following command is used to reconnect the database?','Connect','delimiter','quit','status','A'),('Mysql',3,'Which one of the following component is used for planning the execution of query processing?','Query parser','query preprocessor','query optimizer','execution engine','B'),('Mysql',4,'Which of the following given command is used to find out the details of the database?','Connect','delimiter','quit','status','D'),('Mysql',5,'Which data type only allows characters to be entered?','int','double','varchar','date','C'),('Mysql',6,'Jack, a database developer no longer wants to keep the table in the database, Identify the right SQL command to enable him remove the table from the database?','truncate','alter','insert','drop','D'),('Mysql',7,'Which of the following command is use to add column table?','truncate','alter','insert','drop','B'),('Mysql',8,'A student named \"Tom\" has just left the school, hence, jack, a database administrator need to ensure that tom\'s details are removed from the student table. Identify the correct SQL statement to enable jack remove tom\'s data from the student table.','delete table student name=\'Tom\'','delete from student where student_name=\'Tom\'','delete student where name=\'Tom\'','delete \'Tom\'','B'),('Mysql',9,'What will be output of following query ASCII(\'ABC\')?','Ascii value of ABC letters','Ascii value of letter B','Ascii value of letter A','Ascii value of letter C','B'),('Mysql',10,'Which one of the following operator is used to eliminate the certain rows from group by results?','Group by','Order by','Having','Where','C'),('Mysql',11,'John Manager, want to display the first name and last name of employee together Help him to find out string function is used to join two strings','Substring','Reverse','Left','Concat','D'),('Mysql',12,'Mike, an accountant would like to display details of maximum salary of employees for each department. Identify the correct SQL Statements to display the same.','Select max(salary) of employee group by department','Select max(salary) from employee group by department','Select salary from employee','Select salary from employee group by department','B'),('Mysql',13,'The clause that filters JOIN results is called _________','WHERE','SORT','GROUP','GROUP BY','A'),('Mysql',14,'In inner join, result is produced by matching rows in one table with rows in another table.','true','false','Not complete true','None of these','A'),('Mysql',15,'In which join all the rows from the left table appear in the output and matching of right table appear?','Inner Join','Natural Join','Left Join','Right Join','C'),('Mysql',16,'Database contains two tables Student and Exam which are as follows. 1. Student (student_id , student_name, student_class, exam_id) 2. Exam(exam_id , subject_name). Write a SQL Statement for display only matching records from table student and exams','select *from student join exam','select *from student left join exam','select *from student right join exam','select *from student natural join exam','A'),('Mysql',17,'Which of these operators perform similar operations like ALL and ANY?','SOME','MANY','SELECT','GROUP','A'),('Mysql',18,'Jack, the database developer has created view on table. Now he wants display the view. Which command he should use to display the view?','Create','Update','Select','DROP','C'),('Mysql',19,'Jack ,the database developer has created product table. He has also created indexes in table. Which command he should used to show indexes','Show','Select','drop','All of these','A'),('Mysql',20,'Which index is used to improve the speed of searches made on string value________','Unique Index','Regular Index','Full-text','Primary Key Index','C'),('Mysql',21,'A view can refer to multiple tables via __________','UNION','JOIN','GROUP','SELECT','B'),('Mysql',22,'Which one of the following is also known as a virtual table?','SCHEMA','DATABASE','JOIN','VIEW','D'),('Mysql',23,'Jack ,the database developer has created product table. Which contain Product_id,Product_Name,Product_Description. Help the developer to create primary key index on Product Table.','alter index product add index(product_id);','alter table product add primary key(product_id);','add index product (product_id);','None of these','B'),('Mysql',24,'Which command is used to assign value to variable in Procedures?','Create','Variable','Declare','Set','D'),('Mysql',25,'Which command is used to call procedure?','select','connect','call','None of these','C'),('Mysql',26,'Jack, the database developer has created table student. Now he wants to declare a trigger to make sure that in every inserted record, student age must be greater than 0. Which command he should use to declare trigger','create','show','declare','connect','A'),('Mysql',27,'In compound statement declaration start and end with______','Start End Block','Begin End Block','Open Bracket and Close Bracket','None of these','B'),('Mysql',28,'Jack, the database developer has created two tables student. Now he wants display students using procedure. Help him to identify correct procedure.','delimiter // create procedure proc1() begin select from student','delimiter // create procedure proc1() begin select *from student end// delimiter ;','begin select *from student end//','None of these','B'),('Mysql',29,'A _________ consists of a sequence of query and/or update statements.','Transaction','Commit','Rollback','Flashback','A'),('Mysql',30,'For which of the following are triggers not supported?','delete','update','insert','views','D'),('Mysql',31,'In order to undo the work of transaction after last commit which one should be used?','View','Commit','Rollback','Flashback','D'),('Mysql',32,'What is abc in the following MySQL statement? CREATE TRIGGER abc (...) (...) ON def FOR EACH ROW ghi;','trigger name','table name','trigger statement','update statement','C'),('Mysql',33,'Jack, database developer has created table of Student. He wants to export the data into xls file. Identify correct SQL Statement','select *from student into outfile \'D:\\student.xls\';','select *from student into \'D:\\student.xls\';','select *from student = \'D:\\student.xls\';','None of these','A'),('Mysql',34,'Which one of the following options can be used to execute SQL statements and MySQL commands from within a text file without invoking the command line client?','SOURCE','LOAD DATA INFILE','Mysqlimport','DUMPFILE','A'),('Mysql',35,'The index in which column value can contain duplicate and null is called as_____','Unique Index','Regular Index','Full-text','Primary Key Index','B'),('Mysql',36,'What is xyz in the following MySQL statement? CREATE VIEW xyz AS SELECT a, b FROM t;','Table','Column','View','Database','C'),('Mysql',37,'Which command is used to declare variable in Procedure?','Create','Variable','Declare','Set','C'),('Mysql',38,'Which command is used to assign value to variable in Procedures?','Create','Variable','Declare','Set','D'),('Mysql',39,'Jack, the database developer has created table student. Now he wants to remove a procedure. Which command he should use to this task','REMOVE','DELETE','CLEAR','DROP','D'),('Mysql',40,'Which of following are characteristics of stored procedure?','Deterministic','Contain SQL and Non SQL Statement','Read SQL Data','All of These','D'),('Mysql',41,'Consider the following Statements: DELIMITER// Create Procedure getAvgMark(OUT mark DOUBLE) BEGIN Select Avg(marks) into mark from student; END // DELIMITER; Which one of the following statements would you use to execute the preceding procedure?','CALL getAvgMark(a); Select a;','DECLARE a; CALL getAvgMark(a); SELECT a;','CALL getAvgMark(@a); SELECT @a;','DECLARE @a; CALL getAvgMark(@a); SELECT @a;','A'),('Mysql',42,'Jack, the database developer has created table student. Now he has created a trigger to make sure that in every inserted record, student age must be greater than 0. Which command he should use to remove trigger','REMOVE','DELETE','CLEAR','DROP','D'),('Mysql',43,'Which of the following makes the transaction permanent in the database?','View','Commit','Rollback','Flashback','B'),('Mysql',44,'For which of the following are triggers not supported?','delete','update','insert','views','D'),('Mysql',45,'A __________ consists of a sequence of query and/or update statements.','Transaction','Commit','Rollback','Flashback','A'),('Mysql',46,'What is abc in the following MySQL statement? CREATE TRIGGER abc (...) (...) ON def FOR EACH ROW ghi;','trigger name','table name','trigger statement','update statement','A'),('Mysql',47,'Jack, database developer has created table of Student. He wants to export the data into xls file. Identify correct SQL Statement','select *from student into outfile \'D:\\student.xls\';','select *from student into \'D:\\student.xls\';','select *from student = \'D:\\student.xls\';','None of these','A'),('Mysql',48,'Which one of the following options can be used to execute SQL statements and MySQL commands from within a text file without invoking the command line client?','SOURCE','LOAD DATA INFILE','Mysqlimport','DUMPFILE','A'),('Mysql',49,'The index in which column value can contain duplicate and null is called as_____','Unique Index','Regular Index','Full-text','Primary Key Index','B'),('Mysql',50,'What is xyz in the following MySQL statement? CREATE VIEW xyz AS SELECT a, b FROM t;','Table','Column','View','Database','C'),('Mysql',51,'Which command is used to declare variable in Procedure?','Create','Variable','Declare','Set','A'),('Mysql',52,'Which command is used to assign value to variable in Procedures?','Create','Variable','Declare','Set','D'),('Mysql',53,'Jack, the database developer has created table student. Now he wants to remove a procedure. Which command he should use to this task','REMOVE','DELETE','CLEAR','DROP','D'),('Mysql',54,'Which of following are characteristics of stored procedure?','Deterministic','Contain SQL and Non SQL Statement','Read SQL Data','All of These','D'),('Mysql',55,'Consider the following Statements: DELIMITER// Create Procedure getAvgMark(OUT mark DOUBLE) BEGIN Select Avg(marks) into mark from student; END // DELIMITER; Which one of the following statements would you use to execute the preceding procedure?','CALL getAvgMark(a); Select a;','DECLARE a; CALL getAvgMark(a); SELECT a;','CALL getAvgMark(@a); SELECT @a;','DECLARE @a; CALL getAvgMark(@a); SELECT @a;','C'),('Mysql',56,'Jack, the database developer has created table student. Now he has created a trigger to make sure that in every inserted record, student age must be greater than 0. Which command he should use to remove trigger','REMOVE','DELETE','CLEAR','DROP','D'),('Mysql',57,'Which of the following makes the transaction permanent in the database?','View','Commit','Rollback','Flashback','B'),('Mysql',58,'For which of the following are triggers not supported?','delete','update','insert','views','D');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `result`
--

DROP TABLE IF EXISTS `result`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `result` (
  `SNO` bigint NOT NULL,
  `SNAME` varchar(100) DEFAULT NULL,
  `COURSE` varchar(100) NOT NULL,
  `SCORE` double DEFAULT NULL,
  `DATE` varchar(100) NOT NULL,
  PRIMARY KEY (`SNO`,`DATE`),
  CONSTRAINT `result_student_SNO_fk` FOREIGN KEY (`SNO`) REFERENCES `student` (`SNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `result`
--

LOCK TABLES `result` WRITE;
/*!40000 ALTER TABLE `result` DISABLE KEYS */;
INSERT INTO `result` VALUES (1,'1','java',10,'2024-06-28 17:20:22'),(1,'1','java',10,'2024-06-28 17:49:44'),(1,'1','java',30,'2024-06-28 20:22:23'),(202334070415,'070415','java',20,'2024-06-28 11:58:32'),(202334070415,'070415','Mysql',20,'2024-06-28 11:58:52'),(202334070415,'070415','java',20,'2024-06-28 11:59:48'),(202334070415,'070415','java',0,'2024-06-28 12:00:06'),(202334070415,'070415','java',40,'2024-06-28 12:20:52'),(202334070415,'070415','java',0,'2024-06-28 15:53:48'),(202334070415,'070415','java',30,'2024-06-28 16:42:55'),(202334070416,'070416','Mysql',0,'2024-06-28 16:55:53'),(202334070416,'070416','java',20,'2024-06-28 16:56:01'),(202334070422,'070422','java',20,'2024-06-28 15:56:22'),(202334070440,'070440','java',30,'2024-06-28 16:56:42'),(202334070440,'070440','Mysql',10,'2024-06-28 16:56:56');
/*!40000 ALTER TABLE `result` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `SNO` bigint NOT NULL,
  `SNAME` varchar(100) DEFAULT NULL,
  `PHONE` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`SNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'1','1','1'),(123,'Frank','13500000000','123456'),(202334070401,'070401','1111111111','070401'),(202334070402,'070402','1111111111','070402'),(202334070403,'070403','1111111111','070403'),(202334070404,'070404','1111111111','070404'),(202334070405,'070405','1111111111','070405'),(202334070406,'070406','1111111111','070406'),(202334070407,'070407','1111111111','070407'),(202334070408,'070408','1111111111','070408'),(202334070409,'070409','1111111111','070409'),(202334070410,'070410','1111111111','070410'),(202334070411,'070411','1111111111','070411'),(202334070412,'070412','1111111111','070412'),(202334070413,'070413','1111111111','070413'),(202334070414,'070414','1111111111','070414'),(202334070415,'070415','1111111111','070415'),(202334070416,'070416','1111111111','070416'),(202334070417,'070417','1111111111','070417'),(202334070418,'070418','1111111111','070418'),(202334070419,'070419','1111111111','070419'),(202334070420,'070420','1111111111','070420'),(202334070421,'070421','1111111111','070421'),(202334070422,'070422','1111111111','070422'),(202334070423,'070423','1111111111','070423'),(202334070424,'070424','1111111111','070424'),(202334070425,'070425','1111111111','070425'),(202334070426,'070426','1111111111','070426'),(202334070427,'070427','1111111111','070427'),(202334070428,'070428','1111111111','070428'),(202334070429,'070429','1111111111','070429'),(202334070430,'070430','1111111111','070430'),(202334070431,'070431','1111111111','070431'),(202334070432,'070432','1111111111','070432'),(202334070433,'070433','1111111111','070433'),(202334070434,'070434','1111111111','070434'),(202334070435,'070435','1111111111','070435'),(202334070436,'070436','1111111111','070436'),(202334070437,'070437','1111111111','070437'),(202334070438,'070438','1111111111','070438'),(202334070439,'070439','1111111111','070439'),(202334070440,'070440','1111111111','070440'),(202334070441,'070441','1111111111','070441'),(202334070442,'070442','1111111111','070442'),(202334070443,'070443','1111111111','070443'),(202334070444,'070444','1111111111','070444'),(202334070445,'070445','1111111111','070445'),(202334070446,'070446','1111111111','070446'),(202334070447,'070447','1111111111','070447'),(202334070448,'070448','1111111111','070448'),(202334070449,'070449','1111111111','070449'),(202334070450,'070450','1111111111','070450');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `TNO` varchar(100) NOT NULL,
  `USERNAME` varchar(100) DEFAULT NULL,
  `PASSWORD` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`TNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('1','1','123');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-28 22:55:38
