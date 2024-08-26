<%-- 
    Document   : products
    Created on : Jul 20, 2024, 1:17:08 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1 class="text-center text-primary mt-1">QUẢN TRỊ NGƯỜI DÙNG</h1>
<c:url value="/user" var="action" />
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
<form:form method="post" action="${action}" modelAttribute="user" enctype="multipart/form-data" class="p-5">
    <c:if test="${user.id == null}">

        <form:hidden path="active" />
    </c:if>
        <form:hidden path="dateJoined" />

    <div class="mb-3 mt-3">
        <label for="username" class="form-label">Username</label>
        <form:input path="username" type="text" class="form-control" id="username" placeholder="Username" name="username" />
        <form:errors path="username" cssClass="text-danger" />

    </div>
    <div class="mb-3 mt-3  ">
        <label for="email" class="form-label">Email</label>
        <form:input path="email" type="text" class="form-control" id="email" placeholder="Email" name="email" />
        <form:errors path="email" cssClass="text-danger" />

    </div>
    <div class="mb-3 mt-3  input-group">
        <label for="password" class="form-label">Password</label>
        <div class="input-group">
            <form:input path="password" type="password" class="form-control" id="password" placeholder="Password" name="password" />
            <span class="input-group-text" id="togglePassword" style="cursor: pointer;" onclick="togglePassword()">
                <i class="fas fa-eye" id="eyeIcon"></i>
            </span>
        </div>
        <form:errors path="password" cssClass="text-danger" />

    </div>

    <div class="mb-3 mt-3  ">
        <label for="firstName" class="form-label">First name</label>
        <form:input path="firstName" type="text" class="form-control" id="firstName" placeholder="First name" name="firstName" />
    </div>

    <div class="mb-3 mt-3  ">
        <label for="lastName" class="form-label">Last name</label>
        <form:input path="lastName" type="text" class="form-control" id="lastName" placeholder="Last name" name="lastName" />
    </div>

    <div class="mb-3 mt-3  ">
        <label for="phone" class="form-label">Số điện thoại</label>
        <form:input path="phone" type="number" class="form-control" id="phone" placeholder="phone" name="phone" />
        <form:errors path="phone" cssClass="text-danger" />

    </div>
    <div class="mb-3 mt-3  ">
        <label for="userRole" class="form-label">Vai trò</label>
        <form:select path="userRole" id="userRole" class="form-select">
            <option value="ROLE_ADMIN" selected>Admin</option>
            <option value="ROLE_STAFF" selected>Nhân viên</option>
            <option value="ROLE_USER" selected>Người dùng</option>
        </form:select>
    </div>
    <c:if test="${user.id != null}">
        <div class="form-group">
            <label for="status">Trạng thái</label><br>
            <div class="form-check">
                <form:checkbox id="active" path="active" value="active" class="form-check-input" name="active"/>
                <label for="active" class="form-check-label">Active</label>
            </div>
        </div>
    </c:if>

    <div class="mb-3 mt-3  ">
        <label for="file" class="form-label">Avatar</label>
        <form:input path="file" accept=".png,.jpg" type="file" class="form-control" id="file" name="file" />
        <form:errors path="file" cssClass="text-danger" />
        <c:if test="${user.id != null}">
            <img src="${user.avatar}" alt="${user.username}" width="120" />
        </c:if>
    </div>

    <div class="mb-3 mt-3 ">
        <form:hidden path="id" />
        <form:hidden path="avatar" />
        <button type="submit" class="btn btn-success">
            <c:choose>
                <c:when test="${user.id != null}">Cập nhật</c:when>
                <c:otherwise> Thêm</c:otherwise>
            </c:choose>
        </button>
    </div>
</form:form>