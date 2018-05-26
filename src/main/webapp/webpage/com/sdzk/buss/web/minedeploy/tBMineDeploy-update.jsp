<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>矿井部署</title>
	<t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<script type="text/javascript">
		//编写自定义JS代码
	</script>
</head>
<body>
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tBMineDeployController.do?doUpdate" >
	<input id="id" name="id" type="hidden" value="${tBMineDeployPage.id }"/>
	<input id="updateName" name="updateName" type="hidden" value="${tBMineDeployPage.updateName }"/>
	<input id="updateDate" name="updateDate" type="hidden" value="${tBMineDeployPage.updateDate }"/>
	<input id="updateBy" name="updateBy" type="hidden" value="${tBMineDeployPage.updateBy }"/>
	<input id="createName" name="createName" type="hidden" value="${tBMineDeployPage.createName }"/>
	<input id="createDate" name="createDate" type="hidden" value="${tBMineDeployPage.createDate }"/>
	<input id="createBy" name="createBy" type="hidden" value="${tBMineDeployPage.createBy }"/>
	<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
		<tr>
			<td align="right">
				<label class="Validform_label">
					部署煤矿:
				</label>
			</td>
			<td class="value">
				<input id="mineOrg" name="mineOrg.id" type="text" style="width: 150px" class="inputxt" />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">部署煤矿</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					部署人员:
				</label>
			</td>
			<td class="value">
				<input id="deployer" name="deployer" type="text" style="width: 150px" class="inputxt" datatype="*" value="${tBMineDeployPage.deployer}"/>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">部署人员名称</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					部署时间:
				</label>
			</td>
			<td class="value">
				<input id="deployDate" name="deployDate" type="text" style="width: 150px" datatype="*" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
					   value='<fmt:formatDate value='${tBMineDeployPage.deployDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>' />
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">部署时间</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					更新内容:
				</label>
			</td>
			<td class="value">
				<textarea id="deployReason" name="deployReason" type="text" style="width: 150px" class="inputxt"  ignore="ignore" >${tBMineDeployPage.deployReason}</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">更新内容</label>
			</td>
		</tr>
		<tr>
			<td align="right">
				<label class="Validform_label">
					备注:
				</label>
			</td>
			<td class="value">
				<textarea id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore">${tBMineDeployPage.remark}</textarea>
				<span class="Validform_checktip"></span>
				<label class="Validform_label" style="display: none;">备注</label>
			</td>
		</tr>
	</table>
</t:formvalid>
</body>
<script src = "webpage/com/sdzk/buss/web/minedeploy/tBMineDeploy.js"></script>
<script language="javascript">
	$(function() {
		$('#mineOrg').combotree({
			url : 'tBMineOrgController.do?getMineOrgTree',
			width: 155,
			onSelect : function(node) {
			}
		});
		if('${empty tBMineDeployPage.mineOrg.id}' == 'false') { // 设置新增页面时的父级
			$('#mineOrg').combotree('setValue', '${tBMineDeployPage.mineOrg.id}');
		}
	});

</script>
