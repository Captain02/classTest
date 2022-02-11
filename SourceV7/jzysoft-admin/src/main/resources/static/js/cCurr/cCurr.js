$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'cCurr/cCurr/list',
        datatype: "json",
        colModel: [
            {label: '${column.columnComment}', name: 'id', index: 'id', width: 0, key: true, hidden: true},
            {label: '课程名', name: 'currname', index: 'currname', width: 80},
            {label: '课程时长', name: 'currtime', index: 'currtime', width: 80},
            {label: '任教老师', name: 'teacherid', index: 'teacherid', width: 80},
            {label: '是否审核通过', name: 'isexamine', index: 'isexamine', width: 80},
            {label: '课程顺序', name: 'ordernum', index: 'ordernum', width: 80},
            {label: '属于哪个微课堂', name: 'mcurr', index: 'mcurr', width: 80},
            {label: '视频地址', name: 'videopath', index: 'videopath', width: 80},
            {label: '创建时间', name: 'createtime', index: 'createtime', width: 80},
            // {label: '备注一', name: 'remarks1', index: 'remarks1', width: 80},
            // {label: '备注二', name: 'remarks2', index: 'remarks2', width: 80},
            // {label: '备注三', name: 'remarks3', index: 'remarks3', width: 80}

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
            currname: null,
            currtime: null,
            teacherid: null,
            isexamine: null,
            ordernum: null,
            mcurr: null,
            videopath: null,
            createtime: null,
            remarks1: null,
            remarks2: null,
            remarks3: null,
        },
        showList: true,
        title: null,
        index: null,
        edit: {
            id: '',
            currname: '',
            currtime: '',
            teacherid: '',
            isexamine: '',
            ordernum: '',
            mcurr: '',
            videopath: '',
            createtime: '',
            remarks1: '',
            remarks2: '',
            remarks3: '',
        },
        dropdown: [
            {
                id: null,
                name: null,
            }
        ],
        teacherDropdown:[],
        mcurrDropdown: [
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
        this.binddropdown();
        this.teacherList()
        this.upload()
    },
    methods: {
        binddropdown: function () {
            //字典表下拉框
            // this.dropdown1 = BindDropDownControlsdy('sys_dict_data', 'dict_', 'dict_label', '起重机类型管理')
            // 普通下拉框
            // this.dropdown = BindDropDownControls('customer', 'customer_id', 'username');
            this.mcurrDropdown = BindDropDownControls('c_mcurr', 'id', 'mname');
        },
        // 搜索下拉
        // bindsearchdropdown: function () {
        //     BindDropDownControl('selectuserid', 'customer', 'customer_id', 'username')
        // },
        // 添加页面
        addPage: function () {
            // this.teacherList()
            vm.reload();
            vm.index = layer.open({
                type: 1,
                area: ['95%', '95%'],
                content: $('#editLayer'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                // btn: ['确定', '取消'],
                yes: function () {
                    // vm.saveOrUpdate();
                },
                btn2: function () {
                    vm.reload();
                }
            });
        },
        <!--修改页面-->
        editPage: function (x) {
            // this.teacherList()
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            $.ajax({
                url: baseURL + 'cCurr/cCurr/findByid',
                type: "GET",
                data: {
                    id: id,
                },
                success: function (result) {
                    vm.edit.id = result.data.id;
                    vm.edit.currname = result.data.currname;
                    vm.edit.currtime = result.data.currtime;
                    vm.edit.teacherid = result.data.teacherid;
                    vm.edit.isexamine = result.data.isexamine;
                    vm.edit.ordernum = result.data.ordernum;
                    vm.edit.mcurr = result.data.mcurr;
                    vm.edit.videopath = result.data.videopath;
                    vm.edit.createtime = result.data.createtime;
                    vm.edit.remarks1 = result.data.remarks1;
                    vm.edit.remarks2 = result.data.remarks2;
                    vm.edit.remarks3 = result.data.remarks3;
                    $('#teacherid').val(result.data.teacherid)
                    $('#mcurr').val(result.data.mcurr)
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
            var url = id == null ? "cCurr/cCurr/adddata" : "cCurr/cCurr/updatedata";
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
                    'currtime': vm.q.currtime,
                    'teacherid': vm.q.teacherid,
                    'isexamine': vm.q.isexamine,
                    'ordernum': vm.q.ordernum,
                    'mcurr': vm.q.mcurr,
                    'videopath': vm.q.videopath,
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
                        url: baseURL + "/cCurr/cCurr/del",
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
            vm.q.currname = null;
            vm.q.currtime = null;
            vm.q.teacherid = null;
            vm.q.isexamine = null;
            vm.q.ordernum = null;
            vm.q.mcurr = null;
            vm.q.videopath = null;
            vm.q.createtime = null;
            vm.q.remarks1 = null;
            vm.q.remarks2 = null;
            vm.q.remarks3 = null;
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.currname = '';
            vm.edit.currtime = '';
            vm.edit.teacherid = '';
            vm.edit.isexamine = '';
            vm.edit.ordernum = '';
            vm.edit.mcurr = '';
            vm.edit.videopath = '';
            vm.edit.createtime = '';
            vm.edit.remarks1 = '';
            vm.edit.remarks2 = '';
            vm.edit.remarks3 = '';
            $("#editLayer")[0].reset();
            layer.closeAll();
            $("#jqGrid").trigger("reloadGrid");
        },
        upload: function () {
            layui.use('upload', function () {
                var upload = layui.upload;

                //执行实例
                var uploadInst = upload.render({
                    elem: '#test1', //绑定元素
                    url: baseURL + 'cCurr/cCurr/adddata', //上传接口
                    bindAction: '#confirm',
                    auto: false,//选择文件后不自动上传
                    accept: 'video',
                    acceptMime: 'video/*',
                    // data: {
                    //
                    //     currtime: $('#currtime').val(),
                    //     teacherid: $('#teacherid').val(),
                    //     ordernum: $('#ordernum').val(),
                    //     mcurr: $('#mcurr').val(),
                    //     currname: $('#currname').val(),
                    // },
                    data: vm.edit,
                    before: function(obj){//obj参数包含的信息，跟 choose回调完全一致，可参见上文。
                        layer.load(); //上传loading
                        // debugger
                        // console.log($('#currname').val())
                        // console.log($('#currtime').val())
                        // console.log($('#teacherid').val())
                        // console.log($('#ordernum').val())
                        // console.log($('#mcurr').val())
                    },
                    choose: function (obj) {

                        //将每次选择的文件追加到文件队列
                        var files = obj.pushFile();
                        obj.preview(function (index, file, result) {
                            console.log(index); //得到文件索引
                            console.log(file); //得到文件对象
                            $('#videopath').val(file.name)
                        })

                    },
                    done: function (res) {
                        console.log(res)
                        layer.closeAll('loading'); //关闭loading
                        vm.reload();
                        layer.close(vm.index)
                    }
                    , error: function (res) {
                        console.log(res)
                        layer.closeAll('loading'); //关闭loading
                        //请求异常回调
                    }
                });
            });
        },
        teacherList: function (){
            $.ajax({
                type: "POST",
                url:  "/system/user/getAllStudent",
                contentType: "application/json",
                data: JSON.stringify({
                    rolename: '教师'
                }),
                success: function (r) {

                    console.log(JSON.stringify(r.data))
                    if (r.code == 0) {
                        vm.teacherDropdown = r.data
                    } else {

                    }

                }
            });
        },
        cancel: function (){
            vm.reload();
            layer.close(vm.index)
        }
    }
});