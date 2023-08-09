package com.cjj.pms.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cjj.pms.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 陈建军
 * @version 1.0
 */
@SpringBootTest
class CategoryMapperTest {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryService categoryService;

    @Test
    void test() {
//        categoryMapper.selectList(null);
    categoryService.page(Page.of(1,2));
    }
}