package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;

import java.io.Serializable;

/**
 * Created by ASUS on 2015/10/2.
 */
@Table(name = "homework_comment")
public class Homework_Comment implements Serializable{
    @Id
    private String comment_id;
    private String comment_content;
    private String comment_author;
    private String comment_creattime;

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public String getComment_creattime() {
        return comment_creattime;
    }

    public void setComment_creattime(String comment_creattime) {
        this.comment_creattime = comment_creattime;
    }
}
