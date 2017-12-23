package com.sdzk.buss.web.calculate.entity;

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
 * @Description: 统计信息
 * @author zhangdaihao
 * @date 2017-12-21 15:11:20
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_b_calculate", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TBCalculateEntity implements java.io.Serializable {
	/**主键*/
	private java.lang.String id;
	/**矿井编码*/
	private java.lang.String minecode;
	/**矿井名称*/
	private java.lang.String mineName;
	/**危险源总数*/
	private java.lang.String numDangerSource;
	/**隐患总数*/
	private java.lang.String numHiddenDanger;
	/**重大隐患总数*/
	private java.lang.String numMajorHiddenDanger;
	/**三违总数*/
	private java.lang.String numThreeViolations;
	/**createName*/
	private java.lang.String createName;
	/**createBy*/
	private java.lang.String createBy;
	/**createDate*/
	private java.util.Date createDate;
	/**updateName*/
	private java.lang.String updateName;
	/**updateBy*/
	private java.lang.String updateBy;
	/**updateDate*/
	private java.util.Date updateDate;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
	 */
	
	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
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
	@Column(name ="MINECODE",nullable=true,length=50)
	public java.lang.String getMinecode(){
		return this.minecode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  矿井编码
	 */
	public void setMinecode(java.lang.String minecode){
		this.minecode = minecode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  矿井名称
	 */
	@Column(name ="MINE_NAME",nullable=true,length=50)
	public java.lang.String getMineName(){
		return this.mineName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  矿井名称
	 */
	public void setMineName(java.lang.String mineName){
		this.mineName = mineName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  危险源总数
	 */
	@Column(name ="NUM_DANGER_SOURCE",nullable=true,length=10)
	public java.lang.String getNumDangerSource(){
		return this.numDangerSource;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  危险源总数
	 */
	public void setNumDangerSource(java.lang.String numDangerSource){
		this.numDangerSource = numDangerSource;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  隐患总数
	 */
	@Column(name ="NUM_HIDDEN_DANGER",nullable=true,length=10)
	public java.lang.String getNumHiddenDanger(){
		return this.numHiddenDanger;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  隐患总数
	 */
	public void setNumHiddenDanger(java.lang.String numHiddenDanger){
		this.numHiddenDanger = numHiddenDanger;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  重大隐患总数
	 */
	@Column(name ="NUM_MAJOR_HIDDEN_DANGER",nullable=true,length=10)
	public java.lang.String getNumMajorHiddenDanger(){
		return this.numMajorHiddenDanger;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  重大隐患总数
	 */
	public void setNumMajorHiddenDanger(java.lang.String numMajorHiddenDanger){
		this.numMajorHiddenDanger = numMajorHiddenDanger;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  三违总数
	 */
	@Column(name ="NUM_THREE_VIOLATIONS",nullable=true,length=10)
	public java.lang.String getNumThreeViolations(){
		return this.numThreeViolations;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  三违总数
	 */
	public void setNumThreeViolations(java.lang.String numThreeViolations){
		this.numThreeViolations = numThreeViolations;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  createName
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=50)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  createName
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  createBy
	 */
	@Column(name ="CREATE_BY",nullable=true,length=50)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  createBy
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  createDate
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  createDate
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  updateName
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=50)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  updateName
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  updateBy
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=50)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  updateBy
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  updateDate
	 */
	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  updateDate
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
}
