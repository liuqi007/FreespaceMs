<#assign config_v="20141009044">
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="${shishuo_seo_description}">
<meta name="author" content="师说CMS">
<link rel="icon" href="${TEMPLATE_BASE_PATH}/images/favicon.ico">

<title>${shishuo_seo_title}</title>

<!-- Bootstrap core CSS -->
<link href="${TEMPLATE_BASE_PATH}/css/bootstrap.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${TEMPLATE_BASE_PATH}/css/blog.css" rel="stylesheet">

<!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
<!--[if lt IE 9]><script src="${TEMPLATE_BASE_PATH}/js/ie8-responsive-file-warning.js"></script><![endif]-->
<script src="${TEMPLATE_BASE_PATH}/js/ie-emulation-modes-warning.js"></script>

<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="${TEMPLATE_BASE_PATH}/js/html5shiv.min.js"></script>
      <script src="${TEMPLATE_BASE_PATH}/js/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="blog-masthead">
		<div class="container">
			<nav class="blog-nav">
			<a class="blog-nav-item <#if 0==g_folderId>active</#if>" href="${BASE_PATH}/index.htm">首页</a>
	                	<a class="blog-nav-item active" href=""></a>
			</nav>
		</div>
	</div>
	<div class="container">
		<div class="blog-header">
			<h1 class="blog-title">${shishuo_seo_title}</h1>
			<p class="lead blog-description">${shishuo_seo_description}</p>
		</div>