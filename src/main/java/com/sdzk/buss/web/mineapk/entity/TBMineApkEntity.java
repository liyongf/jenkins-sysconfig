package com.sdzk.buss.web.mineapk.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.soap.Text;
import java.sql.Blob;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 矿井APK配置
 * @author onlineGenerator
 * @date 2018-06-28 15:15:55
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_b_mine_apk", schema = "")
@SuppressWarnings("serial")
public class TBMineApkEntity implements java.io.Serializable {
	/**ID*/
	private java.lang.String id;
	/**矿井ID*/
	private java.lang.String mineId;
	/**是否当前版本*/
	@Excel(name="是否当前版本",width=15)
	private java.lang.String isCurrentVersion;
	/**是否静默下载*/
	@Excel(name="是否静默下载",width=15)
	private java.lang.String isSilent;
	/**是否强制安装*/
	@Excel(name="是否强制安装",width=15)
	private java.lang.String isForce;
	/**是否下载完成后自动安装*/
	@Excel(name="是否下载完成后自动安装",width=15)
	private java.lang.String isAutoInstall;
	/**是否可忽略该版本*/
	@Excel(name="是否可忽略该版本",width=15)
	private java.lang.String isIgnorable;
	/**版本号编码*/
	@Excel(name="版本号编码",width=15)
	private java.lang.String versionCode;
	/**版本号名称*/
	@Excel(name="版本号名称",width=15)
	private java.lang.String versionName;
	/**本版更新内容*/
	@Excel(name="本版更新内容",width=15)
	private java.lang.String updateContent;
	/**apk存放地址*/
	@Excel(name="apk存放地址",width=15)
	private java.lang.String url;
	/**apk的md5*/
	@Excel(name="apk的md5",width=15)
	private java.lang.String md5;
	/**apk文件大小*/
	@Excel(name="apk文件大小",width=15)
	private java.lang.String size;
	/**是否删除*/
	@Excel(name="是否删除",width=15)
	private java.lang.String isDelete;
	/**修改人*/
	private java.lang.String updateName;
	/**修改时间*/
	private java.util.Date updateDate;
	/**修改人id*/
	private java.lang.String updateBy;
	/**创建人*/
	private java.lang.String createName;
	/**创建时间*/
	private java.util.Date createDate;
	/**创建人id*/
	private java.lang.String createBy;

	private  java.lang.String documentId;
	private  java.lang.String mineName;
	
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ID
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
	 *@param: java.lang.String  ID
	 */
	public void setId(java.lang.String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  矿井ID
	 */

	@Column(name ="MINE_ID",nullable=true,length=36)
	public java.lang.String getMineId(){
		return this.mineId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  矿井ID
	 */
	public void setMineId(java.lang.String mineId){
		this.mineId = mineId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否当前版本
	 */

	@Column(name ="IS_CURRENT_VERSION",nullable=true,length=10)
	public java.lang.String getIsCurrentVersion(){
		return this.isCurrentVersion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否当前版本
	 */
	public void setIsCurrentVersion(java.lang.String isCurrentVersion){
		this.isCurrentVersion = isCurrentVersion;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否静默下载
	 */

	@Column(name ="IS_SILENT",nullable=true,length=10)
	public java.lang.String getIsSilent(){
		return this.isSilent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否静默下载
	 */
	public void setIsSilent(java.lang.String isSilent){
		this.isSilent = isSilent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否强制安装
	 */

	@Column(name ="IS_FORCE",nullable=true,length=10)
	public java.lang.String getIsForce(){
		return this.isForce;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否强制安装
	 */
	public void setIsForce(java.lang.String isForce){
		this.isForce = isForce;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否下载完成后自动安装
	 */

	@Column(name ="IS_AUTO_INSTALL",nullable=true,length=36)
	public java.lang.String getIsAutoInstall(){
		return this.isAutoInstall;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否下载完成后自动安装
	 */
	public void setIsAutoInstall(java.lang.String isAutoInstall){
		this.isAutoInstall = isAutoInstall;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否可忽略该版本
	 */

	@Column(name ="IS_IGNORABLE",nullable=true,length=36)
	public java.lang.String getIsIgnorable(){
		return this.isIgnorable;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否可忽略该版本
	 */
	public void setIsIgnorable(java.lang.String isIgnorable){
		this.isIgnorable = isIgnorable;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  版本号编码
	 */

	@Column(name ="VERSION_CODE",nullable=true,length=10)
	public java.lang.String getVersionCode(){
		return this.versionCode;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  版本号编码
	 */
	public void setVersionCode(java.lang.String versionCode){
		this.versionCode = versionCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本号名称
	 */

	@Column(name ="VERSION_NAME",nullable=true,length=100)
	public java.lang.String getVersionName(){
		return this.versionName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本号名称
	 */
	public void setVersionName(java.lang.String versionName){
		this.versionName = versionName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  本版更新内容
	 */

	@Column(name ="UPDATE_CONTENT",nullable=true,length=500)
	public java.lang.String getUpdateContent(){
		return this.updateContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  本版更新内容
	 */
	public void setUpdateContent(java.lang.String updateContent){
		this.updateContent = updateContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  apk存放地址
	 */

	@Column(name ="URL",nullable=true,length=100)
	public java.lang.String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  apk存放地址
	 */
	public void setUrl(java.lang.String url){
		this.url = url;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  apk的md5
	 */

	@Column(name ="MD5",nullable=true,length=100)
	public java.lang.String getMd5(){
		return this.md5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  apk的md5
	 */
	public void setMd5(java.lang.String md5){
		this.md5 = md5;
	}
	/**
	 *方法: 取得java.lang.Integer
	 *@return: java.lang.Integer  apk文件大小
	 */

	@Column(name ="SIZE",nullable=true,length=10)
	public java.lang.String getSize(){
		return this.size;
	}

	/**
	 *方法: 设置java.lang.Integer
	 *@param: java.lang.Integer  apk文件大小
	 */
	public void setSize(java.lang.String size){
		this.size = size;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否删除
	 */

	@Column(name ="IS_DELETE",nullable=true,length=10)
	public java.lang.String getIsDelete(){
		return this.isDelete;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否删除
	 */
	public void setIsDelete(java.lang.String isDelete){
		this.isDelete = isDelete;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人
	 */

	@Column(name ="UPDATE_NAME",nullable=true,length=36)
	public java.lang.String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人
	 */
	public void setUpdateName(java.lang.String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */

	@Column(name ="UPDATE_DATE",nullable=true)
	public java.util.Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateDate(java.util.Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人id
	 */

	@Column(name ="UPDATE_BY",nullable=true,length=36)
	public java.lang.String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人id
	 */
	public void setUpdateBy(java.lang.String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */

	@Column(name ="CREATE_NAME",nullable=true,length=36)
	public java.lang.String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateName(java.lang.String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */

	@Column(name ="CREATE_DATE",nullable=true)
	public java.util.Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(java.util.Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人id
	 */

	@Column(name ="CREATE_BY",nullable=true,length=36)
	public java.lang.String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人id
	 */
	public void setCreateBy(java.lang.String createBy){
		this.createBy = createBy;
	}

	@Transient
	public String getDocumentId(){return this.documentId;}

	public void setDocumentId(String documentId){this.documentId = documentId;}

	@Transient
	public String getMineName(){return this.mineName;}

	public void setMineName(String mineName){this.mineName = mineName;}

}
