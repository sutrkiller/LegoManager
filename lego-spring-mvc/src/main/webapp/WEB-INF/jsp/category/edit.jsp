<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<s:message code="category.title.change" arguments="${categoryChange.name}" var="title"/>
<my:pagetemplate title="${title}">
    <jsp:attribute name="body">
        <div class="container">
            <form:form method="post" action="${pageContext.request.contextPath}/category/update/"
                       modelAttribute="categoryChange" cssClass="form-horizontal">
                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="name" cssClass="col-sm-2 control-label"><s:message code="general.id"/></form:label>
                        <div class="col-sm-10">
                        <form:input path="id" cssClass="form-control" value="${categoryChange.id}" readonly="true"/>
                        <form:errors path="id" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="name" cssClass="col-sm-2 control-label"><s:message code="general.name"/></form:label>
                        <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control" value="${categoryChange.name}"/>
                        <form:errors path="name" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group ${description_error?'has-error':''}">
                    <form:label path="description" cssClass="col-sm-2 control-label"><s:message code="general.description"/></form:label>
                        <div class="col-sm-10">
                        <form:input path="description" cssClass="form-control" value="${categoryChange.description}"/>
                        <form:errors path="description" cssClass="help-block"/>
                    </div>
                </div>     

                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-success" type="submit">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            <s:message code="category.btn.save"/>
                        </button>
                    </div>
                </div>
            </form:form>
        </div>
    </jsp:attribute>
</my:pagetemplate>