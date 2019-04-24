<%@ page import="com.itheima.domain.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>用户信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
        .hidden{
            width: 1px;
            border-right-color: cornsilk;

        }
    </style>
</head>
<body>
<div class="container">
    <h3 style="text-align: center">用户信息列表</h3>

    <div style="float: left;">
        <form action="${pageContext.request.contextPath}/findUserByPageServlet"  class="form-inline" method="post">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" class="form-control" name="name" value="${condition.name[0]}" id="exampleInputName2" >
            </div>
            <div class="form-group">
                <label for="exampleInputName2">籍贯</label>
                <input type="text" class="form-control" name="address" value="${condition.address[0]}" id="exampleInputName3" >
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">Email</label>
                <input type="email" class="form-control" name="email" value="${condition.email[0]}" id="exampleInputEmail2" >
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>

    </div>

    <div style="float: right; margin: 5px">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a>
        <a class="btn btn-primary" href="javascript:void(0)" id="delete_ch">删除</a>
    </div>
    <form  id="form" action="${pageContext.request.contextPath}/deleteSelectServlet" method="post">
        <table border="1" class="table table-bordered table-hover">
        <tr class="success">
            <th class="hidden"></th>
            <th><input type="checkbox" id="fir_bt"  class="bt"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
            <%--数据展示--%>
        <c:forEach items="${pb.list}" var="user" varStatus="s">
            <tr>
                <%--隐藏域 提交当前页码--%>
                <td class="hidden"><input type="hidden" name="page" value="${pb.currentPage}"></td>
                <td><input type="checkbox" name="id" value="${user.id}" class="bt"></td>
                <td>${pb.rows*(pb.currentPage-1)+s.count}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.age}</td>
                <td>${user.address}</td>
                <td>${user.qq}</td>
                <td>${user.email}</td>
                <td ><a class="btn btn-default btn-sm" href=
                        "${pageContext.request.contextPath}/findUserServlet?id1=${user.id}&page=${pb.currentPage}">修改</a>&nbsp;
                    <a class="btn btn-default btn-sm" href="javascript:deleteUser(${user.id});">删除</a>
                </td>
            </tr>
        </c:forEach>
    </table>
    </form>

    <%--分页显示--%>
    <div>
        <nav>
            <ul class="pagination">
                <!--上一页-->
                <!--当前页为第一页,上一页不能点击-->
            <c:if test="${pb.currentPage==1}">
                    <li class="disabled">
                </c:if>
                <!--当前页不为第一页,上一页可以点击-->
                <c:if test="${pb.currentPage!=1}">
                    <li>
                </c:if>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage-1}&rows=${pb.rows}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
               <c:forEach begin="1" end="${pb.totalPage}" var="i">
                   <c:if test="${pb.currentPage==i}">
                   <li class="active"><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=${pb.rows}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                   </c:if>
                   <c:if test="${pb.currentPage!=i}">
                       <li><a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${i}&rows=${pb.rows}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}">${i}</a></li>
                   </c:if>

               </c:forEach>


                <!--下一页-->
                <!--当前页为最后一页,下一页不可点-->
            <c:if test="${pb.currentPage==pb.totalPage}">
                     <li class="disabled">
                </c:if>
                    <!--当前页不为最后一页,下一页可点-->
                <c:if test="${pb.currentPage!=pb.totalPage}">
                     <li>
                </c:if>
                    <a href="${pageContext.request.contextPath}/findUserByPageServlet?currentPage=${pb.currentPage+1}&rows=${pb.rows}&name=${condition.name[0]}&address=${condition.address[0]}&email=${condition.email[0]}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 25px;margin-left: 5px">
                共${pb.totalCount}条记录,共${pb.totalPage}页
            </span>
            </ul>
        </nav>
    </div>
</div>

<script>

    document.getElementById("fir_bt").onclick=function () {
         var elements = document.getElementsByClassName("bt");
         for (var i=0;i<elements.length;i++){
             elements[i].checked=this.checked;
         }
    };

    document.getElementById("delete_ch").onclick=function () {
        var flag=false;
        if(confirm("确定要删除吗?")){
            var elements = document.getElementsByClassName("bt");
            for (var i=0;i<elements.length;i++){
                if (elements[i].checked){
                    flag=true;
                    break;
                }
            }
            if (flag){
                document.getElementById("form").submit();
            }

        }

    };


    function deleteUser(id) {
        if(confirm("确定要删除吗?")){
            location.href="${pageContext.request.contextPath }/deleteServlet?id2="+id+"&page="+${pb.currentPage};
        }
    }


</script>

</body>
</html>