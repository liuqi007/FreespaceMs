<#assign menu="/manage/user/manage.htm">
<#include "/manage/head.ftl">
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
	
		<!-- page start-->
		<div class="row">
			<div class="col-lg-12">
				<!--breadcrumbs start -->
				<ul class="breadcrumb">
					<li>
						<a href="http://localhost/manage/user/manage.htm"><i class="icon-home">用户角色分配</i></a>
					</li>
				</ul>
				<!--breadcrumbs end -->
			</div>
		</div>
		
		<section class="panel">
			<header class="panel-heading">
			 <div class="row">
				<div class="col-lg-4">
					<ul class="breadcrumb" style="margin-bottom:0px;">
						<li>
						</li>
					</ul>
				</div>
				<div class="col-lg-8">
					<a class="btn btn-danger" style="margin-left:80%" href="${BASE_PATH}/manage/user/add.htm">保存</a>
					<a class="btn btn-primary"  href="#" onclick="history:go(-1)">返回</a>
			   	</div>
			   </div>
			</header>
			<div class="panel-body">
                <form id="add_admin_form" method="post" class="form-horizontal" autocomplete="off" action="${BASE_PATH}/manage/roleresourcerel/update.json">
                <input type="hidden" id="userId" name="userId" value="${userId}"/>
                <input type="hidden" id="selectResIds" name="selectResIds" value=""/>
                <div class="adv-table">
					<div role="grid" class="dataTables_wrapper"
						id="hidden-table-info_wrapper">
						<table class="table table-striped table-advance table-hover">
		                <thead>
		                   <tr>
		                    <th><input type="checkbox" id="selectAll"/></th>
		                    <th>角色姓名</th>
		                    <th>创建时间</th>
		                   </tr>
                        </thead>
                      <tbody role="alert" aria-live="polite" aria-relevant="all">
                        <#list allRole as e>
		                    <tr class="gradeA odd">
		                      <td><input type="checkbox" name="subCheckbox"/></td>
	                          <td>${e.name}</td>
	                          <td>${e.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
                          	</tr>
                          </#list>
                        </tbody>
                      </table>
                   </div>
				</div>
                </form>
			</div>
		</section>
		<!-- page end-->
			
	</section>
</section>
<script type="text/javascript">
	initCheckBox('selectAll','subCheckbox');
	$(function() {
		$('#add_admin_form').ajaxForm({
			dataType : 'json',
			success : function(data) {
				if (data.result) {
					bootbox.alert("保存成功，将刷新页面", function() {
						window.location.reload();
					});
				}else{
					showErrors($('#add_admin_form'),data.errors);
				}
			}
		});
	});	
</script>
<#include "/manage/foot.ftl">