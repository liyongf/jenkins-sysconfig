<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tBMineDeployList" checkbox="true" pagination="true" fitColumns="true" title="矿井部署" actionUrl="tBMineDeployController.do?datagrid" onLoadSuccess="afterLoadSuccess" idField="id" fit="true" queryMode="group">
   <t:dgCol title="ID"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="煤矿Id"  field="mineOrg.id" hidden="true"  width="120"></t:dgCol>
   <t:dgCol title="部署煤矿"  field="mineOrg.name" query="true"  width="120"></t:dgCol>
   <t:dgCol title="部署人员"  field="deployer"  query="true"  width="120"></t:dgCol>
   <t:dgCol title="部署时间"  field="deployDate"  formatter="yyyy-MM-dd hh:mm"  query="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="更新内容"  field="deployReason" width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark" width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updateName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改时间"  field="updateDate"  formatter="yyyy-MM-dd hh:mm"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改人id"  field="updateBy"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createName"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate"  formatter="yyyy-MM-dd hh:mm"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人id"  field="createBy"  queryMode="group"  width="120"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
   <%--<t:dgDelOpt title="删除" url="tBMineDeployController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
   <t:dgToolBar title="录入" icon="icon-add" url="tBMineDeployController.do?goAdd" funname="add" width="800" height="600"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tBMineDeployController.do?goUpdate" funname="update" width="800" height="600"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="tBMineDeployController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tBMineDeployController.do?goUpdate" funname="detail" width="800" height="600"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
  </div>
 </div>
 <script src = "webpage/com/jeecg/minedeploy/tBMineDeployList.js"></script>		
 <script type="text/javascript">
 $(document).ready(function(){
 });
 
   
 
//导入
function ImportXls() {
	openuploadwin('Excel导入', 'tBMineDeployController.do?upload', "tBMineDeployList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tBMineDeployController.do?exportXls","tBMineDeployList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tBMineDeployController.do?exportXlsByT","tBMineDeployList");
}

 </script>