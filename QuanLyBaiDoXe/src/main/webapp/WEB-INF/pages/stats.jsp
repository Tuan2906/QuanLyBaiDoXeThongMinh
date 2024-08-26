<%-- 
    Document   : stats
    Created on : Jul 22, 2024, 8:36:45 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<h1 class="text-center text-primary mt-1">THỐNG KÊ DOANH THU</h1>
<c:url value="/stats" var="action" />
<style>
    .highlight-row {
        background-color: red;
        color:white;
    }
</style>
<form action="${action}">
    <div class="mb-3 mt-3 ml-3">
        <label for="q" class="form-label">Tìm theo tháng</label>
        <select  path="month" id="baidoxe" class="form-select" name="month" onchange="this.form.submit()">
            <option value="">Tất cả</option>
            <c:forEach begin="1" end="12" step="1" var="m">
                <option value="${m}">${m}</option>
            </c:forEach>
        </select>
    </div>
</form>
<div class="row ml-2">
    <div class="col-md-5 col-12">
        <table class="table">
            <tr>
                <th>Địa chỉ</th>
                <th>Tên Bãi</th>
                <th>Tổng Tiền</th>
                <th>Số lượng</th>

            </tr>


            <c:forEach items="${stats}" varStatus="status" var="r">
                <tr class="${status.first && r[2]>0 ? 'highlight-row' : ''}">
                    <td>${r[0]}</td>
                    <td>${r[1]}</td>
                    <td>
                        <c:choose>
                            <c:when test="${r[2] == null}">
                                0 VNĐ
                            </c:when>
                            <c:otherwise>
                                ${String.format("%,.2f", r[2])} VNĐ
                            </c:otherwise>
                        </c:choose>
                    </td> 
                    <td>${r[3]}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-7 col-12">
        <canvas id="myChart"></canvas>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script>
            let labels = [];
            let data = [];
    <c:forEach items="${stats}" var="r">
            labels.push('${r[1]}');
            data.push(${r[2]});
    </c:forEach>

            window.onload = function () {
                const ctx = document.getElementById('myChart');

                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: labels,
                        datasets: [{
                                label: '# Doanh thu',
                                data: data,
                                borderWidth: 1
                            }]
                    },
                    options: {
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });

            }
</script>

