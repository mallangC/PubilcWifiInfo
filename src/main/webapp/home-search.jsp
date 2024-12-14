<%@ page import="com.public_wifi_info.pubilcwifiinfomation.service.WifiInfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataSelect" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DataInsert" %>
<%@ page import="com.public_wifi_info.pubilcwifiinfomation.dto.DistanceCheck" %>
<%@ page import="java.util.Collections" %>
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
        DataInsert dataInsert = new DataInsert();
        double lat = 0;
        double lnt = 0;

        ArrayList<WifiInfo> wifiInfos;
        if (request.getParameter("lat") != null) {
          lat = Double.parseDouble(request.getParameter("lat"));
          lnt = Double.parseDouble(request.getParameter("lnt"));
          dataInsert.historyInsert(lat, lnt);
        }
        wifiInfos = dataSelect.wifiInfoSelect();
        DistanceCheck distanceCheck = new DistanceCheck();

        for (WifiInfo w:wifiInfos){
            w.distance = distanceCheck.getDistance(lat,lnt,w.lat,w.lnt);
        }

        Collections.sort(wifiInfos);
        System.out.println(wifiInfos.getFirst().distance);
    %>
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
    </div><p>
    <label for="lat">LAT : </label><input type="text" name="lat" id="lat" value="<%=lat%>" placeholder="0.0">
    <label for="lnt">LNT : </label><input type="text" name="lnt" id="lnt" value="<%=lnt%>" placeholder="0.0">
            <button type="button" onclick="getLocation()">내 위치 가져오기</button>
            <button type="submit" value="가져오기">근처 WIFI 정보 보기</button>
        <script>
            function getLocation() {
                const lat = document.getElementById("lat");
                const lnt = document.getElementById("lnt")
                // Geolocation API 지원 여부 확인
                if ("geolocation" in navigator) {
                    navigator.geolocation.getCurrentPosition(
                        (position) => {
                            const { latitude, longitude } = position.coords;
                            lat.value = latitude;
                            lnt.value = longitude;
                        }
                    );
                }
            }
        </script>
        </p>
        <table>
            <thead>
            <tr>
                <th>거리(Km)</th>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류</th>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
            </thead>
            <tbody>
                    <%
                    for (int i = 0; i<20; i++){
                %>
            <tr>
                <td><%=String.format("%.3f",wifiInfos.get(i).distance)%></td>
                <td><%=wifiInfos.get(i).mgrNo%></td>
                <td><%=wifiInfos.get(i).region%></td>
                <td>
                    <a href="wifi-detail.jsp?mgrNo=<%=wifiInfos.get(i).mgrNo%>&dist=<%=wifiInfos.get(i).distance%>"><%=wifiInfos.get(i).name%></a>
                </td>
                <td><%=wifiInfos.get(i).address%></td>
                <td><%=wifiInfos.get(i).addressDetail%></td>
                <td><%if(wifiInfos.get(i).installationFloor == null){
                    wifiInfos.get(i).installationFloor = "";
                }%>
                    <%=wifiInfos.get(i).installationFloor%>
                </td>
                <td><%=wifiInfos.get(i).installationType%></td>
                <td><%=wifiInfos.get(i).installationInstitution%></td>
                <td><%=wifiInfos.get(i).service%></td>
                <td><%=wifiInfos.get(i).netType%></td>
                <td><%=wifiInfos.get(i).installationYear%></td>
                <td><%=wifiInfos.get(i).inoutDoor%></td>
                <td><%if(wifiInfos.get(i).connectionEnvironment == null){
                    wifiInfos.get(i).connectionEnvironment = "";
                }%>
                    <%=wifiInfos.get(i).connectionEnvironment%>
                </td>
                <td><%=wifiInfos.get(i).lat%></td>
                <td><%=wifiInfos.get(i).lnt%></td>
                <td><%=wifiInfos.get(i).workDate%></td>
            </tr>
            <% } %>
            </tbody>
        </table>

</body>
</html>