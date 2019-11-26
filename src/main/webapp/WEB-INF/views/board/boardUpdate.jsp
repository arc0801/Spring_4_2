<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board} update</title>
<c:import url="../layout/bootStrap.jsp" />
<c:import url="../layout/summernote.jsp" />
</head>
<body>
<c:import url="../layout/nav.jsp" />
	
	<h1>${fn:toUpperCase(board)} Update</h1>
	
	<form action="./${board}Update" method="post" style="width: 70%; margin: 0 auto;" enctype="multipart/form-data">
	
				<input type="hidden" name="num" value="${dto.num}">
		
				<div class="form-group">
					<label for="title">Title:</label> 
					<input type="text" name="title" class="form-control" id="title" placeholder="Enter title" value="${dto.title}">
				</div>

				<div class="form-group">
					<label for="contents">Contents:</label>
					<textarea rows="20" cols="" name="contents" class="form-control" id="contents" name="editordata"></textarea>
				
				</div>


				<div>
				<c:forEach items="${dto.files}" var="file">
					<div class="form-group ff" id="f${file.fnum}">
						<p>${file.oname}<input type="button" id="${file.fnum}" value="del" class="del_file"></p>		
						<%-- 					
						<div style="width: 100px; float: left;"><label for="file">File:</label></div>
						<div style="width: 100px; float: left;"><a href="../resources/upload/${board}/${file.fname}">${file.oname}</a></div>
						<input type="button" class="btn btn-default" value="수정">	 
						--%>
					</div>				
				 </c:forEach>
				</div>


		<div id="files">
			<div class="form-group" title="parent">
				<label for="file" style="float: left;">File:</label> 
				<input type="file" class="form-control" name="file" style="width: 93%; float: left;"> 
				<input type="button" class="btn btn-danger del" value="del">
			</div>
		</div>


		<input type="button" class="btn btn-success" value="ADD FILE" id="add"> <br>

		<button class="btn btn-default">UPDATE</button>
				
	</form>
	
	
	
	<script type="text/javascript">
		//var count = ${count};
		//var count = $(".ff").length;
		var count = ${fn:length(dto.files)};

		//alert(count);
		
	
	
		$(".del_file").click(function() {
			var fnum = $(this).attr("id");
		
			$.post("./fileDelete", {fnum:fnum}, function(data) {	
				//alert(data);
				
				data = data.trim();
				if(data == '1'){
					count--;
					$("#f"+fnum).remove();					
				} else {
					alert("삭제할 파일이 없습니다.");
				}
			});
		});
		
		
		var files = $("#files").html();
		$("#files").empty();
		
		
		$("#add").click(function() {
			if (count < 5) {
				$("#files").append(files);
				count++;
			} else {
				alert("파일추가는 최대 5개까지 가능합니다.")
			}
		});
		
		$("#files").on("click", ".del", function() {
			count--;
			$(this).parent().remove();
			//$(this).parents(".form-group").remove();				
		});
		
		//summernote
		// contents : summernote 불러오기
		$('#contents').summernote({
			minHeight: 300,  
			maxHeight: null,
			height: 300
		});
		
		//contents의 값 받기
/* 		$("#btn").click(function() {
			//alert($("#contents").val());
			alert($("#contents").summernote('code'));
		}); */
		
		//contents에 값 넣기
		var con = "${dto.contents}";
		$("#contents").summernote('code',con);


	</script>
	

</body>
</html>