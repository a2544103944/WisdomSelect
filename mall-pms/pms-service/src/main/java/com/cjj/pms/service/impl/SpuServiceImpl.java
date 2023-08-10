package com.cjj.pms.service.impl;

import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cjj.common.exception.BizException;
import com.cjj.pms.mapper.CategoryMapper;
import com.cjj.pms.mapper.SkuMapper;
import com.cjj.pms.pojo.entity.Sku;
import com.cjj.pms.pojo.entity.Spu;
import com.cjj.pms.pojo.form.SpuForm;
import com.cjj.pms.pojo.query.SpuPageQuery;
import com.cjj.pms.pojo.vo.SpuVO;
import com.cjj.pms.service.SkuService;
import com.cjj.pms.service.SpuService;
import com.cjj.pms.mapper.SpuMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 25441
 * @description 针对表【pms_spu(商品表)】的数据库操作Service实现
 * @createDate 2023-08-10 15:59:09
 */
@Service
public class SpuServiceImpl extends ServiceImpl<SpuMapper, Spu>
        implements SpuService {
    @Autowired
    private CategoryMapper categoryMapperl;
    @Autowired
    private SkuService skuService;

    @Override
    public SpuVO getSpuById(Long spuId) {
        return this.getBaseMapper().selectSpuById(spuId);
    }

    @Override
    public IPage<SpuVO> spuPage(SpuPageQuery query) {
        return this.getBaseMapper()
                .selectSpuPage(Page.of(query.getCurrent(), query.getSize()), query);
    }

    @Override
    @Transactional
    public boolean addSpu(SpuForm spuForm) {
        Spu spu = new Spu();
        BeanUtils.copyProperties(spuForm, spu);
        spu.setStatus(1);
        if (categoryMapperl.selectById(spu.getCategoryId()) == null) {
            throw new BizException(HttpStatus.HTTP_NOT_FOUND, "商品分类不存在或已删除");
        }
        this.save(spu);
        List<Sku> skuList = spuForm.getSkuList().stream().map(sku -> {
            sku.setSpuId(spu.getId());
            spu.setStatus(1);
            return sku;
        }).collect(Collectors.toList());
        return skuService.saveBatch(skuList);
    }

    @Override
    @Transactional
    public boolean updateSpuById(SpuForm spuForm) {
        //1.保存商品信息
        Spu spu = this.getById(spuForm.getId());
        if (spu == null) {
            throw new BizException(HttpStatus.HTTP_NOT_FOUND, "商品信息不存在或已删除");
        }
        BeanUtils.copyProperties(spuForm, spu);
        this.updateById(spu);
        //2.保存商品信息
        return skuService.updateBatchById(spuForm.getSkuList());
    }

    @Override
    @Transactional
    public boolean deleteSpuById(Long spuId) {
        if (this.getById(spuId) == null) {
            throw new BizException(HttpStatus.HTTP_NOT_FOUND, "商品信息不存在或已删除");
        }
        //1.删除对应库存信息
        skuService.remove(new LambdaQueryWrapper<Sku>().eq(Sku::getSpuId,spuId));
        //2.删除商品
        return this.removeById(spuId);
    }

}




