<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title><c:out value="${user_data.login}" /> | Order Food</title>

        <!-- bootstrap -->
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <!-- custom -->
    </head>
    <body>
        <a href="/link/user">Home</a>

        <h3>Order food here</h3>
        <table>
            <tr>
                <th>No.</th>
                <th>ID</th>
                <th>Food Type</th>
                <th>Name</th>
                <th>Price (RM)</th>
                <th></th>
            </tr>

            <c:forEach varStatus="loop" var="food" items="${foodList}">
                <sql:setDataSource var="db" driver="com.mysql.jdbc.Driver"
                                   url="jdbc:mysql://localhost/mini_restaurant"
                                   user="root" password="" />
                <sql:query var="foodType" dataSource="${db}">
                    SELECT * FROM food_types WHERE id LIKE ${food.typeId};
                </sql:query>

                <tr>
                    <td><c:out value="${loop.index + 1}" /></td>
                    <td><c:out value="${food.id}" /></td>
                    <td>
                        <c:forEach var="item" items="${foodType.rows}">
                            <c:out value="${item.name}" />
                        </c:forEach>
                    </td>
                    <td><c:out value="${food.name}" /></td>
                    <td><c:out value="${food.price}" /></td>
                    <td>
                        <form action="/orders/register" method="post">
                            <input type="hidden" name="foodId" value="${food.id}" />
                            <input type="hidden" name="userId" value="${user_data.id}" />

                            <input type="submit" value="Order" />
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>

        <!-- bootstrap -->
        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <!-- custom -->
    </body>
</html>
