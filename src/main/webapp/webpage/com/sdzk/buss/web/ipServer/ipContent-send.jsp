<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="/context/mytags.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title>隐患整改</title>
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <script type="text/javascript" src="plug-in/magicsuggest/magicsuggest-min.js"></script>
    <link href="plug-in/magicsuggest/magicsuggest.css" rel="stylesheet">
    <script type="text/javascript" src="webpage/common/js/magicSuggestSelect.js"></script>
    <link href="plug-in/lhgDialog/skins/metrole.css" rel="stylesheet" id="lhgdialoglink">
    <script type="text/javascript">
        //编写自定义JS代码
        $(function () {

        })

        function saveNoteInfo(state) {
            $('#btn_sub').click();
        }


    </script>
</head>
<body>

<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table"
             action="serverWordController.do?saveReceviceMan">
    <input id="id" name="id" type="hidden" value="${ipServerConfig.id }">
    <%--<input id="checkStatus" name="checkStatus" type="hidden">--%>
    <table style="width: 100%;" cellpadding="0" cellspacing="1" class="formtable">
        <tr >
            <td align="right">
                <label class="Validform_label">
                    检查人:
                </label>
            </td>
            <td class="value">
                <input id="receviceMan" name="receviceMan" type="text" style="width: 150px" class="inputxt"
                       value="${ipServerConfig.ipWord}">
                <span class="Validform_checktip"></span>
                <label class="Validform_label" style="display: none;">检查人</label>
            </td>
        </tr>
    </table>
    <div class="ui_main">
        <div class="ui_buttons">
            <input type="button" value="提交" onclick="saveNoteInfo();" class="ui_state_highlight">
        </div>
    </div>
</t:formvalid>


</body>
<script>
    <%--$(function(){--%>
        <%--var modifyShift = '${shiftDefault}';--%>
        <%--if(modifyShift==""||modifyShift==null){--%>
            <%--$("#shiftDefault").attr("name","shiftDefault");--%>
            <%--$("#shiftDefault").removeAttr("dataType");--%>
            <%--$("select[name='modifyShiftTemp']").attr("name","modifyShift");--%>
        <%--}else{--%>
            <%--$("select[name='modifyShiftTemp']").attr("disabled","true");--%>
        <%--}--%>
    <%--});--%>
</script>
