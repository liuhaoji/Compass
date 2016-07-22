package com.lenovo.compass.compass.utils;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class GsonUtil {
    private static GsonBuilder builder = new GsonBuilder();
    private static Gson gson = builder.create();

    /**
     * 将一个实体类对象转化成JSON数据格式
     *
     * @param bean 实体类对象
     * @return JSON 数据格式字符串
     */
    public static String bean2Json(Object bean) {
        return gson.toJson(bean);
    }

    /**
     * 将Json格式的字符串转换成指定的对象返回
     *
     * @param <T>       泛型标识
     * @param jsonStr   要转化的Json格式的字符串
     * @param beanClass 指定转化对象类型
     * @return 转化后的对象
     */
    public static <T> T json2Bean(String jsonStr, Class<T> beanClass) {
        return gson.fromJson(jsonStr, beanClass);
    }

    /**
     * 将Json格式的字符串转换成指定的对象返回
     *
     * @param <T>       泛型标识
     * @param beanClass 指定转化对象类型
     * @return 转化后的对象
     */
    public static <T> T json2Bean(JsonObject jsonObject, Class<T> beanClass) {
        return gson.fromJson(jsonObject, beanClass);
    }

    /**
     * 将泛型列表集合转换成JSON字符串
     *
     * @param <T>  泛型标识
     * @param list 集合对象
     * @return
     */
    public static <T> String list2Json(List<T> list) {
        Type type = new TypeToken<List<T>>() {
        }.getType();
        return gson.toJson(list, type);
    }

    /**
     * 将Json格式的字符串转换成String类型的List集合
     *
     * @param jsonString JSON数据格式字符串
     * @return List集合
     */
    public static List<String> json2List(String jsonString) {
        return json2List(jsonString, new TypeToken<List<String>>() {
        });
    }

    /**
     * 将Json格式的字符串转换成指定对象组成的List返回
     * <br>例如：List<"String"> list = json2List("……", new TypeToken<"List<"String">">(){});
     * <br>		List<"Map<"Integer, Object">"> maplist = json2List("……", new TypeToken<"List<"Map<"Integer, Object">">">(){});
     *
     * @param <T>        泛型标识
     * @param jsonString JSON数据格式字符串
     * @param typeToken  目标类型器，标识需要转换成的目标List对象
     * @return
     */
    public static <T> List<T> json2List(String jsonString, TypeToken<List<T>> typeToken) {
        Type type = typeToken.getType();
        return gson.fromJson(jsonString, type);
    }

    /**
     * 把Map对象转换成Json字符串
     *
     * @param <K> Map的key的泛型标识
     * @param <V> Map的value的泛型标识
     * @param map 待转换的Map
     * @return 转换后的JSON字符串
     */
    public static <K, V> String map2Json(Map<K, V> map) {
        Type type = new TypeToken<Map<K, V>>() {
        }.getType();
        return gson.toJson(map, type);
    }

    /**
     * 将Json格式的字符串转换指定类型的Map对象
     *
     * @param <K>        Map的key的泛型标识
     * @param <V>        Map的value的泛型标识
     * @param jsonString JSON数据格式字符串
     * @param keyClass   Map的key的类型
     * @param valueClass Map的value的类型
     * @return 指定类型的Map对象
     */
    public static <K, V> Map<K, V> json2Map(String jsonString, Class<K> keyClass, Class<V> valueClass) {
        return json2Map(jsonString, new TypeToken<Map<K, V>>() {
        });
    }

    /**
     * 把Json格式的字符串转换成指定复杂类型的Map对象
     *
     * @param <K>        Map的key的泛型标识
     * @param <V>        Map的value的泛型标识
     * @param jsonString JSON数据格式字符串
     * @param typeToken  目标类型器，标识需要转换成的目标Map对象
     * @return 指定类型的复杂Map对象
     */
    public static <K, V> Map<K, V> json2Map(String jsonString, TypeToken<Map<K, V>> typeToken) {
        Type type = typeToken.getType();
        return gson.fromJson(jsonString, type);
    }

}