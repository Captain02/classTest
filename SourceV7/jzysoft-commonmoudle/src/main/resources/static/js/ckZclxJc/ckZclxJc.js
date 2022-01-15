$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'ckZclxJc/ckzclxjc/list',
        datatype: "json",
        colModel: [
            {label: '', name: 'ID', index: 'ID', width: 0, key: true, hidden: true},
            {label: '名称', name: 'Name', index: 'Name', width: 80},
            {label: '内部序号', name: 'InOrder', index: 'InOrder', width: 80},
            {label: '编号', name: 'Code', index: 'Code', width: 80},
            {label: '所属ID', name: 'ParentID', index: 'ParentID', width: 80, hidden: true},
            {label: '扩展表名', name: 'KZBM', index: 'KZBM', width: 80, hidden: true},
            {label: '是否有子对象', name: 'HasChild', index: 'HasChild', width: 80, hidden: true},
            {label: '是否可以在其上按装设备', name: 'HasDev', index: 'HasDev', width: 80, hidden: true},
            {label: '备注', name: 'Memo', index: 'Memo', width: 80},
            {label: '删除标志', name: 'DelFlag', index: 'DelFlag', width: 80, hidden: true}

        ],
        viewrecords: true,
        height: 385,
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
        layertitle: '添加',
        q: {
            InOrder: null,
            Code: null,
            Name: null,
            ParentID: null,
            KZBM: null,
            HasChild: null,
            HasDev: null,
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
            ParentID: null,
            KZBM: null,
            HasChild: null,
            HasDev: null,
            Memo: null,
            DelFlag: null,
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
        this.bindsearchdropdown();
        this.binddropdown();
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
            vm.layertitle = '添加';
            vm.reload();
            vm.index = layer.open({
                type: 1,
                area: ['60%', '60%'],
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
                url: baseURL + 'ckZclxJc/ckzclxjc/findByid',
                type: "GET",
                data: {
                    ID: id,
                },
                success: function (result) {
                    vm.edit.ID = result.data.ID;
                    vm.edit.InOrder = result.data.InOrder;
                    vm.edit.Code = result.data.Code;
                    vm.edit.Name = result.data.Name;
                    vm.edit.ParentID = result.data.ParentID;
                    vm.edit.KZBM = result.data.KZBM;
                    vm.edit.HasChild = result.data.HasChild;
                    vm.edit.HasDev = result.data.HasDev;
                    vm.edit.Memo = result.data.Memo;
                    vm.edit.DelFlag = result.data.DelFlag;
                    vm.edit.FullName = result.data.FullName;
                }
            })
            vm.layertitle = '编辑';
            vm.index = layer.open({
                type: 1,
                area: ['60%', '60%'],
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
            var url = id == null ? "ckZclxJc/ckzclxjc/adddata" : "ckZclxJc/ckzclxjc/updatedata";
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
                    'ParentID': vm.q.ParentID,
                    'KZBM': vm.q.KZBM,
                    'HasChild': vm.q.HasChild,
                    'HasDev': vm.q.HasDev,
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
            var r = layer.confirm('您确定要删除该用户吗?', {btn: ['确定', '取消'], title: "提示"}, function () {
                if (r) {
                    $.ajax({
                        type: "POST",
                        url: baseURL + "/ckZclxJc/ckzclxjc/del",
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
            vm.q.ParentID = null;
            vm.q.KZBM = null;
            vm.q.HasChild = null;
            vm.q.HasDev = null;
            vm.q.Memo = null;
            vm.q.DelFlag = null;
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.InOrder = null;
            vm.edit.Code = null;
            vm.edit.Name = null;
            vm.edit.ParentID = null;
            vm.edit.KZBM = null;
            vm.edit.HasChild = null;
            vm.edit.HasDev = null;
            vm.edit.Memo = null;
            vm.edit.DelFlag = null;
            $("#editLayer")[0].reset();
            layer.closeAll();
            $("#jqGrid").trigger("reloadGrid");
        }
    }
});