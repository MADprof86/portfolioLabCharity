<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Admin profile</title>

    <!-- Custom fonts for this template-->
    <link href="/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link rel="stylesheet" href="<c:url value='/resources/css/sb-admin-2.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/sb-admin-2.min.css'/>"/>
    <script src="<c:url value='/resources/js/sb-admin-2.js'/>"></script>



</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <tags:admin-navbar-sidebar/>

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <tags:admin-navbar-topbar/>

            <!-- Begin Page Content -->
            <div class="container-fluid">
                <!-- Page Heading -->
                <div class="d-sm-flex align-items-center justify-content-between mb-4">
                    <h1 class="h3 mb-0 text-gray-800">Profil</h1>
                </div>

                    <form class="user">
                        <div class="form-group row">
                            <div class="col-sm-6 mb-6 mb-sm-0">
                                <input type="text" class="form-control form-control-user" id="userName" value="${user.username}">
                            </div>
                            <div class="col-sm-6 mb-6 mb-sm-0">
                                <select class="form-control form-control-user" id="roles" multiple>
                                    <c:forEach var="role" items="${roles}">
                                        <option value="${role.name}" <c:if test="${user.roles.contains(role)}">selected</c:if>>${role.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-6 mb-3 mb-sm0">
                                <input type="password" class="form-control form-control-user" id="password">
                            </div>
                            <div class="col-sm-6 mb-3 mb-sm0">
                                <input type="password" class="form-control form-control-user" id="passwordRepeat">
                            </div>
                        </div>
                        <div class="form-group row">
                            <div class="col-sm-12">
                                <button type="submit" class="btn btn-primary btn-user btn-block">
                                    Save Changes
                                </button>
                            </div>
                        </div>
                    </form>

                </div>



            </div>
            <!-- /.container-fluid -->
        <tags:admin-footer/>
        </div>
        <!-- End of Main Content -->

        <!-- Footer -->


    </div>
    <!-- End of Content Wrapper -->


<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Gotowy by opóścić panel?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Wybierz wyloguj by zakończyć sesję..</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Anuluj</button>
                <a class="btn btn-primary" href="/login">Wyloguj</a>
            </div>
        </div>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<!-- Bootstrap core JavaScript-->
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<script src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="/resources/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="/resources/vendor/chart.js/Chart.min.js"></script>

<!-- Page level custom scripts -->
<script src="/resources/js/demo/chart-area-demo.js"></script>
<script src="/resources/js/demo/chart-pie-demo.js"></script>

</body>

</html>