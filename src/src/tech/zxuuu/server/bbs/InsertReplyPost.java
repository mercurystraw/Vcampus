package src.tech.zxuuu.server.bbs;

import org.apache.ibatis.session.SqlSession;
import tech.zxuuu.dao.IPostMapper;
import tech.zxuuu.entity.PostInfo;
import tech.zxuuu.server.main.App;

public class InsertReplyPost {
    public static Integer getMaxReplyPostid(String PostId){
        Integer maxReplyPostid = 0;
        try{
            SqlSession session = App.sqlSessionFactory.openSession();
            IPostMapper postMapper = session.getMapper(IPostMapper.class);
            maxReplyPostid = postMapper.getMaxReplyPostid(PostId);
            session.commit();
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return maxReplyPostid;
    }
    public static Boolean insertReplyPost(PostInfo post){
        Boolean result = null;
        try{
            SqlSession session = App.sqlSessionFactory.openSession();
            IPostMapper postMapper = session.getMapper(IPostMapper.class);
            //通过mapper获取id
            String tep = String.format("%04d", postMapper.getMaxReplyPostid(post.getId()) + 1);
            String postIdstr = post.getId().substring(0, 3) + tep;

            System.out.println(postIdstr);

            post.setId(postIdstr);
            //插入回复帖子
            result = postMapper.insertReplyPost(post);
            session.commit();
            session.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
