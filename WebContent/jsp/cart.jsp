<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title>购物车</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css" type="text/css" />
		<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js" type="text/javascript"></script>
		<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript"></script>
		<!-- 引入自定义css文件 style.css -->
		<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css" type="text/css"/>
		<style>
			body {
				margin-top: 20px;
				margin: 0 auto;
			}
			
			.carousel-inner .item img {
				width: 100%;
				height: 300px;
			}
			
			.container .row div {
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
		
		
		<script type="text/javascript">
			$(function(){
				//页面加载完成后获取到class为delete的元素，为其绑定点击事件
				$(".delete").click(function(){
					if(confirm("确认删除？")){
						//获取到被删除商品的pid
						var pid = this.id;
						//window.location.href="/store_v1.0/CartServlet?method=removeCartItem&id="+pid;
						window.location.href="${pageContext.request.contextPath}/cart/removeCartItem?pid="+pid;
					}
				});
				
				$("#clear").click(function(){
					if(confirm("确认清空购物车？")){
						window.location.href="${pageContext.request.contextPath}/cart/clearCart";
					}
				});
				

				//修改商品数量：
				//1.值为1的时候提示不能再减少了
				//2.值大于100时提示不能购买更多
				//3.数量增加或减少时对应修改购物项小计和购物车总价
				//-------------------------------------------------------------------------------------------------
				//为“-”绑定点击事件
				$(".quanMinus").click(function(){
					$(".quanPlus").attr("disabled",false);
					
					var pid = this.id;//商品id
					//获取数量标签
					var x=document.getElementsByName("quantity");
					var target;//目标数量标签
					for(var i=0;i<x.length;i++){
						var a = x[i];
						if(pid == a.id){
							//quantity = a.value;
							target = a;
							break;
						}
					}
					
					//获取小计标签
					var y = document.getElementsByClassName("subtotal");
					var targetSubTotal;//目标小计标签
					for(var i=0;i<y.length;i++){
						var a = y[i];
						if(pid == a.id){
							targetSubTotal = a;
							break;
						}
					}
					
					if(target.value == 1){
						alert("该商品数量不能再少了");
						$(this).attr("disabled",true);
					}else{
						//商品数量减1
						target.value = target.value - 1;
						var url = "${pageContext.request.contextPath}/cart/updateCart";
						var obj = {"pid":pid,"num":-1};
						$.post(url,obj,function(data){
							//获取服务端响应的数据
							//subTotal@total
							var str = data.split("@");
							var newSubTotal = str[0];
							var newTotal = str[1];
							
							targetSubTotal.innerHTML = "￥" + newSubTotal;
							$("#cartTotal").html("￥" + newTotal + "元");
						},"html");
						//window.open("${pageContext.request.contextPath}/cart/reduceQuantity?pid="+pid);
					}
				});
				
				//-------------------------------------------------------------------------------------------------
				//为“+”绑定点击事件 increase
				$(".quanPlus").click(function(){
					$(".quanMinus").attr("disabled",false);
					var pid = this.id;//商品id
					//获取数量标签
					var x=document.getElementsByName("quantity");
					var target;//目标数量标签
					for(var i=0;i<x.length;i++){
						var a = x[i];
						if(pid == a.id){
							//quantity = a.value;
							target = a;
							break;
						}
					}
					//获取小计标签
					var y = document.getElementsByClassName("subtotal");
					var targetSubTotal;//目标小计标签
					for(var i=0;i<y.length;i++){
						var a = y[i];
						if(pid == a.id){
							targetSubTotal = a;
							break;
						}
					}
					
					if(target.value == 100){
						alert("已达最大购买数量");
						$(this).attr("disabled",true);
					}else{
						//商品数量加1
						target.value = parseInt(target.value) + 1;
						var url = "${pageContext.request.contextPath}/cart/updateCart";
						var obj = {"pid":pid,"num":1};
						$.post(url,obj,function(data){
							//获取服务端响应的数据
							//subTotal@total
							var str = data.split("@");
							var newSubTotal = str[0];
							var newTotal = str[1];
							
							targetSubTotal.innerHTML = "￥" + newSubTotal;
							$("#cartTotal").html("￥" + newTotal + "元");
						},"html");
						//window.open("${pageContext.request.contextPath}/cart/reduceQuantity?pid="+pid);
					}
				});
				
				//-------------------------------------------------------------------------------------------------
				//根据输入框输入的数据修改商品数量
				var oldValue;
				var newValue;
				$(".quan").focus(function(){
					var pid = this.id; 
					//console.log("quantity:" + this.value);
					oldValue = this.value;
				}); 
				$(".quan").on('input',function(){
					var pid = this.id; 
					//console.log("quantity:" + this.value);
					newValue = this.value;
					
					if(newValue == ""){
						//this.value = oldValue;
						//alert("不能为空");
					}else if(newValue == 0){
						this.value = oldValue;
						alert("购买数量不能为0");
					}else if(newValue < 0){
						this.value = oldValue;
						alert("购买数量不能为负");
					}else if(newValue > 100){
						this.value = oldValue;
						alert("购买数量不能超过100");
					}else if(newValue >= 1 && newValue <= 100){
						
					}else{
						this.value = oldValue;
						alert("输入不合法，请重新输入");
					}
					
				}); 
				
				$(".quan").blur(function(){
					
					newValue = this.value;
					
					var pid = this.id;
					console.log("pid:" + this.id)
					console.log("old:" + oldValue);
					console.log("new:" + newValue);
					
					if(newValue.length == 0){
						this.value = oldValue;
						alert("购买数量不能为空");
					}
					if(newValue != oldValue && newValue >= 1 && newValue <= 100){
						var num = newValue - oldValue;//商品变化的数量
						console.log("num:" + num);
						
						//获取数量标签
						var x=document.getElementsByName("quantity");
						var target;//目标数量标签
						for(var i=0;i<x.length;i++){
							var a = x[i];
							if(pid == a.id){
								//quantity = a.value;
								target = a;
								break;
							}
						}
						
						//获取小计标签
						var y = document.getElementsByClassName("subtotal");
						var targetSubTotal;//目标小计标签
						for(var i=0;i<y.length;i++){
							var a = y[i];
							if(pid == a.id){
								targetSubTotal = a;
								break;
							}
						}
						
						//商品数量变化
						//target.value = parseInt(target.value) + parseInt(num);
						var url = "${pageContext.request.contextPath}/cart/updateCart";
						var obj = {"pid":pid,"num":num};
						$.post(url,obj,function(data){
							//获取服务端响应的数据
							//subTotal@total
							var str = data.split("@");
							var newSubTotal = str[0];
							var newTotal = str[1];
							
							targetSubTotal.innerHTML = "￥" + newSubTotal;
							$("#cartTotal").html("￥" + newTotal + "元");
						},"html");
					}
					
				});
				
				
			});
		</script>
	</head>

	<body>

			<%@ include file="/jsp/header.jsp" %>
			


		<div class="container">
			<c:if test="${empty cart}">
				<div class="row">
					<div class="col-md-12">
						<h1>购物车中还没有商品</h1>
					</div>
				</div>
			</c:if>
			
			<c:if test="${not empty cart}">
			   		<div class="row">
					<div style="margin:0 auto; margin-top:10px;width:950px;">
						<strong style="font-size:16px;margin:5px 0;">购物车详情</strong>
						<table class="table table-bordered">
							<tbody>
								<tr class="warning">
									<th>图片</th>
									<th>商品</th>
									<th>价格</th>
									<th>数量</th>
									<th>小计</th>
									<th>操作</th>
								</tr>
								<c:forEach items="${cartItems}" var="item">
								<tr class="active">
									<td width="60" width="40%">
										<input type="hidden" name="id" value="22">
										<img src="${pageContext.request.contextPath}/${item.product.pimage}" width="70" height="60">
									</td>
									<td width="30%">
										<a target="_blank" href="${pageContext.request.contextPath}/product/findProductByPid?pid=${item.product.pid}">${item.product.pname}</a>
									</td>
									<td width="20%">
										￥${item.product.shop_price}
									</td>
									<td width="15%"><!-- 10% -->
										<input type="button" value="-" class="quanMinus" id="${item.product.pid}">
										<input class="quan" id="${item.product.pid}" type="text" name="quantity" value="${item.quantity}" maxlength="4" size="2" style="text-align:center;">
										<input type="button" value="+" class="quanPlus" id="${item.product.pid}">
									</td>
									<td width="15%">
										<span class="subtotal" id="${item.product.pid}">￥${item.total}</span>
									</td>
									<td>
										<a href="javascript:;" id="${item.product.pid}" class="delete">删除</a>
									</td>
								</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
				</div>
	
	  			<div style="margin-right:130px;">
					<div style="text-align:right;">
					<!--  	<em style="color:#ff6600;">
							登录后确认是否享有优惠&nbsp;&nbsp;
						</em> 
						赠送积分: <em style="color:#ff6600;">${cart.total}</em>&nbsp; 
					-->
					商品金额: <strong id="cartTotal" style="color:#ff6600;">￥${cart.total}元</strong>
					</div>
					<div style="text-align:right;margin-top:10px;margin-bottom:10px;">
						<a href="${pageContext.request.contextPath}/cart/clearCart" id="clear" class="clear">清空购物车</a>
						<a href="${pageContext.request.contextPath}/OrderServlet?method=saveOrder">
							<%--提交表单 --%>
							<input type="submit" width="100" value="提交订单" name="submit" border="0" style="background: url('${pageContext.request.contextPath}/img/register.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0);
							height:35px;width:100px;color:white;">
						</a>
					</div>
				</div>
				
			
			</c:if>
			

		</div>

		

		<%@ include file="/jsp/footer.jsp" %>

	</body>

</html>