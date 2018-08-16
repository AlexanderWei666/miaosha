package com.alexander.miaosha.dao;

import com.alexander.miaosha.domain.MiaoshaGoods;
import com.alexander.miaosha.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GoodsDao {
	

	public List<GoodsVo> listGoodsVo();

	public GoodsVo getGoodsVoByGoodsId(@Param("goodsId")long goodsId);

	public int reduceStock(MiaoshaGoods g);
	
}