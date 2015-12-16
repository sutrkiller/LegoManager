<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Category Detail">
    <jsp:attribute name="body">

        <form method="post" action="${pageContext.request.contextPath}/category/delete/${category.id}">
            <button type="submit" class="btn btn-danger">
                <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                Delete
            </button>
        </form>


        <table class="table">
            <thead>
                <tr>
                    <th>id</th>
                    <th>name</th>
                    <th>description</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>${category.id}</td>
                    <td><c:out value="${category.name}"/></td>
                    <td><c:out value="${category.description}"/></td>
                </tr>
            </tbody>
        </table>
    </jsp:attribute>
</my:pagetemplate>