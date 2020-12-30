<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script  type="text/javascript" src="plug-in/magicsuggest/magicsuggest-min.js"></script>
<link href="plug-in/magicsuggest/magicsuggest.css" rel="stylesheet">
<script type="text/javascript" src="webpage/common/js/magicSuggestSelect.js"></script>

<div class="easyui-layout" fit="true">
	<div region="center" style="padding:0px;border:0px">
		<t:datagrid name="personalIpList" checkbox="true" pagination="true" fitColumns="true" title="三违信息" actionUrl="serverWordController.do?personalDatagrid" idField="id" fit="true" queryMode="group" sortName="createDate" sortOrder="desc">
			<t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group"  width="120" align="center"></t:dgCol>
			<t:dgCol title="公有ip"  field="publicIp" query="true"  queryMode="single" sortable="false" width="80" align="center"></t:dgCol>
			<t:dgCol title="私有ip"  field="privateIp" query="true"  queryMode="single" sortable="false" width="80" align="center"></t:dgCol>
			<t:dgCol title="密码"  field="ipWord" hidden="false"  queryMode="single" sortable="false" width="60" align="center"></t:dgCol>
			<t:dgCol title="密码接收人"  field="receviceMan" query="false" hidden="false"  queryMode="single" sortable="false" width="60" align="center"></t:dgCol>

			<t:dgToolBar title="录入" icon="icon-add" url="serverWordController.do?addIp" funname="add" operationCode="add"></t:dgToolBar>
			<t:dgToolBar title="下发" icon="icon-search" url="serverWordController.do?goSend" funname="goSend" operationCode="goSend" ></t:dgToolBar>
			<t:dgToolBar title="查看" icon="icon-search" url="serverWordController.do?goDetailList" funname="goDetailList" operationCode="detailList" ></t:dgToolBar>
			<t:dgToolBar title="导入" icon="icon-put" funname="ImportXls" operationCode="import"></t:dgToolBar>
			<t:dgToolBar title="模板下载" icon="icon-putout" funname="ExportXlsByT" operationCode="downloadTmpl"></t:dgToolBar>

		</t:datagrid>
	</div>
</div>
<script type="text/javascript">
    $(document).ready(function(){
        /* //初始化查询条件
         $("#tBThreeViolationsListForm>span:last").after($("#tempSearchColums div[name='searchColums']").html());

         var html = "<span style='display:-moz-inline-box;display:inline-block;'>";
         html += "<span style='vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 90px;text-align:right;text-overflow:ellipsis;-o-text-overflow:ellipsis; overflow: hidden;white-space:nowrap;' title='查询方式'>查询方式：</span>";
         html += "<select style='width:100px;' name='isMonthQuery' onchange='changeQueryType();'>";
         html += "<option value='1'>按月份</option><option value='0'>按起止时间</option></select></span>";

         $("#tBThreeViolationsListForm").prepend(html);
         //先隐藏按时间查询
         $("#tBThreeViolationsListForm").find("input[name='vioDate_begin']").parent().hide();
         $("#tBThreeViolationsListForm").find("input[name='queryMonth']").attr("class", "Wdate").attr("style", "height:30px;width:156px;").click(function () {
             WdatePicker({
                 dateFmt: 'yyyy-MM'
             });
         });
         $("#tBThreeViolationsListForm").find("input[name='vioDate_begin']").attr("class", "Wdate").attr("style", "height:30px;width:90px;").click(function () {
             WdatePicker({
                 dateFmt: 'yyyy-MM-dd'
             });
         });
         $("#tBThreeViolationsListForm").find("input[name='vioDate_end']").attr("class", "Wdate").attr("style", "height:30px;width:90px;").click(function () {
             WdatePicker({
                 dateFmt: 'yyyy-MM-dd'
             });
         });

         //初始化列表
         $("#tempSearchColums").empty();
         var checkOrgSelect = getDepartMagicSuggest($("#checkOrgSelect"), $("#checkOrgIds"));
         var dutyOrgSelect = getDepartMagicSuggest($("#dutyOrgSelect"), $("#dutyOrgIds"));
         var dutyManNameSelect = getUserMagicSuggestAllowFreeEntries($('#dutyManNameSelect'), $("#dutyManIds"));
         var checkManSelect = getUserMagicSuggestAllowFreeEntries($('#checkManSelect'), $("#checkManIds"));
         var addressSelect = getAddressMagicSuggest($('#addressSelect'), $("#tBAddressInfoEntityId"));

         //重置按钮时间绑定
         $("a[iconcls='icon-reload']").on("click", function(){
             checkOrgSelect.clear();
             dutyOrgSelect.clear();
             dutyManNameSelect.clear();
             checkManSelect.clear();
             addressSelect.clear();
         });*/
    });
    function goDetailList(){
        var rows = $("#ipServerConfigList").datagrid('getSelections');
        if(rows.length < 1 ){
            tip("请选择要编辑的隐患!!!");
        }else if(rows.length > 1){
            tip("请选择一条记录再编辑!!!");
        }else{
            createdetailwindow("查看","serverWordController.do?goDetailList&id="+rows[0].id,800,600);
        }
    }


    function goSend(){
        var rows = $("#ipServerConfigList").datagrid('getSelections');
        if(rows.length < 1 ){
            tip("请选择要下发的服务器!!!");
        }else if(rows.length > 1){
            tip("请选择一条服务器再下发!!!");
        }else if (null != rows[0].receviceMan && ""!=rows[0].receviceMan){
            tip("请先重置密码再下发!!!");
        }else {
            createdetailwindow("查看","serverWordController.do?goSend&id="+rows[0].id,500,500);
        }
    }

    //导入
    function ImportXls() {
        openuploadwin('服务器信息导入', 'serverWordController.do?upload', "tBThreeViolationsList");
    }

    //模板下载
    function ExportXlsByT() {
        JeecgExcelExport("serverWordController.do?exportXlsByT","ipServerConfigList");
    }

    function changeQueryType(){
        var type = $("select[name='isMonthQuery']").val();
        if(type=="1"){
            $("#tBThreeViolationsListForm").find("input[name='vioDate_begin']").parent().hide();
            $("#tBThreeViolationsListForm").find("input[name='queryMonth']").parent().show();
        }else{
            $("#tBThreeViolationsListForm").find("input[name='vioDate_begin']").parent().show();
            $("#tBThreeViolationsListForm").find("input[name='queryMonth']").parent().hide();
        }
    }


</script>