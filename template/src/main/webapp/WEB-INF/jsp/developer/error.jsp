<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>403</title>

    <!-- Bootstrap -->
    <link href="${ctx }/statics/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome -->
    <link href="${ctx }/statics/css/font-awesome.min.css" rel="stylesheet">
    <!-- NProgress -->
    <link href="${ctx }/statics/css/nprogress.css" rel="stylesheet">
    <!-- Custom Theme Style -->
    <link href="${ctx }/statics/css/custom.min.css" rel="stylesheet">
  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <!-- page content -->
        <div class="col-md-12">
          <div class="col-middle">
            <div class="text-center">
              <h1 class="error-number">403</h1>
              <h2>${msg}</h2>
              <p>请返回到 <a href="${ctx}/devuser/devindex.html">系统入口</a>
              </p>
            </div>
          </div>
        </div>
        <!-- /page content -->
      </div>
    </div>

    <!-- jQuery -->
    <script src="${ctx}/statics/js/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${ctx }/statics/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="${ctx }/statics/js/fastclick.js"></script>
    <!-- NProgress -->
    <script src="${ctx }/statics/js/nprogress.js"></script>
    <!-- Custom Theme Scripts -->
    <script src="${ctx }/statics/js/custom.min.js"></script>
  </body>
</html>				
			