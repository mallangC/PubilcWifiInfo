package com.public_wifi_info.pubilcwifiinfomation.dto;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.sql.*;

public class DataInsert {
  //완
  public void apiDataInsert() {
    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    //드라이버 로드
    try {
      Class.forName("org.mariadb.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      //커넥션 객체 생성
      connection = DriverManager.getConnection(url, dbUserId,dbPassword);

      int pageStart = 1;
      int pageEnd = 1000;
      int num = 1;
      boolean check = false;

      while (true) {
        String apiUrl = String.format("http://openapi.seoul.go.kr:8088/68436c58416b6b33383574507a7649/xml/TbPublicWifiInfo/%d/%d/", pageStart, pageEnd);

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(apiUrl);

        doc.getDocumentElement().normalize();
        System.out.println("Root element : "
                + doc.getDocumentElement().getNodeName());

        NodeList nList = doc.getElementsByTagName("row");
//        System.out.println("파싱할 리스트 수 : " + nList.getLength());
        if (nList.getLength() == 0){
          check = true;
        }
        for (int i = 0; i < nList.getLength(); i++) {
          Node nNode = nList.item(i);

          if (nNode.getNodeType() == Node.ELEMENT_NODE) {
            Element element = (Element) nNode;

            String sql = " insert ignore into public_wifi_info (mgr_no, region, name, address, " +
                    " address_detail, installation_floor, installation_type,  " +
                    " installation_institution, service, net_type, installation_year,  " +
                    " inout_door, connection_environment, lat, lnt, work_date) " +
                    " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?); ";
            //스테이트먼트 객체 생성
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, getTagValue("X_SWIFI_MGR_NO", element));
            preparedStatement.setString(2, getTagValue("X_SWIFI_WRDOFC", element));
            preparedStatement.setString(3, getTagValue("X_SWIFI_MAIN_NM", element));
            preparedStatement.setString(4, getTagValue("X_SWIFI_ADRES1", element));
            preparedStatement.setString(5, getTagValue("X_SWIFI_ADRES2", element));
            preparedStatement.setString(6, getTagValue("X_SWIFI_INSTL_FLOOR", element));
            preparedStatement.setString(7, getTagValue("X_SWIFI_INSTL_TY", element));
            preparedStatement.setString(8, getTagValue("X_SWIFI_INSTL_MBY", element));
            preparedStatement.setString(9, getTagValue("X_SWIFI_SVC_SE", element));
            preparedStatement.setString(10, getTagValue("X_SWIFI_CMCWR", element));
            preparedStatement.setInt(11, Integer.parseInt(getTagValue("X_SWIFI_CNSTC_YEAR", element)));
            preparedStatement.setString(12, getTagValue("X_SWIFI_INOUT_DOOR", element));
            preparedStatement.setString(13, getTagValue("X_SWIFI_REMARS3", element));
            preparedStatement.setDouble(14, Double.parseDouble(getTagValue("LAT", element)));
            preparedStatement.setDouble(15, Double.parseDouble(getTagValue("LNT", element)));
            String s = getTagValue("WORK_DTTM", element);
            Timestamp ts = Timestamp.valueOf(s);
            preparedStatement.setTimestamp(16, ts);
            //쿼리 실행
            int affected = 0;
            try {
              affected = preparedStatement.executeUpdate();
            } catch (SQLException e) {
              e.printStackTrace();
            }

            if (affected > 0) {
              System.out.println("저장 성공");
            } else {
              System.out.println("저장 실패");
            }
            System.out.printf("=======%02d=======\n", num);
          }
          num++;
        }
        pageStart += 1000;
        pageEnd += 1000;
        if (check){
          break;
        }
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }finally {
      //객체 연결 해제
      try {
        if (preparedStatement != null && !preparedStatement.isClosed()) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (connection != null && !connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
  //완
  public void historyInsert (double lat, double lnt) {
    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    //드라이버 로드
    try {
      Class.forName("org.mariadb.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      //커넥션 객체 생성
      connection = DriverManager.getConnection(url, dbUserId,dbPassword);

      String sql = " insert into history(lat,lnt,search_date) " +
              " values (?,?,now()); ";
      //스테이트먼트 객체 생성
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setDouble(1,lat);
      preparedStatement.setDouble(2,lnt);
      //쿼리 실행
      int affected = 0;
      try{
        affected = preparedStatement.executeUpdate();
      }catch (SQLException e){
        e.printStackTrace();
      }

      if (affected > 0){
        System.out.println("저장 성공");
      }else {
        System.out.println("저장 실패");
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }finally {
      //객체 연결 해제
      try {
        if (preparedStatement != null && !preparedStatement.isClosed()) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (connection != null && !connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }


  }


  public void bookmarkInsert (String mgrNo, String groupName,String wifiName) {
    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    //드라이버 로드
    try {
      Class.forName("org.mariadb.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      //커넥션 객체 생성
      connection = DriverManager.getConnection(url, dbUserId,dbPassword);

      String sql = " insert ignore into bookmark(mgr_no, group_name, wifi_name, register_date) " +
              " values (?, ?, ?, now()); ";
      //스테이트먼트 객체 생성
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1,mgrNo);
      preparedStatement.setString(2,groupName);
      preparedStatement.setString(3,wifiName);
      //쿼리 실행
      int affected = 0;
      try{
        affected = preparedStatement.executeUpdate();
      }catch (SQLException e){
        e.printStackTrace();
      }

      if (affected > 0){
        System.out.println("저장 성공");
      }else {
        System.out.println("저장 실패");
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }finally {
      //객체 연결 해제
      try {
        if (preparedStatement != null && !preparedStatement.isClosed()) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (connection != null && !connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }


  public void bookmarkGroupInsert (String groupName, int no) {
    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    //드라이버 로드
    try {
      Class.forName("org.mariadb.jdbc.Driver");
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;

    try {
      //커넥션 객체 생성
      connection = DriverManager.getConnection(url, dbUserId,dbPassword);

      String sql = " insert ignore into bookmark_group(group_name, no, register_date) " +
              " values (?,?,now()) ";
      //스테이트먼트 객체 생성
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1,groupName);
      preparedStatement.setInt(2,no);
      //쿼리 실행
      int affected = 0;
      try{
        affected = preparedStatement.executeUpdate();
      }catch (SQLException e){
        e.printStackTrace();
      }

      if (affected > 0){
        System.out.println("저장 성공");
      }else {
        System.out.println("저장 실패");
      }

    } catch (Exception e) {
      throw new RuntimeException(e);
    }finally {
      //객체 연결 해제
      try {
        if (preparedStatement != null && !preparedStatement.isClosed()) {
          preparedStatement.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      try {
        if (connection != null && !connection.isClosed()) {
          connection.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }


  }


  public String getTagValue(String tag, Element eElement){
    NodeList nodeList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
    Node nValue = (Node) nodeList.item(0);
    if (nValue == null){
      return null;
    }
    return nValue.getNodeValue();
  }
}
