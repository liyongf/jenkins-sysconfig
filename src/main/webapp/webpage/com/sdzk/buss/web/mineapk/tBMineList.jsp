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
<div id="system_function_functionList" class="easyui-layout" fit="true">
  <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tBMineOrgList"  title="矿井组织机构" actionUrl="tBMineOrgController.do?datagrid"  treegrid="true" idField="id" pagination="false" fit="true" >
   <t:dgCol title="ID"  field="id" treefield="id" hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="名称"  field="name" queryMode="group" treefield="text" width="120"></t:dgCol>
   <t:dgCol title="类型"  field="type"  queryMode="group" dictionary="mineOrgType" treefield="fieldMap.type" width="120"></t:dgCol>
   <t:dgCol title="apk最新版本"  field="lastApkVersionName"  queryMode="group" treefield="fieldMap.lastApkVersionName" width="120"></t:dgCol>
      <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
      <t:dgFunOpt funname="setApk(id)" title="apk配置" urlclass="ace_button" urlfont="fa-cog"></t:dgFunOpt>
  </t:datagrid>
  </div>
 </div>
<div data-options="region:'east',
	title:'apk列表',
	collapsed:true,
	split:true,
	border:false,
	onExpand : function(){
		li_east = 1;
	},
	onCollapse : function() {
	    li_east = 0;
	}"
     style="width: 1200px; overflow: hidden;">
 <div class="easyui-panel" style="padding:0px;border:0px" fit="true" border="false" id="settingApkPanel"></div>
</div>
 <script src = "webpage/com/sdzk/buss/web/mineorg/tBMineOrgList.js"></script>
 <script type="text/javascript">

 $(document).ready(function(){
  $("#tBMineOrgListtb").prepend($("#searchBtnDiv").html());
  $("#searchBtnDiv").empty();
     var li_east = 0;
 });

  function setApk(mineId){
      debugger;
    if(li_east == 0){
     $('#system_function_functionList').layout('expand','east');
    }
    $('#settingApkPanel').panel("refresh", "tBMineApkController.do?list&mineId=" +mineId);
  }
 </script>