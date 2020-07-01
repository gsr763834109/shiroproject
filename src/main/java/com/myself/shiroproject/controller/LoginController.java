package com.myself.shiroproject.controller;

import com.myself.shiroproject.dao.RegistrantMapper;
import com.myself.shiroproject.model.Menu;
import com.myself.shiroproject.model.Role;
import com.myself.shiroproject.model.User;
import com.myself.shiroproject.service.LoginService;
import com.myself.shiroproject.service.PermissionsService;
import com.myself.shiroproject.util.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;

/**
 * @ClassName LoginController
 * @Description
 * @Author vici
 * @Date 2020/3/24 15:40
 * @Version V2.0
 **/
@RequestMapping("/shiro")
@EnableAutoConfiguration
@Controller
@Slf4j
public class LoginController {

    @Autowired
    private  RedisUtil redisUtil;

    @Autowired
    private LoginService loginService;

    @Autowired
    private PermissionsService permissionsService;


    @Autowired
    private RegistrantMapper registrantMapper;

    @RequestMapping(value = "/searchUser")
    @ResponseBody
    public User searchUser(){
        User admin = loginService.selectUser("admin");
        return  admin;
    }


    @RequestMapping(value = "/loginUser",method = RequestMethod.POST)
    @ResponseBody
    public  String loginUser(User user,Model model){

        log.info("userName:"+user.getUserName()+"----"+"userPassword"+user.getUserPassword());
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getUserPassword());
        Subject subject = SecurityUtils.getSubject();
        usernamePasswordToken.setRememberMe(true);
        try{
            subject.login(usernamePasswordToken);

            return "0";
        }catch (UnknownAccountException e){
            System.out.println("用户名不存在");
            return "-2";
        }catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
            return "-1";
        }

    }

    /**
     *通过登录的信息用户名
     * @param user
     * @return
     */
    @RequestMapping(value = "/searchAllMenu",method = RequestMethod.POST)
    @ResponseBody
    public List searchAllMenu(User user){
        System.out.println("查询用户的所有菜单");
        user.setUserName("admin");
        List<Menu> permissionsList = permissionsService.selectByPrimaryKey(user.getUserName());
        log.info("权限集合"+permissionsList);
        return permissionsList;
    }


    /**
     * 获取json值
     * @param
     * @return
     */
    @RequestMapping(value = "/searchMenuJson",method = RequestMethod.GET)
    @ResponseBody
    public String searchMenuJson(){
        System.out.println("查询用户的所有菜单");
        return "{\n" +
                "\t\"code\": 0,\n" +
                "\t\"msg\": \"ok\",\n" +
                "\t\"data\": [\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 2,\n" +
                "\t\t\t\"parentId\": null,\n" +
                "\t\t\t\"code\": \"cms\",\n" +
                "\t\t\t\"title\": \"内容管理\",\n" +
                "\t\t\t\"icon\": \"layui-icon-read\",\n" +
                "\t\t\t\"enable\": true,\n" +
                "\t\t\t\"href\": \"\",\n" +
                "\t\t\t\"children\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 21,\n" +
                "\t\t\t\t\t\"parentId\": 2,\n" +
                "\t\t\t\t\t\"code\": \"channelList\",\n" +
                "\t\t\t\t\t\"title\": \"网站导航设置\",\n" +
                "\t\t\t\t\t\"icon\": \"layui-icon-menu-fill\",\n" +
                "\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\"href\": \"page/channel/list.html\"\n" +
                "\t\t\t\t}, {\n" +
                "\t\t\t\t\t\"id\": 22,\n" +
                "\t\t\t\t\t\"parentId\": 2,\n" +
                "\t\t\t\t\t\"code\": \"categoryList\",\n" +
                "\t\t\t\t\t\"title\": \"栏目分类设置\",\n" +
                "\t\t\t\t\t\"icon\": \"layui-icon-util\",\n" +
                "\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\"href\": \"page/category/list.html\"\n" +
                "\t\t\t\t}, \n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 23,\n" +
                "\t\t\t\t\t\"parentId\": 2,\n" +
                "\t\t\t\t\t\"code\": \"post\",\n" +
                "\t\t\t\t\t\"title\": \"文章管理\",\n" +
                "\t\t\t\t\t\"icon\": \"layui-icon-file\",\n" +
                "\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\"href\": \"\",\n" +
                "\t\t\t\t\t\"children\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 231,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 23,\n" +
                "\t\t\t\t\t\t\t\"code\": \"postAdd\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"发文章\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-add-circle\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/post/add.html\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 232,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 23,\n" +
                "\t\t\t\t\t\t\t\"code\": \"postList\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"文章管理\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-file\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/post/list.html\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 24,\n" +
                "\t\t\t\t\t\"parentId\": 2,\n" +
                "\t\t\t\t\t\"code\": \"gallery\",\n" +
                "\t\t\t\t\t\"title\": \"图集管理\",\n" +
                "\t\t\t\t\t\"icon\": \"layui-icon-carousel\",\n" +
                "\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\"href\": \"\",\n" +
                "\t\t\t\t\t\"children\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 241,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 24,\n" +
                "\t\t\t\t\t\t\t\"code\": \"galleryAdd\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"发图集\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-add-circle\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/gallery/add.html\"\n" +
                "\t\t\t\t\t\t},{\n" +
                "\t\t\t\t\t\t\t\"id\": 242,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 24,\n" +
                "\t\t\t\t\t\t\t\"code\": \"galleryList\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"图集管理\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-carousel\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/gallery/list.html\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 25,\n" +
                "\t\t\t\t\t\"parentId\": 2,\n" +
                "\t\t\t\t\t\"code\": \"video\",\n" +
                "\t\t\t\t\t\"title\": \"视频管理\",\n" +
                "\t\t\t\t\t\"icon\": \"layui-icon-video\",\n" +
                "\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\"href\": \"\",\n" +
                "\t\t\t\t\t\"children\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 251,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 25,\n" +
                "\t\t\t\t\t\t\t\"code\": \"videoAdd\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"发视频\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-add-circle\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/video/add.html\"\n" +
                "\t\t\t\t\t\t},{\n" +
                "\t\t\t\t\t\t\t\"id\": 252,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 25,\n" +
                "\t\t\t\t\t\t\t\"code\": \"videoList\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"视频管理\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-video\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/post/list.html\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 29,\n" +
                "\t\t\t\t\t\"parentId\": 2,\n" +
                "\t\t\t\t\t\"code\": \"matter\",\n" +
                "\t\t\t\t\t\"title\": \"素材管理\",\n" +
                "\t\t\t\t\t\"icon\": \"layui-icon-star\",\n" +
                "\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\"href\": \"\",\n" +
                "\t\t\t\t\t\"children\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 291,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 29,\n" +
                "\t\t\t\t\t\t\t\"code\": \"matterList\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"素材概览\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-template\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/post/list.html\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"id\": 9,\n" +
                "\t\t\t\"parentId\": null,\n" +
                "\t\t\t\"code\": \"system\",\n" +
                "\t\t\t\"title\": \"系统管理\",\n" +
                "\t\t\t\"icon\": \"layui-icon-set\",\n" +
                "\t\t\t\"enable\": true,\n" +
                "\t\t\t\"href\": \"\",\n" +
                "\t\t\t\"children\": [\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 91,\n" +
                "\t\t\t\t\t\"parentId\": 9,\n" +
                "\t\t\t\t\t\"code\": \"systemSettings\",\n" +
                "\t\t\t\t\t\"title\": \"系统设置\",\n" +
                "\t\t\t\t\t\"icon\": \"layui-icon-util\",\n" +
                "\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\"href\": \"\",\n" +
                "\t\t\t\t\t\"children\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 911,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 91,\n" +
                "\t\t\t\t\t\t\t\"code\": \"website\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"系统基本参数\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-form\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/sysConfig/index.html\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 912,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 91,\n" +
                "\t\t\t\t\t\t\t\"code\": \"dictionaryList\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"数据字典管理\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-table\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/dictionary/list.html\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 92,\n" +
                "\t\t\t\t\t\"parentId\": 9,\n" +
                "\t\t\t\t\t\"code\": \"navigation\",\n" +
                "\t\t\t\t\t\"title\": \"系统菜单管理\",\n" +
                "\t\t\t\t\t\"icon\": \"layui-icon-release\",\n" +
                "\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\"href\": \"\",\n" +
                "\t\t\t\t\t\"children\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 921,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 92,\n" +
                "\t\t\t\t\t\t\t\"code\": \"navigationAdd\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"新建后台导航菜单\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-add-circle\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/navigation/add.html\"\n" +
                "\t\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\t\"id\": 922,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 92,\n" +
                "\t\t\t\t\t\t\t\"code\": \"navigationList\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"后台导航菜单列表\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-file\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/navigation/list.html\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 93,\n" +
                "\t\t\t\t\t\"parentId\": 9,\n" +
                "\t\t\t\t\t\"code\": \"orgUnit\",\n" +
                "\t\t\t\t\t\"title\": \"组织结构管理\",\n" +
                "\t\t\t\t\t\"icon\": \"layui-icon-component\",\n" +
                "\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\"href\": \"\",\n" +
                "\t\t\t\t\t\"children\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 931,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 93,\n" +
                "\t\t\t\t\t\t\t\"code\": \"orgAdd\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"新建组织结构\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-add-circle\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/orgUnit/add.html\"\n" +
                "\t\t\t\t\t\t}, {\n" +
                "\t\t\t\t\t\t\t\"id\": 932,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 93,\n" +
                "\t\t\t\t\t\t\t\"code\": \"orgList\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"组织结构列表\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-tree\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/orgUnit/list.html\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 94,\n" +
                "\t\t\t\t\t\"parentId\": 9,\n" +
                "\t\t\t\t\t\"code\": \"accountUser\",\n" +
                "\t\t\t\t\t\"title\": \"用户及角色管理\",\n" +
                "\t\t\t\t\t\"icon\": \"layui-icon-user\",\n" +
                "\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\"href\": \"\",\n" +
                "\t\t\t\t\t\"children\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 941,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 94,\n" +
                "\t\t\t\t\t\t\t\"code\": \"accountUserAdd\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"新建管理员\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-add-circle\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/accountUser/list.html\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 942,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 94,\n" +
                "\t\t\t\t\t\t\t\"code\": \"accountUserList\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"管理员列表\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-user\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/accountUser/list.html\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 943,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 94,\n" +
                "\t\t\t\t\t\t\t\"code\": \"accountRoleAdd\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"新建角色\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-add-circle\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/accountRole/list.html\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 944,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 94,\n" +
                "\t\t\t\t\t\t\t\"code\": \"accountRoleList\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"角色列表\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-group\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/accountRole/list.html\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"id\": 99,\n" +
                "\t\t\t\t\t\"parentId\": 9,\n" +
                "\t\t\t\t\t\"code\": \"morePages\",\n" +
                "\t\t\t\t\t\"title\": \"其他页面\",\n" +
                "\t\t\t\t\t\"icon\": \"layui-icon-layer\",\n" +
                "\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\"href\": \"\",\n" +
                "\t\t\t\t\t\"children\": [\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 991,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 99,\n" +
                "\t\t\t\t\t\t\t\"code\": \"cssFormat\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"CSS格式化\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-fonts-code\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/morepages/cssformat.html\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 992,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 992,\n" +
                "\t\t\t\t\t\t\t\"code\": \"layui-select-input\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"Layui select 可输入\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-note\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/morepages/layui-select-input.html\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 993,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 99,\n" +
                "\t\t\t\t\t\t\t\"code\": \"403\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"403 Forbidden页面\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-face-cry\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/403.html\"\n" +
                "\t\t\t\t\t\t},\n" +
                "\t\t\t\t\t\t{\n" +
                "\t\t\t\t\t\t\t\"id\": 994,\n" +
                "\t\t\t\t\t\t\t\"parentId\": 99,\n" +
                "\t\t\t\t\t\t\t\"code\": \"404\",\n" +
                "\t\t\t\t\t\t\t\"title\": \"404 Not Found页面\",\n" +
                "\t\t\t\t\t\t\t\"icon\": \"layui-icon-404\",\n" +
                "\t\t\t\t\t\t\t\"enable\": true,\n" +
                "\t\t\t\t\t\t\t\"href\": \"page/404.html\"\n" +
                "\t\t\t\t\t\t}\n" +
                "\t\t\t\t\t]\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";
    }


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public  String login(User user){
        //List<RegistrantMapper> registrantMappers = registrantMapper.selectAll();
        //System.out.println("测试数据"+registrantMappers);
        return "login";
    }

    @RequestMapping(value = "/error")
    public  String login(){
        return "error";
    }

    @RequestMapping(value = "/index")
    public  String index(){
        return "index";
    }

    @RequestMapping(value = "/show")
    public  String show(Model model){
        Subject subject  = SecurityUtils.getSubject();
        User user = (User)subject.getPrincipal();
        model.addAttribute("userName",user.getUserName());
        return "show";
    }

    @RequestMapping(value = "/welcome")
    public  String welcome(){
        return "welcome";
    }

    @RequestMapping(value = "/upload")
    public  String upload(){
        return "upload";
    }

    @RequestMapping(value = "/websocket/{userId}")
    public  String websocket(Model model,@PathVariable(value = "userId") String userId){
        System.out.println(userId);
        return "websocket";
    }

    @RequestMapping(value = "/system")
    public  String system(){
        return "system";
    }

    @RequestMapping(value = "/add")
    public  String add(){
        return "add";
    }

    @RequestMapping("/redisadd")
    @ResponseBody
    public String redisAdd(){
        //redisUtil.set("linshi","123456");
        User admin = loginService.selectUser("admin");
        System.out.println("admin"+admin.toString());
        return "添加";
    }



    @RequestMapping("/redisdel")
    @ResponseBody
    public String redisDel(){
        redisUtil.del("linshi");
        return "删除";
    }

}
