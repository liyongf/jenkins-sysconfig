package com.sdzk.buss.web.minedeploy.entity;

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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.jeecgframework.poi.excel.annotation.Excel;
import com.sdzk.buss.web.mineorg.entity.TBMineOrgEntity;
/**   
 * @Title: Entity
 * @Description: 矿井部署
 * @author onlineGenerator
 * @date 2018-05-18 11:04:04
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_b_mine_deploy", schema = "")
@SuppressWarnings("serial")
public class TBMineDeployEntity implements java.io.Serializable {
	/**ID*/
	private java.lang.String id;
	/**矿的id*/
	@Excel(name="矿的id",width=15)
	private TBMineOrgEntity mineOrg;
	/**部署人员名称*/
	@Excel(name="部署人员名称",width=15)
	private java.lang.String deployer;
	/**部署时间*/
	@Excel(name="部署时间",width=15,format = "yyyy-MM-dd")
	private java.util.Date deployDate;
	/**更新内容*/
	@Excel(name="更新内容",width=15)
	private java.lang.String deployReason;
	/**备注*/
	@Excel(name="备注",width=15)
	private java.lang.String remark;

	@Excel(name="风险辨识方法",width=15)
	private java.lang.String riskRecogType;

	@Excel(name="分支地址",width=15)
	private java.lang.String deployBranch;

	@Excel(name="本次部署分支",width=15)
	private java.lang.String thisDeployBranch;

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
	 *@return: java.lang.String  矿的id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MINE_ID")
	@NotFound(action= NotFoundAction.IGNORE)
	public TBMineOrgEntity getMineOrg(){
		return this.mineOrg;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  矿的id
	 */
	public void setMineOrg(TBMineOrgEntity mineOrg){
		this.mineOrg = mineOrg;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  部署人员名称
	 */

	@Column(name ="DEPLOYER",nullable=true,length=36)
	public java.lang.String getDeployer(){
		return this.deployer;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  部署人员名称
	 */
	public void setDeployer(java.lang.String deployer){
		this.deployer = deployer;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  部署时间
	 */

	@Column(name ="DEPLOY_DATE",nullable=true)
	public java.util.Date getDeployDate(){
		return this.deployDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  部署时间
	 */
	public void setDeployDate(java.util.Date deployDate){
		this.deployDate = deployDate;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  更新内容
	 */

	@Column(name ="DEPLOY_REASON",nullable=true,length=1000)
	public java.lang.String getDeployReason(){
		return this.deployReason;
	}

	/**
	 *方法: 设置java.lang.String
	 *@param: java.lang.String  更新内容
	 */
	public void setDeployReason(java.lang.String deployReason){
		this.deployReason = deployReason;
	}
	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  备注
	 */

	@Column(name ="REMARK",nullable=true,length=1000)
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

	private String mineNameTemp;
	@Transient
	public String getMineNameTemp(){
		return mineNameTemp;
	}
	public void setMineNameTemp(String mineNameTemp){
		this.mineNameTemp = mineNameTemp;
	}

	@Column(name ="RISK_RECOG_TYPE",nullable=true,length=36)
	public String getRiskRecogType() {
		return riskRecogType;
	}

	public void setRiskRecogType(String riskRecogType) {
		this.riskRecogType = riskRecogType;
	}

	@Column(name ="DEPLOY_BRANCH",nullable=true,length=500)
	public String getDeployBranch() {
		return deployBranch;
	}

	public void setDeployBranch(String deployBranch) {
		this.deployBranch = deployBranch;
	}

	@Column(name ="THIS_DEPLOY_BRANCH",nullable=true,length=500)
	public String getThisDeployBranch() {
		return thisDeployBranch;
	}

	public void setThisDeployBranch(String thisDeployBranch) {
		this.thisDeployBranch = thisDeployBranch;
	}
}
