<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<script  type="text/javascript" src="plug-in/magicsuggest/magicsuggest-min.js"></script>
<link href="plug-in/magicsuggest/magicsuggest.css" rel="stylesheet">
<script type="text/javascript" src="webpage/common/js/magicSuggestSelect.js"></script>
<div id="tempSearchColums" style="display: none">
    <div name="searchColums" style="z-index: 9999">
        <span style="margin-top:5px;display:inline-block;font-size:0;">
            <span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 200px;text-align:right;">矿井信息：</span>
            <span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 190px;text-align:left;text-overflow:ellipsis;-o-text-overflow:ellipsis;">
                <div id="userSuggest" style="width: 150px;height: 15px"></div>
                <input id="belongmineinfo" name="belongmineinfo" type="hidden">
            </span>
              <span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 200px;text-align:right;">矿井信息：</span>
                <span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 190px;text-align:left;text-overflow:ellipsis;-o-text-overflow:ellipsis;">
                <input id="belongmineinfo1" name="belongmineinfo1" class="inputxt" type="text">
              </span>
        </span>
    </div>
</div>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="sysconfigList" checkbox="true"  fitColumns="true"  queryMode="group" title="矿井 配置信息" actionUrl="sysconfigController.do?datagrid" idField="id" fit="true">
            <t:dgCol title="编号" field="id" hidden="true"></t:dgCol>
            <t:dgCol title="矿井编码" field="belongmine"  query="true"  width="120" align="center" ></t:dgCol>
            <t:dgCol title="矿井名字" field="belongminename"   width="120" align="left"></t:dgCol>
            <t:dgCol title="矿井信息" field="belongmineinfo"   width="230" align="center"></t:dgCol>
            <t:dgCol title="矿井ip" field="ip"  width="100" query="true" align="center"></t:dgCol>
            <t:dgCol title="矿井port" field="port"   width="60" align="center"></t:dgCol>
            <t:dgCol title="是否部署" field="isdeploy"  hidden="true" width="60" align="center"></t:dgCol>
            <t:dgCol title="修改用户" field="updateusers"  hidden="true"  width="120" align="center"></t:dgCol>
            <t:dgCol title="dbconfig下载" field="down1" width="150" formatterjs="down1" align="center"></t:dgCol>
            <t:dgCol title="ehcache下载" field="down2" width="150" formatterjs="down2" align="center"></t:dgCol>
            <t:dgCol title="sysConfig下载" field="down3" width="160" formatterjs="down3" align="center"></t:dgCol>
            <t:dgCol title="废弃时间" field="updatedt"  formatter="yyyy-MM-dd"  width="120" align="center"></t:dgCol>
            <t:dgCol title="操作" field="opt" width="100"  align="center"></t:dgCol>
            <t:dgDelOpt title="废弃" url="sysconfigController.do?del&id={id}" urlclass="ace_button"  urlfont="fa-trash-o"/>
            <t:dgToolBar title="录入" icon="icon-add" url="sysconfigController.do?addorupdate" funname="add"></t:dgToolBar>
            <t:dgToolBar title="编辑" icon="icon-edit" url="sysconfigController.do?addorupdate" funname="update"></t:dgToolBar>
            <t:dgToolBar title="查看" icon="icon-search" url="sysconfigController.do?addorupdate" funname="detail"></t:dgToolBar>
            <t:dgToolBar title="上传" icon="icon-add" url="sysconfigController.do?goUpdate" funname="uploads" ></t:dgToolBar>
        </t:datagrid>
    </div>
</div>

<script type="text/javascript">
    var userSuggest;
    $(document).ready(function(){
        var datagrid = $("#sysconfigListtb");
        datagrid.find("form[id='sysconfigListForm']>span:last").after($("#tempSearchColums div[name='searchColums']").html());
        $("#tempSearchColums").empty();
        userSuggest= getBelongmineinfo($("#userSuggest"), $("#belongmineinfo"));

    });
    $("a[iconcls='icon-reload']").click(function(){
        var _body = window.parent;
        var iframe4 = _body.document.getElementsByName('iframe4')[0];
        iframe4.contentWindow.location.reload(true);
    });
    function down1(value,row,index){
        var name=row.belongminename;
        var isdeploy=row.isdeploy;
        if(isdeploy=="1"){
            return '<a href=http://47.92.93.226:8180/mineManage/sysconfigController.do?download&filename='+name+'_dbconfig>'+name+'_dbconfig</a>';
        }
    }
    function down2(value,row,index){
        var name=row.belongminename;
        var isdeploy=row.isdeploy;
        if(isdeploy=="1") {
            return '<a href=http://47.92.93.226:8180/mineManage/sysconfigController.do?download&filename=' + name + '_ehcache>' + name + '_ehcache</a>';
        }
    }
    function down3(value,row,index){
        var name=row.belongminename;
        var isdeploy=row.isdeploy;
        if(isdeploy=="1") {
            return '<a href=http://47.92.93.226:8180/mineManage/sysconfigController.do?download&filename=' + name + '_sysConfig>' + name + '_sysConfig</a>';
        }
    }
    function uploads(title,url,id) {
        var rowData = $('#' + id).datagrid('getSelections');
        if (!rowData) {
            tip('请选择编辑项目');
            return;
        }
        if (rowData.length != 1) {
            tip('请选择一个编辑项目');
            return;
        }
        url += '&id=' + rowData[0].id;
        $.dialog({
            content: "url:" + url,
            lock: true,
            title: "文件上传[" + rowData[0].belongminename + "-" + rowData[0].belongmineinfo + "]",
            opacity: 0.3,
            width: 900,
            height: 500,
            cache: false,
            ok: function () {
                iframe = this.iframe.contentWindow;
                $('#btn_sub', iframe.document).click();
                return false;
            },
            cancelVal: '关闭',
            cancel: true
        });
    }
</script>
