package com.sdzk.buss.web.auth.entity;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.web.system.pojo.base.TSDepart;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 授权信息
 * @author onlineGenerator
 * @date 2017-09-26 10:21:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_b_auth", schema = "")
@SuppressWarnings("serial")
public class TBAuthEntity implements java.io.Serializable {

    /**唯一标识*/
    private String id;
    /**授权编码*/
    private String authNum;
    /**设备mac*/
    private String deviceMac;
    /**开始时间*/
    @Excel(name="开始时间")
    private Date beginDate;
    /**结束时间*/
    @Excel(name="结束时间")
    private Date endDate;

    /**创建人,录入人*/
    @Excel(name="录入人")
    private String createBy;
    /**创建人名称*/
    private String createName;
    /**创建日期*/
    private Date createDate;
    /**更新人登陆名*/
    private String updateBy;
    /**更新人名称*/
    private String updateName;
    /**更新时间*/
    private Date updateDate;

    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  唯一标识
     */
    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="id",nullable=false,length=32)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  authNum
     */
    @Column(name ="auth_num",nullable=true,length=32)
    public String getAuthNum() {
        return authNum;
    }

    public void setAuthNum(String authNum) {
        this.authNum = authNum;
    }

    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  deviceMac
     */
    @Column(name ="device_mac",nullable=true,length=64)
    public String getDeviceMac() {
        return deviceMac;
    }

    public void setDeviceMac(String deviceMac) {
        this.deviceMac = deviceMac;
    }

    /**
     *方法: 取得java.util.Date
     *@return: java.util.Date  授权起始时间
     */
    @Column(name ="begin_date",nullable=true)
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    /**
     *方法: 取得java.util.Date
     *@return: java.util.Date  结束时间
     */
    @Column(name ="end_date",nullable=true)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  创建人,录入人
     */
    @Column(name ="create_by",nullable=true,length=50)
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String 创建人名称
     */
    @Column(name ="create_name",nullable=true,length=100)
    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String 创建日期
     */
    @Column(name ="create_date",nullable=true)
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  更新人登录名
     */
    @Column(name ="update_by",nullable=true,length=50)
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  更新人名称
     */
    @Column(name ="update_name",nullable=true,length=100)
    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String 更新时间
     */
    @Column(name ="update_date",nullable=true)
    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}
