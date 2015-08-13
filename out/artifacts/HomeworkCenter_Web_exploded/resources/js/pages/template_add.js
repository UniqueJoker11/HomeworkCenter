/**
 * Created by DELL on 2015/7/27.
 */
$(function () {
    //验证表单提交
    $("#templateAddForm").validate({
        rules: {
            tamplateName: {
                required: true,
                rangelength: [0, 100]
            },
            tamplateTips: {
                required: true,
                rangelength: [0, 300]
            },
            tamplateDescribe: {
                required: true,
                rangelength: [0, 200]
            }
        },
        messages: {
            tamplateName: {
                required: "模板名称不能为空！",
                rangelength: "模板名称长度不能超过100字符"
            },
            tamplateTips: {
                required: "模板标签不能为空",
                rangelength: "模板名称长度不能超过300字符"
            },
            tamplateDescribe: {
                required: "模板描述不能为空",
                rangelength: "模板描述长度不能超过200字符"
            }
        }
    });
    $("#submitTemplateBtn").click(function () {
        $("#templateAddForm").ajaxForm(function (data) {
            var result="";
            if(data.isSuccess){
                result="新增模板成功！";
            }else{
                result="新增模板失败！";
            }
            $("#add_template_result").html(result);
            $("#add_template_modal").modal({keyboard: false});
            $("#templateAddForm").clearForm();
        });
    });
});
