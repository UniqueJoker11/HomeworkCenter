/**
 * Created by ASUS on 2015/7/12.
 */
$(function () {
    //綁定驗證表單的功能
    $("#homework_signup").validate({
        debug: false,// //调试模式取消submit的默认提交功能
        submitHandler: function (form) { // 表单提交句柄,为一回调函数，带一个参数：form
            form.submit(); // 提交表单
        },
        focusCleanup: true,
        focusInvalid: false, // 当为false时，验证无效时，没有焦点响应
        rules: {
            username: {
                required: true,
                rangelength: [4, 20],
                remote: {
                    url: './usernamevalidate.action',
                    type: 'post',
                    dataType: "json",
                    data: {
                        username: function () {
                            return $("#username").val();
                        }
                    },
                    dataFilter: function (data, type) {
                        if (data == "true")
                            return false;
                        else
                            return true;
                    }
                }
            },
            password:{
                required:true,
                rangelength:[6, 30]
            },
            confirm_password:{
                required:true,
                rangelength:[6, 30],
                equalTo:"#password"
            },
            email:{
                required:true,
                email:true
            }
        },
        message:{
            username: {
                required: "用户名不能为空",
                rangelength: "用户名长度在4~30个字符之间",
                remote:"用户名已经存在，请重新输入"
            },
            password:{
                required:"密码不能为空",
                rangelength:"密码长度在6~30个字符之间"
            },
            confirm_password:{
                required:"确认密码不能为空",
                rangelength:"确认密码长度在6~30个字符之间",
                equalTo:"确认密码和密码不一致"
            },
            email:{
                required:"邮箱不能为空",
                email:"邮箱格式不正确"
            }
        }
    });
});
