<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New piece">
    <jsp:attribute name="body">
        <div class="container">
            <form:form action="${pageContext.request.contextPath}/piecetype/create"
                       method="POST" modelAttribute="pieceTypeCreate" cssClass="form-horizontal">

                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                        <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control"/>
                        <form:errors path="name" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="colors" cssClass="col-sm-2 control-label">Colors</form:label>
                        <div class="col-sm-10">
                            <my:colors allColors="${allColors}"
                                   type="checkbox"
                                   activeColors="${pieceTypeCreate.colors}"
                                   path="colors"/>
                        <form:errors path="name" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">       
                        <button class="btn btn-success" type="submit">Create piece</button>
                    </div>
                </div>
            </form:form>
        </jsp:attribute>
    </my:pagetemplate>