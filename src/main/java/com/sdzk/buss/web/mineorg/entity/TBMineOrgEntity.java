package com.sdzk.buss.web.mineorg.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.persistence.*;
import javax.xml.soap.Text;
import java.sql.Blob;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;

/**   
 * @Title: Entity
 * @Description: 矿井组织机构
 * @author onlineGenerator
 * @date 2018-05-18 11:03:03
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_b_mine_org", schema = "")
@SuppressWarnings("serial")
public class TBMineOrgEntity implements java.io.Serializable {
	/**ID*/
	private java.lang.String id;
	/**类型*/
	@Excel(name="类型",width=15)
	private java.lang.String type;
	/**编码*/
	@Excel(name="编码",width=15)
	private java.lang.String code;
	/**排序*/
	@Excel(name="排序",width=15)
	private java.lang.String order;
	/**名称*/
	@Excel(name="名称",width=15)
	private java.lang.String name;
	/**是否联网煤监*/
	@Excel(name="是否联网煤监",width=15)
	private java.lang.String isConnectToUpper;
	/**风险辨识方法*/
	@Excel(name="风险辨识方法",width=15)
	private java.lang.String riskRecogType;
	/**远程连接类型*/
	@Excel(name="远程连接类型",width=15)
	private java.lang.String remoteConnectType;
	/**连接凭据*/
	@Excel(name="连接凭据",width=15)
	private java.lang.String remoteConnectCert;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark;
	/**是否通用版*/
	@Excel(name="是否通用版",width=15)
	private java.lang.String isCommonVersion;
	/**分支地址*/
	@Excel(name="分支地址",width=15)
	private java.lang.String deployBranch;
	/**上次部署时间*/
	@Excel(name="上次部署时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date lastDeployTime;
	/**修改人*/
	@Excel(name="修改人",width=15)
	private java.lang.String updateName;
	/**修改时间*/
	@Excel(name="修改时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date updateDate;
	/**修改人id*/
	@Excel(name="修改人id",width=15)
	private java.lang.String updateBy;
	/**创建人*/
	@Excel(name="创建人",width=15)
	private java.lang.String createName;
	/**创建时间*/
	@Excel(name="创建时间",width=15,format = "yyyy-MM-dd HH:mm:ss")
	private java.util.Date createDate;
	/**创建人id*/
	@Excel(name="创建人id",width=15)
	private java.lang.String createBy;
	/**上级单位*/
	@Excel(name="上级单位",width=15)
	private TBMineOrgEntity parentOrg;
	private List<TBMineOrgEntity> childOrgs = new ArrayList<TBMineOrgEntity>();//下属单位

	/**app关联标识*/
	@Excel(name="app关联标识",width=15)
	private java.lang.String appCode;
	/**当前app版本*/
	@Excel(name="当前apk版本",width=15)
	private java.lang.String appVersion;
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
	 *@return: java.lang.String  类型
	 */
	@Column(name ="ORG_TYPE",nullable=false,length=36)
	public java.lang.String getType(){
		return this.type;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  类型
	 */
	public void setType(java.lang.String type){
		this.type = type;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  编码
	 */
	@Column(name ="ORG_CODE",nullable=false,length=36)
	public java.lang.String getCode(){
		return this.code;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  编码
	 */
	public void setCode(java.lang.String code){
		this.code = code;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  排序
	 */
	@Column(name ="org_order",nullable=false,length=36)
	public java.lang.String getOrder(){
		return this.order;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  排序
	 */
	public void setOrder(java.lang.String order){
		this.order = order;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  名称
	 */
	@Column(name ="ORG_NAME",nullable=true,length=50)
	public java.lang.String getName(){
		return this.name;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  名称
	 */
	public void setName(java.lang.String name){
		this.name = name;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否联网煤监
	 */
	@Column(name ="IS_CONNECT_TO_UPPER",nullable=true,length=10)
	public java.lang.String getIsConnectToUpper(){
		return this.isConnectToUpper;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否联网煤监
	 */
	public void setIsConnectToUpper(java.lang.String isConnectToUpper){
		this.isConnectToUpper = isConnectToUpper;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  风险辨识方法
	 */
	@Column(name ="RISK_RECOG_TYPE",nullable=true,length=36)
	public java.lang.String getRiskRecogType(){
		return this.riskRecogType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  风险辨识方法
	 */
	public void setRiskRecogType(java.lang.String riskRecogType){
		this.riskRecogType = riskRecogType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  远程连接类型
	 */
	@Column(name ="REMOTE_CONNECT_TYPE",nullable=true,length=36)
	public java.lang.String getRemoteConnectType(){
		return this.remoteConnectType;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  远程连接类型
	 */
	public void setRemoteConnectType(java.lang.String remoteConnectType){
		this.remoteConnectType = remoteConnectType;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  连接凭据
	 */
	@Column(name ="REMOTE_CONNECT_CERT",nullable=true,length=500)
	public java.lang.String getRemoteConnectCert(){
		return this.remoteConnectCert;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  连接凭据
	 */
	public void setRemoteConnectCert(java.lang.String remoteConnectCert){
		this.remoteConnectCert = remoteConnectCert;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */
	@Column(name ="REMARK",nullable=true,length=500)
	public java.lang.String getRemark(){
		return this.remark;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  备注
	 */
	public void setRemark(java.lang.String remark){
		this.remark = remark;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  是否通用版
	 */
	@Column(name ="IS_COMMON_VERSION",nullable=true,length=10)
	public java.lang.String getIsCommonVersion(){
		return this.isCommonVersion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  是否通用版
	 */
	public void setIsCommonVersion(java.lang.String isCommonVersion){
		this.isCommonVersion = isCommonVersion;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  分支地址
	 */
	@Column(name ="DEPLOY_BRANCH",nullable=true,length=500)
	public java.lang.String getDeployBranch(){
		return this.deployBranch;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  分支地址
	 */
	public void setDeployBranch(java.lang.String deployBranch){
		this.deployBranch = deployBranch;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  上次部署时间
	 */
	@Column(name ="LAST_DEPLOY_TIME",nullable=true)
	public java.util.Date getLastDeployTime(){
		return this.lastDeployTime;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  上次部署时间
	 */
	public void setLastDeployTime(java.util.Date lastDeployTime){
		this.lastDeployTime = lastDeployTime;
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
	/**
	 *方法: 取得TBMineOrgEntity
	 *@return: TBMineOrgEntity  上级单位
	 */

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public TBMineOrgEntity getParentOrg(){
		return this.parentOrg;
	}

	/**
	 *方法: 设置TBMineOrgEntity
	 *@param: TBMineOrgEntity  上级单位
	 */
	public void setParentOrg(TBMineOrgEntity parentOrg){
		this.parentOrg = parentOrg;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "parentOrg")
	public List<TBMineOrgEntity> getChildOrgs() {
		return this.childOrgs;
	}

	public void setChildOrgs(List<TBMineOrgEntity> childOrgs) {
		this.childOrgs = childOrgs;
	}
	private Short deleteFlag;// 状态: 0:不删除  1：删除
	@Column(name = "delete_flag",nullable = true, length = 6)
	public Short getDeleteFlag() {
		return deleteFlag;
	}
	public void setDeleteFlag(Short deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	private String lastDeployTimeTemp;
	@Transient
	public String getLastDeployTimeTemp(){return this.lastDeployTimeTemp;}
	public void setLastDeployTimeTemp(String lastDeployTimeTemp){
		this.lastDeployTimeTemp = lastDeployTimeTemp;
	}

	private String createDateTemp;
	@Transient
	public String getCreateDateTemp(){return this.createDateTemp;}
	public void setCreateDateTemp(String createDateTemp){
		this.createDateTemp = createDateTemp;
	}

	private String updateDateTemp;
	@Transient
	public String getUpdateDateTemp(){return this.updateDateTemp;}
	public void setUpdateDateTemp(String updateDateTemp){
		this.updateDateTemp = updateDateTemp;
	}

	private String lastDeployer;
	@Column(name ="LAST_DEPLOYER",nullable=true)
	public String getLastDeployer(){
		return this.lastDeployer;
	}
	public void setLastDeployer(String lastDeployer){
		this.lastDeployer = lastDeployer;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  app关联标识
	 */
	@Column(name ="app_code",nullable=true,length=500)
	public java.lang.String getAppCode(){
		return this.appCode;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  app关联标识
	 */
	public void setAppCode(java.lang.String appCode){
		this.appCode = appCode;
	}

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  当前app版本
	 */
	@Column(name ="app_version",nullable=true,length=500)
	public java.lang.String getAppVersion(){
		return this.appVersion;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  当前app版本
	 */
	public void setAppVersion(java.lang.String appVersion){
		this.appVersion = appVersion;
	}

}
