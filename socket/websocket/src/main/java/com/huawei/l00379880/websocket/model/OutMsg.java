/***********************************************************
 * @Description : Socket返回的消息
 * @author      : 梁山广(Laing Shan Guang)
 * @date        : 2018/8/8 22:52
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutMsg {
    private String from;
    private String content;

    public OutMsg(String content) {
        this.content = content;
    }

    private Date time = new Date();
}
