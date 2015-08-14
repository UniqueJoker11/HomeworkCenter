/**
 * Created by ASUS on 2015/7/12.
 */
$(function () {
    $("#by-signup-form").validate({
        focusInvalid : false, // 当为false时，验证无效时，没有焦点响应
        onkeyup : false,
        submitHandler : function(form) { // 表单提交句柄,为一回调函数，带一个参数：form
            alert("提交表单");
            form.submit(); // 提交表单
        },
        rules : {
            username : {
                required : true,
                rangelength : [ 4, 20 ],
                remote : {
                    url : 'usernamevalidate',
                    type : 'post',
                    dataType : "json",
                    data : {
                        username : function() {
                            return $("#username").val();
                        }
                    },
                    dataFilter : function(data, type) {
                        if (data == "false")
                            return true;
                        else
                            return false;
                    }
                }

            },
            password : {
                required : true,
                rangelength : [ 4, 20 ],
            },
            confirmPassword : {
                required : true,
                rangelength : [ 4, 20 ],
                equalTo : '#password'
            }
        },
        messages : {
            username:{
                required:'！用户名不能为空',
                rangelength:"！用户名长度在4~20个字符之间",
                remote:"！用户名已经存在"
            },
            password:{
                required:"!密码不能为空",
                rangelength:"!密码长度在4~20个字符之间",
            },
            confirmPassword:{
                required:"！确认密码不能为空！",
                rangelength:"!确认密码长度在4~20个字符之间",
                equalTo:"1确认密码和密码不统一！"
            }
        }
    });
});
