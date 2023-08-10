package com.cjj.pms.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;

/**
 * 商品表
 * @TableName pms_spu
 */
@TableName(value ="pms_spu",autoResultMap = true)
@Data
public class Spu implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品分类ID
     */
    private Long categoryId;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 销量
     */
    private Integer sales;

    /**
     * 商品主图
     */
    private String img;

    /**
     * 商品图集
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> imgList;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 商品状态  0:下架 1:上架
     */
    private Integer status;

    /**
     * 商品规格
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<?> specList;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}