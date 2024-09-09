package tech.mainCode.dao;

import java.util.Map;

import tech.mainCode.entity.Teacher;

public interface ITeacherMapper {

	public Boolean verifyTeacher(Teacher teacher);

	public Teacher getTeacherDetailByCardNumber(String cardNumber);

	public String getTeacherNameById(Map<String, String> mp);

	public String getTeacherCardById(Map<String, String> mp);
	
}
