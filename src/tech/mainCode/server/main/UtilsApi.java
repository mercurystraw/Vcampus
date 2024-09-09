package tech.mainCode.server.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import tech.mainCode.dao.INoticeMapper;
import tech.mainCode.dao.IPostMapper;
import tech.mainCode.dao.IStudentMapper;
import tech.mainCode.entity.NoticeInfo;
import tech.mainCode.entity.PostInfo;
import tech.mainCode.util.OtherUtils;

/**
 * 不易归类的后端接口
 *
 */
public class UtilsApi {

	/**
	 * 获取可信时间（服务器时间）
	 * 
	 * @return Unix时间戳
	 */
	public static long getTrustedUnixTimeStamp() {
		return OtherUtils.getLocalTime().getTime();
	}

	/**
	 * 一卡通充值
	 * @param cardNumber
	 * @param howmuch
	 * @return 成功状态
	 */
	public static Boolean chargeCardBalance(String cardNumber, Integer howmuch) {
		Boolean result = null;
		SqlSession sqlSession = null;

		Map<String, Object> map = new HashMap<String, Object>();

		map.put("cardNumber", cardNumber);
		map.put("money", howmuch);

		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IStudentMapper studentMapper = sqlSession.getMapper(IStudentMapper.class);
			result = studentMapper.chargeCard(map);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 获取用于主页显示的通知
	 * @return 四条通知
	 */
	public static List<NoticeInfo> getTop4Notice() {
		List<NoticeInfo> result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			INoticeMapper noticeMapper = sqlSession.getMapper(INoticeMapper.class);
			result = noticeMapper.getTop4Notice();
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static List<PostInfo> getTop3Post(){
		List<PostInfo> result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IPostMapper postMapper = sqlSession.getMapper(IPostMapper.class);
			result = postMapper.getTop3Post();
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
