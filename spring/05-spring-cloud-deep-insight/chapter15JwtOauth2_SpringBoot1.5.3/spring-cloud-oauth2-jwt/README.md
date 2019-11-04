# Spring Cloud OAuth2+JWT

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
