<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.store.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
	<head></head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>会员修改信息</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>

<style>
  body{
   margin-top:20px;
   margin:0 auto;
 }
 .carousel-inner .item img{
	 width:100%;
	 height:300px;
 }
 .container .row div{ 
	 /* position:relative;
	 float:left; */
 }
 
font {
    color: #3164af;
    font-size: 18px;
    font-weight: normal;
    padding: 0 10px;
}
 </style>
</head>
<body>


<%@ include file="/jsp/header.jsp" %>

<%
	User user = (User)request.getSession().getAttribute("loginUser");
	String nickName = user.getName();
	String email = user.getEmail();
	Date birth = user.getBirthday();
	SimpleDateFormat sdf1 =new SimpleDateFormat("yyyy-MM-dd");
	String birthString = sdf1.format(birth);
	//System.out.println(birthString);
	String phone = user.getTelephone();
%>


<div class="container" style="width:100%;background:url('${pageContext.request.contextPath}/img/regist_bg.jpg');">
<div class="row"> 

	<div class="col-md-2"></div>
	
	


	<div class="col-md-8" style="background:#fff;padding:40px 80px;margin:30px;border:7px solid #ccc;">
		<font>个人信息修改</font>
		<form class="form-horizontal" style="margin-top:5px;" action="${pageContext.request.contextPath}/user/changeInfo" method="post">
			 <div class="form-group">
			    <label for="username" class="col-sm-2 control-label">昵称</label>
			    <div class="col-sm-6">
			      <input type="text" name="nickname" class="form-control" id="nickname" value="<%=nickName %>">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
			    <div class="col-sm-6">
			      <input type="email" name="email" class="form-control" id="inputEmail3" value="<%=email %>">
			    </div>
			  </div>
			  <div class="form-group opt">  
			  <label for="inlineRadio1" class="col-sm-2 control-label">性别</label>  
			  <div class="col-sm-6">
      <c:choose>
         <c:when test="${user.getSex().equals('女')}">
            <input type="radio" name="sex" value="男"> 男
            <input type="radio" name="sex" value="女" checked="checked"> 女
         </c:when>     
         <c:otherwise>
             <input type="radio" name="sex" value="男" checked="checked"> 男
            <input type="radio" name="sex" value="女" > 女
         </c:otherwise> 
      </c:choose>
<!-- 			    <label class="radio-inline"> -->
<!-- 			  <input type="radio" name="sex" id="inlineRadio1" value="男" checked="checked"> 男 -->
<!-- 			    </label> -->
<!-- 			<label class="radio-inline"> -->
<!-- 			  <input type="radio" name="sex" id="inlineRadio2" value="女"> 女 -->
<!-- 			</label> -->
			</div>
			  </div>		
			  <div class="form-group">
			    <label for="date" class="col-sm-2 control-label">出生日期</label>
			    <div class="col-sm-6">
			      <input type="date" class="form-control" name="birthday" value="<%=birthString %>">		      
			    </div>
			  </div>
			  
			  <div class="form-group">
			    <label for="date" class="col-sm-2 control-label">电话</label>
			    <div class="col-sm-6">
			      <input type="text" class="form-control"  name="telephone" value="<%=phone %>">		      
			    </div>
			  </div>
			  
			  
			 
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <input type="submit"  width="100" value="修改" name="submit" border="0"
				    style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
   				  <input name="Submit2" type="button" class="btn" onclick="javascript:history.back(-1);" value="返 回" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
				    height:35px;width:100px;color:white;">
			    </div>
			  </div>
			</form>
	</div>
	
	<div class="col-md-2"></div>
  
</div>
</div>

	  
	
	<%@ include file="/jsp/footer.jsp" %>

</body></html>




