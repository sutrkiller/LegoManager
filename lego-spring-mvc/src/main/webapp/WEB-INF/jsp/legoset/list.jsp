<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<s:message code="general.legosets" var="title"/>
<my:pagetemplate title="${title}">
    <jsp:attribute name="body">

        <my:a href="/legoset/new" class="btn btn-success">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <s:message code="legoset.btn.new"/>
        </my:a>

        <table class="table">
            <thead>
                <tr>
                    <th><s:message code="general.name"/></th>
                    <th><s:message code="general.price"/></th>
                    <th><s:message code="general.category"/></th>
                    <th><s:message code="general.models"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${legosets}" var="legoset">
                    <tr>
                        <td class="col-md-2"><c:out value="${legoset.name}"/></td>
                        <td class="col-md-2"><c:out value="${legoset.price}"/></td>
                        <td class="col-md-2"><c:out value="${legoset.category.name}"/></td>
                        <td class="col-md-2">
                            <c:forEach items="${legoset.models}" var="model" varStatus="loop">
                                <c:out value="${model.name}"></c:out>
                                <c:if test="${!loop.last}">, </c:if>
                            </c:forEach>
                        </td>
                        <td class="button-cell tight-cell">
                            <my:a href="/legoset/edit/${legoset.id}" class="btn btn-default">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </my:a>
                        </td>
                        <td class="button-cell tight-cell">
                            <my:a href="/legoset/delete/${legoset.id}" class="btn btn-danger" method="POST">
                                <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                            </my:a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>


    </jsp:attribute>
</my:pagetemplate>