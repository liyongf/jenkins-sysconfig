<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>矿井组织机构</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码
  function openParentNode(a,b,c){
	  alert("tBMineOrg-add.jsp openParentNode");
	  debugger;
  }
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tBMineOrgController.do?doAdd" tiptype="3" callback="refreshNode" refresh="false">
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							名称:
						</label>
					</td>
					<td class="value">
						<input id="name" name="name" type="text" style="width: 150px" class="inputxt"  datatype="*" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">名称</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							机构类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="type" type="list" extendJson="{\"datatype\":\"*\"}"
									  typeGroupCode="mineOrgType"  hasLabel="false"  title="机构类型"></t:dictSelect>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">机构类型</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							上级单位:
						</label>
					</td>
					<td class="value">
						<input id="parentOrg" name="parentOrg.id" type="text" style="width: 150px" class="inputxt"  />
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
							<input type="radio" name="isConnectToUpper" id="isConnectToUpper_Y" value="1" >是
						</label>
						<label>
							<input type="radio" name="isConnectToUpper" id="isConnectToUpper_N" value="0" checked="checked">否
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
						<t:dictSelect field="riskRecogType" type="list" extendJson="{\"datatype\":\"*\"}" defaultVal="0"
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
						<t:dictSelect field="remoteConnectType" type="list" extendJson="{\"datatype\":\"*\"}" defaultVal="teamviewer"
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
						<textarea id="remoteConnectCert" name="remoteConnectCert" type="text" style="width: 150px" ></textarea>
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
						 <textarea id="remark" name="remark" type="text" style="width: 150px"></textarea>
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
							<input type="radio" name="isCommonVersion" id="isCommonVersion_Y" value="1" checked="checked"  >是
						</label>
						<label>
							<input type="radio" name="isCommonVersion" id="isCommonVersion_N" value="0" >否
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
						<textarea id="deployBranch" name="deployBranch" type="text" style="width: 150px" ></textarea>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">分支地址</label>
					</td>
				</tr>
<%--				<tr>
					<td align="right">
						<label class="Validform_label">
							上次部署时间:
						</label>
					</td>
					<td class="value">

						<input id="lastDeployTime" name="lastDeployTime" type="text" style="width: 150px" datatype="*" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"
							   value='<fmt:formatDate value='<%=new Date()%>' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>' />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">上次部署时间</label>
					</td>
					<td align="right">
						<label class="Validform_label">
							上次部署人员:
						</label>
					</td>
					<td class="value">
						<input id="lastDeployer" name="lastDeployer" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">上次部署人员</label>
					</td>
				</tr>--%>

			</table>
		</t:formvalid>
 </body>
<script src = "webpage/com/sdzk/buss/web/mineorg/tBMineOrg.js"></script>
<script language="javascript">
	$(function() {
		$('#parentOrg').combotree({
			url : 'tBMineOrgController.do?getMineOrgTree',
			width: 155,
			onSelect : function(node) {
			}
		});

		if('${empty pid}' == 'false') { // 设置新增页面时的父级
			$('#parentOrg').combotree('setValue', '${pid}');
		}
	});

</script>