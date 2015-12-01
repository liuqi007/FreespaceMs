<#assign menu="/manage/user/manage.htm">
<#include "/manage/head.ftl">
<link rel="stylesheet" href="${BASE_PATH}/static/manage/css/multiselect2side/jquery.multiselect2side.css" type="text/css">
<script type="text/javascript" src="${BASE_PATH}/static/manage/js/multiselect2side/jquery.multiselect2side.js"></script>
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
							<a href="${BASE_PATH}/manage/article/list.htm">用户角色分配 </a>
						</li>
					</ul>
				</div>
			   </div>
			</header>
			<div class="panel-body">
                <form id="add_admin_form" method="post" class="form-horizontal" autocomplete="off" action="${BASE_PATH}/manage/roleresourcerel/update.json">
                <input type="hidden" id="roleId" name="roleId" value="${roleId}"/>
                <input type="hidden" id="selectResIds" name="selectResIds" value=""/>
				<div class="col-lg-4">
					<div id="sel">
				      <select name="liOption[]" id='liOption' multiple='multiple' size='8' >
				        <option value="PHP">PHP</option>
				        <option value="MYSQL">MYSQL</option>
				        <option value="ASP.NET">ASP.NET</option>
				        <option value="XHTML">XHTML</option>
				        <option value="CSS">CSS</option>
				        <option value="JQUERY">JQUERY</option>
				      </select>
				     </div>

				</div>
                          <button class="btn btn-info" type="submit" style="margin-left:100px">确定</button>
                </form>
			</div>
		</section>
		<!-- page end-->
			
	</section>
</section>
<script type="text/javascript">
	//var zNodes =${allResource};
	$(function() {
	   $("#liOption").multiselect2side({
		    selectedPosition: 'right',
		    moveOptions: false,
			labelsx: '待选区',
			labeldx: '已选区'
	   });

	
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