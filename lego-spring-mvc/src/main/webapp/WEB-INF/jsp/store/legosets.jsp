<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Lego sets">
<jsp:attribute name="body">

    <c:forEach items="${categories}" var="category">
        <h3>${category.name}</h3>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Price</th>
                <th>Models</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${legosets}" var="legoset">
                <c:if test="${legoset.category == category}">
                    <c:url value="/store/legosets/${legoset.id}" var="legosetUrl" />
                    <tr onclick="window.document.location='${legosetUrl}';">
                        <td><c:out value="${legoset.name}"/></td>
                        <td><c:out value="${legoset.price}€"/></td>
                        <td class="<%--button-cell tight-cell--%>">
                            <c:forEach items="${legoset.models}" var="model">
                            <my:a href="/store/models/${model.id}" class="<%--btn btn-link--%>">
                                <c:out value="${model.name}"/>
                            </my:a>
                            ,
                            </c:forEach>
                        </td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>

        </table>

    </c:forEach>

</jsp:attribute>
</my:pagetemplate>