package com.bu.admin.utils;

import com.bu.admin.databseproxyuntil.HibernateProxyTypeAdapter;
import com.bu.admin.utils.adapter.DateAdapter;
import com.bu.admin.utils.adapter.FileAdapter;
import com.google.gson.*;

import java.io.File;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ghostWu on 20/4/6.
 */
public class JSONUtils {

    private JSONUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static Gson gson = new GsonBuilder().registerTypeAdapter(
                    // 解决int、long默认转为double
                    Double.class, (JsonSerializer<Double>) (src, typeOfSrc, context) -> {
                        if (src == src.longValue()) {
                            return new JsonPrimitive(src.longValue());
                        } else {
                            return new JsonPrimitive(src);
                        }
                    })
            .registerTypeAdapter(Date.class, new DateAdapter())
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (date, type, jsonSerializationContext) ->
                    new JsonPrimitive(date.toString()))
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, type, jsonDeserializationContext) ->
                    LocalDate.parse(json.getAsString()))
            .registerTypeAdapter(LocalDateTime.class, (JsonSerializer<LocalDateTime>) (dateTime, type, jsonSerializationContext) ->
                    new JsonPrimitive(dateTime.toString()))
            .registerTypeAdapter(LocalDateTime.class, (JsonDeserializer<LocalDateTime>) (json, type, jsonDeserializationContext) ->
                    LocalDateTime.parse(json.getAsString()))
            .registerTypeAdapter(File.class, new FileAdapter())
            .create();

    /**
     * @param obj
     * @return 参数说明
     * @return String    返回类型
     * @throws
     * @Title: toJson
     * @Description: 将对象转换为JSON字符串
     */
    public static String toJson(Object obj) {
        return gson.toJson(obj);
    }

    /**
     * @param json
     * @param classOfT
     * @return 参数说明
     * @return T    返回类型
     * @throws
     * @Title: getJson
     * @Description: 将JSON字符串转换为对象
     */
    public static <T> T getJson(String json, Class<T> classOfT) {
        return gson.fromJson(json, classOfT);
    }

    /**
     * 用法 getJson(json,new TypeToken<List<Bean>>() {}.getType());
     *
     * @param json
     * @Title: getJson
     * @Description: 将JSON字符串转换为集合
     */
    public static <T> List<T> getJson(String json, Type type) {
        //
        return gson.fromJson(json, type);
    }

    /**
     * Map转换成bean
     *
     * @param from
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T toBean(Map<String, Object> from, Class<T> targetClass) {
        String fromJson = toJson(from);
        return getJson(fromJson, targetClass);
    }

    /**
     * List<Map>转换成bean
     * 用法 toBeans(lstObj,new TypeToken<List<Bean>>() {}.getType())
     *
     * @param froms
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> toBeans(List<Map<String, Object>> froms, Type type) {
        String fromsJson = toJson(froms);
        return jsonToList(fromsJson, type);
    }

    /**
     * 用法 jsonToList(json,new TypeToken<List<Bean>>() {}.getType());
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * 对象转化为json string 排除部分属性
     * 用法 toJSONString(Object,{"a"……"b"});
     *
     * @param obj
     * @param excludeNames
     * @return
     */
    public static String toJSONString(Object obj, String... excludeNames) {
        return convert(obj, true, excludeNames);
    }

    /**
     * 对象转化为json string 只包含部分属性
     * 用法 toJSONString(Object,{"a"……"b"});
     *
     * @param obj
     * @param includeNames
     * @return
     */
    public static String toJSONStringInclude(Object obj, String... includeNames) {
        return convert(obj, false, includeNames);
    }

    /**
     * 为jsonObject增加属性
     *
     * @param obj
     * @param extendParams
     * @return
     */
    public static void addParams(JsonObject obj, Object extendParams) {
        if (extendParams instanceof JsonObject || extendParams instanceof Map) {
            addParamsToJsonObject(obj, extendParams);
        } else if (extendParams instanceof String) {
            addParamsToJsonObject(obj, JsonParser.parseString(extendParams.toString()));
        } else {
            addParamsToJsonObject(obj, gson.toJsonTree(extendParams));
        }
    }

    public static JsonElement toJSONElement(Object obj) {
        return gson.toJsonTree(obj);
    }

    public static JsonElement toJSONElement(Object obj, String... excludeNames) {
        return convertJSON(obj, false, true, excludeNames);
    }

    public static JsonElement toJSONInclude(Object obj, String... includeNames) {
        return obj != null ? convertJSON(obj, false, false, includeNames) : null;
    }

    public static JsonElement toJSONIncludeWithLevel(Object obj, String... includeNames) {
        return convertJSON(obj, true, false, includeNames);
    }

    //------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------
    private static JsonElement convertJSON(Object obj, boolean level, boolean exclude, String... names) {
        if (obj instanceof Collection) {
            if (level) {
                return JsonParser.parseString(convert(obj, exclude, names)).getAsJsonObject();
            } else {
                return JsonParser.parseString(convert(obj, exclude, names)).getAsJsonArray();
            }
        } else {
            return JsonParser.parseString(convert(obj, exclude, names)).getAsJsonObject();
        }
    }

    private static String convert(Object obj, boolean exclude, String... names) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }

            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                for (String v : names) {
                    if (f.getName().equals(v))
                        return exclude;
                }
                return !exclude;
            }
        });
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gsonBuilder.registerTypeAdapterFactory(HibernateProxyTypeAdapter.FACTORY);
        Gson gson = gsonBuilder.create();
        return gson.toJson(obj);
    }

    private static void addParamsToJsonObject(JsonObject obj, Object extendParams) {
        for (Map.Entry<String, JsonElement> entry : ((JsonObject) extendParams).entrySet()) {
            obj.add(entry.getKey(), entry.getValue());
        }
    }

}
