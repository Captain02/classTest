$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'cVideoreporte/cVideoreporte/list',
        datatype: "json",
        colModel: [
            {label: '', name: 'id', index: 'id', width: 0, key: true, hidden: true},
            {label: '课程名称', name: 'currname', index: 'currname', width: 80},
            {label: '访问用户', name: 'username', index: 'username', width: 80},
            {label: '访问时间', name: 'createtime', index: 'createtime', width: 80}

        ],
        viewrecords: true,
        height: "100%",
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "data",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalResult"
        },
        prmNames: {
            page: "currentPage",
            rows: "showCount",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });
    $(window).on('load', function () {

        $('.selectpicker').selectpicker({
            'selectedText': 'cat',
            noneSelectedText: '请选择'
        });
    });
});

var vm = new Vue({
    el: '#rrapp',

    data: {
        q: {
            userid: null,
            currid: null,
            createtime: null,
        },
        showList: true,
        title: null,
        index: null,
        edit: {
            id: null,
            userid: null,
            currid: null,
            createtime: null,
        },
        dropdown: [
            {
                id: null,
                name: null,
            }
        ],
        dropdown1: [
            {
                id: null,
                name: null,
            }
        ]
    },
    created: function () {
        // this.bindsearchdropdown();
        // this.binddropdown();
    },
    methods: {
        binddropdown: function () {
            //字典表下拉框
            this.dropdown1 = BindDropDownControlsdy('sys_dict_data', 'dict_', 'dict_label', '起重机类型管理')
            // 普通下拉框
            this.dropdown = BindDropDownControls('customer', 'customer_id', 'username');
        },
        // 搜索下拉
        bindsearchdropdown: function () {
            BindDropDownControl('selectuserid', 'customer', 'customer_id', 'username')
        },
        // 添加页面
        addPage: function () {
            vm.reload();
            vm.index = layer.open({
                type: 1,
                area: ['95%', '95%'],
                content: $('#editLayer'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                btn: ['确定', '取消'],
                yes: function () {
                    vm.saveOrUpdate();
                },
                btn2: function () {
                    vm.reload();
                }
            });
        },
        <!--修改页面-->
        editPage: function (x) {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            $.ajax({
                url: baseURL + 'cVideoreporte/cVideoreporte/findByid',
                type: "GET",
                data: {
                    id: id,
                },
                success: function (result) {
                    vm.edit.id = result.data.id;
                    vm.edit.userid = result.data.userid;
                    vm.edit.currid = result.data.currid;
                    vm.edit.createtime = result.data.createtime;
                }
            })
            vm.index = layer.open({
                type: 1,
                area: ['95%', '95%'],
                content: $('#editLayer'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                btn: ['确定', '取消'],
                yes: function (id) {
                    vm.saveOrUpdate(id);
                },
                btn2: function () {
                    vm.reload();
                }
            });
        },
        <!--新增修改-->
        saveOrUpdate: function (id) {
            var url = id == null ? "cVideoreporte/cVideoreporte/adddata" : "cVideoreporte/cVideoreporte/updatedata";
            $.ajax({
                url: baseURL + url,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(vm.edit),
                success: function (result) {
                    vm.reload();
                    layer.close(vm.index)
                },
                btn2: function () {
                    vm.reload();
                }
            })
        },
        // 搜索
        query: function () {
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'userid': vm.q.userid,
                    'currid': vm.q.currid,
                    'createtime': vm.q.createtime,
                },
                page: page
            }).trigger("reloadGrid");
        },
        // 删除
        del: function (event) {
            var ids = getSelectedRows();
            if (ids == null) {
                return;
            }
            var r = layer.confirm('您确定要删除该用户吗?', {btn: ['确定', '取消'], title: "提示"}, function () {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: baseURL + "/cVideoreporte/cVideoreporte/del",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function (r) {
                            if (r.code = 1) {
                                vm.reload();
                            } else {
                                alert("删除失败")
                            }

                        }
                    });
                }
            });
        },
        // 重置
        reset: function () {
            vm.q.userid = null;
            vm.q.currid = null;
            vm.q.createtime = null;
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.userid = null;
            vm.edit.currid = null;
            vm.edit.createtime = null;
            $("#editLayer")[0].reset();
            layer.closeAll();
            $("#jqGrid").trigger("reloadGrid");
        },
        excelExport: function (){
            $.modal.confirm("确定导出所有课程报表吗？", function() {

                $.ajax({
                    type: "POST",
                    url: baseURL + "/cVideoreporte/cVideoreporte/exportReport",
                    // contentType: "application/json",
                    // data: JSON.stringify(ids),
                    success: function (r) {
                        if (r.code == 0) {
                            window.location.href = ctx + "common/download?fileName=" + encodeURI(r.msg) + "&delete=" + true;
                        } else {
                            $.modal.alertError("导出失败");
                        }

                    }
                });

            });
        }
    }
});