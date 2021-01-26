package com.sdzk.buss.web.uniapk.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

/**
 * @Title: Entity
 * @Description: uniapp 版本控制
 * @author zhangdaihao
 * @date 2021-01-18 15:31:51
 * @version V1.0
 *
 */
@Entity
@Table(name = "t_b_mine_uniapp_apk", schema = "")
@DynamicUpdate(true)
@DynamicInsert(true)
@SuppressWarnings("serial")
public class TBMineUniappApkEntity implements java.io.Serializable {
	/**ID*/
	private String id;
	/**是否静默下载  0：否  1：是*/
	private String isSilent;
	/**是否强制安装  0：否  1：是*/
	private String isForce;
	/**是否下载完成后自动安装   0：否  1：是*/
	private String isAutoInstall;
	/**是否可忽略该版本   0：否  1：是*/
	private String isIgnorable;
	/**版本号编码*/
	private String versionCode;
	/**版本号名称*/
	private String versionName;
	/**本版更新内容*/
	private String updateContent;
	/**apk存放地址*/
	private String url;
	/**apk的md5*/
	private String md5;
	/**apk文件大小*/
	private String size;
	/**是否删除    0：否  1：是*/
	private String isDelete;
	/**修改人*/
	private String updateName;
	/**修改时间*/
	private Date updateDate;
	/**修改人id*/
	private String updateBy;
	/**创建人*/
	private String createName;
	/**创建时间*/
	private Date createDate;
	/**创建人id*/
	private String createBy;
	/**矿井ID*/
	private String mineId;
	/**是否当前版本 0：否  1：是*/
	private String isCurrentVersion;
	private  java.lang.String mineName;
	private  java.lang.String documentId;
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  ID
	 */

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  ID
	 */
	public void setId(String id){
		this.id = id;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否静默下载  0：否  1：是
	 */
	@Column(name ="IS_SILENT",nullable=true,length=10)
	public String getIsSilent(){
		return this.isSilent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否静默下载  0：否  1：是
	 */
	public void setIsSilent(String isSilent){
		this.isSilent = isSilent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否强制安装  0：否  1：是
	 */
	@Column(name ="IS_FORCE",nullable=true,length=10)
	public String getIsForce(){
		return this.isForce;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否强制安装  0：否  1：是
	 */
	public void setIsForce(String isForce){
		this.isForce = isForce;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否下载完成后自动安装   0：否  1：是
	 */
	@Column(name ="IS_AUTO_INSTALL",nullable=true,length=36)
	public String getIsAutoInstall(){
		return this.isAutoInstall;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否下载完成后自动安装   0：否  1：是
	 */
	public void setIsAutoInstall(String isAutoInstall){
		this.isAutoInstall = isAutoInstall;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否可忽略该版本   0：否  1：是
	 */
	@Column(name ="IS_IGNORABLE",nullable=true,length=36)
	public String getIsIgnorable(){
		return this.isIgnorable;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否可忽略该版本   0：否  1：是
	 */
	public void setIsIgnorable(String isIgnorable){
		this.isIgnorable = isIgnorable;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本号编码
	 */
	@Column(name ="VERSION_CODE",nullable=true,length=100)
	public String getVersionCode(){
		return this.versionCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本号编码
	 */
	public void setVersionCode(String versionCode){
		this.versionCode = versionCode;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  版本号名称
	 */
	@Column(name ="VERSION_NAME",nullable=true,length=100)
	public String getVersionName(){
		return this.versionName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  版本号名称
	 */
	public void setVersionName(String versionName){
		this.versionName = versionName;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  本版更新内容
	 */
	@Column(name ="UPDATE_CONTENT",nullable=true,length=500)
	public String getUpdateContent(){
		return this.updateContent;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  本版更新内容
	 */
	public void setUpdateContent(String updateContent){
		this.updateContent = updateContent;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  apk存放地址
	 */
	@Column(name ="URL",nullable=true,length=100)
	public String getUrl(){
		return this.url;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  apk存放地址
	 */
	public void setUrl(String url){
		this.url = url;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  apk的md5
	 */
	@Column(name ="MD5",nullable=true,length=100)
	public String getMd5(){
		return this.md5;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  apk的md5
	 */
	public void setMd5(String md5){
		this.md5 = md5;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  apk文件大小
	 */
	@Column(name ="SIZE",nullable=true,length=100)
	public String getSize(){
		return this.size;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  apk文件大小
	 */
	public void setSize(String size){
		this.size = size;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否删除    0：否  1：是
	 */
	@Column(name ="IS_DELETE",nullable=true,length=10)
	public String getIsDelete(){
		return this.isDelete;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否删除    0：否  1：是
	 */
	public void setIsDelete(String isDelete){
		this.isDelete = isDelete;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人
	 */
	@Column(name ="UPDATE_NAME",nullable=true,length=36)
	public String getUpdateName(){
		return this.updateName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人
	 */
	public void setUpdateName(String updateName){
		this.updateName = updateName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  修改时间
	 */
	@Column(name ="UPDATE_DATE",nullable=true)
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  修改时间
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  修改人id
	 */
	@Column(name ="UPDATE_BY",nullable=true,length=36)
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  修改人id
	 */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人
	 */
	@Column(name ="CREATE_NAME",nullable=true,length=36)
	public String getCreateName(){
		return this.createName;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人
	 */
	public void setCreateName(String createName){
		this.createName = createName;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建时间
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建时间
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  创建人id
	 */
	@Column(name ="CREATE_BY",nullable=true,length=36)
	public String getCreateBy(){
		return this.createBy;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  创建人id
	 */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  矿井ID
	 */
	@Column(name ="MINE_ID",nullable=true,length=36)
	public String getMineId(){
		return this.mineId;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  矿井ID
	 */
	public void setMineId(String mineId){
		this.mineId = mineId;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否当前版本 0：否  1：是
	 */
	@Column(name ="IS_CURRENT_VERSION",nullable=true,length=10)
	public String getIsCurrentVersion(){
		return this.isCurrentVersion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否当前版本 0：否  1：是
	 */
	public void setIsCurrentVersion(String isCurrentVersion){
		this.isCurrentVersion = isCurrentVersion;
	}
	@Transient
	public String getMineName(){return this.mineName;}

	public void setMineName(String mineName){this.mineName = mineName;}

	@Transient
	public String getDocumentId(){return this.documentId;}

	public void setDocumentId(String documentId){this.documentId = documentId;}

}
