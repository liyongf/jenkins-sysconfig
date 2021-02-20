package com.sdzk.buss.web.entity.sysconfig;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;

/**
 * @Title: Entity
 * @Description: 矿井 配置信息
 * @author zhangdaihao
 * @date 2021-01-13 11:43:41
 * @version V1.0
 *
 */
@Entity
@Table(name = "sysconfigProperties", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class SysconfigEntity implements java.io.Serializable {
    /**主键*/
    private java.lang.String id;
    /**矿井编码*/
    private java.lang.String belongmine;
    /**矿井名字*/
    private java.lang.String belongminename;
    /**矿井信息*/
    private java.lang.String belongmineinfo;
    /**矿井ip*/
    private java.lang.String ip;
    /**矿井port*/
    private java.lang.String port;
    /**是否部署*/
    private java.lang.Integer isdeploy;
    /**修改时间*/
    private java.util.Date updatedt;
    /**修改用户*/
    private java.lang.String updateusers;

    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  主键
     */

    @Id
    @GeneratedValue(generator = "paymentableGenerator")
    @GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
    @Column(name ="ID",nullable=false,length=32)
    public java.lang.String getId(){
        return this.id;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  主键
     */
    public void setId(java.lang.String id){
        this.id = id;
    }
    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  矿井编码
     */
    @Column(name ="BELONGMINE",nullable=true,length=9)
    public java.lang.String getBelongmine(){
        return this.belongmine;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  矿井编码
     */
    public void setBelongmine(java.lang.String belongmine){
        this.belongmine = belongmine;
    }
    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  矿井名字
     */
    @Column(name ="BELONGMINENAME",nullable=true,length=32)
    public java.lang.String getBelongminename(){
        return this.belongminename;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  矿井名字
     */
    public void setBelongminename(java.lang.String belongminename){
        this.belongminename = belongminename;
    }
    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  矿井信息
     */
    @Column(name ="BELONGMINEINFO",nullable=true,length=100)
    public java.lang.String getBelongmineinfo(){
        return this.belongmineinfo;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  矿井信息
     */
    public void setBelongmineinfo(java.lang.String belongmineinfo){
        this.belongmineinfo = belongmineinfo;
    }
    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  矿井ip
     */
    @Column(name ="IP",nullable=true,length=32)
    public java.lang.String getIp(){
        return this.ip;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  矿井ip
     */
    public void setIp(java.lang.String ip){
        this.ip = ip;
    }
    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  矿井port
     */
    @Column(name ="PORT",nullable=true,length=32)
    public java.lang.String getPort(){
        return this.port;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  矿井port
     */
    public void setPort(java.lang.String port){
        this.port = port;
    }
    /**
     *方法: 取得java.lang.Integer
     *@return: java.lang.Integer  是否部署
     */
    @Column(name ="ISDEPLOY",nullable=true,precision=10,scale=0)
    public java.lang.Integer getIsdeploy(){
        return this.isdeploy;
    }

    /**
     *方法: 设置java.lang.Integer
     *@param: java.lang.Integer  是否部署
     */
    public void setIsdeploy(java.lang.Integer isdeploy){
        this.isdeploy = isdeploy;
    }
    /**
     *方法: 取得java.util.Date
     *@return: java.util.Date  修改时间
     */
    @Column(name ="UPDATEDT",nullable=true)
    public java.util.Date getUpdatedt(){
        return this.updatedt;
    }

    /**
     *方法: 设置java.util.Date
     *@param: java.util.Date  修改时间
     */
    public void setUpdatedt(java.util.Date updatedt){
        this.updatedt = updatedt;
    }
    /**
     *方法: 取得java.lang.String
     *@return: java.lang.String  修改用户
     */
    @Column(name ="UPDATEUSERS",nullable=true,length=32)
    public java.lang.String getUpdateusers(){
        return this.updateusers;
    }

    /**
     *方法: 设置java.lang.String
     *@param: java.lang.String  修改用户
     */
    public void setUpdateusers(java.lang.String updateusers){
        this.updateusers = updateusers;
    }
}
