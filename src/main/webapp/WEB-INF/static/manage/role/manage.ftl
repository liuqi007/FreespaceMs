<#assign menu="/manage/role/manage.htm">
<#assign submenu="admin_list">
<#include "/manage/head.ftl">	
<style type="text/css">
.pagination {
	border-radius: 4px;
	display: inline-block;
	margin: 0;
	padding-left: 0;
}
</style>
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
	
		<!-- page start-->
		<div class="row">
			<div class="col-lg-12">
				<!--breadcrumbs start -->
				<ul class="breadcrumb">
					<li>
						<a href="http://localhost:8080/byvision/manage/folder/page.htm?folderId=0"><i class="icon-home"></i>角色管理</a>
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
							<a href="${BASE_PATH}/manage/article/list.htm">角色列表 </a>
						</li>
					</ul>
				</div>
			   	<div class="col-lg-8">
					<a class="btn btn-primary" style="float:right;" href="${BASE_PATH}/manage/role/add.htm">增加角色</a>
			   	</div>
			   </div>
			</header>
			<div class="panel-body">
				<div class="adv-table">
					<div role="grid" class="dataTables_wrapper"
						id="hidden-table-info_wrapper">
						<table class="table table-striped table-advance table-hover">
		                <thead>
		                   <tr>
		                    <th>角色姓名</th>
		                    <th>创建时间</th>
		                    <th>操作</th>
		                   </tr>
                        </thead>
                      <tbody role="alert" aria-live="polite" aria-relevant="all">
                        <#list pageVo.list as e>
		                    <tr class="gradeA odd">
	                          <td>${e.name}</td>
	                          <td>${e.createTime?string("yyyy-MM-dd HH:mm:ss")}</td>
	                          <td>
		                        <!-- Icons -->
		                       <a href="javascript:void(0);" roleId="${e.roleId}" title="删除" class="js_delete_admin">
		                                                                                              删除
		                        </a>|
		                        <a href="${BASE_PATH}/manage/role/update.htm?roleId=${e.roleId}" title="修改">
		                        	修改
		                        </a>|
		                        <a href="${BASE_PATH}/manage/adminFolder/manage.htm?roleId=${e.roleId}" title="分配菜单权限">
		                        	分配菜单权限
		                        </a>
		                    	</td>
                          	</tr>
                          </#list>
                        </tbody>
                      </table>
                      <div style="height: 30px;">
                      	  <div class="pagination">${pageVo.pageNumHtml}</div>          
                      </div>
                   </div>
				</div>
			</div>
		</section>
		<!-- page end-->
			
	</section>
</section>
<script type="text/javascript">
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
		
		$('.js_delete_admin').click(function() {
		var roleId= $(this).attr('roleId');
        bootbox.dialog({
            message: "是否" + $(this).attr('title') + "角色",
            title: "提示",
            buttons: {
                "delete": {
                    label: "删除",
                    className: "btn-success",
                    callback: function() {
                        $.post("${BASE_PATH}/manage/role/delete.json", {
                            "roleId": roleId
                        },
                        function(data) {
                            if (data.result) {
                                bootbox.alert("删除成功",
                                function() {
                                   window.location.reload();
                                });
                            } else {
                                bootbox.alert(data.msg,
                                function() {});
                            }
                        },
                        "json");
                    }
                },
                "cancel": {
                    label: "取消",
                    className: "btn-primary",
                    callback: function() {}
                }
            }
        });
    });
	});	
</script>
<#include "/manage/foot.ftl">