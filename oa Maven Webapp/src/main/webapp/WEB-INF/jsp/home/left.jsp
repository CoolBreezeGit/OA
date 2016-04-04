<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>left</title>

<script language="JavaScript" src="script/jquery.js"></script>
<script language="JavaScript" src="script/menu.js"></script>
<link type="text/css" rel="stylesheet" href="style/blue/menu.css" />

</head>
<body style="margin: 0">
	<div id="Menu">
		<ul id="MenuUl">

			<li class="level1">
				<div onClick="menuClick(this);" class="level1Style" style="cursor:pointer">
					<img src="style/images/MenuIcon/FUNC20057.gif" class="Icon" />
					审批流转
				</div>
				<ul style="display: none;" class="MenuLevel2">
					<li class="level2">
						<div class="level2Style">
							<img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a
								target="right" href="processDefAction_list.action">审批流程管理</a>
						</div>
					</li>
					<li class="level2">
						<div class="level2Style">
							<img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a
								target="right" href="processTemplateAction_list.action">表单模板管理</a>
						</div>
					</li>
					<li class="level2">
						<div class="level2Style">
							<img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a
								target="right" href="jobFlowAction_applicationUI1.action">起草申请</a>
						</div>
					</li>
					<li class="level2">
						<div class="level2Style">
							<img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a
								target="right" href="jobFlowAction_queryMyTask.action"> 待我审批</a>
						</div>
					</li>
					<li class="level2">
						<div class="level2Style">
							<img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a
								target="right" href="jobFlowAction_queryMyApp.action">我的申请查询</a>
						</div>
					</li>
				</ul>
			</li>

			<li class="level1">
				<div onClick="menuClick(this);" class="level1Style" style="cursor:pointer">
					<img src="style/images/MenuIcon/FUNC20082.gif" class="Icon" />
					系统管理
				</div>
				<ul style="display: none;" class="MenuLevel2">
					<li class="level2">
						<div class="level2Style">
							<img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a
								target="right" href="roleAction_list.action"> 岗位管理</a>
						</div>
					</li>
					<li class="level2">
						<div class="level2Style">
							<img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a
								target="right" href="departmentAction_list.action"> 部门管理</a>
						</div>
					</li>
					<li class="level2">
						<div class="level2Style">
							<img src="style/images/MenuIcon/menu_arrow_single.gif" /> <a
								target="right" href="userAction_list.action"> 用户管理</a>
						</div>
					</li>

				</ul>
			</li>
		</ul>
	</div>
</body>
</html>