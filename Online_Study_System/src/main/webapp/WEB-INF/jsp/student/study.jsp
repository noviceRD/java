<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>视频播放</title>
    <script src="${ctx}/js/modernizr.min.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/normalize.css" /><!--CSS RESET-->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/demo.css"><!--演示页面样式，使用时可以不引用-->
    <link href="${ctx}/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="${ctx}/css/style.css">
</head>
<body>
<div class="wrapper">

    <div class="js-video">
        <video class="js-media" poster="${ctx}/vidoe_iamges/${course.image}">
            <source src="${ctx}/video/${course.video}" type="video/mp4" />
            <p>你的浏览器不支持 HTML5 Video</p>
        </video>
        <i data-playPause class="playPause fa fa-play ui-icon"><span></span></i>
        <div class="ui">
            <div>
                <div data-progress class="progress">
                    <div data-buffer class="progress-buffer"></div>
                    <div data-time class="progress-time"></div>
                </div><!-- progress -->
            </div>
            <div>
                <span class="timeDisplay"><i data-currentTime>0:00</i> / <i data-duration>0:00</i></span>
            </div>
            <div>
                <i data-mute class="fa fa-volume-up ui-icon"></i>
            </div>
            <div>
                <div data-volume="100" class="volumeControl"><span class="ui-slider-handle"></span></div>
            </div>
        </div><!-- ui -->
        <i data-fullscreen class="fullscreen iconicfill-fullscreen" title="fullscreen"></i>
    </div><!-- js-video -->

</div>

<script src="${ctx}/js/jquery-1.11.0.min.js" type="text/javascript"></script>
<script src="${ctx}/js/vedio.js"></script>
</body>
</html>
