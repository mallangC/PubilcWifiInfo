<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataSelect" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.service.WifiInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="baseStyle.css">
<head>
    <title>와이파이 정보 구하기</title>
    <%
        String id = request.getParameter("mgrNo");
        DataSelect dataSelect = new DataSelect();
        WifiInfo wifiInfo = dataSelect.wifiInfoDetailSelect(id);
        ArrayList<String> groupNames = dataSelect.bookmarkGroupNameSelect();
        if (request.getParameter("dist") != null) {
            wifiInfo.distance = Double.parseDouble(request.getParameter("dist"));
        }
        %>
</head>
<body>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
    <h1>와이파이 정보 구하기</h1>
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
<form action="bookmark.jsp">
    <select name="groupName">
        <option>북마크 그룹 선택</option>
        <%for(String s : groupNames){%>
        <option value="<%=s%>"><%=s%></option>
        <%}%>
    </select>
    <button type="submit" name="addBookmarkButton" value="<%=wifiInfo.mgrNo%>" onclick="btn()">북마크 추가하기</button>
</form>
    <script>
        function btn(){
            alert("북마크 정보를 추가했습니다.")
        }
    </script>

        <table>
            <colgroup>
                <col style="width: 20%;"/>
                <col style="width: 80%;"/>
            </colgroup>
            <thead>
            <tr>
                <th>거리(Km)</th>
                <td><%=String.format("%.3f",wifiInfo.distance)%></td>
            </tr>
            <tr>
                <th>관리번호</th>
                <td><%=wifiInfo.mgrNo%></td>
            </tr>
            <tr>
                <th>자치구</th>
                <td><%=wifiInfo.region%>
            </tr>
            <tr>
                <th>와이파이명</th>
                <td><%=wifiInfo.name%></td>
            </tr>
            <tr>
                <th>도로명주소</th>
                <td><%=wifiInfo.address%></td>
            </tr>
            <tr>
                <th>상세주소</th>
                <td><%=wifiInfo.addressDetail%></td>
            </tr>
            <tr>
                <th>설치위치(층)</th>
                <%if (wifiInfo.installationFloor == null){
                  wifiInfo.installationFloor = "";
                }%>
                <td><%=wifiInfo.installationFloor%></td>
            </tr>
            <tr>
                <th>설치유형</th>
                <td><%=wifiInfo.installationType%></td>
            </tr>
            <tr>
                <th>설치기관</th>
                <td><%=wifiInfo.installationInstitution%></td>
            </tr>
            <tr>
                <th>서비스구분</th>
                <td><%=wifiInfo.service%></td>
            </tr>
            <tr>
                <th>망종류</th>
                <td><%=wifiInfo.netType%></td>
            </tr>
            <tr>
                <th>설치년도</th>
                <td><%=wifiInfo.installationYear%></td>
            </tr>
            <tr>
                <th>실내외구분</th>
                <td><%=wifiInfo.inoutDoor%></td>
            </tr>
            <tr>
                <th>WIFI접속환경</th>
                <%if (wifiInfo.connectionEnvironment == null){
                    wifiInfo.connectionEnvironment = "";
                }%>
                <td><%=wifiInfo.connectionEnvironment%></td>
            </tr>
            <tr>
                <th>X좌표</th>
                <td><%=wifiInfo.lat%></td>
            </tr>
            <tr>
                <th>Y좌표</th>
                <td><%=wifiInfo.lnt%></td>
            </tr>
            <tr>
                <th>작업일자</th>
                <td><%=wifiInfo.workDate%></td>
            </tr>
            <tr>
            </tr>
            </thead>
        </table>
</body>
</html>