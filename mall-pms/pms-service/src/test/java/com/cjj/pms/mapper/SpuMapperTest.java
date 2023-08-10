package com.cjj.pms.mapper;

import com.cjj.pms.pojo.entity.Spu;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author 陈建军
 * @version 1.0
 */
@SpringBootTest
class SpuMapperTest {
    @Autowired
    private SpuMapper spuMapper;
    @Test
    void test(){
        Spu spu = spuMapper.selectById(1);
//        System.out.println(spu);
        spu.setId(null);
        spuMapper.insert(spu);
    }

}