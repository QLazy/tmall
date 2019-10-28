package com.qlazy.tmall.mapper;

import com.qlazy.tmall.entity.review;
import com.qlazy.tmall.entity.reviewExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface reviewMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    long countByExample(reviewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    int deleteByExample(reviewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    int insert(review record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    int insertSelective(review record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    List<review> selectByExampleWithRowbounds(reviewExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    List<review> selectByExample(reviewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    review selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    int updateByExampleSelective(@Param("record") review record, @Param("example") reviewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    int updateByExample(@Param("record") review record, @Param("example") reviewExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    int updateByPrimaryKeySelective(review record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table review
     *
     * @mbg.generated Mon Oct 28 20:49:54 CST 2019
     */
    int updateByPrimaryKey(review record);
}