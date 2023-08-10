package com.cjj.pms.service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cjj.pms.pojo.entity.Spu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cjj.pms.pojo.form.SpuForm;
import com.cjj.pms.pojo.query.SpuPageQuery;
import com.cjj.pms.pojo.vo.SpuVO;

/**
* @author 25441
* @description 针对表【pms_spu(商品表)】的数据库操作Service
* @createDate 2023-08-10 15:59:09
*/
public interface SpuService extends IService<Spu> {
    /**
     * 商品详情
     * @param spuId
     * @return
     */
    SpuVO getSpuById(Long spuId);

    /**
     * 商品分页
     * @param query
     * @return
     */
    IPage<SpuVO> spuPage(SpuPageQuery query);

    /**
     * 新增商品
     * @param spuForm
     * @return
     */
    boolean addSpu(SpuForm spuForm);

    /**
     * 新增商品
     * @param spuForm
     * @return
     */
    boolean updateSpuById(SpuForm spuForm);
    /**
     * 删除商品
     * @param spuId
     * @return
     */
    boolean deleteSpuById(Long spuId);
}
