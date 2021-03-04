<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <title>矿井 配置信息</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<script  type="text/javascript" src="plug-in/magicsuggest/magicsuggest-min.js"></script>
<link href="plug-in/magicsuggest/magicsuggest.css" rel="stylesheet">
<script type="text/javascript" src="webpage/common/js/magicSuggestSelect.js"></script>
<script type="text/javascript">
    var vioUnitsSelect;
    var magicDisable = ${load eq 'detail'?'true':'false'};
    $(document).ready(function(){
       // var vioUnitsSelect = getProvinceValue($("#vioUnitsSelect"), $("#belongmineinfo"),"${sysconfigPage.belongmineinfo}",magicDisable);

         vioUnitsSelect = $('#vioUnitsSelect').magicSuggest({
            allowFreeEntries: false,
            data:'magicSelectController.do?getProvince',
            valueField:'province',
            value:[${temp}],
            placeholder:'输入或选择',
            maxSelection:10,
            selectFirst: true,
            matchField:['id'],
            highlight: false,
            displayField:'province'
        });
        $(vioUnitsSelect).on('selectionchange', function(e,m){
            var obj = vioUnitsSelect.getSelection();
            if(obj.length>0){
                $("#belongmineinfo").val(vioUnitsSelect.getValue());
            }else{
                $("#belongmineinfo").val("");
            }
        });
    });

    function username(){
        $.ajax({
            type:"POST",
            url:"sysconfigController.do?userinfo",
            dataType:"json",
            success:function(data){
                console.log(data);
                if(data.success){
                    $("#updateusers").val(data.msg);
                }
            },
            error:function(){
                tip("错误");
            }
        });
    }
</script>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="sysconfigController.do?save" beforeSubmit="username">
    <input id="id" name="id" type="hidden" value="${sysconfigPage.id }">
    <table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
        <tr>
            <td align="right">
                <label class="Validform_label">
                    矿井编码:
                </label>
            </td>
            <td class="value">
                <input class="inputxt" id="belongmine" name="belongmine" ignore="ignore"  value="${sysconfigPage.belongmine}" />
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    矿井名字:
                </label>
            </td>
            <td class="value">
                <input class="inputxt" id="belongminename" name="belongminename" ignore="ignore"  value="${sysconfigPage.belongminename}" />
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    矿井信息:
                </label>
            </td>
            <td class="value">
                <div id="vioUnitsSelect" style="width: 330px;height: 15px"></div>
                <input id="belongmineinfo" name="belongmineinfo" type="hidden" datatype="*" value='${sysconfigPage.belongmineinfo}'>
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">违章单位</label>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    矿井ip:
                </label>
            </td>
            <td class="value">
                <input class="inputxt" id="ip" name="ip" ignore="ignore"  value="${sysconfigPage.ip}" />
                <span class="Validform_checktip"></span>
            </td>
        </tr>
        <tr>
            <td align="right">
                <label class="Validform_label">
                    矿井port:
                </label>
            </td>
            <td class="value">
            </td>
        </tr>
            <%--<tr>
                <td align="right">
                    <label class="Validform_label">
                        是否部署:
                    </label>
                </td>
                <td class="value">
                    <input class="inputxt" id="isdeploy" name="isdeploy" ignore="ignore"  value="${sysconfigPage.isdeploy}" datatype="n" />
                    <span class="Validform_checktip"></span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <label class="Validform_label">
                        修改时间:
                    </label>
                </td>
                <td class="value">
                    <input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="updatedt" name="updatedt" ignore="ignore"    value="<fmt:formatDate value='${sysconfigPage.updatedt}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
                    <span class="Validform_checktip"></span>
                </td>
            </tr>
            <tr>
                <td align="right">
                    <label class="Validform_label">
                        修改用户:
                    </label>
                </td>
                <td class="value">
                    <input class="inputxt" id="updateusers" name="updateusers" ignore="ignore" />
                    <span class="Validform_checktip"></span>
                </td>
            </tr>--%>
    </table>
</t:formvalid>
</body>


