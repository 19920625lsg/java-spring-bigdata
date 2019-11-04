package com.huawei.l00379880.userservice.dto;

import com.huawei.l00379880.userservice.entity.JWT;
import com.huawei.l00379880.userservice.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {

    private JWT jwt;

    private User user;

}
