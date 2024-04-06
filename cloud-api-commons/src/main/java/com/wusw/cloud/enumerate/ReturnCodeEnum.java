package com.wusw.cloud.enumerate;

import lombok.Getter;
import lombok.NonNull;

import java.util.Arrays;
import java.util.Objects;

/**
 * 1**  100~199  信息，服务器收到请求，需要请求者继续执行操作
 * 2**  200~299  成功，操作被成功接收并处理
 * 3**  300~399  重定向，需要进一步的操作以完成请求
 * 4**  400~499  客户端错误，请求包含语法错误或无法完成请求
 * 5**  500~599  服务器错误，服务器在处理请求的过程中发生了错误
 */
@Getter
public enum ReturnCodeEnum {

    RC999(999, "操作失败"),
    RC200(200, "操作成功"),
    RC201(201, "服务开启降级保护, 请稍后再试"),
    RC202(202, "热点参数限流, 请稍后再试"),
    RC203(203, "系统规则不满足要求, 请稍后再试"),
    RC204(204, "授权规则不通过, 请稍后再试"),
    RC375(375, "数学运算异常, 请稍后再试"),
    RC401(401, "匿名用户无法访问该资源"),
    RC403(403, "无访问权限, 请联系管理员授权"),
    RC404(404, "无法找到页面"),
    RC500(500, "系统异常, 请稍后再试"),
    CLIENT_AUTHENTICATION_FAILED(1001, "客户端认证失败"),
    USER_OR_PASSWORD_ERROR(1002, "用户名或密码错误"),
    UNSUPPORTED_GRANT_TYPE(1003, "不支持的认证模式"),
    BUSINESS_ERROR(1004, "业务逻辑异常"),
    INVALID_TOKEN(2001, "访问令牌不合法"),
    ACCESS_DENIED(2001, "无权限访问该资源"),
    NULL(null, null);


    private final Integer code;

    private final String message;

    ReturnCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static ReturnCodeEnum get(@NonNull Integer code) {
//        if (code == null) {
//            throw new RuntimeException("code不能为空");
//        }
        return Arrays.stream(ReturnCodeEnum.values())
                .filter(Objects::nonNull)
                .filter(i -> code.equals(i.getCode()))
                .findFirst()
                .orElse(NULL);
    }

    public static void main(String[] args) {
        System.out.println(ReturnCodeEnum.get(200).getCode());
        System.out.println(ReturnCodeEnum.get(200).getMessage());
    }
}
