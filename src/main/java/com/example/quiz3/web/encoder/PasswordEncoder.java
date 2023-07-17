package com.example.quiz3.web.encoder;

public interface PasswordEncoder {
    //加密(外面调用一般在注册的时候加密前端传过来的密码保存进数据库)
    String encode(CharSequence rawPassword);

    //加密前后对比(一般用来比对前端提交过来的密码和数据库存储密码, 也就是明文和密文的对比)
    boolean matches(CharSequence rawPassword, String encodedPassword);

    //是否需要再次进行编码, 默认不需要
    default boolean upgradeEncoding(String encodedPassword) {
        return false;
    }
}
