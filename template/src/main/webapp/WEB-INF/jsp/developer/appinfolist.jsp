<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="common/header.jsp" %>
<div class="clearfix"></div>
<div class="row">
    <div class="col-md-12">
        <div class="x_panel">
            <div class="x_title">
                <h2>
                    APP 信息管理维护 <i class="fa fa-user"></i><small>${devUserSession.devName}
                    - 您可以通过搜索或者其他的筛选项对APP的信息进行修改、删除等管理操作。^_^</small>
                </h2>
                <div class="clearfix"></div>
            </div>
            <div class="x_content">
                <form method="get" action="${ctx}/dev/appListPage">
                    <input type="hidden" name="current" value="1"/>
                    <ul>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input name="softwareName" type="text" class="form-control col-md-7 col-xs-12"
                                           value="${param.softwareName}">
                                </div>
                            </div>
                        </li>

                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">APP状态</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="status" class="form-control">
                                        <c:if test="${statusList != null }">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="dataDictionary" items="${statusList}">
                                                <option
                                                        <c:if test="${dataDictionary.valueId == param.status }">selected="selected"</c:if>
                                                        value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">所属平台</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="flatformId" class="form-control">
                                        <c:if test="${flatFormList != null }">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="dataDictionary" items="${flatFormList}">
                                                <option
                                                        <c:if test="${dataDictionary.valueId == param.flatformId }">selected="selected"</c:if>
                                                        value="${dataDictionary.valueId}">${dataDictionary.valueName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">一级分类</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select id="categoryLevel1" name="categoryLevel1"
                                            class="form-control categoryLevel">
                                        <c:if test="${map.categoryLevelList1 != null }">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="appCategory" items="${map.categoryLevelList1}">
                                                <option
                                                        <c:if test="${appCategory.id == param.categoryLevel1 }">selected="selected"</c:if>
                                                        value="${appCategory.id}">${appCategory.categoryName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">二级分类</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <input type="hidden" name="categorylevel2list" id="categorylevel2list"/>
                                    <select name="categoryLevel2" id="categoryLevel2"
                                            class="form-control categoryLevel ">
                                        <c:if test="${map.categoryLevelList2 != null }">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="appCategory" items="${map.categoryLevelList2}">
                                                <option
                                                        <c:if test="${appCategory.id == param.categoryLevel2 }">selected="selected"</c:if>
                                                        value="${appCategory.id}">${appCategory.categoryName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <li>
                            <div class="form-group">
                                <label class="control-label col-md-3 col-sm-3 col-xs-12">三级分类</label>
                                <div class="col-md-6 col-sm-6 col-xs-12">
                                    <select name="categoryLevel3" id="categoryLevel3"
                                            class="form-control categoryLevel">
                                        <c:if test="${map.categoryLevelList3 != null }">
                                            <option value="">--请选择--</option>
                                            <c:forEach var="appCategory" items="${map.categoryLevelList3}">
                                                <option
                                                        <c:if test="${appCategory.id == param.categoryLevel3 }">selected="selected"</c:if>
                                                        value="${appCategory.id}">${appCategory.categoryName}</option>
                                            </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                        </li>
                        <script type="text/javascript">
                            //三级联动 公用ajax
                            $(function () {
                                $(".categoryLevel").change(function () {
                                    var obj = $(this);
                                    var va = obj.val();
                                    if (va != null && va != '') {
                                        $.ajax({
                                            url: "${ctx}/dev/categoryList",
                                            data:{id:va},
                                            Type: "get",
                                            dataType: "json",
                                            success: function (result) {
                                                obj.parents("li").nextAll("li").find(".categoryLevel").html("");
                                                var op = "<option value=''>请选择</option>";
                                                var list = result.data;
                                                for (var i = 0; i < list.length; i++) {
                                                    op += "<option value='" + list[i].id + "'>" + list[i].categoryName + "</option>"
                                                }
                                                obj.parents("li").next("li").find(".categoryLevel").html(op);
                                            },
                                            error: function () {
                                                console.error("something wrong!!");
                                            }
                                        })
                                    }
                                })
                            })
                        </script>
                        <li>
                            <button type="submit" class="btn btn-primary"> 查 &nbsp;&nbsp;&nbsp;&nbsp;询</button>
                        </li>
                    </ul>
                </form>
            </div>
        </div>
    </div>
    <div class="col-md-12 col-sm-12 col-xs-12">
        <div class="x_panel">
            <div class="x_content">
                <p class="text-muted font-13 m-b-30"></p>
                <div id="datatable-responsive_wrapper"
                     class="dataTables_wrapper form-inline dt-bootstrap no-footer">
                    <div class="row">
                        <div class="col-sm-12">
                            <a href="${ctx}/dev/addAppPage" class="btn btn-success btn-sm">新增APP基础信息</a>
                            <table id="datatable-responsive"
                                   class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
                                   cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info"
                                   style="width: 100%;">
                                <thead>
                                <tr role="row">
                                    <th class="sorting_asc" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="First name: activate to sort column descending"
                                        aria-sort="ascending">软件名称
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        APK名称
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        软件大小(单位:M)
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        所属平台
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        所属分类(一级分类、二级分类、三级分类)
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        状态
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        下载次数
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        aria-label="Last name: activate to sort column ascending">
                                        最新版本号
                                    </th>
                                    <th class="sorting" tabindex="0"
                                        aria-controls="datatable-responsive" rowspan="1" colspan="1"
                                        style="width: 124px;"
                                        aria-label="Last name: activate to sort column ascending">
                                        操作
                                    </th>
                                </tr>
                                </thead>
                                <script type="text/javascript">
                                    $(function () {
                                        //商品上下架
                                        $(".saleSwichOpen").on("click", function () {
                                            var obj = $(this);
                                            var id = obj.attr("appinfoid");
                                            $.ajax({
                                                url: "${ctx}/appinfo/open/" + id,
                                                type: "get",
                                                dataType: "json",
                                                success: function (data) {
                                                    if (data.result == "sj") {
                                                        $("#appInfoStatus" + id).html("已上架");
                                                        $("#appInfoStatus" + id).hide();
                                                        $("#appInfoStatus" + id).slideDown(300);
                                                        $(".saleSwichOpen").text("下架")
                                                    } else if (data.result == "xj") {
                                                        $("#appInfoStatus" + id).html("已下架");
                                                        $("#appInfoStatus" + id).hide();
                                                        $("#appInfoStatus" + id).slideDown(300);
                                                        $(".saleSwichOpen").text("上架");
                                                    } else {
                                                        alert("app状态更像错误 请联系管理员")
                                                    }
                                                },
                                                error: function () {
                                                    alert("失败")
                                                }

                                            })
                                        })
                                    })
                                </script>

                                <tbody>
                                <c:forEach var="appInfo" items="${pageBean.pageIndexList}" varStatus="status">
                                    <tr role="row" class="odd">
                                        <td tabindex="0" class="sorting_1">${appInfo.softwareName}</td>
                                        <td>${appInfo.APKName }</td>
                                        <td>${appInfo.softwareSize }</td>
                                        <td>${appInfo.flatformName }</td>
                                        <td>${appInfo.category1.categoryName } -> ${appInfo.category2.categoryName}-> ${appInfo.category3.categoryName}</td>
                                        <td><span id="appInfoStatus${appInfo.id}">${appInfo.statusName }</span></td>
                                        <td>${appInfo.downloads }</td>
                                        <td>
                                            <div class="btn-group">
                                                <button type="button" class="btn btn-danger">点击操作</button>
                                                <button type="button" class="btn btn-danger dropdown-toggle"
                                                        data-toggle="dropdown" aria-expanded="false">
                                                    <span class="caret"></span>
                                                    <span class="sr-only">Toggle Dropdown</span>
                                                </button>
                                                <ul class="dropdown-menu" role="menu">
                                                    <li>

                                                            <%--控制上下架--%>
                                                        <c:choose>
                                                            <c:when test="${appInfo.status == 2 || appInfo.status == 5}">
                                                                <a class="saleSwichOpen" saleSwitch="open"
                                                                   appinfoid=${appInfo.id }  appsoftwarename=${appInfo.softwareName }
                                                                   data-toggle="tooltip" data-placement="top" title=""
                                                                   data-original-title="恭喜您，您的审核已经通过，您可以点击上架发布您的APP">上架</a>
                                                            </c:when>
                                                            <c:when test="${appInfo.status == 4}">
                                                                <a class="saleSwichOpen" saleSwitch="close"
                                                                   appinfoid=${appInfo.id }  appsoftwarename=${appInfo.softwareName }
                                                                   data-toggle="tooltip" data-placement="top" title=""
                                                                   data-original-title="您可以点击下架来停止发布您的APP，市场将不提供APP的下载">下架</a>
                                                            </c:when>
                                                        </c:choose>
                                                    </li>
                                                    <li>
                                                        <a class="addVersion" appinfoid="${appInfo.id }"
                                                           data-toggle="tooltip" data-placement="top" title=""
                                                           data-original-title="新增APP版本信息">新增版本</a>
                                                    </li>


                                                    <li><a class="modifyVersion"
                                                           appinfoid="${appInfo.id }" versionid="${appInfo.versionId }"
                                                           status="${appInfo.status }"
                                                           statusname="${appInfo.statusName }"
                                                           data-toggle="tooltip" data-placement="top" title=""
                                                           data-original-title="修改APP最新版本信息">修改版本</a>
                                                    </li>


                                                    <li><a class="modifyAppInfo"
                                                           appinfoid="${appInfo.id }" status="${appInfo.status }"
                                                           statusname="${appInfo.statusName }"

                                                           data-toggle="tooltip" data-placement="top" title=""
                                                           data-original-title="修改APP基础信息">修改</a></li>


                                                    <li><a class="viewApp"
                                                           appinfoid=${appInfo.id }  data-toggle="tooltip"
                                                           data-placement="top" title=""
                                                           data-original-title="查看APP基础信息以及全部版本信息">查看</a></li>

                                                    <li><a class="deleteApp"
                                                           appinfoid=${appInfo.id }  appsoftwarename=${appInfo.softwareName }
                                                           data-toggle="tooltip" data-placement="top" title=""
                                                           data-original-title="删除APP基础信息以及全部版本信息">删除</a></li>
                                                </ul>
                                            </div>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <%--	删除APP ajax--%>
                                <script type="text/javascript">
                                    $(".deleteApp").on("click", function () {
                                        var obj = $(this);
                                        var flag = confirm("是否要删除APP" + obj.attr("appsoftwarename"));
                                        if (flag) {
                                            $.ajax({
                                                url: "${ctx}/appinfo/delete/" + obj.attr("appinfoid"),
                                                type: "get",
                                                dataType: "json",
                                                success: function (data) {
                                                    if (data.result == "true") {
                                                        $(location).attr("href", "${ctx}/appinfo/appinfolist.html")
                                                    } else {
                                                        alert("删除失败")
                                                    }
                                                },
                                                error: function () {
                                                    alert("失败")
                                                }
                                            })
                                        }
                                    })
                                </script>
                                <script type="text/javascript">
                                    $(".addVersion").on("click", function () {
                                        var obj = $(this);
                                        $(location).attr("href", "${ctx}/appversion/addversion/" + obj.attr("appinfoid"));
                                    })
                                </script>
                                <%--  1.没有版本信息不能修改--%>
                                <%--  2.已上架 审核通过 已下架 不能修改app版本信息--%>
                                <%-- 只有未审核或未通过审核才修改APP版本信息--%>
                                <script type="text/javascript">
                                    $(".modifyVersion").on("click", function () {
                                        var obj = $(this);
                                        var status = obj.attr("status");
                                        var versionId = obj.attr("versionId");
                                        if (status == "2" || status == "4" || status == "5") {
                                            alert("该APP应用的状态为：【" + obj.attr("statusname") + "】，不能修改其版本信息，只可进行【新增版本】操作！")
                                        } else {
                                            if ((status == "1" || status == "3") && versionNo != null && versionNo.length > 0) {
                                                $(location).attr("href", "${ctx}/appversion/versionmodify/" + versionId)
                                            } else {
                                                alert("该APP无版本信息，请先增加版本信息！");
                                            }
                                        }
                                    })
                                </script>
                                <%--待审核 和 审核未通过才能修改app基础信息--%>
                                <script type="text/javascript">
                                    $(".modifyAppInfo").on("click", function () {
                                        var obj = $(this);
                                        var status = obj.attr("status");
                                        if (status == "1" || status == "3") {//待审核、审核未通过状态下才可以进行修改操作
                                            $(location).attr("href", "${ctx}/appinfo/modify/" + obj.attr("appinfoid"));
                                        } else {
                                            alert("该APP应用的状态为：【" + obj.attr("statusname") + "】,不能修改！");
                                        }
                                    });
                                </script>

                                <%--    查看js--%>
                                <script type="text/javascript">
                                    $(".viewApp").on("click", function () {
                                        var obj = $(this);
                                        $(location).attr("href", "${ctx}/appinfo/viewapp/" + obj.attr("appinfoid"));
                                    });
                                </script>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-5">
                            <div class="dataTables_info" id="datatable-responsive_info"
                                 role="status" aria-live="polite">共${pageBean.totalCount }条记录
                                ${pageBean.pageIndex }/${pageBean.totalPageCount }页
                            </div>
                        </div>
                        <div class="col-sm-7">
                            <div class="dataTables_paginate paging_simple_numbers"
                                 id="datatable-responsive_paginate">
                                <ul class="pagination">
                                    <c:if test="${pageBean.pageIndex > 1}">
                                        <li class="paginate_button previous"><a
                                                href="javascript:page_nav(document.forms[0],1);"
                                                aria-controls="datatable-responsive" data-dt-idx="0"
                                                tabindex="0">首页</a>
                                        </li>
                                        <li class="paginate_button "><a
                                                href="javascript:page_nav(document.forms[0],${pageBean.pageIndex-1});"
                                                aria-controls="datatable-responsive" data-dt-idx="1"
                                                tabindex="0">上一页</a>
                                        </li>
                                    </c:if>
                                    <c:if test="${pageBean.pageIndex < pageBean.totalPageCount }">
                                        <li class="paginate_button "><a
                                                href="javascript:page_nav(document.forms[0],${pageBean.pageIndex+1 });"
                                                aria-controls="datatable-responsive" data-dt-idx="1"
                                                tabindex="0">下一页</a>
                                        </li>
                                        <li class="paginate_button next"><a
                                                href="javascript:page_nav(document.forms[0],${pageBean.totalPageCount});"
                                                aria-controls="datatable-responsive" data-dt-idx="7"
                                                tabindex="0">最后一页</a>
                                        </li>
                                    </c:if>
                                </ul>
                            </div>

                            <%--提交表单js--%>
                            <script type="text/javascript">
                                function page_nav(fm, num) {
                                    fm.current.value = num;
                                    fm.submit();
                                }
                            </script>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<%@include file="common/footer.jsp" %>