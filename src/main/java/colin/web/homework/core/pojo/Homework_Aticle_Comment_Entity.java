package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;

/**
 * Created by ASUS on 2015/10/2.
 */
@Table(name = "homework_aticle_comment")
public class Homework_Aticle_Comment_Entity {
    @Id
    private String aticle_comment_id;
    private String aticle_id;
    private String comment_id;

    public String getAticle_comment_id() {
        return aticle_comment_id;
    }

    public void setAticle_comment_id(String aticle_comment_id) {
        this.aticle_comment_id = aticle_comment_id;
    }

    public String getAticle_id() {
        return aticle_id;
    }

    public void setAticle_id(String aticle_id) {
        this.aticle_id = aticle_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }
}
