package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Column;
import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;

/**
 * Created by DELL on 2015/12/25.
 */
@Table(name = "homework_aticle_classify")
public class Homework_Aticle_Classify_Entity {
    @Id
    @Column(name ="classify_id")
    private String classify_id;
    @Column(name ="classify_name")
    private String classify_name;
    @Column(name ="classify_createtime")
    private String classify_createtime;
    @Column(name ="classify_createuser")
    private String classify_createuser;

    public String getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(String classify_id) {
        this.classify_id = classify_id;
    }

    public String getClassify_name() {
        return classify_name;
    }

    public void setClassify_name(String classify_name) {
        this.classify_name = classify_name;
    }

    public String getClassify_createtime() {
        return classify_createtime;
    }

    public void setClassify_createtime(String classify_createtime) {
        this.classify_createtime = classify_createtime;
    }

    public String getClassify_createuser() {
        return classify_createuser;
    }

    public void setClassify_createuser(String classify_createuser) {
        this.classify_createuser = classify_createuser;
    }
}
