<%--<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>--%>
<%--<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>--%>
<%--<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>--%>
<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>

<%--<my:pagetemplate title="${model.name} pieces">--%>
    <%--<jsp:attribute name="body">--%>
        <%--<div class="container">--%>
            <%--<form:form method="post" action="${pageContext.request.contextPath}/model/${id}/piece"--%>
                       <%--modelAttribute="model" cssClass="form-horizontal">--%>

                <%--<c:forEach items="${model.pieces}" var="piece">--%>
                    <%--<table class="table">--%>
                        <%--<thead>--%>
                        <%--<tr>--%>
                            <%--<th>Piece type</th>--%>
                            <%--<th>Color</th>--%>
                        <%--</tr>--%>
                        <%--</thead>--%>
                        <%--<tbody>--%>
                        <%--<tr>--%>
                            <%--<td><c:out value="${piece.type.name}"/></td>--%>
                            <%--<td>--%>
                                <%--<my:colors allColors="${allColors}" active="true"--%>
                                           <%--activeColor="${piece.currentColor}"/>--%>
                            <%--</td>--%>
                        <%--</tr>--%>

                        <%--<tr>--%>

                            <%--<form:form class="form-inline"--%>
                                       <%--action="${pageContext.request.contextPath}/model/{id}/addPiece"--%>
                                       <%--method="POST">--%>

                                <%--<td>--%>
                                    <%--<form:select path="pi" cssClass="form-control">--%>
                                        <%--<c:forEach items="${pieceTypes}" var="p">--%>
                                            <%--<form:option value="${p.id}">${p.name}</form:option>--%>
                                        <%--</c:forEach>--%>
                                    <%--</form:select>--%>
                                <%--</td>--%>
                                <%--<td class="button-cell tight-cell" colspan="2">--%>

                                    <%--<button type="submit" class="btn btn-default">--%>
                                        <%--<span class="glyphicon glyphicon-plus" aria-hidden="true"></span> Create--%>
                                    <%--</button>--%>
                                <%--</td>--%>
                            <%--</form:form>--%>
                        <%--</tr>--%>

                        <%--</tbody>--%>
                    <%--</table>--%>

                <%--</c:forEach>--%>
            <%--</form:form>--%>
        <%--</div>--%>

    <%--</jsp:attribute>--%>
<%--</my:pagetemplate>--%>

