<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="searchBtnDiv" style="display: none;">
 <div name="searchColums" id="searchColums">
  <input id="isShowSearchId" value="false" type="hidden" />
  <input id="_sqlbuilder" name="sqlbuilder" type="hidden" />
  <form onkeydown="if(event.keyCode==13){tBMineOrgListsearch();return false;}" id="tBMineOrgListForm">
   <link rel="stylesheet" href="plug-in/Validform/css/style.css" type="text/css" />
   <link rel="stylesheet" href="plug-in/Validform/css/tablefrom.css" type="text/css" />
   <input type="hidden" name="isSearch" value="yes" />
            <span style="display:-moz-inline-box;display:inline-block;">
                <span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 90px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap; " title="名称">名称：</span>
                <input onkeypress="EnterPress(event)" onkeydown="EnterPress()" name="name" style="width: 120px" class="inuptxt" type="text" />
            </span>
   <a href="#" class="easyui-linkbutton l-btn" iconcls="icon-search" onclick="tBMineOrgListsearch()">
    查询
   </a>
   <%--<a href="#" class="easyui-linkbutton l-btn" iconcls="icon-reload" onclick="searchReset('tBMineOrgList')">--%>
    <%--重置--%>
   <%--</a>--%>
  </form>
 </div>
</div>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tBMineOrgList"  title="矿井组织机构" actionUrl="tBMineOrgController.do?datagrid"  treegrid="true" idField="id" pagination="false" fit="true" onLoadSuccess="onLoadSuccess"  >
   <t:dgCol title="ID"  field="id" treefield="id" hidden="true"  queryMode="group"  width="120"></t:dgCol>

   <t:dgCol title="名称"  field="name" queryMode="group" treefield="text" width="120"></t:dgCol>
   <%--<t:dgCol title="编码"  field="code"  queryMode="group" treefield="fieldMap.code" hidden="true" width="120"></t:dgCol>--%>
   <t:dgCol title="类型"  field="type"  queryMode="group" dictionary="mineOrgType" treefield="fieldMap.type" width="120"></t:dgCol>

   <t:dgCol title="是否联网煤监"  field="isConnectToUpper" replace="是_1,否_0"  queryMode="group" treefield="fieldMap.isConnectToUpper" width="120"></t:dgCol>
   <t:dgCol title="风险辨识方法"  field="riskRecogType" dictionary="riskRecogType" queryMode="group" treefield="fieldMap.riskRecogType"  width="120"></t:dgCol>
   <t:dgCol title="远程连接类型"  field="remoteConnectType" dictionary="remoteConnectType"  queryMode="group"  treefield="fieldMap.remoteConnectType" width="120"></t:dgCol>
   <t:dgCol title="连接凭据"  field="remoteConnectCert"  queryMode="group" treefield="fieldMap.remoteConnectCert" width="120"></t:dgCol>
   <t:dgCol title="备注"  field="remark"  queryMode="group" treefield="fieldMap.remark" width="120"></t:dgCol>
   <t:dgCol title="是否通用版"  field="isCommonVersion" replace="是_1,否_0" queryMode="group" treefield="fieldMap.isCommonVersion" width="120"></t:dgCol>
   <t:dgCol title="分支地址"  field="deployBranch"  queryMode="group" treefield="fieldMap.deployBranch" width="120"></t:dgCol>
   <t:dgCol title="上次部署时间"  field="lastDeployTime" treefield="fieldMap.lastDeployTimeTemp" width="120"></t:dgCol>
   <t:dgCol title="上次部署人" field="lastDeployer"  treefield="fieldMap.lastDeployer" width="120"></t:dgCol>
   <%--<t:dgCol title="修改人"  field="updateName"  queryMode="group" treefield="fieldMap.updateName" width="120"></t:dgCol>--%>
   <%--<t:dgCol title="修改时间"  field="updateDate"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="group" treefield="fieldMap.updateDateTemp" width="120"></t:dgCol>--%>
   <%--<t:dgCol title="修改人id"  field="updateBy"  queryMode="group" treefield="fieldMap.updateBy" width="120"></t:dgCol>--%>
   <%--<t:dgCol title="创建人"  field="createName"  queryMode="group" treefield="fieldMap.createName" width="120"></t:dgCol>--%>
   <%--<t:dgCol title="创建时间"  field="createDate"  formatter="yyyy-MM-dd hh:mm:ss"  queryMode="group" treefield="fieldMap.createDateTemp" width="120"></t:dgCol>--%>
   <%--<t:dgCol title="创建人id"  field="createBy"  queryMode="group" treefield="fieldMap.createBy" width="120"></t:dgCol>--%>
   <%--<t:dgCol title="上级单位"  field="parentId"  queryMode="single"  width="120"></t:dgCol>--%>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
   <%--<t:dgDelOpt title="删除" url="tBMineOrgController.do?doDel&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
   <t:dgToolBar title="录入" icon="icon-add" funname="addOrg"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tBMineOrgController.do?goUpdate" funname="update" width="800" height="600"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="tBMineOrgController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tBMineOrgController.do?goUpdate" funname="detail" width="800" height="600"></t:dgToolBar>
  </t:datagrid>
   <%--<div id="tBMineOrgListtb" style="padding: 3px; height: 25px">--%>
    <%--<div style="float: left;">--%>
     <%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-add" onclick="addOrg()">录入</a>--%>
     <%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-edit" onclick="update('编辑','tBMineOrgController.do?goUpdate','tBMineOrgList','800px','600px')">编辑</a>--%>
     <%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-search" onclick="detail('查看','tBMineOrgController.do?goUpdate','tBMineOrgList','800px','600px')">查看</a>--%>
     <%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-remove" onclick="deleteALLSelect('批量删除','tBMineOrgController.do?doBatchDel','tBMineOrgList')">批量删除</a>--%>
     <%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-put" onclick="ImportXls()"><t:mutiLang langKey="excelImport" langArg="common.department"/></a>--%>
     <%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-putout" onclick="ExportXls()"><t:mutiLang langKey="excelOutput" langArg="common.department"/></a>--%>
     <%--<a href="#" class="easyui-linkbutton" plain="true" icon="icon-putout" onclick="ExportXlsByT()"><t:mutiLang langKey="templateDownload" langArg="common.department"/></a>--%>
    <%--</div>--%>
   <%--</div>--%>
  </div>
 </div>
 <script src = "webpage/com/sdzk/buss/web/mineorg/tBMineOrgList.js"></script>
 <script type="text/javascript">
 $(document).ready(function(){
  $("#tBMineOrgListtb").prepend($("#searchBtnDiv").html());
  $("#searchBtnDiv").empty();
 });

function addOrg() {
 var id = "";
 var rowsData = $('#tBMineOrgList').datagrid('getSelections');
 if (rowsData.length == 1) {
  id = rowsData[0].id;
 }
 var url = "tBMineOrgController.do?goAdd&id=" + id;
 add('录入', url, "tBMineOrgList","800","600");

}

 //导入
function ImportXls() {
	openuploadwin('Excel导入', 'tBMineOrgController.do?upload', "tBMineOrgList");
}

//导出
function ExportXls() {
	JeecgExcelExport("tBMineOrgController.do?exportXls","tBMineOrgList");
}

//模板下载
function ExportXlsByT() {
	JeecgExcelExport("tBMineOrgController.do?exportXlsByT","tBMineOrgList");
}

 function onLoadSuccess(){
 //$('#tBMineOrgList').treegrid('expandAll');
 //$('#tBMineOrgList').treegrid('collapseAll');
 }
 function refreshNode(data){
  var nodeIdArr = data.obj;
  if(nodeIdArr.length==1){
   reloadTable();
  } else {
   if($("#tBMineOrgList").treegrid('find', nodeIdArr[1]).children!=undefined && $("#tBMineOrgList").treegrid('find', nodeIdArr[1]).children.length>0){
    $('#tBMineOrgList').treegrid('reload', nodeIdArr[1]);
   } else {
    $('#tBMineOrgList').treegrid('refresh', nodeIdArr[1]);
   }
  }
  setTimeout(function(){
   $('#tBMineOrgList').treegrid('select', nodeIdArr[0]);
  },300);

 }
 </script>