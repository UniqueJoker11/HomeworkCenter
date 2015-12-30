package colin.web.homework.core.pojo;

import colin.web.homework.annotation.Table;

/**
 * Created by DELL on 2015/12/29.
 * 心情
 */
@Table(name = "homework_mood")
public class Homework_Mood_Entity {
    private  String mood_id;
    private  int mood_level;
    private  String mood_content;
    private  String mood_createtime;
    private  String mood_user;

    public String getMood_id() {
        return mood_id;
    }

    public void setMood_id(String mood_id) {
        this.mood_id = mood_id;
    }

    public int getMood_level() {
        return mood_level;
    }

    public void setMood_level(int mood_level) {
        this.mood_level = mood_level;
    }

    public String getMood_content() {
        return mood_content;
    }

    public void setMood_content(String mood_content) {
        this.mood_content = mood_content;
    }

    public String getMood_createtime() {
        return mood_createtime;
    }

    public void setMood_createtime(String mood_createtime) {
        this.mood_createtime = mood_createtime;
    }

    public String getMood_user() {
        return mood_user;
    }

    public void setMood_user(String mood_user) {
        this.mood_user = mood_user;
    }
}
