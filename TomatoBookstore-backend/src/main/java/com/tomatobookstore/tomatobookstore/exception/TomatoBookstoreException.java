package com.tomatobookstore.tomatobookstore.exception;

public class TomatoBookstoreException extends RuntimeException {
    public TomatoBookstoreException(String message) {
        super(message);
    }

    public static TomatoBookstoreException createFail() {
        throw new TomatoBookstoreException("创建用户失败");
    }

    public static TomatoBookstoreException loginRequired() {
        throw new TomatoBookstoreException("请登录");
    }

    public static TomatoBookstoreException userExisted() {
        throw new TomatoBookstoreException("用户名已存在");
    }

    public static TomatoBookstoreException loginFailure() {
        throw new TomatoBookstoreException("用户不存在/用户密码错误");
    }

    public static TomatoBookstoreException updateFailed() {
        throw new TomatoBookstoreException("更新用户信息失败");
    }

    public static Exception userNotExisted() {
        throw new TomatoBookstoreException("用户不存在");
    }

    public static Exception productNotExisted() {
        throw new TomatoBookstoreException("商品不存在");
    }

    public static Exception noIdAllowed() {
        throw new TomatoBookstoreException("创建商品禁止携带id！");
    }

}
