<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${board} write</title>
<c:import url="../layout/bootStrap.jsp" />
<c:import url="../layout/summernote.jsp" />
</head>
<body>
	<c:import url="../layout/nav.jsp" />
	
	<h1>${board} Write</h1>
	
	<form action="./${board}Write" method="post" style="width: 70%; margin: 0 auto;" enctype="multipart/form-data">
		
				<div class="form-group">
					<label for="title">Title:</label> 
					<input type="text" name="title" class="form-control" id="title" placeholder="Enter title">
				</div>

				<div class="form-group">
					<label for="writer">Writer:</label>
					<input type="text" name="writer" class="form-control" id="writer" readonly="readonly" value="${member.id}">
				</div>

				<div class="form-group">
					<label for="contents">Contents:</label>
					<textarea rows="10" cols="" name="contents" class="form-control" placeholder="Enter contents" id="contents" name="editordata"></textarea>
				</div>


		<div id="files">
			<div class="form-group" title="parent">
				<label for="file">File:</label> 
				<input type="file" class="form-control" name="file"> 
				<input type="button" class="btn btn-danger del" value="del">
			</div>
		</div>


		<input type="button" class="btn btn-success" value="ADD FILE" id="add">
		
		<button>Write</button>
		<!-- <input type="button" id="btn" value="test"> -->
	
	</form>
	
	<script type="text/javascript">

	var files = $("#files").html();
	var count = 0;
	var index = 0; //인덱스번호 사용하기
	$("#files").empty();
	
	

	/* 	$("#files").delegate(".del", "click", function(){
			alert("add");
		}); */
		
		
		$("#files").on("click", ".del", function() {
				count--;
				//$(this).closest("div").remove();
				//$(this).parent().attr("title");
				//$(this).parents("div").attr("class");
				$(this).parents(".form-group").remove();				
		});
		

		$("#add").click(function() {
			if (count < 5) {
				$("#files").append(files);
				count++;
			} else {
				alert("파일추가는 최대 5개까지 가능합니다.")
			}
		});
		
		
		// contents : summernote 불러오기
		$('#contents').summernote({
			placeholder: 'Enter contents',
			minHeight: 300,  
			maxHeight: null,
			height: 300,
			
			//contents에서 summernote로 등록한 이미지 받기
			// onImageUpload callback
			callbacks : {
				onImageUpload : function(files, editor) {
					uploadFile(files[0], this); //함수호출
				},//upload
				
				onMediaDelete : function(files, editor) {
					deleteFile(files[0],this);
				}//delete
				
			}//callback
		});//summernote
		
		function uploadFile(file, editor) {
			//매개변수받을 때 함수는 데이터타입 안쓴다.
			
			var formData = new FormData();
			formData.append('file', file);
			
			$.ajax({
				//데이터를 파라미터로 보낸다.
				data : formData,
				
				//name : value
				type : "POST",
				url : "./summerFile",
				enctype : "multipart/form-data",
				contentType : false,
				cache : false,
				processData : false,
				
				
				//응답을 받는다.
				//성공시
				success : function(data) {
					//console.log(data);
					
					data = data.trim();
					data = '../resources/upload/summer/'+data;
					$(editor).summernote('insertImage', data);		
				}
				//실패시
				/* error : function() {} */
			});
			//alert("hi");
		}//upload : fun
		
		
		function deleteFile(file, editor) {
			//console.log(file);
			var filename = $(file).attr("src");
			//console.log(filename);
			//console.log(filename.substring(filename.lastIndexOf("/")+1));
			filename = filename.substring(filename.lastIndexOf("/")+1);
			
			$.ajax({   
				type : "POST",
				url : "summerFileDelete",
				data : {
					file : filename
				},
				
				success : function(data) {
					console.log(data);
				}
			});
			
				
		}//delete : fun
		
		
		
		
		//contents의 값 받기
	/* 	$("#btn").click(function() {
			//alert($("#contents").val());
			alert($("#contents").summernote('code'));
		}); */
		
		
		//contents에 값 넣기
		//$("#contents").summernote('code','Hello');
		
		

	</script>
	

</body>
</html>