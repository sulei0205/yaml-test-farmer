package com.farmer.chicken.yamltest.bean;

import lombok.Data;

@Data
public class ParamBean{
    /**
     * clazz
     */
    private Class<?> clazz;
    /**
     * 类名
     */
    private String clazzName;

    /**
     * 方法名
     */
    private String methodName;

    /**
     * 返回值类型对象
     */
    private Object param;

    /**
     * 属性类型
     */
    private String sourceType;

    //测试
    private Integer age;

    private int stupid;


}