<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="Change legoset">
    <jsp:attribute name="body">
        <div class="container">
            
            <form:form method="post" action="${pageContext.request.contextPath}/legoset"
                       modelAttribute="legosetChange" cssClass="form-horizontal">
                
                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="name" cssClass="col-sm-2 control-label">Id</form:label>
                        <div class="col-sm-10">
                        <form:input path="id" cssClass="form-control" value="${legosetChange.id}" readonly="true"/>
                        <form:errors path="id" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                        <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control" value="${legosetChange.name}"/>
                        <form:errors path="name" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group ${price_error?'has-error':''}">
                    <form:label path="price" cssClass="col-sm-2 control-label">Price</form:label>
                        <div class="col-sm-10">
                        <form:input path="price" cssClass="form-control" value="${legosetChange.price}"/>
                        <form:errors path="price" cssClass="help-block"/>
                    </div>
                </div>

                <div class="form-group">
                    <form:label path="category" cssClass="col-sm-2 control-label">Category</form:label>
                        <div class="col-sm-10">
                        <form:select path="category" cssClass="form-control">
                            <c:forEach items="${categories}" var="c" >
                                <c:choose>
                                    <c:when test="${c.id == legosetChange.category.id}">
                                        <form:option selected="selected" value="${c.id}"/>
                                    </c:when>
                                    <c:otherwise>
                                        <form:option value="${c.id}" />
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>

                        </form:select>
                        <p class="help-block"><form:errors path="category" cssClass="error"/></p>
                    </div>
                </div>


                <div class="form-group">
                    <form:label path="models" cssClass="col-sm-2 control-label">Models</form:label>
                        <div class="col-sm-10">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th>id</th>
                                        <th>name</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${models}" var="model">
                                    <tr>
                                        <td>${model.id}</td>
                                        <td><c:out value="${model.name}"/></td>
                                        <td>
                                            <c:set var="isSelected" value="false"/>
                                            <c:forEach items="${legosetChange.models}" var="l">
                                                <c:if test="${l.id == model.id}">
                                                    <c:set var="isSelected" value="true"/>
                                                </c:if>
                                            </c:forEach>
                                            <c:choose>
                                         <c:when test="${!isSelected}">
                                             <my:a href="/legoset/${legosetChange.id}/addModel?modelId=${model.id}" class="btn btn-default">
                                                <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                                Add
                                            </my:a>
                                         </c:when>
                                         <c:otherwise>
                                             <my:a href="/legoset/${legosetChange.id}/removeModel?modelId=${model.id}" class="btn btn-defualt">
                                                <span class="glyphicon glyphicon-minus" aria-hidden="true"></span>
                                                Remove
                                            </my:a>
                                         </c:otherwise>
                                     </c:choose>
                                            
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <p class="help-block"><form:errors path="models" cssClass="error"/></p>
                    </div>
                </div>

                
                <div class="form-group">        
                    <div class="col-sm-offset-2 col-sm-10">
                        <button class="btn btn-success" type="submit">Change legoset details</button>
                    </div>
                </div>
            </form:form>
        </div>



    </jsp:attribute>
</my:pagetemplate>