<%@page import="util.crawler.MovieCrawler"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="info.*"%>
<%@ page import="movie.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String title = request.getParameter("title");
	System.out.println(title);
	MovieService movieService = new MovieService();
	InfoDAO infoDao = new InfoDAO();
	MovieVO movieVO = movieService.getMovie(title);
	InfoVO infoVO = infoDao.getInfo(title);
	
	MovieCrawler crawler = new MovieCrawler(title);
	
	System.out.println(movieVO.getIframeVidioLink());
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>::영화 상세 정보::</title>
	<style type="text/css">
		*{
			margin: 0 auto;
		}
		body{
			background: rgb(253, 252, 240);
		}
		header{
			width: 960px;
			height: 163px;
		}
		#container{
			width: 960px;
			padding-top: 70px;
		}
		#poster{
			float: left;
		}
		#infoBox{
			width: 680px;
			float: right;
			padding-left: 50px;
		}
		#infoBox > h1{
			font-size: 50px;
		}
		#infoBox > h3{
			font-size: 20px;
		}
		#ratingBox{
			float: right;
			width: 150px;
			margin-top: -88px;
			margin-right: 150px;
		}
		#ratingBox > #rating > img {
			width: 50px;
			height: 50px;
		}
		#ratingBox > #rating {
			float: right;
		}
		#ratingBox > #showing {
			width: 70px;
			height: 35px;
			float: left;
			background-color: #6666DD;
		}
		#showing > p {
			text-align: center;
			font-size: 18px;
			color: #FFF;
			margin-top: 4px;
		}
		#space1{
			height: 380px;
		}
		#graph {
		
		}
		#vidio{
			width: 960px;
			height: 720px;
		}
	</style>
</head>
<body>
	<header>
		<img src="../resource/header.png">
	</header>
	<hr><br><hr>
	<div id="container">
		<p></p>
		<img id="poster" src=<%=movieVO.getPosterLink()%>>
		<div id="infoBox">
			<h1><%=infoVO.getTitle() %></h1>
			<h3><%=infoVO.getEnglishTitle() %></h3>
			<div id="ratingBox">
				<div id="showing">
					<%
					if(movieVO.isShowing()){
						%><p>상영중</p><%					
					}
					%>
				</div>
				<div id="rating">
					<img src="../resource/star.png">
					<h2><%=movieVO.getRating()%></h2>
				</div>
			</div>
			<br>
			<%=infoVO.getYear() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=infoVO.getCountry() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=infoVO.getGrade() %>
			<br><br>
			<%=infoVO.getGenre() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=movieVO.getSpectator() %>
			<br><br>
			개봉일 : <%=infoVO.getReleaseDate() %>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;누적 관객 : <%=movieVO.getSpectator() %>
			<br><br>
			감독 : <%=infoVO.getDirector() %>
			<br><br>
			출연 : <%=infoVO.getPerformer() %>
		</div>
		<div id="space1"></div>
		<div id="storyBox">
			<%=infoVO.getStoryHtml()%>
		</div>
		<br><br>
		<div id="graph" style="position: relative; width: 802px; height: 260px; overflow: hidden; margin: 0 auto;">
			<div style="position: absolute; left: -8px; top: -1174px;">
				<iframe src="<%=crawler.getGraphLink() %>" width="1000px" height="2000px"
					frameborder="0" scrolling="no"></iframe>
			</div>
		</div>
		<br><br><br>
		<iframe id="vidio" src="<%=movieVO.getIframeVidioLink() %>"></iframe>
	</div>
</body>
</html>