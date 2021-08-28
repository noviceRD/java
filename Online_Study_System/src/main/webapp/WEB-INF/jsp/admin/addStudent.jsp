<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title>添加学生信息</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<!-- 引入bootstrap -->
	<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
	<!-- 引入JQuery  bootstrap.js-->
	<script src="${ctx}/js/jquery-3.2.1.min.js"></script>
	<script src="${ctx}/js/bootstrap.min.js"></script>
</head>
<body>
	<jsp:include page="top.jsp"/>
    <div class="container" id="content">
        <div class="row">
			<jsp:include page="menu.jsp"/>
			<div class="col-md-10">
				<div class="panel panel-default">
				    <div class="panel-heading">
						<div class="row">
					    	<h1 style="text-align: center;">添加学生信息</h1>
						</div>
				    </div>
				    <div class="panel-body">
						<form class="form-horizontal" role="form" onsubmit="return add_student()" action="${ctx}/admin/addStudent" id="editfrom" method="post">
							<div class="form-group">
								<label class="col-sm-2 control-label">学号</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="number" id="number" placeholder="请输入学号">
								</div>
							</div>
							<div class="form-group">
							    <label class="col-sm-2 control-label">姓名</label>
							    <div class="col-sm-10">
							      <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
							    </div>
							  </div>
							  <div class="form-group">
							    <label class="col-sm-2 control-label">性别</label>
							    <div class="col-sm-10">
								    <label class="checkbox-inline">
									   	<input type="radio" name="sex" value="男" checked>男
									</label>
									<label class="checkbox-inline">
										<input type="radio" name="sex" value="女">女
									</label>
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
    $("#nav li:nth-child(2)").addClass("active")
</script>
<script>
    function add_student(){
        var number = $("#number").val();
        var name = $("#name").val();
        if(number==""){
            alert("学号不能为空！！");
            return false;
        }else if(name==""){
            alert("学生姓名不能为空！！")
            return false;
        }else {
            return true;
        }
    }
</script>
</html>