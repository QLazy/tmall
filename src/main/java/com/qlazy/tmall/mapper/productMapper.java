package com.qlazy.tmall.mapper;

import com.qlazy.tmall.entity.product;
import com.qlazy.tmall.entity.productExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface productMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    long countByExample(productExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int deleteByExample(productExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int insert(product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int insertSelective(product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    List<product> selectByExampleWithRowbounds(productExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    List<product> selectByExample(productExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    product selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int updateByExampleSelective(@Param("record") product record, @Param("example") productExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int updateByExample(@Param("record") product record, @Param("example") productExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int updateByPrimaryKeySelective(product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int updateByPrimaryKey(product record);
}