package com.alexander.miaosha.results;

public enum  CodeMsg {

    SUCCESS(0, "success"),
    SERVER_ERROR(500100, "server error"),
    BIND_ERROR(500101, "参数校验异常：%s"),
    SESSION_ERROR(500210, "Session不存在或者已经失效"),
    PASSWORD_EMPTY(500211, "登录密码不能为空"),
    MOBILE_EMPTY(500212, "手机号不能为空"),
    MOBILE_ERROR(500213, "手机号格式错误"),
    MOBILE_NOT_EXIST(500214, "手机号不存在"),
    PASSWORD_ERROR(500215, "密码错误"),
    MIAO_SHA_OVER(500500, "商品秒杀完毕"),
    REPEATE_MIAOSHA(500, "不能重复秒杀");
    private int code;
    private String msg;


    CodeMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMsg fillArgs(Object... args) {
        String message = String.format(this.msg, args);
        this.msg = message;
        return this;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }


    @Override
    public String toString() {
        return "CodeMsg [code=" + code + ", msg=" + msg + "]";
    }
}
