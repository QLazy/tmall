package com.qlazy.tmall.entity;

import org.springframework.stereotype.Component;

@Component
public class category {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mycategory.id
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column mycategory.name
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mycategory.id
     *
     * @return the value of mycategory.id
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mycategory.id
     *
     * @param id the value for mycategory.id
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column mycategory.name
     *
     * @return the value of mycategory.name
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column mycategory.name
     *
     * @param name the value for mycategory.name
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}