<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Insert title here</title>
<style type="text/css">
	.main{
		width: 500px;
		height: 400px;
		position: fixed;
	}
	.content{
		padding: 30px;
		text-align: center;
		font-size: 16px;
		line-height: 30px;
	}
	.btn{
		height: 25px;
		width: 120px;
		cursot: pointer;
		display: block;
		text-align: center;
		font-size: 16px;
		font-weight: bold;
		border-radius: 3px;
		background: #f00;
		color: #fff;
		position: absolute;
		right: 50px;
		bottom: 280px;
		text-decoration: none;
	}
	.list{
		width: 150px;
		right: 210px;
		bottom: 280px;
	}
	
	.register{
		width: 150px;
		right: 50px;
		bottom: 280px;
	}
</style>
</head>
<body>

	<div class="main">	
		<div class="content">welcome! 처음으로 만든 풀스텍 웹 페이지입니다!</div>
		<a href="/board/list" class="btn list">글목록 조회로 Go!</a>
		<a href="/board/register" class="btn register">글등록으로 Go!</a>
	</div>
	
	<!-- 
	<h2>GET방식으로 데이터 전송</h2>
	<form action="sample/ex01">
		<p> 이름 : <input type="text" name="name"> </p>
		<p> 나이 : <input type="text" name="age"> </p>
		
		<input type="submit" value="전송!">		
		<input type="reset" value="취소!">		
	</form>
	 -->
	 
	 <!-- 
	 <form action="/board/register" method="post">
		제목 : <input type="text" name="title" /> <br>
		내용 : <input type="text" name="content" /> <br>
		작성자 : <input type="text" name="writer" /> <br>
		
		<input type="submit" value="글등록" />
		<input type="submit" value="취소" />
	</form>
	 -->
</body>
</html>