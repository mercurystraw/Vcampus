package tech.zxuuu.server.bbs;

import org.apache.ibatis.session.SqlSession;
import tech.zxuuu.dao.IPostMapper;
import tech.zxuuu.server.main.App;

public class DeleteReply {
    public static Boolean deleteReply(String postId) {
        Boolean result =null;
        SqlSession sqlSession = null;
        try {
            sqlSession = App.sqlSessionFactory.openSession();
            IPostMapper postMapper = sqlSession.getMapper(IPostMapper.class);
            result = postMapper.deleteReply(postId);
            System.out.println("删除Reply结果："+result);
            sqlSession.commit();
            sqlSession.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
