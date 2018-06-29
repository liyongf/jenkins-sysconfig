<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>矿井APK配置</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  //编写自定义JS代码

  function uploadFile(data){
	  if($(".uploadify-queue-item").length>0){
		  $("#bussId").val(data.attributes.id);
		  upload();
	  }else{
		  frameElement.api.opener.reloadTable();
		  frameElement.api.close();
	  }
  }
  </script>
 </head>
 <body>
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" callback="@Override uploadFile" action="tBMineApkController.do;jsessionid=${pageContext.session.id}?doAdd" >
					<input id="id" name="id" type="hidden" value="${tBMineApkPage.id }"/>
	  <input id="mineId" name="mineId" type="hidden" value="${tBMineApkPage.mineId }"/>
		<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否静默下载:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="isSilent" type="list" extendJson="{\"datatype\":\"*\"}"
									  typeGroupCode="yesOrNo" defaultVal="0" hasLabel="false"></t:dictSelect>
					     	 <%--<input id="isSilent" name="isSilent" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否静默下载</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							是否强制安装:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="isForce" type="list" extendJson="{\"datatype\":\"*\"}"
									  typeGroupCode="yesOrNo" defaultVal="0" hasLabel="false"></t:dictSelect>
					     	 <%--<input id="isForce" name="isForce" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否强制安装</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							是否下载完成后自动安装:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="isAutoInstall" type="list" extendJson="{\"datatype\":\"*\"}"
									  typeGroupCode="yesOrNo" defaultVal="0" hasLabel="false"></t:dictSelect>
					     	 <%--<input id="isAutoInstall" name="isAutoInstall" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否下载完成后自动安装</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							是否可忽略该版本:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="isIgnorable" type="list" extendJson="{\"datatype\":\"*\"}"
									  typeGroupCode="yesOrNo" defaultVal="0" hasLabel="false"></t:dictSelect>
					     	 <%--<input id="isIgnorable" name="isIgnorable" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否可忽略该版本</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							版本号编码:
						</label>
					</td>
					<td class="value">
					        <input id="versionCode" name="versionCode" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">版本号编码</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							版本号名称:
						</label>
					</td>
					<td class="value">
					     	 <input id="versionName" name="versionName" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">版本号名称</label>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							本版更新内容:
						</label>
					</td>
					<td class="value" colspan="3">
							<textarea id="updateContent" name="updateContent" type="text" style="width: 250px" ></textarea>
					     	<%--<input id="updateContent" name="updateContent" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />--%>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">本版更新内容</label>
						</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							apk存放地址:
						</label>
					</td>
					<td class="value" colspan="3">
<%--							<textarea id="url" name="url" type="text" style="width: 150px" ></textarea>
					     	 &lt;%&ndash;<input id="url" name="url" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />&ndash;%&gt;
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">apk存放地址</label>--%>
							<input type="hidden" id="bussId" name="bussId" />
								<t:upload name="fiels" buttonText="上传apk" uploader="tBMineApkController.do;jsessionid=${pageContext.session.id}?doAddApk&typecode=${typecode}" multi="true" extend="*.apk" id="file_upload" formData="bussId,jsessionid"></t:upload>
							<div id="filediv" style="height: 50px">

							</div>
						</td>
					</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							apk的md5:
						</label>
					</td>
					<td class="value">
					     	 <input id="md5" name="md5" type="text" style="width: 150px" class="inputxt"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">apk的md5</label>
						</td>
					<td align="right">
						<label class="Validform_label">
							apk文件大小(MB):
						</label>
					</td>
					<td class="value">
					     	 <input id="size" name="size" type="text" style="width: 150px" class="inputxt"  datatype="n"  ignore="ignore" />
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">apk文件大小</label>
						</td>
					</tr>
					<tr>
						<td align="right">
							<label class="Validform_label">
								是否设置为当前版本:
							</label>
						</td>
						<td class="value" colspan="3">
							<t:dictSelect field="isCurrentVersion" type="list" extendJson="{\"datatype\":\"*\"}"
										  typeGroupCode="yesOrNo" defaultVal="1" hasLabel="false"></t:dictSelect>
							<span class="Validform_checktip"></span>
							<label class="Validform_label" style="display: none;">是否设置为当前版本</label>
						</td>
					</tr>
			</table>
		</t:formvalid>
 </body>
  <script src = "webpage/com/sdzk/buss/web/mineapk/tBMineApk.js"></script>
