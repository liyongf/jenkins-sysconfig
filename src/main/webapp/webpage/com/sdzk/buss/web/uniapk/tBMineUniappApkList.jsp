<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<c:if test="${empty mineId}">
 <t:base type="jquery,easyui,tools,DatePicker"></t:base>
</c:if>
<div class="easyui-layout" fit="true">
 <div region="center" style="padding:0px;border:0px">
  <t:datagrid name="tBMineApkList" checkbox="true" pagination="true" fitColumns="true" title="版本配置" actionUrl="tBMineUniappApkController.do?datagrid&mineId=${mineId}" idField="id" fit="true" queryMode="group">
   <t:dgCol title="ID"  field="id"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="矿井ID"  field="mineId"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <c:if test="${empty mineId}">
    <t:dgCol title="矿井名称"  field="mineName"  hidden="false" query="false" width="120"></t:dgCol>
   </c:if>
   <t:dgCol title="是否强制安装"  field="isForce"  queryMode="group" dictionary="yesOrNo"  width="120"></t:dgCol>
   <t:dgCol title="是否下载完成后自动安装"  field="isAutoInstall" dictionary="yesOrNo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否可忽略该版本"  field="isIgnorable" dictionary="yesOrNo"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="版本号编码"  field="versionCode"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="版本号名称"  field="versionName"  query="true"  width="120"></t:dgCol>
   <t:dgCol title="本版更新内容"  field="updateContent"  query="true"  width="120"></t:dgCol>
   <%--<t:dgCol title="apk存放地址"  field="url"  queryMode="group"  width="120"></t:dgCol>--%>
   <t:dgCol title="md5"  field="md5"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="文件大小(MB)"  field="size"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="是否当前版本"  field="isCurrentVersion"  hidden="false"  queryMode="group" dictionary="yesOrNo" width="120"></t:dgCol>
   <t:dgCol title="修改人"  field="updateName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改时间"  field="updateDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="修改人id"  field="updateBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人"  field="createName"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建时间"  field="createDate"  formatter="yyyy-MM-dd"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="创建人id"  field="createBy"  hidden="true"  queryMode="group"  width="120"></t:dgCol>
   <t:dgCol title="文件链接"  field="documentId"  hidden="false"  queryMode="group"  width="120" formatterjs="downloadLink"></t:dgCol>
   <%--<t:dgCol title="操作" field="opt" width="100"></t:dgCol>--%>
   <%--<t:dgDefOpt title="下载" url="commonController.do?viewFile&fileid={documentId}&subclassname={subclassname}" urlclass="ace_button"  urlfont="fa-trash-o"/>--%>
   <t:dgToolBar title="上传版本" icon="icon-add" url="tBMineUniappApkController.do?goAdd&mineId=${mineId}" funname="add" height="500" width="800"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="tBMineUniappApkController.do?goUpdate" funname="update" height="500" width="800"></t:dgToolBar>
   <t:dgToolBar title="批量删除"  icon="icon-remove" url="tBMineUniappApkController.do?doBatchDel" funname="deleteALLSelect"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="tBMineUniappApkController.do?goUpdate" funname="detail"  height="500" width="800"></t:dgToolBar>
   <%--<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="导出" icon="icon-putout" funname="ExportXls"></t:dgToolBar>--%>
   <%--<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT"></t:dgToolBar>--%>
  </t:datagrid>
 </div>
</div>
<script src = "webpage/com/sdzk/buss/web/mineapk/tBMineApkList.js"></script>
<script src = "plug-in/layer/layer.js"></script>
<script type="text/javascript">
 $(document).ready(function(){

 });

 function downloadLink(value){
  return "<a href='commonController.do?viewFile&fileid="+value+"'>apk下载</a>";
 }

 //导入
 function ImportXls() {
  openuploadwin('Excel导入', 'tBMineApkController.do?upload', "tBMineApkList");
 }

 //导出
 function ExportXls() {
  JeecgExcelExport("tBMineApkController.do?exportXls","tBMineApkList");
 }

 //模板下载
 function ExportXlsByT() {
  JeecgExcelExport("tBMineApkController.do?exportXlsByT","tBMineApkList");
 }

 function showTip(message){
  tip(message);
 }
</script>
