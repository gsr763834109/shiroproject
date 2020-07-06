package com.myself.shiroproject.util.synchronizedTest;

import com.google.common.collect.Lists;
import com.myself.shiroproject.model.OilDicVo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BianMaTest
 * @Description
 * @Author vici
 * @Date 2020/7/3 14:59
 * @Version V2.0
 **/
public class BianMaTest {



    public static void main(String[] args) {

        //List<String> ids = Lists.newArrayList("A001", "A002", "A004", "A007");

        List<String> idList = new ArrayList<>();
        idList.add("A001");
        idList.add("A002");
        idList.add("A003");
        idList.add("A006");

        List<OilDicVo> oilDicVos = new ArrayList<>();
        oilDicVos.add(new OilDicVo("A001","89#"));
        oilDicVos.add(new OilDicVo("A002","92#"));
        oilDicVos.add(new OilDicVo("A003","95#"));
        oilDicVos.add(new OilDicVo("A004","98#"));
        oilDicVos.add(new OilDicVo("A005","87#"));
        oilDicVos.add(new OilDicVo("A006","86#"));
        oilDicVos.add(new OilDicVo("A007","85#"));
        //第一种方法
        /*Map<String,String> map = new HashMap<>();
        for (OilDicVo oilDicVo: oilDicVos) {
            map.put(oilDicVo.getOlisDisId(), oilDicVo.getName());
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String id : idList) {
            stringBuilder.append("/").append(map.get(id));
        }
        System.out.println("第一种"+stringBuilder.toString());*/
        //第二种

        Map<String,String> map = new HashMap<>(oilDicVos.size());
        for (OilDicVo oilDicVo: oilDicVos) {
            map.put(oilDicVo.getOlisDisId(), oilDicVo.getName());
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String id : idList) {
            stringBuilder.append("/").append(map.get(id));
        }
        System.out.println("第二种"+stringBuilder.toString());


    }

}
