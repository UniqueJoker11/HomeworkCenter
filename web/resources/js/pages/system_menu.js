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
                    "<h4 class=\"sender colin-menu-li\" onclick=\"loadNodeMenuInfo('" + data[i].menu_id + "')\"><i class=\"" + data[i].menu_icon + "\"></i>&nbsp;" + data[i].menu_name + "</h4></li>"
                }
                $("#colin-root-menu-list").html(rootMenuContent).children("li").eq(0).children("h4").trigger("click");

            } else {
                alert("还没有配置系统菜单");
            }
        });
    }
});

/**
 * 开始编辑信息
 * @param menuId
 */
var menuGId="";
function editMenuInfo(menuId, isNode) {
    if(menuGId==menuId){
        $("#colin_edit_menu_template").toggle();
    }else{
        menuGId=menuId;
        //移除所有的模板内容
        $("#colin_edit_menu_template").remove("");
        var menuObj = $("#colin_menu_" + menuId);
        var editTemplate =
            "<li id='colin_edit_menu_template'>" +
            "<div class=\"panel-body\">" +
            "<div class=\"form\">" +
            "<div class=\"input-group mb15\">" +
            "<span class=\"input-group-addon\">图标</span>" +
            "<input type=\"text\" placeholder=\"修改图标\" id=\"menuIcon\" class=\"form-control\" value='" + menuObj.children("h4").children("i").attr("class") + "'>" +
            "</div>" +
            "<div class=\"input-group mb15\">" +
            "<span class=\"input-group-addon\">名称</span>" +
            "<input type=\"text\" placeholder=\"修改名称\" id=\"menuIcon\" class=\"form-control\" value='" + menuObj.children("h4").text() + "'>" +
            "</div>";
        if (isNode) {
            editTemplate += "<div class=\"input-group mb15\">" +
            "<span class=\"input-group-addon\">链接</span>" +
            "<input type=\"text\" placeholder=\"访问链接\" id=\"menuIcon\" class=\"form-control\" value='" + menuObj.children(".colin-node-menu-url").find("mark").html() + "'>" +
            "</div>";
        }
        editTemplate += "<div class=\"panel-footer\">" +
        "<button type=\"submit\" class='btn btn-primary col-sm-3 col-sm-offset-2' onclick='confirmEditMenuInfo(" + menuId + ")'>修改</button><button type='reset'class='btn btn-danger col-sm-3 col-sm-offset-1' onclick='confirmResetEditMenuInfo()'>取消</button>" +
        "</div>" +
        "</li>";
        //操作dom
        menuObj.after(editTemplate);
        $("#colin_edit_menu_template").slideDown();
    }
}
/**
 * 加载子节点
 */
function loadNodeMenuInfo(menuId) {
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
                "</a></small><h4 class=\"sender colin-menu-li\"  onclick=\"loadNodeMenuInfo(" + data[i].menu_id + ")\"><i class=\"" + data[i].menu_icon + "\"></i>&nbsp;" + data[i].menu_name + "</h4>" +
                "<div class=\"colin-node-menu-url\"><small>页面访问目录：<mark>" + data[i].menu_url + "</mark></small></div></li>";
            }
            $("#colin-node-menu-list").html(nodeMenuContent);
        } else {
            $("#colin-node-menu-list").html("<li>当前没有子菜单哦！</li>")
        }
    });
}
//根据id删除菜单
function removeMenuInfo(menuId){
    var result=Widnow.confirm("确认要删除该菜单吗？");
    if(result){
        var params=new Object();
        params.menuId=menuId;
        $.post("./del_menu_info.action",params,function(data){
            if(data.isSuccess){
                alert("删除菜单成功！");
                $("colin_menu_"+menuId).remove();
            }else{
                alert("删除菜单失败！");
            }
        });
    }
}
/**
 * 确认修改菜单信息
 */
function confirmEditMenuInfo(menuId) {

}
/**
 * 确认取消修改
 */
function confirmResetEditMenuInfo() {

}
