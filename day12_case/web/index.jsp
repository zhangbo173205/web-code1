
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>首页</title>

  <!-- 1. 导入CSS的全局样式 -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
  <script src="js/jquery-2.1.0.min.js"></script>
  <!-- 3. 导入bootstrap的js文件 -->
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript">
  </script>
  <style>
    #a{
      font-size: 20px;
    }
  </style>

</head>
<body>
<%
  String flag = request.getParameter("flag");
  if (flag!=null){
      request.removeAttribute("flag");
    request.getSession().invalidate();
  }

%>
<div style="margin-left: 50px">
  <h3 style="color: red">
    <c:if test="${manager!=null}">
      ${manager.username}
      <a href="" id="exit">[退出]</a>
      欢迎你
  </h3>
  </c:if>
<c:if test="${manager==null}">
  您好!
  <a style="color: red" href="${pageContext.request.contextPath}/login.jsp" id="a">请登录</a>

  </h3>
</c:if>
</div>
  <div align="center">
    <a
            href="${pageContext.request.contextPath}/findUserByPageServlet" style="text-decoration:none;font-size:33px">查询所有用户信息
    </a>
  </div>
   <%--
    <c:if test="${manager==null}">
      <a style="color: red" href="${pageContext.request.contextPath}/login.jsp" id="a">请登录</a>
      欢迎你
    </h3>
</div>
   <div align="center">
     <a
                href="javascript:void(0)" style="text-decoration:none;font-size:33px">查询所有用户信息
     </a>
   </div>
    </c:if>--%>
<%--    欢迎你
  </h3>
  </div>
  <div align="center">
  <a
          href="${pageContext.request.contextPath}/findUserByPageServlet" style="text-decoration:none;font-size:33px">查询所有用户信息
  </a>
  </div>--%>

<script>
      document.getElementById("exit").onclick=function () {
          this.href="${pageContext.request.contextPath}/index.jsp?flag=false"

      }

</script>
</body>
</html>
