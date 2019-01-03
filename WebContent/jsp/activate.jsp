<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户激活</title>
<style type="text/css">
        *{
            margin: 0;
            padding: 0;
            
        }
        div{
            width: 600px;
            height: 400px;
            
        }
        .center-in-center{
            position: absolute;
            top: 50%;
            left: 50%;
            -webkit-transform: translate(-50%, -50%);
            -moz-transform: translate(-50%, -50%);
            -ms-transform: translate(-50%, -50%);
            -o-transform: translate(-50%, -50%);
            transform: translate(-50%, -50%);
        }
    </style>
<script type="text/javascript">
function activateFlag(){
	var postData= { //传递参数到后台
        "flag": "success"
};
/**重点：ajax的type,url,dataType,data属性*/
$.ajax({
        async : false,
        cache : false,
        type : 'POST',
        url : 'user/activate',
        dataType : "json",
        data : postData,            
        error : function() {
            alert('请求失败 ');
        },
        success : function(data) {
            alert(data);
        }

    });}


</script>
    
    
    <script type="text/javascript">
	var time = 8;
	
	function returnUrlByTime() {
		
 
		window.setTimeout('returnUrlByTime()', 1000);
 
		time = time - 1;
 
		document.getElementById("layer").innerHTML = time;
	
	}
</script>

</head>
<body  style=" text-align:center" onload="returnUrlByTime();activateFlag()">
<div class= "center-in-center">
<h1>账户激活中</h1>
<h1><b><span id="layer">7</span>秒后，转入登录界面。</b> </h1>
<%
		//转向语句
		response.setHeader("Refresh", "7;URL=login.jsp");
	%>
</div>

</body>
</html>