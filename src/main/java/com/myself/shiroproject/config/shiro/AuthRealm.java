package com.myself.shiroproject.config.shiro;

import com.myself.shiroproject.model.Menu;
import com.myself.shiroproject.model.Role;
import com.myself.shiroproject.model.User;
import com.myself.shiroproject.service.LoginService;
import com.myself.shiroproject.service.PermissionsService;
import com.myself.shiroproject.service.RoleService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName CustomRealm
 * @Description
 * @Author vici
 * @Date 2020/3/24 9:44
 * @Version V2.0
 **/
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionsService permissionsService;


    /**
     * 为用户授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("doGetAuthorizationInfo...");
        //获取前端输入的用户信息，封装为User对象
        User userweb = (User) principalCollection.getPrimaryPrincipal();
        //获取前端输入的用户名
        String username = userweb.getUserName();
        System.out.println("前台输入"+username);
        //根据前端输入的用户名查询数据库中对应的记录
        User selectUser = loginService.selectUser(username);
        //如果数据库中有该用户名对应的记录，就进行授权操作

        List<Role> rolesList = roleService.searchRoleByUser(selectUser.getUserName());
        System.out.println("rolesList"+rolesList.toString());
        List<Menu> permissionsList = permissionsService.selectByPrimaryKey(selectUser.getUserName());
        System.out.println("permissionsList"+permissionsList);

        //获取该用户的角色
        if (selectUser != null){
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //因为addRoles和addStringPermissions方法需要的参数类型是Collection
            //所以先创建两个collection集合
            Collection<String> rolesCollection = new HashSet<String>();
            Collection<String> perStringCollection = new HashSet<String>();

            //遍历集合

            //将每一个role的name装进collection集合
            Set<String> roleSet = rolesList.stream().map(Role::getRoleName).collect(Collectors.toSet());

            //为用户授予角色
            info.setRoles(roleSet);


            //获取每一个Role的permission的set集合
            Set<String> permissionSet = permissionsList.stream().map(Menu::getMenuPerms).collect(Collectors.toSet());
            //为用户授权
            info.addStringPermissions(permissionSet);

            return info;
        }else{
            return null;
        }

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //token携带了用户信息
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        //获取前端输入的用户名
        String userName  = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());

        //根据用户名查询数据库中对应的记录
        User user = loginService.selectUser(userName);
        if (user == null) {
            throw new UnknownAccountException("此用户不存在");
        }
        //当前realm对象的name
        String realmName = getName();
        //盐值
        ByteSource credentialsSalt = ByteSource.Util.bytes(user.getUserName());
        //封装用户信息，构建AuthenticationInfo对象并返回
        AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user, user.getUserPassword(),
                credentialsSalt, realmName);
        return authcInfo;
    }


    public static void main(String[] args) {
        SimpleHash simpleHash = new SimpleHash("MD5", "123456", "guoguo", 1024);

        System.out.println(simpleHash);
    }
}
