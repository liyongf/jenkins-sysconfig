<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>短信权限</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tBSmsMineController.do?save">
			<input id="id" name="id" type="hidden" value="${tBSmsMinePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							矿井编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="mineCode" name="mineCode" ignore="ignore"  value="${tBSmsMinePage.mineCode}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							矿井名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="mineName" name="mineName" ignore="ignore"  value="${tBSmsMinePage.mineName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							授权时间开始:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="beginMineDate" name="beginMineDate" ignore="ignore"    value="<fmt:formatDate value='${tBSmsMinePage.beginMineDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							授权时间结束:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="endMineDate" name="endMineDate" ignore="ignore"    value="<fmt:formatDate value='${tBSmsMinePage.endMineDate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>