package com.huawei.l00379880.blogservice.client.hystrix;

import com.huawei.l00379880.blogservice.client.UserServiceClient;
import com.huawei.l00379880.blogservice.entity.User;
import com.huawei.l00379880.common.dto.RespDTO;
import org.springframework.stereotype.Component;


/**
 * Created by fangzhipeng on 2017/5/31.
 */
@Component
public class UserServiceHystrix implements UserServiceClient {

    @Override
    public RespDTO<User> getUser(String token, String username) {
        System.out.println(token);
        System.out.println(username);
        return null;
    }
}
