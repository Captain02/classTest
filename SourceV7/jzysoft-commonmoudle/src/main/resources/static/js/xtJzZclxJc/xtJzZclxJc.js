$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'xtJzZclxJc/xtJzZclxJc/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'ID', index: 'ID', width: 0, key: true, hidden: true},
            {label: '名称', name: 'Name', index: 'Name', width: 80},
            {label: '内部序号', name: 'InOrder', index: 'InOrder', width: 80},
            {label: '编号', name: 'Code', index: 'Code', width: 80},
            // {label: '拓展表名', name: 'KZBM', index: 'KZBM', width: 80},
            // {label: '是否有子对象', name: 'HasChild', index: 'HasChild', width: 80},
            {label: '备注', name: 'Memo', index: 'Memo', width: 80},
            // {label: '删除标志', name: 'DelFlag', index: 'DelFlag', width: 80}

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
            InOrder: null,
            Code: null,
            Name: null,
            KZBM: null,
            HasChild: null,
            Memo: null,
            DelFlag: null,
        },
        showList: true,
        title: null,
        index: null,
        edit: {
            ID: null,
            InOrder: null,
            Code: null,
            Name: null,
            KZBM: null,
            HasChild: null,
            Memo: null,
            DelFlag: null,
            flag: 0,
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
    },
    methods: {
        binddropdown: function () {
            //字典表下拉框
            // 普通下拉框
        },
        // 搜索下拉
        bindsearchdropdown: function () {
        },
        // 添加页面
        addPage: function () {
            debugger
            vm.reload();
            vm.index = layer.open({
                type: 1,
                area: ['58%', '50%'],
                title: '添加建筑组成类型',
                content: $('#editLayer'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                btn: ['确定', '取消'],
                yes: function () {
                    $.ajax({
                        type: "GET",
                        async: false, //同步执行
                        url: "../../xtJzZclxJc/xtJzZclxJc/list",
                        data: {},
                        dataType: "json", //返回数据形式为json
                        success: function (result) {
                            for (var i = 0; i < result.data.length; i++) {
                                if (vm.edit.Code == result.data[i].Code) {
                                    layer.alert("该编号已存在")
                                    vm.edit.flag = 1;
                                    break

                                }
                                else if (vm.edit.Name == result.data[i].Name) {
                                    vm.edit.flag = 1;
                                    layer.alert("该建筑组成类型已存在")
                                    break
                                } else {
                                    vm.edit.flag = 0;
                                }
                            }
                            if (vm.edit.flag != 1) {
                                vm.saveOrUpdate();
                            }
                        },

                    })

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
                url: baseURL + 'xtJzZclxJc/xtJzZclxJc/findByid',
                type: "GET",
                data: {
                    ID: id,
                },
                success: function (result) {
                    vm.edit.ID = result.data.ID;
                    vm.edit.InOrder = result.data.InOrder;
                    vm.edit.Code = result.data.Code;
                    vm.edit.Name = result.data.Name;
                    vm.edit.KZBM = result.data.KZBM;
                    vm.edit.HasChild = result.data.HasChild;
                    vm.edit.Memo = result.data.Memo;
                    vm.edit.DelFlag = result.data.DelFlag;
                    console.log(vm.edit.ID)
                }
            })
            vm.index = layer.open({
                type: 1,
                area: ['60%', '60%'],
                title: '修改建筑组成类型',
                content: $('#editLayer'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                btn: ['确定', '取消'],
                yes: function () {
                    debugger
                    $.ajax({
                        type: "GET",
                        async: false, //同步执行
                        url: "../../xtJzZclxJc/xtJzZclxJc/list",
                        data: {
                            IDS:vm.edit.ID
                        },
                        dataType: "json", //返回数据形式为json
                        success: function (result) {
                            for (var i = 0; i < result.data.length; i++) {
                                if (vm.edit.Code == result.data[i].Code) {
                                    layer.alert("该编号已存在")
                                    vm.edit.flag = 1;
                                    break

                                }
                                else if (vm.edit.Name == result.data[i].Name) {
                                    vm.edit.flag = 1;
                                    layer.alert("该建筑组成类型已存在")
                                    break
                                } else {
                                    vm.edit.flag = 0;
                                }
                            }

                            if (vm.edit.flag != 1) {
                                vm.saveOrUpdate(vm.edit.ID);
                            }

                        },

                    })
                },
                btn2: function () {
                    vm.reload();
                }
            });
        },
        <!--新增修改-->
        saveOrUpdate: function (id) {
            var url = id == null ? "xtJzZclxJc/xtJzZclxJc/adddata" : "xtJzZclxJc/xtJzZclxJc/updatedata";
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
                    'InOrder': vm.q.InOrder,
                    'Code': vm.q.Code,
                    'Name': vm.q.Name,
                    'KZBM': vm.q.KZBM,
                    'HasChild': vm.q.HasChild,
                    'Memo': vm.q.Memo,
                    'DelFlag': vm.q.DelFlag,
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
            var r = layer.confirm('您确定要删除该组成类型吗?', {btn: ['确定', '取消'], title: "提示"}, function () {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: baseURL + "xtJzZclxJc/xtJzZclxJc/del",
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
            vm.q.InOrder = null;
            vm.q.Code = null;
            vm.q.Name = null;
            vm.q.KZBM = null;
            vm.q.HasChild = null;
            vm.q.Memo = null;
            vm.q.DelFlag = null;
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.flag = 0;
            vm.edit.InOrder = null;
            vm.edit.Code = null;
            vm.edit.Name = null;
            vm.edit.KZBM = null;
            vm.edit.HasChild = null;
            vm.edit.Memo = null;
            vm.edit.DelFlag = null;
            $("#editLayer")[0].reset();
            layer.closeAll();
            $("#jqGrid").trigger("reloadGrid");
        }
    }
});