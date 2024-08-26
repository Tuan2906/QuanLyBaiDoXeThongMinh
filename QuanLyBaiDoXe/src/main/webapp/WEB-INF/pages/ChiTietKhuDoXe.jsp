<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-primary mt-1">QUẢN TRỊ CHI TIET CHO ĐỖ XE</h1>
<c:url value="/chiTietKhuDoXe" var="action" />

<form:form method="post" action="${action}" modelAttribute="ChiTietKhuDoXe" enctype="multipart/form-data">
    <c:if test="${not empty errMsg}">
        <div class="alert alert-danger">${errMsg}</div>
    </c:if>
    <div class="mb-3 mt-3">
        <form:errors path="ghiChu" element="div" cssClass="alert alert-danger" />
        <label for="address" class="form-label">Ghi Chú</label>
        <form:input path="ghiChu" type="text" class="form-control" id="address" placeholder="Ghi chu" name="ghiChu" />
    </div>
    <div class="mb-3 mt-3">
        <label for="ngayCapNhat" class="form-label">Ngày Cập Nhật</label>
        <form:input path="ngayCapNhat" type="datetime-local" class="form-control" id="ngayCapNhat" />
    </div>

    <div class="mb-3 mt-3">
        <label for="openTime" class="form-label">Phương tiện</label>
        <form:select class="form-select" path="phuongTienId" >
            <c:forEach items="${phuongtien}" var="c">
                <c:choose>
                    <c:when test="${c.id == product.phuongTienId.id}">
                        <option value="${c.id}" selected>${c.loai}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${c.id}">${c.loai}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>
    </div>


    <div class="mb-3 mt-3">
        <label for="openTime" class="form-label">Khu đỗ xe </label>
        <form:select class="form-select" path="khuDoId" >
            <c:forEach items="${khudoXe}" var="k">
                <c:choose>
                    <c:when test="${k.id == ChiTietKhuDoXe.khuDoId.id}">
                        <option value="${k.id}" selected>${k.tenDay}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${k.id}">${k.tenDay}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>
    </div><div class="mb-3 mt-3">
        <label for="openTime" class="form-label">Gía phương tiện</label>
        <form:select class="form-select" path="giaId" >
            <c:forEach items="${gia}" var="g">
                <c:choose>
                    <c:when test="${g.id == ChiTietKhuDoXe.giaId.id}">
                        <option value="${g.id}" selected>${g.gia}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${g.id}">${g.gia}</option>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </form:select>
    </div>
        <form:hidden path="id" />
        <button class="btn btn-success" type="submit">

            <c:choose>
                <c:when test="${ChiTietKhuDoXe.id != null}">
                    <option value="${c.id}" selected>Cập nhật chi tiết khu đỗ xe</option>
                </c:when>
                <c:otherwise>
                    Thêm chi tiết khu đỗ xe
                </c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>