<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<title>首页</title>
<style type="text/css">
	html{height:100%}
	body{height:100%;margin:0px;padding:0px}
	#container{height:100%}
</style>
	<script type="text/javascript" charset="utf-8" src="jscript/jquery-1.10.1.min.js"></script>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=C38c741903df72b4f624db13033e4644">
</script>
</head>
<body>
	<!-- 页头 -->
	<div id="container">
	</div>
</body>
	<script type="text/javascript">
//页面加载后执行
			$(function(){

				// 百度地图API功能
//				var map = new BMap.Map("container");
//				map.centerAndZoom(new BMap.Point(121.355053,31.229125), 15);
//				map.enableScrollWheelZoom();
//
//				var polyline = new BMap.Polyline([
//								new BMap.Point(121.355053,31.229125),
//								new BMap.Point(121.335629,31.229185),
//								new BMap.Point(121.325097,31.227451)
//				], {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});   //创建折线
//				map.addOverlay(polyline);   //增加折线

//				var polygon = new BMap.Polygon([
//					new BMap.Point(116.387112,39.920977),
//					new BMap.Point(116.385243,39.913063),
//					new BMap.Point(116.394226,39.917988),
//					new BMap.Point(116.401772,39.921364),
//					new BMap.Point(116.41248,39.927893)
//				], {strokeColor:"blue", strokeWeight:2, strokeOpacity:0.5});  //创建多边形
//				map.addOverlay(polygon);   //增加多边形
				var reourceMap = new BMap.Map("container");
				var point = new BMap.Point(121.355053,31.229125);
				reourceMap.centerAndZoom(point, 15);
				reourceMap.addControl(new BMap.MapTypeControl());
				reourceMap.addControl(new BMap.NavigationControl());
				reourceMap.enableScrollWheelZoom(true);

				//地图初始化完成
				reourceMap.addEventListener("tilesloaded",dogetpointhistory);

				function dogetpointhistory(){

					reourceMap.removeEventListener("tilesloaded",dogetpointhistory);
					$.ajax({
						url : '/data/gps.json',
						type : 'get',
						dataType:'json',
						success : function(data){
							var otherPoints = [];
							$.each(data, function(index, item){
										otherPoints.push(new BMap.Point(item.lng, item.lat));
							});
							var polygon = new BMap.Polyline(otherPoints, {strokeColor:"red", strokeWeight:2, strokeOpacity:0.8});
                                 // 创建多边形
								reourceMap.addOverlay(polygon);   //创建折线
//								reourceMap.addOverlay(new BMap.Polyline(otherPoints, {strokeColor:"#797979", strokeWeight:7, strokeOpacity:0.6, strokeStyle:"dashed"}));   //创建折线
							}

				});
				}
			});
</script>
</html>
