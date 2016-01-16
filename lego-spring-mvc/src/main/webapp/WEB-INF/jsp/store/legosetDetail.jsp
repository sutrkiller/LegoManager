<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<s:message code="general.legosets" var="title"/>
<my:pagetemplate title="${title} ${legoset.name}">
<jsp:attribute name="body">

<dl class="dl-horizontal">
    <dt><s:message code="general.category"/></dt>
    <dd>${legoset.category.name} - ${legoset.category.description}</dd>

    <dt><s:message code="general.price"/></dt>
    <dd>${legoset.price}€</dd>

    <dt><s:message code="general.models"/></dt>
    <dd>
        <c:forEach items="${legoset.models}" var="model" varStatus="loop">
            <my:a href="/store/models/${model.id}" class="<%--btn btn-link--%>">
                <c:out value="${model.name}"/>
            </my:a>
            <c:if test="${!loop.last}">, </c:if>
        </c:forEach>
    </dd>
</dl>

</jsp:attribute>
</my:pagetemplate>