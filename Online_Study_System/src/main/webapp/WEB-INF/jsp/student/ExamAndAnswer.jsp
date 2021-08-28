<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>

<html>
<head>
    <title>课后习题</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- 引入bootstrap -->
    <link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
    <!-- 引入JQuery  bootstrap.js-->
    <script src="${ctx}/js/jquery-3.2.1.min.js"></script>
    <script src="${ctx}/js/bootstrap.min.js"></script>

</head>

<body>
<!-- 顶栏 -->
<jsp:include page="top.jsp"/>
<!-- 中间主体 -->
<div class="container" id="content">
    <div class="row">
        <jsp:include page="menu.jsp"/>
        <div class="col-md-10">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <div class="row">
                        <h1 style="text-align: center;">课后练习及答案</h1>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="panel-body">
                        <div class="tm_paper_head">
                            <h5 style="background:#a6b4cd; color: #ffffff; padding:15px 0; font-size:14px; font-weight:bold; text-align: center" >
                              得分：  ${studentCourse.mark}分
                            </h5>
                        </div>
                        <div class="row">
                            <c:forEach items="${questionsList}" var="ch">
                                <li>
                                    <div style="border-bottom: 2px solid #FFF;margin-left: 30px;">
                                        <h3>${ch.title}(${ch.score}分)</h3>
                                        <p>${ch.itemA}</p>
                                        <p>${ch.itemB}</p>
                                        <p>${ch.itemC}</p>
                                        <p>${ch.itemD}</p>
                                        <p>选择答案:
                                            <c:if test="${ch.answer== ch.myAnswer}">
                                                <span style="color:green"><b>${ch.myAnswer}</b></span>
                                            </c:if>
                                            <c:if test="${ch.answer!= ch.myAnswer}">
                                                <span style="color:red">
                                                <b>${ch.myAnswer}</b>
                                                </span>
                                            </c:if>
                                            <br/>
                                            正确答案: <span style="color:#F1AF00;"><b>${ch.answer}</b></span>
                                            &emsp;
                                            </br>
                                        </p>
                                    </div>
                                </li>
                            </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container" id="footer">
        <div class="row">
            <div class="col-md-12"></div>
        </div>
    </div>

</body>

</html>
