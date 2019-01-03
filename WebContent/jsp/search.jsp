<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>元旦狂欢网络商城</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	type="text/css" />
<script src="${pageContext.request.contextPath}/js/jquery-1.11.3.min.js"
	type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"
	type="text/javascript"></script>
</head>

<body>
	<div class="container-fluid">

		<%@ include file="/jsp/header.jsp"%>

		<!--

            	描述：商品显示
            -->
		<div id="box">

		</div>


		<!--
            	描述：页脚部分
            -->
		<%@ include file="/jsp/footer.jsp"%>

	</div>
</body>


<script>

        
        function getQueryString(name) {  
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
            var r = window.location.search.substr(1).match(reg);  
            if (r != null) return unescape(decodeURIComponent(r[2]));  
            return null;  
        }  
        //页面加载   获取全部信息
        var search = getQueryString("search");
        //alert(search);
        $(function(){
            $.ajax({
                type: "GET",//请求方式
                dataType: "json",
                url: "http://localhost:8081/solr/store/select?hl.fl=product_name,product_desc&hl.simple.post=%3C/span%3E&hl.simple.pre=%3Cspan%20style=%27color:red%27%3E&hl=on&q=product_name:" + search + " OR product_desc:" + search, 
                success:function(result){
                    addBox(result);
                }
            });
            /*$.get("item.json",function(result){
                //result数据添加到box容器中;
                addBox(result);
            });*/
        });
        function addBox(result){
        //	alert(result);
        
        	var info = result.response.docs;
        	var highlighting = result.highlighting;
            $.each(info,function(index,obj){
            	var id = obj['id'];
            	var high = highlighting[id];
            	if(high.hasOwnProperty("product_name")){
            		//alert(high['product_name']);
            		var product_name = high['product_name'][0];
            	}
            	else{
            		var product_name = obj['product_name'];
            	}
            	if(high.hasOwnProperty("product_desc")){
            		//alert(high['product_name']);
            		var product_desc = high['product_desc'][0];
            	}
            	else{
            		var product_desc = obj['product_desc'];
            	}            	
        		$("#box").append("<div class='col-md-6' style='text-align:center;height:200px;padding:10px 0px;'>" + 
												"<a href='${pageContext.request.contextPath}/product/findProductByPid?pid=" + obj['product_id'] + "'>" +
													"<img src='${pageContext.request.contextPath}/" + obj['product_img'] + "' width='130' height='130' style='display: inline-block;' />" +
												"</a>" + 
												"<p><a href='${pageContext.request.contextPath}/product/findProductByPid?pid=" + obj['product_id'] + "' style='color:#666'>" + product_name + "</a><br/></p>" +
												"<p>" + product_desc + "</p>" +
												"<p><font color='#E4393C' style='font-size:16px'>&yen;" + obj['product_price'] + "</font><br/></p>" +
											 "<br/></div>"); 
            });
        }
    </script>
</html>