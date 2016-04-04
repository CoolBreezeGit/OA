<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<title>审批流程列表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script language="javascript"
	src="${pageContext.request.contextPath}/script/jquery.js"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/pageCommon.js"
	charset="utf-8"></script>
<script language="javascript"
	src="${pageContext.request.contextPath}/script/PageUtils.js"
	charset="utf-8"></script>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/style/blue/pageCommon.css" />
<script type="text/javascript">
	function showProcessImage(processDefId) {
	
		processDefId = encodeURI(processDefId);

		processDefId = encodeURI(processDefId);

		var url = "${pageContext.request.contextPath}/processDefAction_showProcessImg.action?id="
				+ processDefId + "&t=" + new Date(); //防止缓存
		// myOpenWindow(url, 600, 500, "showProcessImage", true);
		window.open(url, "流程图", "height=450,width=650,top=80,left=400");
	}
</script>
</head>
<body>

	<div id="Title_bar">
		<div id="Title_bar_Head">
			<div id="Title_Head"></div>
			<div id="Title">
				<!--页面标题-->
				<img border="0" width="13" height="13"
					src="${pageContext.request.contextPath}/style/images/title_arrow.gif" />
				审批流程管理
			</div>
			<div id="Title_End"></div>
		</div>
	</div>

	<div id="MainArea">
		<table cellspacing="0" cellpadding="0" class="TableStyle">

			<!-- 表头-->
			<thead>
				<tr align=center valign=middle id=TableTitle>
					<td width="200px">流程名称</td>
					<td width="80px">最新版本</td>
					<td width="300px">说明</td>
					<td>相关操作</td>
					<td>aaa<s:property value="@com.coolbreeze.oa.tool.TestOGNLStatic@test()"/> </td>
				</tr>
			</thead>

			<!--显示数据列表-->
			<tbody id="TableData" class="dataContainer">
				<s:iterator value="#processDefList">
					<tr class="TableDetail1 template">
						<td>${name}&nbsp;</td>
						<td align="CENTER">${version}&nbsp;</td>
						<td>${description}&nbsp;</td>
						<td><s:a action="processDefAction_delete"
								onclick="return delConfirm()">
								<!-- 为什么为空？？？？？？struts.xml已经配置好，？？？？？ -->
								<s:param name="key"
									value="@java.net.URLEncoder@encode(key,'utf8')"></s:param>
						删除
					</s:a> <a href="javascript:showProcessImage('${id}')">查看流程图</a></td>
					</tr>
				</s:iterator>

			</tbody>
		</table>

		<!-- 其他功能超链接 -->
		<div id="TableTail">
			<div id="TableTail_inside">
				<table border="0" cellspacing="0" cellpadding="10" align="left">
					<tr>
						<td><div class="FuncBtn">
								<div class=FuncBtnHead></div>
								<div class=FuncBtnMemo>
									<s:a action="processDefAction_deployUI">部署流程定义文档</s:a>
								</div>
								<div class=FuncBtnTail></div>
							</div></td>
					</tr>
				</table>
			</div>
		</div>
	</div>

	<div class="Description">
		说明：<br /> 1，列表显示的是所有流程定义（不同名称）的最新版本。<br />
		2，删除流程定义时，此名称的所有版本的流程定义都会被删除。<br />
	</div>

</body>
</html>




