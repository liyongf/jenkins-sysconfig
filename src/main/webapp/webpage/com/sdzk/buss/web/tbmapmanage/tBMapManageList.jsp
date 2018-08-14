<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div class="easyui-layout" fit="true">
    <div region="center" style="padding:0px;border:0px">
        <t:datagrid name="tBMapManageList" checkbox="true" pagination="true" fitColumns="true" title="矿图管理" actionUrl="tBMapManageController.do?datagrid" idField="id" fit="true" queryMode="group">
             <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
             <t:dgCol title="关系id"  field="sendId"  hidden="true"  queryMode="single"  width="120"></t:dgCol>
             <t:dgCol title="ip地址"  field="ipAddress"  hidden="true"  width="120"></t:dgCol>
             <t:dgCol title="端口号"  field="port"  hidden="true"  width="120"></t:dgCol>
             <t:dgCol title="项目名"  field="projectName" hidden="true"  width="120"></t:dgCol>
             <t:dgCol title="上传人"  field="uploadName"  queryMode="single"  width="120" align="center"></t:dgCol>
             <t:dgCol title="上传人登录名称"  field="uploadBy"  hidden="true" queryMode="single"  width="120"></t:dgCol>
             <t:dgCol title="上传日期"  field="uploadDate"  formatter="yyyy-MM-dd" align="center" queryMode="single"  width="120"></t:dgCol>
             <t:dgCol title="文件路径"  field="filePath"  queryMode="single"  width="120" align="center"></t:dgCol>
             <t:dgCol title="是否使用"  field="isUsed"  hidden="true" queryMode="single"  width="120"></t:dgCol>
             <t:dgCol title="是否删除"  field="isDelete" hidden="true" queryMode="single"  width="120"></t:dgCol>
             <t:dgCol title="上传类型"  field="uploadType"  hidden="true" queryMode="single"  width="120"></t:dgCol>
             <t:dgCol title="状态"  field="status" replace="未处理_0,处理中_1,处理完成_2" queryMode="single" align="center" width="120"></t:dgCol>
             <t:dgCol title="操作" field="opt" width="100" align="center"></t:dgCol>
             <t:dgFunOpt title="处理" urlclass="ace_button"  urlfont="fa-trash-o" funname="deal"/>
             <t:dgFunOpt title="闭环" urlclass="ace_button"  urlfont="fa-trash-o" funname="deal"/>
        </t:datagrid>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function(){

    });

    function deal() {
        var rows = $("#tBMapManageList").datagrid('getSelections');
        console.log(JSON.stringify(rows))
        var id = rows[0].id;
        var sendId = rows[0].sendId;
        var ipAddress = rows[0].ipAddress;
        var port = rows[0].port;
        var projectName = rows[0].projectName;
        if (rows.length < 1) {
            tip("请先选择一条数据");
        }else if (rows.length < 1) {
            tip("只能选择一条进行处理!");
        } else {
            $.dialog.confirm('确定处理该条数据吗?', function(r) {
                this.close();
                if (r) {
                    $.ajax({
                        type: 'POST',
                        url: 'tBMapManageController.do?doUpdate',
                        dataType:"json",
                        async:true,
                        cache: false,
                        data: {
                            id : id,
                            status: 1
                        },
                        success:function(data){
                            var msg = data.msg;
                            tip(msg);
                            reloadTable();
                        },
                        error:function(data){
                        }
                    });
                    setTimeout(function(){
                        $.ajax({
                            type: 'GET',
                            url: 'http://'+ipAddress+':'+port+projectName+'/tbMapManageController.do?changeStatus',
                            dataType:"json",
                            async:true,
                            cache: false,
                            data: {
                                id:sendId,
                                status: 1
                            },
                            success:function(data){
                                var msg = data.msg;
                                tip(msg);
                            },
                            error:function(data){
                            }
                        });
                    },500);
                }
            });
        }
    }


    function complete() {
        var rows = $("#tBMapManageList").datagrid('getSelections');
        var id = rows[0].id;
        var sendId = rows[0].sendId;
        var ipAddress = rows[0].ipAddress;
        var port = rows[0].port;
        var projectName = rows[0].projectName;
        if (rows.length < 1) {
            tip("请先选择一条数据");
        }else if (rows.length < 1) {
            tip("只能选择一条进行闭环!");
        } else {
            $.dialog.confirm('确定闭环该条数据吗?', function(r) {
                this.close();
                if (r) {
                    $.ajax({
                        type: 'POST',
                        url: 'tbMapManageController.do?doUpdate',
                        dataType:"json",
                        async:true,
                        cache: false,
                        data: {
                            id : id,
                            status : 2
                        },
                        success:function(data){
                            var msg = data.msg;
                            tip(msg);
                            reloadTable();
                        },
                        error:function(data){
                        }
                    });
                    setTimeout(function(){
                        $.ajax({
                            type: 'GET',
                            url: 'http://'+ipAddress+':'+port+projectName+'/tbMapManageController.do?changeStatus',
                            dataType:"json",
                            async:true,
                            cache: false,
                            data: {
                                id : sendId,
                                status : 2
                            },
                            success:function(data){
                                var msg = data.msg;
                                tip(msg);
                            },
                            error:function(data){
                            }
                        });
                    },500);
                }
            });
        }
    }
</script>