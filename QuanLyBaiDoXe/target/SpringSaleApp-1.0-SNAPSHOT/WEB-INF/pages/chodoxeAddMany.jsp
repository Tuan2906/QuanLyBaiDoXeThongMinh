 
Document   : products
Created on : Jul 20, 2024, 1:17:08 PM
Author     : admin


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-primary mt-1">QUẢN TRỊ CHỔ ĐỔ XE</h1>
<c:url value="/chodoxe/addMany" var="action" />
<c:if test="${not empty infoMessage}">
    <div class="alert alert-info">${infoMessage}</div>
</c:if>

<c:if test="${errEmailMsg != null}">
    <div class="alert alert-danger">
        ${errEmailMsg}
    </div>
</c:if>
<c:if test="${errPhoneMsg != null}">
    <div class="alert alert-danger">
        ${errPhoneMsg}
    </div>
</c:if>
<c:if test="${errUsernameMsg != null}">
    <div class="alert alert-danger">
        ${errUsernameMsg}
    </div>
</c:if>
<form:form method="post" action="${action}" modelAttribute="chodoxeMany" class="p-5">
    <button type="button"  class="btn btn-success" onclick="addRegion()">
        <i class="fa fa-plus fa-lg"></i>
    </button>
    <div id="regions">
        <div class="region">
            <div class="mb-3 mt-3">
                <label for="vitri" class="form-label">Vị trí</label>
                <form:input path="vitri" type="number" class="form-control" id="vitri" placeholder="Vị trí" name="vitri[0]" />
                <form:errors path="vitri" cssClass="text-danger" />

            </div>
            <div class="mb-3 mt-3  ">
                <label for="khoangcach" class="form-label">Khoảng cách</label>
                <form:input path="khoangCach" type="number" class="form-control" id="khoangCach" placeholder="Khoảng cách" name="khoangCach[0]" />
                <form:errors path="khoangCach" cssClass="text-danger" />
            </div>
            <hr/>
        </div>
    </div>



    <div class="mb-3 mt-3">
        <label for="baiDoXeid" class="form-label">Chọn bãi đổ xe</label>
        <form:select path="baiDoXeid" id="baiDoXeid" class="form-select" name="baiDoXeid">
            <c:forEach items="${baidoData.Baidoxe}" var="bdx">
                <c:choose>
                    <c:when test="${bdx.id == chodoxe.baiDoXeid.id}">
                        <option value="${bdx.id}" selected>${bdx.diachi}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${bdx.id}">${bdx.diachi}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>
    <div class="mb-3 mt-3">
        <label for="khuDoXeid" class="form-label">Chọn khu đổ xe</label>
        <form:select path="kdxId" id="khuDoXeid" class="form-select" name="kdxId">
            <c:forEach items="${khudoData}" var="kdx">
                <c:choose>
                    <c:when test="${kdx.id == chodoxe.khuDoXeid.id}">
                        <option value="${kdx.id}" selected>${kdx.tenDay}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${kdx.id}">${kdx.tenDay}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>
    <div class="mb-3 mt-3">
        <label for="khuDoXeid" class="form-label">Chọn Giá</label>
        <form:select path="giaId" id="khuDoXeid" class="form-select" name="giaId">
            <c:forEach items="${gia}" var="giaCho">
                <c:choose>
                    <c:when test="${giaCho.id == chodoxe.giaId}">
                        <option value="${giaCho.id}" selected>${giaCho.gia}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${giaCho.id}">${giaCho.gia}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>
    <div class="mb-3 mt-3">
        <label for="khuDoXeid" class="form-label">Chọn phương tiện</label>
        <form:select path="ptId" id="khuDoXeid" class="form-select" name="ptId">
            <c:forEach items="${phuongtien}" var="pt">
                <c:choose>
                    <c:when test="${pt.id == chodoxe.ptId}">
                        <option value="${pt.id}" selected>${pt.loai}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${pt.id}">${pt.loai}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>
    <div class="mb-3 mt-3">
        <label for="stateId" class="form-label">Chọn trạng thái</label>
        <form:select path="stateId" id="stateId" class="form-select" name="stateId">
            <c:forEach items="${stateData}" var="state">
                <c:choose>
                    <c:when test="${bdx.id == chodoxe.stateId.id}">
                        <option value="${state.id}" selected>${state.loai}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${state.id}">${state.loai}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </form:select>
    </div>

    <div class="mb-3 mt-3 ">
        <form:hidden path="id" />
        <button type="submit" class="btn btn-success">
            <c:choose>
                <c:when test="${chodoxe.id != null}">Cập nhật</c:when>
                <c:otherwise> Thêm</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>