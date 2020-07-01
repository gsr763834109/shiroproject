package com.myself.shiroproject.controller;

import com.myself.shiroproject.util.common.FileUtile;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @ClassName DownloadController
 * @Description
 * @Author vici
 * @Date 2020/6/18 10:13
 * @Version V2.0
 **/
@RequestMapping("/shiro")
@EnableAutoConfiguration
@Controller
@Slf4j
public class DownloadController {

    /**
     * 添加平台包
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/fileadd",method = RequestMethod.POST)
    public Map<String, Object> addGame(HttpServletRequest request) throws Exception {
        System.out.println("进入文件保存方法");
        HashMap<String, Object> successMap = new HashMap<>();
        try {
            // TODO Auto-generated method stub
            //创建一个通用的多部分解析器
            CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
            //判断 request 是否有文件上传,即多部分请求
            if (multipartResolver.isMultipart(request)) {
                //转换成多部分request
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                //取得request中的所有文件名
                Iterator<String> iter = multiRequest.getFileNames();
                while (iter.hasNext()) {
                    //取得上传文件
                    MultipartFile file = multiRequest.getFile(iter.next());
                    if (file != null) {
                        //取得当前上传文件的文件名称
                        String myFileName = file.getOriginalFilename();
                        //如果名称不为“”,说明该文件存在，否则说明该文件不存在
                        if (myFileName.trim() != "") {
                            System.out.println(myFileName);
                            String fileName = myFileName;// 文件原名称;
                            //定义上传路径
                            try {
                                InputStream inputStream = file.getInputStream();
                                FileUtile.SaveFileByPhysicalDir("D:\\static\\wenjian\\"+myFileName,inputStream);
                                //todo 文件保存到本地
                                //JSONObject ret =aliyunOssService.uploadFile(fileName,platfromConfig.getGame(),file.getInputStream());
                                /*PlatformFileManageGame p=new PlatformFileManageGame();
                                p.setFileName(fileName);
                                PlatformFileManageGame p1= platformFileManageGameService.searchPlatformFileManageGameByParams(p);

                                PlatformFileManageGame p2 = new PlatformFileManageGame();
                                p2.setVersion(ret.optString("version"));
                                p2.setFileAddress( ret.optString("url"));
                                p2.setFileName(fileName);
                                if(p1==null){//没有记录
                                    platformFileManageGameService.addPlatformFileManageGame(p2);
                                }else {
                                    p2.setId(p1.getId());
                                    platformFileManageGameService.updatePlatformFileManageGame(p2);
                                }*/
                            }catch (IllegalStateException e) {
                                // TODO Auto-generated catch block
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
            successMap.put("ok",200);
            return successMap;
        } catch (Exception e) {
            System.out.println("添加包异常！");
            throw new Exception(e);
        }
    }





}
