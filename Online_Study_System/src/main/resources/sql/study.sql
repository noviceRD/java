/*
SQLyog Professional v12.08 (64 bit)
MySQL - 5.7.26 : Database - study1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`study` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `study1`;

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `name` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `teacher_id` int(11) DEFAULT NULL COMMENT '教师id',
  `video` varchar(100) NOT NULL COMMENT '课程视频',
  `video_url` varchar(100) NOT NULL COMMENT '课程视频地址',
  PRIMARY KEY (`id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`name`,`teacher_id`,`video`,`video_url`) values (1,'大学计算机基础',2,'aa','aa'),(2,'大学计算机基础',3,'bb','bb'),(3,'大学计算机基础',4,'cc','cc');

/*Table structure for table `question_answer` */

DROP TABLE IF EXISTS `question_answer`;

CREATE TABLE `question_answer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(200) NOT NULL,
  `item_A` varchar(200) NOT NULL,
  `item_B` varchar(200) NOT NULL,
  `item_C` varchar(200) NOT NULL,
  `item_D` varchar(200) NOT NULL,
  `answer` varchar(10) NOT NULL,
  `score` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `question_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `question_answer_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

/*Data for the table `question_answer` */

insert  into `question_answer`(`id`,`title`,`item_A`,`item_B`,`item_C`,`item_D`,`answer`,`score`,`course_id`,`question_id`) values (1,'1.计算机最主要的工作特点是()','A.高速度','B.高精度','C.存记忆能力','D.存储程序与自动控制','D',15,1,1),(2,'2.目前微型计算机中采用的逻辑元件是()','A.小规模集成电路','B.中规模集成电路','C.大规模和超大规模集成电路 ','D.分立元件','C',15,1,2),(3,'3.现代的计算机是基于()模型','A.莱布尼茨 ','B.冯﹒诺依曼 ','C.帕斯卡 ','D.查尔斯﹒巴贝奇','B',15,1,3),(4,'4.在一个()数据压缩方式中，接收到的数据是原始数据的复制品。','A.无损','B.有损','C. JPEG','D. MPEG','A',15,1,4),(5,'5.JPEG编码方式中，()过程把原始的图像分成许多小块，并对块中每一像素赋值。','A.分块','B.离散余弦变换','C.量化','D.向量化','B',20,1,5),(6,'6.10位可以用位模式表示()种符号。','A.128','B. 256','C.8512','D.1024','D',20,1,6),(7,'1.一个字节包含()位。','A.2','B.4','C.8','D.16','C',15,2,1),(8,'2.在一个64种符号的集合中，每个符号需要的位模式长度为()位。','A.4','B.5','C.6','D.7','C',15,2,2),(9,'3.假如E的ASCII码为1000101，那么e的ASCII码是()','A.1000110','B. 1000111','C. 0000110','D.1100101','D',15,2,3),(10,'4.使用()方法，图像可以在计算机中表示。','A.位图','B.矢量图','C.余码系统','D.A或B','D',15,2,4),(11,'5.存储于计算机中的数字的小数部分的精度由()来定义。','A.符号 ',' B.指数','C.尾数','D.以上全部答案','C',20,2,5),(12,'6.()编码方式是无损数据压缩方法。','A.游程长度编码','B.赫夫曼编码','C. LZ编码','D.以上全部答案','D',20,2,6),(13,'1.软件与程序的区别是()','A.程序价格便宜、软件价格昂贵','B.程序是用户自己编写的，而软件是厂家提供的','C.程序使用高级语言编写的，而软件是用机器语言编写的','D.软件是程序以及开发、使用和维护所需要的所有文档的总和，而程序是软件的一部分','D',15,3,1),(14,'2.微型计算机的性能主要取决于()','A.内存','B.中央处理器','C.硬盘','D.显示卡','B',15,3,2),(15,'3.在微机系统中，最基本的输入输出模BIOS存放在()','A.RAM中','B.ROM中','C.硬盘中','D.寄存卡中','B',15,3,3),(16,'4.运算器主要用于()','A.四则运算','B.逻辑判断','C.传送数据','D.算术运算和逻辑运算','D',15,3,4),(17,'5.计算机中根据()访问内存','A.存储内容','B.存储地址','C.存储单元','D.存储容量','B',20,3,5),(18,'6.计算机中对以下几个部件访问速度最快的是()','A.光盘','B.RAM','C.硬盘','D.软盘','B',20,3,6);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `permissions` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`permissions`) values (1,'管理员',''),(2,'教师',NULL),(3,'学生',NULL);

/*Table structure for table `student` */

DROP TABLE IF EXISTS `student`;

CREATE TABLE `student` (
  `id` int(11) NOT NULL COMMENT '学生id',
  `name` varchar(100) DEFAULT NULL COMMENT '学生姓名',
  `sex` varchar(20) DEFAULT NULL COMMENT '学生性别',
  `number` int(11) DEFAULT NULL COMMENT '学号',
  PRIMARY KEY (`id`),
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student` */

insert  into `student`(`id`,`name`,`sex`,`number`) values (5,'小明','男',1001),(6,'小花','女',1002),(7,'小林','男',1003);

/*Table structure for table `student_course` */

DROP TABLE IF EXISTS `student_course`;

CREATE TABLE `student_course` (
  `course_id` int(11) NOT NULL COMMENT '课程id',
  `student_id` int(11) NOT NULL COMMENT '学生id',
  `mark` int(11) DEFAULT NULL COMMENT '习题成绩',
  `haw_exam` int(11) DEFAULT '0' COMMENT '是否完成习题(0:未完成，1:完成)',
  KEY `course_id` (`course_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `student_course_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `student_course_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `student_course` */

/*Table structure for table `teacher` */

DROP TABLE IF EXISTS `teacher`;

CREATE TABLE `teacher` (
  `id` int(11) NOT NULL COMMENT '教师id',
  `name` varchar(100) DEFAULT NULL COMMENT '教师姓名',
  `sex` varchar(20) DEFAULT NULL COMMENT '教师性别',
  PRIMARY KEY (`id`),
  CONSTRAINT `teacher_ibfk_1` FOREIGN KEY (`id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `teacher` */

insert  into `teacher`(`id`,`name`,`sex`) values (2,'孔老师','男'),(3,'刘老师','男'),(4,'李老师','女');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `password` varchar(100) DEFAULT NULL COMMENT '用户密码',
  `role` int(11) DEFAULT NULL COMMENT '用户角色',
  PRIMARY KEY (`id`),
  KEY `role` (`role`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`role`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`name`,`password`,`role`) values (1,'admin','b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d',1),(2,'孔老师','b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d',2),(3,'刘老师','b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d',2),(4,'李老师','b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d',2),(5,'小明','b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d',3),(6,'小花','b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d',3),(7,'小林','b2d1f375db625e65a4a63ba0c7b29bdd738392cd83c38b8216c10c4d',3);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
