package colin.web.homework.core.dao;

import colin.web.homework.core.dao.decoratedao.DecorateCommnDao;
import org.springframework.stereotype.Repository;

/**
 * Created by ASUS on 2015/7/11.
 */
@Repository
public class SigninDao<Homework_User_Entity> extends DecorateCommnDao<Homework_User_Entity> {
}
