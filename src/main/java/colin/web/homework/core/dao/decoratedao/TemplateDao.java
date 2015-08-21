package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.pojo.Homework_Template_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by DELL on 2015/7/28.
 */
@Repository
public class TemplateDao extends DecorateCommnDao {

    /**
     * 获取模板的所有标签
     *
     * @return
     */
    public Set<String> fetchAllTemplateTips() {
        String searchSql = "select distinct(template_tip) from homework_template";
        final Set<String> tipSet = new HashSet<String>();
        this.getJdbcTemplate().query(searchSql, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                String[] tipContents = resultSet.getString("template_tip").replaceAll("，", ",").split(",");
                if (tipContents != null && tipContents.length > 0) {
                    for (String tip : tipContents) {
                        tipSet.add(tip);
                    }
                }
            }
        });
        return tipSet;
    }

    /**
     * 返回最近发布的模板
     *
     * @return
     */
    public List<Homework_Template_Entity> fetchRecentlyTemplateObj() {
        String searchSql = "select * from homework_template ORDER BY template_create_time limit 0,8";
        List<Homework_Template_Entity> homework_template_entities = this.getJdbcTemplate().query(searchSql, new DefaultRowmapper<Homework_Template_Entity>(Homework_Template_Entity.class.getName()));
        return homework_template_entities;
    }
}
