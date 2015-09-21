/**
 * Created by DELL on 2015/7/27.
 */
var templateSnapshotUploader = null;
$(function () {
    //初始化上传文件
    templateSnapshotUploader = WebUploader.create({
        // swf文件路径
        swf: '../js/webuploader/Uploader.swf',
        dnd: "#templateSnapshotArea",
        server: "./uploadSnapshotPic.action",
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {
            id: '#templateSnapshotBtn',
            multiple: true
        },
        fileSingleSizeLimit: 1000 * 1024,
        fileNumLimit: 4,
        // 只允许选择图片文件。
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        },
        method: 'post',
        fileVal: 'templateSnapshotPics',
        auto: false,
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });
    //监听模板上传事件
    listenTemplateSnapshotUploader();
    function listenTemplateSnapshotUploader() {
        var imageUrl = "";//上传成功的文件url

        //当图片文件被加入队列以后触发。
        templateSnapshotUploader.on("fileQueued", function (file) {
            $("#uploadTemplateResourcePrepare").hide();
            var snapshotReadyObj = $("#uploadTemplateResourceReady");
            var snapshotThumObjs = snapshotReadyObj.find("#templateSnapshotUploadThum");
            makethum(file, snapshotThumObjs, templateSnapshotUploader);
            templateSnapshotUploader.addButton({id: "#resumeAddSnapshotBtn", innerHTML: "继续添加"});
            snapshotReadyObj.removeClass("hidden").show();
        });
        //图片上传过程中事件
        templateSnapshotUploader.on("uploadProgress", function (file, percentage) {
            $("#templateSnapshotUploadForm").find(".progress-bar").attr("aria-valuenow", percentage * 100).html(percentage * 100 + "%").css("width", percentage * 100 + "%");
        });
        //文件上传出错时触发，停止后续的上传
        templateSnapshotUploader.on("uploadError", function (file, reason) {
            alert("文件" + file.name + "上传出错了出错原因是" + reason + "，我们将尝试重新上传该文件");
            templateSnapshotUploader.retry(file);
        });
        //不管成功或者失败，文件上传完成时触发。
        templateSnapshotUploader.on("uploadStart", function (file) {
            if (templateSnapshotUploader.getStats().uploadFailNum != 0) {
                templateSnapshotUploader.cancelFile(file);
            }
        });
        //当文件上传结束时触发
        templateSnapshotUploader.on("uploadSuccess", function (file, response) {
            if (response.isSuccess) {
                imageUrl += response.uploadImg + ",";
                var currentSnapshotTemplateResource = $("#templateSnapshotUploadThum").children("#" + file.id).find("img");
                //当前上传成功的文件模板
                var snapshtoSuccessFragment = "<div class=\"col-xs-6 col-sm-3\">"
                    + "<a href=\"javascript:;\" class=\"thumbnail\">"
                    + "<img src=\"" + currentSnapshotTemplateResource.attr("src") + "\" title=\"" + currentSnapshotTemplateResource.attr("title") + "\">"
                    + "</a>"
                    + "</div>";
                $("#templateResourceUploadSuccess").find(".form-control").append(snapshtoSuccessFragment);
                $("#uploadImg").val(response.uploadImg);
            } else {
                alert("服务器存储上传文件出现错误");
            }
            $("#uploadTemplateResourceReady").hide();
            $("#templateResourceUploadSuccess").removeClass("hidden");
        });
    }

    function makethum(file, domObj, uploaderObj) {
        uploaderObj.makeThumb(file, function (error, ret) {
            if (error) {
                domObj.append('预览错误');
            } else {
                var snapshotTemplateFragment = " <div id=\"" + file.id + "\" class=\"col-xs-6 col-sm-3\">"
                    + "<a href=\"javascript:;\" class=\"thumbnail\">"
                    + "<i class=\"glyphicon glyphicon-remove-circle\" onclick=\"removeUploadFile('" + file.id + "')\"></i>"
                    + "<img src=\"" + ret + "\" title=\"" + file.name + "\">"
                    + "</a><strong>"
                    + "文件名：" + file.name + ""
                    + "</strong></div>";
                domObj.append(snapshotTemplateFragment);
            }
        });
    }

    //开始上传资源
    $("#startUploadSnapshotBtn").bind("click", function () {
        templateSnapshotUploader.upload();
    });
    //初始化资源上传
    //初始化上传文件
    var templateZipUploader = WebUploader.create({
        // swf文件路径
        swf: '../js/webuploader/Uploader.swf',
        server: "./uploadTemplateZip.action",
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: '#templateZipResource',
        fileNumLimit: 1,
        // 只允许选择压缩文件。
        accept: {
            title: 'Zip',
            extensions: 'zip',
            mimeTypes: 'application/zip'
        },
        method: 'post',
        fileVal: 'templateZipFile',
        auto: false,
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });
    //监听压缩资源事件
    listenZipUploaderResource();
    function listenZipUploaderResource() {
        //添加完成资源
        templateZipUploader.on("fileQueued", function (file) {
            $("#uploadZipResourcePrepare").hide();
            var $uploadZipResourceReady = $("#uploadZipResourceReady");
            $uploadZipResourceReady.find("#uploadZipResourceReadyFileInfo").html("上传的文件名是：" + file.name);
            $uploadZipResourceReady.removeClass("hidden").show();
        });
        //绑定上传文件事件
        $("#templateResourceUpload").find("button").bind("click", function () {
            //开始上传文件
            templateZipUploader.upload();
        });
        //上传过程中文件
        templateZipUploader.on("uploadProgress", function (file, percentage) {
            $("#templateResourceUploadProgress").find(".progress-bar").attr("aria-valuenow", percentage * 100).css("width",percentage * 100+"%").html(percentage * 100 + "%");
        });
        //上传资源完毕
        templateZipUploader.on("uploadSuccess", function (file, response) {
            if (response.isSuccess) {
                $("#uploadZipResourceReady").hide();
                var $uploadZipResourceCompleted = $("#uploadZipResourceCompleted");
                $uploadZipResourceCompleted.find(".col-sm-8").html("文件" + file.name + "上传成功！")
                $("#uploadZipLocation").val(response.uploadZipLocation);
                $("#uploadZip").val(response.uploadZip);
                $uploadZipResourceCompleted.removeClass("hidden").show();
            } else {
                alert("上传压缩文件失败！");
                $("#uploadZipResourcePrepare").show();
            }
        });
    }

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
            var result = "";
            if (data.isSuccess) {
                result = "新增模板成功！";
            } else {
                result = "新增模板失败！";
            }
            $("#add_template_result").html(result);
            $("#add_template_modal").modal({keyboard: false});
            $("#templateAddForm").clearForm();
            $("#templateResourceUploadSuccess").hide();
            $("#uploadTemplateResourcePrepare").show();
            $("#uploadZipResourceCompleted").hide();
            $("#uploadZipResourcePrepare").show();
        });
    });
});
//移除上传的图片资源
function removeUploadFile(fileId) {
    templateSnapshotUploader.removeFile(fileId);
    $(event.target).parent("a").parent("div").remove();
}
