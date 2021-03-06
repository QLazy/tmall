package com.qlazy.tmall.mapper;

import com.qlazy.tmall.entity.category;
import com.qlazy.tmall.entity.categoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface categoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    long countByExample(categoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int deleteByExample(categoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int insert(category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int insertSelective(category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    List<category> selectByExampleWithRowbounds(categoryExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    List<category> selectByExample(categoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    category selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int updateByExampleSelective(@Param("record") category record, @Param("example") categoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int updateByExample(@Param("record") category record, @Param("example") categoryExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int updateByPrimaryKeySelective(category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table mycategory
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    int updateByPrimaryKey(category record);
}