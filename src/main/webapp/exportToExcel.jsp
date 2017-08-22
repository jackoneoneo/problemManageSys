<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.dbcp2.BasicDataSource"%>
<%@page import="org.springframework.jdbc.core.JdbcTemplate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"  
    pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html xmlns:x="urn:schemas-microsoft-com:office:excel">  
<%   
String fileName = "问题汇总列表.xls";
response.setContentType("application/vnd.ms-excel");
 response.addHeader("Content-Disposition", "attachment;filename="+java.net.URLEncoder.encode(fileName, "UTF-8"));
  %>

 <head>  
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">  
<title>Export to Excel - Demo</title>  
</head>  
<body>  
    <table align="left" border="2">  
        <thead>  
            <tr bgcolor="lightgreen">  
                <th>问题提出者</th>  
                <th>问题提出时间</th>  
                <th>问题具体描述</th>  
                <th>问题回答者姓名</th>
                <th>问题回答的时间</th>
                <th>问题回答的内容</th>  
            </tr>  
        </thead>  
        <tbody>  
            <%  
            BasicDataSource dataSource = new BasicDataSource();
            dataSource.setDriverClassName("com.mysql.jdbc.Driver");
            dataSource.setUrl("jdbc:mysql://localhost:3306/api?useUnicode=true&characterEncoding=utf-8");
            dataSource.setUsername("root");
            dataSource.setPassword("123456");
            JdbcTemplate jdbcTemplate = new JdbcTemplate();
            jdbcTemplate.setDataSource(dataSource);
            String sql = "select a.presenter as presenter,a.createTime as createTime,a.content as content,b.userId as answerer,b.createtime as answer_time,"+
            "b.content as answer_content from webpage a left join comment b on a.id= b.webpageid;";
            List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
            int length = list.size();
                for (int i = 0; i < length; i++) {  
            %>  
            <tr bgcolor="lightblue">  
                <td align="center"><%=list.get(i).get("presenter")%></td>  
                <td align="center"> <%=list.get(i).get("createTime")%></td>  
                <td align="center"><%=list.get(i).get("content")%></td>  
                <td align="center"><%=list.get(i).get("answerer")%></td>  
                <td align="center"><%=list.get(i).get("answer_time")%></td>  
                <td align="center"><%=list.get(i).get("answer_content")%></td>  
            </tr>  
            <%  
                }  
            %>  
        </tbody>  
    </table>  
    <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>  
              
    
</body>  
</html>  