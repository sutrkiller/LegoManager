<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Categories">
    <jsp:attribute name="body">

        <my:a href="/category/new" class="btn btn-success">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            New category
        </my:a>


        <table class="table">
            <thead>
                <tr>
                    <th>name</th>
                    <th>description</th>
                    <th>edit</th>
                    <th>delete</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td><c:out value="${category.name}"/></td>
                        <td><c:out value="${category.description}"/></td>
                        <td class="button-cell tight-cell">
                            <my:a href="/category/change/${category.id}" class="btn btn-default">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </my:a>
                        </td>
                        <td class="button-cell tight-cell">
                            <my:a href="/category/delete/${category.id}" class="btn btn-danger" method="POST">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </my:a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


    </jsp:attribute>
</my:pagetemplate>