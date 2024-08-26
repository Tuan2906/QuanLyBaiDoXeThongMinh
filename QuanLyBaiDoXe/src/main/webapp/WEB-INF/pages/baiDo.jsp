<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-primary mt-1">QUẢN TRỊ BÃI ĐỖ XE</h1>
<c:url value="/baiDo" var="action" />

<form:form method="post" action="${action}" modelAttribute="baiDoXe" enctype="multipart/form-data">
    <c:if test="${not empty timeError}">
        <div class="alert alert-danger">${timeError}</div>
    </c:if>
    <div class="mb-3 mt-3">
        <form:errors path="diachi" element="div" cssClass="alert alert-danger" />
        <label for="address" class="form-label">Địa chỉ</label>
        <form:input path="diachi" type="text" class="form-control" id="address" placeholder="Địa chỉ" name="diachi" />
    </div>
    <div class="mb-3 mt-3">
        <label for="address" class="form-label">Tên Bãi</label>
        <form:input path="tenBai" type="text" class="form-control" id="address" placeholder="Tên Bãi" name="tenBai" />
    </div>
    <div class="mb-3 mt-3">
        <form:errors path="thoigiancua" element="div" cssClass="alert alert-danger" />
        <label for="openTime" class="form-label">Thời gian mở cửa</label>
        <form:input path="thoigiancua" type="datetime-local" class="form-control" id="openTime" name="thoigiancua" />
    </div>

    <div class="mb-3 mt-3">
        <form:errors path="thoigiandongcua" element="div" cssClass="alert alert-danger" />
        <label for="closeTime" class="form-label">Thời gian đóng cửa</label>
        <form:input path="thoigiandongcua" type="datetime-local" class="form-control" id="closeTime" name="thoigiandongcua" />
    </div>

    <div class="mb-3 mt-3">
        <label for="files" class="form-label">Ảnh bãi đỗ xe (chọn nhiều ảnh)</label>

        <input type="file" id="files" name="files" multiple="multiple" />
        <form:errors path="files" element="div" cssClass="alert alert-danger" />
    </div>

    <div class="mb-3 mt-3">
        <form:hidden path="id" />
        <form:hidden path="files" />

        <button type="submit" class="btn btn-success">   Thêm bãi đỗ xe
        </button>
    </div>
</form:form>
