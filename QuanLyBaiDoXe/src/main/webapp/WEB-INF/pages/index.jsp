<%-- 
    Document   : index
    Created on : Jul 13, 2024, 1:15:52 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-primary mt-1 content">DANH SÁCH NGƯỜI DÙNG</h1>
<c:if test="${errMsg != null}">
    <div class="alert alert-danger">
        ${errMsg}
    </div>
</c:if>
 <div id="errorMessage" class=""></div>

<!-- Main content -->
<section class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3 col-12 bg-secondary">
                <c:url value="/" var="action" />
                <form action="${action}">
                    <div class="mb-3 mt-3">
                        <label for="q" class="form-label">Tìm theo username</label>
                        <input type="search" class="form-control" id="q" placeholder="Nhập username" name="q">
                    </div>
                    <div class="mb-3 mt-3">
                        <label for="mail" class="form-label">Tìm theo email</label>
                        <input type="search" class="form-control" id="mail" placeholder="Nhập email" name="mail">
                    </div>

                    <div class="mb-3 mt-3">
                        <input type="submit" onclick="(e) => {
                                    e.preventDefault()
                                }" class="btn btn-success" value="Tìm" />
                    </div>
                </form>
            </div>
            <div class="col-md-9 col-12">
                <div class="d-flex  justify-content-between">
                    <a href="<c:url value="/user" />" class="btn btn-info mb-1"><i class="fa fa-plus fa-lg"></i></a>
                    <div class="d-flex">
                        <button id="prevPage" class="btn btn-primary" style="margin-right: 5px;">«</button>
                        <div id="pages" class="page-container overflow-auto text-nowrap">
                            <c:forEach begin="1" end="${usersData.totalUsers}" var="page">
                                <a href="<c:url value="?page=${page}" />" class="btn btn-secondary page-item">${page}</a>
                            </c:forEach>
                        </div>
                        <button id="nextPage" class="btn btn-primary" style="margin-left: 5px;">»</button>

                    </div>
                </div>
                <table class="table table-striped">
                    <tr>
                        <th>Avatar</th>
                        <th>Id</th>
                        <th>Username</th>
                        <th>Email</th>
                        <th>active</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${usersData.users}" var="p">
                        <tr id="user${p.id}">
                            <td>
                                <img width="80" src="${p.avatar}" alt="${p.username}" />
                            </td>
                            <td>${p.id}</td>
                            <td>${p.username}</td>
                            <td>${p.email}</td>
                            <td>
                                ${p.active}
                            </td>
                            <td>
                                <a href="<c:url value="/user/${p.id}" />" class="btn btn-info">&#128394;</a>

                                <c:url value="/api/user/${p.id}" var="endpoint" />
                                <button onclick="deleteClass('${endpoint}', 'user${p.id}')" class="btn btn-danger">&times;</button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div><!-- /.container-fluid -->
</section>
<!-- /.content -->
