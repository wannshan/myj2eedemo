<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>首页</title>
<script type="text/javascript" charset="utf-8" src="jscript/jquery-1.10.1.min.js"></script>
</head>
<body>
	<!-- 页头 -->
	<div id="mapdiv">
	</div>
</body>
<script type="text/javascript">
$(function () {
	$.ajax({
	url: "/data/json.data",//json文件位置
	type: "GET",//请求方式为get
	dataType: "json", //返回数据格式为json
	success: function(data) {//请求成功完成后要执行的方法
	$.each(data.first, function(i, item) {
	var str = '姓名:' + item.name + '性别：' + item.sex + '</div>';
	  document.write(str);
	})
	}
	});
});
</script>
</html>
