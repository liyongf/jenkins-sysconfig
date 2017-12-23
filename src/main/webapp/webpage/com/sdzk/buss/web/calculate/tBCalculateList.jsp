<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tBCalculateList" title="统计信息" actionUrl="tBCalculateController.do?datagrid" queryMode="group" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
   <t:dgCol title="矿井编码" field="minecode"   width="120"></t:dgCol>
   <t:dgCol title="矿井名称" field="mineName"  query="true" queryMode="single"  width="120"></t:dgCol>
   <t:dgCol title="危险源总数" field="numDangerSource"   width="120"></t:dgCol>
   <t:dgCol title="隐患总数" field="numHiddenDanger"   width="120"></t:dgCol>
   <t:dgCol title="重大隐患总数" field="numMajorHiddenDanger"   width="120"></t:dgCol>
   <t:dgCol title="三违总数" field="numThreeViolations"   width="120"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="tBCalculateController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
   <%--<t:dgToolBar title="录入" icon="icon-add" url="tBCalculateController.do?addorupdate" funname="add"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="编辑" icon="icon-edit" url="tBCalculateController.do?addorupdate" funname="update"></t:dgToolBar>--%>
   <t:dgToolBar title="查看" icon="icon-search" url="tBCalculateController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>