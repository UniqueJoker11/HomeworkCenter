/**
 * Created by DELL on 2015/7/29.
 */
var templateDatagrid=null;
var options={language: {
    "sProcessing": "处理中...",
        "sLengthMenu": "显示 _MENU_ 项结果",
        "sZeroRecords": "没有匹配结果",
        "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
        "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
        "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
        "sInfoPostFix": "",
        "sSearch": "搜索:",
        "sUrl": "",
        "sEmptyTable": "表中数据为空",
        "sLoadingRecords": "载入中...",
        "sInfoThousands": ",",
        "oPaginate": {
        "sFirst": "首页",
            "sPrevious": "上页",
            "sNext": "下页",
            "sLast": "末页"
    },
    "oAria": {
        "sSortAscending": ": 以升序排列此列",
            "sSortDescending": ": 以降序排列此列"
    }
}};
$(function () {
    templateDatagrid=$("#template_view_table").dataTable({
        "sPaginationType": "full_numbers",
        "language":options.language
    });
    //保存更改的信息
    $("#edit_templateSubmitBtn").on("click", function (e) {
        var params = new Object();
        params.template_id = $("#template_edit_dialog").attr("data-id");
        params.template_name = $("#edit_tamplateName").val();
        params.template_tip = $("#edit_tamplateTips").val();
        params.template_describe = $("#edit_tamplateDescribe").val();
        $.post("./update_template.action", params, function (data) {
            if (!data.isSuccess) {
                alert(data.msg);
            }else{
                alert("更新数据成功！");
            }
            //关闭对话框
            $("#template_edit_dialog").modal("hide");
        });
        //
    });
});
//查看用户的的模板页面
function showTemplate(accessurl) {
    //在一个新的窗口打开该链接的页面
    $("#template_browser_dialog").find("iframe").attr("src", accessurl);
    $("#template_browser_dialog").modal("show");
}
//编辑用户的模板
function editTemplate(templateId) {
    //初始化表单内容
    var params = new Object();
    params.template_id = templateId;
    $.post("./template_search.action", params, function (data) {
        $("#template_edit_dialog").attr("data-id", templateId);
        $("#edit_tamplateName").val(data.template_name);
        $("#edit_tamplateTips").val(data.template_tip);
        $("#edit_tamplateDescribe").val(data.template_describe);
        //显示对话框
        $("#template_edit_dialog").modal("show");
    });
}
//删除模板的信息
function delTemplate(templateId) {
    var result = window.confirm("确定要删除该模板么？");
    if (result) {
        var params = new Object();
        params.template_id = templateId;
        $.post("./delate_template.action", params, function (data) {
            if (data.isSuccess) {
                alert("删除成功");
                var templateObj=$("#template_"+templateId).fadeIn();
                templateDatagrid.dataTable({
                    "sPaginationType": "full_numbers",
                    "language":options.language
                });
                templateObj.remove();
            } else {
                alert(data.msg);
            }
        });
    }
}

