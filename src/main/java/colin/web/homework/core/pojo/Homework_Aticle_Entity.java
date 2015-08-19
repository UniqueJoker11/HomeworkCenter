package colin.web.homework.core.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by DELL on 2015/8/5.
 */
@Table(name = "homework_aticle")
public class Homework_Aticle_Entity {
    @Id
    @Column(name ="aticle_id" ,nullable = false)
    private String aticle_id;
    @Column(name ="aticle_category" ,nullable = false)
    private String aticle_category;
    @Column(name ="aticle_content" ,nullable = false)
    private String aticle_content;
    @Column(name ="aticle_cover_img" ,nullable = false)
    private String aticle_cover_img;
    @Column(name ="aticle_createtime" ,nullable = false)
    private String aticle_createtime;
    @Column(name ="aticle_author" ,nullable = false)
    private String aticle_author;
    @Column(name ="aticle_digest" ,nullable = false)
    private String aticle_digest;
    @Column(name ="aticle_name" ,nullable = false)
    private String aticle_name;
    @Column(name ="aticle_read_num" ,nullable = false)
    private int aticle_read_num;
    @Column(name ="key_words" ,nullable = false)
    private String key_words;

    public String getAticle_id() {
        return aticle_id;
    }

    public void setAticle_id(String aticle_id) {
        this.aticle_id = aticle_id;
    }

    public String getAticle_category() {
        return aticle_category;
    }

    public void setAticle_category(String aticle_category) {
        this.aticle_category = aticle_category;
    }

    public String getAticle_content() {
        return aticle_content;
    }

    public void setAticle_content(String aticle_content) {
        this.aticle_content = aticle_content;
    }

    public String getAticle_cover_img() {
        return aticle_cover_img;
    }

    public void setAticle_cover_img(String aticle_cover_img) {
        this.aticle_cover_img = aticle_cover_img;
    }

    public String getAticle_createtime() {
        return aticle_createtime;
    }

    public void setAticle_createtime(String aticle_createtime) {
        this.aticle_createtime = aticle_createtime;
    }

    public String getAticle_author() {
        return aticle_author;
    }

    public void setAticle_author(String aticle_author) {
        this.aticle_author = aticle_author;
    }

    public String getAticle_digest() {
        return aticle_digest;
    }

    public void setAticle_digest(String aticle_digest) {
        this.aticle_digest = aticle_digest;
    }

    public String getAticle_name() {
        return aticle_name;
    }

    public void setAticle_name(String aticle_name) {
        this.aticle_name = aticle_name;
    }

    public int getAticle_read_num() {
        return aticle_read_num;
    }

    public void setAticle_read_num(int aticle_read_num) {
        this.aticle_read_num = aticle_read_num;
    }

    public String getKey_words() {
        return key_words;
    }

    public void setKey_words(String key_words) {
        this.key_words = key_words;
    }
}
