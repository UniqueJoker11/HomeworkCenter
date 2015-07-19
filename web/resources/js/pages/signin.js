/**
 * Created by ASUS on 2015/7/11.
 */
jQuery(document).ready(function () {
    // Please do not use the code below
    // This is for demo purposes only
    /*var c = jQuery.cookie('change-skin');
     if (c && c == 'greyjoy') {
     jQuery('.btn-success').addClass('btn-orange').removeClass('btn-success');
     } else if (c && c == 'dodgerblue') {
     jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
     } else if (c && c == 'katniss') {
     jQuery('.btn-success').addClass('btn-primary').removeClass('btn-success');
     }*/

    $("#homework_signin").validate({
        rules:{
            username:{
                required:true,
                rangelength:[6,20]
            },
            password:{
                required:true,
                minlength:6
            }
        },
        messages:{
            username:{
                required:"用戶名不能爲空",
                rangelength:"用戶名的長度在6~20個字符之間"
            },
            password:{
                required:"密碼不能爲空！",
                minlength:"密碼的長度不能少於6個字符"
            }
        },
        submitHandler: function(form) {
            console.log(form);
            $(form).ajaxSubmit();
        }
    });
});
