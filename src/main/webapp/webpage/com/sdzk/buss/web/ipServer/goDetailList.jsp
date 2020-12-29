<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<div id="main_typegroup_list" class="easyui-layout" fit="true">
<div region="center" style="padding:1px;">
    <t:datagrid name="ipServerLogList" checkbox="false" fitColumns="false" title="密码变更信息记录" actionUrl="serverWordController.do?datagridList&serverId=${serverId}" idField="id" fit="true" queryMode="group">
       <t:dgCol title="主键"  field="id"  hidden="true"  queryMode="group" sortable="false" width="120"></t:dgCol>
       <t:dgCol title="私有ip"  field="privateIp" query="false"  queryMode="single" sortable="false" width="120" align="center"></t:dgCol>
       <t:dgCol title="密码"  field="passWord" hidden="false"  queryMode="single" sortable="false" width="100" align="center"></t:dgCol>
        <t:dgCol title="密码接收人"  field="receviceMan" hidden="false"  queryMode="single" sortable="false" width="90" align="center"></t:dgCol>
       <t:dgCol title="创建时间" field="createDate" formatter="yyyy-MM-dd hh:mm:ss" hidden="false" sortable="false" width="120" align="center"></t:dgCol>
    </t:datagrid>
</div>
</div>
 <script type="text/javascript">

     $(document).ready(function(){
     /*    var li_east = 0;
            //给时间控件加上样式
                $("#tBThreeViolationsListtb").find("input[name='vioDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){
                    WdatePicker({
                        dateFmt:'yyyy-MM'
                    });
                });
         $("#tBThreeViolationsListtb").find("input[name='vioDate_begin']").next().css("display","none");
         $("#tBThreeViolationsListtb").find("input[name='vioDate_end']").css("display","none");
                $("#tBThreeViolationsListtb").find("input[name='createDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
                $("#tBThreeViolationsListtb").find("input[name='createDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
                $("#tBThreeViolationsListtb").find("input[name='updateDate_begin']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
                $("#tBThreeViolationsListtb").find("input[name='updateDate_end']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy-MM-dd'});});
   */
     });


 </script>