<#assign menu="/manage/resource/manage.htm">
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
						<a href="http://localhost:8080/byvision/manage/folder/page.htm?folderId=0"><i class="icon-home"></i>资源管理</a>
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
							<a href="${BASE_PATH}/manage/article/list.htm">资源列表 </a>
						</li>
					</ul>
				</div>
			   </div>
			</header>
			<div class="panel-body">
				<div class="col-lg-4">
					<ul id="treeDemo" class="ztree"></ul>
					 <div class="form-group" style="margin-left:25px">
                          <button class="btn btn-info" type="submit">增加</button>
                          <button class="btn btn-info" type="button">修改</button>
                          <button class="btn btn-info" type="submit">删除</button>
                      </div>
                      <div class="form-group" style="margin-left:55px">
                          <button class="btn btn-danger" type="submit">上移</button>
                          <button class="btn btn-danger" type="submit">下移</button>
                      </div>
				</div>
				<div class="col-lg-8">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading">
	                            	 添加角色
                          </header>
                          <div class="panel-body">
                              <form id="add_admin_form" method="post" class="form-horizontal" autocomplete="off" action="">
                              	<fieldset>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">角色名称</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control" name="name"
                                          	placeholder="角色名称" id="name" value="" maxlength="50">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                  	<label class="col-sm-2 col-sm-2 control-label"></label>
                                      <button class="btn btn-danger" type="submit">新增</button>
                                      <button class="btn btn-info" type="button" onclick="history.go(-1);">返回</button>
                                  </div>
                                 </fieldset>
                              </form>
                          </div>
                      </section>
                  </div>
              </div>	
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
				beforeCheck: beforeCheck
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

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			$("#checkTrue").bind("click", {type:"checkTrue"}, checkNode);
			$("#checkFalse").bind("click", {type:"checkFalse"}, checkNode);
			$("#toggle").bind("click", {type:"toggle"}, checkNode);
			$("#checkTruePS").bind("click", {type:"checkTruePS"}, checkNode);
			$("#checkFalsePS").bind("click", {type:"checkFalsePS"}, checkNode);
			$("#togglePS").bind("click", {type:"togglePS"}, checkNode);
			$("#checkAllTrue").bind("click", {type:"checkAllTrue"}, checkNode);
			$("#checkAllFalse").bind("click", {type:"checkAllFalse"}, checkNode);

			$("#autoCallbackTrigger").bind("change", {}, setAutoTrigger);
		});


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
            message: "是否" + $(this).attr('title') + "资源",
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