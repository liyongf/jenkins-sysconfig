package org.jeecgframework.web.system.pojo.base;

import org.hibernate.annotations.GenericGenerator;
import org.jeecgframework.core.common.entity.IdEntity;
import org.jeecgframework.poi.excel.annotation.Excel;

import javax.persistence.*;
import java.util.Date;


/**
 * @author  张代浩
 * 项目附件父表(其他附件表需继承该表)
 */
@Entity
@Table(name = "t_b_ip_server_config", schema = "")
@SuppressWarnings("serial")
public  class IpServerConfig implements java.io.Serializable {
	
	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 * @since Ver 1.1
	 */

	/**主键*/
	private String id;
	@Excel(name="公有IP")
	private String publicIp;// 公网ip
	@Excel(name="私有IP")
	private String privateIp;// 私有ip
	private String ipWord;// 密码
	private String receviceMan;//密码接收人
	private Date createDate; //创建时间
	private Date updateDate;  //更新时间

	/**
	 *方法: 取得java.lang.String
	 *@return: java.lang.String  主键
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
	 *@param: java.lang.String  主键
	 */
	public void setId(String id){
		this.id = id;
	}
	@Column(name = "PUBLIC_IP", length = 32)
	public String getPublicIp() {
		return publicIp;
	}

	public void setPublicIp(String publicIp) {
		this.publicIp = publicIp;
	}

	@Column(name = "PRIVATE_IP", length = 100)
	public String getPrivateIp() {
		return this.privateIp;
	}

	public void setPrivateIp(String privateIp) {
		this.privateIp = privateIp;
	}

	@Column(name = "IP_WORD", length = 32)
	public String getIpWord() {
		return this.ipWord;
	}

	public void setIpWord(String ipWord) {
		this.ipWord = ipWord;
	}


	@Column(name = "RECEVICE_MAN", length = 32)
	public String getReceviceMan() {
		return this.receviceMan;
	}

	public void setReceviceMan(String receviceMan) {
		this.receviceMan = receviceMan;
	}



	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  创建日期
	 */
	@Column(name ="CREATE_DATE",nullable=true)
	public Date getCreateDate(){
		return this.createDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  创建日期
	 */
	public void setCreateDate(Date createDate){
		this.createDate = createDate;
	}
	/**
	 *方法: 取得java.util.Date
	 *@return: java.util.Date  更新日期
	 */
	@Column(name ="UPDATE_DATE",nullable=true)
	public Date getUpdateDate(){
		return this.updateDate;
	}

	/**
	 *方法: 设置java.util.Date
	 *@param: java.util.Date  更新日期
	 */
	public void setUpdateDate(Date updateDate){
		this.updateDate = updateDate;
	}

}