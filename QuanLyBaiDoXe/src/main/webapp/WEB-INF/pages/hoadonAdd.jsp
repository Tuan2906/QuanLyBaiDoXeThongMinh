<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-primary mt-1">QUẢN TRỊ KHU ĐỖ XE</h1>
<c:url value="/hoadonAdd" var="action" />

<form:form method="post" action="${action}" modelAttribute="hoadon" class="ml-3">
    <c:if test="${not empty errMsg}">
        <div class="alert alert-danger">${errMsg}</div>
    </c:if>
    <div class="mb-3 mt-3">
        <form:errors path="*" element="div" cssClass="alert alert-danger" />
        <label for="address" class="form-label">tiền</label>
        <form:input path="soTien" type="text" class="form-control" id="address" placeholder="Nhập tiền" name="soTien" />
    </div>
    <div class="mb-3 mt-3">
        <label for="baiDoXeid" class="form-label">Chọn thông tin đăng ký</label>
        <form:select path="userId" id="userId" class="form-select">
            <c:forEach items="${TTDKData}" var="ttdk">
                <c:choose>
                    <c:when test="${ttdk.id == hoadon.userId.id}">
                        <option value="${ttdk.id}" selected>${ttdk.id}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${ttdk.id}">${ttdk.id}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>
    <div class="mb-3 mt-3">
        <form:hidden path="id" />
        <form:hidden path="ngayCapNhat" />
        <form:hidden path="uid" />

        <button class="btn btn-success" type="submit">

            <c:choose>
                <c:when test="${hoadon.id != null}">
                    <option value="${c.id}" selected>Cập nhật hóa đơn</option>
                </c:when>
                <c:otherwise>
                    Thêm hóa đơn
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>
