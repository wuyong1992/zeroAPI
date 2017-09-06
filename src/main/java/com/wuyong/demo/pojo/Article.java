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
@Table(name = "article_info")
@Getter@Setter@ToString
@Component
public class Article {

    @Id
    @GeneratedValue
    @Column(name = "article_id")
    private Integer id;

    /** 状态 0 可用 1 禁用 2 已删除 */
    @Column(name = "article_status")
    private Integer status;
    /** 排序字段 */
    @Column(name = "article_sort_order")
    private Integer sortOrder;
    /** 标题 */
    @Column(name = "article_title")
    private String title;
    /** 主图的缩略图 */
    @Column(name = "article_thumbnail")
    private String Thumbnail;
    /** 主图 */
    @Column(name = "article_main_image")
    private String mainImage;
    /** 简介 */
    @Column(name = "article_intro")
    private String intro;
    /** 详情 */
    @Column(name = "article_detail")
    private String detail;
    /** 所属作者id */
    @Column(name = "user_id")
    private Integer userId;
    /** 所属分类id */
    @Column(name = "category_id")
    private Integer categoryId;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(updatable = false,name = "article_create_time")
    private Date createTime;
    @UpdateTimestamp
    @Column(name = "article_update_time")
    private Date updateTime;

}
