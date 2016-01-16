<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<s:message code="general.models" var="title"/>
<my:pagetemplate title="${title}">
<jsp:attribute name="body">

    <c:forEach items="${categories}" var="category">
        <h3>${category.name}</h3>
        <table class="table table-hover">
            <thead>
            <tr>
                <th><s:message code="general.name"/></th>
                <th><s:message code="general.price"/></th>
                <th><s:message code="general.agelimit"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${models}" var="model">
                <c:if test="${model.category == category}">
                    <c:url value="/store/models/${model.id}" var="modelUrl" />
                    <tr onclick="window.document.location='${modelUrl}';">
                        <td class="col-md-3"><c:out value="${model.name}"/></td>
                        <td class="col-md-3"><c:out value="${model.price}€"/></td>
                        <td class="col-md-3"><c:out value="${model.ageLimit}"/></td>
                    </tr>
                </c:if>
            </c:forEach>
            </tbody>

        </table>

    </c:forEach>

</jsp:attribute>
</my:pagetemplate>