package com.qlazy.tmall.mapper;

import com.qlazy.tmall.entity.order;
import com.qlazy.tmall.entity.orderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface orderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    long countByExample(orderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    int deleteByExample(orderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    int insert(order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    int insertSelective(order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    List<order> selectByExampleWithRowbounds(orderExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    List<order> selectByExample(orderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    order selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    int updateByExampleSelective(@Param("record") order record, @Param("example") orderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    int updateByExample(@Param("record") order record, @Param("example") orderExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    int updateByPrimaryKeySelective(order record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table order_
     *
     * @mbg.generated Wed Nov 13 09:08:46 CST 2019
     */
    int updateByPrimaryKey(order record);
}