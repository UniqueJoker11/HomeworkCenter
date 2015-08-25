/**
 * Created by DELL on 2015/8/21.
 */
var ueditor = null;
$(function () {
    <!-- 实例化编辑器代码 -->
    initEditor();
    //初始化编辑器
    function initEditor() {
        if (ueditor == null) {
            ueditor = UM.getEditor('aticleContentContainer', {
                /* 传入配置参数,可配参数列表看umeditor.config.js */
                initialFrameWidth: $("#basicWizard>.tab-content").width() * 0.75 - 20,
                autoHeightEnabled: true,
                autoFloatEnabled: true
            });
        }

    }

    //初始化下拉菜单
    $("#aticleCategory").select2({
        width: '100%'
    });

    //初始化上传图片插件
    /* initUploadComponent();
     function initUploadComponent() {
     var uploader = WebUploader.create({
     auto:false,
     // swf文件路径
     swf: '../webuploader/Uploader.swf',

     // 文件接收服务端。
     server: './upload_image.action',

     // 选择文件的按钮。可选。
     // 内部根据当前运行是创建，可能是input元素，也可能是flash.
     pick: '#picker',

     // 不压缩image, 默认如果是jpeg，文件上传前会压缩一把再上传！
     resize: false,
     // 只允许选择图片文件。
     accept: {
     title: 'Images',
     extensions: 'gif,jpg,jpeg,bmp,png',
     mimeTypes: 'image*/
    /*'
     }
     });
     // 当有文件被添加进队列的时候
     uploader.on('fileQueued', function (file) {
     $("#thelist").append('<div id="' + file.id + '" class="item">' +
     '<h4 class="info">' + file.name + '</h4>' +
     '<p class="state">等待上传...</p>' +
     '</div>');
     });
     // 文件上传过程中创建进度条实时显示。
     uploader.on( 'uploadProgress', function( file, percentage ) {
     var $li = $( '#'+file.id ),
     $percent = $li.find('.progress .progress-bar');

     // 避免重复创建
     if ( !$percent.length ) {
     $percent = $('<div class="progress progress-striped active">' +
     '<div class="progress-bar" role="progressbar" style="width: 0%">' +
     '</div>' +
     '</div>').appendTo( $li ).find('.progress-bar');
     }

     $li.find('p.state').text('上传中');

     $percent.css( 'width', percentage * 100 + '%' );
     });
     uploader.on( 'uploadSuccess', function( file ) {
     $( '#'+file.id ).find('p.state').text('已上传');
     });

     uploader.on( 'uploadError', function( file ) {
     $( '#'+file.id ).find('p.state').text('上传出错');
     });

     uploader.on( 'uploadComplete', function( file ) {
     $( '#'+file.id ).find('.progress').fadeOut();
     });
     $("#uploader").on("click",function(e){
     uploader.upload();
     });
     }*/

    //初始化流程表单插件
    $('#basicWizard').bootstrapWizard({
        'nextSelector': '.next',
        'previousSelector': '.previous'
    });

    //绑定表单提交
    $("#colin-add-aticle-form").validate({
        debug: false,// //调试模式取消submit的默认提交功能
        focusCleanup: true,
        focusInvalid: false, // 当为false时，验证无效时，没有焦点响应
        rules: {
            aticleTitle: {
                required: true
            },
            aticleDigest: {
                required: true
            },
            aticleCategory: {
                required: true
            },
            aticleTips: {
                required: true
            }
        },
        message:{
            aticleTitle: {
                required: "文章题目不能为空"
            },
            aticleDigest: {
                required: "文章摘要不能为空"
            },
            aticleCategory: {
                required: "文章分类不能为空"
            },
            aticleTips: {
                required: "文章标签不能为空"
            }
        }
    });
    //绑定表单提交
    $("#colin-aticle-add-submitbtn").on("click", function () {
        $("#colin-add-aticle-form").ajaxForm({
            beforeSubmit: function(arr, $form, options) {
                $.extend($form,{aticleContent:ueditor.getAllHtml()});
                return true;
            },
            success:function(data){
                if(data){
                    alert("保存文件成功！");
                    //跳转至文章预览
                    $("#colin-aticle-preview-btn").trigger("click");
                    $("#colin-aticle-preview-title").html($("#aticleTitle").val());
                    var date=new Date();
                    $("#colin-aticle-preview-date").html("发布日期:"+date.toLocaleDateString());
                    var tagContent="";
                    $.each($("#aticleTips").val().split(","),function(i,e){
                        tagContent+="<span class=\"label label-warning\" style='margin:0 4px;'>"+e+"</span>";
                    });
                    $("#colin-aticle-preview-tags").html(tagContent);
                    $("#colin-aticle-preview-digest").html($("#aticleDigest").val());
                    $("#colin-aticle-preview-content").html(ueditor.getAllHtml());

                }else{
                    alert("保存文章失败！");
                }
            }
        });
    });
});



