package tech.mainCode.server.library;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import tech.mainCode.dao.IBookMapper;
import tech.mainCode.entity.Book;
import tech.mainCode.server.main.App;

public class Addons {

	public static List<Book> getBorrowedBookList(String cardNumber) {
		List<Book> result = null;
		SqlSession sqlSession = null;
		try {
			sqlSession = App.sqlSessionFactory.openSession();
			IBookMapper bookMapper = sqlSession.getMapper(IBookMapper.class);
			result = bookMapper.getBorrowedBook(cardNumber);
			sqlSession.commit();
			sqlSession.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
