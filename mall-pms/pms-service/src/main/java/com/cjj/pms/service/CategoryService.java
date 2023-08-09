package com.cjj.pms.service;

import com.cjj.pms.pojo.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjj.pms.pojo.form.CategoryForm;

/**
* @author 25441
* @description 针对表【pms_category(商品分类)】的数据库操作Service
* @createDate 2023-08-09 09:26:33
*/
public interface CategoryService extends IService<Category> {
boolean addCateGory(CategoryForm categoryForm);
}
