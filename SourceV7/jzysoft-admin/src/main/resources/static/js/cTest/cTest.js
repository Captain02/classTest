$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'cTest/cTest/list',
        datatype: "json",
        colModel: [
            {label: '${column.columnComment}', name: 'id', index: 'id', width: 0, key: true, hidden: true},
            {label: '微课堂', name: 'mname', index: 'mclassid', width: 80},
            {label: '创建时间', name: 'createtime', index: 'createtime', width: 80},
            {label: '是否审核通过', name: 'isexaminestr', index: 'isexamine', width: 80},
            {label: '题干', name: 'teststem', index: 'teststem', width: 80, formatter:showItem},


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

function showItem(cellvalue, options, cell){
    var id = cell.testid

    var a = '<a href="" onclick="openItem('+id+')">'+cell.teststem+'</a>'

    return a
}

function openItem(id){
    debugger
    layer.open({
        type: 2,
        title: '选项管理',
        maxmin: true,
        shadeClose: false, // 点击遮罩关闭层
        area: ['100%', '100%'],
        content: baseURL + 'cTestItem/cTestItem/'+id, //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响

        // yes: function () {
        //     vm.saveOrUpdate();
        // },
        // btn2: function () {
        //     vm.reload();
        // }
    });
}
var vm = new Vue({
    el: '#rrapp',

    data: {
        q: {
            mclassid: null,
            createtime: null,
            isexamine: null,
            teststem: null,
            remarks1: null,
            remarks2: null,
            remarks3: null,
        },
        showList: true,
        title: null,
        index: null,
        edit: {
            id: null,
            mclassid: null,
            createtime: null,
            isexamine: null,
            teststem: null,
            remarks1: null,
            remarks2: null,
            remarks3: null,
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
                url: baseURL + 'cTest/cTest/findByid',
                type: "GET",
                data: {
                    id: id,
                },
                success: function (result) {
                    vm.edit.id = result.data.id;
                    vm.edit.mclassid = result.data.mclassid;
                    vm.edit.createtime = result.data.createtime;
                    vm.edit.isexamine = result.data.isexamine;
                    vm.edit.teststem = result.data.teststem;
                    vm.edit.remarks1 = result.data.remarks1;
                    vm.edit.remarks2 = result.data.remarks2;
                    vm.edit.remarks3 = result.data.remarks3;
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
            var url = id == null ? "cTest/cTest/adddata" : "cTest/cTest/updatedata";
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
                    'mclassid': vm.q.mclassid,
                    'createtime': vm.q.createtime,
                    'isexamine': vm.q.isexamine,
                    'teststem': vm.q.teststem,
                    'remarks1': vm.q.remarks1,
                    'remarks2': vm.q.remarks2,
                    'remarks3': vm.q.remarks3,
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
                        url: baseURL + "/cTest/cTest/del",
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
            vm.q.mclassid = null;
            vm.q.createtime = null;
            vm.q.isexamine = null;
            vm.q.teststem = null;
            vm.q.remarks1 = null;
            vm.q.remarks2 = null;
            vm.q.remarks3 = null;
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.mclassid = null;
            vm.edit.createtime = null;
            vm.edit.isexamine = null;
            vm.edit.teststem = null;
            vm.edit.remarks1 = null;
            vm.edit.remarks2 = null;
            vm.edit.remarks3 = null;
            $("#editLayer")[0].reset();
            layer.closeAll();
            $("#jqGrid").trigger("reloadGrid");
        }
    }
});