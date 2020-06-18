<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<div class="clearfix"></div>
<div class="row">
	<div class="col-md-12">
		<div class="x_panel">
			<div class="x_title">
				<h2>
					APP 审核列表 <i class="fa fa-user"></i><small>${userSession.userName}
						- 您可以通过搜索或者其他的筛选项对APP的信息进行审核操作。^_^</small>
				</h2>
				<div class="clearfix"></div>
			</div>
			<div class="x_content">
				<form method="get" action="${ctx}/backenduser/applist.html">
					<input type="hidden" name="pageIndex" value="1" />
			    <ul>
					<li>
						<div class="form-group">
							<label class="control-label col-md-3 col-sm-3 col-xs-12">软件名称</label>
							<div class="col-md-6 col-sm-6 col-xs-12">
								<input name="softwareName" type="text" class="form-control col-md-7 col-xs-12" value="${param.softwareName }">
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
									   		<option <c:if test="${dataDictionary.valueId == param.flatformId }">selected="selected"</c:if>
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
								<select id="categoryLevel1" name="categoryLevel1" class="form-control categoryLevel">
									<c:if test="${map.categoryLevel1List != null }">
									   <option value="">--请选择--</option>
									   <c:forEach var="appCategory" items="${map.categoryLevel1List}">
									   		<option <c:if test="${appCategory.id == param.categoryLevel1 }">selected="selected"</c:if>
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
        						<select name="categoryLevel2" id="categoryLevel2" class="form-control categoryLevel">
        							<c:if test="${map.categoryLevel2List != null }">
									   <option value="">--请选择--</option>
									   <c:forEach var="appCategory" items="${map.categoryLevel2List}">
									   		<option <c:if test="${appCategory.id == param.categoryLevel2 }">selected="selected"</c:if>
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
        						<select name="categoryLevel3" id="categoryLevel3" class="form-control categoryLevel">
									<c:if test="${map.categoryLevel3List != null }">
										<option value="">--请选择--</option>
										<c:forEach var="appCategory" items="${map.categoryLevel3List}">
											<option <c:if test="${appCategory.id == param.categoryLevel3 }">selected="selected"</c:if>
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
                                var obj=$(this);
                                var va=obj.val();
                                if(va!=null&&va!=''){
                                    $.ajax({
                                        url:"${ctx}/category/categorylist/"+va,
                                        Type:"get",
                                        dataType:"json",
                                        success:function (data) {
                                            obj.parents("li").nextAll("li").find(".categoryLevel").html("");
                                            var op="<option value=''>请选择</option>";
                                            for (var i=0;i<data.length;i++){
                                                // alert(data[i].id);
                                                op+="<option value='"+data[i].id+"'>"+data[i].categoryName+"</option>"
                                            }
                                            obj.parents("li").next("li").find(".categoryLevel").html(op);
                                        },
                                        error:function () {

                                        }
                                    })
                                }
                            })
                        })
					</script>
					<li><button type="submit" class="btn btn-primary"> 查 &nbsp;&nbsp;&nbsp;&nbsp;询 </button></li>
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
						<table id="datatable-responsive" class="table table-striped table-bordered dt-responsive nowrap dataTable no-footer dtr-inline collapsed"
							cellspacing="0" width="100%" role="grid" aria-describedby="datatable-responsive_info" style="width: 100%;">
							<thead>
								<tr role="row">
									<th class="sorting_asc" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 70px;" aria-label="First name: activate to sort column descending"
										aria-sort="ascending">软件名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 10px;"
										aria-label="Last name: activate to sort column ascending">
										APK名称</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 90px;"
										aria-label="Last name: activate to sort column ascending">
										软件大小(单位:M)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 50px;"
										aria-label="Last name: activate to sort column ascending">
										所属平台</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 170px;"
										aria-label="Last name: activate to sort column ascending">
										所属分类(一级分类、二级分类、三级分类)</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 30px;"
										aria-label="Last name: activate to sort column ascending">
										状态</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 30px;"
										aria-label="Last name: activate to sort column ascending">
										下载次数</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 64px;"
										aria-label="Last name: activate to sort column ascending">
										最新版本号</th>
									<th class="sorting" tabindex="0"
										aria-controls="datatable-responsive" rowspan="1" colspan="1"
										style="width: 30px;"
										aria-label="Last name: activate to sort column ascending">
										操作</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="appInfo" items="${pageBean.pageIndexList }" varStatus="status">
									<tr role="row" class="odd">
										<td tabindex="0" class="sorting_1">${appInfo.softwareName}</td>
										<td>${appInfo.APKName }</td>
										<td>${appInfo.softwareSize }</td>
										<td>${appInfo.flatformName }</td>
										<td>${appInfo.categoryLevel1Name } -> ${appInfo.categoryLevel2Name } -> ${appInfo.categoryLevel3Name }</td>
										<td>${appInfo.statusName }</td>
										<td>${appInfo.downloads }</td>
										<td>${appInfo.versionNo }</td>
										<td>
										<button type="button" class="btn btn-default checkApp" 
											appinfoid="${appInfo.id }" versionid="${appInfo.versionId }" status="${appInfo.status }" 
											statusname="${appInfo.statusName }"											
											data-toggle="tooltip" data-placement="top" title="" data-original-title="查看并审核APP">审核</button>
										</td>
									</tr>
								</c:forEach>
								<script type="text/javascript">
									$(".checkApp").on("click",function () {
										var obj=$(this);
										$(location).attr("href","${ctx}/backenduser/check/"+obj.attr("appinfoid"));
                                    })
								</script>
							</tbody>
						</table>
					</div>
				</div>
				<div class="row">
					<div class="col-sm-5">
						<div class="dataTables_info" id="datatable-responsive_info"
							role="status" aria-live="polite">共${pageBean.totalCount }条记录
							${pageBean.pageIndex }/${pageBean.totalPageCount }页</div>
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
										href="javascript:page_nav(document.forms[0],${pageBean.totalPageCount });"
										aria-controls="datatable-responsive" data-dt-idx="7"
										tabindex="0">最后一页</a>
									</li>
								</c:if>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function page_nav(fm,num) {
			fm.pageIndex.value=num;
			fm.submit();
        }
	</script>
</div>
</div>
<%@include file="common/footer.jsp"%>