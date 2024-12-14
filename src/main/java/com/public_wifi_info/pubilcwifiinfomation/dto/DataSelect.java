package com.public_wifi_info.pubilcwifiinfomation.dto;

import com.public_wifi_info.pubilcwifiinfomation.service.Bookmark;
import com.public_wifi_info.pubilcwifiinfomation.service.BookmarkGroup;
import com.public_wifi_info.pubilcwifiinfomation.service.History;
import com.public_wifi_info.pubilcwifiinfomation.service.WifiInfo;

import java.sql.*;
import java.util.ArrayList;

public class DataSelect {

  public int wifiInfoSelectCount(){

    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    try {
      Class.forName("org.mariadb.jdbc.Driver");
    }catch (ClassNotFoundException e){
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
    int count = 0;

    try{
      connection = DriverManager.getConnection(url, dbUserId, dbPassword);
      String sql = " select count(*) " +
              " from public_wifi_info; ";
      preparedStatement = connection.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();
      resultSet.next();
      count = resultSet.getInt(1);

    }catch (SQLException e){
      throw new RuntimeException(e);
    }finally {
      try {
        if (resultSet != null && !resultSet.isClosed()) {
          resultSet.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
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
    return count;
  }


  public WifiInfo wifiInfoDetailSelect(String mgrNo){

    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    WifiInfo wifiInfo = new WifiInfo();

    try {
      Class.forName("org.mariadb.jdbc.Driver");
    }catch (ClassNotFoundException e){
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try{
      connection = DriverManager.getConnection(url, dbUserId, dbPassword);
      String sql = " select mgr_no, region, name, address, address_detail, " +
              " installation_floor, installation_type, installation_institution, " +
              " service, net_type, installation_year, inout_door, connection_environment, " +
              " lat, lnt, work_date " +
              " from public_wifi_info " +
              " where mgr_no = ? " ;
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setString(1, mgrNo);
      resultSet = preparedStatement.executeQuery();
      resultSet.next();

        wifiInfo.mgrNo = resultSet.getString("mgr_no");
        wifiInfo.region = resultSet.getString("region");
        wifiInfo.name = resultSet.getString("name");
        wifiInfo.address = resultSet.getString("address");
        wifiInfo.addressDetail = resultSet.getString("address_detail");
        wifiInfo.installationFloor = resultSet.getString("installation_floor");
        wifiInfo.installationType = resultSet.getString("installation_type");
        wifiInfo.installationInstitution = resultSet.getString("installation_institution");
        wifiInfo.service = resultSet.getString("service");
        wifiInfo.netType = resultSet.getString("net_type");
        wifiInfo.installationYear = resultSet.getInt("installation_year");
        wifiInfo.inoutDoor = resultSet.getString("inout_door");
        wifiInfo.connectionEnvironment = resultSet.getString("connection_environment");
        wifiInfo.lat = resultSet.getDouble("lat");
        wifiInfo.lnt = resultSet.getDouble("lnt");
        wifiInfo.workDate = resultSet.getTimestamp("work_date");

    }catch (SQLException e){
      throw new RuntimeException(e);
    }finally {
      try {
        if (resultSet != null && !resultSet.isClosed()) {
          resultSet.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
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
    return wifiInfo;
  }


  public ArrayList<WifiInfo> wifiInfoSelect(){

    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    ArrayList<WifiInfo> wifiInfos = new ArrayList<>();

    try {
      Class.forName("org.mariadb.jdbc.Driver");
    }catch (ClassNotFoundException e){
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try{
      connection = DriverManager.getConnection(url, dbUserId, dbPassword);
      String sql = " select mgr_no, region, name, address, address_detail, " +
              " installation_floor, installation_type, installation_institution, " +
              " service, net_type, installation_year, inout_door, connection_environment, " +
              " lat, lnt, work_date " +
              " from public_wifi_info; ";
      preparedStatement = connection.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        WifiInfo wifiInfo = new WifiInfo();
        wifiInfo.mgrNo = resultSet.getString("mgr_no");
        wifiInfo.region = resultSet.getString("region");
        wifiInfo.name = resultSet.getString("name");
        wifiInfo.address = resultSet.getString("address");
        wifiInfo.addressDetail = resultSet.getString("address_detail");
        wifiInfo.installationFloor = resultSet.getString("installation_floor");
        wifiInfo.installationType = resultSet.getString("installation_type");
        wifiInfo.installationInstitution = resultSet.getString("installation_institution");
        wifiInfo.service = resultSet.getString("service");
        wifiInfo.netType = resultSet.getString("net_type");
        wifiInfo.installationYear = resultSet.getInt("installation_year");
        wifiInfo.inoutDoor = resultSet.getString("inout_door");
        wifiInfo.connectionEnvironment = resultSet.getString("connection_environment");
        wifiInfo.lat = resultSet.getDouble("lat");
        wifiInfo.lnt = resultSet.getDouble("lnt");
        wifiInfo.workDate = resultSet.getTimestamp("work_date");

        wifiInfos.add(wifiInfo);
      }
    }catch (SQLException e){
      throw new RuntimeException(e);
    }finally {
      try {
        if (resultSet != null && !resultSet.isClosed()) {
          resultSet.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
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
    return wifiInfos;
  }


  public ArrayList<History> historySelect(){

    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    ArrayList<History> histories = new ArrayList<>();

    try {
      Class.forName("org.mariadb.jdbc.Driver");
    }catch (ClassNotFoundException e){
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try{
      connection = DriverManager.getConnection(url, dbUserId, dbPassword);
      String sql = " select id, lat, lnt, search_date " +
              " from history; ";
      preparedStatement = connection.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        History history = new History();
        history.id = resultSet.getInt("id");
        history.lat = resultSet.getDouble("lat");
        history.lnt = resultSet.getDouble("lnt");
        history.searchDate = resultSet.getTimestamp("search_date");

        histories.add(history);
      }
    }catch (SQLException e){
      throw new RuntimeException(e);
    }finally {
      try {
        if (resultSet != null && !resultSet.isClosed()) {
          resultSet.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
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
    return histories;
  }


  public ArrayList<Bookmark> bookmarkSelect(){

    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    ArrayList<Bookmark> bookmarks = new ArrayList<>();

    try {
      Class.forName("org.mariadb.jdbc.Driver");
    }catch (ClassNotFoundException e){
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try{
      connection = DriverManager.getConnection(url, dbUserId, dbPassword);
      String sql = " select id, mgr_no, group_name, wifi_name, register_date " +
              " from bookmark; ";
      preparedStatement = connection.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        Bookmark bookmark = new Bookmark();
        bookmark.id = resultSet.getInt("id");
        bookmark.mgrNo = resultSet.getString("mgr_no");
        bookmark.groupName = resultSet.getString("group_name");
        bookmark.wifiName = resultSet.getString("wifi_name");
        bookmark.registerDate = resultSet.getTimestamp("register_date");

        bookmarks.add(bookmark);
      }
    }catch (SQLException e){
      throw new RuntimeException(e);
    }finally {
      try {
        if (resultSet != null && !resultSet.isClosed()) {
          resultSet.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
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
    return bookmarks;
  }


  public Bookmark bookmarkSelect(int id){

    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    Bookmark bookmark = new Bookmark();

    try {
      Class.forName("org.mariadb.jdbc.Driver");
    }catch (ClassNotFoundException e){
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try{
      connection = DriverManager.getConnection(url, dbUserId, dbPassword);
      String sql = " select id, mgr_no, group_name, wifi_name, register_date " +
              " from bookmark" +
              " where id = ?; ";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();

      resultSet.next();
      bookmark.id = resultSet.getInt("id");
      bookmark.mgrNo = resultSet.getString("mgr_no");
      bookmark.groupName = resultSet.getString("group_name");
      bookmark.wifiName = resultSet.getString("wifi_name");
      bookmark.registerDate = resultSet.getTimestamp("register_date");

    }catch (SQLException e){
      throw new RuntimeException(e);
    }finally {
      try {
        if (resultSet != null && !resultSet.isClosed()) {
          resultSet.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
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
    return bookmark;
  }


  public ArrayList<BookmarkGroup> bookmarkGroupSelect(){

    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    ArrayList<BookmarkGroup> bookmarkGroups = new ArrayList<>();

    try {
      Class.forName("org.mariadb.jdbc.Driver");
    }catch (ClassNotFoundException e){
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try{
      connection = DriverManager.getConnection(url, dbUserId, dbPassword);
      String sql = " select id, group_name, no, register_date, modify_date " +
              " from bookmark_group; ";
      preparedStatement = connection.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        BookmarkGroup bookmarkGroup = new BookmarkGroup();
        bookmarkGroup.id = resultSet.getInt("id");
        bookmarkGroup.groupName = resultSet.getString("group_name");
        bookmarkGroup.no = resultSet.getString("no");
        bookmarkGroup.registerDate = resultSet.getTimestamp("register_date");
        bookmarkGroup.modifyDate = resultSet.getTimestamp("modify_date");

        bookmarkGroups.add(bookmarkGroup);
      }
    }catch (SQLException e){
      throw new RuntimeException(e);
    }finally {
      try {
        if (resultSet != null && !resultSet.isClosed()) {
          resultSet.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
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
    return bookmarkGroups;
  }


  public BookmarkGroup bookmarkGroupSelect(int id){

    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    BookmarkGroup bookmarkGroup = new BookmarkGroup();

    try {
      Class.forName("org.mariadb.jdbc.Driver");
    }catch (ClassNotFoundException e){
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try{
      connection = DriverManager.getConnection(url, dbUserId, dbPassword);
      String sql = " select id, group_name, no, register_date, modify_date " +
              " from bookmark_group" +
              " where id = ?; ";
      preparedStatement = connection.prepareStatement(sql);
      preparedStatement.setInt(1, id);
      resultSet = preparedStatement.executeQuery();

      resultSet.next();
      bookmarkGroup.id = resultSet.getInt("id");
      bookmarkGroup.groupName = resultSet.getString("group_name");
      bookmarkGroup.no = resultSet.getString("no");
      bookmarkGroup.registerDate = resultSet.getTimestamp("register_date");
      bookmarkGroup.modifyDate = resultSet.getTimestamp("modify_date");

    }catch (SQLException e){
      throw new RuntimeException(e);
    }finally {
      try {
        if (resultSet != null && !resultSet.isClosed()) {
          resultSet.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
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
    return bookmarkGroup;
  }


  public ArrayList<String> bookmarkGroupNameSelect(){

    String url = "jdbc:mariadb://localhost:3306/publicwifi_db";
    String dbUserId = "root";
    String dbPassword = "mallang";

    ArrayList<String> groupNames = new ArrayList<>();

    try {
      Class.forName("org.mariadb.jdbc.Driver");
    }catch (ClassNotFoundException e){
      throw new RuntimeException(e);
    }

    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    try{
      connection = DriverManager.getConnection(url, dbUserId, dbPassword);
      String sql = " select group_name " +
              " from bookmark_group" +
              " group by group_name; ";
      preparedStatement = connection.prepareStatement(sql);
      resultSet = preparedStatement.executeQuery();

      while (resultSet.next()){
        String groupName = resultSet.getString("group_name");
        groupNames.add(groupName);
      }
    }catch (SQLException e){
      throw new RuntimeException(e);
    }finally {
      try {
        if (resultSet != null && !resultSet.isClosed()) {
          resultSet.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
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
    return groupNames;
  }

}
