# 5-3~5-4_REST-前端API的设计和完成

## 给所有URL添加统一前缀/sell

在application.yml中添加如下内容

```yaml
server:
  context-path: /sell
```

## 给前端返回指定名称的属性JsonProperty

```java
@JsonProperty("name")
private String categoryName;
```

这样就可以在返回给前端时把categoryName自动转换成name

## 给所有接口一个统一的返回体模板

> 比如下面的消息体模板类，可以兼容所有的消息

```java
/***********************************************************
 * @Description : http请求返回的最外层对象
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2019/5/2 18:16
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.sell.vo;

import lombok.Data;

@Data
public class ResultVO<T> {


    public ResultVO(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResultVO() {
    }

    /**
     * 错误码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String msg;

    /**
     * 具体内容
     */
    private T data;
}
```

## 访问`http://虚拟机ip/`时自动跳转到微信授权页面发生跳转，提示`Oops! Something went wrong:(`

这是因为没设置cookie.  

解决方法：首先找到虚拟机的ip,用ifconfig查得为10.102.26.63，然后访问http://10.102.26.63/#/order，这个页面没有进行授权判断，不会发生跳转，然后在console控制台中设置cookie,命令如下

```javascript
document.cookie='openid=abc'
```

此时有cookie了就可以正常访问和调试http://10.102.26.63 了

## 配置了cookie惠普，可以访问页面，但是接口数据访问不到

这是因为nginx没配置，虚拟机里的nginx是原始作者的，我们需要改成自己的本机ip,用ifconfig查得为`10.102.26.234`,nginx的配置文件路径为`/usr/local/nginx/conf/nginx.conf`,把俩面的`location /sell/ `字段改为如下内容:

```json
location /sell/ {
    proxy_pass http://10.102.26.234:8080/sell/;
}
```

然后重启nginx `nginx -s reload` 即可在 http://10.102.26.63 访问到商品列表数据了

## 微信不让用ip访问服务，所以需要再Nginx中把server_name改成`sell.com`

```json
server {
    listen       80;
    server_name  sell.com;
    ......
}
```

然后重启nginx `nginx -s reload`，然后在本机hosts文件中添加映射`虚拟机ip sell.com`，然后即可在 http://sell.com 访问到数据了

+ 注意事项
  + ！！！经过上面的配置仍然不行，要检查下是否本地打开了VPN！！！！一定要关掉VPN才能正常访问sell.com!!!
  + 因为现在访问地是sell.com了，原来的cookie是针对10.102.26.63那个ip的，所以需要访问 http://sell.com/#/orde 重新用`document.cookie='openid=abc'`设置下cookie就能正常访问sell.com了！！