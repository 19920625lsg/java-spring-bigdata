/***********************************************************
 * @Description : 登录过滤器.下面是写过滤器的流程
 * 	              1、新建一个类，实现ZuulFilter，重写里面的方法
 * 	              2、在类顶部加注解，@Component,让Spring扫描
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/11/26 07:58
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

@Component
public class LoginFilter extends ZuulFilter {
    @Override
    public String filterType() {
        // 登录采用前置过滤器，此外还有POST、ROUTE等类型，见本工程README.md里的图
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        // 过滤器的优先级决定值，越小越先执行
        return 4;
    }

    @Override
    public boolean shouldFilter() {
        // 这个RequestContext也可以在其他方法里使用
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        // 调用者的IP
        System.out.println("调用者IP：" + request.getRemoteAddr());
        // 服务的相对地址
        System.out.println("调用的微服务的相对地址：" + request.getRequestURI());
        // 服务的绝对地址
        System.out.println("调用的微服务的绝对地址：" + request.getRequestURL());
        if ("/order-service/api/v1/order/save".equals(request.getRequestURI())) {
            // 拦截指定的端口，最常用的是登录端口拦截
            return true;
        }
        // 过滤器是否生效，这里返回true才会进入下面的run方法进行进一步的拦截和处理，
        // 返回false就直接往下走了，不会进行run方法里的鉴权了
        return false;
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("拦截到下单接口/order-service/api/v1/order/save啦！");
        // JWT
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader("token");
        // Header里可能没有传token，那就尝试下载参数中获取
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
        // 如果token是空的，Zuul就不再往下传递请求了，返回401(未授权)的错误即可
        if (StringUtils.isBlank(token)) {
            System.out.println("接口请求未授权！");
            // 设置为False可以直接终止请求，把出错结果返回给用户
            requestContext.setSendZuulResponse(false);
            // 401未授权
            requestContext.setResponseStatusCode(HttpStatus.SC_UNAUTHORIZED);
        }
        // token不为空时，requestContext是正常的,Zuul会继续往下放行接口请求
        return null;
    }
}
