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

            <div class="span7 text-center">
                <my:a href="/store/legosets" class="btn btn-lg btn-info text-left">
                    Go to the legosets
                </my:a>
                <my:a href="/store/models" class="btn btn-lg btn-info text-left">
                    Go to the models
                </my:a>
            </div>
        </div>

    </jsp:attribute>
</my:pagetemplate>