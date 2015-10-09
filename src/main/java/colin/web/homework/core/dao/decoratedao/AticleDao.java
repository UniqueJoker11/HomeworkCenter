package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.pojo.Homework_Aticle_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by DELL on 2015/8/5.
 */
@Repository
public class AticleDao extends DecorateCommnDao{

    public List<Homework_Aticle_Entity> findRecommondAticleList(String[] keyWords,String aticleId){
        StringBuilder searchSql=new StringBuilder("select * from homework_aticle where aticle_id !=:aticle_id and (");
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("aticle_id",aticleId);
        for(int i=0;i<keyWords.length;i++){
            params.put("keywords"+i,keyWords[i]);
            searchSql.append("key_words REGEXP '[:keywords"+i+"]'").append(" or ");
        }
        System.out.println(searchSql.substring(0, searchSql.toString().lastIndexOf(" or "))+")");
        return this.getJdbcTemplate().query(searchSql.substring(0, searchSql.toString().lastIndexOf(" or "))+")",params,new DefaultRowmapper<Homework_Aticle_Entity>(Homework_Aticle_Entity.class.getName()));
    }

    /**
     * 查询相邻的文章内容
     * @param createTime
     * @param nextOrPrev next为1，prev为0
     * @return
     */
    public List<Homework_Aticle_Entity> findUponAticles(String createTime,int nextOrPrev){
        StringBuilder searchSql=new StringBuilder("select * from homework_aticle where aticle_createtime");
        if(nextOrPrev==0){
            searchSql.append("<:createTime");
        }else if(nextOrPrev==1){
            searchSql.append(">:createTime");
        }
        searchSql.append(" ORDER BY aticle_createtime LIMIT 1");
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("createTime",createTime);
        return  this.getJdbcTemplate().query(searchSql.toString(),params,new DefaultRowmapper<Homework_Aticle_Entity>(Homework_Aticle_Entity.class.getName()));
    }
}
