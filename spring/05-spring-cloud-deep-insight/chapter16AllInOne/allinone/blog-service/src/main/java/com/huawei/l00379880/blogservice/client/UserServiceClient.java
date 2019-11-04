package com.huawei.l00379880.blogservice.client;

import com.huawei.l00379880.blogservice.client.hystrix.UserServiceHystrix;
import com.huawei.l00379880.blogservice.entity.User;
import com.huawei.l00379880.common.dto.RespDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;



/**
 * Created by fangzhipeng on 2017/5/27.
 */

@FeignClient(value = "user-service",fallback = UserServiceHystrix.class )
public interface UserServiceClient {

    @PostMapping(value = "/user/{username}")
    RespDTO<User> getUser(@RequestHeader(value = "Authorization") String token, @PathVariable("username") String username);
}



