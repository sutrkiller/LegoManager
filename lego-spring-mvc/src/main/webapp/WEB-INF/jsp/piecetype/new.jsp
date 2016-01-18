<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<s:message code="piece.title.new" var="title"/>
<my:pagetemplate title="${title}">
    <jsp:attribute name="body">
        <div class="container">
            <form:form action="${pageContext.request.contextPath}/piecetype/create"
                       method="POST" modelAttribute="pieceTypeCreate" cssClass="form-horizontal">

                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="name" cssClass="col-sm-2 control-label"><s:message code="general.name"/></form:label>
                        <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control"/>
                        <form:errors path="name" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="colors" cssClass="col-sm-2 control-label"><s:message code="general.colors"/></form:label>
                        <div class="col-sm-10">
                            <my:colors allColors="${allColors}"
                                   type="checkbox"
                                   activeColors="${pieceTypeCreate.colors}"
                                   path="colors"/>
                            <form:errors path="colors" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">       
                        <button class="btn btn-success" type="submit"><s:message code="piece.btn.create"/></button>
                    </div>
                </div>
            </form:form>
        </jsp:attribute>
    </my:pagetemplate>