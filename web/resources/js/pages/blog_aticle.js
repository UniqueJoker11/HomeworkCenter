$(function () {
    //初始化分页加载文章
    initBlogList();
    function initBlogList() {
        var params = new Object();
        params.navId=$("body").attr("data-navId");
        params.startIndex = $("#blogInfoList").attr("data-index");
        params.pageSize=10;
        fetchRemoteBlogList(params);
    };
    function fetchRemoteBlogList(params) {
        $.ajax({
            url: './fetch_blog_aticle_list.action',
            type: 'post',
            data: params,
            dataType: "json",
            beforeSend: function (XMLHttpRequest) {
                var ajaxLoadingTemplate = "<div id=\"blogListLoading\" class=\"text-center blog-list-loading\"><i class=\"fa fa-refresh fa-spin\"></i></div>";
                $("#blogInfoList").prepend(ajaxLoadingTemplate);
            },
            complete: function (XMLHttpRequest) {
                $("#blogInfoList").find("#blogListLoading").remove();
            },
            success: function (data, textStatus, jqXHR) {
                if(!data.success&&$("#blogInfoList").find(".panel").length==0){
                    alert("该栏目下没有内容！");
                }else if(!data.success&& $("#blogInfoList").find(".panel").length!=0){
                    alert("已经是最后一页了！");
                }else{
                    var blogList=data.data;
                    //循环展示文章内容
                    showBlogListInfo(blogList.currentData);
                    var blogTemplatePage = "<nav class=\"text-center\"><ul class='pagination pagination-lg'>"
                    //第一页，上一页不可用
                    if (blogList.currentPage == 1) {
                        blogTemplatePage += "<li id=\"previousPage\" class=\"disabled\"><a class=\"btn btn-default btn-lg\" href=\"javascript:;\"aria-label=\"Previous\">上一页</a></li>";
                    } else {
                        blogTemplatePage += "<li id=\"previousPage\"><a class=\"btn btn-default btn-lg\" href=\"javascript:;\"aria-label=\"Previous\">上一页</a></li>";
                    }
                    //最后一页，下一页不可用
                    if (blogList.currentPage == data.totalPage) {
                        blogTemplatePage += "<li id=\"nextPage\" class=\"disabled\"><a class=\"btn btn-default btn-lg\" href=\"javascript:;\"aria-label=\"Previous\">下一页</a></li>";
                    } else {
                        blogTemplatePage += "<li id=\"nextPage\"><a class=\"btn btn-default btn-lg\" href=\"javascript:;\"aria-label=\"Previous\">下一页</a></li>"
                    }
                    blogTemplatePage += "</ul></nav>";
                    $("#blogInfoList").append(blogTemplatePage);
                    initPrevAndNextPageContent();
                }
            }
        });
    };
    /**
     * 初始化博客内容
     * @param blogList
     */
    function showBlogListInfo(blogList) {
        var blogTemplateContent = "";
        $.each(blogList, function (i, blog) {
            var createTime=blog.aticle_createtime.split("-");
            blogTemplateContent += "<div class=\"panel panel-default\">" +
            "<div class=\"panel-heading text-center\">" +
            "<header class=\"pull-left blog-header-time\"><p>"+createTime[0]+"</p><strong>"+createTime[1]+"月</strong></header>"+
            "<a target=\"_blank\" href=\"./blog_detail.html?aticleId=" + blog.aticle_id + "\"><h3>" + blog.aticle_name + "</h3></a>" +
            "<small>作者：" + blog.aticle_author + "</small>" +
            "</div>" +
            "<div class=\"panel-body\"><blockquote class=\"blog-info-digest\">" +
            "<p>" + blog.aticle_digest + "</p>" +
            "</blockquote>" + blog.aticle_content+
            "</div>" +
            "<div class=\"panel-footer\">标签:&nbsp;";
            var keywords = blog.key_words.split("，");
            for (var tip in keywords) {
                blogTemplateContent += "&nbsp;<span class=\"label label-default\">" + keywords[tip] + "</span>&nbsp;"
            }
            blogTemplateContent += "<a class=\"pull-right\" target=\"_blank\" href='./blog_detail.html?aticleId=" + blog.aticle_id + "'>阅读全文</a></div></div>";
        });
        $("#blogInfoList").html(blogTemplateContent);

    }

    function initPrevAndNextPageContent() {
        var $blogPageObj = $("#blogInfoList");
        $("#previousPage >a").on("click", function (e) {
            var pageIndex = parseInt($blogPageObj.attr("data-index")) - 1;
            $blogPageObj.attr("data-index", pageIndex);
            initBlogList();
            e.preventDefault();
        });
        $("#nextPage >a").on("click", function (e) {
            var pageIndex = parseInt($blogPageObj.attr("data-index")) + 1;
            $blogPageObj.attr("data-index", pageIndex);
            initBlogList();
            e.preventDefault();
        });
    }
});