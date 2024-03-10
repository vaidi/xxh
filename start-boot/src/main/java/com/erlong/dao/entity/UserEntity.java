package com.erlong.dao.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * [STRATO MyBatis Generator]
 * Table: users
 @mbggenerated do_not_delete_during_merge 2018-05-23 15:56:12
 */
@TableName("`student`")
@Data
public class UserEntity implements Serializable {
    /**
     *   用户ID
     * Column: users.user_id
     @mbggenerated 2018-05-23 15:56:12
     */
    private int id;

    /**
     *   用户昵称
     * Column: users.nick_name
     @mbggenerated 2018-05-23 15:56:12
     */
    private String name;


    /**
     * 用户的真实姓名
     */
    private String mobile;

    private Date createTime;

    private Date updateTime;

}