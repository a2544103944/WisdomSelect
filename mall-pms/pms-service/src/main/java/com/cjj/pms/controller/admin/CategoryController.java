package com.cjj.pms.controller.admin;
import cn.hutool.core.lang.tree.Tree;
import com.cjj.common.exception.BizException;
import com.cjj.common.result.Result;
import com.cjj.pms.pojo.form.CategoryForm;
import com.cjj.pms.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/**
 * @author 陈建军
 * @version 1.0
 */
@RestController
@RequestMapping("/api_admin/pms/category")
public class CategoryController {
/*    @GetMapping
    public Result list(){
        return Result.success();
    }*/

    @Autowired
    private CategoryService categoryService;
    @PostMapping
    public Result addCategory(@RequestBody @Validated CategoryForm categoryForm){
        categoryService.addCateGory(categoryForm);
        return Result.success();
    }

    /**
     * 商品分类列表
     * @return
     */
    @GetMapping
    public Result<List<Tree<Long>>> tree(){
        return Result.success(categoryService.tree());
    }

    @PutMapping("/{categoryId}")
    public Result updateCategoryById(@PathVariable Long categoryId,@Validated @RequestBody CategoryForm categoryForm){
        categoryForm.setId(categoryId);
        categoryService.updateCategoryById(categoryForm);
        return Result.success();
    }

    @DeleteMapping("/{categoryId}")
    public Result deleteSpuById(@PathVariable Long categoryId){
        categoryService.deleteCategoryById(categoryId);
        return Result.success();
    }
}
