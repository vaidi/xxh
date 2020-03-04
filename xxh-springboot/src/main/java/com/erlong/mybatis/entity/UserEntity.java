package com.erlong.mybatis.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * [STRATO MyBatis Generator]
 * Table: users
 @mbggenerated do_not_delete_during_merge 2018-05-23 15:56:12
 */
@Data
public class UserEntity implements Serializable {
    /**
     *   用户ID
     * Column: users.user_id
     @mbggenerated 2018-05-23 15:56:12
     */
    private String userId;

    /**
     *   用户昵称
     * Column: users.nick_name
     @mbggenerated 2018-05-23 15:56:12
     */
    private String nickName;


    /**
     * 用户的真实姓名
     */
    private String realName;

    /**
     *   手机号
     * Column: users.mobile
     @mbggenerated 2018-05-23 15:56:12
     */
    private String mobile;

    /**
     *   邮箱
     * Column: users.email
     @mbggenerated 2018-05-23 15:56:12
     */
    private String email;

    /**
     *   性别(1:男,2:女,3:未知)
     * Column: users.sex
     @mbggenerated 2018-05-23 15:56:12
     */
    private Integer sex;

}