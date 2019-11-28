<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board} select</title>
<c:import url="../layout/bootStrap.jsp" />

<style type="text/css">
.div_t {
	width: 70%;
	margin: 0 auto;
	background-color: rgb(240, 240, 240);
}

.t {
	width: 100%;
	margin: 0 auto;
	text-align: center;
	font-size: 13px;
	border-bottom: 5px rgb(240, 240, 240) solid;
	border-spacing: 0px;
	border-collapse: collapse;
}

.notice {
	height: 70px;
	font-size: 30px;
	background-color: rgb(240, 240, 240);
}

.black {
	font-weight: bold;
	height: 50px;
	background-color: #252525;
	text-align: center;
	color: white;
}

.b1 {
	width: 10%;
}

.b2 {
	text-align: left;
	border-bottom: 5px rgb(240, 240, 240) solid;
}

.b3 {
	height: 50px;
	background-color: white;
	border-bottom: 5px rgb(240, 240, 240) solid;
}

.b4 {
	text-align: left;
	font-weight: bold;
}

.b5 {
	width: inherit;
	height: 400px;
}

.a {
	color: black;
	text-decoration: none;
}
</style>
</head>
<body>
	<c:import url="../layout/nav.jsp" />
	<h1>${board} Select</h1>

	<section
		style="background-color: rgb(240, 240, 240); height: auto; padding-bottom: 20px;">
		<div class="div_t">
			<table class="t">

				<thead>
					<tr class="notice">
						<td colspan="5">${board}</td>
					</tr>

					<tr class="black">
						<td class="b1">NO</td>
						<td>TITLE</td>
						<td class="b1">WRITER</td>
						<td class="b1">DATE</td>
						<td class="b1">HIT</td>
					</tr>
				</thead>

				<tbody>
					<tr class="b3">
						<td>${boardVO.num}</td>
						<td class="b4">${boardVO.title}</td>
						<td>${boardVO.writer}</td>
						<td>${boardVO.reg_date}</td>
						<td>${boardVO.hit}</td>
					</tr>

					<tr class="b3">
						<td class="b5" colspan="5" style="text-align: left; vertical-align: top; padding: 25px; height:300px;">
							${boardVO.contents}
						</td>
					</tr>
					
						<c:forEach items="${boardVO.files}" var="file">
					<tr style="background-color: white; border-bottom: 5px rgb(240, 240, 240) solid;">
						<td colspan="5" style="text-align: left; vertical-align: top; padding: 5px;">
						<a href="./fileDown?fnum=${file.fnum}">${file.oname}</a>					
						</td>
					</tr>
				        </c:forEach>
					
				</tbody>
			</table>
			
			<c:if test="${member.id eq boardVO.writer}">
				<button class="btn btn-default" id="upd">UPDATE</button>
				<button class="btn btn-default" id="del">DELETE</button>
			</c:if>
<%-- 			<a href="./${board}Update?num=${dto.num}">UPDATE</a>
			<a href="./${board}Delete?num=${dto.num}">DELETE</a> --%>
			
			<c:catch>
			<c:if test="${not empty boardVO.ref}">
				<button class="btn btn-default" id="rep">REPLY</button>
			</c:if>
			</c:catch>

		</div>
	</section>
	
	<script type="text/javascript">
	//문장 나누기 작업
	
	
	
	
	//
	$("#upd").click(function() {
		location.href="./${board}Update?num=${dto.num}";
	});
	
	$("#del").click(function() {
		location.href="./${board}Delete?num=${dto.num}";
	});
	
	$("#rep").click(function() {
		location.href="./${board}Reply?num=${dto.num}";
	});
	
	
	
	</script>
	
	

</body>
</html>