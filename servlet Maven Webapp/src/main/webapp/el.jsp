<%@ page language="java" import="java.util.*"  pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib uri="http://testcomp.com/testcomp/core" prefix="MyEL" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>EL表达式测试页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
   <%
        String name="xlj";
        pageContext.setAttribute("name", name);
    %>
  <body>
  自定义EL测试：<br>
    ${MyEL:MyLowerToUpper(name)} <br><br>
<c:if test="${5>3}" var="string">
   c:if测试
</c:if>
<br><br>
c:out的测试：<br>
value为空，输出标签对中的内容：<br>
  <c:out value="${null}">使用的表达式结果为null，则输出该默认值</c:out><br/><br>
  value不为空，输出value中的内容：<br>
   <c:out value="${name}">使用的表达式结果为null，则输出该默认值</c:out><br/>
  </body>
</html>
