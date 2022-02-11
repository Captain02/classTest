$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'video/video/list',
        datatype: "json",
        colModel: [
            {label: '', name: 'id', index: 'id', width: 0, key: true, hidden: true},
            {label: '微课堂名称', name: 'mname', index: 'mname', width: 80},
            {label: '课堂名称', name: 'currname', index: 'currname', width: 80},
            {label: '视频地址', name: 'videopath', index: 'videopath', width: 80},

            {label: '操作', name: '', index: '', width: 80,formatter: showName},


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
function showName( cellvalue, options, cell ) {

    var object = encodeURI(JSON.stringify(cell));

    var button = '<button type="button" class="layui-btn  layui-btn-sm layui-btn-radius layui-btn-normal" onclick="showVideo(this,\''+object+'\')">查看</button>'
    return  button ;
}

function showVideo(val,object){
    debugger
    var ob = JSON.parse(decodeURI(object))
    var videopath = ob.videopath
    var loadVideo = "<video width='100%' height='100%' autoplay='autoplay' controls='controls' src="+videopath+">"
    layer.open({
        type:1,
        content:loadVideo,
        area:["90%","90%"]
    })
}

var vm = new Vue({
    el: '#rrapp',

    data: {
        q: {
            mcurr: null,
            currname: null,
            createtime: null,
            teacherid: null,
            isexamine: null,
            ordernum: null,
            id: null,
            currtime: null,
            videopath: null,
        },
        showList: true,
        title: null,
        index: null,
        edit: {
            mcurr: null,
            currname: null,
            createtime: null,
            teacherid: null,
            isexamine: null,
            ordernum: null,
            id: null,
            currtime: null,
            videopath: null,
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
            this.dropdown = BindDropDownControls('sys_dict_data', 'dict_type', 'dict_value');
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
                url: baseURL + 'video/video/findByid',
                type: "GET",
                data: {
                    id: id,
                },
                success: function (result) {
                    vm.edit.id = result.data.id;
                    vm.edit.videopath = result.data.videopath;

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
            var url = id == null ? "video/video/adddata" : "video/video/updatedata";
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
                    'currname': vm.q.currname,

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
                        url: baseURL + "/video/video/del",
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
            vm.q.mcurr = null;
            vm.q.currname = null;
            vm.q.createtime = null;
            vm.q.teacherid = null;
            vm.q.isexamine = null;
            vm.q.ordernum = null;
            vm.q.id = null;
            vm.q.currtime = null;
            vm.q.videopath = null;
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.mcurr = null;
            vm.edit.currname = null;
            vm.edit.createtime = null;
            vm.edit.teacherid = null;
            vm.edit.isexamine = null;
            vm.edit.ordernum = null;
            vm.edit.id = null;
            vm.edit.currtime = null;
            vm.edit.videopath = null;
            $("#editLayer")[0].reset();
            layer.closeAll();
            $("#jqGrid").trigger("reloadGrid");
        }
    }
});