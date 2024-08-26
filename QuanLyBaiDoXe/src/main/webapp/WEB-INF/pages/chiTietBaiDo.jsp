<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container mt-4">
    <h1 class="text-center text-primary mt-1">Chi Tiết Bãi Đỗ Xe</h1>
    <c:if test="${errorMessage != null}">
        <div class="alert alert-danger">
            ${errorMessage}
        </div>
    </c:if>
    <c:if test="${not empty timeError}">
        <div class="alert alert-danger">${timeError}</div>
    </c:if>
    <c:if test="${not empty duplicateError}">
        <div class="alert alert-danger">${duplicateError}</div>
    </c:if>
    <c:url value="/updateBaiDo" var="updateUrl" />
    ---<!-- Dung kiem tra xem chi tiet bai xe co du lieu khong -->
    <c:if test="${not empty chiTietBaiXe}">
        <!-- Form for updating Bãi Đỗ ID and Địa Chỉ -->
        <form:form action="${updateUrl}" method="post" modelAttribute="baiDoXeWrapper" enctype="multipart/form-data">
            <c:forEach var="detail" items="${chiTietBaiXe}" varStatus="status">
                <c:if test="${status.first}">
                    <div class="form-group">
                        <label for="id">Bãi Đỗ ID:</label>
                        <input id="id" name="baidoxe.id" class="form-control" value="${detail[0]}" readonly />
                    </div>
                    <div class="form-group">
                        <label for="diachi">Địa Chỉ:</label>
                        <input type="text" id="diachi" name="baidoxe.diachi" class="form-control" value="${detail[1]}" placeholder="Enter address" />
                    </div>
                    <div class="form-group">
                        <label for="diachi">Tên bãi: </label>
                        <input type="text" id="tenBai" name="baidoxe.tenBai" class="form-control" value="${detail[2]}" placeholder="Enter address" />
                    </div>
                    <div class="form-group">
                        <label for="thoigiancua">Thời Gian Mở Cửa:</label>
                        <input type="datetime-local" id="thoigiancua" name="baidoxe.thoigiancua" class="form-control" 
                               value="<fmt:formatDate value='${detail[3]}' pattern='yyyy-MM-dd\'T\'HH:mm'/>" 
                               placeholder="Enter opening time" />
                    </div>
                    <div class="form-group">
                        <label for="thoigiandongcua">Thời Gian Đóng Cửa:</label>
                        <input type="datetime-local" id="thoigiandongcua" name="baidoxe.thoigiandongcua" class="form-control" 
                               value="<fmt:formatDate value='${detail[4]}' pattern='yyyy-MM-dd\'T\'HH:mm'/>" 
                               placeholder="Enter closing time" />
                    </div>
                </c:if>
            </c:forEach>

            <table class="table table-striped mt-4">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Số Lượng Chỗ</th>
                        <th>Tên dãy-Giá Vé-Phương Tiện</th>                                                
                        <th>Ghi chú</th>                
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="detail" items="${chiTietBaiXe}" varStatus="status">
                        <tr>

                            <td>
                                ${detail[6]}
                            </td>
                            <td>${detail[5]}</td>
                            <td>
                                <select id="id-${status.index}" name="khuDoXeList[${status.index}].id" class="form-control">
                                    <c:forEach var="option" items="${chiTietKhuDoXe}">
                                        <c:set var="optionId" value="${option.id}" />
                                        <option value="${optionId}" <c:if test="${optionId == detail[6]}">selected</c:if>>
                                            ${option.khuDoId.tenDay} - ${option.giaId.gia} - ${option.phuongTienId.loai}
                                        </option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                ${detail[7]}
                                <input type="hidden" name="khuDoXeList[${status.index}].ghiChu" value="${detail[7]}" />
                                <input type="hidden" name="khuDoXeList[${status.index}].oldDetailId" value="${detail[6]}" />

                            </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>

            <div class="form-group text-right">
                <form:hidden path="baidoxe.id" />

                <button type="submit" class="btn btn-primary">Cập nhật</button>
            </div>
        </form:form>

    </c:if>
</div>
