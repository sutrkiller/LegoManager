<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" dynamic-attributes="attr" %>
<%@ attribute name="allColors" required="true" type="java.util.Collection" %>
<%@ attribute name="activeColors" required="true" type="java.util.Collection" %>
<%@ attribute name="path" required="false" %>
<%@ attribute name="active" required="true" type="java.lang.Boolean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:choose>
    <c:when test="${active}">

        <div class="btn-group colors" data-toggle="buttons">
            <c:forEach items="${allColors}" var="color">

                <c:set var="activeColorString" value=" " />
                <c:forEach var="activeColor" items="${activeColors}">
                    <c:if test="${activeColor eq color}">
                        <c:set var="activeColorString" value="active" />
                    </c:if>
                </c:forEach>

                <label class="btn btn-primary color ${activeColorString}"
                       style="background-color: rgb(${color.r}, ${color.g}, ${color.b})">

                    <form:checkbox value="${color}" path="${path}" />

                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                </label>

            </c:forEach>
        </div>

    </c:when>
    <c:otherwise>

        <div class="btn-group colors inactive">
            <c:forEach items="${activeColors}" var="color">

                <a class="btn btn-primary color disabled" value="${color}"
                      style="background-color: rgb(${color.r}, ${color.g}, ${color.b})">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true" style="visibility: hidden;"></span>
                </a>

            </c:forEach>
        </div>

    </c:otherwise>
</c:choose>

