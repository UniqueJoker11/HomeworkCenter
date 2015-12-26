/**
 * Created by DELL on 2015/12/25.
 */
var aticleClassifyDatagrid=null;
$(function () {
    var extendOptions = {
        ajax: "./aticle_classify_all.action",
        "columns": [
            {"data": "classify_index"},
            {"data": "classify_name"},
            {"data": "classify_createtime"},
            {"data": "classify_createuser"},
            {"data": "classify_operation"}
        ]
    };
    $.extend(options, extendOptions);
    aticleClassifyDatagrid = $("#aticleClassifyTable").DataTable(options);
    //过滤表格的显示效果
    aticleClassifyDatagrid.on('xhr', function () {
        var result = aticleClassifyDatagrid.ajax.json().data;
        $.each(result, function (i, e) {
            e.classify_index = i;
            e.classify_operation = "<button class=\"btn btn-info\" onclick=\"editAticleClassify('" + e.classify_id + "')\">编辑 </button>" +
            "&nbsp;<button class=\"btn btn-danger\" onclick=\"delAticleClassify(\'" + e.classify_id + "\')\">删除</button>"
        });
        return result;
    });
    //添加文章分类
    $("#addAticleClassifyBtn").on("click", function () {
        $("#addAticleClassifyDialog").modal("show");
    });
});
//添加文章分类
function addAticleClassify() {
    var classifyNameVal = $.trim($("#addAticleClassifyName").val());
    if (classifyNameVal == "") {
        alert("文章分类不能为空！");
    } else {
        var params = new Object();
        params.classifyName = classifyNameVal;
        $.post("./aticle_classify_add.action", params, function (data) {
            if (data.success) {
                aticleClassifyDatagrid.ajax.reload();
                $("#addAticleClassifyDialog").modal("hide")
            }
            $("#addAticleClassifyName").val("");
        });
    }
}
function editAticleClassify(classifyId){
    //初始化编辑对话框
    $eventObj=$(event.target);
    $("#editAticleClassifyName").attr("data-id",classifyId).val($eventObj.parent("td").siblings("td:eq(1)").html());
    $("#editAticleClassifyDialog").modal("show");
}
//更新文章分类
function updateAticleClassifyInfo(){
    var classifyNameVal = $.trim($("#editAticleClassifyName").val());
    if (classifyNameVal == "") {
        alert("文章分类不能为空！");
    } else {
        var params = new Object();
        params.classifyId=$("#editAticleClassifyName").attr("data-id");
        params.classifyName = classifyNameVal;
        $.post("./aticle_classify_update.action", params, function (data) {
            if (data.success) {
                aticleClassifyDatagrid.ajax.reload();
                $("#editAticleClassifyDialog").modal("hide")
            }
            $("#editAticleClassifyName").val("");
        });
    }
}
function  delAticleClassify(classifyId){
  if(window.confirm("确定要删除当前分类吗？")){
      var params = new Object();
      params.classifyId=classifyId;
      $.post("./aticle_classify_del.action",params,function(data){
           if(data.success){
               aticleClassifyDatagrid.ajax.reload();
           }
      });
  }
}