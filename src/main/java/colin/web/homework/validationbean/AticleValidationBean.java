package colin.web.homework.validationbean;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


/**
 * Created by DELL on 2015/10/14.
 */
public class AticleValidationBean {
    @NotBlank(message = "{aticle.aticle_id.blank}")
    private String aticle_id;
    @NotBlank(message = "文章标题不能为空")
    @Min(value= 18 ,message= "文章标题长度必须超过18个字" )
    private String aticle_name;
    @NotBlank(message = "文章分类不能为空")
    private String aticle_category;
    @NotBlank(message = "文章内容不能为空")
    private String aticle_content;
    @NotBlank(message = "文章摘要不能为空")
    private String aticle_digest;
    @NotBlank(message = "文章关键字不能为空")
    private String key_words;

    public String getAticle_id() {
        return aticle_id;
    }

    public void setAticle_id(String aticle_id) {
        this.aticle_id = aticle_id;
    }

    public String getAticle_name() {
        return aticle_name;
    }

    public void setAticle_name(String aticle_name) {
        this.aticle_name = aticle_name;
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

    public String getAticle_digest() {
        return aticle_digest;
    }

    public void setAticle_digest(String aticle_digest) {
        this.aticle_digest = aticle_digest;
    }

    public String getKey_words() {
        return key_words;
    }

    public void setKey_words(String key_words) {
        this.key_words = key_words;
    }
}
