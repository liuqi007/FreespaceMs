<#assign menu="admin_list">
<#assign submenu="add_admin">
<#include "/manage/head.ftl">
<style type="text/css">
.m-bot15 {
    margin-bottom: 5px;
}
.form-control {
    border: 1px solid #E2E2E4;
    box-shadow: none;
    color: #C2C2C2;
}
.input-lg {
    border-radius: 6px;
    font-size: 15px;
    height: 40px;
    line-height: 1.33;
    padding: 9px 15px；
}

</style>
		<!--main content start-->
		<section id="main-content">
			<section class="wrapper">
              <!-- page start-->
              <div class="row">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
                          		<#if updateRole.roleId!=0>
	                          		修改角色
	                          	<#else>
	                            	 添加角色
	                          	</#if>
                          </header>
                          <div class="panel-body">
                              <form id="add_admin_form" method="post" class="form-horizontal" autocomplete="off" action="${BASE_PATH}/manage/role/<#if updateRole.roleId!=0>update.json<#else>addNew.json</#if>">
                              	<fieldset>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">角色名称</label>
                                      <div class="col-sm-10">
                                          <input type="hidden" id="roleId" name="roleId" value="${updateRole.roleId}"/>
                                          <input type="text" class="form-control" name="name"
                                          	placeholder="角色名称" id="name" value="${updateRole.name}" maxlength="50">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                  	<label class="col-sm-2 col-sm-2 control-label"></label>
                                      <button class="btn btn-danger" type="submit"><#if updateRole.roleId!=0>修改<#else>新增</#if></button>
                                      <button class="btn btn-info" type="button" onclick="history.go(-1);">返回</button>
                                  </div>
                                 </fieldset>
                              </form>
                          </div>
                      </section>
                  </div>
              </div>
              <!-- page end-->
          </section>
		</section>
		<!--main content end-->
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
	});	
</script>
<#include "/manage/foot.ftl">
