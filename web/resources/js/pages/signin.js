/**
 * Created by ASUS on 2015/7/11.
 */
jQuery(document).ready(function () {
    var accessUrl="/HomeworkCenter/homework/";
    $("#homework_signin").validate({
        debug : false,// //调试模式取消submit的默认提交功能
        submitHandler : function(form) { // 表单提交句柄,为一回调函数，带一个参数：form
            form.submit(); // 提交表单
        },
        focusCleanup : true,
        focusInvalid : false, // 当为false时，验证无效时，没有焦点响应

        rules : {
            username : {
                required : true,
                rangelength : [ 4, 20 ],
                remote : {
                    url : accessUrl+"usernamevalidate.action",
                    type : 'post',
                    dataType : "json",
                    data : {
                        username : function() {
                            return $("#username").val();
                        }
                    },
                    dataFilter : function(data, type) {
                        if (data == "true")
                            return true;
                        else
                            return false;
                    }
                },
            },
            password : {
                required : true,
                rangelength : [ 4, 20 ]
            }
        },
        messages : {
            username : {
                required : '用户名不能为空！',
                rangelength : '用户名的字符长度在4,20位之间',
                remote:'用户名不正确！'
            },
            password : {
                required : '用户名密码不能为空',
                rangelength : '用户名密码长度在4,20位之间'
            }
        }
    });
});
