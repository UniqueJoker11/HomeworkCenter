package colin.web.homework.service;

import colin.web.homework.core.vo.HomeworkMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by DELL on 2015/8/26.
 */
@Service
public class DashboardService {
    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuService;

    /**
     * 根据用户的id来返回所有的用户信息
     *
     * @param userId
     * @return
     */
    public List<HomeworkMenuVo> getUserMenuInfo(String userId) {
        //获取用户的所有角色id
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        return menuService.getUserMenuInfoList(params);
    }
}
