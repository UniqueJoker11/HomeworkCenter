package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.pojo.Homework_Aticle_Entity;
import colin.web.homework.core.pojo.Homework_Nav_Manage_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ASUS on 2015/12/27.
 */
@Repository
public class NavManageDao extends DecorateCommnDao{

    public List<Homework_Nav_Manage_Entity> fetchAllNavManageEntity(){
        return super.getAmongObjectWithOrder(Homework_Nav_Manage_Entity.class, null, "nav_sort", "", new DefaultRowmapper<Homework_Nav_Manage_Entity>(Homework_Nav_Manage_Entity.class.getName()), true);
    }

}
