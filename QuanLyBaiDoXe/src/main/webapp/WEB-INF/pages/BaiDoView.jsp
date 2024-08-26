<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<h1 class="text-center text-primary mt-1">DANH MỤC SẢN PHẨM</h1>
<div id="errorMessage" class=""></div>

<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
<c:if test="${successMessage != null}">
    <div class="alert alert-success">
        ${successMessage}
    </div>
</c:if>
<div class="row ml-2">
    <div class="col-md-3 col-12 bg-secondary">
        <c:url value="/baido" var="action" />
        <form action="${action}">
            <div class="mb-3 mt-3">
                <label for="q" class="form-label">Địa chỉ:</label>
                <input type="search" class="form-control" id="q" placeholder="Nhập địa chỉ..." name="q" value="${param.q}">
            </div>
            <div class="mb-3 mt-3">
                <label for="thoigiancua" class="form-label">Thời gian mở cửa từ:</label>
                <input type="datetime-local" class="form-control" id="thoigiancua" name="thoigiancua" value="${param.thoigiancua}">
            </div>
            <div class="mb-3 mt-3">
                <label for="thoigiandongcua" class="form-label">Thời gian đóng cửa đến:</label>
                <input type="datetime-local" class="form-control" id="thoigiandongcua" name="thoigiandongcua" value="${param.thoigiandongcua}">
            </div>
            <div class="mb-3 mt-3">
                <label for="sort" class="form-label">Sắp xếp theo:</label>
                <select  id="sort" name="sort" class="form-control" onchange="this.form.submit()">

                    <option value="">Chọn sắp xếp</option>
                    <option value="id" <c:if test="${param.sort == 'id'}">selected</c:if>>ID</option>
                    <option value="diachi" <c:if test="${param.sort == 'diachi'}">selected</c:if>>Địa chỉ</option>
                    </select>
                </div>
                <div class="mb-3 mt-3">
                    <input type="submit" class="btn btn-success" value="Tìm kiếm">
                </div>
            </form>
        </div>
        <div class="col-md-7 col-500">
            <div class="d-flex  justify-content-between">
                <a href="<c:url value="/baiDo" />" class="btn btn-info mb-1">Thêm bãi đổ</a>

            <div class="d-flex">
                <button id="prevPage" class="btn btn-primary" style="margin-right: 5px;">«</button>
                <div id="pages" class="page-container overflow-auto text-nowrap">
                    <c:forEach begin="1" end="${baiDoXe.totalBaidoxe}" var="page">
                        <a href="<c:url value="?page=${page}" />" class="btn btn-secondary page-item">${page}</a>
                    </c:forEach>
                </div>
                <button id="nextPage" class="btn btn-primary" style="margin-left: 5px;">»</button>

            </div>
        </div>
        <table class="table table-striped">
            <tr>
                <th>ID</th>
                <th>Địa chỉ</th>
                <th>Tên Bãi</th>
                <th>Thời Gian Mở Cửa</th>
                <th>Thời Gian Đóng Cửa</th>
                <th></th>
            </tr>
            <c:forEach items="${baiDoXe.Baidoxe}" var="p">
                <tr id="baiDo${p.id}">
                    <td>${p.id}</td>
                    <td>${p.diachi}</td>
                    <td>${p.tenBai}</td>
                    <td>
                        <p><fmt:formatDate value="${p.thoigiancua}" pattern="dd/MM/yyyy HH:mm:ss" /></p>
                    </td>
                    <td>
                        <p><fmt:formatDate value="${p.thoigiandongcua}" pattern="dd/MM/yyyy HH:mm:ss" /></p>
                    </td>
                    <td>
                        <c:url value="/baiDo/${p.id}" var="u" />
                        <a href="${u}" class="btn btn-success">&orarr;</a><!-- Cập nhật -->
                        <c:url value="/api/baiDo/${p.id}" var="endpoint" />
                        <button onclick="deleteBaiDo('${endpoint}', ${p.id})" class="btn btn-danger">&times;</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <!-- Phân trang -->

    </div>
</div>