<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<fmt:message var="title" key="store.show.title"/>
<my:pagetemplate title="${title}">
    <jsp:attribute name="body">
        
        <div class="modelsClass">
            <h2>Models</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Age limit</th>
                        <th>Category</th>
                        <th>Pieces id</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${models}" var="model" varStatus="im">
                        <tr>
                            <td><p><c:out value="${model.id}"/></p></td>
                            <td><p><c:out value="${model.name}"/></p></td>
                            <td><p><c:out value="${model.price}"/></p></td>
                            <td><p><c:out value="${model.ageLimit}"/></p></td>
                            <td><p><c:out value="${model.category.name}"/></p></td>
                            <td><p>
                                    <c:forEach items="${model.pieces}" var="piece" varStatus="loop">
                                        <c:out value="${piece.id}"></c:out>
                                        <c:if test="${!loop.last}">, </c:if>
                                    </c:forEach>
                                </p></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <div class="legosetsClass">
            <h2>Lego sets</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Name</th>
                        <th>Price</th>
                        <th>Category</th>
                        <th>Models</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${legosets}" var="legoset" varStatus="il">
                        <tr>
                            <td><p><c:out value="${legoset.id}"/></p></td>
                            <td><p><c:out value="${legoset.name}"/></p></td>
                            <td><p><c:out value="${legoset.price}"/></p></td>
                            <td><p><c:out value="${legoset.category.name}"/></p></td>
                            <td><p>
                                    <c:forEach items="${legoset.models}" var="model" varStatus="loop">
                                        <c:out value="${model.name}"></c:out>
                                        <c:if test="${!loop.last}">, </c:if>
                                    </c:forEach>
                                </p></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

    </jsp:attribute>
</my:pagetemplate>