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
    //开始加载当前用户的角色和权限
    var params=new Object();
    params.user_id="";
    $.post("/fetch_system_user_roleinfo.action",params,function(data){});
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
