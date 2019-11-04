# spring-cloud-deep-insight
《深入理解Spring Cloud与微服务构建》学习笔记
[作者的源码仓库](https://github.com/forezp/springcloud-book)

![图书图片](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1542812928797&di=8d2b860df722200bebd7b3a154c477c3&imgtype=0&src=http%3A%2F%2Fimages-cn.ssl-images-amazon.com%2Fimages%2FI%2F51lVWhbRqwL.jpg)


## 生成jks文件的命令

```bash
keytool -genkeypair -alias fzp-jwt -validity 3650 -keyalg RSA -dname "CN=jwt,OU=jtw,O=jtw,L=zurich,S=zurich,C=CH" -keypass fzp123 -keystore fzp-jwt.jks -storepass fzp123
```

## 获取jks文件的公钥

```bash
keytool -list -rfc --keystore fzp-jwt.jks | openssl x509 -inform pem -pubkey
```

## 防止jks和cert文件被maven编译
```xml
<!--ignore jks and cert file-->
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-resources-plugin</artifactId>
    <configuration>
        <nonFilteredFileExtensions>
            <nonFilteredFileExtension>cert</nonFilteredFileExtension>
            <nonFilteredFileExtension>jks</nonFilteredFileExtension>
        </nonFilteredFileExtensions>
    </configuration>
</plugin>
```
