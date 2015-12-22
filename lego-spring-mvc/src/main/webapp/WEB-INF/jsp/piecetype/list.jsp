<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Piece types">
    <jsp:attribute name="body">

        <table class="table">
            <thead>
            <tr>
                <th>name</th>
                <th>colors</th>
                <th>edit</th>
                <th>delete</th>
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


            <tr>
                <td colspan="4"></td>
            </tr>

            <tr>

                <form:form class="form-inline" action="${pageContext.request.contextPath}/piecetype/create"
                           method="POST"
                           commandName="pieceTypeCreate">

                    <td class="button-cell">
                        <s:bind path="name">
                            <div class="form-group ${status.error ? 'has-error' : ''}">
                                <form:input path="name" type="text" cssClass="form-control" id="name" placeholder="Name"
                                            autofocus="true"/>
                                <form:errors path="name" cssClass="help-block"/>
                            </div>
                        </s:bind>
                    </td>
                    <td class="button-cell">
                        <s:bind path="colors">
                            <div class="form-group ${status.error ? 'has-error' : ''}">

                                <my:colors allColors="${allColors}"
                                           type="checkbox"
                                           activeColors="${pieceTypeCreate.colors}"
                                           path="colors"/>
                                <form:errors path="colors" cssClass="help-block"/>
                            </div>
                        </s:bind>
                    </td>
                    <td class="button-cell tight-cell" colspan="2">

                        <button type="submit" class="btn btn-default">
                            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Create
                        </button>
                    </td>
                </form:form>
            </tr>

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