<#assign menu="/manage/role/manage.htm">
<#include "/manage/head.ftl">
<link rel="stylesheet" href="${BASE_PATH}/static/manage/css/ztree/demo.css" type="text/css">
<link rel="stylesheet" href="${BASE_PATH}/static/manage/css/ztree/zTreeStyle.css" type="text/css">
<script type="text/javascript" src="${BASE_PATH}/static/manage/js/ztree/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${BASE_PATH}/static/manage/js/ztree/jquery.ztree.excheck-3.5.js"></script>	
<!--main content start-->
<section id="main-content">
	<section class="wrapper">
	
		<!-- page start-->
		<div class="row">
			<div class="col-lg-12">
				<!--breadcrumbs start -->
				<ul class="breadcrumb">
					<li>
						<a href="http://localhost/manage/resource/manage.htm"><i class="icon-home"></i>角色资源分配</a>
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
							<a href="${BASE_PATH}/manage/article/list.htm">角色资源列表 </a>
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
					<ul id="treeDemo" class="ztree" style="height:300px"></ul>
					 <div class="form-group">
                          <button class="btn btn-info" type="submit" style="margin-left:100px">确定</button>
                     </div>
				</div>
                </form>
			</div>
		</section>
		<!-- page end-->
			
	</section>
</section>
<script type="text/javascript">
		var setting = {
			view: {
				selectedMulti: false
			},
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeCheck: beforeCheck,
				onCheck: checkNode
			}
		};

		var zNodes =${allResource};
		
		var code, log, className = "dark";
		function beforeCheck(treeId, treeNode) {
			className = (className === "dark" ? "":"dark");
			return (treeNode.doCheck !== false);
		}


		function checkNode(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			type = e.data.type,
			nodes = zTree.getSelectedNodes();
			if (type.indexOf("All")<0 && nodes.length == 0) {
				alert("请先选择一个节点");
			}

			if (type == "checkAllTrue") {
				zTree.checkAllNodes(true);
			} else if (type == "checkAllFalse") {
				zTree.checkAllNodes(false);
			} else {
				var callbackFlag = $("#callbackTrigger").attr("checked");
				for (var i=0, l=nodes.length; i<l; i++) {
					if (type == "checkTrue") {
						zTree.checkNode(nodes[i], true, false, callbackFlag);
					} else if (type == "checkFalse") {
						zTree.checkNode(nodes[i], false, false, callbackFlag);
					} else if (type == "toggle") {
						zTree.checkNode(nodes[i], null, false, callbackFlag);
					}else if (type == "checkTruePS") {
						zTree.checkNode(nodes[i], true, true, callbackFlag);
					} else if (type == "checkFalsePS") {
						zTree.checkNode(nodes[i], false, true, callbackFlag);
					} else if (type == "togglePS") {
						zTree.checkNode(nodes[i], null, true, callbackFlag);
					}
				}
			}
		}

		function setAutoTrigger(e) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo");
			zTree.setting.check.autoCheckTrigger = $("#autoCallbackTrigger").attr("checked");
			$("#autoCheckTriggerValue").html(zTree.setting.check.autoCheckTrigger ? "true" : "false");
		}

		function checkNode(e){
			var nodes = $.fn.zTree.getZTreeObj("treeDemo").getCheckedNodes();
			var len = nodes.length;
			var resId="";
			for(var i=0;i<len;i++){
				var node = nodes[i];
				if(i==0){
					resId = node.id;
				}else{
					resId = resId + "&" + node.id ;
				}
			}
			console.info(resId);
			$("#selectResIds").val(resId);
		}

	$(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	
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