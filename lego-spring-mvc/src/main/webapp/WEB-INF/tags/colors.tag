<%@ tag pageEncoding="utf-8" trimDirectiveWhitespaces="true" dynamic-attributes="attr" %>
<%@ attribute name="allColors" required="true" type="java.util.Collection" %>
<%@ attribute name="type" required="true" %> <%-- checkbox, radiobutton, inactive --%>
<%@ attribute name="activeColors" type="java.util.Collection" %>
<%@ attribute name="activeColor" %>
<%@ attribute name="path" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<c:choose>
    <c:when test="${type == 'checkbox'}">

        <div class="btn-group colors" data-toggle="buttons">
            <c:forEach items="${allColors}" var="color">

                <c:set var="activeColorString" value=" "/>
                <c:forEach var="activeColor" items="${activeColors}">
                    <c:if test="${activeColor eq color}">
                        <c:set var="activeColorString" value="active"/>
                    </c:if>
                </c:forEach>

                <label class="btn btn-primary color ${activeColorString}"
                       style="background-color: rgb(${color.r}, ${color.g}, ${color.b})">

                    <form:checkbox value="${color}" path="${path}"/>

                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                </label>

            </c:forEach>
        </div>

    </c:when>
    <c:when test="${type == 'radiobutton'}">

        <div class="btn-group colors" data-toggle="buttons">
            <c:forEach items="${allColors}" var="color">

                <c:set var="activeColorString" value=" "/>
                <c:if test="${activeColor == color}">
                    <c:set var="activeColorString" value="active"/>
                </c:if>

                <label class="btn btn-primary color ${activeColorString}"
                       style="background-color: rgb(${color.r}, ${color.g}, ${color.b})">

                    <form:radiobutton value="${color}" path="${path}"/>

                    <span class="glyphicon glyphicon-ok" aria-hidden="true"></span>
                </label>

            </c:forEach>
        </div>

    </c:when>

    <c:when test="${type == 'single'}">

        <div class="btn-group colors" data-toggle="buttons">
            <c:forEach items="${allColors}" var="color">

                <c:set var="activeColorString" value=" "/>
                <c:if test="${activeColor == color}">
                    <c:set var="activeColorString" value="active"/>

                    <label class="btn btn-primary color ${activeColorString}"
                           style="background-color: rgb(${color.r}, ${color.g}, ${color.b})">
                    </label>

                </c:if>

            </c:forEach>
        </div>

    </c:when>

    <c:otherwise>

        <div class="btn-group colors inactive">

            <c:choose>
                <c:when test="${not empty activeColor}">

                    <a class="btn btn-primary color disabled" value="${activeColor}"
                       style="background-color: rgb(${activeColor.r}, ${activeColor.g}, ${activeColor.b})">
                        <span class="glyphicon glyphicon-ok" aria-hidden="true" style="visibility: hidden;"></span>
                    </a>

                </c:when>
                <c:otherwise>

                    <c:forEach items="${activeColors}" var="activeColor">

                        <a class="btn btn-primary color disabled" value="${activeColor}"
                           style="background-color: rgb(${activeColor.r}, ${activeColor.g}, ${activeColor.b})">
                            <span class="glyphicon glyphicon-ok" aria-hidden="true" style="visibility: hidden;"></span>
                        </a>

                    </c:forEach>

                </c:otherwise>
            </c:choose>

        </div>

    </c:otherwise>
</c:choose>

