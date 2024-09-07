package tech.zxuuu.server.bbs;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSession;
import tech.zxuuu.dao.IPostMapper;
import tech.zxuuu.entity.PostInfo;
import tech.zxuuu.server.main.App;
import java.util.List;
import java.util.List;

public class PostDetails {
    public static List<PostInfo> getChildPostList(String postId){
        //TODO: get reply list by postId
        System.out.println("getChildPostList Begin!");
        List<PostInfo> replyList = null;
        SqlSession sqlSession = null;
        try{
            sqlSession = App.sqlSessionFactory.openSession();
            IPostMapper postMapper = sqlSession.getMapper(IPostMapper.class);
            replyList = postMapper.getChildPostList(postId);
            sqlSession.commit();
            sqlSession.close();
            for(PostInfo postInfo : replyList){
                System.out.println(postInfo.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return replyList;

    }
}
