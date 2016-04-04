<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<html>
<head>
    <title>我的申请查询</title>
	<%@ include file="/WEB-INF/jsp/public/common.jspf"%>
</head>
<body>

<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 我的申请查询
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="0" class="TableStyle">
        <!-- 表头-->
        <thead>
            <tr align="CENTER" valign="MIDDLE" id="TableTitle">
				<td width="250px">标题</td>
				<td width="115px">申请人</td>
				<td width="115px">申请日期</td>
				<td width="115px">当前状态</td>
				<td>相关操作</td>
			</tr>
		</thead>	

		<!--显示数据列表：正在审批或审批完成的表单显示示例-->
        <tbody id="TableData" class="dataContainer" datakey="formList">
			<!-- 正在审批或审批完成的表单显示示例 -->
			<s:iterator value="#applications">
			<tr class="TableDetail1 template">
				<td><a href="${pageContext.request.contextPath}/Flow_Formflow/showForm.html">${name}</a></td>
				<td>${applicant.name}&nbsp;</td>
				<td><s:date name="applyTime" format="yyyy-MM-dd HH:mm:ss"/>&nbsp;</td>
				<td>${status}&nbsp;</td>
				<td><s:a action="processTemplateAction_download?id=%{processTemplate.id}">查看申请信息</s:a>
					<s:a action="jobFlowAction_queryProcessRecord?applicationId=%{id}">查看流转记录</s:a>
				</td>
			</tr>
			</s:iterator>
        </tbody>
    </table>
    
    <!-- 其他功能超链接 -->
    <div id="TableTail"><div id="TableTail_inside"></div></div>
</div>


<div class="Description">
	说明：<br />
	<!--
	1，对于退回的表单，可以执行“修改后再次提交”与“删除”的操作。<br />&nbsp;&nbsp;
	   其他状态表单（正在审批的或是审批完成的）则没有这两个操作。<br />
	   2，删除退回的申请文档后，此文档相关的审批信息也要同时删除，对应的流程也终止了。<br />
	   -->
</div>

</body>
</html>
