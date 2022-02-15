$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'cStuTest/cStuTest/list',
        datatype: "json",
        colModel: [
            {label: '${column.columnComment}', name: 'id', index: 'id', width: 0, key: true, hidden: true},
            {label: '试卷id', name: 'testid', index: 'testid', width: 80},
            {label: '学生id', name: 'stuid', index: 'stuid', width: 80},
            {label: '答案id', name: 'stuanswer', index: 'stuanswer', width: 80},
            {label: '创建时间', name: 'createtime', index: 'createtime', width: 80},
            {label: '备注1', name: 'remarks1', index: 'remarks1', width: 80},
            {label: '备注2', name: 'remarks2', index: 'remarks2', width: 80},
            {label: '备注3', name: 'remarks3', index: 'remarks3', width: 80}

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
    layui.use('form',function(){undefined
        var form = layui.form;

        //刷新界面 所有元素

        form.render();

    });
    layui.use(['layer', 'element','form','jquery'], function() {
        var $ = layui.jquery,
            layer = layui.layer,
            element = layui.element,
            form = layui.form;
        form.on('radio(wh1)', function(data){

            console.log(JSON.stringify(data))
            if (data.value == 'a'){
                layer.open({
                    title:'回答正确',
                    btn:'下一题',
                    content:"7月底，中共一大代表毛泽东、董必武、陈潭秋、王尽美、邓恩铭、李达等，由李达夫人王会悟做向导，从上海乘火车转移到嘉兴，再从狮子汇渡口登上渡船到湖心岛，最后转登王会悟预订的游船，并在游船中庄严宣告中国共产党的诞生。在船上，中共一大通过了党的第一个纲领和决议，正式宣告中国共产党庄严诞生。",
                    yes: function (index,layero){
                        $('#list1').hide();
                        $('#list2').show();
                        layer.close(index);
                        element.progress('demo', '6%');
                    }
                })
            }

        });
        form.render();
    })

});

var vm = new Vue({
    el: '#rrapp',

    data: {
        q: {
            testid: null,
            stuid: null,
            stuanswer: null,
            createtime: null,
            remarks1: null,
            remarks2: null,
            remarks3: null,
        },
        showList: true,
        title: null,
        index: null,
        edit: {
            id: null,
            testid: null,
            stuid: null,
            stuanswer: null,
            createtime: null,
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
        answerList: [],
    },
    created: function () {
        // this.bindsearchdropdown();
        // this.binddropdown();
        this.getAnswerList()
    },
    methods: {
        binddropdown: function () {
            //字典表下拉框
            // this.dropdown1 = BindDropDownControlsdy('sys_dict_data', 'dict_', 'dict_label', '起重机类型管理')
            // // 普通下拉框
            // this.dropdown = BindDropDownControls('customer', 'customer_id', 'username');
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
                url: baseURL + 'cStuTest/cStuTest/findByid',
                type: "GET",
                data: {
                    id: id,
                },
                success: function (result) {
                    vm.edit.id = result.data.id;
                    vm.edit.testid = result.data.testid;
                    vm.edit.stuid = result.data.stuid;
                    vm.edit.stuanswer = result.data.stuanswer;
                    vm.edit.createtime = result.data.createtime;
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
            var url = id == null ? "cStuTest/cStuTest/adddata" : "cStuTest/cStuTest/updatedata";
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
                    'testid': vm.q.testid,
                    'stuid': vm.q.stuid,
                    'stuanswer': vm.q.stuanswer,
                    'createtime': vm.q.createtime,
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
                        url: baseURL + "/cStuTest/cStuTest/del",
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
            vm.q.testid = null;
            vm.q.stuid = null;
            vm.q.stuanswer = null;
            vm.q.createtime = null;
            vm.q.remarks1 = null;
            vm.q.remarks2 = null;
            vm.q.remarks3 = null;
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.testid = null;
            vm.edit.stuid = null;
            vm.edit.stuanswer = null;
            vm.edit.createtime = null;
            vm.edit.remarks1 = null;
            vm.edit.remarks2 = null;
            vm.edit.remarks3 = null;
            $("#editLayer")[0].reset();
            layer.closeAll();
            $("#jqGrid").trigger("reloadGrid");
        },
        getAnswerList: function (){
            $.ajax({
                type: "POST",
                url: baseURL + "canswer/canswer/canswer",
                data: {
                    mclassid: 1
                },
                success: function (r) {
                    if (r.code == 0) {
                        vm.answerList = r.data
                        console.log(JSON.stringify(vm.answerList))
                    } else {
                        alert("删除失败")
                    }

                }
            });
        }
    }
});