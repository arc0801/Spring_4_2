<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board} reply</title>
<c:import url="../layout/bootStrap.jsp" />
<c:import url="../layout/summernote.jsp" />
</head>
<body>
	<c:import url="../layout/nav.jsp" />
	
	<h1>${board} Reply</h1>
	
	<form action="./${board}Reply" method="post" style="width: 70%; margin: 0 auto;">
	
				<input type="hidden" value="${num}" name="num"> 
		
				<div class="form-group">
					<label for="title">Title:</label> 
					<input type="text" name="title" class="form-control" id="title" placeholder="Enter title">
				</div>
				
				<div class="form-group">
					<label for="writer">Writer:</label> 
					<input type="text" name="writer" class="form-control" id="writer" placeholder="Enter writer">
				</div>

				<div class="form-group">
					<label for="contents">Contents:</label>
					<textarea rows="20" cols="" name="contents" class="form-control" id="contents" placeholder="Enter contents" name="editordata"></textarea>
				</div>

		<button>Reply</button>
	</form>
	
<script type="text/javascript">
	$("#contents").summernote({
			placeholder: 'Enter reply contents',
			minHeight: 300,  
			maxHeight: null,
			height: 300
	});



</script>

</body>
</html>