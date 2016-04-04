<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>top</title>
<link type="text/css" rel="stylesheet" href="style/blue/top.css" />
</head>
<body class="PageBody" style="margin: 0">

	<div id="Head1">
		<div id="Logo">
			<a id="msgLink" href="javascript:void(0)"></a> <font color="#0000CC"
				style="color:#F1F9FE; font-size:28px; font-family:Arial Black, Arial">CoolBreeze
				OA</font>
			<!--<img border="0" src="style/blue/images/logo.png" />-->
		</div>

		<div id="Head1Right">
			<div id="Head1Right_UserName">
				<img border="0" width="13" height="14"
					src="style/images/top/user.gif" /> 您好，<b>${user.name}</b>
			</div>
			<div id="Head1Right_UserDept"></div>
			
			<div id="Head1Right_Time"></div>
		</div>

		<div id="Head1Right_SystemButton">
			<s:a target="_parent" action="userAction_logout"> <img
				width="78" height="20" alt="退出系统"
				src="style/blue/images/top/logout.gif" />
			</s:a>
	</div>
	</div>

</body>
</html>