package tech.zxuuu.server.bbs;

import org.apache.ibatis.session.SqlSession;
import tech.zxuuu.dao.IPostMapper;
import tech.zxuuu.server.main.App;

public class DeletePost {
    public static Boolean deletePost(String postId) {
        Boolean result =null;
        SqlSession sqlSession = null;
        try {
            sqlSession = App.sqlSessionFactory.openSession();
            IPostMapper postMapper = sqlSession.getMapper(IPostMapper.class);
            result = postMapper.deletePost(postId);
            System.out.println("删除Post结果："+result);
            sqlSession.commit();
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
