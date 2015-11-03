/**
 * Created by DELL on 2015/7/27.
 */
$(function () {
    "use strict";

    function showTooltip(x, y, contents) {
        jQuery('<div id="tooltip" class="tooltipflot">' + contents + '</div>').css({
            position: 'absolute',
            display: 'none',
            top: y + 5,
            left: x + 5
        }).appendTo("body").fadeIn(200);
    }

    var uploads = [[0, 2], [1, 9], [2, 5], [3, 13], [4, 6], [5, 13], [6, 8]];
    var downloads = [[0, 0], [1, 6.5], [2, 4], [3, 10], [4, 2], [5, 10], [6, 4]];



    var previousPoint = null;
    jQuery("#basicflot").bind("plothover", function (event, pos, item) {
        jQuery("#x").text(pos.x.toFixed(2));
        jQuery("#y").text(pos.y.toFixed(2));

        if (item) {
            if (previousPoint != item.dataIndex) {
                previousPoint = item.dataIndex;

                jQuery("#tooltip").remove();
                var x = item.datapoint[0].toFixed(2),
                    y = item.datapoint[1].toFixed(2);

                showTooltip(item.pageX, item.pageY,
                    item.series.label + " of " + x + " = " + y);
            }

        } else {
            jQuery("#tooltip").remove();
            previousPoint = null;
        }

    });

    jQuery("#basicflot").bind("plotclick", function (event, pos, item) {
        if (item) {
            plot.highlight(item.series, item.datapoint);
        }
    });


    // Trigger Resize in Morris Chart
    var delay = (function () {
        var timer = 0;
        return function (callback, ms) {
            clearTimeout(timer);
            timer = setTimeout(callback, ms);
        };
    })();


    jQuery('#sparkline').sparkline([4, 3, 3, 1, 4, 3, 2, 2, 3, 10, 9, 6], {
        type: 'bar',
        height: '30px',
        barColor: '#428BCA'
    });


    jQuery('#sparkline2').sparkline([9, 8, 8, 6, 9, 10, 6, 5, 6, 3, 4, 2], {
        type: 'bar',
        height: '30px',
        barColor: '#999'
    });

    // Do not use the code below. It's for demo purposes only
    var c = jQuery.cookie('change-skin');
    if (jQuery('.panel-stat').length > 0 && c == 'dodgerblue') {
        jQuery('.panel-stat').each(function () {
            if ($(this).hasClass('panel-danger')) {
                $(this).removeClass('panel-danger').addClass('panel-warning');
            }
        });
    }

    if (jQuery('#basicflot').length > 0 && c == 'greyjoy') {
        plot.setData([{
            data: uploads,
            color: '#dd5702',
            label: 'Uploads',
            lines: {
                show: true,
                fill: true,
                lineWidth: 1
            },
            splines: {
                show: false
            }
        },
            {
                data: downloads,
                color: '#cc0000',
                label: 'Downloads',
                lines: {
                    show: true,
                    fill: true,
                    lineWidth: 1
                },
                splines: {
                    show: false
                }
            }]);
        plot.draw();
    }

    jQuery("#colin-dashboard-menu").find(".children").children("li").bind("click", function (e) {
        var hrefVal = $(this).children("a").attr("href");
        $("#rightPanel").load(hrefVal);
        resizeWindow();
        e.preventDefault();
        return false;
    });
    //重新绘制页面的高度
    function resizeWindow(){
        //移除初始化高度
        $(".mainpanel").removeAttr("style");
        var leftPanelHeight=$(".leftpanel").innerHeight();
        var rightPanelHeight=$("#rightPanel").innerHeight();
        $(window).height(leftPanelHeight<rightPanelHeight?rightPanelHeight:leftPanelHeight);
    }
});
