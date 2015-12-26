package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.pojo.Homework_Aticle_Classify_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by DELL on 2015/12/25.
 */
@Repository
public class AticleClassifyDao extends DecorateCommnDao {

    public List<Homework_Aticle_Classify_Entity> fetchAll(){
          return super.getAmongObjectWithOrder(Homework_Aticle_Classify_Entity.class,null, "classify_createtime","",new DefaultRowmapper<Homework_Aticle_Classify_Entity>(Homework_Aticle_Classify_Entity.class.getName()),false);
    }
}
