<%@ page contentType="application/json; charset=UTF-8" %>
<%@ page import="com.google.gson.Gson" %><%@ page import="java.util.Map"%>
<%
    Map<String, String> data = (Map<String, String>) request.getAttribute("data");
    Gson gson = new Gson();
    String jsonResponse = gson.toJson(data);
    out.print(jsonResponse);
%>
