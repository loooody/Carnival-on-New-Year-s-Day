<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">

<title>login</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<link href="css/style2.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css" type="text/css" />
</head>

<body>
    <script type="text/javascript">
  		function check(form){
                    //form属性的elements的首字母e要小写
				if(form.elements[0].value == ""||form.elements[1].value == ""){ 
					alert("用户名和密码不能为空");
					form.elements[0].focus();
					form.elements[1].focus();
					return false;
				}else if(form.elements[1].value!=form.elements[2].value){
					alert("两次密码输入不同");
					form.elements[1].focus();
					form.elements[2].focus();
					return false;
				}else{
					alert("注册成功!");
					return true;
				}
  	  	}

</script>
	<script>
		$(document).ready(function(c) {
			$('.close').on('click', function(c) {
				$('.login-form').fadeOut('slow', function(c) {
					$('.login-form').remove();
				});
			});
		});
	</script>
	<h1>SIGN UP</h1>
	<div class="login-form">

		<div class="head-info">
			<label class="lbl-1"> </label> <label class="lbl-2"> </label> <label
				class="lbl-3"> </label>
		</div>
		<div class="clear"></div>

		<form action="${pageContext.request.contextPath}/user/userRegister" method="post" onsubmit="return check(this)">
			<label class="lbl-4">用&nbsp;&nbsp;&nbsp;户&nbsp;&nbsp;&nbsp;名</label>
			<input type="text" class="text"  name="username">
			<div class="key">
				<label class="lbl-4">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
				<input type="password"name="pwd">
			     <label class="lbl-4">确&nbsp;认&nbsp;密&nbsp;码</label>
				<input type="password" name="ConfirmPwd">
			</div>
			<div class="form opt">
				<label class="lbl-4">&nbsp;&nbsp;&nbsp;Email&nbsp;&nbsp;&nbsp;&nbsp;</label>
				<input type="email"name="email" placeholder="例如：123@qq.com">
			</div>
			<div class="form opt">
				<label class="lbl-4">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
				<input type="text" class="text" name="name" >
			</div>
			<div class="form opt">
				<label class="lbl-4">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label>

				<label class="lbl-4"> <input type="radio" name="sex"
					id="inlineRadio1" value="男" checked="checked"> 男
				</label>
				<lable>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</lable>
				<label class="lbl-4"> <input type="radio" name="sex"
					id="inlineRadio2" value="女"> 女
				</label>
			</div>
			<div class="form opt">
				<label class="lbl-4">出&nbsp;生&nbsp;日&nbsp;期</label> 
				<input type="text" class="text" name="birthday" placeholder="例如：2018-12-31">
			</div>
			<div class="last item">
				<label class="lbl-4">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</label>
				<input type="text" class="text" name="phone">
			</div>
			<div class="signin">
				<input id="register_butt" type="submit" value="OK" />
			</div>
		</form>
	</div>
</body>
</html>
