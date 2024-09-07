package tech.zxuuu.dao;

import tech.zxuuu.entity.PostInfo;

import java.util.List;
import java.util.Map;

public interface IPostMapper {

    public List<PostInfo> getParentPostList();

    public List<PostInfo> getChildPostList(String postId);

    public PostInfo getPostById(String postId);

    public Integer getMaxPostid();

    public Boolean insertNewPost(PostInfo post);

    public Integer getMaxReplyPostid(String postId);

    public Boolean insertReplyPost(PostInfo post);

    public Boolean deletePost(String postId);

    public Boolean deleteReply(String postId);

}
