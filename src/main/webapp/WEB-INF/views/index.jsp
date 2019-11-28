<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>Home</title>
	<c:import url="./layout/bootStrap.jsp" />
	<c:import url="./layout/summernote.jsp" />
</head>

<body>
	<c:import url="./layout/nav.jsp" />

	<div style="padding-left: 20px;">
		<h1>Hello world!</h1>
		<P>The time on the server is ${serverTime}.</P>
	</div>
	
	<button id="btn4">Movie</button>
	<button id="btn3">Movies</button>
	<button id="btn2">Exchange</button>
	<button id="btn">Get Json</button>
	
	<div id="movie"></div>
	
	<div id="index" >
	</div>
	
	<script type="text/javascript">
		$('#btn4').click(function() {
			$.ajax({
				type: "GET",
				url: "http://www.kobis.or.kr/kobisopenapi/webservice/rest/boxoffice/searchDailyBoxOfficeList.json",
				data: {
					key: "ff19a9e93f90f4687f3c084727cb65bc",
					targetDt: "20191126"
				},
				success: function(data) {
					$.each(data.boxOfficeResult.dailyBoxOfficeList, function(i, m) {
						//alert(data.boxOfficeResult.dailyBoxOfficeList[0].movieNm);
						$('#movie').append("<h3>"+m.movieNm+"</h3>");
					});
				}
			});
		});
	
		$('#btn3').click(function() {
			$.ajax({
				type: "GET",
				url: "https://yts.lt/api/v2/list_movies.json",
				data: {
					limit: 10
				},
				success:function(data){
					$.each(data.data.movies, function(i, m) {
						$('#movie').append("<h3>"+m.title_long+"</h3>");
						$('#movie').append('<img src="'+m.large_cover_image+'">');
					})
				}
			})
		});
	
		$('#btn2').click(function() {
			$.ajax({
				type: "GET",
				url: "https://api.manana.kr/exchange/price.json",
				data: {
					base:"USD",
					price:1,
					code:"KRW"
				},
				success:function(data){
					console.log(data.KRW);
				}
			});
			
			/* $.get("https://api.manana.kr/exchange/price/USD/1/KRW,USD,JPY.json", function(data) {
				alert(data.KRW);
				alert(data.USD);
				alert(data.JPY);
			}) */
		});
	
		$('#btn').click(function() {
			$.get("/getJson3", function(data) {
				//data = data.trim();
				//data = JSON.parse(data);
				//alert(typeof data); 데이터타입
				//alert(data.title);
				//alert(data.writer);
				//alert(data.contents);
				
				//alert(data[0].num);
				
				/* for(var i=0;i<data.length;i++){
					console.log(data[i].num);
				} */
				
				$.each(data, function(i, vo) { //인덱스번호, 꺼내서 받아온 것
					console.log(i);
					console.log(vo.num);
				});
				
			});
		});
		
		//$('#index').summernote();
		
	</script>

</body>
</html>

