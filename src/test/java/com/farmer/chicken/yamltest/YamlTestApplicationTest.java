package com.farmer.chicken.yamltest;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.farmer.chicken.yamltest.annotion.YmlFileSource;
import com.farmer.chicken.yamltest.bean.ParamBean;
import com.farmer.chicken.yamltest.service.TestService;
import com.farmer.chicken.yamltest.service.YmlHandler;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * tips:下面方法注解中的resources换成你本地yml文件的绝对路径！！！！！！！！！！！！！
 */
@SpringBootTest(classes = YamlTestApplication.class)
@ExtendWith(SpringExtension.class)
public class YamlTestApplicationTest{


    @Autowired
    YmlHandler ymlHandler;

    @MockBean
    TestService testService;



    //1.Date类型(done)
    //2.json类型（done）
    //3.null值(done)
    //3.List类型(done)
    //4.引用类型（done）
    //5.HashMap(done)
    //6.JSONObject(done)
    //7.JSONArray (done)


    @YmlFileSource(resources = "/Users/sulei/IdeaProjects/yaml-test-farmer/src/main/resources/YamlTest.yml")
    public void test(
                     /**基础类型*/
                     String orderId,
                     Integer id,
                     /** List中包含引用类型*/
                     List<ParamBean> paramBean,
                     /** Date类型*/
                     Date date,
                     /** Map类型*/
                     Map<String,String> maps,
                     /** JSONObject对象*/
                     JSONObject jsonObject,
                     /** List中包含Date类型*/
                     List<Date> dates,
                     /** List包含基础类型*/
                     List<String> strs,
                     List<Integer> ints,
                     /** JSONArray对象*/
                     JSONArray jsonArray,
                     /** 引用类型*/
                     ParamBean p,
                     /** 引用类型是null*/
                     ParamBean pa,
                     /** List是一个空集合*/
                     List<ParamBean> pl,
                     /** List是一个null*/
                     List<ParamBean> nl) {
        System.out.println("第一个参数:"
                +orderId + "\n" +
                "第二个参数:" +  id + "\n" +
                "第三个参数:" + paramBean + "\n" +
                "第四个参数:" + date+ "\n" +
                "第五个参数:" + maps+ "\n" +
                "第六个参数:" + jsonObject+ "\n" +
                "第七个参数:" + dates + "\n" +
                "第八个参数:" + strs + "\n" +
                "第九个参数:" + ints + "\n" +
                "第十个参数:" + jsonArray + "\n" +
                "第十一个参数:" + p + "\n" +
                "第十二个参数:" + pa + "\n" +
                "第十三个参数:" + pl + "\n" +
                "第十四个参数:" + nl);
        Mockito.when(testService.get()).thenReturn(p);
        ParamBean execute = ymlHandler.execute(orderId, id);
        System.out.println("执行的结果集" + execute);


        for (ParamBean bean : paramBean) {
            System.err.println(bean.getStupid());
        }
    }

    /**
     * 基础类型 + 包装类型 + null值测试
     * @param a
     * @param b
     * @param c
     * @param d
     * @param f
     * @param h
     */
    @YmlFileSource(resources = {"/Users/sulei/IdeaProjects/yaml-test-farmer/src/main/resources/basicAndPackageType1.yml"})
    public void basicTypeTest(int a,Long b,Double c,Float d,Boolean f,Short h,String i,String j) {
        System.out.println(a);
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        System.out.println(f);
        System.out.println(h);
        System.out.println(i);
        System.out.println("".equals(i));
        System.out.println(j);
    }

    /**
     * Date + null值测试
     * Date                     fix
     * Date is null             fix
     * List<Date> contains null fix
     * Map<Date,Date> contains null fix
     * Map<String,Date> contains nunll fix
     * Map<List<Date>,String>
     */
    @YmlFileSource(resources = {"/Users/sulei/IdeaProjects/yaml-test-farmer/src/main/resources/DateType1.yml",
            "/Users/sulei/IdeaProjects/yaml-test-farmer/src/main/resources/DateType1.yml"})
    public void dateTypeTest(List<Date> date,Map<Date,Date> map,Map<String,Date> s,Map<String,List<Date>> ss){
        System.out.println("第一组参数=" + date);
        for (Date date1 : date) {
            if (date1 == null){
                System.out.println("当前date1是= " + date1);
            }else {
                System.out.println("当前date1是= " + date1);
            }
        }

        System.out.println("第二组参数=" + map);
        Iterator<Map.Entry<Date, Date>> mapDate = map.entrySet().iterator();
        while (mapDate.hasNext()){
            Map.Entry<Date, Date> next = mapDate.next();
            Date key = next.getKey();
            Date value = next.getValue();
            System.out.println("key =" + key + "value = " + value);
        }

        System.out.println("第三组参数=" + s);
        Iterator<Map.Entry<String, Date>> mapStringDate = s.entrySet().iterator();
        while (mapStringDate.hasNext()){
            Map.Entry<String, Date> next = mapStringDate.next();
            String key = next.getKey();
            Date value = next.getValue();
            System.out.println("key =" + key + "value = " + value);
        }

        System.out.println("第四组参数=" + s);
        Iterator<Map.Entry<String,List<Date>>> mapListStringDate = ss.entrySet().iterator();
        while (mapListStringDate.hasNext()){
            Map.Entry<String,List<Date>> next = mapListStringDate.next();
            String key = next.getKey();
            List<Date> value = next.getValue();
            System.out.println("key =" + key + "value = " + value);

            for (Date date1 : value) {
                System.out.println(date1);
            }
        }
    }

}

