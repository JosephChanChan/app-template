<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>新增APP基础信息 <i class="fa fa-user"></i><small>${devUserSession.devName}</small></h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <div class="clearfix"></div>
                <form class="form-horizontal form-label-left" action="${ctx}/dev/addApp" method="post"
                      enctype="multipart/form-data">
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="softwareName">软件名称 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input id="softwareName" class="form-control col-md-7 col-xs-12"
                                   data-validate-length-range="20" data-validate-words="1" name="softwareName"
                                   required="required"
                                   placeholder="请输入软件名称" type="text">
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="APKName">APK名称 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input id="APKName" class="form-control col-md-7 col-xs-12"
                                   data-validate-length-range="20" data-validate-words="1" name="APKName"
                                   required="required"
                                   placeholder="请输入APK名称" type="text">
                        </div>
                    </div>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="supportROM">支持ROM <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input id="supportROM" class="form-control col-md-7 col-xs-12" name="supportROM"
                                   data-validate-length-range="20" data-validate-words="1" required="required"
                                   placeholder="请输入支持的ROM" type="text">
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="interfaceLanguage">界面语言 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input id="interfaceLanguage" class="form-control col-md-7 col-xs-12"
                                   data-validate-length-range="20" data-validate-words="1" name="interfaceLanguage"
                                   required="required"
                                   placeholder="请输入软件支持的界面语言" type="text">
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="softwareSize">软件大小 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="number" id="softwareSize" name="softwareSize" required="required"
                                   onkeyup="value=value.replace(/[^\d]/g,'')"
                                   data-validate-minmax="10,500" placeholder="请输入软件大小，单位为Mb"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="downloads">下载次数 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="number" id="downloads" name="downloads" required="required"
                                   data-validate-minmax="10,500" placeholder="请输入下载次数"
                                   class="form-control col-md-7 col-xs-12">
                        </div>
                    </div>

                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="flatformId">所属平台 <span
                                class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="flatformId" id="flatformId" class="form-control" required="required">

                                <c:if test="${flatFormList !=null}">
                                    <option value="">请选择</option>
                                    <c:forEach items="${flatFormList}" var="flatForm">
                                        <option
                                                <c:if test="${flatForm.valueId== flatformId}">
                                                    selected="selected"
                                                </c:if>
                                                value="${flatForm.valueId}">
                                                ${flatForm.valueName}
                                        </option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="categoryLevel1">一级分类 <span
                                class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="categoryLevel1" id="categoryLevel1" class="form-control" required="required">
                                <c:if test="${categoryLevelList1!=null}">
                                    <option value="">请选择</option>
                                    <c:forEach items="${categoryLevelList1}" var="category">
                                        <option
                                                <c:if test="${category.id == categoryLevel1}">
                                                    selected="selected"
                                                </c:if>
                                                value="${category.id}">${category.categoryName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $("#categoryLevel1").change(function () {
                            var c1 = $("#categoryLevel1").val();
                            if (c1 != null && c1 != "") {
                                $.ajax({
                                    url: "${ctx}/dev/categoryList",
                                    type: "get",
                                    data:{id:c1},
                                    dataType: "json",
                                    success: function (result) {
                                      var data = result.data;
                                        $("#categoryLevel2").html("");
                                        var op = "<option value=''>请选择</option>";
                                        for (var i = 0; i < data.length; i++) {
                                            op += "<option value='" + data[i].id + "'>" + data[i].categoryName + "</option>"
                                        }
                                        $("#categoryLevel2").html(op);
                                    },
                                    error: function () {
                                        alert("失败")
                                    }
                                })
                            }
                        })
                    </script>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="categoryLevel2">二级分类 <span
                                class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="categoryLevel2" id="categoryLevel2" class="form-control "
                                    required="required"></select>
                        </div>
                    </div>
                    <script type="text/javascript">
                        $("#categoryLevel2").change(function () {
                            var c2 = $("#categoryLevel2").val();
                            if (c2 != null && c2 != '') {
                                $.ajax({
                                    url: "${ctx}/dev/categoryList",
                                    type: "get",
                                    data:{id:c2},
                                    dataType: "json",
                                    success: function (result) {
                                      var data = result.data;
                                        $("#categoryLevel3").html("");
                                        var op = "<option value=''>请选择</option>";
                                        for (var i = 0; i < data.length; i++) {
                                            op += "<option value='" + data[i].id + "'>" + data[i].categoryName + "</option>"
                                        }
                                        $("#categoryLevel3").html(op);
                                    },
                                    error: function () {
                                        alert("失败")
                                    }
                                })
                            }
                        })
                    </script>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="categoryLevel3">三级分类 <span
                                class="required">*</span></label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <select name="categoryLevel3" id="categoryLevel3" class="form-control"
                                    required="required"></select>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="status">APP状态 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="hidden" name="status" id="status" value="1">待审核
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="appInfo">应用简介 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
              <textarea id="appInfo" name="appInfo" required="required"
                        placeholder="请输入本软件的相关信息，本信息作为软件的详细信息进行软件的介绍。"
                        class="form-control col-md-7 col-xs-12"></textarea>
                        </div>
                    </div>
                    <div class="item form-group">
                        <label class="control-label col-md-3 col-sm-3 col-xs-12" for="logoPicture">LOGO图片 <span
                                class="required">*</span>
                        </label>
                        <div class="col-md-6 col-sm-6 col-xs-12">
                            <input type="file" class="form-control col-md-7 col-xs-12" name="logoPicture"
                                   required="required" id="logoPicture"/>
                            ${msg}
                        </div>
                    </div>
                    <div class="ln_solid"></div>
                    <div class="form-group">
                        <div class="col-md-6 col-md-offset-3">
                            <button id="send" type="submit" class="btn btn-success">保存</button>
                            <button type="button" class="btn btn-primary" id="back">返回</button>
                            <script type="text/javascript">
                                $("#back").on("click", function () {
                                    history.go(-1);
                                })
                            </script>
                            <br/><br/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>
<%--<script src="${pageContext.request.contextPath }/statics/localjs/appinfoadd.js"></script>--%>