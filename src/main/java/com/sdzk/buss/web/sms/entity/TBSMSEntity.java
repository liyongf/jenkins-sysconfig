package com.sdzk.buss.web.sms.entity;

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
 * @smsor onlineGenerator
 * @date 2017-09-26 10:21:57
 * @version V1.0   
 *
 */
@Entity
@Table(name = "t_b_sms", schema = "")
@SuppressWarnings("serial")
public class TBSMSEntity implements java.io.Serializable {

    /**唯一标识*/
    private String id;
    /**集团编码*/
    private String groupCode;
    /**集团名称*/
    private String groupName;
    /**矿井编码*/
    private String mineCode;
    /**矿井名称*/
    private String mineName;
    /**请求发送时间*/
    private Date requestTime;
    /**实际发送时间*/
    private Date sendTime;
    /**短信类型*/
    private String type;
    /**发送内容*/
    private String content;
    /**目标手机号*/
    private String phoneNumber;
    /**发送状态*/
    private String handleStatus;
    /**最高组织机构*/
    private String topDepartNames;
    /**服务器信息*/
    private String serverInfo;

    /**创建人,录入人*/
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
     *@return: java.lang.String
     */
    @Column(name ="group_code",nullable=true,length=50)
    public String getGroupCode() {
        return groupCode;
    }
    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    @Column(name ="group_name",nullable=true,length=50)
    public String getGroupName() {
        return groupName;
    }
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Column(name ="mine_code",nullable=true,length=50)
    public String getMineCode() {
        return mineCode;
    }
    public void setMineCode(String mineCode) {
        this.mineCode = mineCode;
    }

    @Column(name ="mine_name",nullable=true,length=50)
    public String getMineName() {
        return mineName;
    }
    public void setMineName(String mineName) {
        this.mineName = mineName;
    }

    @Column(name ="request_time",nullable=true)
    public Date getRequestTime() {
        return requestTime;
    }
    public void setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
    }

    @Column(name ="send_time",nullable=true)
    public Date getSendTime() {
        return sendTime;
    }
    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    @Column(name ="type",nullable=true,length=10)
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    @Column(name ="content",nullable=true,length=1000)
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }

    @Column(name ="phone_number",nullable=true,length=20)
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Column(name ="handle_status",nullable=true,length=10)
    public String getHandleStatus() {
        return handleStatus;
    }
    public void setHandleStatus(String handleStatus) {
        this.handleStatus = handleStatus;
    }

    @Column(name ="top_depart_names",nullable=true,length=50)
    public String getTopDepartNames() {
        return topDepartNames;
    }
    public void setTopDepartNames(String topDepartNames) {
        this.topDepartNames = topDepartNames;
    }


    @Column(name ="server_info",nullable=true,length=500)
    public String getServerInfo() {
        return serverInfo;
    }
    public void setServerInfo(String serverInfo) {
        this.serverInfo = serverInfo;
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
