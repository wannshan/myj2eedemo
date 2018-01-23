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
				};
				var searchComplete = function (results) {
					var plan = results.getPlan(0);
					alert(plan.getDistance(false));
				}
				var transit = new BMap.DrivingRoute(reourceMap, {renderOptions: {map: reourceMap},
					onSearchComplete: searchComplete,
				});

				var beginPoint = new BMap.Point("121.255261", "31.157828");
				var endPoint = new BMap.Point("121.335836", "30.731636");
				try{
					transit.search(beginPoint, endPoint);
				} catch(error) {
                   alert('ccc');
				}



			});
</script>
</html>
