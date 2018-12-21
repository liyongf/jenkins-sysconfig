package com.sdzk.buss.web.smsmine.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**   
 * @Title: Entity
 * @Description: 短信权限
 * @author zhangdaihao
 * @date 2018-12-20 14:33:47
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_b_sms_mine", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TBSmsMineEntity implements java.io.Serializable {
	/**唯一标识*/
	private String id;
	/**矿井编码*/
	private String mineCode;
	/**矿井名称*/
	private String mineName;
	/**授权时间开始*/
	private Date beginMineDate;
	/**授权时间结束*/
	private Date endMineDate;

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  唯一标识
	 */

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=32)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  唯一标识
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  矿井编码
	 */
	@Column(name ="MINE_CODE",nullable=true,length=50)
	public String getMineCode(){
		return this.mineCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  矿井编码
	 */
	public void setMineCode(String mineCode){
		this.mineCode = mineCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  矿井名称
	 */
	@Column(name ="MINE_NAME",nullable=true,length=50)
	public String getMineName(){
		return this.mineName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  矿井名称
	 */
	public void setMineName(String mineName){
		this.mineName = mineName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  授权时间开始
	 */
	@Column(name ="BEGIN_MINE_DATE",nullable=true)
	public Date getBeginMineDate(){
		return this.beginMineDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  授权时间开始
	 */
	public void setBeginMineDate(Date beginMineDate){
		this.beginMineDate = beginMineDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  授权时间结束
	 */
	@Column(name ="END_MINE_DATE",nullable=true)
	public Date getEndMineDate(){
		return this.endMineDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  授权时间结束
	 */
	public void setEndMineDate(Date endMineDate){
		this.endMineDate = endMineDate;
	}
}
