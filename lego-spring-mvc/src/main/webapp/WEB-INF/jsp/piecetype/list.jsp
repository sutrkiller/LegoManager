<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<s:message code="general.piecetypes" var="title"/>
<my:pagetemplate title="${title}">
    <jsp:attribute name="body">

        <my:a href="/piecetype/new" class="btn btn-success">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            <s:message code="piece.btn.new"/>
        </my:a>

        <table class="table">
            <thead>
                <tr>
                    <th><s:message code="general.name"/></th>
                    <th><s:message code="general.colors"/></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${pieceTypes}" var="pieceType" varStatus="status">
                    <tr>
                        <td>
                            <c:out value="${pieceType.name}"/>
                        </td>
                        <td class="button-cell">
                            <my:colors allColors="${allColors}" activeColors="${pieceType.colors}" type="inactive"/>
                        </td>
                        <td class="button-cell tight-cell">
                            <my:a class="btn btn-default" href="/piecetype/edit/${pieceType.id}">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                            </my:a>
                        </td>
                        <td class="button-cell tight-cell">

                            <form:form class="form-inline"
                                       action="${pageContext.request.contextPath}/piecetype/delete/${pieceType.id}"
                                       method="POST">
                                <button type="submit" class="btn btn-danger">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </button>
                            </form:form>

                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>


        <%--        <!-- Modal -->
                <div class="modal fade" id="editPieceTypeModal" tabindex="-1" role="dialog"
                     aria-labelledby="editPieceTypeModalLabel">
                    <div class="modal-dialog modal-lg" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                                        aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Modal title</h4>
                            </div>

                            <form:form class="form-inline"
                                       action="${pageContext.request.contextPath}/piecetype/edit/{id}"
                                       method="PUT"
                                       commandName="pieceTypeEdit">

                                <div class="modal-body">

                                    <div class="form-group">
                                        <form:input path="name" type="text" cssClass="form-control name" id="name" placeholder="Name"
                                                    autofocus="true"/>
                                    </div>
                                    <div class="form-group">
                                        <my:colors allColors="${allColors}" active="true" activeColors="${pieceTypeEdit.colors}"
                                                   path="colors"/>
                                    </div>

                                </div>

                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary pull-right">
                                        <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Save changes
                                    </button>
                                </div>
                            </form:form>

                        </div>
                    </div>
                </div>--%>

    </jsp:attribute>
</my:pagetemplate>