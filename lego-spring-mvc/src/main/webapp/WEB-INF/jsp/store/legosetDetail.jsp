<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Lego set ${legoset.name}">
<jsp:attribute name="body">

<dl class="dl-horizontal">
    <dt>Category</dt>
    <dd>${legoset.category.name} - ${legoset.category.description}</dd>

    <dt>Price</dt>
    <dd>${legoset.price}€</dd>

    <dt>Models</dt>
    <dd>
        <c:forEach items="${legoset.models}" var="model">
            <my:a href="/store/models/${model.id}" class="<%--btn btn-link--%>">
                <c:out value="${model.name}"/>
            </my:a>
            ,
        </c:forEach>
    </dd>
</dl>

</jsp:attribute>
</my:pagetemplate>