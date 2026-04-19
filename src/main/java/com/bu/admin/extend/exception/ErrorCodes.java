package com.bu.admin.extend.exception;

public interface ErrorCodes {

    ErrorCode NO_ERROR = new ErrorCode(0, "交互成功"); // 接口交互正常
    ErrorCode UNKNOWN_ERROR = new ErrorCode(666666, "交互错误"); // 接口未知错误

    // api接入
    abstract class ApiEntrance {
        private ApiEntrance() {
            throw new IllegalStateException("ApiEntrance class");
        }

        public static final ErrorCode INVALID_SIGN = new ErrorCode(100001, "签名不正确");
        public static final ErrorCode EMPTY_PARAM = new ErrorCode(100002, "字段为空");
        public static final ErrorCode INVALID_TOKEN = new ErrorCode(100003, "Token不合法");
        public static final ErrorCode USER_NOT_EXISTS = new ErrorCode(100004, "用户不可用");
        public static final ErrorCode INVALID_PURVIEW = new ErrorCode(100005, "权限不合法");
        public static final ErrorCode IDEMPOTENT_REPORT_SUBMIT = new ErrorCode(100006, "重复提交");
        public static final ErrorCode USER_OR_PASSWORD_ERROR = new ErrorCode(100007, "用户名或密码错误");
        public static final ErrorCode INVALID_SERVER_TOKEN = new ErrorCode(100008, "服务器Token不合法");
        public static final ErrorCode REQUEST_ERROR_METHOD = new ErrorCode(100009, "错误的请求方式");
        public static final ErrorCode INVALID_TICKET = new ErrorCode(100010, "错误的票据");
        public static final ErrorCode INVALID_OPERATE = new ErrorCode(100011, "操作越权");
        public static final ErrorCode RESULT_DATA_ERROR = new ErrorCode(100012, "交互数据异常");
        public static final ErrorCode REQUEST_OUT_LIMIT = new ErrorCode(100013, "请求次数超限");
        public static final ErrorCode RSA_ENCRYPT_ERROR = new ErrorCode(100021, "RSA加密错误");
        public static final ErrorCode RSA_DECRYPT_ERROR = new ErrorCode(100021, "RSA解密错误");
        public static final ErrorCode AES_ENCRYPT_ERROR = new ErrorCode(100022, "AES加密错误");
        public static final ErrorCode AES_DECRYPT_ERROR = new ErrorCode(100022, "AES解密错误");
    }

    // 缓存相关异常code描述
    abstract class CACHE {
        private CACHE() {
            throw new IllegalStateException("CACHE class");
        }

        public static final ErrorCode EMPTY_REGION = new ErrorCode(101001, "Region值为空");
        public static final ErrorCode EMPTY_KEY = new ErrorCode(101002, "Key值为空");
        public static final ErrorCode GET_UNSUPPORTED_COLLECTION_OR_MAP = new ErrorCode(101003, "不支持集合或Map的获取");
        public static final ErrorCode EMPTY_CLASS_OBJECT = new ErrorCode(101004, "Class参数为空");
        public static final ErrorCode CACHE_KEY_NOT_EXISTS = new ErrorCode(101005, "Key不存在");
        public static final ErrorCode CACHE_ACQUIRE_LOCK_TIMEOUT = new ErrorCode(101006, "获取锁超时");
        public static final ErrorCode CACHE_UN_SUPPORT_VALUE_TYPE = new ErrorCode(101007, "不支持的值类型");
        public static final ErrorCode RELEASE_BEFORE_ACQUIRE_LOCK = new ErrorCode(101008, "在获取锁之前无法释放锁");
        public static final ErrorCode LOCK_GET_ERROR = new ErrorCode(200009, "获取锁异常");
        public static final ErrorCode GET_STRING_BYTES_ERROR = new ErrorCode(101010, "缓存服务字符串获取字节数据报错");
        public static final ErrorCode GET_BYTES_STRING_ERROR = new ErrorCode(101011, "缓存服务字节数据生成字符串报错");
        public static final ErrorCode NO_VALUE_WITH_KEY = new ErrorCode(101012, "key对应的值不存在");
    }

    abstract class DataDeal {
        private DataDeal() {
            throw new IllegalStateException("DataDeal class");
        }

        public static final ErrorCode CONVERT_OBJ_ERROR = new ErrorCode(107001, "转换数据异常");
        public static final ErrorCode SYSTEM_INIT_ERROR = new ErrorCode(107002, "系统初始化失败");
        public static final ErrorCode IO_ERROR = new ErrorCode(107003, "数据读写异常");
        public static final ErrorCode OBJECT_NOT_EXIST = new ErrorCode(107004, "对象不存在");
        public static final ErrorCode OBJECT_ALREADY_EXIST = new ErrorCode(107005, "对象已存在");
        public static final ErrorCode OBJECT_STATUS_ERROR = new ErrorCode(107006, "对象状态错误");
        public static final ErrorCode DATA_IS_NULL_OR_BLANK = new ErrorCode(107007, "数据为空");
        public static final ErrorCode INPUT_FILE_ERROR = new ErrorCode(107008, "输入文件格式错误");
        public static final ErrorCode EXCEL_DATA_MIS_PARAM = new ErrorCode(107009, "数据文件缺少列");
        public static final ErrorCode EXCEL_NOT_DATA_ERROR = new ErrorCode(107010, "数据文件无数据");
        public static final ErrorCode EXCEL_DATA_ERROR = new ErrorCode(107011, "数据读取错误");
        public static final ErrorCode CONFIG_NOT_EXIST = new ErrorCode(107012, "配置信息不存在");
    }
}
