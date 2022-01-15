$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'ckZc/ckzc/list',
        datatype: "json",
        colModel: [
            {label: 'ID', name: 'ID', index: 'ID', width: 0, key: true, hidden: true},
            {
                label: '名称',
                name: 'Name',
                index: 'Name',
                width: 150,
                loaded: true,
                formatter: function (value, grid, rows, state) {
                    debugger
                    var flag = '';
                    if (rows.isSelecttrue) {
                        flag = 'checked';
                        idArr.push(rows.ID);
                    }
                    var row = JSON.stringify(rows).replace(/"/g, '"');
                    return '<input type="checkbox" ' + flag + ' value=' + rows.ID + ' onclick="RelativeTreeGridCheck(' + rows.ID + ',' + rows.level + ',this)" />'
                        + rows.Name;

                }
            },
            {label: '内部序号', name: 'InOrder', index: 'InOrder', width: 80},
            {label: '编号', name: 'Code', index: 'Code', width: 80},
            {label: '全称', name: 'FullName', index: 'FullName', width: 80},
            {label: '所属测控组成ID', name: 'CKZCLXID', index: 'CKZCLXID', width: 80, hidden: true},
            {label: '所属建筑组成', name: 'JzName', index: 'JzName', width: 80},
            {label: '所属测控组成类型', name: 'CklxName', index: 'CklxName', width: 80},
            // {label: '所属ID', name: 'ParentID', index: 'ParentID', width: 80},
            // {label: '', name: 'ParentIDS', index: 'ParentIDS', width: 80},
            // {label: '所属部门ID', name: 'DeptID', index: 'DeptID', width: 80},
            // {label: '所属建筑组成ID', name: 'JZZCID', index: 'JZZCID', width: 80},
            // {label: '安装位置描述', name: 'AZWZ', index: 'AZWZ', width: 80},
            // {label: '经度', name: 'JD', index: 'JD', width: 80},
            // {label: '纬度', name: 'WD', index: 'WD', width: 80},
            // {label: '高度', name: 'GD', index: 'GD', width: 80},
            // {label: '树编号', name: 'Sid', index: 'Sid', width: 80},
            // {label: '树层级', name: 'cj', index: 'cj', width: 80},
            // {label: '建立人ID', name: 'CreateUserID', index: 'CreateUserID', width: 80},
            // {label: '更新时间', name: 'UpdateTime', index: 'UpdateTime', width: 80},
            // {label: '更新人ID', name: 'UpdateUserID', index: 'UpdateUserID', width: 80},
            // {label: '0:初始化;1:正在使用', name: 'ZT', index: 'ZT', width: 80},
            // {label: '是否可用', name: 'Enabled', index: 'Enabled', width: 80},
            // {label: '删除标志', name: 'DelFlag', index: 'DelFlag', width: 80},
            {label: '备注', name: 'Memo', index: 'Memo', width: 80}

        ],

        treeGrid: true,
        treeGridModel: "adjacency",
        ExpandColumn: "Name",

        viewrecords: true,
        height: 450,
        rowNum: 10,
        rowList: [10, 30, 50],
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
        treeReader: {
            //前面的四个参数需要在json中展示
            // level_field: "level",  //级别，主要是就是说展现出来是第几层，最高级是0，然后是1,2,3……
            // parent_id_field: "parent",  //用来标识哪个是父元素（需要时字符串格式：“parent”：“123”是可以的，但是如果是“parent”：123就错了）
            leaf_field: "is_leaf",  //是不是根节点，false表示这个不是最后的节点，true表示是最后的节点。（如果这个是最后的节点，但是设置的还是false，会发生一种情况，就是点击这个又重新加载了一遍数据）
            // expanded_field: "expanded", //是不是需要展开，false不展开，true展开
            loaded_field: true //
        },

        gridComplete: function () {
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});

        },

    });
    $(window).on('load', function () {

        $('.selectpicker').selectpicker({
            'selectedText': 'cat',
            noneSelectedText: '请选择'
        });
    });
});
$("#jqGrid input:checkbox:checked").each(function () {
    debugger
    idArr.push(Number($(this).val()))
});

function RelativeTreeGridCheck(row, leave, e) {
    debugger
    idArr = [];
    var rowData = $('#jqGrid').jqGrid('getRowData', row, true);
    vm.edit.ID = rowData.ID;

};

var vm = new Vue({
    el: '#rrapp',

    data: {
        layertitle: '添加',
        q: {
            InOrder: null,
            Code: null,
            Name: null,
            FullName: null,
            CKZCLXID: null,
            ParentID: null,
            ParentIDS: null,
            DeptID: null,
            JZZCID: null,
            AZWZ: null,
            JD: null,
            WD: null,
            GD: null,
            Sid: null,
            cj: null,
            CreateUserID: null,
            UpdateTime: null,
            UpdateUserID: null,
            ZT: null,
            Enabled: null,
            DelFlag: null,
            Memo: null,
        },
        showList: true,
        title: null,
        index: null,
        edit: {
            Pame: null,
            JzName: null,
            ID: null,
            InOrder: null,
            Code: null,
            Name: null,
            FullName: null,
            CKZCLXID: null,
            ParentID: null,
            ParentIDS: null,
            DeptID: null,
            JZZCID: null,
            AZWZ: null,
            JD: null,
            WD: null,
            GD: null,
            Sid: null,
            cj: null,
            CreateUserID: null,
            UpdateTime: null,
            UpdateUserID: null,
            ZT: null,
            Enabled: null,
            DelFlag: null,
            Memo: null,
            // 摄像机
            deviceid: null,
            nw_ip: null,
            nw_port: null,
            nw_web_port: null,
            ww_ip: null,
            ww_port: null,
            ww_web_port: null,
            username: null,
            password: null,
            td: null,
            rtsp_url: null,
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
            this.dropdown = BindDropDownControls('xt_ck_zclx_jc', 'ID', 'name');
        },
        // 搜索下拉
        bindsearchdropdown: function () {

        },
        // 测控组成树枝图
        selectDeptTree: function () {
            var options = {
                title: '选择建筑组成',
                width: "380",
                url: baseURL + "ckZc/ckzc/selectProjectTreejz/" + 1,
                callBack: vm.doSubmit
            };
            $.modal.openOptions(options);
        },
        doSubmit: function (index, layero) {
            debugger
            var tree = layero.find("iframe")[0].contentWindow.$._tree;
            var body = layer.getChildFrame('body', index);
            $("#treeId").val(body.find('#treeId').val());
            $("#treeName").val(body.find('#treeName').val());
            if ($("#treeId").val() == null) {
                alert("为空")
            }
            vm.edit.ParentID = $("#treeId").val();
            vm.edit.Pame = $("#treeName").val();

            layer.close(index);
        },
        // 建筑组成树枝图
        selectDeptTrees: function () {
            var options = {
                title: '选择测控组成',
                width: "380",
                url: baseURL + "xtJzZc/xtJzZc/selectProjectTreejz/" + 1,
                callBack: vm.doSubmits
            };
            $.modal.openOptions(options);
        },
        doSubmits: function (index, layero) {
            debugger
            var tree = layero.find("iframe")[0].contentWindow.$._tree;
            var body = layer.getChildFrame('body', index);
            $("#treeIds").val(body.find('#treeId').val());
            $("#treeNames").val(body.find('#treeName').val());
            if ($("#treeIds").val() == null) {
                alert("为空")
            }
            vm.edit.JZZCID = $("#treeIds").val();
            vm.edit.JzName = $("#treeNames").val();

            layer.close(index);
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
            var id = vm.edit.ID;
            if (id == null) {
                return;
            }
            $.ajax({
                url: baseURL + 'ckZc/ckzc/findByid',
                type: "GET",
                data: {
                    ID: id,
                },
                success: function (result) {
                    vm.edit.ID = result.data.ID;
                    vm.edit.InOrder = result.data.InOrder;
                    vm.edit.Code = result.data.Code;
                    vm.edit.Name = result.data.Name;
                    vm.edit.FullName = result.data.FullName;
                    vm.edit.CKZCLXID = result.data.CKZCLXID;
                    vm.edit.ParentID = result.data.ParentID;
                    vm.edit.ParentIDS = result.data.ParentIDS;
                    vm.edit.DeptID = result.data.DeptID;
                    vm.edit.JZZCID = result.data.JZZCID;
                    vm.edit.AZWZ = result.data.AZWZ;
                    vm.edit.JD = result.data.JD;
                    vm.edit.WD = result.data.WD;
                    vm.edit.GD = result.data.GD;
                    vm.edit.Sid = result.data.Sid;
                    vm.edit.cj = result.data.cj;
                    vm.edit.CreateUserID = result.data.CreateUserID;
                    vm.edit.UpdateTime = result.data.UpdateTime;
                    vm.edit.UpdateUserID = result.data.UpdateUserID;
                    vm.edit.ZT = result.data.ZT;
                    vm.edit.Enabled = result.data.Enabled;
                    vm.edit.DelFlag = result.data.DelFlag;
                    vm.edit.Memo = result.data.Memo;
                    vm.edit.Pame = result.data.Pame;
                    vm.edit.JzName = result.data.JzName;
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
            var url = id == null ? "deviceCamera/devicecamera/adddata" : "ckZc/ckzc/updatedata";
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
                    'FullName': vm.q.FullName,
                    'CKZCLXID': vm.q.CKZCLXID,
                    'ParentID': vm.q.ParentID,
                    'ParentIDS': vm.q.ParentIDS,
                    'DeptID': vm.q.DeptID,
                    'JZZCID': vm.q.JZZCID,
                    'AZWZ': vm.q.AZWZ,
                    'JD': vm.q.JD,
                    'WD': vm.q.WD,
                    'GD': vm.q.GD,
                    'Sid': vm.q.Sid,
                    'cj': vm.q.cj,
                    'CreateUserID': vm.q.CreateUserID,
                    'UpdateTime': vm.q.UpdateTime,
                    'UpdateUserID': vm.q.UpdateUserID,
                    'ZT': vm.q.ZT,
                    'Enabled': vm.q.Enabled,
                    'DelFlag': vm.q.DelFlag,
                    'Memo': vm.q.Memo,

                },
                page: page
            }).trigger("reloadGrid");
        },
        // 删除
        del: function (event) {
            debugger
            var id = vm.edit.ID;

            if (id == null) {
                return;
            }
            layer.confirm('您确定要删除该建筑组成吗?', {btn: ['确定', '取消'], title: "提示"}, function () {

                $.ajax({
                    url: baseURL + "/ckZc/ckzc/del",
                    type: "GET",
                    data: {
                        ID: id,
                    },
                    success: function (result) {
                        if (result.data.msg == false) {
                            layer.alert("存在下级不允许删除");
                        } else {
                            vm.reload();
                        }

                    }
                });

            });
        },
        // 重置
        reset: function () {
            vm.q.InOrder = null;
            vm.q.Code = null;
            vm.q.Name = null;
            vm.q.JzName = null;
            vm.q.Pame = null;
            vm.q.FullName = null;
            vm.q.CKZCLXID = null;
            vm.q.ParentID = null;
            vm.q.ParentIDS = null;
            vm.q.DeptID = null;
            vm.q.JZZCID = null;
            vm.q.AZWZ = null;
            vm.q.JD = null;
            vm.q.WD = null;
            vm.q.GD = null;
            vm.q.Sid = null;
            vm.q.cj = null;
            vm.q.CreateUserID = null;
            vm.q.UpdateTime = null;
            vm.q.UpdateUserID = null;
            vm.q.ZT = null;
            vm.q.Enabled = null;
            vm.q.DelFlag = null;
            vm.q.Memo = null;
            // 照相机
            vm.q.nw_ip = null;
            vm.q.nw_port = null;
            vm.q.nw_web_port = null;
            vm.q.ww_ip = null;
            vm.q.ww_port = null;
            vm.q.ww_web_port = null;
            vm.q.username = null;
            vm.q.password = null;
            vm.q.td = null;
            vm.q.rtsp_url = null;
            vm.q.memo = null;
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.InOrder = null;
            vm.edit.Code = null;
            vm.edit.Name = null;
            vm.edit.JzName = null;
            vm.edit.Pame = null;
            vm.edit.FullName = null;
            vm.edit.CKZCLXID = null;
            vm.edit.ParentID = null;
            vm.edit.ParentIDS = null;
            vm.edit.DeptID = null;
            vm.edit.JZZCID = null;
            vm.edit.AZWZ = null;
            vm.edit.JD = null;
            vm.edit.WD = null;
            vm.edit.GD = null;
            vm.edit.Sid = null;
            vm.edit.cj = null;
            vm.edit.CreateUserID = null;
            vm.edit.UpdateTime = null;
            vm.edit.UpdateUserID = null;
            vm.edit.ZT = null;
            vm.edit.Enabled = null;
            vm.edit.DelFlag = null;
            vm.edit.Memo = null;
            // 照相机
            vm.edit.nw_ip = null;
            vm.edit.nw_port = null;
            vm.edit.nw_web_port = null;
            vm.edit.ww_ip = null;
            vm.edit.ww_port = null;
            vm.edit.ww_web_port = null;
            vm.edit.username = null;
            vm.edit.password = null;
            vm.edit.td = null;
            vm.edit.rtsp_url = null;
            vm.edit.memo = null;
            $("#editLayer")[0].reset();
            layer.closeAll();
            $("#jqGrid").trigger("reloadGrid");
        }
    }
});