/**
 * Created by DELL on 2015/8/21.
 */
$(function () {
    //加载发布模板的信息
    initTemplateList();
    function initTemplateList() {
        var params = new Object();
        params.currentPage = $("#coco-production-list").attr("data-page");
        params.pageSize=12;
        $.post("../homework/fetch_template.action", params, function (data) {
                //处理加载的数据
                var templateContent = "";
                for (var i = 0; i < data.currentData.length; i++) {
                    templateContent += "<div class=\"col-md-3\"><div class=\"thumbnail\"><a href=\"../" + data.currentData[i].template_snapshot.split(",")[0] + "\" rel=\"prettyPhoto[pp_gal]\" title=\"Coco的作品-" + data.currentData[i].template_name + "\"><img src=\"../" + data.currentData[i].template_snapshot.split(",")[0] + "\" alt=\"点击欣赏\" class=\"img-rounded\"/></a>"
                    + "<div class=\"caption\"><h3>" + data.currentData[i].template_name + "</h3><p  class=\"coco-interaction-component\"><label><i class=\"glyphicon glyphicon-heart-empty\"></i>&nbsp;喜欢</label><label><i class=\"glyphicon glyphicon-thumbs-up\"></i>&nbsp;点赞（" + data.currentData[i].templaye_browser_num + "）</label></p><p>作品描述：" + data.currentData[i].template_describe + "</p><p><a target='_blank' href=\"../" + data.currentData[i].template_access_url + "\" class=\"btn btn-primary\" role=\"button\">查看作品</a>&nbsp;&nbsp;<a target=\"_blank\" class='btn btn-danger' href=\"../homework/template_download.action?sourceId="+data.currentData[i].template_resource_url+"\">下载模板</a></p></div></div></div>"
                }
                $("#coco-production-list").html(templateContent);
                //瀑布流化内容
                $("#coco-production-list").masonry({
                    itemSelector: '.col-md-3',
                    percentPosition: true
                });
                //初始化图片点击链接
                $("a[rel^='prettyPhoto']").prettyPhoto();
                //显示分页控件
                var option = {
                    currentPage: params.currentPage,
                    totalPages: data.totalPage,
                    numberOfPages: 5,
                    alignment: 'center',
                    bootstrapMajorVersion: 3,
                    itemTexts: function (type, page, current) {
                        switch (type) {
                            case "first":
                                return "首页";
                            case "prev":
                                return "上一页";
                            case "next":
                                return "下一页";
                            case "last":
                                return "末页";
                            case "page":
                                return page;
                        }
                    },
                    onPageClicked: function (event, originalEvent, type, page) {
                        $("#coco-production-list").attr("data-page", page);
                        initTemplateList();
                    }
                };
                $("#template_pagination").bootstrapPaginator(option);
            }
        );
    }
})
;
