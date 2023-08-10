package com.cjj.pms.service;
import cn.hutool.core.lang.tree.Tree;
import com.cjj.pms.pojo.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjj.pms.pojo.form.CategoryForm;
import java.util.List;

/**
 * @author 25441
 * @description 针对表【pms_category(商品分类)】的数据库操作Service
 * @createDate 2023-08-09 09:26:33
 */
public interface CategoryService extends IService<Category> {
    /**
     * 获取商品分类树
     *
     * @return
     */
    List<Tree<Long>> tree();

    /**
     * 新增商品分类
     * @param categoryForm
     * @return
     */
    boolean addCateGory(CategoryForm categoryForm);

    /**
     * 更新商品分类
     * @param categoryForm
     * @return
     */

    boolean updateCategoryById(CategoryForm categoryForm);

    /**
     * 删除商品分类
     * @param categoryId
     * @return
     */
    boolean deleteCategoryById(Long categoryId);
}
