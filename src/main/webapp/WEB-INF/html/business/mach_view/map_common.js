/**
 * Created by Badger on 16/7/22.
 */

function loadMap(divID) {
    var map = new BMap.Map(divID);          // 创建地图实例
    var point = new BMap.Point(116.404, 39.915);  // 创建点坐标
    map.centerAndZoom(point, 15);                 // 初始化地图，设置中心点坐标和地图级别
    map.enableScrollWheelZoom();    //启动鼠标滚轮缩放地图
    map.enableKeyboard();   //启动键盘操作地图
}