package com.qlazy.tmall.entity;

import org.springframework.stereotype.Component;

@Component
public class property {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column property.id
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column property.cid
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    private Integer cid;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column property.name
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column property.id
     *
     * @return the value of property.id
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column property.id
     *
     * @param id the value for property.id
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column property.cid
     *
     * @return the value of property.cid
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    public Integer getCid() {
        return cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column property.cid
     *
     * @param cid the value for property.cid
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    public void setCid(Integer cid) {
        this.cid = cid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column property.name
     *
     * @return the value of property.name
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column property.name
     *
     * @param name the value for property.name
     *
     * @mbg.generated Wed Oct 30 09:09:36 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}