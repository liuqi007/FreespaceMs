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
						<a href="http://localhost/manage/resource/manage.htm"><i class="icon-home"></i>资源管理</a>
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
					<ul id="treeDemo" class="ztree" style="width:228px"></ul>
					 <div class="form-group">
                          <button class="btn btn-info" type="button" id="btnAdd">增加</button>
                          <button class="btn btn-info" type="button" id="btnDel">删除</button>
                          <button class="btn btn-danger" type="submit">上移</button>
                          <button class="btn btn-danger" type="submit">下移</button>
                      </div>
				</div>
				<div class="col-lg-8">
                  <div class="col-lg-12">
                      <section class="panel">
                          <header class="panel-heading addOrEditRes">
	                       		修改资源
                          </header>
                          <div class="panel-body">
                              <form id="add_admin_form" method="post" class="form-horizontal" autocomplete="off" action="${BASE_PATH}/manage/resource/addOrUpdate.json">
                              	<input type="hidden" name="resId" id="resId" />
                              	<input type="hidden" name="parentResId" id="parentResId" />
                              	<input type="hidden" name="iconcss" id="iconcss" />
                              	<input type="hidden" name="type" id="type" />
                              	<input type="hidden" name="sort" id="sort" />
                              	<fieldset>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">资源名称</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control" name="name" id="name" value="" maxlength="50">
                                      </div>
                                  </div>
                                  <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">资源连接</label>
                                      <div class="col-sm-10">
                                          <input type="text" class="form-control" name="url" id="url" value="" maxlength="50">
                                      </div>
                                  </div>
                                   <div class="form-group">
                                      <label class="col-sm-2 col-sm-2 control-label">是否启用</label>
                                      <div class="col-sm-2">
                                          <select id="isUse" name="isName" class="form-control">
											  <option value=""></option>
											  <option value="0">是</option>
											  <option value="1">否</option>
											</select>
                                      </div>
                                  </div>
                                  <div class="form-group">
                                  	<label class="col-sm-2 col-sm-2 control-label"></label>
                                      <button class="btn btn-danger" type="submit" id="btnSubmit">确定</button>
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
				beforeCheck: beforeCheck,
				onClick: selectNode
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

		function selectNode(e){
			var node = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes();
			if(node[0].id==1){
				$("#btnSubmit").attr("disabled","disabled");
			}else{
				$("#btnSubmit").removeAttr("disabled");
			}
			$("#resId").val(node[0].id);
			$("#name").val(node[0].name);
			$("#url").val(node[0].link);
			$("#parentResId").val(node[0].parentResId);
			$("#isUse").val(node[0].isUse);
			$("#iconcss").val(node[0].iconcss);
            $("#type").val(node[0].type);
            $("#sort").val(node[0].sort);
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
		
		$('#btnAdd').click(function() {
			var node = $.fn.zTree.getZTreeObj("treeDemo").getSelectedNodes();
			if(!node[0]){
				bootbox.alert({message: "请选择父节点",title: "提示"});
	            return;
			}
			$("#parentResId").val(node[0].id);
			$("#resId").val(0);
			$("#name").val("");
			$("#url").val("");
			$("#isUse").val("");
			$(".addOrEditRes").text("添加资源");
		});
		
		$('#btnDel').click(function() {
			var resId= $("#resId").val();
			var name= $("#name").val();
			if(!resId){
				bootbox.alert({message: "请选择要删除的资源",title: "提示"});
	            return;
			}
        bootbox.dialog({
            message: "是否确定删除【" + name + "】资源",
            title: "提示",
            buttons: {
                "delete": {
                    label: "删除",
                    className: "btn-success",
                    callback: function() {
                        $.post("${BASE_PATH}/manage/resource/delete.json", {
                            "resId": resId
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