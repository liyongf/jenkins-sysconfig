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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tBMineDeployController.do?doAdd" >
		<input id="id" name="id" type="hidden" value="${tBMineDeployPage.id }"/>
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
						<span style="display:none" class="isShow">
							风险辨识方法 :<input id="riskRecogType" name="riskRecogType" type="hidden" style="width: 150px"/>
							<input id="riskRecogTypeTemp" type="text" style="width: 150px"/>
						</span>
						<span style="display:none" class="isShow">分支地址 :<input id="deployBranch" name="deployBranch" type="text" style="width: 150px"/></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							部署人员:
						</label>
					</td>
					<td class="value">
						<input id="deployer" name="deployer" type="text" style="width: 150px" class="inputxt" datatype="*" value="${deployer}"/>
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
							   value='<fmt:formatDate value='<%=new Date()%>' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>' />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">部署时间</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							本次部署分支:
						</label>
					</td>
					<td class="value">
						<input id="thisDeployBranch" name="thisDeployBranch" type="text" style="width: 150px" class="inputxt" datatype="*"/>
						<span class="Validform_checktip"></span>
						<label class="Validform_label" style="display: none;">本次部署分支</label>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							更新内容:
						</label>
					</td>
					<td class="value">
						<textarea id="deployReason" name="deployReason" type="text" style="width: 150px" class="inputxt"  ignore="ignore" ></textarea>
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
						<textarea id="remark" name="remark" type="text" style="width: 150px" class="inputxt"  ignore="ignore"></textarea>
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
                 var mineOrgId = node.id;
                 if(mineOrgId==null||mineOrgId==''){
                     tip("请先选择下级煤矿!");
                     return;
                 }
                 $.ajax({
                     url: "tBMineDeployController.do?queryByMineOrgId",
                     type: 'post',
                     data: {
                         mineOrgId: mineOrgId,
                     },
                     success: function (data) {
                         var d = $.parseJSON(data);
                         if (d.success) {
                             var riskRecogType = d.obj.riskRecogType;
                             $("#riskRecogType").val(riskRecogType);
							 if(riskRecogType == '0'){
                                 $("#riskRecogTypeTemp").val("矩阵法");
							 }else if (riskRecogType == '1'){
                                 $("#riskRecogTypeTemp").val("LES法");
							 }

                             var deployBranch = d.obj.deployBranch;
                             $("#deployBranch").val(deployBranch);

                             $(".isShow").show();
                         }else {
                             tip(d.msg);
                         }
                     }
                 });
			 }
		 });
		 if("${isAdmin}"!="true"){
			 $("#deployer").attr("readonly","readonly");
		 }
	 });

 </script>
