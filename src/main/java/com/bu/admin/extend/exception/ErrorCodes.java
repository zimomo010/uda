package com.bu.admin.extend.exception;

public interface ErrorCodes {

    ErrorCode NO_ERROR = new ErrorCode(0, "success");
    ErrorCode UNKNOWN_ERROR = new ErrorCode(666666, "user name or password error");

    // api接入
    abstract class ApiEntrance {
        private ApiEntrance() {
            throw new IllegalStateException("ApiEntrance class");
        }

        public static final ErrorCode EMPTY_PARAM = new ErrorCode(100002, "empty param");
        public static final ErrorCode INVALID_TOKEN = new ErrorCode(100003, "Token invalid");
        public static final ErrorCode INVALID_SERVER_TOKEN = new ErrorCode(100008, "server Token invalid");
        public static final ErrorCode REQUEST_ERROR_METHOD = new ErrorCode(100009, "request method invalid");
        public static final ErrorCode RESULT_DATA_ERROR = new ErrorCode(100012, "result data error");
    }

    abstract class CACHE {
        private CACHE() {
            throw new IllegalStateException("CACHE class");
        }

        public static final ErrorCode EMPTY_REGION = new ErrorCode(101001, "Region empty");
        public static final ErrorCode EMPTY_KEY = new ErrorCode(101002, "Key empty");
        public static final ErrorCode GET_UNSUPPORTED_COLLECTION_OR_MAP = new ErrorCode(101003, "not support collection or Map");
        public static final ErrorCode EMPTY_CLASS_OBJECT = new ErrorCode(101004, "class object empty");
        public static final ErrorCode GET_STRING_BYTES_ERROR = new ErrorCode(101010, "string bytes error");
    }

    abstract class DataDeal {
        private DataDeal() {
            throw new IllegalStateException("DataDeal class");
        }

        public static final ErrorCode CONVERT_OBJ_ERROR = new ErrorCode(107001, "convert obj error");
        public static final ErrorCode SYSTEM_INIT_ERROR = new ErrorCode(107002, "system init error");
        public static final ErrorCode DATA_IS_NULL_OR_BLANK = new ErrorCode(107007, "data is null or blank");
        public static final ErrorCode INPUT_FILE_ERROR = new ErrorCode(107008, "input file error");
        public static final ErrorCode EXCEL_DATA_MIS_PARAM = new ErrorCode(107009, "excel data mis-param");
        public static final ErrorCode EXCEL_NOT_DATA_ERROR = new ErrorCode(107010, "excel not data error");
    }
}
