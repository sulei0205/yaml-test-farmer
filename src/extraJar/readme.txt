因为pom文件中有三个pom地址是修改过源码的（然后被我打了个包）,所以我把打过的jar包放在了这里
你们用的话直接把这三个jar包放到本地，或者上传到公司的私服

如下（放在了归档1）

<dependency>
    <groupId>org.missfresh.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>
    <groupId>org.missfresh.jupiter</groupId>
    <artifactId>junit-jupiter-api</artifactId>
    <version>1.0.0</version>
</dependency>
<dependency>
    <groupId>org.missfresh.jupiter</groupId>
    <artifactId>junit-jupiter-params</artifactId>
    <version>1.0.0</version>
</dependency>