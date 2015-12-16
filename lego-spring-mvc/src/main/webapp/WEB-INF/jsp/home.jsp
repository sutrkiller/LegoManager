<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<my:pagetemplate>
    <jsp:attribute name="body">

        <div class="jumbotron">
            <h1>Welcome to Lego Manager!</h1>
            <p class="lead">Lego Manager is a Java application, that serves to easily manage a Lego storage of your shop. </p>
            <p class="lead"><small>This Java application is created as a semestral project within a course PA165 Enterprise Java applications at MU.</small></p>
            <p><a class="btn btn-lg btn-info" href="${pageContext.request.contextPath}/store/show"
                  role="button">I'm a customer!</a>
                <a class="btn btn-lg btn-warning" href="${pageContext.request.contextPath}/login"
                   role="button">I'm a Lego admin!</a></p>
        </div>

    </jsp:attribute>
</my:pagetemplate>