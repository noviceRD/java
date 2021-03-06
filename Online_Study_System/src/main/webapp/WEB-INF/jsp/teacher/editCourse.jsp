<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title>修改课程信息</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="${ctx}/js/jquery-3.2.1.min.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
</head>
<body>
	<!-- 顶栏 -->
	<jsp:include page="../teacher/top.jsp"/>
	<!-- 中间主体 -->
		<div class="container" id="content">
		<div class="row">
			<jsp:include page="../teacher/menu.jsp"/>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 style="text-align: center;">修改课程信息</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form class="form-horizontal" enctype="multipart/form-data" role="form" action="${ctx}/teacher/editCourse" id="editfrom" method="post">
							<input type="hidden" name="id" value="${course.id}"/>
							  <div class="form-group">
							    <label for="name" class="col-sm-2 control-label">课程名称</label>
									<div class="col-sm-10">
							      <input type="text" class="form-control" id="name" name="name" value="${course.name}" placeholder="请输入课程名称">
							    </div>
							  </div>
							<div class="form-group">
								<label class="col-sm-2 control-label">授课老师</label>
								<div class="col-sm-10">
									<input class="form-control" type="hidden" name="teacherId" value="${teacher.id}">
									<input class="form-control" type="text" name="teacherName" value="${teacher.name}" readonly>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-2 control-label">选择文件</label>
								<div class="col-sm-10">
									<input  type="file" name="file" id="file">
								</div>
							</div>
							<div class="form-group" style="text-align: center">
								<button class="btn btn-default" type="submit">提交</button>
								<button class="btn btn-default" type="reset">重置</button>
							</div>
						</form>
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
	<script type="text/javascript">
		$("#nav li:nth-child(1)").addClass("active")

        var teacheridSelect = $("#teacherId option");
        for (var i=0; i<teacheridSelect.length; i++) {
            if (teacheridSelect[i].value == '${course.teacherId}') {
                teacheridSelect[i].selected = true;
            }
        }
	</script>
</html>