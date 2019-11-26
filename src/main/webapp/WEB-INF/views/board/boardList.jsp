<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
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

.a {
	color: black;
	text-decoration: none;
}

.C {
	text-transform: capitalize;
}

</style>

</head>
<body>
	<c:import url="../layout/nav.jsp" />
	
	
	<h1 class="C">${board} List</h1>

	<section style="background-color: rgb(240, 240, 240); height: auto; padding-bottom: 20px;">
		<div class="div_t">

		<div>
			<form action="./${board}List" id="frm">
				<input type="hidden" value="1" name="curPage" id="curPage">
			
				<select name="kind">				
					<option id="kT" value="kT">제목</option>
					<option id="kW" value="kW">작성자</option>
					<option id="kC" value="kC">내용</option>
				</select>
			
				<input type="text" name="search" value="${pager.search}">
				
				<button>검색</button>
			</form>
		</div>

			<table class="t">
				<thead>
					<tr class="notice">
						<td colspan="5" class="C">${board}</td>
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
					<c:forEach items="${list}" var="dto">
						<tr class="b3">
							<td>${dto.num}</td>
							
							
							<td class="b4">
								<c:catch>
									<c:forEach begin="1" end="${dto.depth}"> --</c:forEach>
								</c:catch>
								<a class="a" href="./${board}Select?num=${dto.num}">${dto.title}</a>
							</td>
							
							
							<td>${dto.writer}</td>
							<td>${dto.reg_date}</td>
							<td>${dto.hit}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>

			<div style="width: 100%; margin: 0 auto; text-align: center; padding-top: 10px;">
				<ul class="pagination" style="margin: 0 auto; text-align: center;">
					
					<c:if test="${pager.curBlock gt 1}">
						<li><span id=${pager.startNum - 1} class="list">이전</span></li>
					</c:if>
					
					<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" step="1" var="i">
						<li><span id="${i}" class="list">${i}</span></li>
					</c:forEach>

					<c:if test="${pager.curBlock lt pager.totalBlock}">
						<li><span id="${pager.lastNum + 1}" class="list">다음</span></li>
					</c:if>
					
				</ul>
			</div>
			
			<c:if test="${not empty member}">
				<a class="C" href="${board}Write">Go ${board} write</a><br>
			</c:if>
			
		</div>
		
	</section>
	

	<script type="text/javascript">
		var kind = '${pager.kind}';
		if (kind == '') {
			kind = "kT";
		}
		
		$("#"+kind).prop("selected", true);
	
		$(".list").click(function() {
			$("#curPage").val($(this).attr("id"));
			$("#frm").submit();
		});
	</script>

</body>
</html>