/**
 * Created by ASUS on 2015/10/21.
 */
$(function () {
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