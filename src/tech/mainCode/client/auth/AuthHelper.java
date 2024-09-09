package tech.mainCode.client.auth;

import tech.mainCode.entity.*;
import tech.mainCode.net.Request;
import tech.mainCode.util.OtherUtils;
import tech.mainCode.util.ResponseUtils;
import tech.mainCode.client.main.App;

/**
 * 登陆界面前端请求相关代码
 * 
 * @author z0gSh1u
 */
public class AuthHelper {

	public static Student verifyStudent(String cardNumber, String password) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null, "tech.mainCode.server.auth.Auth.studentLoginChecker",
						new Object[] { new Student(cardNumber, null, OtherUtils.getMD5(password), null) }).send())
				.getReturn(Student.class);
	}

	public static Teacher verifyTeacher(String cardNumber, String password) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null, "tech.mainCode.server.auth.Auth.teacherLoginChecker",
						new Object[] { new Student(cardNumber, null, OtherUtils.getMD5(password), null) }).send())
				.getReturn(Teacher.class);
	}

	public static Manager verifyManager(String cardNumber, String password) {
		return ResponseUtils
				.getResponseByHash(new Request(App.connectionToServer, null, "tech.mainCode.server.auth.Auth.managerLoginChecker",
						new Object[] { new Student(cardNumber, null, OtherUtils.getMD5(password), null) }).send())
				.getReturn(Manager.class);
	}

}
