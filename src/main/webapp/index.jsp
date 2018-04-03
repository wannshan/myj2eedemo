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
				var point = new BMap.Point(121.507118,31.237184);
				reourceMap.centerAndZoom(point, 15);
				reourceMap.addControl(new BMap.MapTypeControl());
				reourceMap.addControl(new BMap.NavigationControl());
				reourceMap.enableScrollWheelZoom(true);

				//地图初始化完成
				reourceMap.addEventListener("tilesloaded",dogetpointhistory);
                var inited=false;
				function dogetpointhistory(){
                    if(inited){
                        return;
                    }
                    var addData= "platNum=优赛302&startTime=2018-03-30-15-57&endTime=2018-03-30-17-16";
                    $.ajax({
                        url : '/getHistoryPoint.action',
                        type : 'post',
                        dataType:'json',
                        data :addData,
                        success : function(data){
                            var gpsAndWifiPoints = [];
                            var otherPoints = [];
                            var pointType = null;
                            var startPoint = null;
                            var endPoint = null;
                            var firstOne = true;
                            var _point_ = null;
                            var _index_ = 0;

                            $.each(data, function(index, item){
                                if(item.lng != '' && item.lat != '' && item.lng != '0' && item.lat != '0' && item.pointType != 66 &&  item.pointType != 67) {
                                    if(startPoint == null) {
                                        startPoint = new BMap.Point(item.lng, item.lat);
                                    }
                                    // if(index < _index_) {
                                    //     return;
                                    // }
                                    // if(index > 1 && index + 1 < data.length) {
                                    //     if(_point_ == null) {
                                    //         _point_ = data[index - 1];
                                    //     }
                                    //     var distanceA = getDistance(_point_.lng, _point_.lat, this.lng, this.lat);
                                    //     if(distanceA > 100) {
                                    //         for(var idx = index + 1; idx < data.length; idx++) {
                                    //             var distanceB = getDistance(_point_.lng, _point_.lat, data[idx].lng, data[idx].lat);
                                    //             if(distanceB < 100) {
                                    //                 _index_ = idx;
                                    //                 return;
                                    //             }
                                    //         }
                                    //     }
                                    //     _point_ = null;
                                    // }
                                    if(item.pointType == 61 || item.pointType == 161) {
                                        if(firstOne) {
                                            gpsAndWifiPoints.push(startPoint);
                                            firstOne = false;
                                        }
                                        if(otherPoints.length > 0) {
                                            if(61 != pointType && 161 != pointType) {
                                                gpsAndWifiPoints.push(otherPoints[otherPoints.length - 1]);
                                                pointType = item.pointType;
                                            }
                                            reourceMap.addOverlay(new BMap.Polyline(otherPoints, {strokeColor:"#797979", strokeWeight:7, strokeOpacity:0.6, strokeStyle:"dashed"}));   //创建折线
                                            otherPoints = [];
                                        }
                                        gpsAndWifiPoints.push(new BMap.Point(item.lng,item.lat));
                                    } else {
                                        if(firstOne) {
                                            otherPoints.push(startPoint);
                                            firstOne = false;
                                        }
                                        if(gpsAndWifiPoints.length > 0) {
                                            if(61 == pointType || 161 == pointType) {
                                                otherPoints.push(gpsAndWifiPoints[gpsAndWifiPoints.length - 1]);
                                                pointType = item.pointType;
                                            }
                                            reourceMap.addOverlay(new BMap.Polyline(gpsAndWifiPoints, {strokeColor:"red", strokeWeight:7, strokeOpacity:0.6}));   //创建折线 */
                                            gpsAndWifiPoints = [];
                                        }
                                        otherPoints.push(new BMap.Point(item.lng, item.lat));
                                    }
                                    if(null == pointType) {
                                        pointType = item.pointType;
                                    }
                                }
                            });

                            if(otherPoints.length > 0) {
                                reourceMap.addOverlay(new BMap.Polyline(otherPoints, {strokeColor:"#797979", strokeWeight:7, strokeOpacity:0.6, strokeStyle:"dashed"}));   //创建折线
                                endPoint = otherPoints[otherPoints.length - 1];
                            }
                            if(gpsAndWifiPoints.length > 0) {
                                reourceMap.addOverlay(new BMap.Polyline(gpsAndWifiPoints, {strokeColor:"red", strokeWeight:7, strokeOpacity:0.6}));   //创建折线
                                endPoint = gpsAndWifiPoints[gpsAndWifiPoints.length - 1];
                            }
                            var startIcon = new BMap.Icon("${request.contextPath}/resources/img/markers_new.png", new BMap.Size(25, 42));
                            startIcon.setImageOffset(new BMap.Size(-200, -138));
                            reourceMap.addOverlay(new BMap.Marker(startPoint,{icon:startIcon}));    // 起始GPS
                            var endIcon = new BMap.Icon("${request.contextPath}/resources/img/markers_new.png", new BMap.Size(25, 42));
                            endIcon.setImageOffset(new BMap.Size(-225, -138));
                            reourceMap.addOverlay(new BMap.Marker(endPoint,{icon:endIcon}));    // 起始GPS
                        }
                    });
                    inited=true;
				};

                // dogetpointhistory();
               function  getUser() {
                   alert('123');
                   $.ajax({
                           url : '/getUserById.action',
                           type : 'post',
                           dataType:'json',
                           data :"userId=10",
                           success : function(data){
                           }
                       }
                   );
               }
                getUser();
            });

function getDistance(lng_a, lat_a, lng_b, lat_b) {
    var pk = 180 / Math.PI;
    var a1 = lat_a / pk;
    var a2 = lng_a / pk;
    var b1 = lat_b / pk;
    var b2 = lng_b / pk;
    var t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
    var t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
    var t3 = Math.sin(a1) * Math.sin(b1);
    var tt = Math.acos(t1 + t2 + t3);
    var d = 6366000 * tt;
    return d;
}

</script>
</html>
