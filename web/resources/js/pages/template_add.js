/**
 * Created by DELL on 2015/7/27.
 */
$(function () {
    //初始化上传文件
    var templateSnapshotUploader=WebUploader.create({
        // swf文件路径
        swf : '../js/webuploader/Uploader.swf',
        server : "./uploadSnapshotPic.action",
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick : {
            id : '#templateSnapshotBtn',
            multiple : true
        },
        // 只允许选择图片文件。
        accept : {
            title : 'Images',
            extensions : 'gif,jpg,jpeg,bmp,png',
            mimeTypes : 'image/*'
        },
        method : 'post',
        fileVal : 'templateSnapshotPics',
        thumb : {
            width : 110,
            height : 110,
            // 图片质量，只有type为`image/jpeg`的时候才有效。
            quality : 70,
            // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
            allowMagnify : true,
            // 是否允许裁剪。
            crop : true,
            // 为空的话则保留原有图片格式。
            // 否则强制转换成指定的类型。
            type : 'image/jpeg'
        },
        auto : false,
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize : false
    });
    templateSnapshotUploader.on("",function(){});
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
            $('#templateSnapshot').fileinput('reset');
            $('#templateResource ').fileinput('reset');
        });
    });
});
