<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>

<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tBAuthList" checkbox="true" pagination="true" fitColumns="true" title="授权信息" actionUrl="tBAuthController.do?datagrid" idField="id" fit="true" queryMode="group" sortName="createDate" sortOrder="desc">
    <t:dgCol title="唯一标识"  field="id"  hidden="true" queryMode="group"  width="120" align="center"></t:dgCol>
    <t:dgCol title="授权编码"  field="authNum" width="120" align="center"></t:dgCol>
    <t:dgCol title="设备mac地址"  field="deviceMac" query="true" width="120" align="center"></t:dgCol>
    <t:dgCol title="起始时间"  field="beginDate" query="true" queryMode="group" formatter="yyyy-MM-dd hh:mm:ss" width="120" align="center"></t:dgCol>
    <%--<t:dgCol title="有效期"  field="beAuthdMan" query="true" width="120" align="center"></t:dgCol>--%>
    <t:dgCol title="结束时间"  field="endDate" query="true" queryMode="group" formatter="yyyy-MM-dd hh:mm:ss" width="120" align="center"></t:dgCol>
    <t:dgToolBar title="录入" icon="icon-add" url="tBAuthController.do?goAdd" funname="add" operationCode="add"></t:dgToolBar>
    <t:dgToolBar title="编辑" icon="icon-edit" url="tBAuthController.do?goUpdate" funname="update" operationCode="update" ></t:dgToolBar>
    <t:dgToolBar title="批量删除"  icon="icon-remove" url="tBAuthController.do?doBatchDel" funname="deleteALLSelect" operationCode="batchdelete"></t:dgToolBar>

  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
 $(document).ready(function(){

 });

 window.top["reload_tBAuthList"]=function(){
     $("#tBAuthList").datagrid( "load");
     if(typeof(this.msg)!='undeauthd' && this.msg!=null&&this.msg!=""){
         tip(this.msg);
     }
 };

 </script>