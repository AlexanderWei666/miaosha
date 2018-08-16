package com.alexander.miaosha.dao;

import com.alexander.miaosha.domain.MiaoshaOrder;
import com.alexander.miaosha.domain.OrderInfo;
import org.apache.ibatis.annotations.*;

@Mapper
public interface OrderDao {
	

	public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId")long userId, @Param("goodsId")long goodsId);

//	@Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
//			+ "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
//	@SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
	public long insert(OrderInfo orderInfo);

	public int insertMiaoshaOrder(MiaoshaOrder miaoshaOrder);

	
}