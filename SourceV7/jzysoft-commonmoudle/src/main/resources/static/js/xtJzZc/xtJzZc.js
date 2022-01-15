$(function () {
    console.log($(window).height())

    $("#jqGrid").jqGrid({
        url: baseURL + 'xtJzZc/xtJzZc/list',
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
            {label: '全称', name: 'FullName', index: 'FullName', width: 150},
            {label: '建筑组成类型', name: 'jzname', index: 'jzname', width: 80},
            {label: '备注', name: 'Memo', index: 'Memo', width: 150},


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
        q: {
            InOrder: null,
            Code: null,
            Name: null,
            FullName: null,
            JZZCLXID: null,
            Pame: null,
            ParentID: null,
            ParentIDS: null,
            ProvinceID: null,
            CityID: null,
            AreaID: null,
            Lng: null,
            Lat: null,
            HighWayType: null,
            Points: null,
            TreeLevel: null,
            DeptID: null,
            CreateTime: null,
            CreateUserID: null,
            UpdateTime: null,
            UpdateUserID: null,
            Enabled: null,
            Memo: null,
            DelFlag: null,
            expanded: null,
            is_leaf: null,
            level: null,
            loaded: null,
        },
        showList: true,
        title: null,
        index: null,
        edit: {
            flag: 0,
            ID: null,
            InOrder: null,
            Pame: null,
            Code: null,
            Name: null,
            FullName: null,
            JZZCLXID: null,
            ParentID: null,
            ParentIDS: null,
            ProvinceID: null,
            CityID: null,
            AreaID: null,
            Lng: null,
            Lat: null,
            Points: null,
            TreeLevel: null,
            DeptID: null,
            CreateTime: null,
            CreateUserID: null,
            UpdateTime: null,
            UpdateUserID: null,
            Enabled: null,
            Memo: null,
            DelFlag: null,
            expanded: null,
            is_leaf: null,
            level: null,
            loaded: null,
            RoadType: null,
            RoadWidth: null,
            PavementType: null,
            CarNum: null,
            RoadTypes: null,
            HighWayType: null,
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
        dropdown2: [
            {
                id: null,
                name: null,
            }
        ],
        dropdown3: [
            {
                id: null,
                name: null,
            }
        ],
        dropdown4: [
            {
                id: null,
                name: null,
            }
        ]

    },
    created: function () {
        // this.bindsearchdropdown();
        this.binddropdown();
    },
    methods: {
        binddropdown: function () {

            this.dropdown = BindDropDownControls('xt_jz_zclx_jc', 'ID', 'name');
            // this.dropdown2 = BindDropDownControls('rs_jc_roadtype', 'ID', 'RoadTypeName');
            // this.dropdown3 = BindDropDownControlsdyx('sys_dict_data', 'dict_value', 'dict_label', '道路类型')
            // this.dropdown4 = BindDropDownControlsdyx('sys_dict_data', 'dict_value', 'dict_label', '公路类型')
        },
        // 搜索下拉
        bindsearchdropdown: function () {

        },
        selectDeptTree: function () {
            var options = {
                title: '选择建筑组成',
                width: "380",
                url: baseURL + "xtJzZc/xtJzZc/selectProjectTreejz/" + 1,
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

        mapchoose: function (fun) {
            if (vm.edit.JZZCLXID == null) {
                layer.alert("请先选择建筑组成类型")
            } else {
                debugger
                switch (vm.edit.JZZCLXID) {
                    // 道路线
                    case 1:
                        var xx = 'POLYLINE';
                        break;
                    // 多边形区域
                    case 2:
                        var xx = 'POLYGON';
                        break;
                    // 病害
                    case 3:
                        var xx = 'POINT';
                        break;
                    // 圆
                    case 4:
                        var xx = 'CIRCLE';
                        break;
                    default:
                        break;
                }
                debugger
                var index = layer.open({
                    type: 2,

                    area: ['95%', '95%'],
                    scrollbar: false,
                    content: '../../road/webgis/comm?type=' + xx,
                    btn: ['确认', '取消'],
                    yes: function (index, layero) {
                        switch (vm.edit.JZZCLXID) {
                            // 道路线
                            case 1:
                                vm.edit.Points = layero.find("iframe")[0].contentWindow.$('#polyline').val();
                                break;
                            // 多边形区域
                            case 2:
                                vm.edit.Points = layero.find("iframe")[0].contentWindow.$('#polygon').val();
                                break;
                            // 病害
                            case 3:
                                vm.edit.Points = layero.find("iframe")[0].contentWindow.$('#point').val();

                                break;
                            // 圆
                            case 4:
                                var id = 'circle';
                                break;
                            default:
                                alert('无此字段')

                                break;
                        }
                        debugger
                        layer.close(index);
                    },
                    btn2: function () {
                        layer.close(index);
                    }
                });
            }

        },
        // 添加页面
        addPage: function () {

            $('#shu').show();
            debugger
            vm.reload();
            vm.index = layer.open({
                type: 1,
                title: '添加建筑组成',
                area: ['65%', '65%'],
                content: $('#editLayer'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                btn: ['确定', '取消'],
                yes: function () {

                    $.ajax({
                        type: "GET",
                        async: false, //同步执行
                        url: "../../xtJzZc/xtJzZc/list",
                        data: {},
                        dataType: "json", //返回数据形式为json
                        success: function (result) {
                            for (var i = 0; i < result.data.length; i++) {
                                if (vm.edit.Code == result.data[i].Code) {
                                    layer.alert("该编号已存在")
                                    vm.edit.flag = 1;
                                    break

                                }
                                else if (vm.edit.Name == result.data[i].Name) {
                                    vm.edit.flag = 1;
                                    layer.alert("该建筑组成已存在")
                                    break
                                } else {
                                    vm.edit.flag = 0;
                                }
                            }
                            if (vm.edit.flag != 1) {
                                vm.saveOrUpdate();
                            }

                        },

                    })
                },
                btn2: function () {
                    vm.reload();
                }
            });
        },
        <!--修改页面-->
        editPage: function (x) {

            debugger
            var id = vm.edit.ID;
            if (id == null) {
                return;
            }
            $.ajax({
                url: baseURL + 'xtJzZc/xtJzZc/findByid',
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
                    vm.edit.JZZCLXID = result.data.JZZCLXID;
                    vm.edit.ParentID = result.data.parent;
                    vm.edit.ParentIDS = result.data.ParentIDS;
                    vm.edit.ProvinceID = result.data.ProvinceID;
                    vm.edit.CityID = result.data.CityID;
                    vm.edit.AreaID = result.data.AreaID;
                    vm.edit.Lng = result.data.Lng;
                    vm.edit.Lat = result.data.Lat;
                    vm.edit.Points = result.data.Pointsz;
                    vm.edit.TreeLevel = result.data.TreeLevel;
                    vm.edit.DeptID = result.data.DeptID;
                    vm.edit.CreateTime = result.data.CreateTime;
                    vm.edit.CreateUserID = result.data.CreateUserID;
                    vm.edit.UpdateTime = result.data.UpdateTime;
                    vm.edit.UpdateUserID = result.data.UpdateUserID;
                    vm.edit.Enabled = result.data.Enabled;
                    vm.edit.Memo = result.data.Memo;
                    vm.edit.DelFlag = result.data.DelFlag;
                    vm.edit.expanded = result.data.expanded;
                    vm.edit.is_leaf = result.data.is_leaf;
                    vm.edit.level = result.data.level;
                    vm.edit.loaded = result.data.loaded;
                    vm.edit.Pame = result.data.Pame;
                    vm.edit.RoadType = result.data.RoadType;
                    vm.edit.RoadWidth = result.data.RoadWidth;
                    vm.edit.PavementType = result.data.PavementType;
                    vm.edit.HighWayType = result.data.HighWayType;
                    vm.edit.CarNum = result.data.CarNum;
                    var asd = vm.edit.JZZCLXID + ''
                    if (asd == '1') {
                        $('.xxx').show();
                    } else {
                        $('.xxx').hide();
                    }
                }
            })

            vm.index = layer.open({
                type: 1,
                title: '修改建筑组成',
                area: ['65%', '65%'],

                content: $('#editLayer'), //这里content是一个DOM，注意：最好该元素要存放在body最外层，否则可能被其它的相对元素所影响
                btn: ['确定', '取消'],
                yes: function (id) {
                    $.ajax({
                        type: "GET",
                        async: false, //同步执行
                        url: "../../xtJzZc/xtJzZc/listz",
                        data: {
                            IDS: vm.edit.ID
                        },
                        dataType: "json", //返回数据形式为json
                        success: function (result) {
                            for (var i = 0; i < result.data.length; i++) {
                                if (vm.edit.Code == result.data[i].Code) {
                                    layer.alert("该编号已存在")
                                    vm.edit.flag = 1;
                                    break

                                }
                                else if (vm.edit.Name == result.data[i].Name) {
                                    vm.edit.flag = 1;
                                    layer.alert("该建筑组成已存在")
                                    break
                                } else {
                                    vm.edit.flag = 0;
                                }
                            }

                            if (vm.edit.flag != 1) {
                                vm.saveOrUpdate(vm.edit.ID);
                            }

                        },

                    })
                },
                btn2: function () {
                    vm.reload();
                }
            });
        },
        <!--新增修改-->
        saveOrUpdate: function (id) {
            if (id != null && id == vm.edit.ParentID) {
                alert("不能选择此建筑组成")
                return false;
            }
            debugger

            var url = id == null ? "xtJzZc/xtJzZc/adddata" : "xtJzZc/xtJzZc/updatedata";
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
            debugger
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData: {
                    'InOrder': vm.q.InOrder,
                    'Code': vm.q.Code,
                    'Name': vm.q.Name,
                    'FullName': vm.q.FullName,
                    'JZZCLXID': vm.q.JZZCLXID,
                    'ParentID': vm.q.ParentID,
                    'ParentIDS': vm.q.ParentIDS,
                    'ProvinceID': vm.q.ProvinceID,
                    'HighWayType': vm.q.HighWayType,
                    'CityID': vm.q.CityID,
                    'AreaID': vm.q.AreaID,
                    'Lng': vm.q.Lng,
                    'Lat': vm.q.Lat,
                    'Points': vm.q.Points,
                    'TreeLevel': vm.q.TreeLevel,
                    'DeptID': vm.q.DeptID,
                    'CreateTime': vm.q.CreateTime,
                    'CreateUserID': vm.q.CreateUserID,
                    'UpdateTime': vm.q.UpdateTime,
                    'UpdateUserID': vm.q.UpdateUserID,
                    'Enabled': vm.q.Enabled,
                    'Memo': vm.q.Memo,
                    'DelFlag': vm.q.DelFlag,
                    'expanded': vm.q.expanded,
                    'is_leaf': vm.q.is_leaf,
                    'level': vm.q.level,
                    'loaded': vm.q.loaded,
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
                    url: baseURL + "/xtJzZc/xtJzZc/del",
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
            vm.q.FullName = null;
            vm.q.JZZCLXID = null;
            vm.q.ParentID = null;
            vm.q.ParentIDS = null;
            vm.q.ProvinceID = null;
            vm.q.CityID = null;
            vm.q.HighWayType = null;
            vm.q.AreaID = null;
            vm.q.Lng = null;
            vm.q.Lat = null;
            vm.q.Points = null;
            vm.q.TreeLevel = null;
            vm.q.DeptID = null;
            vm.q.CreateTime = null;
            vm.q.CreateUserID = null;
            vm.q.UpdateTime = null;
            vm.q.UpdateUserID = null;
            vm.q.Enabled = null;
            vm.q.Memo = null;
            vm.q.DelFlag = null;
            vm.q.expanded = null;
            vm.q.is_leaf = null;
            vm.q.level = null;
            vm.q.loaded = null;
            $("#gen-form")[0].reset();
        },
        reload: function () {
            vm.edit.flag = 0;
            vm.edit.InOrder = null;
            vm.edit.Pame = null;
            vm.edit.Code = null;
            vm.edit.Name = null;
            vm.edit.FullName = null;
            vm.edit.JZZCLXID = null;
            vm.edit.ParentID = null;
            vm.edit.ParentIDS = null;
            vm.edit.ProvinceID = null;
            vm.edit.CityID = null;
            vm.edit.AreaID = null;
            vm.edit.Lng = null;
            vm.edit.Lat = null;
            vm.edit.Points = null;
            vm.edit.TreeLevel = null;
            vm.edit.DeptID = null;
            vm.edit.CreateTime = null;
            vm.edit.HighWayType = null;
            vm.edit.CreateUserID = null;
            vm.edit.UpdateTime = null;
            vm.edit.UpdateUserID = null;
            vm.edit.Enabled = null;
            vm.edit.Memo = null;
            vm.edit.DelFlag = null;
            vm.edit.expanded = null;
            vm.edit.is_leaf = null;
            vm.edit.level = null;
            vm.edit.loaded = null;
            $("#editLayer")[0].reset();
            layer.closeAll();
            $("#jqGrid").trigger("reloadGrid");
        }
    }
});