<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" trimDirectiveWhitespaces="false" session="false" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="my" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<my:pagetemplate title="New lego set">
    <jsp:attribute name="body">
        <div class="container">
            <form:form method="post" action="${pageContext.request.contextPath}/legoset/create"
                       modelAttribute="legosetCreate" cssClass="form-horizontal">

                <div class="form-group ${name_error?'has-error':''}">
                    <form:label path="name" cssClass="col-sm-2 control-label">Name</form:label>
                        <div class="col-sm-10">
                        <form:input path="name" cssClass="form-control"/>
                        <form:errors path="name" cssClass="help-block"/>
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
                    <form:label path="categoryId" cssClass="col-sm-2 control-label">Category</form:label>
                        <div class="col-sm-10">
                        <form:select path="categoryId" cssClass="form-control">
                            <c:forEach items="${categories}" var="c">
                                <form:option value="${c.id}"><c:out value="${c.name}"/></form:option>
                            </c:forEach>
                        </form:select>
                        <p class="help-block"><form:errors path="categoryId" cssClass="error"/></p>
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">       
                        <button class="btn btn-success" type="submit">Create legoset</button>
                    </div>
                </div>
            </form:form>
        </div>



    </jsp:attribute>
</my:pagetemplate>