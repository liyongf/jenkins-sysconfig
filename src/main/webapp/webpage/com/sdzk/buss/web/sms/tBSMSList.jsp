<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tBSMSList" checkbox="true" pagination="true" fitColumns="true" title="短信发送列表" actionUrl="tBSMSController.do?datagrid" idField="id" fit="true" queryMode="group" sortName="createDate" sortOrder="desc">
    <t:dgCol title="唯一标识"  field="id"  hidden="true" queryMode="group"  width="120" align="center"></t:dgCol>
    <t:dgCol title="集团"  field="groupName" query="true" width="120" align="center"></t:dgCol>
    <t:dgCol title="煤矿"  field="mineName" query="true" width="120" align="center"></t:dgCol>
    <t:dgCol title="类型"  field="type" dictionary="smsType" query="true" width="120" align="center"></t:dgCol>
    <t:dgCol title="内容"  field="content" query="true" width="120" align="center"></t:dgCol>
    <t:dgCol title="发送至"  field="phoneNumber" query="true" width="120" align="center"></t:dgCol>
    <t:dgCol title="请求发送时间"  field="requestTime" query="true" queryMode="group" formatter="yyyy-MM-dd hh:mm:ss" width="120" align="center"></t:dgCol>
    <t:dgCol title="实际发送时间"  field="sendTime" query="true" queryMode="group" formatter="yyyy-MM-dd hh:mm:ss" width="120" align="center"></t:dgCol>
    <t:dgCol title="发送状态"  field="handleStatus" dictionary="smsSendStatus" query="true" width="120" align="center"></t:dgCol>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){

 });

 </script>