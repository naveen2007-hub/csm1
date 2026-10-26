<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Scanner,java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
  
  <% int a = 10;
  out.println(a);
   %>
  
  <%!
  int square(int n){
	  return n*n;
  }
  
  %>
  <%= square(5) %>
  <% int age=20;%>
  <% if(age>=18){ %>
  vote
  <%}else{ %>
  Not eligible
  <%} %>
  <%    
      int ag=10;
      if(ag>=18){
	      out.println("Vote");
      }else{
	       out.println("<h1>Not elg</h1>");
  }
  %>
  <%
  for(int i=0;i<=10;i++){ out.println(i);}%>
  <% request.setAttribute("name","Naveen");%>
  <% out.println(request.getAttribute("name")); %>
  
  
  
  <% Scanner sc = new Scanner(System.in);%>
  
   <%System.out.println("Enter id");
     int id=sc.nextInt();
     System.out.println("Enter Name");
     String name=sc.next();
     out.println(id +" "+name);
  
  
  %>
  <% ArrayList arr= new ArrayList<>();
     arr.add(1);
     arr.add("Naveen");
     arr.add('A');
  
     out.println(arr);
  
  %>
  
  
  
  
</body>
</html>