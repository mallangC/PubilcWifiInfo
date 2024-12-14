<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" href="baseStyle.css">
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <h1>북마크 그룹 추가</h1>
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
        <colgroup>
            <col  style="width: 20%"/>
            <col  style="width: 80%"/>
        </colgroup>
            <thead>
            <form action="bookmark-group.jsp">
            <tr>
                <th>북마크 이름</th>
                <td><input type="text" name="bookmarkGroupName"></td>
            </tr>
            <tr>
                <th>순서</th>
                <td><input type="text" name="no"></td>
            </tr>
            <tr>
                <td colspan="2" class="info">
                    <a href="bookmark-group.jsp">돌아가기</a> |
                        <input type="submit" value="추가" onclick="btn()">
                    <script>
                        function btn(){
                            alert("북마크 그룹이 추가되었습니다.")
                        }
                    </script>

                </td>
            </tr>
            </form>
            </thead>
        </table>
</body>
</html>