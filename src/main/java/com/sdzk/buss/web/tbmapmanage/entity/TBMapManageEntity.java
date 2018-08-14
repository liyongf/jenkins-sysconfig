package com.sdzk.buss.web.tbmapmanage.entity;

import java.math.BigDecimal;
import java.util.Date;
import java.lang.String;
import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import javax.xml.soap.Text;
import java.sql.Blob;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.sdzk.buss.web.common.Constants;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.SequenceGenerator;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.web.system.pojo.base.TSUser;

/**   
 * @Title: Entity
 * @Description: 矿图管理
 * @author onlineGenerator
 * @date 2018-08-06 16:21:02
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_b_map_manage", schema = "")
@SuppressWarnings("serial")
public class TBMapManageEntity implements java.io.Serializable {
	/**主键*/
	private String id;
	/**关系Id*/
	private String sendId;
	/**上传人名称*/
	private String uploadName;
	/**上传人登录名称*/
	private String uploadBy;
	/**上传日期*/
	private Date uploadDate;
	/**解压路径*/
	@Excel(name="解压路径",width=15)
	private String filePath;
	/**是否使用*/
	@Excel(name="是否使用",width=15)
	private String isUsed;
	/**是否删除*/
	@Excel(name="是否删除",width=15)
	private String isDelete;
	/**上传类型*/
	@Excel(name="上传类型",width=15)
	private String uploadType;
	/**状态*/
	@Excel(name="状态",width=15)
	private String status;
	/**客户端ip地址*/
	private String ipAddress;
	/**端口号*/
	private String port;
	/**项目名*/
	private String projectName;

	public TBMapManageEntity(){

	}

	public TBMapManageEntity(String mapId, String uoloadName, String filePath, String ipAddress, String port, String projectName, String uploadType){
		this.sendId = mapId;
		this.filePath = filePath;
		this.uploadDate = new Date();
		this.uploadBy = null;
		this.uploadName = uoloadName;
		this.isDelete = Constants.IS_DELETE_N;
		this.isUsed = "0";
		this.uploadType = uploadType;
		this.status = "0";
		this.ipAddress = ipAddress;
		this.port = port;
		this.projectName = projectName;
	}

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")

	@Column(name ="ID",nullable=false,length=36)
	public String getId(){
		return this.id;
	}

	public void setId(String id){
		this.id = id;
	}

	@Column(name ="UPLOAD_NAME",nullable=true,length=50)
	public String getUploadName(){
		return this.uploadName;
	}

	public void setUploadName(String uploadName){
		this.uploadName = uploadName;
	}

	@Column(name ="UPLOAD_BY",nullable=true,length=50)
	public String getUploadBy(){
		return this.uploadBy;
	}

	public void setUploadBy(String uploadBy){
		this.uploadBy = uploadBy;
	}

	@Column(name ="UPLOAD_DATE",nullable=true,length=20)
	public Date getUploadDate(){
		return this.uploadDate;
	}

	public void setUploadDate(Date uploadDate){
		this.uploadDate = uploadDate;
	}

	@Column(name ="FILE_PATH",nullable=true,length=225)
	public String getFilePath(){
		return this.filePath;
	}

	public void setFilePath(String filePath){
		this.filePath = filePath;
	}

	@Column(name ="IS_USED",nullable=true,length=5)
	public String getIsUsed(){
		return this.isUsed;
	}

	public void setIsUsed(String isUsed){
		this.isUsed = isUsed;
	}

	@Column(name ="IS_DELETE",nullable=true,length=5)
	public String getIsDelete(){
		return this.isDelete;
	}

	public void setIsDelete(String isDelete){
		this.isDelete = isDelete;
	}

	@Column(name ="UPLOAD_TYPE",nullable=true,length=32)
	public String getUploadType(){
		return this.uploadType;
	}

	public void setUploadType(String uploadType){
		this.uploadType = uploadType;
	}

	@Column(name ="STATUS",nullable=true,length=32)
	public String getStatus(){
		return this.status;
	}

	public void setStatus(String status){
		this.status = status;
	}

	@Column(name ="SEND_ID",nullable=false,length=36)
	public String getSendId() { return sendId; }

	public void setSendId(String sendId) { this.sendId = sendId; }

	@Column(name ="IP_ADDRESS",nullable=false,length=255)
	public String getIpAddress() { return ipAddress; }

	public void setIpAddress(String ipAddress) { this.ipAddress = ipAddress; }

	@Column(name ="PORT",nullable=false,length=36)
	public String getPort() { return port; }

	public void setPort(String port) { this.port = port; }

	@Column(name ="PROJECT_NAME",nullable=false,length=36)
	public String getProjectName() { return projectName; }

	public void setProjectName(String projectName) { this.projectName = projectName; }
}
