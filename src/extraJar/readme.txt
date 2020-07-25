因为pom文件中有四个pom地址是修改过源码的（然后被我打了个包）,所以我把打过的jar包放在了这里
你们用的话直接把这四个jar包放到本地，或者上传到公司的私服

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
<dependency>
    <groupId>org.missfresh.platform</groupId>
    <artifactId>junit-platform-commons</artifactId>
    <version>1.0.0</version>
</dependency>

注意：zip中还有一个junit-bom的文件，也一并放到你的私服或者本地仓库