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
    /**
     * $ajax.select2({
      ajax: {
        url: "https://api.github.com/search/repositories",
        dataType: 'json',
        delay: 250,
        data: function (params) {
          return {
            q: params.term, // search term
            page: params.page
          };
        },
        processResults: function (data, params) {
          // parse the results into the format expected by Select2
          // since we are using custom formatting functions we do not need to
          // alter the remote JSON data, except to indicate that infinite
          // scrolling can be used
          params.page = params.page || 1;

          return {
            results: data.items,
            pagination: {
              more: (params.page * 30) < data.total_count
            }
          };
        },
        cache: true
      },
      escapeMarkup: function (markup) { return markup; },
      minimumInputLength: 1,
      templateResult: formatRepo,
      templateSelection: formatRepoSelection
    });**/

    //初始化下拉菜单
    $("#aticleCategory").select2({
        language: "zh-CN",
        width: '100%'
    });


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
                    clearFormContent();
                }else{
                    alert("保存文章失败！");
                }
            }
        });
    });
    function clearFormContent(){
       var $formObj= $("#colin-add-aticle-form");
        $formObj.find("input").val("");
        $formObj.find("textarea").val("");
        ueditor.reset();
    }
});



