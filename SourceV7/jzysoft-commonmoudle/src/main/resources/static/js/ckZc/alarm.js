$(function () {
    jqgrid();
    histogram();

});

function jqgrid() {
    $("#jqGrid").jqGrid({
        url: baseURL + 'zhxfDeviceAlarmRecord/zhxfDeviceAlarmRecord/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'ID', index: 'ID', width: 0, key: true, hidden: true},
            {label: '类型', name: 'alarmType', index: 'alarmType', width: 80},
            {label: '报警时间', name: 'upload_time', index: 'upload_time', width: 80},
            {label: '联网单位', name: 'CkName', index: 'CkName', width: 80},
            {label: '监控对象', name: 'JzName', index: 'JzName', width: 80},
            {label: '报警次数', name: 'num', index: 'num', width: 80},
            {label: '状态', name: 'deal_flag', index: 'deal_flag', width: 80},
            {
                label: '操作', name: 'JzzcId', index: 'JzzcId', width: 80, align: 'center',
                formatter: function (cellValue, grid, rows, state) {
                    debugger
                    return "<a href=\"javascript:void(0)\" onclick=\"showDetailOrder(" + rows.JzzcId + ")\" >查看</a>"
                }
            }

        ],
        viewrecords: true,
        height: "100%",
        rowNum: 15,
        rowList: [15, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        loadui: "Disable",
        //multiboxonly:true,
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
            // $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "scroll"});
        },
        beforeSelectRow: function () {
            $("#jqGrid").jqGrid('resetSelection');
            return (true);
        }
    });
    $(window).on("load", function () {

        $(window).on('load', function () {

            // $('.selectpicker').selectpicker({
            //     'selectedText': 'cat',
            //     noneSelectedText: '请选择',
            //     width: '500px',
            // });

        });
        /* page_resize();
         $(window).resize(function(){
             setTimeout("page_resize()", 10);
         });*/
    });
}


// 操作按钮
function showDetailOrder(JzzcId) {
    debugger

    var index = layer.open({
        title:'视频播放',
        type: 2,
        area: ['90%', '90%'],
        scrollbar: false,
        content: '/alarmmonitor/alarmmonitor?JzzcId=' + JzzcId,
        closeBtn:1
    });
}


var vm = new Vue({
    el: '#rrapp',

    data: {
        backdata: null,
        q: {
            ID: null,
            ProjectDirectoryID: 0,
            ProjectName: null,
            ParentID: null,
            LocalPath: null,
            NetworkPath: null,
            JZZCID: null,
            CJTime: null,
            LayoutFlag: null,
            RadarChannelNum: null,
            CameraEnabled: null,
            VideoFileCount: null,
            VideoAEnabled: null,
            VideoBEnabled: null,
            VideoCEnabled: null,
            VideoDEnabled: null,
            GPSEnabled: null,
            CreateDate: null,
            UpdateDate: null,
            AddUserID: null,
            DeptID: null,
            Memo: null,
            deptId: null,
        },
        showList: true,
        title: null,
        index: null,
        basicfile: null,
        layeredit: {
            ProjectDirectoryID: null,
            ProjectName: null,
            LocalPath: null,
            CJTime: null,
            LayoutFlag: null,
            RadarChannelNum: null,
            CameraEnabled: null,
            VideoFileCount: null,
            VideoAEnabled: null,
            VideoBEnabled: null,
            VideoCEnabled: null,
            VideoDEnabled: null,
            GPSEnabled: null,
        },
        edit: {
            ID: null,
            ProjectDirectoryID: null,
            ProjectName: null,
            ParentID: null,
            LocalPath: null,
            NetworkPath: null,
            CJTime: null,
            LayoutFlag: null,
            RadarChannelNum: null,
            CameraEnabled: null,
            VideoFileCount: null,
            VideoAEnabled: null,
            VideoBEnabled: null,
            VideoCEnabled: null,
            VideoDEnabled: null,
            GPSEnabled: null,
            CreateDate: null,
            UpdateDate: null,
            AddUserID: null,
            DeptID: null,
            Memo: null,


            ID: null,
            f52_code: null,
            controller_code: null,
            ckzclxid: null,
            device_code: null,
            event_id: null,
            device_atr: null,
            device_value: null,
            upload_time: null,
            memo: null,
            deal_flag: null,
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
        openOrclose: [],
    },
    created: function () {
        this.binddropdown();
        this.bindsearchdropdown();
    },
    methods: {
        ceshi: function () {
            alert("测试")
        },
        conmmitDataInfo: function () {
            vm.layeredit.ProjectDirectoryID = vm.q.ProjectDirectoryID
            vm.layeredit.backdata = vm.backdata
            if (vm.layeredit.ProjectDirectoryID == 0 || vm.layeredit.ProjectDirectoryID == null) {
                alert('请选择上级目录')
                window.location.reload()
                return
            }
            $.ajax({
                url: baseURL + '/rsProjectProjectinfo/rsProjectProjectinfo/conmmitDataInfo',
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(vm.layeredit),
                success: function (result) {
                    console.log(result)
                    vm.reload()
                }
            })
        },
        submitFile: function () {
            $.ajax({
                url: baseURL + 'rsProjectProjectinfo/rsProjectProjectinfo/singleUpload/',
                type: "POST",
                data: {
                    url: vm.basicfile
                },
                success: function (result) {
                    console.log(result)
                }
            })
        },
        binddropdown: function () {
            //字典表下拉框
            // this.dropdown1 = BindDropDownControlsdy('sys_dict_data', 'dict_', 'dict_label', '起重机类型管理')
            // // 普通下拉框
            this.dropdown = BindDropDownControls('sys_dept', 'dept_id', 'dept_name');
            // this.openOrclose = BindDropDownControlsdy('sys_dict_data', 'dict_value', 'dict_label', '开启或关闭')
        },
        // 搜索下拉
        bindsearchdropdown: function () {
            // BindDropDownControl('selectuserids', 'rs_project_projectinfo', 'ID', 'ProjectName')
        },
        selectDataById: function (x) {
            // alert(x)
            vm.q.JZZCID = x
            vm.queryProjectDirectoryID();

        },
        // 搜索
        queryProjectDirectoryID: function () {
            histogram(vm.q.JZZCID, vm.q.deptId);
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'ProjectName': vm.q.ProjectName,
                    'JZZCID': vm.q.JZZCID
                },
                page: page
            }).trigger("reloadGrid");
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
                url: baseURL + 'rsProjectProjectinfo/rsProjectProjectinfo/findByid',
                type: "GET",
                data: {
                    ID: id,
                },
                success: function (result) {
                    vm.edit.ID = result.data.ID;
                    vm.edit.ProjectName = result.data.ProjectName;
                    vm.edit.ParentID = result.data.ParentID;
                    vm.edit.LocalPath = result.data.LocalPath;
                    vm.edit.NetworkPath = result.data.NetworkPath;
                    vm.edit.CJTime = result.data.CJTime;
                    vm.edit.LayoutFlag = result.data.LayoutFlag;
                    vm.edit.RadarChannelNum = result.data.RadarChannelNum;
                    vm.edit.CameraEnabled = result.data.CameraEnabled;
                    vm.edit.VideoFileCount = result.data.VideoFileCount;
                    vm.edit.VideoAEnabled = result.data.VideoAEnabled;
                    vm.edit.VideoBEnabled = result.data.VideoBEnabled;
                    vm.edit.VideoCEnabled = result.data.VideoCEnabled;
                    vm.edit.VideoDEnabled = result.data.VideoDEnabled;
                    vm.edit.GPSEnabled = result.data.GPSEnabled;
                    vm.edit.CreateDate = result.data.CreateDate;
                    vm.edit.UpdateDate = result.data.UpdateDate;
                    vm.edit.AddUserID = result.data.AddUserID;
                    vm.edit.DeptID = result.data.DeptID;
                    vm.edit.Memo = result.data.Memo;
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
            var url = "rsProjectProjectinfo/rsProjectProjectinfo/updatedata";
            $.ajax({
                url: baseURL + url,
                type: "POST",
                contentType: "application/json",
                data: JSON.stringify(vm.layeredit),
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
            histogram(vm.q.JZZCID, vm.q.deptId);
            debugger
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {

                    'ProjectName': vm.q.ProjectName,
                    'ID': $("#selectuserids").val(),
                    'ParentID': vm.q.ParentID,
                    'LocalPath': vm.q.LocalPath,
                    'NetworkPath': vm.q.NetworkPath,
                    'CJTime': vm.q.CJTime,
                    'LayoutFlag': vm.q.LayoutFlag,
                    'RadarChannelNum': vm.q.RadarChannelNum,
                    'CameraEnabled': vm.q.CameraEnabled,
                    'VideoFileCount': vm.q.VideoFileCount,
                    'VideoAEnabled': vm.q.VideoAEnabled,
                    'VideoBEnabled': vm.q.VideoBEnabled,
                    'VideoCEnabled': vm.q.VideoCEnabled,
                    'VideoDEnabled': vm.q.VideoDEnabled,
                    'GPSEnabled': vm.q.GPSEnabled,
                    'CreateDate': vm.q.CreateDate,
                    'UpdateDate': vm.q.UpdateDate,
                    'AddUserID': vm.q.AddUserID,
                    'DeptID': vm.q.DeptID,
                    'Memo': vm.q.Memo,
                    'deptId': vm.q.deptId,
                },
                page: page
            }).trigger("reloadGrid");
        },
        // 删除

        // 重置
        reset: function () {
            vm.q.ProjectName = null;
            vm.q.ParentID = null;
            vm.q.LocalPath = null;
            vm.q.NetworkPath = null;
            vm.q.CJTime = null;
            vm.q.LayoutFlag = null;
            vm.q.RadarChannelNum = null;
            vm.q.CameraEnabled = null;
            vm.q.VideoFileCount = null;
            vm.q.VideoAEnabled = null;
            vm.q.VideoBEnabled = null;
            vm.q.VideoCEnabled = null;
            vm.q.VideoDEnabled = null;
            vm.q.GPSEnabled = null;
            vm.q.CreateDate = null;
            vm.q.UpdateDate = null;
            vm.q.AddUserID = null;
            vm.q.DeptID = null;
            vm.q.deptId = null;
            vm.q.Memo = null;
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.ProjectName = null;
            vm.edit.ParentID = null;
            vm.edit.LocalPath = null;
            vm.edit.NetworkPath = null;
            vm.edit.CJTime = null;
            vm.edit.LayoutFlag = null;
            vm.edit.RadarChannelNum = null;
            vm.edit.CameraEnabled = null;
            vm.edit.VideoFileCount = null;
            vm.edit.VideoAEnabled = null;
            vm.edit.VideoBEnabled = null;
            vm.edit.VideoCEnabled = null;
            vm.edit.VideoDEnabled = null;
            vm.edit.GPSEnabled = null;
            vm.edit.CreateDate = null;
            vm.edit.UpdateDate = null;
            vm.edit.AddUserID = null;
            vm.edit.DeptID = null;
            vm.edit.Memo = null;
            vm.q.deptd = null;
            // $("#editLayer")[0].reset();
            layer.closeAll();
            $("#jqGrid").trigger("reloadGrid");
        }
    }
});

//   折线图
function histogram(x, y) {
    debugger

    var rllfx = echarts.init(document.getElementById("chart1"));
    var option = {
        // 标题
        title: {
            text: '近七天报警',
            // subtext: '数据'
        },
        /* 线条颜色，可设置多个颜色 */
        color: ['#ff794f', '#f6ff62', '#ffa829', '#ffc68c', '#3a5f2c'],
        legend: {
            data: ['火警', '预警', '报警', '故障', '反馈']
        },

        /* 鼠标悬浮时显示数据 */
        tooltip: {
            trigger: 'axis',
            // axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            //     type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
            // }
        },
        xAxis: {
            type: 'category',
            data: [],
            boundaryGap: false,
            //设置轴线的属性
            // axisLine: {
            //     lineStyle: {
            //         color: '#0a0a0a',
            //     }
            // },
            //调整x轴的lable
            // axisLabel: {
            //     textStyle: {
            //         fontSize: 10 // 让字体变小
            //     }
            // }
        },
        yAxis: {
            type: 'value',
            // 控制网格线是否显示
            splitLine: {
                show: true,
                //  改变轴线颜色
                lineStyle: {
                    // 使用深浅的间隔色
                    color: ['#132a6e']
                }
            },
            //设置轴线的属性
            axisLine: {
                lineStyle: {
                    color: '#ec19b7',
                }
            }
        },
        /* 数据配置，若有多条折线则在数组中追加{name: , data: } */
        series: [{
            name: '火警',
            data: [],
            type: 'line',
            symbol: 'circle',
            // 设置折点大小
            symbolSize: 10,
            // 设置为光滑曲线
            // smooth: true
        }, {
            name: '预警',
            data: [],
            type: 'line',
            symbol: 'circle',
            // 设置折点大小
            symbolSize: 10,
            // 设置为光滑曲线
            // smooth: true
        }, {
            name: '报警',
            data: [],
            type: 'line',
            symbol: 'circle',
            // 设置折点大小
            symbolSize: 10,
            // 设置为光滑曲线
            // smooth: true
        }, {
            name: '故障',
            data: [],
            type: 'line',
            symbol: 'circle',
            // 设置折点大小
            symbolSize: 10,
            // 设置为光滑曲线
            // smooth: true
        }, {
            name: '反馈',
            data: [],
            type: 'line',
            symbol: 'circle',
            // 设置折点大小
            symbolSize: 10,
            // 设置为光滑曲线
            // smooth: true
        },]
    };
    rllfx.showLoading();
    var nowDate = [];    //类别数组（实际用来盛放X轴坐标值）
    var fireAlarm = [];    //火警数组（实际用来盛放Y坐标值）
    var earlyAlarm = [];    //预警
    var callAlarm = [];    //报警
    var fault = [];         //故障
    var callBack = [];    //反馈
    $.ajax({
        type: "GET",
        async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
        url: baseURL + 'zhxfDeviceAlarmRecord/zhxfDeviceAlarmRecord/alarmById?JZZCID=' + x + '&deptId=' + y + '&currentPage=1&showCount=999999',  //请求发送到TestServlet处
        data: {},    //返回数据形式为json
        success: function (result) {
            console.log(result.data)
            debugger
            //请求成功时执行该函数内容，result即为服务器返回的json对象
            if (result) {
                for (var i = 0; i < result.data.length; i++) {
                    nowDate.push(result.data[i].nowDate);    //挨个取出类别并填入类别数组
                    fireAlarm.push(result.data[i].fireAlarm);   //火警数组
                    earlyAlarm.push(result.data[i].earlyAlarm);  //预警
                    callAlarm.push(result.data[i].callAlarm);   //报警
                    fault.push(result.data[i].fault);            //故障
                    callBack.push(result.data[i].callBack);    //反馈
                }
                rllfx.hideLoading();    //隐藏加载动画
                rllfx.setOption({        //加载数据图表
                    xAxis: {
                        color: '#FFFF00',
                        data: nowDate,
                        // axisLabel: {
                        //     interval: 0,
                        //     rotate: 80
                        // }

                    },
                    yAxis: {
                        // type: 'value'
                    },
                    series: [{
                        // 根据名字对应到相应的系列
                        name: '火警',
                        type: 'line',
                        data: fireAlarm
                    }, {
                        // 根据名字对应到相应的系列
                        name: '预警',
                        type: 'line',
                        data: earlyAlarm
                    }, {
                        // 根据名字对应到相应的系列
                        name: '报警',
                        type: 'line',
                        data: callAlarm
                    }, {
                        // 根据名字对应到相应的系列
                        name: '故障',
                        type: 'line',
                        data: fault
                    }, {
                        // 根据名字对应到相应的系列
                        name: '反馈',
                        type: 'line',
                        data: callBack
                    }]
                });

            }

        },
        error: function (errorMsg) {
            //请求失败时执行该函数
            alert("图表请求数据失败!");
            service1.hideLoading();
        }
    })
    rllfx.setOption(option, true);


}