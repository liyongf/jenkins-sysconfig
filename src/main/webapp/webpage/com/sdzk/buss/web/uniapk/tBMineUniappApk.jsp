<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>uniapp 版本控制</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="tBMineUniappApkController.do?save">
			<input id="id" name="id" type="hidden" value="${tBMineUniappApkPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否静默下载  0：否  1：是:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isSilent" name="isSilent" ignore="ignore"  value="${tBMineUniappApkPage.isSilent}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否强制安装  0：否  1：是:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isForce" name="isForce" ignore="ignore"  value="${tBMineUniappApkPage.isForce}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否下载完成后自动安装   0：否  1：是:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isAutoInstall" name="isAutoInstall" ignore="ignore"  value="${tBMineUniappApkPage.isAutoInstall}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否可忽略该版本   0：否  1：是:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isIgnorable" name="isIgnorable" ignore="ignore"  value="${tBMineUniappApkPage.isIgnorable}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							版本号编码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="versionCode" name="versionCode" ignore="ignore"  value="${tBMineUniappApkPage.versionCode}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							版本号名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="versionName" name="versionName" ignore="ignore"  value="${tBMineUniappApkPage.versionName}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							本版更新内容:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="updateContent" name="updateContent" ignore="ignore"  value="${tBMineUniappApkPage.updateContent}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							apk存放地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="url" name="url" ignore="ignore"  value="${tBMineUniappApkPage.url}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							apk的md5:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="md5" name="md5" ignore="ignore"  value="${tBMineUniappApkPage.md5}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							apk文件大小:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="size" name="size" ignore="ignore"  value="${tBMineUniappApkPage.size}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否删除    0：否  1：是:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isDelete" name="isDelete" ignore="ignore"  value="${tBMineUniappApkPage.isDelete}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							矿井ID:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="mineId" name="mineId" ignore="ignore"  value="${tBMineUniappApkPage.mineId}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否当前版本 0：否  1：是:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="isCurrentVersion" name="isCurrentVersion" ignore="ignore"  value="${tBMineUniappApkPage.isCurrentVersion}" />
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>