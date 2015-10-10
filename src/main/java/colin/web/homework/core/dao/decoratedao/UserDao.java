package colin.web.homework.core.dao.decoratedao;

import colin.web.homework.core.pojo.Homework_Role_Entity;
import colin.web.homework.core.pojo.Homework_User_Entity;
import colin.web.homework.core.pojo.Homework_User_Role_Entity;
import colin.web.homework.core.rowmapper.DefaultRowmapper;
import colin.web.homework.core.vo.HomeworkMenuVo;
import colin.web.homework.core.vo.HomeworkUserAuthority;
import colin.web.homework.core.vo.HomeworkUserInfo;
import colin.web.homework.core.vo.HomeworkUserRole;
import colin.web.homework.tools.StringToolsUtils;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by DELL on 2015/8/26.
 */
@Repository
public class UserDao extends DecorateCommnDao {


    /**
     * 根据用户名和密码验证用户的登录信息是否存在
     *
     * @param params
     * @return
     */
    public Map<String, Object> validateUserInfoByNamePwd(Map<String, Object> params) {
        String searchSql = "select user_id,user_callname from homework_user where (user_name,user_password)=(:user_name,:user_password)";
        final Map<String, Object> result = new HashMap<>();
        this.getJdbcTemplate().query(searchSql, params, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                if (resultSet.getString("user_id") != null) {
                    result.put("isExists", true);
                    result.put("callname", resultSet.getString("user_callname"));
                } else {
                    result.put("isExists", false);
                }
            }
        });
        return result;
    }

    /**
     * 根据用户的id查询其可管理的用户内容
     *
     * @return
     */
    public List<HomeworkUserInfo> fetchUserRoleInfo(Map<String, Object> params) {
        List<String> roleIdList = this.fetchUserRoleIdsList(params);
        //获取用户所拥有的全部角色及其子角色
        Set<String> roleIdsList = this.fetchUserRoleIds(roleIdList);
        //查询所有用户对应的角色
        Set<String> userIdList = this.fetchRoleUserIds(roleIdsList);
        //返回所有的管理用户的信息
        return this.fetchManageUserInfoList(userIdList);
    }
    public boolean updateUserRoleConfig(String userId, String[] rolesIds){
        //先删除之前的所有权限，然后重新生成新的权限
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("userId",userId);
        String delUserRole="delete from homework_user_role where user_id=:userId";
        this.getJdbcTemplate().update(delUserRole,params);
        String insertUserRole="insert into homework_user_role(user_role_id,user_id,role_id) values(:userRoleId,:userId,:roleId)";
        for(String roleId : rolesIds){
            params.put("userRoleId", StringToolsUtils.getCommonUUID());
            params.put("roleId",roleId);
            this.getJdbcTemplate().update(insertUserRole,params);
        }
        return true;
    }
    /**
     * 获取用户的角色和权限信息
     *
     * @param userParams
     * @return
     */
    public List<HomeworkUserRole> fetchUserRoleList(Map<String, Object> userParams) {
        //获取用户拥有的角色ID
        List<String> roleIdList = this.fetchUserRoleIdsList(userParams);
        //查询用户所拥有的全部角色ID
        Set<String> allRoleIdList = this.fetchUserRoleIds(roleIdList);
        //查询所有的角色
        List<Homework_Role_Entity> allRoleList = this.seletcObjectByMap(Homework_Role_Entity.class, null, new DefaultRowmapper<Homework_Role_Entity>(Homework_Role_Entity.class.getName()));
        List<HomeworkUserRole> userRoleList = new ArrayList<>();
        for (Homework_Role_Entity role_entity : allRoleList) {
            HomeworkUserRole userRole = new HomeworkUserRole();
            userRole.setRole_id(role_entity.getRole_id());
            userRole.setRole_name(role_entity.getRole_name());
            Map<String,Object> userRoleDetail=this.fetchUserRoleAuthority(role_entity.getRole_id());
            userRole.setAuthorityList((List<HomeworkUserAuthority>) userRoleDetail.get("authorityList"));
            List<HomeworkMenuVo> menuVoList=new ArrayList<>();
            menuVoList.addAll((Set<HomeworkMenuVo>)userRoleDetail.get("menuList"));
            userRole.setMenuList(menuVoList);
            if(allRoleIdList.contains(role_entity.getRole_id())){
                userRole.setOwned(true);
            }else{
                userRole.setOwned(false);
            }
            userRoleList.add(userRole);
        }
        return userRoleList;
    }

    /**
     * 返回用户的角色IDList
     *
     * @param params
     * @return
     */
    public List<String> fetchUserRoleIdsList(Map<String, Object> params) {
        String searchUserRoleSql = "select role_id from homework_user_role where user_id=:user_id";
        //加载用户的返回role_id
        final List<String> roleIdList = new ArrayList<String>();
        super.getJdbcTemplate().query(searchUserRoleSql, params, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                roleIdList.add(resultSet.getString("role_id"));
            }
        });
        return roleIdList;
    }

    /**
     * 查询对应用户角色的信息
     *
     * @param roleIdList
     * @return
     */
    private Set<String> fetchUserRoleIds(List<String> roleIdList) {
        String searchRoleListSql = "select getRoleList(:role_id)";
        final Set<String> roleIdsList = new HashSet<String>();
        Map<String, Object> roleParams = new HashMap<String, Object>();
        for (String roleId : roleIdList) {
            roleParams.put("role_id", roleId);
            super.getJdbcTemplate().query(searchRoleListSql, roleParams, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    String roleIdList = resultSet.getString(1);
                    for (String roleId : roleIdList.split(",")) {
                        roleIdsList.add(roleId);
                    }
                }
            });
        }
        return roleIdsList;
    }

    /**
     * 查询所有的角色对应的用户ID
     *
     * @param roleIdsList
     * @return
     */
    private Set<String> fetchRoleUserIds(Set<String> roleIdsList) {
        //在user_role表里查询所有的userIdList
        String searchUserRoleInfo = "select user_id from homework_user_role where role_id=:role_id";
        Map<String, Object> searchUserRoleParams = new HashMap<>();
        final Set<String> userIdList = new HashSet<>();
        for (String roleId : roleIdsList) {
            searchUserRoleParams.put("role_id", roleId);
            super.getJdbcTemplate().query(searchUserRoleInfo, searchUserRoleParams, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    userIdList.add(resultSet.getString(1));
                }
            });
        }
        return userIdList;
    }

    /**
     * 获取管理用户的List
     *
     * @param userIdList
     * @return
     */
    private List<HomeworkUserInfo> fetchManageUserInfoList(Set<String> userIdList) {
        //查询满足条件的用户信息
        String searchUserInfo = "select user_id,user_name,user_callname,user_email,user_phone,user_createtime,user_logintime from homework_user where user_id=:user_id";
        Map<String, Object> userParams = new HashMap<String, Object>();
        final List<HomeworkUserInfo> userInfoList = new ArrayList<>();
        for (String userId : userIdList) {
            userParams.put("user_id", userId);
            super.getJdbcTemplate().query(searchUserInfo, userParams, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    HomeworkUserInfo userInfo = new HomeworkUserInfo();
                    userInfo.setUser_id(resultSet.getString("user_id"));
                    userInfo.setUser_phone(resultSet.getString("user_phone"));
                    userInfo.setUser_name(resultSet.getString("user_name"));
                    userInfo.setUser_callname(resultSet.getString("user_callname"));
                    userInfo.setUser_email(resultSet.getString("user_email"));
                    userInfo.setUser_createtime(resultSet.getString("user_createtime"));
                    userInfo.setUser_logintime(resultSet.getString("user_logintime"));
                    userInfoList.add(userInfo);
                }
            });
        }
        return userInfoList;
    }

    /**
     * 获取角色对应的权限内容，此处对应的是菜单和操作权限
     *
     * @param roleId
     * @return
     */
    private Map<String, Object> fetchUserRoleAuthority(String roleId) {
        //当前用户的权限id
        Set<String> authoritySet = this.fetchUserAuthorityIds(roleId);
        //查询用户的权限
        Set<String> authorityAllSet = this.fetchAllUserAuthorityIds(authoritySet);
        //查询权限的详细信息
        List<HomeworkUserAuthority> userAuthorityList = this.fetchUserAuthorityDetail(authorityAllSet);
        //查询当前权限对应的菜单
        Set<HomeworkMenuVo> menuVoList =this.fetchUserMenuDetail(roleId);
        Map<String,Object> resultMap=new HashMap<>();
        resultMap.put("authorityList",userAuthorityList);
        resultMap.put("menuList",menuVoList);
        return resultMap;
    }

    /**
     * 返回当前用户的roleId
     *
     * @param roleId
     * @return
     */
    private Set<String> fetchUserAuthorityIds(String roleId) {
        Map<String, Object> roleParams = new HashMap<>();
        roleParams.put("role_id", roleId);
        String searchRoleAuthority = "select authority_id from homework_role_authority where role_id=:role_id";
        final Set<String> authoritySet = new HashSet<>();
        super.getJdbcTemplate().query(searchRoleAuthority, roleParams, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                authoritySet.add(resultSet.getString(1));
            }
        });
        return authoritySet;
    }

    /**
     * 返回当前权限及其子权限
     *
     * @param authoritySet
     * @return
     */
    private Set<String> fetchAllUserAuthorityIds(Set<String> authoritySet) {
        String searchUserAuthority = "select getAuthorityList(:authority_id)";
        Map<String, Object> authorityParams = new HashMap<>();
        final Set<String> authorityAllSet = new HashSet<>();
        for (String authorityId : authoritySet) {
            authorityParams.put("authority_id", authorityId);
            super.getJdbcTemplate().query(searchUserAuthority, authorityParams, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    for (String authorityId : resultSet.getString(1).split(","))
                        authorityAllSet.add(authorityId);
                }
            });
        }
        return authorityAllSet;
    }

    /**
     * 返回权限具体信息类
     *
     * @param authorityAllSet
     * @return
     */
    private List<HomeworkUserAuthority> fetchUserAuthorityDetail(Set<String> authorityAllSet) {
        String searchUserAuthorityDetail = "select authority_name,authoruty_description from homework_authority where authority_id=:authority_id";
        Map<String, Object> authorityDetailParams = new HashMap<>();
        final List<HomeworkUserAuthority> authorityList = new ArrayList<>();
        for (String authorityId : authorityAllSet) {
            authorityDetailParams.put("authority_id", authorityId);
            super.getJdbcTemplate().query(searchUserAuthorityDetail, authorityDetailParams, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    HomeworkUserAuthority userAuthority = new HomeworkUserAuthority();
                    userAuthority.setAuthority_name(resultSet.getString(1));
                    userAuthority.setAuthoruty_description(resultSet.getString(2));
                    authorityList.add(userAuthority);
                }
            });
        }
        return authorityList;
    }

    /**
     * 获取当前用户的角色ID
     *
     * @param roleId
     * @return
     */
    private Set<HomeworkMenuVo> fetchUserMenuDetail(String roleId) {
        String searchUserMenuIds = "select menu_id from homework_role_menu where role_id=:role_id";
        Map<String, Object> menuParams = new HashMap<>();
        final Set<String> menuIdSet = new HashSet<>();
        menuParams.put("role_id", roleId);
        super.getJdbcTemplate().query(searchUserMenuIds, menuParams, new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet resultSet) throws SQLException {
                menuIdSet.add(resultSet.getString(1));
            }
        });
        String searchUserRoleMenuIds = "select getMenuList(:menuId)";
        Map<String, Object> menuRoleParams = new HashMap<>();
        final Set<String> menuIds = new HashSet<>();
        for (String menuId : menuIdSet) {
            menuRoleParams.put("menuId", menuId);
            super.getJdbcTemplate().query(searchUserRoleMenuIds, menuRoleParams, new RowCallbackHandler() {
                @Override
                public void processRow(ResultSet resultSet) throws SQLException {
                    menuIds.add(resultSet.getString(1));
                }
            });
        }
        String searchUserMenuDetail = "select menu_name from homework_menu where menu_id=:menu_id";
        Map<String, Object> menuDetailParams = new HashMap<>();
        final Set<HomeworkMenuVo> menuVoList = new HashSet<>();
        for (String menuId : menuIds) {
            for(String menuArray:menuId.split(",")){
                menuDetailParams.put("menu_id", menuArray);
                super.getJdbcTemplate().query(searchUserMenuDetail, menuDetailParams, new RowCallbackHandler() {
                    @Override
                    public void processRow(ResultSet resultSet) throws SQLException {
                        HomeworkMenuVo menuVo = new HomeworkMenuVo();
                        menuVo.setMenu_name(resultSet.getString(1));
                        menuVoList.add(menuVo);
                    }
                });
            }
        }
        return menuVoList;
    }
}
