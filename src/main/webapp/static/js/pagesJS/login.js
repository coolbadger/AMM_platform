/**
 * Created by JLee on 16/7/20.
 */

var secondCtrl = angular.module('login', ['ngRoute', 'httpService']);

secondCtrl.controller('loginCtrl', function ($scope, $location, httpService) {
    $scope.login = function () {
        var params = {
            "username": $scope.username,
            "password": $scope.password
        };

        httpService.postUrl("api/system/login", params, function (data) {
            //alert(data);
            if (data.code == '200') {
                //alert("success")
                //$location.path('/home');
                window.location.href="http://localhost:8080/pages/home.html";
            } else {
                alert("error")
                $location.path('/');
            }
        });

        console.log(params);
        console.log($scope.username, $scope.password);
        console.log("登录");

    }
});