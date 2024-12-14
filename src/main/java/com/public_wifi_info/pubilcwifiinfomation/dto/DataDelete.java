package com.public_wifi_info.pubilcwifiinfomation.dto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataDelete {

  public void historyDelete (int id) {
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

      String sql = " delete " +
              " from history " +
              " where id = ?; ";
      //스테이트먼트 객체 생성
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1,id);
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

  public void bookmarkDelete (String mgrNo) {
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

      String sql = " delete " +
              " from bookmark " +
              " where mgr_no = ?; ";
      //스테이트먼트 객체 생성
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1,mgrNo);
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

  public void bookmarkGroupDelete (int id) {
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

      String sql = " delete " +
              " from bookmark_group " +
              " where id = ?; ";
      //스테이트먼트 객체 생성
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1,id);
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

}
