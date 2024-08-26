<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-primary mt-1">QUẢN TRỊ KHU ĐỖ XE</h1>
<c:url value="/khuDoXe" var="action" />

<form:form method="post" action="${action}" modelAttribute="khudoxe" enctype="multipart/form-data">
    <c:if test="${not empty errMsg}">
        <div class="alert alert-danger">${errMsg}</div>
    </c:if>
    <div class="mb-3 mt-3">
        <form:errors path="*" element="div" cssClass="alert alert-danger" />
        <label for="address" class="form-label">Tên khu</label>
        <form:input path="tenDay" type="text" class="form-control" id="address" placeholder="Nhập tên khu..." name="tenDay" />
    </div>

    

    <div class="mb-3 mt-3">
        <form:hidden path="id" />
        <button class="btn btn-success" type="submit">

            <c:choose>
                <c:when test="${khudoxe.id != null}">
                    <option value="${c.id}" selected>Cập nhật khu do xe</option>
                </c:when>
                <c:otherwise>
                    Thêm khu do xe
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>
