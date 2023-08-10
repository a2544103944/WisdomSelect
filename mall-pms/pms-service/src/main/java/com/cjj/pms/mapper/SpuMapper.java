package com.cjj.pms.mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cjj.pms.pojo.entity.Spu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cjj.pms.pojo.query.SpuPageQuery;
import com.cjj.pms.pojo.vo.SpuVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
* @author 25441
* @description 针对表【pms_spu(商品表)】的数据库操作Mapper
* @createDate 2023-08-10 15:59:09
* @Entity com.cjj.pms.pojo.entity.Spu
*/

public interface SpuMapper extends BaseMapper<Spu> {
SpuVO selectSpuById(Long spuId);
    /**
     * 商品分页
     * @param page
     * @param query
     * @return
     */
    IPage<SpuVO> selectSpuPage(IPage<SpuVO> page, @Param("param") SpuPageQuery query);
}




