/**
 * Created by DELL on 2015/8/24.
 */
$(function () {
    //加载菜单根目录
    loadRootMenuInfo();
    function loadRootMenuInfo() {
        $.post("./fetch_menu_root.action", function (data) {
            if (data != null && data.length > 0) {
                var rootMenuContent = "";
                for (var i = 0; i < data.length; i++) {
                    rootMenuContent += "<li id=\"colin_menu_" + data[i].menu_id + "\"><small class=\"pull-right\">" +
                    "<a href=\"javascript:;\" class=\"panel-edit\" onclick='editMenuInfo(\"" + data[i].menu_id + "\",false)'>" +
                    "<i class=\"fa fa-edit\"></i></a>&nbsp;&nbsp;&nbsp;" +
                    "<a href=\"javascript:;\" class=\"panel-remove\" onclick='removeMenuInfo(\"" + data[i].menu_id + "\")'>" +
                    "<i class=\"glyphicon glyphicon-remove-circle\"></i></a></small>" +
                    "<h4 class=\"sender colin-menu-li\" onclick=\"loadNodeMenuInfo('" + data[i].menu_id + "')\"><i class=\"" + data[i].menu_icon + "\"></i>&nbsp;<strong>" + data[i].menu_name + "</strong></h4></li>"
                }
                $("#colin-root-menu-list").html(rootMenuContent).children("li").eq(0).children("h4").trigger("click");

            } else {
                alert("还没有配置系统菜单");
            }
        });
    }

    //绑定添加根节点菜单添加的事件
    $("#colin-root-menu-modal-submitbtn").bind("click", function () {
        $("#root_menuOrder").val(($("#colin-root-menu-list>li").length + 1) * 10000);
        $("#colin-root-menu-modal-form").ajaxSubmit({
            success: function (data) {
                if (data) {
                    alert("添加成功！");
                    loadRootMenuInfo();
                } else {
                    alert("添加失败！")
                }

            }
        });
        $("#colin-root-menu-modal").modal("hide");
    });
    //绑定添加子节点菜单添加的事件
    $("#colin-node-menu-modal-submitbtn").bind("click", function () {
        $("#node_menuOrder").val(($("#colin-node-menu-list>li").length + 1) * 10000);
        $("#node_parentId").val($("#colin-node-menu-title").attr("data-parentId"));
        $("#colin-node-menu-modal-form").ajaxSubmit({
            success: function (data) {
                if (data) {
                    var parentId = $("#colin-node-menu-title").attr("data-parentId");
                    $("#colin_menu_" + parentId).children("h4").trigger("click");
                    alert("添加子菜单成功！");
                } else {
                    alert("添加子菜单失败！");
                }
            }
        });
        $("#colin-node-menu-modal").modal("hide");
    });
});

/**
 * 开始编辑信息
 * @param menuId
 */
var menuGId = "";
function editMenuInfo(menuId, isNode) {
    if (menuGId == menuId) {
        if ($("#colin_edit_menu_template").length != 0) {
            $("#colin_edit_menu_template").toggle();
        } else {
            initTemplate(menuId, isNode)
        }
    } else {
        menuGId = menuId;
        //移除所有的模板内容
        if ($("#colin_edit_menu_template").length != 0) {
            $("#colin_edit_menu_template").remove();
        }
        initTemplate(menuId, isNode);
    }


}
function initTemplate(menuId, isNode) {
    var menuObj = $("#colin_menu_" + menuId);
    var editTemplate =
        "<li id='colin_edit_menu_template'>" +
        "<div class=\"panel-body\">" +
        "<form id=\"colin_edit_menu_form\" class=\"form\">" +
        "<input type=\"hidden\" name=\"menuId\" value=\"" + menuId + "\"/>" +
        "<div class=\"input-group mb15\">" +
        "<span class=\"input-group-addon\">图标</span>" +
        "<input type=\"text\" placeholder=\"修改图标\" name=\"menuIcon\" class=\"form-control\" value='" + menuObj.children("h4").children("i").attr("class") + "'>" +
        "</div>" +
        "<div class=\"input-group mb15\">" +
        "<span class=\"input-group-addon\">名称</span>" +
        "<input type=\"text\" placeholder=\"修改名称\" name=\"menuName\" class=\"form-control\" value='" + $.trim(menuObj.children("h4").text()) + "'>" +
        "</div>";
    if (isNode) {
        editTemplate += "<div class=\"input-group mb15\">" +
        "<span class=\"input-group-addon\">链接</span>" +
        "<input type=\"text\" placeholder=\"访问链接\" name=\"menuUrl\" class=\"form-control\" value='" + menuObj.children(".colin-node-menu-url").find("mark").html() + "'>" +
        "</div></form>";
    }
    editTemplate += "<div class=\"panel-footer\">" +
    "<button type=\"button\" class='btn btn-primary col-sm-3 col-sm-offset-2' onclick='confirmEditMenuInfo()'>修改</button><button type='reset'class='btn btn-danger col-sm-3 col-sm-offset-1' onclick='confirmResetEditMenuInfo()'>取消</button>" +
    "</div>" +
    "</li>";

    //操作dom
    menuObj.after(editTemplate);
    $("#colin_edit_menu_template").slideDown();
}
/**
 * 加载子节点
 */
function loadNodeMenuInfo(menuId) {
    $("#colin-node-menu-title").attr("data-parentId", menuId);
    var params = new Object();
    params.menuParId = menuId;
    $.post("./fetch_menu_node.action", params, function (data) {
        //加载目录
        $("#colin-node-menu-title").html($("#colin_menu_" + menuId).children("h4").text() + "菜单目录");
        //循环显示出子节点的列表
        if (data != null && data.length > 0) {
            var nodeMenuContent = "";
            for (var i = 0; i < data.length; i++) {
                nodeMenuContent += "<li id=\"colin_menu_" + data[i].menu_id + "\"><small class=\"pull-right\"><a href=\"javascript:;\" class=\"panel-edit\"  onclick='editMenuInfo(\"" + data[i].menu_id + "\",true)'><i class=\"fa fa-edit\">" +
                "</i></a>&nbsp;&nbsp;&nbsp;<a href=\"javascript:;\" class=\"panel-remove\" onclick='removeMenuInfo(\"" + data[i].menu_id + "\")'><i class=\"glyphicon glyphicon-remove-circle\"></i>" +
                "</a></small><h4 class=\"sender colin-menu-li\"  onclick=\"loadNodeMenuInfo(" + data[i].menu_id + ")\"><i class=\"" + data[i].menu_icon + "\"></i>&nbsp;<strong>" + data[i].menu_name + "</strong></h4>" +
                "<div class=\"colin-node-menu-url\"><small>页面访问目录：<mark>" + data[i].menu_url + "</mark></small></div></li>";
            }
            $("#colin-node-menu-list").html(nodeMenuContent);
        } else {
            $("#colin-node-menu-list").html("<li>当前没有子菜单哦！</li>")
        }
    });
}
//根据id删除菜单
function removeMenuInfo(menuId) {
    var result = window.confirm("确认要删除该菜单吗？");
    if (result) {
        var params = new Object();
        params.menuId = menuId;
        $.post("./del_menu_info.action", params, function (data) {
            if (data) {
                alert("删除菜单成功！");
                if ($("#colin_menu_" + menuId).parent("ul").attr("id") == "colin-root-menu-list") {
                    $("#colin-root-menu-list").children("li").eq(0).children("h4").trigger("click");
                }
                if ($("#colin_menu_" + menuId).siblings("li").length == 0) {
                    $("#colin_menu_" + menuId).html("<li>当前没有菜单啦！</li>");
                } else {
                    $("#colin_menu_" + menuId).remove();
                }
            } else {
                alert("删除菜单失败！");
            }
        });
    }
}
/**
 * 确认修改菜单信息
 */
function confirmEditMenuInfo() {
    var params = new Object();
    params = $("#colin_edit_menu_form").serialize();
    $.post("./update_menu_info.action", params, function (data) {
        if (data) {
            //隐藏编辑框
            var menuTemplateObj = $("#colin_edit_menu_template");
            menuTemplateObj.slideUp();
            //刷新内容
            var prevObj = menuTemplateObj.prev("li");
            var prevMenuObj = prevObj.children("h4");
            if (menuTemplateObj.find("input[name='menuIcon']").val() != "") {
                prevMenuObj.children("i").attr("class", menuTemplateObj.find("input[name='menuIcon']").val());
            }
            if (menuTemplateObj.find("input[name='menuName']").val() != "") {
                prevMenuObj.children("strong").html(menuTemplateObj.find("input[name='menuName']").val());
            }
            if (menuTemplateObj.find("input[name='menuUrl']").length > 0 && menuTemplateObj.find("input[name='menuUrl']").val() != "") {
                prevObj.children(".colin-node-menu-url").children("small").html("页面访问目录：<mark>" + menuTemplateObj.find("input[name='menuUrl']").val() + "</mark>");
            }
            alert("更新菜单内容成功！");
        } else {
            alert("更新菜单内容失败！");
        }
    });
    event.preventDefault();
    return false;
}
/**
 * 确认取消修改
 */
function confirmResetEditMenuInfo() {
    var menuTemplateObj = $("#colin_edit_menu_template");
    menuTemplateObj.slideUp();
}
/**
 * 添加根目录菜单
 */
function addRootMenuInfo() {
    $("#colin-node-menu-modal").modal("hide");
    $("#colin-root-menu-modal").modal("show");
}
/**
 * 添加节点目录菜单
 */
function addNodeMenuInfo() {
    $("#colin-node-menu-modal").modal("show");
    $("#colin-root-menu-modal").modal("hide");
}