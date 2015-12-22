<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Piece types">
    <jsp:attribute name="body">


        <form:form class="form-inline clearfix"
                   action="${pageContext.request.contextPath}/piecetype/edit/${pieceTypeEditId}"
                   method="POST"
                   commandName="pieceTypeEdit">

            <s:bind path="name">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <form:input path="name" type="text" cssClass="form-control name" id="name"
                                placeholder="Name"
                                autofocus="true"/>
                    <form:errors path="name" cssClass="help-block"/>
                </div>
            </s:bind>
            <s:bind path="colors">
                <div class="form-group ${status.error ? 'has-error' : ''}">
                    <my:colors allColors="${allColors}"
                               type="checkbox"
                               activeColors="${pieceTypeEdit.colors}"
                               path="colors"/>
                    <form:errors path="colors" cssClass="help-block"/>
                </div>
            </s:bind>

                <button type="submit" class="btn btn-primary pull-right">
                    <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Save changes
                </button>
        </form:form>

    </jsp:attribute>
</my:pagetemplate>