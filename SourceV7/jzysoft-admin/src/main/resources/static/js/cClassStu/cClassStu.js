$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'cClassStu/cClassStu/list',
        datatype: "json",
        colModel: [
                                                {label: '${column.columnComment}',name: 'id',index: 'id',width: 0,key: true,hidden: true},
                                                                {label: '班级主键',name: 'classnum',index: 'classnum',width: 80}, 
                                                                {label: '学生主键',name: 'studnum',index: 'studnum',width: 80}
                            
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
                classnum:null,
                studnum:null,
    },
showList: true,
    title: null,
    index: null,
    edit: {
            id:null,
            classnum:null,
            studnum:null,
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
    binddropdown:function(){
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
            url: baseURL + 'cClassStu/cClassStu/findByid',
            type: "GET",
            data: {
        id: id,
            },
            success: function (result) {
                                    vm.edit.id= result.data.id;
                                    vm.edit.classnum= result.data.classnum;
                                    vm.edit.studnum= result.data.studnum;
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
        var url = id == null ? "cClassStu/cClassStu/adddata" : "cClassStu/cClassStu/updatedata";
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
                                                                                                'classnum':vm.q.classnum,
                                                                                'studnum':vm.q.studnum,
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
        var r=layer.confirm('您确定要删除该用户吗?',{btn: ['确定', '取消'],title:"提示"}, function() {
            if (r) {
            $.ajax({
                type: "POST",
                url: baseURL + "/cClassStu/cClassStu/del",
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
    reset:function () {
                                                        vm.q.classnum=null;
                                                vm.q.studnum=null;
                            $("#gen-form")[0].reset();
    },
    reload: function () {
                                                        vm.edit.classnum=null;
                                                vm.edit.studnum=null;
                            $("#editLayer")[0].reset();
        layer.closeAll();
        $("#jqGrid").trigger("reloadGrid");
    }
}
});