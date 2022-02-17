$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'cCurr/cCurr/selectCurrByMCurrId?mcurrid='+$('#mcurrId').val(),
        datatype: "json",
        colModel: [
            {label: '', name: 'id', index: 'id', width: 0, key: true, hidden: true},
            {label: '微课堂名称', name: 'mname', index: 'mname', width: 80},
            {label: '课堂名称', name: 'currname', index: 'currname', width: 80},
            // {label: '视频地址', name: 'videopath', index: 'videopath', width: 80},
            {label: '任教老师', name: 'teachername', index: 'teachername', width: 80},
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
    var ob = JSON.parse(decodeURI(object))
    var videopath = ob.videopath
    var loadVideo = "<video width='100%' height='100%' autoplay='autoplay' controls='controls' src="+videopath+">"
    layer.open({
        type:1,
        content:loadVideo,
        area:["90%","90%"]
    })
    $.ajax({
        url: baseURL + 'video/video/visitVideo',
        type: "POST",
        data: {
            currid: ob.id,
        },
        success: function (result) {

        }
    })

}

var vm = new Vue({
    el: '#rrapp',

    data: {
        q: {
            mcurr: '',
            currname: '',
            createtime: '',
            teacherid: '',
            isexamine: '',
            ordernum: '',
            id: '',
            currtime: '',
            videopath: '',
        },
        showList: true,
        title: null,
        index: null,
        edit: {
            mcurr: '',
            currname: '',
            createtime: '',
            teacherid: '',
            isexamine: '',
            ordernum: '',
            id: '',
            currtime: '',
            videopath: '',
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
            vm.upload();
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
                // btn: ['确定', '取消'],
                yes: function (id) {
                    // vm.saveOrUpdate(id);
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
            vm.q.mcurr = '';
            vm.q.currname = '';
            vm.q.createtime = '';
            vm.q.teacherid = '';
            vm.q.isexamine = '';
            vm.q.ordernum = '';
            vm.q.id = '';
            vm.q.currtime = '';
            vm.q.videopath = '';
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.mcurr = '';
            vm.edit.currname = '';
            vm.edit.createtime = '';
            vm.edit.teacherid = '';
            vm.edit.isexamine = '';
            vm.edit.ordernum = '';
            vm.edit.id = '';
            vm.edit.currtime = '';
            vm.edit.videopath = '';
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
                    url: baseURL + 'video/video/updatedata', //上传接口
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
        cancel: function (){
            vm.reload();
            layer.close(vm.index)
        },
        mcurrTest: function (){
            let mcurrid = $('#mcurrId').val()
            var url = baseURL + 'cStuTest/cStuTest/'+mcurrid
            $.modal.openTab("课堂测试", url);

        }
    }
});