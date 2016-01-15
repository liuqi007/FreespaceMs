function initCheckBox(allCheckBtn, subCheckBtn) {
	$("#"+allCheckBtn).click(function() {
		$('input[name="'+subCheckBtn+'"]').prop("checked", this.checked);
	});
	var $subBox = $("input[name='"+subCheckBtn+"']");
	$subBox.click(function() {
		$("#"+allCheckBtn).prop("checked",
		$subBox.length == $("input[name='"+subCheckBtn+"']:checked").length ? true : false);
	});
}
