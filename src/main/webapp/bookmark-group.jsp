<%@ page import="com.public_wifi_info.pubilcwifiinfomation.service.WifiInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataSelect" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataInsert" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.service.BookmarkGroup" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataDelete" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataUpdate" %>
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

        String bookmarkGroupName = request.getParameter("bookmarkGroupName");
        int no = 0;

        String bookmarkGroupNameEdit = request.getParameter("bookmarkGroupNameEdit");
        int noEdit = 0;
        int idEdit = 0;

        if(request.getParameter("noEdit") != null){
            noEdit= Integer.parseInt(request.getParameter("noEdit"));
            idEdit= Integer.parseInt(request.getParameter("idEdit"));
        }

        if(request.getParameter("no") != null){
            no= Integer.parseInt(request.getParameter("no"));
        }
        DataInsert dataInsert = new DataInsert();
        DataSelect dataSelect = new DataSelect();
        DataDelete dataDelete = new DataDelete();
        DataUpdate dataUpdate = new DataUpdate();
        if (request.getParameter("deleteId") != null){
            int bookmarkGroupDeleteId = Integer.parseInt(request.getParameter("deleteId"));
            dataDelete.bookmarkGroupDelete(bookmarkGroupDeleteId);
        }
        if (mgrNo != null){
            WifiInfo wifiInfo = dataSelect.wifiInfoDetailSelect(mgrNo);
            dataInsert.bookmarkInsert(mgrNo, groupName, wifiInfo.name);
        }
        if (bookmarkGroupName != null){
            dataInsert.bookmarkGroupInsert(bookmarkGroupName,no);
        }
        BookmarkGroup bookmarkGroup;
        if(bookmarkGroupNameEdit != null){
          dataUpdate.bookmarkGroupUpdate(bookmarkGroupNameEdit, noEdit, idEdit);
        }

        ArrayList<BookmarkGroup> bookmarkGroups = dataSelect.bookmarkGroupSelect();
    %>
    <h1>북마크 그룹 목록</h1>
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
    <form action="bookmark-group-add.jsp">
        <button>북마크 그룹 이름 추가</button>
    </form>
        <table>
            <thead>
            <tr>
                <th>ID</th>
                <th>북마크 이름</th>
                <th>순서</th>
                <th>등록일자</th>
                <th>수정일자</th>
                <th>비고</th>
            </tr>
            </thead>
            <tbody>
                <%
                    for (BookmarkGroup bookmark: bookmarkGroups){
                %>
            <tr>
                <td><%=bookmark.id%></td>
                <td><%=bookmark.groupName%></td>
                <td><%=bookmark.no%></td>
                <td><%=bookmark.registerDate%></td>
                <%if(bookmark.modifyDate == null){%>
                <td></td>
                <%}else{%>
                    <td><%=bookmark.modifyDate%></td>
                <%}%>

                <td class="info"><a href="bookmark-group-edit.jsp?id=<%=bookmark.id%>">수정</a>|<a href="bookmark-group-delete.jsp?id=<%=bookmark.id%>">삭제</a></td>
            </tr>
            <% } %>
            </tbody>
        </table>
</body>
</html>