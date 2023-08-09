package com.cjj.pms.service.impl;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjj.common.exception.BizException;
import com.cjj.pms.pojo.entity.Category;
import com.cjj.pms.pojo.form.CategoryForm;
import com.cjj.pms.service.CategoryService;
import com.cjj.pms.mapper.CategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
/**
* @author 25441
* @description 针对表【pms_category(商品分类)】的数据库操作Service实现
* @createDate 2023-08-09 09:26:33
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{
    @Override
    public boolean addCateGory(CategoryForm categoryForm) {
        Category category=new Category();
        BeanUtils.copyProperties(categoryForm,category);
        //判断父分类是否存在
        Long parenId=categoryForm.getParentId();
        if (parenId==null){
            category.setLevel(1);
        }else {
            Category parent=this.getById(parenId);
            if (parent==null){
                throw new BizException(HttpStatus.HTTP_NOT_FOUND,"父类不存在");
            }
            category.setLevel(parent.getLevel()+1);
        }
        return this.save(category);
    }
}




