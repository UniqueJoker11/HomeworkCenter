/**
 * Created by ASUS on 2015/9/30.
 */
var aticleDatagrid;
$(function(){
    //初始化文章列表
    aticleDatagrid=$("#aticleListTable").dataTable({
        "bPaginate": true, //翻页功能
        "bLengthChange": true, //改变每页显示数据数量
        "bFilter": true, //过滤功能
        "bSort": true, //排序功能
        "bInfo": true,//页脚信息
        "bAutoWidth": true,//自动宽度
        "oLanguage": {
            "sLengthMenu": "每页显示 _MENU_条",
            "sZeroRecords": "没有找到符合条件的数据",
            "sProcessing": "&lt;img src=’./loading.gif’ /&gt;",
            "sInfo": "当前第 _START_ - _END_ 条　共计 _TOTAL_ 条",
            "sInfoEmpty": "木有记录",
            "sInfoFiltered": "(从 _MAX_ 条记录中过滤)",
            "sSearch": "搜索：",
            "oPaginate": {
                "sFirst": "首页",
                "sPrevious": "前一页",
                "sNext": "后一页",
                "sLast": "尾页"
            }}
        });

});
//查看文章
function checkAticleInfo(aticleId){

}
//开始编辑文章
function editAticleInfo(aticleId){


}
//删除文章
function delAticleInfo(aticleId){
    if(window.confirm("确定要删除该篇文章吗？")){
        var params=new Object();
        params.aticleId=aticleId;
        $.post("./aticle_delete.action",params,function(data){
            if(data){
                aticleDatagrid.
                alert("删除文章成功");
            }else{
                alert("删除文章失败");
            }
        });
    }
}
