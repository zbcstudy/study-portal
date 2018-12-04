
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <title>MAM</title>
    <meta name="description" content="wondertek media asset management system" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />
    <link rel="icon" href="./images/icon/wd_cloud.png" type="image/x-icon" />
    <link rel="shortcut icon" href="./images/icon/wd_cloud.png" type="image/x-icon" />
    <link rel="stylesheet" href="./ace/assets/css/bootstrap.css" />
    <link rel="stylesheet" href="./ace/assets/css/bootstrap-datetimepicker.css"/>
    <link rel="stylesheet" href="./ace/assets/css/bootstrap-timepicker.css"/>
    <link rel="stylesheet" href="./ace/assets/css/bootstrap-editable.css"/>
    <link rel="stylesheet" href="./ace/assets/css/bootstrap-multiselect.css" />
    <link rel="stylesheet" href="./ace/assets/css/datepicker.css" />
    <link rel="stylesheet" href="./ace/assets/css/ui.jqgrid.css" />
    <link rel="stylesheet" href="./ace/assets/css/chosen.css" />
    <link rel="stylesheet" href="./ace/assets/css/dropzone.css" />
    <link rel="stylesheet" href="./ace/assets/css/colorbox.css"/>
    <link rel="stylesheet" href="./ace/assets/css/select2.css"/>
    <link rel="stylesheet" href="./ace/assets/css/jquery-ui.css" />
    <link rel="stylesheet" href="./ace/assets/css/jquery-ui.custom.css"/>
    <link rel="stylesheet" href="./ace/assets/css/jquery.gritter.css"/>
    <link rel="stylesheet" href="./ace/plugins/css/jquery.marquee.css" type="text/css"/>
    <link rel="stylesheet" href="./ace/plugins/css/jquery-jcrop/jquery.Jcrop.css"/>
    <link rel="stylesheet" href="./ace/plugins/css/autocomplete/jquery.autocomplete.css"/>
    <link rel="stylesheet" href="./ace/plugins/css/css.css"/>
    <link rel="stylesheet" href="./ace/plugins/css/lobibox.css" />
    <link rel="stylesheet" href="./ace/plugins/css/autoSearchText.css" />
    <link rel="stylesheet" href="./ace/plugins/css/zyFile/zyUpload.css"/>
    <link rel="stylesheet" href="./ace/plugins/css/ztree/demo.css"/>
    <link rel="stylesheet" href="./ace/plugins/css/ztree/zTreeStyle.css"/>
    <link rel="stylesheet" href="./ace/assets/css/font-awesome.css" />
    <link rel="stylesheet" href="./ace/assets/css/ace-fonts.css" />
    <link rel="stylesheet" href="./ace/assets/css/ace.css" />
    <link rel="stylesheet" href="./ace/assets/css/ace-ext.css"/>
    <link rel="stylesheet" href="./css/mam.css?v=1.0.7"/>
    <link rel="stylesheet" href="./ace/plugins/css/video/video-js.css"/>
    <link rel="stylesheet" href="./css/blank.css">
    <link rel="stylesheet" href="./css/common.css">
    <link rel="stylesheet" href="./css/sampleTable.css">
    <link rel="stylesheet" href="./css/dcalendar.picker.css"/>

    <!--[if !IE]> -->
    <script type="text/javascript">
        window.jQuery || document.write("<script src='./ace/assets/js/jquery.js'>"+"<"+"/script>");
    </script>
    <!--  <![endif]-->
    <script type="text/javascript" src="./ace/assets/js/jquery.js"></script>

</head>

<body class="no-skin">
<script type="text/javascript">
    var Mam=[];
</script>
<!--导航开始-->
<div class="navbar navbar-fixed-top" id="navbar">
    <script type="text/javascript">
        try {
            ace.settings.check('navbar', 'fixed')
        } catch (e) {
        }
    </script>
    <button style="display:none;" class="btn btn-md" id="alertButton"></button><!-- 提示信息要用到 -->
    <!-- #section:basics/navbar.layout -->
    <div class="navbar-container" id="navbar-container">
        <button type="button" class="navbar-toggle pull-left menu-toggler" id="menu-toggler" data-toggle="collapse" data-target="#sidebar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <div class="navbar-header pull-left hidden-sm hidden-xs">
            <a href="#" class="navbar-brand">
                <small>
                    <img src="./images/shouye/wd_logo.png" alt="" style="margin-bottom: 5px;margin-right: 15px;">
                    <span>媒资管理系统</span>
                </small>
            </a><!-- /.brand -->
        </div><!-- /.navbar-header -->

        <div class="navbar-header pull-right" role="navigation" id="head_menu">
            <ul class="nav ace-nav">


                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-list-ul"></i>
                        <span class="badge badge-success apl-num">1</span>
                    </a>

                    <!-- <ul class="dropdown-menu dropdown-caret dropdown-close warning-list">
                        <li><a href="http://117.131.17.141:8083/sso-cloud/main.html" target="_blank"><i class="btn-point"></i>统一用户管理系统</a></li>
                    </ul> -->
                    <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                        <li class="dropdown-header">
                            应用
                        </li>
                        <li class="dropdown-content">
                            <ul class="dropdown-menu dropdown-navbar">

                                <li>
                                    <a href="" target="_blank">
                                        <i class="btn btn-xs no-hover btn-pink"></i>
                                    </a>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </li>

                <li class="dropdown">
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <i class="ace-icon fa fa-bell-o icon-animated-bell"></i>
                        <span class="badge badge-important war-num">0</span>
                    </a>

                    <ul class="dropdown-menu dropdown-caret dropdown-close">

                    </ul>
                </li>

                <li class="dropdown">
                    <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                        <img src="./images/shouye/icon_top_admin.png" alt="">
								<span class="user-info" style="margin-left: 14px;top:18px;">
									<small>欢迎您 ,</small>
									<small>administrator</small>
								</span>
                        <i class="icon-caret-down"></i>
                        <!-- <img src="./images/shouye/admin_arrow_down.png"> -->
                    </a>

                    <ul class="user-menu dropdown-menu dropdown-yellow dropdown-caret dropdown-close" style="margin-left: 10px;">
                        <!--  <li>
                             <a href="#">
                                 <img src="./images/shouye/admin_install.png">
                                 <span>个人设置</span>
                             </a>
                         </li>

                         <li>
                             <a href="#">
                                 <img src="./images/shouye/admin_charging.png">
                                 <span>计费结算</span>
                             </a>
                         </li> -->

                        <li>
                            <a href="/sso/User_pwdEdit.html" target="_Blank">
                                <img src="./images/shouye/admin_password.png">
                                <span>密码修改</span>
                            </a>
                        </li>

                        <li>
                            <a href="/logout">
                                <img src="./images/shouye/admin_out.png">
                                <span>退出</span>
                            </a>
                        </li>
                    </ul>
                </li>
            </ul><!-- /.ace-nav -->
        </div><!-- /.navbar-header -->
    </div><!-- /.container -->
</div>
<!--导航结束-->
<!--主体开始-->
<div class="main-container" id="main-container">
    <script type="text/javascript">
        try {
            ace.settings.check('main-container', 'fixed')
        } catch (e) {
        }
    </script>
    <div class="main-container-inner">
        <!--侧边栏slider开始-->


        <div id="sidebar"
             class="sidebar responsive ace-save-state sidebar-fixed">
            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'fixed')
                } catch (e) {
                }
            </script>

            <!-- <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                        <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                            <img src="./images/shouye/icon_menu.png" alt="" >
                        </div>
                    </div> -->
            <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                <i id="sidebar-toggle-icon"
                   class="ace-icon fa fa-angle-double-left ace-save-state"
                   data-icon1="ace-icon fa fa-angle-double-left"
                   data-icon2="ace-icon fa fa-angle-double-right"></i>
            </div>


            <ul class="nav nav-list" id="left_menu">

                <li><a title="/mam-cloud/mam/Content_view_workbench.htm">
                    <i class="fa fa-laptop"></i> <span class="menu-text"> 我的工作台</span>
                </a></li>

                <li><a class="dropdown-toggle"><i class="fa fa-camera"></i>
                    <span class="menu-text"> 短视频管理 </span> <i class="fa fa-angle-down"></i>
                </a>
                    <ul class="submenu nav-hide">

                        <li><a title="/mam-cloud/mam/Content_view_edit.htm?type=short">
                            <i class="fa fa-edit"></i> 内容采编
                        </a></li>

                        <li><a title="/mam-cloud/mam/Content_view_audit.htm?type=short">
                            <i class="fa fa-paper-plane"></i> 内容审核
                        </a></li>

                        <li><a
                                title="/mam-cloud/mam/Content_view_transcode.htm?type=short">
                            <i class="fa fa-retweet"></i> 转码进度
                        </a></li>

                        <li><a title="/mam-cloud/mam/Content_view_manage.htm?type=short">
                            <i class="fa fa-reorder"></i> 内容管理
                        </a></li>

                    </ul></li>

                <li><a class="dropdown-toggle"><i class="fa fa-film"></i> <span
                        class="menu-text"> 长视频管理 </span> <i class="fa fa-angle-down"></i>
                </a>
                    <ul class="submenu nav-hide">

                        <li><a
                                title="/mam-cloud/mam/Content_view_pointAssetEdit.htm"> <i
                                class="fa fa-edit"></i> 内容采编
                        </a></li>

                        <li><a title="/mam-cloud/mam/Content_view_pointAssetAudit.htm">
                            <i class="fa fa-paper-plane"></i> 内容审核
                        </a></li>

                        <li><a
                                title="/mam-cloud/mam/Content_view_transcode.htm?type=long">
                            <i class="fa fa-retweet"></i> 转码进度
                        </a></li>

                        <li><a title="/mam-cloud/mam/Content_view_manage.htm?type=long">
                            <i class="fa fa-reorder"></i> 内容管理
                        </a></li>

                    </ul></li>

                <li><a class="dropdown-toggle"><i class="fa fa-video-camera"></i>
                    <span class="menu-text"> 在线直播管理 </span> <i class="fa fa-angle-down"></i>
                </a>
                    <ul class="submenu nav-hide">

                        <li><a title="/mam-cloud/mam/Channel_collect.htm">
                            <i class="fa fa-edit"></i> 直播采编
                        </a></li>

                        <li><a title="/mam-cloud/mam/Channel_checkList.htm">
                            <i class="fa fa-paper-plane"></i> 直播审核
                        </a></li>

                        <li><a title="/mam-cloud/mam/Channel_list.htm"> <i
                                class="fa fa-reorder"></i> 直播管理
                        </a></li>

                        <li><a title="/mam-cloud/mam/Content_view_epg.htm">
                            <i class="fa fa-edit"></i> EPG生产
                        </a></li>

                    </ul></li>

                <li><a class="dropdown-toggle"><i class="fa fa-slideshare"></i>
                    <span class="menu-text"> 互动直播管理 </span> <i class="fa fa-angle-down"></i>
                </a>
                    <ul class="submenu nav-hide">

                        <li><a title="/mam-cloud/mam/StudioRoom_execute.htm">
                            <i class="fa fa-qrcode"></i> 直播间管理
                        </a></li>

                        <li><a title="/mam-cloud/mam/RoomMonitor_view.htm">
                            <i class="fa fa-binoculars"></i> 直播间监控
                        </a></li>

                        <li><a title="/mam-cloud/mam/AnchorInfo_view.htm">
                            <i class="fa fa-male"></i> 主播管理
                        </a></li>

                        <li><a title="/mam-cloud/mam/PropManage_view.htm">
                            <i class="fa fa-magic"></i> 道具管理
                        </a></li>

                        <li><a title="/mam-cloud/mam/SensitiveWord_manage.htm">
                            <i class="fa fa-maxcdn"></i> 敏感词管理
                        </a></li>

                        <li><a title="/mam-cloud/mam/Pad_manage.htm">
                            <i class="fa fa-sliders"></i> 垫片管理
                        </a></li>

                    </ul>
                </li>

                <li><a class="dropdown-toggle"><i class="fa fa-magnet"></i>
                    <span class="menu-text">UGC管理 </span> <i class="fa fa-angle-down"></i>
                </a>
                    <ul class="submenu nav-hide">

                        <li><a title="/mam-cloud/mam/UgcVideoManager_view.htm">
                            <i class="fa fa-file-video-o"></i> UGC上传内容
                        </a></li>

                        <li><a
                                title="/mam-cloud/mam/Content_view_edit.htm?type=ugc">
                            <i class="fa fa-edit"></i> UGC采编
                        </a></li>

                        <li><a
                                title="/mam-cloud/mam/Content_view_audit.htm?type=ugc">
                            <i class="fa fa-paper-plane"></i> UGC审核
                        </a></li>

                        <li><a
                                title="/mam-cloud/mam/Content_view_transcode.htm?type=ugc">
                            <i class="fa fa-retweet"></i> UGC转码进度
                        </a></li>

                        <li><a
                                title="/mam-cloud/mam/Content_view_manage.htm?type=ugc">
                            <i class="fa fa-reorder"></i> UGC内容管理
                        </a></li>

                    </ul></li>

                <li><a class="dropdown-toggle"><i class="fa fa-photo"></i>
                    <span class="menu-text"> 图文管理 </span> <i class="fa fa-angle-down"></i>
                </a>
                    <ul class="submenu nav-hide">

                        <li><a title="/mam-cloud/mam/Content_view_twEditing.htm">
                            <i class="fa fa-edit"></i> 图文采编
                        </a></li>

                        <li><a
                                title="/mam-cloud/mam/Content_view_twTask.htm">
                            <i class="fa fa-paper-plane"></i> 图文审核
                        </a></li>

                        <li><a
                                title="/mam-cloud/mam/Content_view_twManager.htm">
                            <i class="fa fa-reorder"></i> 图文内容管理
                        </a></li>


                    </ul></li>

                <li><a class="dropdown-toggle"> <i class="fa fa-music"></i>
                    <span class="menu-text"> 音视频编辑</span> <i class="fa fa-angle-down"></i>
                </a>

                    <ul class="submenu">

                        <li><a href="#" title="/mam-cloud/pages/mediaView.htm"><i
                                class="fa fa-paint-brush"></i> 媒资打点 </a></li>

                        <li><a href="#" title="/mam-cloud/pages/videodeltManagement.htm"><i
                                class="fa fa-paint-brush"></i> 内容剪切 </a></li>

                        <li><a href="#"
                               title="/mam-cloud/pages/liveExtractMobile.htm"> <i
                                class="fa fa-scissors"></i>直播抽取
                        </a></li>

                        <li><a href="#"
                               title="/mam-cloud/pages/liveRecord.htm"><i
                                class="fa fa-clipboard"></i> 直播收录 </a></li>

                        <li><a href="#"
                               title="/mam-cloud/pages/videoMerge.htm"><i
                                class="fa fa-clipboard"></i> 视频合并 </a></li>

                        <li><a href="#"
                               title="/mam-cloud/pages/videoExtract.htm"><i
                                class="fa fa-clipboard"></i> 内容抽取 </a></li>

                        <li><a href="#"
                               title="/mam-cloud/pages/videoMosiac.htm"><i
                                class="fa fa-clipboard"></i> 视频遮挡 </a></li>

                    </ul></li>

                <li><a class="dropdown-toggle"> <i class="fa fa-tag"></i> <span
                        class="menu-text"> 版权管理 </span> <i class="fa fa-angle-down"></i>
                </a>

                    <ul class="submenu nav-hide">

                        <li><a title="/mam-cloud/mam/CopyrightApply_apply.htm">
                            <i class="fa fa-edit"></i> 版权录入
                        </a></li>

                        <li><a title="/mam-cloud/mam/CopyrightApply_view.htm">
                            <i class="fa fa-cubes"></i> 版权暂存区
                        </a></li>

                        <li><a title="/mam-cloud/mam/CopyrightApply_verify.htm"> <i
                                class="fa fa-chain"></i> 内容版权校审
                        </a></li>

                        <li><a title="/mam-cloud/mam/Copyright_Lib.htm"> <i
                                class="fa fa-reorder"></i> 版权库
                        </a></li>

                        <li><a title="/mam-cloud/mam/CopyrightFileManage_manage.htm"> <i
                                class="fa fa-file"></i> 版权文件管理
                        </a></li>

                    </ul></li>

                <li><a class="dropdown-toggle"> <i class="fa fa-paw"></i> <span
                        class="menu-text"> 资信库 </span> <i class="fa fa-angle-down"></i>
                </a>

                    <ul class="submenu nav-hide">

                        <li><a
                                title="/mam-cloud/mam/Star_loadListPage_List.htm"> <i
                                class="fa fa-users"></i> 人物库
                        </a></li>

                        <li><a title="/mam-cloud/mam/Awards_list.htm"> <i
                                class="fa fa-trophy"></i> 奖项库
                        </a></li>

                    </ul></li>

                <li><a class="dropdown-toggle"> <i class="fa fa-suitcase"></i>
                    <span class="menu-text"> 产品包 </span> <i class="fa fa-angle-down"></i>
                </a>
                    <ul class="submenu" style="display: none;" id="productList">
                    </ul></li>

                <li><a
                        title="/poms-cloud/pom/ProductInfo_manage.html">
                    <i class="fa fa-folder"></i> <span class="menu-text"> 产品包管理
				</span>
                </a></li>

                <li><a
                        title="/poms-cloud/pom/Cooperation_execute.html">
                    <i class="fa fa-puzzle-piece"></i> <span class="menu-text">
						内容合作 </span>
                </a></li>

                <li><a class="dropdown-toggle"> <i class="fa fa-history"></i>
                    <span class="menu-text"> 历史记录 </span> <i class="fa fa-angle-down"></i>
                </a>
                    <ul class="submenu nav-hide" style="display: none">

                        <li><a
                                title="/poms-cloud/pom/BcHistory_list.html">
                            <i class="fa fa-gavel"></i>播控历史
                        </a></li>

                        <li><a
                                title="/poms-cloud/pom/PubHistory_query.html">
                            <i class="fa fa-external-link"></i>发布历史
                        </a></li>

                        <!--
                                <li><a
                                    title="/poms-cloud/pom/XmlHistory_query.html">
                                        <i class="fa fa-external-link"></i>XML同步记录
                                </a></li>
                               -->
                    </ul></li>

                <li><a class="dropdown-toggle"> <i class="fa fa-pie-chart"></i>
                    <span class="menu-text"> 运营数据 </span> <i class="fa fa-angle-down"></i>
                </a>
                    <ul class="submenu nav-hide" style="display: none">


                        <li><a title="/mam-cloud/mam/Stat_queryStats.htm">
                            <i class="fa fa-bar-chart" aria-hidden="true"></i> 媒资数据统计
                        </a></li>


                    </ul></li>

                <li><a class="dropdown-toggle"> <i class="fa fa-cog"></i> <span
                        class="menu-text"> 系统管理 </span> <i class="fa fa-angle-down"></i>
                </a>

                    <ul class="submenu nav-hide">

                        <li><a title="/mam-cloud/mam/CpManage_view.htm"> <i
                                class="fa fa-cubes"></i> CP管理
                        </a></li>

                        <li><a title="/mam-cloud/mam/UserInfo_view.htm"> <i
                                class="fa fa-cubes"></i> FTP账号管理
                        </a></li>

                        <li><a title="/mam-cloud/mam/Log_manage.htm"> <i
                                class="fa fa-th-list"></i> 日志管理
                        </a></li>

                        <li><a title="/mam-cloud/mam/BaseAttr_manage.htm">
                            <i class="fa fa-paperclip"></i> 标签管理
                        </a></li>

                        <li><a title="/mam-cloud/mam/Notice_manage.htm"> <i
                                class="fa fa-bell"></i> 公告管理
                        </a></li>

                        <li><a title="/mam-cloud/mam/SysParams_manage.htm">
                            <i class="fa fa-cogs"></i> 系统参数管理
                        </a></li>

                        <li><a
                                title="/poms-cloud/pom/BcInfoManagement_execute.html">
                            <i class="fa fa-trello"></i> 播控方管理
                        </a></li>

                        <li><a
                                title="/mam-cloud/mam/Content_view_labelClassfy.htm"> <i
                                class="fa fa-tasks"></i>条目管理
                        </a></li>
                        <li><a
                                title="/mam-cloud/sys/mediaRuleManage.htm"> <i
                                class="fa fa-lock"></i>转码规则
                        </a></li>
                        <li><a
                                title="/mam-cloud/sys/listVideoStg.htm"> <i
                                class="fa fa-key"></i>音视频转码策略
                        </a></li>


                    </ul></li>

            </ul>
            <!-- /.nav-list -->

            <script type="text/javascript">
                try {
                    ace.settings.check('sidebar', 'collapsed')
                } catch (e) {
                }
                $(window).resize(function(){
                    if($(window).width() > 991){
                        $(".sidebar").removeClass("collapse");
                    }
                })
                $("#sidebar-collapse").click(function(){
                    if($(".sidebar").hasClass("menu-min") == true){
                        $(".menu-text").css("margin-top","0");
                        $(".submenu").css("top","0")
                        $(".sidebar .fa.fa-angle-down").css("display","block");
                    }else{
                        $(".sidebar .fa.fa-angle-down").css("display","none");
                    }
                })
            </script>
        </div>


        <!--侧边栏slider结束-->

        <!--内容部分开始-->
        <div class="main-content">
            <div class="page-content" id="main_page">

            </div><!-- /.page-content -->
        </div><!-- /.main-content -->

    </div><!-- /.main-container-inner -->

</div><!-- /.main-container -->

<!-- <script type="text/javascript">
    if ("ontouchend" in document) document.write("<script src='./ace/assets/js/jquery.mobile.custom.js'>" + "<" + "/script>");
</script>
<script src="./ace/assets/js/typeahead.jquery.js"></script>

<script src="./ace/assets/js/ace-elements.js"></script>
<script src="./ace/assets/js/ace.js"></script>
<script type="text/javascript" src="./ace/assets/js/ace/ace.searchbox-autocomplete.js"></script> -->
<script type="text/javascript">
    if('ontouchstart' in document.documentElement) document.write("<script src='./ace/assets/js/jquery.mobile.custom.js'>"+"<"+"/script>");
</script>

<script src="./ace/assets/js/bootstrap.js"></script>
<script type="text/javascript" src="./js/jquery.form.js"></script>
<script src="./ace/assets/js/jquery-ui.custom.js"></script>
<script src="./ace/assets/js/jquery.ui.touch-punch.js"></script>
<script src="./ace/assets/js/flot/jquery.flot.js"></script>
<script src="./ace/assets/js/flot/jquery.flot.pie.js"></script>
<script src="./ace/assets/js/flot/jquery.flot.resize.js"></script>
<script src="./ace/assets/js/ace/elements.scroller.js"></script>
<script src="./ace/assets/js/ace/elements.colorpicker.js"></script>
<script src="./ace/assets/js/ace/elements.fileinput.js"></script>
<script src="./ace/assets/js/ace/elements.typeahead.js"></script>
<script src="./ace/assets/js/ace/elements.wysiwyg.js"></script>
<script src="./ace/assets/js/ace/elements.spinner.js"></script>
<script src="./ace/assets/js/ace/elements.treeview.js"></script>
<script src="./ace/assets/js/ace/elements.wizard.js"></script>
<script src="./ace/assets/js/ace/elements.aside.js"></script>
<script src="./ace/assets/js/ace/ace.js"></script>
<script src="./ace/assets/js/ace-extra.js"></script>
<script src="./ace/assets/js/ace/ace.ajax-content.js"></script>
<script src="./ace/assets/js/ace/ace.touch-drag.js"></script>
<script src="./ace/assets/js/ace/ace.sidebar.js"></script>
<script src="./ace/assets/js/ace/ace.sidebar-scroll-1.js"></script>
<script src="./ace/assets/js/ace/ace.submenu-hover.js"></script>
<script src="./ace/assets/js/ace/ace.widget-box.js"></script>
<script src="./ace/assets/js/ace/ace.settings.js"></script>
<script src="./ace/assets/js/ace/ace.settings-rtl.js"></script>
<script src="./ace/assets/js/ace/ace.settings-skin.js"></script>
<script src="./ace/assets/js/ace/ace.widget-on-reload.js"></script>
<script src="./ace/assets/js/ace/ace.searchbox-autocomplete.js"></script>
<script type="text/javascript"> ace.vars['base'] = '/mam-cloud/'; </script>
<script src="./ace/assets/js/ace/elements.onpage-help.js"></script>
<script src="./ace/assets/js/ace/ace.onpage-help.js"></script>
<script src="./js/highcharts.js"></script>
<!-- jqgrid -->
<script src="./ace/assets/js/jqGrid/jquery.jqGrid.src.js"></script>
<script src="./ace/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
<!-- jquery plugin -->
<script src="./ace/assets/js/jquery-ui.js"></script>
<script src="./ace/assets/js/jquery-ui.custom.js"></script>
<script src="./ace/assets/js/jquery.ui.touch-punch.js"></script>
<script src="./ace/assets/js/jquery.gritter.js"></script>
<script src="./ace/assets/js/jquery.easypiechart.js"></script>
<!-- <script src="./ace/assets/js/jquery.sparkline.js"></script> -->
<script src="./ace/assets/js/typeahead.jquery.js"></script>
<script src="./ace/assets/js/jquery.hotkeys.js"></script>
<script src="./ace/assets/js/bootstrap-wysiwyg.js"></script>
<script src="./ace/assets/js/fuelux/fuelux.wizard.js"></script>
<script src="./ace/assets/js/select2.js"></script>
<script src="./ace/assets/js/x-editable/bootstrap-editable.js"></script>
<script src="./ace/assets/js/x-editable/ace-editable.js"></script>
<script src="./ace/assets/js/jquery.colorbox.js"></script>
<script src="./ace/assets/js/jquery.maskedinput.js"></script>
<script src="./ace/assets/js/chosen.jquery.js"></script>
<script src="./ace/plugins/js/jquery-pin/jquery.pin.min.js"></script>
<script src="./ace/plugins/js/json/json2.min.js"></script>
<script src="./ace/plugins/js/waypoints/jquery.waypoints.min.js"></script>
<script src="./ace/plugins/js/jquery-jcrop/jquery.Jcrop.js"></script>
<script src="./ace/plugins/js/autocomplete/jquery.autocomplete.js"></script>
<script src="./ace/plugins/js/msgTips.js"></script>

<script src="./ace/plugins/js/lobibox.js"></script>
<!-- ztree -->
<script src="./ace/plugins/js/ztree/jquery.ztree.core-3.5.js"></script>
<script src="./ace/plugins/js/ztree/jquery.ztree.excheck-3.5.js"></script>
<script src="./ace/plugins/js/ztree/jquery.ztree.exedit-3.5.js"></script>
<script src="./ace/plugins/js/ztree/jquery.ztree.exhide-3.5.min.js"></script>
<script src="./ace/assets/js/date-time/moment-with-locales.js"></script><!--时间获取和处理js -->
<script src="./ace/assets/js/date-time/moment.js"></script>
<script src="./ace/assets/js/date-time/daterangepicker.js"></script>
<script src="./ace/assets/js/date-time/bootstrap-datepicker.js"></script>
<script src="./ace/assets/js/date-time/bootstrap-datetimepicker.js"></script>
<script src="./ace/assets/js/date-time/todayafternoselect-bootstrap-datepicker.js"></script>
<script src="./ace/assets/js/date-time/bootstrap-timepicker.js"></script>
<script type="text/javascript" src="./js/my-datetimepicker.js"></script>
<script type="text/javascript" src="./js/jquery.datetimepicker.full.js"></script>
<!-- 版权文件控件 -->
<script type="text/javascript" src="./mam/copyright/dropzone.js"></script>
<script src="./ace/assets/js/bootbox.js"></script>
<script src="./ace/assets/js/fuelux/fuelux.spinner.js"></script>
<!-- <script src="./ace/plugins/js/resize.js"></script> -->
<script src="./ace/plugins/js/mytag/elements.mytypeahead.js"></script>
<script src="./ace/plugins/js/mytag/bootstrap-mytag.js"></script>
<script src="./js/imageEditer.js?v=1.0.7"></script>
<!--双向 选择框 -->
<script src="./ace/assets/js/jquery.bootstrap-duallistbox.js"></script>
<script src="./ace/assets/js/fuelux/fuelux.tree.js"></script>
<script src="./ace/plugins/js/mouseRightmenu/jquery-smartMenu.js"></script>
<script src="./ace/plugins/js/echarts/dist/echarts.js"></script>
<script src="./ace/plugins/js/zyFile/zyFile.js"></script>
<script src="./ace/plugins/js/zyFile/zyUpload.js"></script>
<!--多频道字幕滚动 -->
<script src="./ace/plugins/js/jquery.marquee.js"></script>
<script type="text/javascript" src="./js/main.js"></script>
<script type="text/javascript" src="./js/util.js"></script>
<script src="./js/down_list.js"></script>
<script src="./js/labelBox.js?v=1.0.7"></script>
<script src="./js/starSelect.js"></script>
<!-- 版权绑定媒资列表控件 -->
<script src="./mam/copyright/jQuery.autoSearchTextCopyright2.js"></script>
<script type="text/javascript" src="./js/base.js"></script>
<script type="text/javascript" src="./js/page.js"></script>
<script type="text/javascript" src="./js/sampleTable.js"></script>



<input type="hidden" id="material-alert" value="提示">
<input type="hidden" id="button-delete" value="删除">
<input type="hidden" id="sys-confirmDel" value="确认删除？">
<input type="hidden" id="sys-del-success" value="删除成功！">
<input type="hidden" id="sys-del-fail" value="删除失败！">

<!-- 媒资 -->
<input type="hidden" id="media-reso-01" value="176*144">
<input type="hidden" id="media-reso-02" value="240*180">
<input type="hidden" id="media-reso-03" value="320*240">
<input type="hidden" id="media-reso-04" value="352*288">
<input type="hidden" id="media-reso-05" value="400*240">
<input type="hidden" id="media-reso-06" value="320*480">
<input type="hidden" id="media-reso-07" value="480*360">
<input type="hidden" id="media-reso-08" value="640*480">
<input type="hidden" id="media-reso-09" value="720*576">
<input type="hidden" id="media-reso-10" value="800*480">
<input type="hidden" id="media-reso-11" value="1280*720">
<input type="hidden" id="media-reso-12" value="1920*1080">
<input type="hidden" id="media-reso-13" value="1920*1080">
<input type="hidden" id="media-reso-14" value="400*300">
<input type="hidden" id="media-reso-15" value="400*320">
<input type="hidden" id="media-reso-16" value="720*480">
<input type="hidden" id="media-reso-17" value="480*272">
<input type="hidden" id="media-reso-18" value="640*360">
<input type="hidden" id="media-reso-19" value="1024*576">
<input type="hidden" id="media-reso-20" value="854*480">
<input type="hidden" id="media-reso-21" value="2560*1440">

<!-- 系统日志 -->
<!-- '用户ID','操作用户名', '操作时间','对象ID','对象类型','操作行为','触发方式' -->
<input type="hidden" id="sys-sysLog-userId" value="操作用户ID">
<input type="hidden" id="sys-sysLog-userName" value="操作用户名">
<input type="hidden" id="sys-sysLog-createTime" value="操作时间">
<input type="hidden" id="sys-sysLog-objId" value="对象ID">
<input type="hidden" id="sys-sysLog-objType" value="对象类型">
<input type="hidden" id="sys-sysLog-objType-1" value="点播">
<input type="hidden" id="sys-sysLog-objType-2" value="直播">
<input type="hidden" id="sys-sysLog-objType-3" value="版权">
<input type="hidden" id="sys-sysLog-objType-4" value="人物">
<input type="hidden" id="sys-sysLog-objType-5" value="奖项">
<input type="hidden" id="sys-sysLog-objType-6" value="条目">
<input type="hidden" id="sys-sysLog-objType-7" value="自频道">
<input type="hidden" id="sys-sysLog-objType-8" value="自频道节目单">
<input type="hidden" id="sys-sysLog-objType-9" value="EPG">
<input type="hidden" id="sys-sysLog-objType-10" value="系统参数">
<input type="hidden" id="sys-sysLog-objType-11" value="直播收录">
<input type="hidden" id="sys-sysLog-objType-12" value="组织">
<input type="hidden" id="sys-sysLog-objType-13" value="输出源">
<input type="hidden" id="sys-sysLog-objType-14" value="多频道监控">
<input type="hidden" id="sys-sysLog-objType-15" value="媒资（点播或直播）图片">
<input type="hidden" id="sys-sysLog-objType-16" value="内容">
<input type="hidden" id="sys-sysLog-objType-17" value="???sys.sysLog.objType.17???">
<input type="hidden" id="sys-sysLog-objType-18" value="???sys.sysLog.objType.18???">
<input type="hidden" id="sys-sysLog-objType-19" value="???sys.sysLog.objType.19???">
<input type="hidden" id="sys-sysLog-objType-20" value="???sys.sysLog.objType.20???">
<input type="hidden" id="sys-sysLog-objType-21" value="???sys.sysLog.objType.21???">
<input type="hidden" id="sys-sysLog-operCode" value="操作行为">
<input type="hidden" id="sys-sysLog-triggerWay" value="触发方式">
<input type="hidden" id="sys-sysLog-triggerWay-0" value="人工">
<input type="hidden" id="sys-sysLog-triggerWay-1" value="系统">
<input type="hidden" id="sys-sysLog-triggerWay-2" value="外部接口">

<input type="hidden" id="sys-sysLog-error-time" value="开始时间不能大于结束时间！">
<input type="hidden" id="sys-sysLog-error-typeId" value="对象ID必须是数字!">

<!-- 系统参数 -->

<input type="hidden" id="sys-error-selectOne" value="请至少选择一条数据！">
<input type="hidden" id="sys-error-onlyOne" value="一次只能选择一条数据！">
<!-- 'ID', '键','值','参数说明' -->
<input type="hidden" id="sys-sysConfig-id" value="ID">
<input type="hidden" id="sys-sysConfig-key" value="键">
<input type="hidden" id="sys-sysConfig-value" value="值">
<input type="hidden" id="sys-sysConfig-detail" value="参数说明">

<input type="hidden" id="sys-error-keyEmpty" value="键不可为空！">
<input type="hidden" id="sys-error-valueEmpty" value="值不可为空！">

<!-- 发布任务管理 -->

<input type="hidden" id="sys-pubTask-assetId" value="媒资ID">
<input type="hidden" id="sys-pubTask-assetName" value="媒资名称">
<input type="hidden" id="sys-pubTask-pubState" value="同步状态">
<input type="hidden" id="sys-pubTask-pubState-0" value="待同步">
<input type="hidden" id="sys-pubTask-pubState-1" value="同步中">
<input type="hidden" id="sys-pubTask-pubState-2" value="同步成功">
<input type="hidden" id="sys-pubTask-pubState-3" value="同步失败">
<input type="hidden" id="sys-pubTask-pubState-4" value="人工撤销">
<input type="hidden" id="sys-pubTask-repub" value="重新发布">
<input type="hidden" id="sys-pubTask-error-assetid" value="媒资ID必须是数字！">
<input type="hidden" id="sys-pubTask-contId" value="内容ID">
<input type="hidden" id="sys-pubTask-oprcode" value="操作">
<input type="hidden" id="sys-pubTask-oprcode-1" value="同步">
<input type="hidden" id="sys-pubTask-oprcode-2" value="下线">
<input type="hidden" id="sys-pubTask-pubtime" value="同步时间">
<input type="hidden" id="sys-pubTask-urgency" value="优先级">
<input type="hidden" id="sys-pubTask-urgency-0" value="普通">
<input type="hidden" id="sys-pubTask-urgency-1" value="热点">
<input type="hidden" id="sys-pubTask-urgency-2" value="紧急">
<input type="hidden" id="sys-pubTask-machine" value="同步服务器">

<input type="hidden" id="award-start-edit" value="启用编辑">

<!-- 转码策略 -->
<input type="hidden" id="mediaRule-ruleId" value="ID">
<input type="hidden" id="mediaRule-nameRule" value="文件命名规范">
<input type="hidden" id="mediaRule-codeRate" value="码率等级">
<input type="hidden" id="mediaRule-codeFormat" value="编码格式">
<input type="hidden" id="mediaRule-containFormat" value="容器封装格式">
<input type="hidden" id="mediaRule-fileType" value="文件类型">
<input type="hidden" id="mediaRule-usagCode" value="编码分类">
<input type="hidden" id="mediaRule-usagDesc" value="编码分类描述">
<input type="hidden" id="mediaRule-isSource" value="是否是源文件">
<input type="hidden" id="mediaRule-profile" value="转码策略名">
<input type="hidden" id="mediaRule-netType" value="网络类型">
<input type="hidden" id="mediaRule-mimeType" value="mime类型">
<input type="hidden" id="mediaRule-resolution" value="分辨率">
<input type="hidden" id="mediaRule-bitRate" value="视频码率">
<input type="hidden" id="mediaRule-mediaProfile" value="MEDIAPROFILE">
<input type="hidden" id="mediaRule-audioBitRate" value="音频码率">
<input type="hidden" id="mediaRule-mediaLevel" value="清晰度">
<input type="hidden" id="mediaRule-error-usagCode" value="编码分类需为整数">
<input type="hidden" id="mediaRule-error-usagDesc" value="编码分类描述不能为空">
<input type="hidden" id="mediaRule-error-isSource" value="是否是源文件不能为空">
<input type="hidden" id="mediaRule-error-codeFormat" value="编码格式不能为空">
<input type="hidden" id="mediaRule-error-containFormat" value="容器封装格式不能为空">
<input type="hidden" id="mediaRule-error-codeRate" value="码率等级不能为空">
<input type="hidden" id="mediaRule-error-netType" value="网络类型不能为空">
<input type="hidden" id="mediaRule-error-mimeType" value="mime类型不能为空">
<input type="hidden" id="mediaRule-error-nameRule" value="文件命名规范不能为空">
<input type="hidden" id="mediaRule-error-profile" value="转码策略名不能为空">
<input type="hidden" id="mediaRule-error-fileType" value="文件类型不能为空">

<input type="hidden" id="mediaStrategy-id" value="策略ID">
<input type="hidden" id="mediaStrategy-name" value="策略名">
<input type="hidden" id="mediaStrategy-description" value="描述">
<input type="hidden" id="mediaStrategy-mediaUsagecodes" value="编码参数">
<input type="hidden" id="mediaStrategy-belong" value="属于">
<input type="hidden" id="mediaStrategy-videoType" value="适用于">
<input type="hidden" id="mediaStrategy-videoType-0" value="纯音频">
<input type="hidden" id="mediaStrategy-videoType-1" value="2D">
<input type="hidden" id="mediaStrategy-videoType-2" value="vr">
<input type="hidden" id="mediaStrategy-videoType-3" value="3D">
<input type="hidden" id="mediaStrategy-isAvailable" value="是否可用">
<input type="hidden" id="mediaStrategy-isAvailable-0" value="不可用">
<input type="hidden" id="mediaStrategy-isAvailable-1" value="可用">
<input type="hidden" id="mediaStrategy-error-name" value="策略名不能为空">
<input type="hidden" id="mediaStrategy-error-description" value="描述不能为空">
<input type="hidden" id="mediaStrategy-error-mediaUsagecodes" value="编码参数不能为空"><!-- inline scripts related to this page -->
<script type="text/javascript">
    var poms_webroot = "/poms-cloud/";
    var __webroot__ = './';
    var webroot = './';
    var _cpid = '0';
    var context_path = '/mam-cloud/';
    isShortMAM = ('1' == '1');
    var preview_source = 'http://127.0.0.1:8080/source/';
    var preview_store_tmp = 'http://127.0.0.1:8080/store_tmp/asset/zhengshi';
    var preview_store = 'http://127.0.0.1:8080/store/zhengshi';
    //slidebar
    $(document).ready(function() {
        //initBcList();
        initProductList();
        $.ajaxSetup({
            cache : false
            //关闭AJAX相应的缓存
        });

        $("#left_menu").on("click","li a",function(){
            if($(this).parent().find("ul[id='productList']").length > 0){
                if($(this).parent().find("ul[id='productList'] li").length == 0){
                    initProductList();
                }
            }else{
                var url = $(this).attr("title");
                if (url) {
                    openPage(url);
                }
            }
        });

        $("#left_menu").find('li:first').addClass("active");
        openPage(context_path+ "mam/Content_view_workbench.htm");

        /*系统公告删除*/
        $(".frb_close img").on("click",function(){
            $(".frb").remove()
        })
        /*系统公告删除*/
    });


    function openPage(url) {
        var p = $("#main_page");
        if (p.length > 0) {
            $.ajaxSetup ({
                cache: false //关闭AJAX相应的缓存
            });
            p.load(url, function(response, status, xhr) {
                if (status != "success") {
                    if(checkSession) {
                        checkSession();
                    }
                }
            });
        }
    }

    //定时检查session是否过期 2分钟执行一次
    var tmchecksession = setInterval("checkSession()", 120000);
    var checksessioncount = 0;
    function checkSession() {
        $.ajax({url: __webroot__ + "mam/Test_checkSession.inf"}).done(function (data) {
            checksessioncount = 0;
        }).fail(function( jqXHR, textStatus ) {
            checksessioncount ++;
            if(checksessioncount > 3) {
                checksessioncount = 0;
                clearInterval(tmchecksession);
                Lobibox.confirm({
                    title:"提示",
                    msg: "您的登录已超时，系统即将刷新，是否重新登录？",
                    callback: function ($this, type, ev) {
                        if (type === 'yes') {
                            location.reload();
                        }
                    }
                });
            }
        });
    }
</script>
</body>
</html>
