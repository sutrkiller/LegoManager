<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="true" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate>
    <jsp:attribute name="body">

        <div class="container">
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3">

                    <c:url value="/login" var="loginUrl"/>
                    <form action="${loginUrl}" method="post" class="login-form">
                        <c:if test="${param.error != null}">
                            <div class="alert alert-danger">
                                <p><s:message code="login.invalid"/></p>
                            </div>
                        </c:if>
                        <c:if test="${param.logout != null}">
                            <div class="alert alert-success">
                                <p><s:message code="login.logout"/></p>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <div class="input-group">
                                <span class="input-group-addon" for="username">
                                    <i class="glyphicon glyphicon-user"></i>
                                </span>
                                <s:message code="login.username" var="username"/>
                                <input type="text" class="form-control" id="username" name="username"
                                       placeholder="${username}" required="required" autofocus="autofocus" />
                            </div>

                        </div>
                        <div class="form-group">
                            <div class="input-group" >
                                <span class="input-group-addon" for="password">
                                    <i class="glyphicon glyphicon-lock"></i>
                                </span>
                                <s:message code="login.password" var="pass"/>
                                <input type="password" class="form-control" id="password" name="password"
                                       placeholder="${pass}" required="required" />
                            </div>
                        </div>

                        <input type="hidden"
                               name="${_csrf.parameterName}"
                               value="${_csrf.token}"/>

                        <div class="form-group">
                            <s:message code="login.btn" var="btn"/>
                            <input type="submit" class="btn btn-block btn-primary btn-default" value="${btn}">
                        </div>

                    </form>

                </div>
            </div>
        </div>

    </jsp:attribute>
</my:pagetemplate>