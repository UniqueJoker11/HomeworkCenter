/**
 * Created by ASUS on 2015/9/17.
 */
var userInfoGrid=null;
var pageSize = 4;
var currentPage = 1;
$(function () {
    var userDatagrid = $("#userInfoTable").dataTable(options);
});

//配置用户角色
function configUserRole(userId){
    $("#userInfoModal").children("div:eq(0)").hide().siblings("div").removeClass("hidden").slideDown();
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
    $("#userinfoEditDialog").attr("data-Id",userId);
    $("#userinfoEditDialog").modal("show");
}
//删除用户信息
function deleteUserInfo(userId){
   if(window.confirm("你确定要删除该用户信息吗？")){
       var params=new Object();
       params.userId=userId;
       $.post("./del_system_user.action",params,function(data){
           if(data){
               alert("删除成功！");
               //移除当前的元素
               $("#user_"+userId).remove();
               //刷新当前的列表
               var userInfoListObj = $("#userInfoTable").children("tbody");
               userInfoListObj.children("tr").show();
               userInfoListObj.children("tr:lt(" + ((currentPage - 1) * pageSize) + ")").hide();
               userInfoListObj.children("tr:gt(" + (currentPage * pageSize - 1) + ")").hide();
           }else{
               alert("删除失败！");
           }
       });
   }
}
function submitEditUserinfo(){

}
//返回上一级菜单
function returnLastMenu(num){
    switch (num){
        case "0":
            $("#userInfoModal").children("div:eq(0)").hide().next("div").slideDown();
            break;
        case "1":
            $("#userInfoModal").children("div:eq(1)").hide().prev("div").slideDown();
            break;
    }
}
