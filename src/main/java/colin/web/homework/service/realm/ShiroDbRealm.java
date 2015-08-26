package colin.web.homework.service.realm;

import colin.web.homework.core.dao.decoratedao.UserDao;
import colin.web.homework.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Created by DELL on 2015/8/26.
 */
public class ShiroDbRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    protected AuthorizationInfo doGetAuthorizationInfo(
            PrincipalCollection principals) {
        System.out.println("fnhioewfjiwe4jr3wiow");
        return null;
    }

    /**
     * 认证回调函数,登录时调用.
     */
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        System.out.println("我是内涵覅文件");
        System.out.println(token.getUsername() + "---" + token.getPassword().toString());
        Map<String, Object> userResult = userService.validateUserInfoByNamePwd(token.getUsername(), token.getPassword().toString());
        if ((Boolean) userResult.get("isExists")) {
            return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword().toString(), userResult.get("callname").toString());
        } else {
            return null;
        }
    }

}
