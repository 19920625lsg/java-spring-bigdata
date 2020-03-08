/***********************************************************
 * @Description : 自定义用户实体类
 * @author      : 梁山广(Liang Shan Guang)
 * @date        : 2020/3/8 20:07
 * @email       : liangshanguang2@gmail.com
 ***********************************************************/
package com.huawei.l00379880.vo;

public class UserVo {
    private Integer userId;
    /**
     * 对应username
     */
    private String name;
    /**
     * 对应sex
     */
    private String gender;
    /**
     * 对应address
     */
    private String site;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return "UserVo{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", site='" + site + '\'' +
                '}';
    }
}
