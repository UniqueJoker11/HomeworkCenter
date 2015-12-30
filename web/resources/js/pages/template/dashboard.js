/**
 * Created by DELL on 2015/8/21.
 */
var hasData = true;
$(function () {
    //初始化轮播图片
    $("#templateBanner").unslider({
        speed: 500,               //  The speed to animate each slide (in milliseconds)
        delay: 3000,              //  The delay between slide animations (in milliseconds)
        complete: function () {
        },  //  A function that gets called after every slide animation
        keys: true,               //  Enable keyboard (left, right) arrow shortcuts
        dots: true,               //  Display dot navigation
        fluid: false              //  Support responsive design. May break non-responsive designs
    });
    var cocoProductionMasonry = $("#cocoProductionList").masonry({
        itemSelector: '.wallet-template-li',
        gutterWidth: 20,
        isAnimated: true,
        percentPosition: true
    })
    //加载发布模板的信息
    initTemplateList();
    function initTemplateList() {
        if (hasData) {
            var params = new Object();
            params.currentPage = $("#cocoProductionList").attr("data-page");
            params.pageSize = 4;
            $.post("../homework/fetch_template.action", params, function (data) {
                    $("#templatePagination").button("reset");
                    if (params.currentPage >data.totalPage) {
                        $("#templatePagination").popover({
                            content: "哥，这次真没了！",
                            placement: "top"
                        });
                        hasData = false;
                    } else {
                        //处理加载的数据
                        var templateContent = "";
                        for (var i = 0; i < data.currentData.length; i++) {
                             templateContent += "<div class=\"col-md-3\"><div class=\"thumbnail\"><a href=\"../" + data.currentData[i].template_snapshot.split(",")[0] + "\" rel=\"prettyPhoto[pp_gal]\" title=\"Coco的作品-" + data.currentData[i].template_name + "\"><img src=\"../" + data.currentData[i].template_snapshot.split(",")[0] + "\" alt=\"点击欣赏\" class=\"img-rounded\"/></a>"
                            + "<div class=\"caption\"><h3>" + data.currentData[i].template_name + "</h3><p class=\"text-center\"><a target='_blank' href=\"../" + data.currentData[i].template_access_url + "\" class=\"btn btn-primary\" role=\"button\">查看作品</a>&nbsp;&nbsp;<a target=\"_blank\" class='btn btn-danger' href=\"../homework/template_download.action?sourceId=" + data.currentData[i].template_resource_url + "\">下载模板</a></p></div></div></div>"
                        }
                        $("#cocoProductionList").append(templateContent);
                        //瀑布流化内容
                        cocoProductionMasonry.masonry("layout");
                        //初始化图片点击链接
                        $("a[rel^='prettyPhoto']").prettyPhoto();
                    }
                }
            );
        } else {
            $("#templatePagination").popover({
                content: "哥，这次真没了！",
                placement: "right"
            });
        }
    }

//加载数据
    $("#templatePagination").on("click", function () {
        $Btn=$(event.target);
        $Btn.button("努力加载中...");
        var currentPage = $("#cocoProductionList").attr("data-page");
        $("#cocoProductionList").attr("data-page", (parseInt(currentPage) + 1));
        initTemplateList();
    });
});

