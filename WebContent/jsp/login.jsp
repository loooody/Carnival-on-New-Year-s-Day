<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<title>login</title>
<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<link href="css/style2.css" rel='stylesheet' type='text/css'/>
	<script type="text/javascript">
	function checklogin(){
	var result = document.getElementById("userID").value;
   var password = document.getElementById("passwordID").value;
   if(result == ""  ){
     alert("用户名不能为空");
     return false;
   }
   if(password == ""  ){
    alert("密码不能为空");
     return false;
   }else{
   return true;
   }
}
	</script>
</head>
<body>
	<script> $(document).ready(function(c) {
	$('.close').on('click', function(c){
		$('.login-form').fadeOut('slow', function(c){
	  		$('.login-form').remove();
		});
	});	  
});
</script>
  <h1>SIGN UP</h1>
  <div class="login-form" >
	<div class="close"> </div>
		<div class="head-info">
			<label class="lbl-1"> </label>
			<label class="lbl-2"> </label>
			<label class="lbl-3"> </label>
		</div>
			<div class="clear"> </div>
	<div class="avtar">
		<img src="img/avtar.png" />
	</div>
	<div  style="color:#F00">${msg}</div>
  <form action="${pageContext.request.contextPath}/user/userLogin" method="post">
    <p><input type="text" name="username" id="username" value="username" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = 'username';}"></p>
    <p><input type="password" name="password" id="inputPassword3" placeholder="password"></p>
    
    <div class="form-group">
	  <a href="jsp/register.jsp">立即注册</a> 
	</div>		
    <div class="signin">
	  <input id="login_butt" type="submit" value="Login" name="submit">
	  
	</div>	 
  </form>
</body>
</html>