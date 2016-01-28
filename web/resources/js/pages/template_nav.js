/**
 * Created by DELL on 2016/1/26.
 */
var picUploader = null;
$(function () {
    picUploader = WebUploader.create({
        // swf文件路径
        swf: "../js/webuploader/Uploader.swf",
        dnd: "#picNavContainer",
        // 文件接收服务端。
        server: './template_nav_add.action',
        // 选择文件的按钮。可选。
        // 内部根据当前运行是创建，可能是input元素，也可能是flash.
        pick: {id: '#picNav', innerHTML: "选择轮播图片"},
        fileVal: "navPic",
        accept: {
            title: 'Images',
            extensions: 'gif,jpg,jpeg,bmp,png',
            mimeTypes: 'image/*'
        },
        thumb: {
            width: 110,
            height: 110,
            // 图片质量，只有type为`image/jpeg`的时候才有效。
            quality: 70,
            // 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
            allowMagnify: true,
            // 是否允许裁剪。
            crop: true,
            // 为空的话则保留原有图片格式。
            // 否则强制转换成指定的类型。
            type: 'image/jpeg'
        },
        auto: false,
        // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
        resize: false
    });
    picUploader.on("fileQueued", function (file) {
       $("#picNavContainer").append("<p>选中上传图片是:"+file.name+"</p>");
    });
    picUploader.on("uploadSuccess", function (file, response) {
        console.log(response);

        alert("上传成功");
    });
});
//打开模板导航页轮播图对话框
function addTemplateNav() {
    $("#addTemplateNavModal").modal("show");
}
function confirmAddTemplateNav() {
    var navDigest=$("#addNavDigest").val();
    var navOrder=$("#addNavOrder").val();
    picUploader.option("formData",{navDigest:navDigest,navOrder:navOrder});
    picUploader.upload();
}