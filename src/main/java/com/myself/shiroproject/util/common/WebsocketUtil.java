package com.myself.shiroproject.util.common;

import net.sf.saxon.expr.instruct.Copy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @ClassName WebsocketUtil
 * @Description
 * @Author vici
 * @Date 2020/6/16 13:55
 * @Version V2.0
 **/
@ServerEndpoint("/shiro/websocket/{hospitalId}")
@Component
public class WebsocketUtil {

    @Autowired
    //设计成线程安全，静态变量，用来记录当前的连接数。
    //private static int onlineCount = 0;

    //private final Logger log = LoggerFactory.getLogger(getClass());
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    //private static int onlineCount = 0;
    private static ConcurrentHashMap<String,Integer> onlineHashMap =  new ConcurrentHashMap<String, Integer>();
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static ConcurrentHashMap<WebsocketUtil,String> webSocketSet = new ConcurrentHashMap<WebsocketUtil,String>();

    //与某个客户端连接会话，需要通过它来给客户端发送数据
    private Session session;

    /**
     * 连接建立成功调用的方法
     * @param session
     */
    @OnOpen
    public void onOpen(@PathParam(value = "hospitalId") String hospitalId,Session session){
        this.session = session;
        webSocketSet.put(this,hospitalId);
        addOnlineCount(hospitalId);
      /*  log.info(hospitalId+"机构，当前在线人数为"+getOnlineCount(hospitalId));
        log.info("数据内容"+webSocketSet.get(this));*/
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void  onClose(){
        String id = webSocketSet.get(this);
        webSocketSet.remove(this);
        subOnliCount(id);
        //log.info("当前在线人数是"+WebsocketUtil.onlineHashMap.get(id));
    }

    /**
     * 收到客户端消息后调用
     */
    @OnMessage
    public  void onMessage(String message, Session session){
        //log.info("来自客户端的消息"+message);
        String id = webSocketSet.get(this);
        //log.info("连接数量"+WebsocketUtil.onlineHashMap.get(id));
        for (Map.Entry<WebsocketUtil,String> entry: webSocketSet.entrySet()){
            try {
                entry.getKey().sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }


    /**
     * 实现服务器的主动推送
     * @param message
     */
    public void sendMessage(String message) throws IOException {
        this.session.getBasicRemote().sendText(message);
    }

    /**
     * 群发自定义消息
     */
    public void sendInfo(String message,@PathParam("token") String token) throws  IOException{
        for(Map.Entry<WebsocketUtil,String> entry: webSocketSet.entrySet()){

        }
    }

    /**
     * 查询在线人数
     * @param id
     * @return
     */
    public static synchronized  int getOnlineCount(String id){
        if(WebsocketUtil.onlineHashMap.get(id)==null){
            return 0;
        }
        return WebsocketUtil.onlineHashMap.get(id);
    }

    public static  synchronized  void addOnlineCount(String id){
        System.out.println("机构"+id+"在线数增加");
        Integer i = WebsocketUtil.onlineHashMap.get(id);
        if(i==null){
            i=0;
        }
        i++;
        WebsocketUtil.onlineHashMap.put(id,i);
    }

    /**
     * 断开连接
     */
    public static synchronized  void subOnliCount(String id){
        Integer count = WebsocketUtil.onlineHashMap.get(id);
        //Integer i = WebsocketUtil.onlineCount;
        count--;
        WebsocketUtil.onlineHashMap.put(id,count);
    }


    public static synchronized  ConcurrentHashMap<String, Integer> getOnlieAll(){
        return  WebsocketUtil.onlineHashMap;
    }








}
