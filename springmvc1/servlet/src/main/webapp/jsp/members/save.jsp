<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%
    // request, response 그대로 사용 가능. JSP 도 결국엔 내부에서 Servlet 으로 변환된다. 동일 서비스 로직, 문법
    MemberRepository memberRepository = MemberRepository.getInstance();

    System.out.println("<save.jsp>");
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    memberRepository.save(member);

%>  <!-- 이 표시 없으면 그냥 쭉 찍어버리는 거. HTTP responseBody 에 그냥 담김 -->
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>
