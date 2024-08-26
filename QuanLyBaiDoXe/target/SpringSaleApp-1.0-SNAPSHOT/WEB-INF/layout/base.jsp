<%-- 
    Document   : base
    Created on : Jul 21, 2024, 1:53:33 PM
    Author     : quang
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>
            <tiles:insertAttribute name="title" />
        </title>

        <link rel="stylesheet" href="<c:url value="/plugins/fontawesome-free/css/all.min.css"/>">

        <link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">

        <link rel="stylesheet" href="<c:url value="/plugins/tempusdominus-bootstrap-4/css/tempusdominus-bootstrap-4.min.css"/>">

        <link rel="stylesheet" href="<c:url value="/plugins/icheck-bootstrap/icheck-bootstrap.min.css"/>">

        <link rel="stylesheet" href="<c:url value="/plugins/jqvmap/jqvmap.min.css"/>">

        <link rel="stylesheet" href="<c:url value="/dist/css/adminlte.min.css"/>">

        <link rel="stylesheet" href="<c:url value="/plugins/overlayScrollbars/css/OverlayScrollbars.min.css"/>">

        <link rel="stylesheet" href="<c:url value="/plugins/daterangepicker/daterangepicker.css"/>">

        <link rel="stylesheet" href="<c:url value="/plugins/summernote/summernote-bs4.css"/>">

        <link href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700" rel="stylesheet">

        
        
        <script src="<c:url value='/plugins/jquery/jquery.min.js'/>"></script>

        <script src="<c:url value='/plugins/jquery-ui/jquery-ui.min.js'/>"></script>

        <script>
            $.widget.bridge('uibutton', $.ui.button)
        </script>

        <script src="<c:url value='/plugins/bootstrap/js/bootstrap.bundle.min.js'/>"></script>

        <script src="<c:url value='/plugins/chart.js/Chart.min.js'/>"></script>

        <script src="<c:url value='/plugins/sparklines/sparkline.js'/>"></script>

        <script src="<c:url value='/plugins/jqvmap/jquery.vmap.min.js'/>"></script>
        <script src="<c:url value='/plugins/jqvmap/maps/jquery.vmap.usa.js'/>"></script>

        <script src="<c:url value='/plugins/jquery-knob/jquery.knob.min.js'/>"></script>

        <script src="<c:url value='/plugins/moment/moment.min.js'/>"></script>
        <script src="<c:url value='/plugins/daterangepicker/daterangepicker.js'/>"></script>

        <script src="<c:url value='/plugins/tempusdominus-bootstrap-4/js/tempusdominus-bootstrap-4.min.js'/>"></script>

        <script src="<c:url value='/plugins/summernote/summernote-bs4.min.js'/>"></script>

        <script src="<c:url value='/plugins/overlayScrollbars/js/jquery.overlayScrollbars.min.js'/>"></script>

        <script src="<c:url value='/dist/js/adminlte.js'/>"></script>

        <script src="<c:url value='/dist/js/pages/dashboard.js'/>"></script>

        <script src="<c:url value='/dist/js/demo.js'/>"></script>




        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
        <script src="<c:url value="/js/main.js" />" defer></script>
<!--        <script src="<c:url value="/js/user.js"/>"></script>-->


    </head>
    <body>
        <tiles:insertAttribute name="header" />
        <div class="hold-transition sidebar-mini layout-fixed">
            <tiles:insertAttribute name="aside" />
            <div class="content-wrapper">
                 <tiles:insertAttribute name="content" />
            </div>
        </div>
        <tiles:insertAttribute name="footer" />
    </body>    
</html>
