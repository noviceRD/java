<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jsp/taglib.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<title>学生管理</title>

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
					    	<h1 class="col-md-5">学生管理</h1>
							<button class="btn btn-default col-md-2" style="margin-top: 20px" onClick="location.href='${ctx}/admin/addStudent'">
								添加学生信息
								<sapn class="glyphicon glyphicon-plus"/>
							</button>

						</div>
				    </div>
				    <table class="table table-bordered">
					        <thead>
					            <tr>
					                <th>学号</th>
				  					<th>姓名</th>
				  					<th>性别</th>
				  					<th>操作</th>
					            </tr>
					        </thead>
					        <tbody>
							<c:forEach  items="${studentList}" var="item">
								<tr>
									<td>${item.number}</td>
									<td>${item.name}</td>
									<td>${item.sex}</td>
									<td>
										<button class="btn btn-default btn-xs btn-info" onClick="location.href='${ctx}/admin/editStudent?id=${item.id}'">修改</button>
										<a class="btn btn-danger btn-xs" href="${ctx}/admin/removeStudent?id=${item.id}" onclick="return checkConfirm()">删除</a>
									</td>
								</tr>
							</c:forEach>
					        </tbody>
				    </table>
				    <div class="panel-footer">
						<c:if test="${pagingVO != null}">
							<nav style="text-align: center">
								<ul class="pagination">
									<li><a href="${ctx}/admin/showStudent?page=${pagingVO.upPageNo}">&laquo;上一页</a></li>
									<li class="active"><a href="">${pagingVO.curentPageNo}</a></li>
									<c:if test="${pagingVO.curentPageNo+1 <= pagingVO.totalCount}">
										<li><a href="${ctx}/admin/showStudent?page=${pagingVO.curentPageNo+1}">${pagingVO.curentPageNo+1}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+2 <= pagingVO.totalCount}">
										<li><a href="${ctx}/admin/showStudent?page=${pagingVO.curentPageNo+2}">${pagingVO.curentPageNo+2}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+3 <= pagingVO.totalCount}">
										<li><a href="${ctx}/admin/showStudent?page=${pagingVO.curentPageNo+3}">${pagingVO.curentPageNo+3}</a></li>
									</c:if>
									<c:if test="${pagingVO.curentPageNo+4 <= pagingVO.totalCount}">
										<li><a href="${ctx}/admin/showStudent?page=${pagingVO.curentPageNo+4}">${pagingVO.curentPageNo+4}</a></li>
									</c:if>
									<li><a href="${ctx}/admin/showStudent?page=${pagingVO.totalCount}">最后一页&raquo;</a></li>
								</ul>
							</nav>
						</c:if>
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
		$("#nav li:nth-child(2)").addClass("active");

        function checkConfirm() {
            var msg = "您真的确定要删除吗？！";
            if (confirm(msg)==true){
                return true;
            }else{
                return false;
            }
        }

        $("#sub").click(function () {
            $("#form1").submit();
        });

        <c:if test="${pagingVO != null}">
			if (${pagingVO.curentPageNo} == ${pagingVO.totalCount}) {
				$(".pagination li:last-child").addClass("disabled")
			};

			if (${pagingVO.curentPageNo} == ${1}) {
				$(".pagination li:nth-child(1)").addClass("disabled")
			};
        </c:if>
	</script>
</html>