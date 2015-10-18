package colin.web.homework.service;

import colin.web.homework.core.dao.decoratedao.CommentDao;
import colin.web.homework.core.pojo.Homework_Aticle_Comment_Entity;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by DELL on 2015/10/15.
 */
@Service
public class CommentSevice {
    @Resource
    private CommentDao commentDao;

    public List<Homework_Aticle_Comment_Entity> findAllAticleCommentList() {
        return null;
    }
}
