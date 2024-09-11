package tech.mainCode.entity;

public class PostInfo {
    private String id;
    private String content;
    private String date;
    private String userid;
    private int thumbup;
    private String type;

    @Override
    public String toString() {
        return "PostInfo{" +
                "id='" + id + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", userid='" + userid + '\'' +
                ", thumbup=" + thumbup +
                ", type='" + type + '\'' +
                '}';
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public PostInfo(String id, String content, String date, String userid, int thumbup, String type) {
        this.id = id;
        this.content = content;
        this.date = date;
        this.userid = userid;
        this.thumbup = thumbup;
        this.type = type;
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
