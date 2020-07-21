# yaml-test-farmer
开发自用的测试框架，将数据与逻辑分离

前言
开发过程中工期紧，类似于交易生单的场景，每次新接入一个需求就要回归，尤其是对于又大嵌套又深的参数场景，
经常会忘了设置或者设置错，并且同时开发人员过度依赖测试人员的测试,进入到提测环节的时候，
其实自己并没有把原来的场景都验证一遍，因此一些痛点，氤氲而生～

用法（四大步骤）

第一步：
引入pom文件
<dependency>
    <groupId>com.hackathon</groupId>
    <artifactId>yaml-test</artifactId>
    <version>1.0.4-SNAPSHOT</version>
<scope>test</scope>
</dependency>

tips：注意需要对应的 spring-boot-starter-test版本在2.2.2RELEASE版本
如果你被父POM管理着，那么你需要独自定义下面的pom(在yaml-test的pom文件上方)
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <version>2.2.2.RELEASE</version>
</dependency>

第二步：
定义一个测试类（类似如下图）

tips：
注意这两个注解是必填的
@SpringBootTest(classes = Application.class)
@ExtendWith(SpringExtension.class)

