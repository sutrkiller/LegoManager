<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" dynamic-attributes="attr" %>
<%@ attribute name="colors" required="true" type="java.util.Collection" %>
<%@ attribute name="pieceType" required="false" type="java.util.Collection" %>
<%@ attribute name="active" required="true" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:choose>
    <c:when test="${active}">

        <div class="btn-group" data-toggle="buttons">
            <c:forEach items="${colors}" var="color">

                <c:set var="activeColor" value=" " />
                <c:forEach var="col" items="${pieceType.colors}">
                    <c:if test="${col eq color}">
                        <c:set var="activeColor" value="active" />
                    </c:if>
                </c:forEach>

                <label class="btn btn-primary color ${activeColor}"
                       style="background-color: rgb(${color.r}, ${color.g}, ${color.b})">

                    <form:checkbox value="${color}" path="pieceType.colors" />

                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                </label>

            </c:forEach>
        </div>

    </c:when>
    <c:otherwise>

        <div class="btn-group">
            <c:forEach items="${colors}" var="color">

                <span class="btn btn-primary color" value="${color}"
                      style="background-color: rgb(${color.r}, ${color.g}, ${color.b})">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true" style="visibility: hidden;"></span>
                </span>

            </c:forEach>
        </div>

    </c:otherwise>
</c:choose>

