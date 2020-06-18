<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>APP开发者平台</title>

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
    <%--<script src="http://libs.baidu.com/jquery/2.1.4/jquery.min.js"></script>--%>
    <script type="text/javascript">
              function log() {
                  // var code=$("[name=devCode]").val();
                  // var password=$("[name=devPassword]").val();
                  if(""!=$(".form-control").val()&&null!=$(".form-control").val()){
                      $.ajax({
                          url:"${ctx}/devuser/devlogin.do",
                          // data:{"devCode":code,"devPassword":password},
                          data:$(".logfrom").serialize(),
                          type:"post",
                          dataType:"JSON",
                          success:function (data) {
                              if(data.result=="true"){
                                  $(location).attr("href","/devuser/devindex.html")
                              }else if (data.result="false"){
                                  $("#sp").text("用户名密码不正确");
                              }
                          },
                          error:function () {
                              alert("出错")
                          }
                      })
                  }else {
                      $("#sp").text("用户名密码不能为空");
                  }
              }
    </script>
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        <div class="animate form login_form">
          <section class="login_content">
            <form action="##" method="post" class="logfrom">
              <h1>APP开发者平台</h1>
              <div>
                <input type="text" class="form-control" name="devCode" placeholder="请输入用户名" required="" />
              </div>
              <div>
                <input type="password" class="form-control" name="devPassword" placeholder="请输入密码" required="" />
              </div>
              <span id="sp"></span>
              <div>
              	<button type="button" class="btn btn-success" onclick="log()">登     录</button>
              	<button type="reset" class="btn btn-default">重　填</button>
              </div>
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