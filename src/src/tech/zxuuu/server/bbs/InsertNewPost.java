package src.tech.zxuuu.server.bbs;

import org.apache.ibatis.session.SqlSession;
import tech.zxuuu.dao.IPostMapper;
import tech.zxuuu.entity.PostInfo;
import tech.zxuuu.server.main.App;

public class InsertNewPost {
    public static Integer getMaxPostid() {
        Integer maxPostid = 0;
        try{
            SqlSession sqlSession = App.sqlSessionFactory.openSession();
            IPostMapper postMapper = sqlSession.getMapper(IPostMapper.class);
            maxPostid = postMapper.getMaxPostid();
            sqlSession.commit();
            sqlSession.close();
            System.out.println("maxPostid: " + maxPostid);
        }catch(Exception e) {
            e.printStackTrace();
        }
        return maxPostid;
    }
    public static Boolean insertNewPost(PostInfo post) {
        Boolean result =null;
        SqlSession sqlSession = null;
        try {
            sqlSession = App.sqlSessionFactory.openSession();
            IPostMapper postMapper = sqlSession.getMapper(IPostMapper.class);
            String postIdStr = String.format("%03d", postMapper.getMaxPostid() + 1) + "0000";
            post.setId(postIdStr);
            result = postMapper.insertNewPost(post);
            System.out.println("后"+result);
            sqlSession.commit();
            sqlSession.close();
            System.out.println("Insert new post success!");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Insert new post failed!");
        }
        return result;
    }
}
