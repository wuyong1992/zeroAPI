package com.wuyong.demo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by 坚果
 * on 2017/9/5
 */
@Entity
@Table(name = "user_info")
@Component
@Getter@Setter@ToString
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id",unique = true)
    private Integer id;

    @Column(name = "user_mobile",unique = true)
    private String mobile;
    @Column(name = "user_username",unique = true)
    private String username;
    @Column(name = "user_password")
    private String password;
    @Column(name = "user_email",unique = true)
    private String email;
    /** 角色 0 正常 1 禁用*/
    @Column(name = "user_status")
    private Integer status = 0;
    /** 角色 0 普通用户 1 管理员*/
    @Column(name = "user_role")
    private Integer role = 0;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false,name = "user_createTime")
    private Date createTime;

    @UpdateTimestamp
    @Column(name = "user_updateTime")
    private Date updateTime;

}
