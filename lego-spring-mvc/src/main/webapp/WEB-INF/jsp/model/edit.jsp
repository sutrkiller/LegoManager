<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Change model">
    <jsp:attribute name="body">
        <div class="container">

            <div class="form-group">
                <form:form method="post" action="${pageContext.request.contextPath}/model/discount/${id}"
                           modelAttribute="modelChange" cssClass="form-horizontal">
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button class="btn btn-success" type="submit">Set 50% discount</button>
                        </div>
                    </div>
                </form:form>
            </div>
            <form:form method="post" action="${pageContext.request.contextPath}/model/edit/${id}"
                       modelAttribute="modelChange" cssClass="form-horizontal">

                <div class="form-group">
                    <form:label path="categoryId" cssClass="col-sm-2 control-label">Category</form:label>
                        <div class="col-sm-10">
                        <form:select path="categoryId" cssClass="form-control">
                            <c:forEach items="${categories}" var="c">
                                <form:option value="${c.id}">${c.name}</form:option>
                            </c:forEach>
                        </form:select>
                        <p class="help-block"><form:errors path="categoryId" cssClass="error"/></p>
                    </div>
                </div>
                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                        <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control"/>
                        <form:errors path="name" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group ${ageLimit_error?'has-error':''}">
                    <form:label path="ageLimit" cssClass="col-sm-2 control-label">Age limit</form:label>
                        <div class="col-sm-10">
                        <form:input path="ageLimit" cssClass="form-control"/>
                        <form:errors path="ageLimit" cssClass="help-block"/>
                    </div>
                </div>
                <div class="form-group ${price_error?'has-error':''}">
                    <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
                        <div class="col-sm-10">
                        <form:input path="price" cssClass="form-control"/>
                        <form:errors path="price" cssClass="help-block"/>
                    </div>
                </div>


                <div class="form-group">
                    <form:label path="name" cssClass="col-sm-2 control-label">Pieces</form:label>
                        <div class="col-sm-10">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>Name</th>
                                        <th>Colors</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${pieces}" var="p">
                                    <tr>
                                        <td><c:out value="${p.type.name}"/></td>
                                        <td class="button-cell">
                                            <my:colors allColors="${p.type.colors}" type="inactive" activeColor="${p.currentColor}"/>
                                        </td>
                                        <td class="button-cell tight-cell">
                                            <my:a href="/model/${id}/deletePiece/${p.id}" class="btn" method="post">
                                                <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                                Remove
                                            </my:a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="name" cssClass="col-sm-2 control-label"><i class="glyphicon glyphicon-plus"></i> Add Pieces</form:label>
                        <div class="col-sm-10">
                            <table class="table">
                                <tbody>
                                    <tr>
                                        <td class="button-cell" colspan="3">
                                            <div class="btn-group">
                                            <c:forEach items="${pieceTypes}" var="pieceType">
                                                <my:a href="/model/edit/${id}/addPiece?pieceTypeId=${pieceType.id}" class="btn btn-default">${pieceType.name}</my:a>
                                            </c:forEach>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-success" type="submit">
                            <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span> Save changes
                        </button>
                    </div>
                </div>
            </form:form>


        </div>
    </jsp:attribute>
</my:pagetemplate>