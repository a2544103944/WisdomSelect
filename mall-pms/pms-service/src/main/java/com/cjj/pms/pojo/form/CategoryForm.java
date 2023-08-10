package com.cjj.pms.pojo.form;
import lombok.Data;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 商品分类
 * @TableName pms_category
 */
@Data
public class CategoryForm {

    private Long id;

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 分类名称
     */
    @NotBlank(message = "商品分类名称不能为空")
    private String name;


    /**
     * 图标
     */
    @NotBlank(message = "商品分类图标不能为空")
    private String icon;

    /**
     * 排序
     */
    @NotNull(message = "商品分类排序不能为空")
    private Double weight;

}
