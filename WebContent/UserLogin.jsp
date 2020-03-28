<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>台股練兵系統</title>
<link href="https://fonts.googleapis.com/css?family=Open+Sans:300,600" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="css/UserLogin.css">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+TC&display=swap" rel="stylesheet"> 
</head>
<body>
<section class="ff-login">
    <div class="container">
        <div class="row">
            <div class="col-sm-12">
                <div class="ff-login-box">
                    <form method="post" name="lForm">
                        <h2 class="title">台股練兵系統</h2>
                        <input type="text" class="form-control form-control-lg font-weight-light mt-4" placeholder="Username" name="userName"><span class="wrong">${errorMsgMap.AccountEmptyError}</span>
                        <input type="password" class="form-control form-control-lg font-weight-light mt-3" placeholder="Password" name="userPassword"><span class="wrong">${errorMsgMap.PasswordEmptyError}</span>
                        <div class="custom-control custom-checkbox ml-4 mt-3">
                            <input type="checkbox" name="rememberMe" class="custom-control-input" id="rememberMe">
                            <label class="custom-control-label" for="rememberMe">Remember Me</label>
                        </div>
                        <input type="submit" value="Login" class="btn btn-primary btn-lg mt-3 ff-login-btn font-weight-bold" onclick="document.lForm.action='LoginController'"/><span class="wrong">${errorMsgMap.LoginError}</span><span class="registerOK">${msgMapFromRegister.registerOK}</span>
                    	<input type="submit" value="Create an account" class="btn btn-primary btn-lg mt-3 ff-login-btn font-weight-bold" onclick="document.lForm.action='Register.jsp'"/>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>
<h3 class="title">今日大盤</h3>
<div class="TAIEX">${TAIEX}</div>

</body>
</html>