/**
 * Created by DELL on 2015/8/21.
 */

$(function(){
    <!-- 实例化编辑器代码 -->
    window.um = UM.getEditor('container', {
        /* 传入配置参数,可配参数列表看umeditor.config.js */
        toolbar: ['undo redo | bold italic underline']
    });
    //初始化想到是表单插件
    $('#basicWizard').bootstrapWizard({
        'nextSelector': '.next',
        'previousSelector': '.previous',
        onNext: function(tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index+1;
            var $percent = ($current/$total) * 100;
            jQuery('#progressWizard').find('.progress-bar').css('width', $percent+'%');
        },
        onPrevious: function(tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index+1;
            var $percent = ($current/$total) * 100;
            jQuery('#progressWizard').find('.progress-bar').css('width', $percent+'%');
        },
        onTabShow: function(tab, navigation, index) {
            var $total = navigation.find('li').length;
            var $current = index+1;
            var $percent = ($current/$total) * 100;
            jQuery('#progressWizard').find('.progress-bar').css('width', $percent+'%');
        }
    });
});



