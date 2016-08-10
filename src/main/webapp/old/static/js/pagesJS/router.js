/**
 * Created by JLee on 16/7/20.
 */

/*
路由配置
 */

var router = angular.module('WebDemo',['ngRoute','login','home']);

router.config(['$routeProvider', function ($routeProvider)
{
    $routeProvider.when('/',{       //一进来就显示的页面
        templateUrl: 'pages/login.html',
        controller:'loginCtrl'
    });
    $routeProvider.when('/home',{
        templateUrl: '/pages/home.html',
        controller:'thirdCtrl'
    })
}]);