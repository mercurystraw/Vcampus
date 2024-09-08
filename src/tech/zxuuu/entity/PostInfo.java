package tech.zxuuu.entity;

public class PostInfo {
    private String id;
    private String content;
    private String date;
    private String userid;
    private int thumbup;

    public PostInfo(String id, String date, String content, String userid, int thumbup) {
        this.id = id;
        this.date = date;
        this.content = content;
        this.userid = userid;
        this.thumbup = thumbup;
    }

    public int getThumbup() {
        return thumbup;
    }

    public void setThumbup(int thumbup) {
        this.thumbup = thumbup;
    }

    public PostInfo() {
    }
    public String getUser_id() {
        return userid;
    }

    public void setUser_id(String userid) {
        this.userid = userid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


}
