<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Mobile first web app theme | first</title>
    <meta name="description" content="mobile first, app, web app, responsive, admin dashboard, flat, flat ui">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
</head>
<body>
<!-- header -->
<#macro head>
<header id="header" class="navbar">
    <ul class="nav navbar-nav navbar-avatar pull-right">
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                <span class="hidden-sm-only">
                <#if user?exists>
                    ${user.name}
                <#else>
                    未登录
                </#if>
                </span>
                <span class="thumb-small avatar inline"><img src="" alt="" class="img-circle"></span>
                <b class="caret hidden-sm-only"></b>
            </a>
            <ul class="dropdown-menu">
                <li><a href="javascript:void (0)">Profile</a></li>
                <li><a href="javascript:void (0)">Logout</a></li>
            </ul>
        </li>
    </ul>
    <a class="navbar-brand" href="#">first</a>
    <button type="button" class="btn btn-link pull-left nav-toggle hidden-lg" data-toggle="class:show" data-target="#nav">
        <i class="icon-reorder icon-xlarge text-default"></i>
    </button>
    <form class="navbar-form pull-left shift" action="" data-toggle="shift:appendTo" data-target=".nav-primary">
        <i class="icon-search text-muted"></i>
        <input type="text" class="input-small form-control" placeholder="Search">
    </form>
</header>
<!-- / header -->
<!-- nav -->
<nav id="nav" class="nav-primary visible-lg nav-vertical">
    <ul class="nav" data-spy="affix" data-offset-top="50">
        <li><a href="/index"><i class="icon-dashboard icon-xlarge"></i>首页</a></li>
        <li class="dropdown-submenu">
            <a href="/article/getAll"><i class="icon-th icon-xlarge"></i>管理</a>
            <!-- <ul class="dropdown-menu">
              <li><a href="buttons.html">Buttons</a></li>
              <li><a href="icons.html"><b class="badge pull-right">302</b>Icons</a></li>
              <li><a href="grid.html">Grid</a></li>
              <li><a href="widgets.html"><b class="badge bg-primary pull-right">8</b>Widgets</a></li>
              <li><a href="components.html"><b class="badge pull-right">18</b>Components</a></li>
            </ul> -->
        </li>
        <li><a href="/log"><i class="icon-edit icon-xlarge"></i>日志</a></li>
<#--
        <li class="active"><a href="chart.html"><i class="icon-signal icon-xlarge"></i>Chart</a></li>
-->
    </ul>
</nav>
</#macro>
</body>
</html>