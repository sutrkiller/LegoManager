<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Models">
<jsp:attribute name="body">

    <my:a href="/model/new" class="btn btn-primary">
        <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
        New Model
    </my:a>

    <c:forEach items="${categories}" var="category">
        <h1>"${category.name}"</h1>
        <table class="table">
            <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Age limit</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${models}" var="model">
                <c:if test="${model.category == category}">
                    <tr>
                        <td><c:out value="${model.name}"/></td>
                        <td><c:out value="${model.price}€"/></td>
                        <td><c:out value="${model.ageLimit}"/></td>
                        <td class="button-cell tight-cell">
                            <my:a href="/model/${model.id}/piece" class="btn btn-default">
                                <span class="glyphicon glyphicons-plus-sign" aria-hidden="true"></span>
                            </my:a>
                        </td>
                        <td class="button-cell tight-cell">
                            <my:a href="/model/change/${model.id}" class="btn btn-default">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </my:a>
                        </td>
                        <td class="button-cell tight-cell">
                            <my:a href="/model/delete/${model.id}" class="btn btn-danger" method="POST">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </my:a>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>

        </table>

    </c:forEach>

</jsp:attribute>
</my:pagetemplate>