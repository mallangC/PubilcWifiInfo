<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataInsert" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataSelect" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
    <body>
        <%
            DataInsert dataInsert = new DataInsert();
            DataSelect dataSelect = new DataSelect();
            dataInsert.apiDataInsert();
        %>

    <h1 id="wifiNumber">
        <%=dataSelect.wifiInfoSelectCount()%>개의 WIFI 정보를 정상적으로 저장하였습니다.
    </h1>
    <a href="home.jsp">홈 으로 가기</a>
    </body>
</html>