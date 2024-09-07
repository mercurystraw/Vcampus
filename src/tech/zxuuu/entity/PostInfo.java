package tech.zxuuu.entity;

public class PostInfo {
    private String id;
    private String content;
    private String date;
    private String userid;

    @Override
    public String toString() {
        return "PostInfo{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", userid='" + userid + '\'' +
                '}';
    }


    public PostInfo() {
    }

    public PostInfo(String id, String content, String date, String userid) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.userid = userid;
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
