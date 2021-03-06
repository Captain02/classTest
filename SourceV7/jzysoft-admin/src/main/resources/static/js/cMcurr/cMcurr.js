
    // $("#jqGrid").jqGrid({
    //     url: baseURL + 'cMcurr/cMcurr/list',
    //     datatype: "json",
    //     colModel: [
    //         {label: '${column.columnComment}', name: 'id', index: 'id', width: 0, key: true, hidden: true},
    //         {label: '微课堂名称', name: 'mname', index: 'mname', width: 80,},
    //         {label: '任教老师', name: 'teachername', index: 'teacherid', width: 80},
    //         {label: '创建时间', name: 'createtime', index: 'createtime', width: 80},
    //         // {label: '是否审核通过', name: 'isexamine', index: 'isexamine', width: 80},
    //         // {label: '父课程', name: 'parentid', index: 'parentid', width: 80},
    //         {label: '是否审核通过', name: 'isexaminestr', index: 'isexaminestr', width: 80},
    //         {label: '操作', name: '', index: '', width: 80, formatter: op},
    //         // {label: '备注1', name: 'remarks1', index: 'remarks1', width: 80},
    //         // {label: '备注2', name: 'remarks2', index: 'remarks2', width: 80},
    //         // {label: '备注3', name: 'remarks3', index: 'remarks3', width: 80}
    //
    //     ],
    //     viewrecords: true,
    //     height: "100%",
    //     rowNum: 10,
    //     rowList: [10, 30, 50],
    //     rownumbers: true,
    //     rownumWidth: 25,
    //     autowidth: true,
    //     multiselect: true,
    //     pager: "#jqGridPager",
    //
    //     jsonReader: {
    //         root: "data",
    //         repeatitems: false
    //         // page: "page.currPage",
    //         // total: "page.totalPage",
    //         // records: "page.totalResult"
    //     },
    //     // prmNames: {
    //     //     page: "currentPage",
    //     //     rows: "showCount",
    //     //     order: "order"
    //     // },
    //     treeGrid: true,  // 启用treeGrid树形表格
    //     treeGridModel: 'adjacency', // treeGrid所使用的数据结构方法,nested:嵌套集模型，: 邻接模型
    //     ExpandColumn: 'idstr', // 指定那列来展开tree grid，默认为第一列
    //
    //     treeReader: { // 扩展表格的colModel
    //         level_field: "level_field",  //  treeGrid等级字段，从0开始
    //         parent_id_field: "parentid",  // treeGrid父级id字段
    //         leaf_field: "leaf_field",  // 是否叶子节点字段o
    //         expanded_field: "expanded", //treeGrid是否展开字段
    //         loaded_field: true //
    //     },
    //     gridComplete: function () {
    //         //隐藏grid底部滚动条
    //         $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
    //     }
    // });
    // $(window).on('load', function () {
    //
    //     $('.selectpicker').selectpicker({
    //         'selectedText': 'cat',
    //         noneSelectedText: '请选择'
    //     });
    // });
$(function () {
    var prefix = ctx + "cMcurr/cMcurr"
    var options = {
        code: "id",
        parentCode: "parentid",
        uniqueId: "id",
        url: prefix + '/list',
        // createUrl: prefix + "/add/{id}",
        updateUrl: prefix + "/edit/{id}",
        removeUrl: prefix + "/remove/{id}",
        modalName: "微课堂",
        columns: [
            {
                field: 'selectItem',
                radio: true
            },
            // {label: '${column.columnComment}', name: 'id', index: 'id', width: 0, key: true, hidden: true},
            {
                title: '微课堂名称',
                field: 'mname',

            },
            {
                title: '任教老师',
                field: 'teachername',
            },

            {
                title: '创建时间',
                field: 'createtime',

            },

            {
                title: '是否审核通过',
                field: 'isexaminestr',
            },
            {
                title: '操作',
                field: '',
                formatter: function (value, row, index){
                    var object = encodeURI(JSON.stringify(row));

                    var button = '<button type="button" class="layui-btn  layui-btn-sm layui-btn-radius layui-btn-normal" onclick="edit(\'' + object + '\')">编辑</button>'
                    var button2 = '<button type="button" class="layui-btn  layui-btn-sm layui-btn-radius layui-btn-normal" onclick="del(\'' + row.id + '\')">删除</button>'
                    return button + button2;
                }
            },
        ]
    };
    $.treeTable.init(options);
    }
);

function op(cellvalue, options, cell) {

    var object = encodeURI(JSON.stringify(cell));

    var button = '<button type="button" class="layui-btn  layui-btn-sm layui-btn-radius layui-btn-normal" onclick="edit(\'' + object + '\')">编辑</button>'
    return button;
}

<!--修改页面-->
function edit(x) {
    var object = JSON.parse(decodeURI(x))
    var id = object.id
    if (id == null) {
        return;
    }
    $.ajax({
        url: baseURL + 'cMcurr/cMcurr/findByid',
        type: "GET",
        data: {
            id: id,
        },
        success: function (result) {
            vm.edit.id = result.data.id;
            vm.edit.mname = result.data.mname;
            vm.edit.teacherid = result.data.teacherid;
            vm.edit.teachername = result.data.teachername;
            vm.edit.createtime = result.data.createtime;
            vm.edit.isexamine = result.data.isexamine;
            vm.edit.parentid = result.data.parentid;
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
}

function del(x) {

    var object = JSON.parse(decodeURI(x))
    var id = object
    if (id == null) {
        return;
    }
    $.ajax({
        url: baseURL + 'cMcurr/cMcurr/del',
        type: "POST",
        // contentType: "application/json",
        data: {
            id: id,
        },
        success: function (result) {
            $.treeTable.refresh();
        }
    })
}




var vm = new Vue({
    el: '#rrapp',

    data: {
        q: {
            mname: null,
            teacherid: null,
            createtime: null,
            isexamine: null,
            parentid: null,
            remarks1: null,
            remarks2: null,
            remarks3: null,
        },
        showList: true,
        title: null,
        index: null,
        edit: {
            id: null,
            mname: null,
            teacherid: null,
            createtime: null,
            isexamine: null,
            parentid: null,
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
        ],
        teacherDropdown: [],
        mcurrDropdown: [
            {
                id: 0,
                name: '根目录',
            }
        ],
    },
    created: function () {
        // this.bindsearchdropdown();
        this.binddropdown();
        this.teacherList()
    },
    methods: {
        binddropdown: function () {
            //字典表下拉框
            // this.dropdown1 = BindDropDownControlsdy('sys_dict_data', 'dict_', 'dict_label', '起重机类型管理')
            // 普通下拉框
            // this.dropdown = BindDropDownControls('sys_dict_data', 'dict_type', 'dict_value');
            let drop = BindDropDownControls('c_mcurr', 'id', 'mname');
            for (let i=0;i<drop.length;i++) {

                this.mcurrDropdown.push(drop[i])
            }

            console.log(this.mcurrDropdown)
        },
        // 搜索下拉
        bindsearchdropdown: function () {
            BindDropDownControl('sys_user_sex', 'sys_dict_data', 'dict_value', 'dict_value')
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
                url: baseURL + 'cMcurr/cMcurr/findByid',
                type: "GET",
                data: {
                    id: id,
                },
                success: function (result) {
                    vm.edit.id = result.data.id;
                    vm.edit.mname = result.data.mname;
                    vm.edit.teacherid = result.data.teacherid;
                    vm.edit.teachername = result.data.teachername;
                    vm.edit.createtime = result.data.createtime;
                    vm.edit.isexamine = result.data.isexamine;
                    vm.edit.parentid = result.data.parentid;
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

            var url = id == null ? "cMcurr/cMcurr/adddata" : "cMcurr/cMcurr/updatedata";
            $.ajax({
                url: baseURL + url,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(vm.edit),
                success: function (result) {
                    $.treeTable.refresh();
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
                    'mname': vm.q.mname,
                    'teacherid': vm.q.teacherid,
                    'createtime': vm.q.createtime,
                    'isexamine': vm.q.isexamine,
                    'parentid': vm.q.parentid,
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
                        url: baseURL + "/cMcurr/cMcurr/del",
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
            vm.q.mname = null;
            vm.q.teacherid = null;
            vm.q.createtime = null;
            vm.q.isexamine = null;
            vm.q.parentid = null;
            vm.q.remarks1 = null;
            vm.q.remarks2 = null;
            vm.q.remarks3 = null;
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.mname = null;
            vm.edit.teacherid = null;
            vm.edit.createtime = null;
            vm.edit.isexamine = null;
            vm.edit.parentid = null;
            vm.edit.remarks1 = null;
            vm.edit.remarks2 = null;
            vm.edit.remarks3 = null;
            $("#editLayer")[0].reset();
            layer.closeAll();
            $("#jqGrid").trigger("reloadGrid");
        },
        teacherList: function () {
            $.ajax({
                type: "POST",
                url: "/system/user/getAllStudent",
                contentType: "application/json",
                data: JSON.stringify({
                    rolename: '教师'
                }),
                success: function (r) {

                    if (r.code == 0) {
                        vm.teacherDropdown = r.data
                    } else {

                    }

                }
            });
        },
    }
});