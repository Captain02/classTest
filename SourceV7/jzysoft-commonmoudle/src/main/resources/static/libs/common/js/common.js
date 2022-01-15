//jqGrid的配置信息
$.jgrid.defaults.width = 1000;
$.jgrid.defaults.responsive = true;
$.jgrid.defaults.styleUI = 'Bootstrap';

var baseURL = "../../";

//工具集合Tools
window.T = {};

// 获取请求参数
// 使用示例
// location.href = http://localhost:8080/index.html?id=123
// T.p('id') --> 123;
var url = function (name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
};
T.p = url;

//全局配置
$.ajaxSetup({
    dataType: "json",
    cache: false
});

// //重写alert
// window.alert = function(msg, callback){
// 	parent.layer.alert(msg, function(index){
// 		parent.layer.close(index);
// 		if(typeof(callback) === "function"){
// 			callback("ok");
// 		}
// 	});
// }
//
// //重写confirm式样框
// window.confirm = function(msg, callback){
// 	parent.layer.confirm(msg, {btn: ['确定','取消']},
// 	function(){//确定事件s
// 		if(typeof(callback) === "function"){
// 			callback("ok");
// 		}
// 	});
// }
/*----------------------------------------------------------jqgrid操作函数---------------------------------------------------------*/
//选择一条记录
function getSelectedRow() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }

    var selectedIDs = grid.getGridParam("selarrrow");
    if (selectedIDs.length > 1) {
        alert("只能选择一条记录");
        return;
    }

    return selectedIDs[0];
}

//选择多条记录
function getSelectedRows() {
    var grid = $("#jqGrid");
    var rowKey = grid.getGridParam("selrow");
    if (!rowKey) {
        alert("请选择一条记录");
        return;
    }

    return grid.getGridParam("selarrrow");
}


//判断是否为空
function isBlank(value) {
    return !value || !/\S/.test(value)
}

//获取地址栏传参
function getQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return (r[2]);
    return null;
}

//获得行数据
function getRowsDatas(rowid) {
    var rowData = $("#jqGrid").jqGrid('getRowData', rowid);

    return rowData;
}
/*----------------------------------------------------------jqgrid操作函数---------------------------------------------------------*/
/*-------------------------------------------------下拉框操作函数--------------------------------------------------------------*/
function BindDropDownControlData(controlid, data) {
    debugger
    var str = '<option value="null" style="margin-left: 20px" selected="selected">      全部</option> ';
    // 循环
    // for (var i = vm.dropdown.length - 1; i >= 0; i--) {
    for (var i = 0; i < data.length; i++) {
        str += '<option value="' + data[i].id + '">' + data[i].name + '</option>'
        // 获得用户表第2个id和username
    }
    var ddlcontrol = $("#" + controlid);
    ddlcontrol.html(str);
    // ddlcontrol.html();
    ddlcontrol.selectpicker('refresh');//动态刷新
}

function BindDropDownControl(controlid, tablename, valuefieldname, namefieldname) {
    debugger
    $.ajax({
        url: baseURL + "/common/GetCommonDropDownData/" + tablename + "/" + valuefieldname + "/" + namefieldname,
        type: "GET",
        data: {},
        success: function (result) {
            BindDropDownControlData(controlid, result.data);

        }
    })
}

function BindDropDownControlJZ(controlid, tablename, valuefieldname, namefieldname) {
    debugger
    $.ajax({
        url: baseURL + "/common/GetCommonDropDownDatas/" + tablename + "/" + valuefieldname + "/" + namefieldname,
        type: "GET",
        data: {},
        success: function (result) {
            BindDropDownControlData(controlid, result.data);

        }
    })
}

function BindDropDownControls(tablename, valuefieldname, namefieldname) {
    var self = this;
    var selectModuleValue = null
    $.ajax({
        url: baseURL + "/common/GetCommonDropDownData/" + tablename + "/" + valuefieldname + "/" + namefieldname,
        type: "GET",
        async: false,
        data: {},
        success: function (result) {
            console.log(result.data)
            selectModuleValue = result.data;
        }
    })
    return selectModuleValue;
}

function BindDropDownControlsdy(tablename, valuefieldname, namefieldname, dict_type) {
    var self = this;
    var selectModuleValue = null
    $.ajax({
        url: baseURL + "/common/GetCommonDropDownData/" + tablename + "/" + valuefieldname + "/" + namefieldname + "/" + dict_type,
        type: "GET",
        async: false,
        data: {},
        success: function (result) {
            console.log(result.data)
            selectModuleValue = result.data;
        }
    })
    return selectModuleValue;
}

function GetCommonDropDownById(tablename, valuefieldname, namefieldname,key,value) {
    var self = this;
    var selectModuleValue = null
    $.ajax({
        url: baseURL + "/common/GetCommonDropDownById/" + tablename + "/" + valuefieldname + "/" + namefieldname + "/" + key + "/" + value,
        type: "GET",
        async: false,
        data: {},
        success: function (result) {
            console.log(result.data)
            selectModuleValue = result.data;
        }
    })
    return selectModuleValue;
}

function BindDropDownControlsdyx(tablename, valuefieldname, namefieldname, dict_type) {

    var self = this;
    var selectModuleValue = null
    $.ajax({
        url: baseURL + "/common/GetCommonDropDownDatadyx/" + tablename + "/" + valuefieldname + "/" + namefieldname + "/" + dict_type,
        type: "GET",
        async: false,
        data: {},
        success: function (result) {
            console.log(result.data)
            selectModuleValue = result.data;
        }
    })
    return selectModuleValue;
}
/*--------------------------------------------------提示信息----------------------------------------------------------------------*/
/* 提示成功，页面不迁移*/
function ajaxSuccess(infor) {

    layer.alert('<font color=blue>' + infor + '</font>', {
        icon : 1,
        skin : 'layer-ext-moon', // 该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
        title : '提示信息'
    });
}

// 提示错误信息
function ajaxError(infor) {
    layer.alert('<font color=red>' + infor + '</font>', {
        icon : 2,
        skin : 'layer-ext-moon', // 该皮肤由layer.seaning.com友情扩展。关于皮肤的扩展规则，去这里查阅
        title : '提示信息'
    });
}
window.alert = function (msg)
{
    layer.alert(msg, {
        title : '提示信息'
    });
}