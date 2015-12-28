package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Column;
import colin.web.homework.annotation.Id;
import colin.web.homework.annotation.Table;

/**
 * Created by DELL on 2015/12/28.
 */
@Table(name = "homework_nav_classify")
public class Homework_Nav_Classify_Entity {
    @Id
    @Column(name = "nav_classify_id")
    private String nav_classify_id;
    @Column(name = "nav_id")
    private String nav_id;
    @Column(name = "classify_id")
    private String classify_id;

    public String getNav_classify_id() {
        return nav_classify_id;
    }

    public void setNav_classify_id(String nav_classify_id) {
        this.nav_classify_id = nav_classify_id;
    }

    public String getNav_id() {
        return nav_id;
    }

    public void setNav_id(String nav_id) {
        this.nav_id = nav_id;
    }

    public String getClassify_id() {
        return classify_id;
    }

    public void setClassify_id(String classify_id) {
        this.classify_id = classify_id;
    }
}
