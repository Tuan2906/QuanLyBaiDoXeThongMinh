<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<h1 class="text-center text-primary mt-1">QUẢN LÝ KHU ĐỖ XE</h1>
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
<c:if test="${messSucess != null}">
    <div class="alert alert-success">
        ${messSucess}
    </div>
</c:if>
 <div id="errorMessage" class=""></div>

<div class="row m-2">
    <div class="col-md-3 col-12 bg-secondary">
        <c:url value="/khudoxeView" var="action" />
         <form action="${action}" method="get">
            <div class="mb-3 mt-3">
                <label for="q" class="form-label">Ghi Chú:</label>
                <input type="search" class="form-control" id="tenDay" placeholder="Nhập tên khu vực..." name="tenDay" value="${param.tenDay}">
            </div>

            <div class="mb-3 mt-3">
                <label for="sort" class="form-label">Sắp xếp theo:</label>
                <select id="sort" name="sort" class="form-control" onchange="this.form.submit()">
                    <option value="">Chọn sắp xếp</option>
                    <option value="id" <c:if test="${param.sort == 'id'}">selected</c:if>>ID</option>
                    <option value="tenDay" <c:if test="${param.sort == 'tenDay'}">selected</c:if>>Tên khu vực</option>
                </select>
            </div>
                 <div class="mb-3 mt-3">
                <input type="submit" class="btn btn-success" value="Tìm kiếm">
            </div>
        </form>
    </div>
    <div class="col-md-7 col-500">
        <a href="<c:url value="/khuDoXe" />" class="btn btn-info mb-1">Thêm Khu do xe</a>
    <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage - 1}&amp;q=${param.q}&amp;thoigiancua=${param.thoigiancua}&amp;thoigiandongcua=${param.thoigiandongcua}&amp;sort=${param.sort}" aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item <c:if test="${i == currentPage}">active</c:if>">
                        <a class="page-link" href="?page=${i}&amp;q=${param.q}&amp;thoigiancua=${param.thoigiancua}&amp;thoigiandongcua=${param.thoigiandongcua}&amp;sort=${param.sort}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link" href="?page=${currentPage + 1}&amp;q=${param.q}&amp;thoigiancua=${param.thoigiancua}&amp;thoigiandongcua=${param.thoigiandongcua}&amp;sort=${param.sort}" aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </nav>
        <table class="table table-striped">
            <tr>
                <th>ID</th>
                <th>Tên khu</th>
                
                <th></th>
            </tr>
            <c:forEach items="${khuDoXe}" var="p">
                <tr id="khudo{p.id}">
                    <td>${p.id}</td>
                    <td>${p.tenDay}</td>
                    
                    <td>
                        <c:url value="/khuDo/${p.id}" var="k" />
                        <a href="${k}" class="btn btn-success">&orarr;</a><!-- Cập nhật -->
                        <c:url value="/api/khuDo/${p.id}" var="endpoint" />
                        <button onclick="deleteKhuDo('${endpoint}', ${p.id})" class="btn btn-danger">&times;</button>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <!-- Phân trang -->
        
    </div>
</div>
