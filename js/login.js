$(document).ready(function () {

    var loginButton = $('.login__button button')
    loginButton.click(function () {
        let email = $('.login__email input').val();
        let password = $('.login__password input').val();
        $.ajax({
            type: "POST",
            url: "http://localhost:8080/login/signIn",
            headers: {
                "Content-Type": "application/json",
            },
            data: JSON.stringify({
                "username": email,
                "password": password,
            }),
            success: function (rs) {
                console.log(rs);
                if (rs.succes) {
                    localStorage.setItem("token", rs.data);
                    localStorage.setItem("username", email);
                    window.location = "./index.html";
                }else{
                    alert("Đăng nhập thất bại");
                }
            }
        });
    })

});
