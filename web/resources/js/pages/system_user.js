/**
 * Created by ASUS on 2015/9/17.
 */
var userInfoSlider=null;
$(function () {
    var pageSize = 4;
    var currentPage = 1;
    var userInfoListObj = $("#userInfoTable").children("tbody");
    var userInfoListObjLength = userInfoListObj.find("tr").length;
    $("#userInfoModal").children("div:gt(0)").hide();
    //初始化用户数据列表项
    initUserInfoList();
    function initUserInfoList() {
        userInfoListObj.children("tr").show();
        userInfoListObj.children("tr:lt(" + ((currentPage - 1) * pageSize) + ")").hide();
        userInfoListObj.children("tr:gt(" + (currentPage * pageSize - 1) + ")").hide();

    }
    var options = {
        bootstrapMajorVersion: 3,
        currentPage: currentPage,
        totalPages: userInfoListObjLength % pageSize > 0 ? (userInfoListObjLength / pageSize) + 1 : userInfoListObjLength / pageSize,
        size: "normal",
        alignment: "center",
        itemTexts: function (type, page, current) {
            switch (type) {
                case "first":
                    return "第一页";
                case "prev":
                    return "<";
                case "next":
                    return ">";
                case "last":
                    return "最后页";
                case "page":
                    return page;
            }
        },
        onPageClicked: function (e, originalEvent, type, page) {
            currentPage = page;
            initUserInfoList();
        }
    }
    //用户列表分页模块
    $('#userInfoTablePagenation').bootstrapPaginator(options);
});

//配置用户角色
function configUserRole(userId){
    $("#userInfoModal").children("div").hide().eq("1").slideDown();
    var $userRoleInfo=$("#userRoleInfo"),roleInfoTemplate="";
    $userRoleInfo.children("tbody").html("");
    //开始加载当前用户的角色和权限
    var params=new Object();
    params.userId=userId;
    $.post("./fetch_system_user_roleinfo.action",params,function(data){
        $.each(data,function(i,ele){
            roleInfoTemplate+="<tr>";
            if(ele.owned){
                roleInfoTemplate+="<td class='col-sm-1'><input data-roleId=\""+ele.role_id+"\"  type='checkbox' checked=\"checked\"></td>";
            }else{
                roleInfoTemplate+="<td class='col-sm-1'><input  data-roleId=\""+ele.role_id+"\" type='checkbox'></td>";
            }
            roleInfoTemplate+="<td class='col-sm-3'>"+ele.role_name+"</td>";
            roleInfoTemplate+="<td class='col-sm-4'>";
            //加载权限
            $.each(ele.authorityList,function(j,authObj){
                roleInfoTemplate+="<span class=\"label label-info\">"+authObj.authority_name+"</span>&nbsp;"
            });
            roleInfoTemplate+="</td><td class='col-sm-4'><div class='col-sm-12'>";
            //加载菜单
            $.each(ele.menuList,function(i,menuObj){
                roleInfoTemplate+="<span class=\"label label-success\">"+menuObj.menu_name+"</span>&nbsp;"
            })
            roleInfoTemplate+="</div></td></tr>";
        });
        $userRoleInfo.children("tbody").html(roleInfoTemplate);
        var saveBtnTemplate="<div class='text-center'><button type=\"button\" id='roleConfigBtn' onclick=\"saveUserRoleConfig('"+userId+"')\" class='btn btn-default btn-lg'>保存修改</button></div>";
        $userRoleInfo.siblings(".text-center").remove();
        $userRoleInfo.after(saveBtnTemplate);
    });
}
//保存用户的角色配置信息
function saveUserRoleConfig(userId){
    //遍历当前被选中的角色，然后更新用户的角色选择
    var roleIds="";
    $.each($("#userRoleInfo").find(":checked"),function(i,e){
        roleIds+=$(e).attr("data-roleId")+",";
    });
    $.post("./update_system_user_role.action",{"userId":userId,"roleIds":roleIds},function(data){
        if(data){
            alert("更新用户角色成功");
        }else{
            alert("更新用户角色失败");
        }
    });
}
//增加用户信息
function addUserInfo(){

}
//修改用户信息
function editUserInfo(userId){

}
//删除用户信息
function deleteUserInfo(userId){

}
//返回上一级菜单
function returnLastMenu(num){
    switch (num){
        case "0":
            $("#userInfoModal").children("div").hide().eq("0").slideDown();
            break;
        case "1":
            break;
    }
}
