/**
 * Created by DELL on 2015/8/4.
 */
$('#calendar').fullCalendar({
    header: {
        left: '上一个,下一个,今天',
        center: '标题',
        right: '月份,周心情,今日心情'
    },
    editable: true,
    eventLimit: true, // allow "more" link when too many events
    events: {
        url: './fetch_current_templer.action',
        error: function() {
            $('#script-warning').show();
        }
    },
    loading: function(bool) {
        $('#loading').toggle(bool);
    },
    eventRender: function(event, el) {
        // render the timezone offset below the event title
        if (event.start.hasZone()) {
            el.find('.fc-title').after(
                $('<div class="tzo"/>').text(event.start.format('Z'))
            );
        }
    }

})
