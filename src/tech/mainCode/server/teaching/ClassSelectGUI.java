package tech.mainCode.server.teaching;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import tech.mainCode.dao.IClassMapper;
import tech.mainCode.entity.ClassInfo;
import tech.mainCode.entity.Student;
import tech.mainCode.server.main.App;

public class ClassSelectGUI {

	public static List<ClassInfo> getClassInfo(String academy) {
		List<ClassInfo> result = null;
		System.out.println("学生选课getClassInfo开始：");
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getClassInfo(academy);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("学生选课getClassInfo结束：");
		return result;
	}

	public static Boolean takeClass(Student student, String newClassId) {
		SqlSession sqlSession = App.sqlSessionFactory.openSession();
		Boolean one = null, two = null;
		try {
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			one = classMapper.takeClass(student);
			// add on to support exam system
			String temp = classMapper.getStudentOfOneClass(newClassId);
			temp += student.getCardNumber();
			temp += "xxx,";
			Map<String, String> map = new HashMap<String, String>();
			map.put("classId", newClassId);
			map.put("content", temp);
			two = classMapper.updateScoreOfOneClass(map);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return Boolean.logicalAnd(one, two);
	}

	public static Boolean takeClassInv(Student student, String dropClassId) { //退课
		SqlSession sqlSession = App.sqlSessionFactory.openSession();
		Boolean one = null, two = null;
		try {
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			one = classMapper.takeClass(student);
			// add on to support exam system
			String temp = classMapper.getStudentOfOneClass(dropClassId);
			String[] sp = temp.split(",");
			String writeBack = "";
			for (String string : sp) {
				if (string.indexOf(student.getCardNumber()) != -1) {
					continue;
				}
				//写回
				writeBack += string;
				writeBack += ",";
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("classId", dropClassId);
			map.put("content", writeBack);
			two = classMapper.updateScoreOfOneClass(map);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return Boolean.logicalAnd(one, two);
	}

	public static String getClassSelection(Student student) {
		String result = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getClassSelection(student);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static ClassInfo getOneClass(String ID) {
		ClassInfo result = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getOneClass(ID);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<ClassInfo> getClassOfOneTeacher(String name) {
		List<ClassInfo> result = null;
		try {
			SqlSession sqlSession = App.sqlSessionFactory.openSession();
			IClassMapper classMapper = sqlSession.getMapper(IClassMapper.class);
			result = classMapper.getClassOfOneTeacher(name);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
