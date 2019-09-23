# JavaDoc Deploy

## 生成 JavaDoc

[]()<a name="224e2ccd"></a>
### 配置

通过maven插件来实现生成 JavaDoc：

```xml
<properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    </properties>
    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-javadoc-plugin</artifactId>
                    <version>2.10.3</version>
                    <configuration>
					  <!-- Java 生成目录，相对工程的根目录-->
					  <reportOutputDirectory>javadocs</reportOutputDirectory>
                        <additionalparam>-Xdoclint:none</additionalparam>
                        <aggregate>true</aggregate>
                    </configuration>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
```

[]()<a name="4dfe7036"></a>
### 生成

在工程目录下执行（需要能编译通过）：

```bash
mvn javadoc:javadoc
```

[]()<a name="8b289fbb"></a>

### 本地访问
直接打开 javadocs/apidocs/index.html。
或者可以在本地使用 Spring-Boot 配置一个访问静态资源的服务:
将生成的静态文件部署至 src/main/resources/static/apidocs 目录，然后启动Spring。
这种方式也可以 将Java 代码编译打包，部署至独立的服务器。