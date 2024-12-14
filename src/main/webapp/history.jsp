<%@ page import="java.util.ArrayList" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataSelect" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.service.History" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataDelete" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="baseStyle.css">
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <%
        DataSelect dataSelect = new DataSelect();
        DataDelete dataDelete = new DataDelete();
        if (request.getParameter("deleteHistoryId") != null){
          int id = Integer.parseInt(request.getParameter("deleteHistoryId"));
          dataDelete.historyDelete(id);
        }
        ArrayList<History> histories = dataSelect.historySelect();
    %>
    <h1>위치 히스토리 목록</h1>
    <div>
        <p>
            <a href="home.jsp">홈</a>
            |
            <a href="history.jsp">위치 히스토리 목록</a>
            |
            <a href="load-wifi.jsp">Open API 와이파이 정보 가져오기</a>
            |
            <a href="bookmark.jsp">북마크보기</a>
            |
            <a href="bookmark-group.jsp">북마크 그룹 관리</a>
        </p>
    </div>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>조회일자</th>
                <th>비고</th>
            </tr>
            </thead>
            <tbody>
                <%for (History history: histories){%>
            <tr>
                <td><%=history.id%></td>
                <td><%=history.lat%></td>
                <td><%=history.lnt%></td>
                <td><%=history.searchDate%></td>
                <form action="history.jsp">
                <td class="info"><button type="submit" name="deleteHistoryId" value="<%=history.id%>">삭제</button></td>
                </form>
            </tr>
            <% } %>
            </tbody>
        </table>
</body>
</html>