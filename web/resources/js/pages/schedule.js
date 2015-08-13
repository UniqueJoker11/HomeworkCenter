/**
 * Created by DELL on 2015/8/4.
 */
$('#calendar').fullCalendar({
    header: {
        left: 'title',
        center: 'today,month,agendaDay,basicWeek',
        right: 'prev,next'
    },
    editable: true,
    eventLimit: true, // allow "more" link when too many events
    events: {
        url: 'fetch_current_schedule.action',

        error: function () {
            $('#script-warning').show();
        },
        editable: true,
        backgroundColor: '#87CEFA',
        color: '#87CEEB',     // an option!
        textColor: '#212121'// an option!
    },
    eventClick: function (calEvent, jsEvent, view) {

        alert('Event: ' + calEvent.title);
        alert('Coordinates: ' + jsEvent.pageX + ',' + jsEvent.pageY);
        alert('View: ' + view.name);
        event.title = "CLICKED!";

        $('#calendar').fullCalendar('updateEvent', event);
        // change the border color just for fun
        $(this).css('border-color', 'red');

    },

    loading: function (bool) {
        $('#loading').toggle(bool);
    },
    eventRender: function (event, el) {
        // render the timezone offset below the event title
        if (event.start.hasZone()) {
            el.find('.fc-title').after(
                $('<div class="tzo"/>').text(event.start.format('Z'))
            );
        }
    }

});
$(function () {
    //加载今日的时间安排
    initTodaySchedule();
    function initTodaySchedule() {
        var params = new Object();
        var date = new Date();
        var searchDate = date.getFullYear() + "-" + (date.getMonth() < 9 ? "0" + (date.getMonth() + 1) : (date.getMonth() + 1)) + "-" + (date.getDate() < 10 ? "0" + date.getDate() : date.getDate());
        params.start = searchDate + " 00:00:00";
        params.end = searchDate + " 24:00:00";
        $.post('fetch_today_schedule.action', params, function (data) {
            if (data.length > -1) {
                var eventHtml = "";
                for (var i = 0; i < data.length; i++) {
                    eventHtml += "<div class='external-event'>" + data[i].title + "</div>";
                }
                $("#external-events").html(eventHtml);
            }
        });
    }
    $("#colin-pub-btn").on("click",function(e){
        $("#colin-pub-schedule").slideToggle();
    });
    //初始化日历控件
    $("#schedule_start").datetimepicker({
        autoclose:true,
        todayBtn:true
    });
    $("#schedule_end").datetimepicker({
        autoclose:true,
        todayBtn:true
    });
    //绑定表单验证
    var validator =$("#colin-pub-form").validate({
        debug : false,// //调试模式取消submit的默认提交功能
        submitHandler : function(form) { // 表单提交句柄,为一回调函数，带一个参数：form
            form.submit(); // 提交表单
        },
        focusCleanup : true,
        focusInvalid : false, // 当为false时，验证无效时，没有焦点响应
        rules:{
            schedule_title:{
                required:true
            },
            schedule_start:{
                required:true,
                date:true
            },
            schedule_end:{
                required:true,
                date:true
            }
        },
        messages:{
            schedule_title:{
                required:'日程内容不能为空！'
            },
            schedule_start:{
                required:"起始日期不能为空",
                date:"日期格式不正确"
            },
            schedule_end:{
                required:"截止日期不能为空",
                date:"日期格式不正确"
            }
        }
    });
    //提交表单
    $("#colin-pub-btn-submit").on("click",function(e){
        if(validator.form()){
            $.post("./publish_current_schedule.action",$("#colin-pub-form").serialize(),function(data){
               if(data.isSuccess){
                   //重新绘制日历，发布新的事情
                   $('#calendar').fullCalendar("refetchEvents");
                   initTodaySchedule();
                   $("#colin-pub-btn").trigger("click");
                   validator.resetForm();
               }
            })
        }
        return false;
    });
    $("#colin-pub-btn-reset").on("click",function(e){
        validator.resetForm();
    });
});
