
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member Join</title>
<c:import url="../layout/bootStrap.jsp" />
</head>
<body>
<c:import url="../layout/nav.jsp" />

	<div class="container">
		<h2>Member Join</h2>

		<form action="./memberJoin" id="form" enctype="multipart/form-data" method="post">

			<div class="form-group">
				<label for="id">Id:</label> 
				<input type="text" class="form-control" id="id" name="id">
				<div id="checkId" style="padding-top: 10px; "></div>
			
			</div>

			<div class="form-group">
				<label for="pw">Password:</label> 
				<input type="password" class="form-control" id="pw" name="pw">
			</div>

			<div class="form-group">
				<label for="pw">Password 확인:</label> 
				<input type="password" class="form-control" id="pw2" name="pw2">
				<div id="checkPw" style="padding-top: 10px; "></div>
			</div>

			<div class="form-group">
				<label for="name">Name:</label> 
				<input type="text" class="form-control commit" id="name" name="name">
			</div>

			<div class="form-group">
				<label for="phone">Phone:</label> 
				<input type="number" class="form-control commit" id="phone" name="phone">
			</div>


			<div class="form-group">
				<label for="email">Email:</label> 
				<input type="email" class="form-control commit" id="email" name="email">
				<div id="checkEmail" style="padding-top: 10px; "></div>
			</div>

			<div class="form-group">
				<label for="birth">Birth:</label> 
				<input type="date" class="form-control commit" id="birth" name="birth">
			</div>

			<div class="form-group">
				<label for="gender">Gender: </label> 
				
				<label class="radio-inline">
					<input type="radio" name="gender" checked value="F">F
				</label> 
				
				<label class="radio-inline"> 
					<input type="radio" name="gender" value="M">M
				</label>
			</div>


			<div class="form-group">
				<label for="file">File:</label> 
				<input type="file" class="form-control" id="file" name="file">
			</div>
			
			

			<!-- <button class="btn btn-default">JOIN</button> -->
			<input type="button" class="btn btn-default" value="회원가입" id="join">

		</form>
	</div>


	<script type="text/javascript">
		var idCheck = false;
		//false면 중복된 아이디 가지거나 중복검사를 하지 않았다.
		//true면 중복되지 않은 아이디를 가진다.

		$("#join").click(function() {
			//if(idCheck){
				$("#form").submit();
			//}
			
		});

		//

		$("#id").blur(function() {
			check();
		});

		$("#id").change(function() {
			$("#checkId").html("");
			check();
		});

		function check() {
			var id = $('#id').val();
			$.get("./memberIdCheck?id=" + id, function(data) {
				/*  $("#checkId").html(data);  */

				if (data == 1) {
					/* 중복 가능 */
					/* $("#checkId").css("color","green"); */

					if (id == "") {
						alert("아이디를 입력하세요")
					} else {
						$("#checkId").prop("class", "text-success");
						$("#checkId").html("사용가능한 아이디입니다.");
						idCheck = true;
					}
				} else {
					/* 중복 불가능 */
					$('#id').val("");
					/* $("#checkId").css("color","red"); */
					$("#checkId").prop("class", "text-danger");
					$("#checkId").html("사용불가능한 아이디입니다.");
					$('#id').focus();
					idCheck = false;
				}
			});
		}

		//모든 칸 입력하기
		/* $('#pw').blur(function() {
			if($('#pw').val() == ""){
				alert("비밀번호를 입력하세요.");
			};
		});
		
		$('#pw').change(function() {
			if($('#pw').val() == ""){
				alert("비밀번호를 입력하세요.");
			};
		});
		
		$('#pw2').blur(function() {
			if($('#pw2').val() == ""){
				alert("비밀번호를 확인하세요.");
			};
		});
		
		$('#pw2').change(function() {
			if($('#pw2').val() == ""){
				alert("비밀번호를 확인하세요.");
			};
		});
		
		$('#name').blur(function() {
			if($('#name').val() == ""){
				alert("이름을 입력하세요.");
			};
		});
		
		$('#name').change(function() {
			if($('#name').val() == ""){
				alert("이름을 입력하세요.");
			};
		});
		
		$('#phone').blur(function() {
			if($('#phone').val() == ""){
				alert("휴대폰번호를 입력하세요.");
			};
		});
		
		$('#phone').change(function() {
			if($('#phone').val() == ""){
				alert("휴대폰번호를 입력하세요.");
			};
		});

		$('#email').blur(function() {
			if($('#email').val() == ""){
				alert("이메일을 입력하세요.");
			};
		});
		
		$('#email').change(function() {
			if($('#email').val() == ""){
				alert("이메일을 입력하세요.");
			};
		});
		
		$('#birth').blur(function() {
			if($('#birth').val() == ""){
				alert("생일을 입력하세요.");
			};
		});
		
		$('#birth').change(function() {
			if($('#birth').val() == ""){
				alert("생일을 입력하세요.");
			};
		}); */
		
		$(".commit").blur(function() {
			if($(".commit").val() == ""){
				alert("모든 항목을 입력하세요");
			}
		});
		
		$(".commit").change(function() {
			if($(".commit").val() == ""){
				alert("모든 항목을 입력하세요");
			}
		});
		

		
		//비밀번호 확인
		$('#pw2').blur(function() {
			var p1 = $('#pw').val();
			var p2 = $('#pw2').val();
			if(p1 == p2){
				if(p1 != "" && p2 != ""){
				$("#checkPw").prop("class", "text-success");
				$("#checkPw").html("사용가능한 비밀번호입니다.");					
				} else {
					alert("비밀번호를 입력하세요")
				}
			} else {
				$("#checkPw").prop("class", "text-danger");
				$("#checkPw").html("사용불가능한 비밀번호입니다.");
			}
		});
		
		$('#pw').change(function() {
			$('#pw2').val("");
			$("#checkPw").html("");
		});
		
		
		//이메일 확인 (=아이디확인)
		$("#email").blur(function() {
			checke();
		});

		$("#email").change(function() {
			$("#checkEmail").html("");
			checke();
		});

		function checke() {
			var email = $('#email').val();
			$.get("./memberEmailCheck?email=" + email, function(data) {
				/*  $("#checkId").html(data);  */

				if (data == 1) {

					if (email == "") {
						alert("이메일을 입력하세요")
					} else {
						$("#checkEmail").prop("class", "text-success");
						$("#checkEmail").html("사용가능한 이메일입니다.");
					}
				} else {
					/* 중복 불가능 */
					$('#email').val("");
					$("#checkEmail").prop("class", "text-danger");
					$("#checkEmail").html("사용불가능한 이메일입니다.");

				}
			});
		}
		
		
	</script>


</body>
</html>