package colin.homework.test.tools;

import colin.web.homework.core.dao.decoratedao.CommentDao;
import colin.web.homework.core.pojo.Homework_Aticle_Comment_Entity;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.Iterator;

/**
 * Created by DELL on 2015/10/15.
 */
public class RepositoryTest extends BaseSpringTest{

    @Resource
    private CommentDao commentDao;
    @Test
    public void testJPARepository(){
       /* Iterator<Homework_Aticle_Comment_Entity> comment_entities=commentDao.findAll().iterator();
        while(comment_entities.hasNext()){
            Homework_Aticle_Comment_Entity entity=comment_entities.next();
            System.out.println(entity.getComment_id());
        }*/
    }
}
