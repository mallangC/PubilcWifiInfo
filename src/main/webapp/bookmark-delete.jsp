<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataSelect" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.service.Bookmark" %>
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
        int id = Integer.parseInt(request.getParameter("id"));
        Bookmark bookmark = dataSelect.bookmarkSelect(id);
    %>
    <h1>북마크 삭제</h1>
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
    <p>북마크를 삭제하시겠습니까?</p>
        <table>
            <colgroup>
                <col  style="width: 20%"/>
                <col  style="width: 80%"/>
            </colgroup>
            <thead>
            <form action="bookmark.jsp" method="get">
            <tr>
                <th>북마크 이름</th>
                <td><%=bookmark.groupName%></td>
            </tr>
            <tr>
                <th>와이파이명</th>
                <td><%=bookmark.wifiName%></td>
            </tr>
            <tr>
                <th>등록일자</th>
                <td><%=bookmark.registerDate%></td>
            </tr>
            <tr>
                <td colspan="2" class="info">
                    <a href="bookmark.jsp">돌아가기</a> |
                    <button type="submit" name="bookmarkDeleteMrgNo" value="<%=bookmark.mgrNo%>" onclick="btn()">삭제</button>
                    <script>
                        function btn(){
                            alert("북마크 그룹이 삭제되었습니다.")
                        }
                    </script>
                </td>
            </tr>
            </form>
            </thead>
        </table>
</body>
</html>