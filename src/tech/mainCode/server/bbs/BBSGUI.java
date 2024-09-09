package tech.mainCode.server.bbs;

import org.apache.ibatis.session.SqlSession;
import tech.mainCode.dao.IPostMapper;
import tech.mainCode.entity.PostInfo;
import tech.mainCode.server.main.App;

import java.util.ArrayList;
import java.util.List;

public class BBSGUI {
    public static List<PostInfo> getParentPostList(){
        List<PostInfo> list =new ArrayList<>();
        SqlSession sqlSession = null;
        try{
            sqlSession = App.sqlSessionFactory.openSession();
            IPostMapper postMapper = sqlSession.getMapper(IPostMapper.class);
            list = postMapper.getParentPostList();
            sqlSession.commit();
            sqlSession.close();
            for (PostInfo post : list) {
                System.out.println("PostInfo: " + post);
            }
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Error occurred while fetching parent post list: " + e.getMessage());
        }
        return list;
    }

    public static PostInfo getPostById(String postId){
        PostInfo post = null;
        try{
            SqlSession sqlSession = App.sqlSessionFactory.openSession();
            IPostMapper postMapper = sqlSession.getMapper(IPostMapper.class);
            post = postMapper.getPostById(postId);
            sqlSession.commit();
            sqlSession.close();
            System.out.println("PostInfo: " + post);
        }catch (Exception e){
            e.printStackTrace();
        }
        return post;
    }

    public static Boolean updateThumbup(String postId, Integer thumbup){
        Boolean result = null;
        try{
            SqlSession sqlSession = App.sqlSessionFactory.openSession();
            IPostMapper postMapper = sqlSession.getMapper(IPostMapper.class);
            result = postMapper.updateThumbup(postId, thumbup);
            sqlSession.commit();
            sqlSession.close();

        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

//    public static List<PostInfo> getMyPostList(String userid){
//        List<PostInfo> list = new ArrayList<>();
//        try{
//            SqlSession sqlSession = App.sqlSessionFactory.openSession();
//            IPostMapper postMapper = sqlSession.getMapper(IPostMapper.class);
//            list = postMapper.getMyPostList(userid);
//            sqlSession.commit();
//            sqlSession.close();
//            for (PostInfo post : list) {
//                System.out.println("我发布的帖子 PostInfo: " + post);
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return list;
//    }

}
