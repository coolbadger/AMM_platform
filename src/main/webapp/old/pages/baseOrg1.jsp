<%--
  Created by csw on 2016/7/24 9:33.
  Explain: 
--%>
<%@ page contentType="text/html;charset=gb2312" language="java" %>

<jsp:include page="/pages/base/head1.jsp" flush="true"></jsp:include>

<body style="font-size: 12px">
<div class="container">
    <div id="toolbar">
        <button id="add" class="btn btn-primary" data-toggle="modal" data-target="#myModal" style="font-size: 12px">
            <i class="glyphicon glyphicon-plus"></i> 添加
        </button>
        <button id="remove" class="btn btn-danger" disabled style="font-size: 12px">
            <i class="glyphicon glyphicon-remove"></i> 删除已选
        </button>
    </div>
    <table id="table"
           class="table table-hover table-bordered table-striped"
           data-toolbar="#toolbar"
           data-search="true"
           data-show-refresh="true"
           data-show-toggle="true"
           data-show-columns="true"
           data-show-export="true"
           data-minimum-count-columns="2"
           data-show-pagination-switch="true"
           data-pagination="true"
           data-id-field="id"
           data-page-list="[10, 25, 50, 100, ALL]"

           data-url="http://localhost:8080/api/baseOrgs"
           datatype="json"
           data-local="zh-US"
            >
    </table>
</div>

<!-- Modal -->
<div ng-app="orgApp" ng-controller="orgCtrl" class="modal fade" id="myModal" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">添加信息</h4>
            </div>
            <div class="modal-body">
                <form id="myForm" action="#" method="post" class="form-horizontal">
                    <div class="form-group">
                        <input type="text" class="form-control hidden" id="id" ng-model="id">
                    </div>
                    <div class="form-group">
                        <label for="orgCode" class="col-sm-2 control-label">组织代码</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="orgCode" ng-model="orgCode"
                                   placeholder="请输入组织代码">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="orgName" class="col-sm-2 control-label">组织名称</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="orgName" ng-model="orgName"
                                   placeholder="请输入组织名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="orgContact" class="col-sm-2 control-label">联系人</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="orgContact" ng-model="orgContact"
                                   placeholder="请输入联系人">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button ng-click="saveOrg()" class="btn btn-success">
                    <i class="glyphicon glyphicon-ok"></i> 保存
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">
                    <i class="glyphicon glyphicon-remove"></i> 取消
                </button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript" src="../static/frameworks/angular-1.5.5/angular.js" charset="utf-8"></script>
<script type="text/javascript" src="../static/js/utilJS/util.js" charset="utf-8"></script>

<script>
    //angularjs提交新增记录或修改记录的js
    var app = angular.module('orgApp', []);
    app.controller('orgCtrl', function ($scope, $http) {
        $scope.saveOrg = function () {
            var params = {
                "orgCode": $("#orgCode").val(),
                "orgName": $("#orgName").val(),
                "orgContact": $("#orgContact").val()
            };
            $http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

            var id = $('#id').val();//取得隐藏id控件的值，用来判断saveOrg方法是创建记录，还是还是修改记录
            if (id != "") {  //修改
//                alert(id);
                var url = 'http://localhost:8080/api/baseOrgs/' + id;
                console.log(params);
                $http({
                    method: 'put',
                    url: url,
                    data: params
                }).success(function (data, state) {
                    console.log(data);
                    console.log(state);
                    if (state == '205') {
                        alert("success");
                        $('#table').bootstrapTable('refresh', {url: 'http://localhost:8080/api/baseOrgs'});
                    } else {
                        alert("error");
                    }
                }).error(function (data) {
                    console.log(data);
                    alert("error!!!!!!")
                });
            } else {    //创建
                var url = 'http://localhost:8080/api/baseOrgs';
                var paramStr = transformParams(params);
                console.log(paramStr);
                $http.post(url, paramStr, {
                    'Content-Type': "application/json"
                }).success(function (data, state) {
                    console.log(data);
                    console.log(state);
                    if (state == '201') {
                        alert("success");
                        $('#table').bootstrapTable('refresh', {url: 'http://localhost:8080/api/baseOrgs'});
                    } else {
                        alert("error");
                    }
                }).error(function (data) {
                    console.log(data);
                    alert("error!!!!!!")
                });
            }
            $('#myModal').modal('hide');
        };
    });

    //bootstrap相关的js
    $(function () {
        var $table = $('#table');
        var $remove = $('#remove');
        var selections = [];
        $table.bootstrapTable({
            height: getHeight(),
            columns: [{
                field: 'state',
                checkbox: true,
                align: 'center',
                valign: 'middle'
            }, {
                title: 'ID',
                field: 'id',
                align: 'center',
                sortable: true,
                footerFormatter: totalTextFormatter
            }, {
                title: '组织代码',
                field: 'orgCode',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '组织名称',
                field: 'orgName',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '联系人',
                field: 'orgContact',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '地址',
                field: 'orgAddress',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '电话',
                field: 'orgTell',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '传真',
                field: 'orgFax',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '邮箱',
                field: 'orgEmail',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '操作',
                field: 'operate',
                align: 'center',
                valign: 'middle',
                formatter: operateFormatter,
                events: operateEvents
            }]
        });
        setTimeout(function () {
            $table.bootstrapTable('resetView');
        }, 200);
        $table.on('check.bs.table uncheck.bs.table ' +
                'check-all.bs.table uncheck-all.bs.table', function () {
            $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);
            // save your data, here just save the current page
            selections = getIdSelections();
            // push or splice the selections if you want to save all data selections
        });

        //'删除已选'按钮操作
        $remove.click(function () {
            var ids = getIdSelections();
            $table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            $remove.prop('disabled', true);
        });

        //'添加'按钮操作，清除模态框内的数据
        $("#add").click(function () {
            //清除form表单中原来的数据
            $(':input', '#myForm')
                    .not(':button, :submit')
                    .val('')
                    .removeAttr('checked')
                    .removeAttr('selected');
        });

        $(window).resize(function () {
            $table.bootstrapTable('resetView', {
                height: getHeight()
            });
        });

        function getIdSelections() {
            return $.map($table.bootstrapTable('getSelections'), function (row) {
                return row.id
            });
        }

        function totalTextFormatter(data) {
            return 'Total';
        }

        function totalNameFormatter(data) {
            return data.length;
        }

        function operateFormatter(value, row, index) {
            return [
                '<a class="edit" href="javascript:void(0)" title="修改">',
                '<i class="glyphicon glyphicon-edit"></i>',
                '</a>&nbsp;&nbsp;&nbsp;',
                '<a class="remove" href="javascript:void(0)" title="删除">',
                '<i class="glyphicon glyphicon-remove"></i>',
                '</a>'
            ].join('');
        }

        function getHeight() {
            return $(window).height();
        }
    });

    window.operateEvents = {
        'click .edit': function (e, value, row, index) {
            $('#id').val(row.id);
            $('#orgCode').val(row.orgCode);
            $('#orgName').val(row.orgName);
            $('#orgContact').val(row.orgContact);
            $('#myModal').modal('show');
        },
        'click .remove': function (e, value, row, index) {
            alert('你选择了删除第' + index + '条记录,' + value + '是: ' + JSON.stringify(row));
            $('#table').bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });
        }
    };

</script>

</body>




