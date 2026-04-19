package com.bu.admin.utils;

import com.bu.admin.extend.exception.BasicException;
import com.bu.admin.extend.exception.ErrorCodes;
import com.bu.admin.feign.bo.transaction.Transaction;
import com.bu.admin.feign.bo.transaction.TransactionProduct;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.converters.BigDecimalConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.collections4.list.GrowthList;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;


public class BeanCopyUtils {
    private static final Logger logger = LoggerFactory.getLogger(BeanCopyUtils.class);

    private BeanCopyUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static <T> T convertBusinessValueWithoutId(Object resource, Class<T> target) {
        return convertBusinessValue(resource, target, "id");
    }

    public static <T> T convertBusinessValue(Object resource, Class<T> target, String... ignoreProperties) {
        return convertValue(resource, target, mergeProperties(getNullPropertyNames(resource), ignoreProperties));
    }

    public static <T> T convertValue(Object resource, Class<T> target, String... ignoreProperties) {
        try {
            T t = target.getDeclaredConstructor().newInstance();
            if (resource != null) {
                BeanUtilsBean beanUtilsBean = getBeanCopyUtils();
                Map<String, Object> properties = PropertyUtils.describe(resource);
                for (String ignoreProperty : ignoreProperties) {
                    properties.remove(ignoreProperty);
                }
                beanUtilsBean.populate(t, properties);
            }
            return t;
        } catch (Exception e) {
            logger.error("get convert with class error message:", e);
            throw new BasicException(ErrorCodes.DataDeal.CONVERT_OBJ_ERROR);
        }
    }


    public static <T> T convertValue(Object resource, Object target, String... ignoreProperties) {
        try {
            Map<String, Object> properties = PropertyUtils.describe(resource);
            BeanUtilsBean beanUtilsBean = getBeanCopyUtils();
            for (String ignoreProperty : ignoreProperties) {
                properties.remove(ignoreProperty);
            }
            beanUtilsBean.populate(target, properties);
            return (T) target;
        } catch (Exception e) {
            logger.error("get convert error message:", e);
            throw new BasicException(ErrorCodes.DataDeal.CONVERT_OBJ_ERROR);
        }
    }

    public static <T> T convertBusinessValue(Object resource, Object target, String... ignoreProperties) {
        logger.info("set this method to ignore null to skip error");
        return convertValue(resource, target, mergeProperties(getNullPropertyNames(resource), ignoreProperties));
    }

    private static BeanUtilsBean getBeanCopyUtils() {
        Converter dateConverter = new DateConverter(null);
        Converter bigDecimalCon = new BigDecimalConverter(null);
        BeanUtilsBean beanUtilsBean = BeanUtilsBean.getInstance();
        beanUtilsBean.getConvertUtils().register(dateConverter, java.util.Date.class);
        beanUtilsBean.getConvertUtils().register(bigDecimalCon, BigDecimal.class);
        return beanUtilsBean;
    }


    public static <T> T copyPropertiesIgnoreNull(Object resource, Class<T> target, String... ignoreProperties) {
        try {
            T t = target.getDeclaredConstructor().newInstance();
            if (resource != null) {
                convertValue(resource, t, mergeProperties(getNullPropertyNames(resource), ignoreProperties));
            }
            return t;
        } catch (Exception e) {
            logger.error("get  copy Properties error message:", e);
            throw new BasicException(ErrorCodes.DataDeal.CONVERT_OBJ_ERROR);
        }
    }


    public static <T> T copyPropertiesIgnoreNull(Object resource, Object target, String... ignoreProperties) {
        if (resource != null) {
            convertValue(resource, target, mergeProperties(getNullPropertyNames(resource), ignoreProperties));
        }
        return (T) target;
    }


    public static void checkPropertiesNullThrow(Object source, String... checkProperties) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        for (String property : checkProperties) {
            Object srcValue = src.getPropertyValue(property);
            if (srcValue == null || srcValue == "") {
                logger.info("get null param name is [{}]", property);
                throw new BasicException(ErrorCodes.DataDeal.DATA_IS_NULL_OR_BLANK);
            }
        }
    }


    public static String[] checkPropertiesNull(Object source, String... checkProperties) {
        logger.info("check null data..........");
        final BeanWrapper src = new BeanWrapperImpl(source);
        Set<String> emptyNames = new HashSet<>();
        for (String property : checkProperties) {
            Object srcValue = src.getPropertyValue(property);
            if (srcValue == null || StringUtils.isBlank(String.valueOf(srcValue))) {
                logger.info("get src value is null,param is [{}]", property);
                emptyNames.add(property);
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }


    public static Object getPropertyValue(Object source, String paramsName) {
        logger.info("check null data..........");
        final BeanWrapper src = new BeanWrapperImpl(source);

        return src.getPropertyValue(paramsName);
    }


    public static String[] mergeProperties(String[] properties1, String[] properties2) {
        int strLen1 = properties1.length;// 保存第一个数组长度
        int strLen2 = properties2.length;// 保存第二个数组长度
        properties1 = Arrays.copyOf(properties1, strLen1 + strLen2);// 扩容
        System.arraycopy(properties2, 0, properties1, strLen1, strLen2);// 将第二个数组与第一个数组合并
        return properties1;
    }

    public static String[] getClassParams(Class<?> object) {
        // 获取类的所有字段
        Field[] fields = object.getDeclaredFields();
        String[] attributesArray = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            attributesArray[i] = field.getName();
        }
        return attributesArray;
    }

    public static String[] getClassParamsWithIgnores(Class<?> object, String... ignoreProperties) {
        // 获取类的所有字段
        List<String> ignoreList = Arrays.asList(ignoreProperties);
        Field[] fields = object.getDeclaredFields();
        List<String> attributesArray = new GrowthList<>();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (!ignoreList.contains(field.getName())) {
                attributesArray.add(field.getName());
            }
        }
        return attributesArray.toArray(new String[0]);
    }

    public static String[] enumValuesToString(Enum[] e) {
        // 获取类的所有字段
        return Arrays.stream(e).map(Enum::name).toArray(String[]::new);
    }

    public static Map<String, Object> convertMap(Object object) {
        Map<String, Object> map = new HashMap<>();
        Field[] fields = object.getClass().getDeclaredFields();

        for (Field field : fields) {
            try {
                ReflectionUtils.makeAccessible(field);
                map.put(field.getName(), field.get(object));
            } catch (IllegalAccessException e) {
                logger.error("get convert error message:", e);
            }
        }

        return map;
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    public static void main(String[] args) {
        TransactionProduct transaction = new TransactionProduct();
        transaction.setId("123");
        transaction.setCusTemplateId(123);

        Transaction transaction1 = convertBusinessValue(transaction, Transaction.class, "transSn");
        logger.info("get t1 is {}",transaction1);
    }

}
