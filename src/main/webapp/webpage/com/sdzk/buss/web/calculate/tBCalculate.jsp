<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>统计信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tBCalculateController.do?save">
			<input id="id" name="id" type="hidden" value="${tBCalculatePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							矿井编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="minecode" name="minecode" ignore="ignore"  value="${tBCalculatePage.minecode}" />
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
						<input class="inputxt" id="mineName" name="mineName" ignore="ignore"  value="${tBCalculatePage.mineName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							危险源总数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="numDangerSource" name="numDangerSource" ignore="ignore"  value="${tBCalculatePage.numDangerSource}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							隐患总数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="numHiddenDanger" name="numHiddenDanger" ignore="ignore"  value="${tBCalculatePage.numHiddenDanger}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							重大隐患总数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="numMajorHiddenDanger" name="numMajorHiddenDanger" ignore="ignore"  value="${tBCalculatePage.numMajorHiddenDanger}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							三违总数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="numThreeViolations" name="numThreeViolations" ignore="ignore"  value="${tBCalculatePage.numThreeViolations}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>