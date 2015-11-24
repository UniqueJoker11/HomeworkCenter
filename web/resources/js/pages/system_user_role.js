/**
 * Created by ASUS on 2015/10/21.
 */
$(function () {
    var options = {
        "bPaginate": true, //翻页功能
        "bLengthChange": true, //改变每页显示数据数量
        "bFilter": true, //过滤功能
        "bSort": true, //排序功能
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "oLanguage": {
            "sLengthMenu": "每页显示 _MENU_条",
            "sZeroRecords": "没有找到符合条件的数据",
            "sProcessing": "&lt;img src=’./loading.gif’ /&gt;",
            "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
            "sInfoEmpty": "木有记录",
            "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
            "sSearch": "搜索：",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            }
        }
    }
    var roleDatagrid = $("#colinRoleDatagrid").dataTable(options);
});
//配置菜单
function configMenu(roleId) {
    $("#colinConfigRoleMenuDialog").attr("data-roleId",roleId);
    var params = new Object();
    params.roleId = roleId;
    $.post("./fetch_system_role_menu.action", params, function (data) {
        initMenuTree(data);
    });
    $("#colinConfigRoleMenuDialog").modal("show");
};
var t = $("#colinRoleMenuList");
function initMenuTree(data) {
    $.fn.zTree.destroy("colinRoleMenuList");
    var selectTreeNodes = new Array();
    var setting = {
        treeId: "colinRoleMenuList",
        view: {
            dblClickExpand: false,
            showLine: true,
            selectedMulti: false
        },
        data: {
            simpleData: {
                enable: true,
                idKey: "id",
                pIdKey: "pId",
                rootPId: "root"
            }
        },
        check: {
            enable: true,
            autoCheckTrigger:true
        },
        callback: {
            beforeClick: function (treeId, treeNode, clickFlag) {
                return true;
            },
            onClick: function (event, treeId, treeNode, clickFlag) {
               if(treeNode.checked){
                   treeNode.checked=false;
               }else{
                   treeNode.checked=true;
               }
                t.refresh();
            }
        }
    };

    t = $.fn.zTree.init($("#colinRoleMenuList"), setting, data);

}
/**
 * 保存角色菜单的配置
 */
function updateRoleMenuConfig() {
    var changeNodes = t.getChangeCheckedNodes();
    if (changeNodes.length > 0) {
        /**
         * 保存修改的节点
         */
        var nodes = t.getCheckedNodes(true);
        var params=new Object();
        params.roleId=$("#colinConfigRoleMenuDialog").attr("data-roleId");
        params.menuIds=new Array();
        $.each(nodes, function (i, e) {
           params.menuIds.push(e.id);
        });
        $.post("./update_system_role_menu.action",params,function(data){
            if(data){
                alert("更新成功！");
                t.refresh();
                $("#colinConfigRoleMenuDialog").modal("hide");
            }else{
                alert("更新角色对应的菜单失败！");
            }
        });
    } else {
        alert("您未作任何的修改配置，无需保存！");
    }

}