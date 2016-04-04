<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>CoolBreeze OA</title>
    
  </head>

<!-- 此处应把body去掉！！frameset包含整个页面，不用body -->
    <frameset rows="55px,*,30px">
    	<frame name="top" scrolling="no" src="${pageContext.request.contextPath}/homeAction_top.action"></frame>
    	<frameset cols="200px,*">
    		<frame name="left" scrolling="no" src="${pageContext.request.contextPath}/homeAction_left.action"></frame>
    		<frame name="right" scrolling="auto"></frame>
    	</frameset>
    	<frame name="bottom" scrolling="no" src="${pageContext.request.contextPath}/homeAction_bottom.action"></frame>
    </frameset>
    
</html>
