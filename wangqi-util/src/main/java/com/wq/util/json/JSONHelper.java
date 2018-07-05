package com.wq.util.json;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * JSON和JAVA的POJO的相互转换
 * <p>
 * JSONHelper.java
 */
public final class JSONHelper {

    @JSONField(name = "name", format = "yyyy-MM-dd HH-mm-ss")
    private String name;

    // 将JSON转换成数组,其中valueClz为数组中存放的对象的Class
    public static Object json2Array(String json, Class valueClz) {
        JSONArray jsonArray = JSONArray.parseArray(json);
        return JSONArray.toJavaObject(jsonArray, valueClz);
    }

    public static void main(String[] args) {
        Object o = json2Array("[1,2,3]", String.class);
        System.out.println(o);
    }

    // 将Collection转换成JSON
//    public static String collection2json(Object object) {
//        JSONArray jsonArray = JSONArray.fromObject(object);
//        return jsonArray.toString();
//    }

    // 将JSON转换成Collection,其中collectionClz为Collection具体子类的Class,
    // valueClz为Collection中存放的对象的Class
    public static Collection json2Collection(String json, Class collectionClz,
                                             Class valueClz) {
//		JSONArray jsonArray = JSONArray.fromObject(json);
//		return JSONArray.toCollection(jsonArray, valueClz);
        return null;
    }


    // 将JSON转换成Map,其中valueClz为Map中value的Class,keyArray为Map的key
    public static Map json2Map(Object[] keyArray, String json, Class valueClz) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        Map classMap = new HashMap();

        for (int i = 0; i < keyArray.length; i++) {
            classMap.put(keyArray[i], valueClz);
        }

//		return (Map) JSONObject.toBean(jsonObject, Map.class, classMap);
        return JSONObject.toJavaObject(jsonObject, Map.class);
    }

    // 将POJO转换成JSON
    public static String bean2json(Object object) {
        return JSONObject.toJSONString(object);
    }

    // 将JSON转换成POJO,其中beanClz为POJO的Class
    public static Object json2Object(String json, Class beanClz) {
        return JSONObject.toJavaObject(JSONObject.parseObject(json), beanClz);
    }

    // 将String转换成JSON
    public static String string2json(String key, String value) {
        JSONObject object = new JSONObject();
        object.put(key, value);
        return object.toString();
    }

    // 将JSON转换成String
    public static String json2String(String json, String key) {
        JSONObject jsonObject = JSONObject.parseObject(json);
        return jsonObject.get(key).toString();
    }
}