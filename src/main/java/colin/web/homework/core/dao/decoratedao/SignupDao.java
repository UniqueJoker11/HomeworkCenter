package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.pojo.Homework_User_Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class SignupDao extends DecorateCommnDao{

	/**
	 * 
	 * 方法描述：注册用户信息 注意事项：
	 * 
	 * @param homework_user_entity
	 * @return
	 * @Exception 异常对象
	 */
	public boolean signupUserinfo(Homework_User_Entity homework_user_entity) {
			return super.addObjInfo(homework_user_entity);
	}
}
