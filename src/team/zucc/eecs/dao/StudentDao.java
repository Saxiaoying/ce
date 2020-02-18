package team.zucc.eecs.dao;

import java.util.List;

import team.zucc.eecs.model.Student;

public interface StudentDao {
	Student getStudentByStu_id(int stu_id);
	List<Student> getStudentListByClass_id(int class_id); 
	List<Student> getStudentList(); 
	
	int getStudentNumberByInf(String stu_name, String class_name);
	List<Student> getStudentListByInfFromAtoB(int a, int b, String stu_name, String class_name);
}
