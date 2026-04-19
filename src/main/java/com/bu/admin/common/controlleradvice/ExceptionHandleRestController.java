package com.bu.admin.common.controlleradvice;

import com.bu.admin.api.Response;
import com.bu.admin.extend.exception.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * rest controller 统一异常处理
 *
 * @author liujiegang
 * @date 2024/5/24 15:21
 */
@Slf4j
@RestControllerAdvice
public class ExceptionHandleRestController {

    /**
     * 参数校验(Valid)异常，将校验失败的所有异常组合成一条错误信息
     *
     * @param e 异常
     * @return 异常结果
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public String handleValidException(MethodArgumentNotValidException e, NativeWebRequest request) {
        log.error("参数绑定校验异常", e);

        BindingResult bindingResult = e.getBindingResult();

        List<String> msgList = new ArrayList<>();

        for (ObjectError error : bindingResult.getAllErrors()) {
            String msg = "";
            if (error instanceof FieldError e1) {
                msg += e1.getField() + ": ";
            }
            msg += (error.getDefaultMessage() == null ? "" : error.getDefaultMessage());
            msgList.add(msg);
        }

        Response response = Response.error(ErrorCodes.ApiEntrance.RESULT_DATA_ERROR);
        response.message(String.join(",", msgList));

        return response.build();
    }
}
