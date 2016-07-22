/**
 * Created by JLee on 16/7/20.
 */

(function () {
    //给JavaScript代码标志为“严格模式”

    var httpService = angular.module('httpService', []);

    httpService.service('httpService', function ($http) {

        var baseURL = 'http://localhost:8080/';

        var postURL = function (url, params, successFunc) {
            var strUrl = baseURL + url;
            $http
                .post(strUrl, params)
                .success(function (responseData) {
                    successFunc(responseData);  //成功处理信息
                    console.log(responseData);
                })
                .error(function (errorData) {

                })
        };


        //把接口返回
        return {
            postUrl: postURL
        }
    });
}).call(this);