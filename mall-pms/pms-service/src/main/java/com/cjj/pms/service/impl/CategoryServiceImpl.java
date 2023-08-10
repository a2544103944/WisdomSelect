package com.cjj.pms.service.impl;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjj.common.exception.BizException;
import com.cjj.pms.pojo.entity.Category;
import com.cjj.pms.pojo.form.CategoryForm;
import com.cjj.pms.service.CategoryService;
import com.cjj.pms.mapper.CategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 25441
 * @description 针对表【pms_category(商品分类)】的数据库操作Service实现
 * @createDate 2023-08-09 09:26:33
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
        implements CategoryService {
    @Override
    public List<Tree<Long>> tree() {
        List<Category> list = this.list();
        List<TreeNode<Long>> nodeList = new ArrayList<>();
        for (Category item : list) {
            TreeNode<Long> treeNode = new TreeNode<>(item.getId(), item.getParentId(), item.getName(), item.getWeight());
            Map<String, Object> extra = new HashMap<>();
            extra.put("icon", item.getIcon());
            extra.put("level", item.getLevel());
            treeNode.setExtra(extra);
            nodeList.add(treeNode);
        }
        return TreeUtil.build(nodeList, 0L);
    }

    @Override
    public boolean addCateGory(CategoryForm categoryForm) {
        Category category = setLevel(categoryForm);
        return this.save(category);
    }

    @Override
    public boolean updateCategoryById(CategoryForm categoryForm) {
        Category category = setLevel(categoryForm);
        return this.updateById(category);
    }

    @Override
    public boolean deleteCategoryById(Long categoryId) {
        if (this.getById(categoryId)==null){
            throw new BizException(HttpStatus.HTTP_NOT_FOUND, "商品分类不存在或已删除");
        }
        //判断该商品是否有子分类
        boolean exists=this.baseMapper.exists(new LambdaQueryWrapper<Category>().eq(Category::getParentId,categoryId));
        if (exists){
            throw new BizException(HttpStatus.HTTP_BAD_REQUEST,"该分类存在子分类，不能删除");
        }
        //TODO 判断该分类下是否有商品
        return this.removeById(categoryId);
    }

    private Category setLevel(CategoryForm categoryForm) {
        Category category;
        if (categoryForm.getId() == null) {
            //新增
            category = new Category();
        } else {
            //更新
            category = this.getById(categoryForm.getId());
            if (category == null) {
                throw new BizException(HttpStatus.HTTP_NOT_FOUND, "商品分类不存在或已删除");
            }
        }
            BeanUtils.copyProperties(categoryForm, category);
            Long parentId = category.getParentId();
            if (parentId == null) {
                category.setParentId(0L);
                category.setLevel(1);
            } else {
                Category parent = this.getById(category.getParentId());
                if (parent == null) {
                    throw new BizException(HttpStatus.HTTP_NOT_FOUND, "父类不存在");
                }
                category.setLevel(parent.getLevel() + 1);
            }
            return category;
        }
    }





