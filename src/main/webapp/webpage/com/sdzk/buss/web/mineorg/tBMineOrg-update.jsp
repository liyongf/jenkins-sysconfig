<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>矿井组织机构</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tBMineOrgController.do?doUpdate" callback="refreshNode" refresh="false" >
	<input id="id" name="id" type="hidden" value="${tBMineOrgPage.id }"/>
	<input id="updateName" name="updateName" type="hidden" value="${tBMineOrgPage.updateName }"/>
	<input id="updateDate" name="updateDate" type="hidden" value="${tBMineOrgPage.updateDate }"/>
	<input id="updateBy" name="updateBy" type="hidden" value="${tBMineOrgPage.updateBy }"/>
	<input id="createName" name="createName" type="hidden" value="${tBMineOrgPage.createName }"/>
	<input id="createDate" name="createDate" type="hidden" value="${tBMineOrgPage.createDate }"/>
	<input id="createBy" name="createBy" type="hidden" value="${tBMineOrgPage.createBy }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					名称:
				</label>
			</td>
			<td class="value">
				<input id="name" name="name" type="text" style="width: 150px" class="inputxt" datatype="*" value="${tBMineOrgPage.name}" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">名称</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					机构类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect field="type" type="list" extendJson="{\"datatype\":\"*\"}" defaultVal="${tBMineOrgPage.type}"
							  typeGroupCode="mineOrgType"  hasLabel="false"  title="机构类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">类型</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					上级单位:
				</label>
			</td>
			<td class="value" >
				<input id="cc" name="cc" type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${tBMineOrgPage.parentOrg.name}"  />
				<input id="parentOrg" name="parentOrg.id" type="hidden" value="${tBMineOrgPage.parentOrg.id}"  />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">上级单位</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					是否联网煤监:
				</label>
			</td>
			<td class="value">
				<label>
					<input type="radio" name="isConnectToUpper" id="isConnectToUpper_Y" value="1" ${tBMineOrgPage.isConnectToUpper eq "1"?"checked='checked'":""}>是
				</label>
				<label>
					<input type="radio" name="isConnectToUpper" id="isConnectToUpper_N" value="0" ${tBMineOrgPage.isConnectToUpper eq "0"?"checked='checked'":""}>否
				</label>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否联网煤监</label>
			</td>

		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					风险辨识方法:
				</label>
			</td>
			<td class="value">
				<t:dictSelect field="riskRecogType" type="list" extendJson="{\"datatype\":\"*\"}" defaultVal="${tBMineOrgPage.riskRecogType}"
							  typeGroupCode="riskRecogType"  hasLabel="false"  title="风险辨识方法"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">风险辨识方法</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					远程连接类型:
				</label>
			</td>
			<td class="value">
				<t:dictSelect field="remoteConnectType" type="list" extendJson="{\"datatype\":\"*\"}" defaultVal="${tBMineOrgPage.remoteConnectType}"
							  typeGroupCode="remoteConnectType"  hasLabel="false"  title="远程连接类型"></t:dictSelect>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">远程连接类型</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					连接凭据:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea id="remoteConnectCert" name="remoteConnectCert" type="text" style="width: 150px" class="inputxt"  ignore="ignore" >${tBMineOrgPage.remoteConnectCert}</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">连接凭据</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore" >${tBMineOrgPage.remark}</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					是否通用版:
				</label>
			</td>
			<td class="value" colspan="3">
				<label>
					<input type="radio" name="isCommonVersion" id="isCommonVersion_Y" value="1" ${tBMineOrgPage.isCommonVersion eq "1"?"checked='checked'":""}>是
				</label>
				<label>
					<input type="radio" name="isCommonVersion" id="isCommonVersion_N" value="0" ${tBMineOrgPage.isCommonVersion eq "0"?"checked='checked'":""}>否
				</label>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">是否通用版</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					分支地址:
				</label>
			</td>
			<td class="value" colspan="3">
				<textarea id="deployBranch" name="deployBranch" type="text" style="width: 150px" class="inputxt"  ignore="ignore" >${tBMineOrgPage.deployBranch}</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">分支地址</label>
			</td>
		</tr>

		<c:if test="${load eq 'detail'}">
		<tr>
			<td align="right">
				<label class="Validform_label">
					上次部署时间:
				</label>
			</td>
			<td class="value">
				<input id="lastDeployTime" name="lastDeployTime" type="text" style="width: 150px" datatype="*" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					   value="<fmt:formatDate value='${tBMineOrgPage.lastDeployTime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">上次部署时间</label>
			</td>
			<td align="right">
				<label class="Validform_label">
					上次部署人员:
				</label>
			</td>
			<td class="value">
				<input id="lastDeployer" name="lastDeployer" type="text" style="width: 150px" class="inputxt"  ignore="ignore" value="${tBMineOrgPage.lastDeployer}" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">名称</label>
			</td>
		</tr>
		</c:if>
		<tr>
			<td align="right">
				<label class="Validform_label">
					app关联标识:
				</label>
			</td>
			<td class="value" colspan="3">
				<input id="appCode" name="appCode" type="text" style="width: 150px" class="inputxt" value="${tBMineOrgPage.appCode}" />
					<%--<textarea id="appCode" name="appCode" type="text" style="width: 150px" ></textarea>--%>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">app关联标识</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script src = "webpage/com/sdzk/buss/web/mineorg/tBMineOrg.js"></script>
<script language="javascript">
	$(function() {
		$('#cc').combotree({
			url : 'tBMineOrgController.do?getMineOrgTree&selfId=${tBMineOrgPage.id}',
			panelHeight: 200,
			width: 157,
			onClick : function(node) {
				$('#parentOrg').val(node.id);
			}
		});

//		if($("#id").val()) {
//			$('#parentOrg').combotree('disable');
//		}
		<%--if('${empty tBMineOrgPage.parentOrg.id}' == 'false') { // 设置新增页面时的父级--%>
			<%--$('#parentOrg').combotree('setValue', '${tBMineOrgPage.parentOrg.id}');--%>
		<%--}--%>
	});

</script>