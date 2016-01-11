<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Lego sets">
    <jsp:attribute name="body">

        <c:forEach items="${legosets}" var="legoset">
            <c:forEach items="${categories}" var="category">

                <c:if test="${legoset.category == category}">
                    <c:if test="${not empty legoset.id}">

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
                                <c:url value="/store/legosets/${legoset.id}" var="legosetUrl" />
                                <tr onclick="window.document.location = '${legosetUrl}';">
                                    <td class="col-md-3"><c:out value="${legoset.name}"/></td>
                                    <td class="col-md-3"><c:out value="${legoset.price}€"/></td>
                                    <td class="<%--button-cell tight-cell--%>">
                                        <c:forEach items="${legoset.models}" var="model" varStatus="loop">
                                            <my:a href="/store/models/${model.id}" class="<%--btn btn-link--%>">
                                                <c:out value="${model.name}"/>
                                            </my:a>
                                            <c:if test="${!loop.last}">, </c:if>

                                        </c:forEach>
                                    </td>
                                </tr>
                            </tbody>
                        </table>   
                    </c:if>
                </c:if>      
            </c:forEach>
        </c:forEach>
    </jsp:attribute>
</my:pagetemplate>