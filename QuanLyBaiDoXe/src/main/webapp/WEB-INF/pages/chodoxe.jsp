<%-- 
    Document   : index
    Created on : Jul 13, 2024, 1:15:52 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-primary mt-1 content">DANH SÁCH CHỔ ĐẬU XE</h1>
<div id="errorMessage" class=""></div>

<!-- Main content -->
<section class="content">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-3 col-12 bg-secondary">
                <c:url value="/chodoxe" var="action" />
                <form action="${action}">
                    <div class="mb-3 mt-3">
                        <label for="q" class="form-label">Tìm theo vị trí</label>
                        <input type="search" class="form-control" id="q" placeholder="Nhập vị trí" name="q">
                    </div>
                    <div class="mb-3 mt-3">
                        <label for="mail" class="form-label">Tìm theo khoảng cách</label>
                        <input type="search" class="form-control" id="khoangcach" placeholder="Nhập khoảng cách" name="khoangcach">
                    </div>

                    <div class="mb-3 mt-3">
                        <label for="baidoxe" class="form-label">Tìm theo Bãi đổ xe</label>
                        <select path="baiDoXeid" id="baidoxe" class="form-select" name="bdx">
                            <option value="">Tất cả</option>
                            <c:forEach items="${baidoData.Baidoxe}" var="bdx">
                                <option value="${bdx.id}">${bdx.diachi}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3 mt-3">
                        <label for="baidoxe" class="form-label">Tìm theo Khu đổ xe</label>
                        <select path="baiDoXeid" id="khudoxe" class="form-select" name="kdx">
                            <option value="">Tất cả</option>
                            <c:forEach items="${khudoData}" var="kdx">
                                <option value="${kdx.id}">${kdx.tenDay}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="mb-3 mt-3">
                        <label for="baidoxe" class="form-label">Tìm theo trạng thái</label>
                        <select path="baiDoXeid" id="state" class="form-select" name="state">
                            <option value="">Tất cả</option>

                            <c:forEach items="${stateData}" var="state">
                                <option value="${state.id}">${state.loai}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="mb-3 mt-3">
                        <input type="submit" onclick="(event) => event.preventDefault();" class="btn btn-success" value="Tìm" />
                    </div>
                </form>
            </div>
            <div class="col-md-9 col-12">
                <div class="d-flex  justify-content-between">
                    <span>
                        <a href="<c:url value="/chodoxe/add" />" class="btn btn-info mb-1"><i class="fa fa-plus fa-lg"></i></a>
                        <a href="<c:url value="/chodoxe/addMany" />" class="btn btn-info mb-1">Thêm nhiều chổ đổ</i></a>
                    </span>
                    
                    <div class="d-flex">
                        <button id="prevPage" class="btn btn-primary" style="margin-right: 5px;">«</button>
                        <div id="pages" class="page-container overflow-auto text-nowrap">
                            <c:forEach begin="1" end="${chodoData.totalChoDo}" var="page">
                                <a href="<c:url value="?page=${page}" />" class="btn btn-secondary page-item">${page}</a>
                            </c:forEach>
                        </div>
                        <button id="nextPage" class="btn btn-primary" style="margin-left: 5px;">»</button>

                    </div>
                </div>
                <table class="table table-striped">
                    <tr>
                        <th>Id</th>
                        <th>Vị trí</th>
                        <th>Khoảng cách</th>
                        <th>Bãi đổ xe</th>
                        <th>Khu đổ</th>
                        <th>Trạng thái</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${chodoData.chodo}" var="p">
                        <tr id="chodoxe${p.id}">

                            <td>${p.id}</td>
                            <td>${p.vitri}</td>
                            <td>${p.khoangCach}</td>
                            <td>${p.baiDoXeid.diachi}</td>
                            <td>${p.khuDoXeid.khuDoId.tenDay}</td>
                            <td>${p.stateId.loai}</td>
                            <td>
                                <a href="<c:url value="/chodoxe/${p.id}" />" class="btn btn-info">&#128394;</a>
                                <c:url value="/api/chodoxe/${p.id}" var="endpoint" />
                                <button onclick="deleteClass('${endpoint}', 'chodoxe${p.id}')" class="btn btn-danger">&times;</button>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div><!-- /.container-fluid -->
</section>
<!-- /.content -->
