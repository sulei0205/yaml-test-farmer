package com.farmer.chicken.yamltest.provider;
import com.alibaba.fastjson.JSONObject;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.farmer.chicken.yamltest.annotion.YmlFileSource;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.support.AnnotationConsumer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

/**
 * @Author: sulei
 * @Date: 2020-07-13 22:32
 * @Discription:
 */
public class MissfreshFileProvider implements ArgumentsProvider, AnnotationConsumer<YmlFileSource> {

    private YmlFileSource annotation;

    @Override
    public void accept(YmlFileSource ymlFileSource) {
        this.annotation = ymlFileSource;
    }

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) throws Exception {
        AtomicLong index = new AtomicLong(0);
        Optional<Method> testMethod = context.getTestMethod();
        if (!testMethod.isPresent()){
            System.err.println("没有找到当前的测试方法");
            return Arrays.stream(new Object[0])
                    .peek(values -> index.incrementAndGet())
                    .map(Arguments::of);
        }

        // 1.获取多组yml文件解析出来的json对象
        List<Map> maps = getRequestJson();

        Object[][] resultList = new Object[maps.size()][];
        for (int i = 0; i < maps.size(); i++) {
            // 2.获取对应一维数组的值
            List<Object> objects = sortedByParametersOrder(testMethod.get(), maps.get(i));
            // 3.将二维数组填满
            Object[] currentCaseResult = setAndGetParameters(objects);
            resultList[i] = currentCaseResult;
        }

        return Arrays.stream(resultList)
                .peek(values -> index.incrementAndGet())
                .map(Arguments::of);
    }

    /**
     * 设置并返回测试用例的二维数组参数列阵
     * @param objs 排过序的参数集合
     * @return 结果集
     */
    private Object[] setAndGetParameters(List<Object> objs) {
        Object[] resultList = new Object[objs.size()];
        for (int j = 0; j < objs.size(); j++) {
            // 结果集
            Object object = objs.get(j);

            if (Objects.isNull(object)){
                continue;
            }
            resultList[j] = object;

        }
        return resultList;
    }

    /**
     * 根据参数列表排顺序
     * @return 结果集
     * @param method
     * @param map 每个yml文件的结果对象
     */
    private List<Object> sortedByParametersOrder(Method method, Map map) {
        List<Object> sortL = new ArrayList<>();
        Type[] genericParameterTypes = method.getGenericParameterTypes();
        Parameter[] parameters = method.getParameters();
        for (int i = 0; i < parameters.length; i++) {
            String name = parameters[i].getName();
            Object result = map.get(name);

            Type genericParameterType = genericParameterTypes[i];
            //如果是基础数据类型
            if (basicOrPackageType(result,genericParameterType) != null){
                sortL.add(basicOrPackageType(result,genericParameterType));
            }else {
                if (Objects.isNull(result) || "null".equals(result.toString())){
                    sortL.add(null);
                }else {
                    Object object = formJson(JSONObject.toJSONString(result), genericParameterType);
                    sortL.add(object);
                }
            }
        }
        return sortL;
    }

    /**
     * 获取入参的JSON对象
     * @return 结果集
     * @throws YamlException
     */
    private List<Map> getRequestJson() throws YamlException {
        String[] resources = this.annotation.resources();
        List<Map> list = new ArrayList<>();
        for (String resource : resources) {
            Map map = readYML(resource);
            list.add(map);
        }

        return list;
    }

    /**
     * 基础类型和包装类型的返回值
     * @param object 结果对象
     * @param type 对象type
     * @return 结果集
     * tips:byte和char目前不支持
     */
    private Object basicOrPackageType(Object object, Type type) {
        if (Objects.isNull(object)){
            return null;
        }
        String resultStr = object.toString();
        // 八大基础类型
        if (type.getTypeName().equals("int")){
            if ("null".equals(resultStr)){
                return 0;
            }
            return Integer.parseInt(resultStr);
        }else if (type.getTypeName().equals("long")){
            if ("null".equals(resultStr)){
                return 0L;
            }
            return Long.parseLong(resultStr);
        }else if (type.getTypeName().equals("double")){
            if ("null".equals(resultStr)){
                return 0d;
            }
            return Double.parseDouble(resultStr);
        }else if (type.getTypeName().equals("float")){
            if ("null".equals(resultStr)){
                return 0f;
            }
            return Float.parseFloat(resultStr);
        }else if (type.getTypeName().equals("char")){
            if ("null".equals(resultStr)){
                return '\u0000';
            }
            return resultStr.toCharArray();
        }else if (type.getTypeName().equals("boolean")){
            if ("null".equals(resultStr)){
                return false;
            }
            return Boolean.parseBoolean(resultStr);
        } else if (type.getTypeName().equals("byte")){
            if ("null".equals(resultStr)){
                return "0".getBytes();
            }
            return resultStr.getBytes();
        } else if (type.getTypeName().equals("short")){
            if ("null".equals(resultStr)){
                return 0;
            }
            return Short.parseShort(resultStr);
        }
        return null;
    }

    /**
     * 读取yml文件
     * @param ymlPath
     */
    public Map readYML(String ymlPath) throws YamlException {
        YamlReader reader;
        try{
            reader = new YamlReader(new FileReader(ymlPath));
        }catch(FileNotFoundException e){
            System.err.println("您写的yml文件地址有误哦(没有找到该文件)～请确认! 您输入的地址是:" + ymlPath);
            return null;
        }
        //读取
        return (Map)reader.read();
    }

    public <T> T formJson(String json, Type type) {
        try {
            Gson gson = new Gson();
            return gson.fromJson(json, type);
        } catch (JsonSyntaxException e) {
            System.err.println("有参数解析异常!,请查看yml文件是否格式写的有问题\n" + "json = " + json +
            "type name = " + type.getTypeName());
        }
        return null;
    }
}
