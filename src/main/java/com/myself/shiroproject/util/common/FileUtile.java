package com.myself.shiroproject.util.common;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @ClassName FileUtile
 * @Description
 * @Author vici
 * @Date 2020/6/23 13:49
 * @Version V2.0
 **/
public class FileUtile {

    static Map<String, Function<String, String>> checkResultDispatcher = new HashMap<>();


    public static void main(String[] args) {



        checkResultDispatcher.put("校验1", order -> String.format("对%s执行业务逻辑1", order));
        checkResultDispatcher.put("校验2", order -> String.format("对%s执行业务逻辑2", order));
        checkResultDispatcher.put("校验3", order -> String.format("对%s执行业务逻辑3", order));
        checkResultDispatcher.put("校验4", order -> String.format("对%s执行业务逻辑4", order));
        checkResultDispatcher.put("校验5", order -> String.format("对%s执行业务逻辑5", order));
        checkResultDispatcher.put("校验6", order -> String.format("对%s执行业务逻辑6", order));
        checkResultDispatcher.put("校验7", order -> String.format("对%s执行业务逻辑7", order));
        checkResultDispatcher.put("校验8", order -> String.format("对%s执行业务逻辑8", order));
        checkResultDispatcher.put("校验9", order -> chuli());

        /*Map<String, Function<String, String>> checkResultDispatcher = new HashMap<>();
        checkResultDispatcher.put("校验1", order -> String.format("对%s执行业务逻辑1", order));
        checkResultDispatcher.put("校验2", order -> String.format("对%s执行业务逻辑2", order));
        checkResultDispatcher.put("校验3", order -> String.format("对%s执行业务逻辑3", order));
        checkResultDispatcher.put("校验4", order -> String.format("对%s执行业务逻辑4", order));
        checkResultDispatcher.put("校验5", order -> String.format("对%s执行业务逻辑5", order));
        checkResultDispatcher.put("校验6", order -> String.format("对%s执行业务逻辑6", order));
        checkResultDispatcher.put("校验7", order -> String.format("对%s执行业务逻辑7", order));
        checkResultDispatcher.put("校验8", order -> String.format("对%s执行业务逻辑8", order));
        checkResultDispatcher.put("校验9", order -> String.format("对%s执行业务逻辑9", order));*/

        String check = check("校验9");
        System.out.println("输出结果"+check);

        /*try {
            FileInputStream fileInputStream = new FileInputStream("D:\\static\\evaluation.zip");
            SaveFileByPhysicalDir("D:\\static\\wenjian\\evaluation.zip",fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

    }

    public static String check(String order){
        //从逻辑分派Dispatcher中获得业务逻辑代码，result变量是一段lambda表达式
        Function<String, String> result = checkResultDispatcher.get(order);
        if (result != null) {
            //执行这段表达式获得String类型的结果
            return result.apply(order);
        }
        return "不在处理的逻辑中返回业务错误";
    }

    public static boolean SaveFileByPhysicalDir(String physicalPath,
                                                InputStream istream) {

        boolean flag = false;
        try {
            OutputStream os = new FileOutputStream(physicalPath);
            int readBytes = 0;
            byte buffer[] = new byte[8192];
            while ((readBytes = istream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, readBytes);
            }
            os.close();
            flag = true;
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return flag;
    }




    public static void checkResultDispatcherInit() {

    }

    public static String chuli() {
        return "处理9";
    }
}
