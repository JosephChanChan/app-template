<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>后台管理系统</title>
    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/statics/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${pageContext.request.contextPath }/statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${pageContext.request.contextPath }/statics/css/nprogress.css" rel="stylesheet">
    <!-- Animate.css -->
    <%--<link href="https://colorlib.com/polygon/gentelella/css/animate.min.css" rel="stylesheet">--%>

    <!-- Custom Theme Style -->
    <link href="${pageContext.request.contextPath }/statics/css/custom.min.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/statics/js/jquery-1.8.3.min.js"></script>
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>
      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form action="#" method="post" id="fm">
              <input type="hidden" name="realm" value="backend"/>
              <h1>后台管理系统</h1>
              <div>
                <input type="text" class="form-control" name="loginName" placeholder="请输入用户名" required="" />
              </div>
              <div>
                <input type="password" class="form-control" name="cipher" placeholder="请输入密码" required="" />
              </div>
              <span id="msg"></span>
              <div>
              	<button id="dl" type="button" class="btn btn-success">登     录</button>
              	<button type="reset" class="btn btn-default">重　填</button>
              </div>
              <script type="text/javascript">
                $("#dl").on("click",function () {
                    var params=$("#fm").serialize();
                    $.ajax({
                        url:"${ctx}/auth/login",
                        type:"post",
                        data:params,
                        dataType:"json",
                        success:function (result) {
                            if(result.data === true){
                                $(location).attr("href","${ctx}/backend/mainPage")
                            }else {
                                $("#msg").html("用户名密码不正确")
                            }
                        },
                        error:function () {
                            alert("请求失败")
                        }
                    })
                })
              </script>
              <div class="clearfix"></div>

              <div class="separator">
                <div>
                  <p>2018.9月 广州菁英-JT01</p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>