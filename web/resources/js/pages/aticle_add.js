/**
 * Created by DELL on 2015/8/21.
 */

$(function () {
    <!-- 实例化编辑器代码 -->
    initEditor();
    //初始化编辑器
    function initEditor() {
        var ue = null;
        if (ue == null) {
            ue = UM.getEditor('aticleContentContainer', {
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
    initUploadComponent();
    function initUploadComponent() {
        var uploader = WebUploader.create({
            auto:true,
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
                mimeTypes: 'image/*'
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
    }

    //初始化想到是表单插件
    $('#basicWizard').bootstrapWizard({
        'nextSelector': '.next',
        'previousSelector': '.previous',
        onNext: function (tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index + 1;
            var $percent = ($current / $total) * 100;
            jQuery('#progressWizard').find('.progress-bar').css('width', $percent + '%');
        },
        onPrevious: function (tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index + 1;
            var $percent = ($current / $total) * 100;
            jQuery('#progressWizard').find('.progress-bar').css('width', $percent + '%');
        },
        onTabShow: function (tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index + 1;
            var $percent = ($current / $total) * 100;
            jQuery('#progressWizard').find('.progress-bar').css('width', $percent + '%');
        }
    });
});



