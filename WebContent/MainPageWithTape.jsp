<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="tw.STSProject.controller.TapeReadingController"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" type="text/css" href="css/MainPageWithTape.css">
<link
	href="https://fonts.googleapis.com/css?family=Noto+Sans+TC&display=swap"
	rel="stylesheet">
<title>台股練兵系統</title>
</head>
<body>
		<div class="Sidebar">

		<h2>你的自選股</h2>
		<div class="fStockTab">
			<table class="fStock">
				<tr>
					<th>股票代號</th>
					<th>公司名稱</th>
					<th>漲跌</th>
					<th>成交價</th>
					<th>最高價</th>
					<th>最低價</th>
				</tr>
				<c:forEach var="fs" items="${favoriteStockInfo}" varStatus="status">
					<c:if test="${status.count%6==1}">
						<tr>
					</c:if>
					<th>${fs}</th>
					<c:if test="${status.count%6==0}">
						</tr>
					</c:if>
				</c:forEach>
			</table>
		</div>
		<br>
		<form method="post" name="Tform">
			<input type="submit" name="submit" value="停止看盤!"
				class="btn btn-outline-primary"
				onclick="document.Tform.action='MainPageTableController'">
		</form>

	</div>


	<div class="Body">

		<div class="inventory">
			<h3>你的庫存</h3>
			<div class="Fin">
				<table class="fStock">
					<tr>
						<th>股票代號</th>
						<th>公司名稱</th>
						<th>庫存量</th>
						<th>現價</th>
						<th>市值</th>
					</tr>
					<c:forEach var="id" items="${InventoryData}" varStatus="status">
						<c:if test="${status.count%5==1}">
							<tr>
						</c:if>
						<th>${id}</th>
						<c:if test="${status.count%5==0}">
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</div>

		<div class="transactionRecord">
			<h3>你的交易紀錄</h3>
			<div class="Ftr">
				<table class="fStock">
					<tr>
						<th>股票代號</th>
						<th>公司名稱</th>
						<th>買或賣</th>
						<th>交易量</th>
						<th>交易價</th>
						<th>交易日期</th>
					</tr>
					<c:forEach var="trd" items="${transactionRecordData}"
						varStatus="status">
						<c:if test="${status.count%6==1}">
							<tr>
						</c:if>
						<th>${trd}</th>
						<c:if test="${status.count%6==0}">
							</tr>
						</c:if>
					</c:forEach>
				</table>
			</div>
		</div>
		<div class="total">你的總金額: ${userMoney}模擬幣</div>
		<h3 class="newsTitle">今日財經新聞</h3>
		<div id="newss" class="news"></div>

	</div>
	<script>
		var xhttp = new XMLHttpRequest();
		xhttp.onreadystatechange = function() {
			if (xhttp.readyState == 4 && xhttp.status == 200) {
				var xmlDoc = xhttp.responseXML;
				var txt = "";
				var titles = xmlDoc.getElementsByTagName("title");
				var links = xmlDoc.getElementsByTagName("link");
				var pubDates = xmlDoc.getElementsByTagName("pubDate");
				for (let i = 1; i < titles.length; i++) {

					var GMT = new Date(pubDates[i].firstChild.nodeValue);
					txt += GMTToStr(GMT)
							+ "  <a href=\"" + links[i].firstChild.nodeValue+"\">"
							+ titles[i].firstChild.nodeValue + "</a><br>";
				}
				document.getElementById("newss").innerHTML = txt;
			}
		}
		xhttp.open("POST", "RssNewsController", true);
		xhttp.send();

		GMTToStr = function(time) {
			let date = new Date(time)
			let Str = date.getFullYear() + '-' + (date.getMonth() + 1) + '-'
					+ date.getDate() + ' ' + date.getHours() + ':'
					+ date.getMinutes() + ':' + date.getSeconds()
			return Str
		}
	</script>

</body>
</html>