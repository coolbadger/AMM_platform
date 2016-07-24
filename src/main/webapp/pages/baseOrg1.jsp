<%--
  Created by csw on 2016/7/24 9:33.
  Explain: 
--%>
<%@ page contentType="text/html;charset=gb2312" language="java" %>

<jsp:include page="/pages/base/head1.jsp" flush="true"></jsp:include>
<%--<%@ include file="/pages/base/head1.jsp" %>--%>

<body>

<div class="container" style="font-size: 12px">
    <div id="toolbar">
        <button id="add" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
            <i class="glyphicon glyphicon-plus"></i> ���
        </button>
        <button id="remove" class="btn btn-danger" disabled>
            <i class="glyphicon glyphicon-remove"></i> ɾ����ѡ
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
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop="static">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">�����Ϣ</h4>
            </div>
            <div class="modal-body">
                <form id="myForm" action="#" method="post" class="form-horizontal">
                    <div class="form-group">
                        <label for="orgCode" class="col-sm-2 control-label">��֯����</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="orgCode" name="orgCode" placeholder="��������֯����">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="orgName" class="col-sm-2 control-label">��֯����</label>

                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="orgName" name="orgName"
                                   placeholder="��������֯����">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="orgContact" class="col-sm-2 control-label">��ϵ��</label>

                        <div class="col-sm-10">
                            <input type="email" class="form-control" id="orgContact" name="orgContact" placeholder="��������ϵ��">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-success">
                    <i class="glyphicon glyphicon-ok"></i> ����
                </button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">
                    <i class="glyphicon glyphicon-remove"></i> ȡ��
                </button>
            </div>
        </div>
    </div>
</div>

<script>
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
                title: '��֯����',
                field: 'orgCode',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '��֯����',
                field: 'orgName',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '��ϵ��',
                field: 'orgContact',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '��ַ',
                field: 'orgAddress',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '�绰',
                field: 'orgTell',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '����',
                field: 'orgFax',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '����',
                field: 'orgEmail',
                align: 'center',
                footerFormatter: totalNameFormatter
            }, {
                title: '����',
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
        $table.on('expand-row.bs.table', function (e, index, row, $detail) {
            if (index % 2 == 1) {
                $detail.html('Loading from ajax request...');
                $.get('LICENSE', function (res) {
                    $detail.html(res.replace(/\n/g, '<br>'));
                });
            }
        });
        $table.on('all.bs.table', function (e, name, args) {
            console.log(name, args);
        });
        $remove.click(function () {
            var ids = getIdSelections();
            $table.bootstrapTable('remove', {
                field: 'id',
                values: ids
            });
            $remove.prop('disabled', true);
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
                '<a class="edit" href="javascript:void(0)" title="�޸�">',
                '<i class="glyphicon glyphicon-edit"></i>',
                '</a>  ',
                '<a class="remove" href="javascript:void(0)" title="ɾ��">',
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
            var rowJsonData = jQuery.parseJSON(JSON.stringify(row));
            //alert('��ѡ���˱༭��'+index+'����¼,'+value+'��: ' + JSON.stringify(row));
            //alert(rowJsonData.userName);
            $('#userName').val(rowJsonData.userName);
            $('#myModal').modal('show');
//            window.parent.window.$(document).find("#myModal").modal('show');
        },
        'click .remove': function (e, value, row, index) {
            alert('��ѡ����ɾ����' + index + '����¼,' + value + '��: ' + JSON.stringify(row));
            $('#table').bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });
        }
    };
</script>

</body>




