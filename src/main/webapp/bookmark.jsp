<%@ page import="com.public_wifi_info.pubilcwifiinfomation.service.WifiInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataSelect" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.service.Bookmark" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataInsert" %>
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
        String mgrNo = request.getParameter("addBookmarkButton");
        String groupName = request.getParameter("groupName");
        DataInsert dataInsert = new DataInsert();
        DataSelect dataSelect = new DataSelect();
        DataDelete dataDelete = new DataDelete();
        String bookmarkDeleteMgrNo = request.getParameter("bookmarkDeleteMrgNo");

        if (bookmarkDeleteMgrNo != null){
            dataDelete.bookmarkDelete(bookmarkDeleteMgrNo);
        }

        if (mgrNo !=null){
        WifiInfo wifiInfo = dataSelect.wifiInfoDetailSelect(mgrNo);
        dataInsert.bookmarkInsert(mgrNo, groupName, wifiInfo.name);
            System.out.println(mgrNo + " "+groupName);
        }
        ArrayList<Bookmark> bookmarks = dataSelect.bookmarkSelect();
    %>
    <h1>북마크 목록</h1>
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
                <th>북마크 이름</th>
                <th>와이파이명</th>
                <th>등록일자</th>
                <th>비고</th>
            </tr>
            </thead>
            <tbody>
                    <%
                    for (Bookmark bookmark: bookmarks){
                %>
            <tr>
                <td><%=bookmark.id%></td>
                <td><%=bookmark.groupName%></td>
                <td><a href="wifi-detail.jsp?mgrNo=<%=bookmark.mgrNo%>"><%=bookmark.wifiName%></a></td>
                <td><%=bookmark.registerDate%></td>
                <td class="info"><a href="bookmark-delete.jsp?id=<%=bookmark.id%>">삭제</a></td>
            </tr>
            <% } %>
            </tbody>
        </table>
</body>
</html>