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
@Table(name = "category")
@Getter@Setter@ToString
@Component
public class Category {
    @Id
    @GeneratedValue
    @Column(name = "category_id")
    private Integer id;

    /** 父级分类，如果没有则表示该分类为一级分类 */
    @Column(name = "category_parent_id")
    private Integer categoryParentId;
    @Column(name = "category_name")
    private String categoryName;
    /** 分类状态 0 可用 1 禁用 */
    @Column(name = "category_status")
    private Integer status;
    /** 排序字段 */
    @Column(name = "category_sort_order")
    private Integer sortOrder;


    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false,name = "category_create_time")
    private Date createTime;
    @UpdateTimestamp
    @Column(name = "category_update_time")
    private Date updateTime;
}
