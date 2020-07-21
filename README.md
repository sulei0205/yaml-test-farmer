# yaml-test-farmer
开发自用的测试框架，将数据与逻辑分离

前言
开发过程中工期紧，类似于交易生单的场景，每次新接入一个需求就要回归，尤其是对于又大嵌套又深的参数场景，
经常会忘了设置或者设置错，并且同时开发人员过度依赖测试人员的测试,进入到提测环节的时候，
其实自己并没有把原来的场景都验证一遍，因此一些痛点，氤氲而生～

用法

tips:
1.因为其中pom文件引用的三个jar包是基于源码改动点内容,所以你第一步骤，先将src下的extraJar下的jar包解压放在你本地的.m2的repository下,记得文件路径得和pom文件匹配上
这三个文件pom地址分别是

    <artifactId>junit-jupiter-engine</artifactId>

    <artifactId>junit-jupiter-api</artifactId>

    <artifactId>junit-jupiter-params</artifactId>
 

2.然后你确定好你本地的pom文件内容全部下载完毕之后打开测试类YamlTestApplicationTest
然后你会发现里面有几个测试方法，每个方法上都有一个注解@YmlFileSource(resources={“这里的地址可以换成你本地yml文件的绝对路径”}）

上述两步骤执行完毕了，可以自己动手跑一下里面的几个测试方法感受下
这个项目可以打成jar包让你的团队内部使用，或者是自己用都可以，是一款开发自用的可以Mock，数据和逻辑分离的测试框架

对外提供的使用步骤（打成jar包给其他项目使用）
用法
https://shimo.im/docs/vyjpwHvRvvYQd8VW/ 
