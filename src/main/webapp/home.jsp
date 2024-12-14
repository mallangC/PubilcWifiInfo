<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="baseStyle.css">
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
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
            <form action="home-search.jsp">
                <label for="lat">LAT : </label><input type="text" name="lat" id="lat" value="" placeholder="0.0">
                <label for="lnt">LNT : </label><input type="text" name="lnt" id="lnt" value="" placeholder="0.0">
            <button type="button" onclick="getLocation()">내 위치 가져오기</button>
            <button type="submit" >근처 WIFI 정보 보기</button>
            </form>
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
                <td colspan="17" class="info">
                    위치 정보를 입력한 후에 조회해 주세요
                </td>
            </tbody>
        </table>
</body>
</html>