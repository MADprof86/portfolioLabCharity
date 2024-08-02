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
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/resources/css/sb-admin-2.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/resources/css/sb-admin-2.min.css'/>"/>
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css">
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
                <h1 class="h3 mb-2 text-gray-800">Instytucje współpracujące z serwisem</h1>
                <p class="mb-4"> Szczegóły instytucji

                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Assumenda atque cum deleniti doloremque ea est fuga, fugit ipsum minus numquam perferendis praesentium quas quia, quibusdam quis repellendus reprehenderit similique voluptatibus.</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                    <tr>
                                        <th>Nazwa</th>
                                        <th>Opis</th>
                                        <th>Przyjętych darowizn</th>
                                        <th>Akcje</th>

                                    </tr>
                                    </thead>

                                    <tbody>
                                    <c:forEach items="${institutions}" var="institution">
                                    <tr>
                                        <td>${institution.name}</td>
                                        <td>${institution.description}</td>
                                        <td>${donationsByInstitutions.get(institution)}</td>

                                        <td>
                                        <a href="delete" class="btn-danger">Usuń</a>
                                        <a href="edit" class="btn-warning">Edycja</a>

                                        </td>
                                    </c:forEach>
                                    </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                <form:form class="form" action="/index-admin/admin-institutions" method="post" modelAttribute="institution">
                    <div class="form-group row">
                        <div class="col-sm-6 mb-6 mb-sm-0">
                            <label>Nazwa fundacji</label>
                            <input type="text" class="form-control form-control-user" id="name" name="name" value="Nazwa fundacji">
                        </div>
                        <div class="col-sm-6 mb-6 mb-sm-0">
                            <label>Opis fundacji</label>
                            <textarea class="form-control form-control-user" id="description" name="description" ></textarea>

                        </div>
                    </div>

                    <div class="form-group row">
                        <div class="col-sm-12">
                            <button type="submit" class="btn btn-primary btn-user btn-block" >
                                Dodaj nową fundację
                            </button>
                        </div>
                    </div>
                </form:form>

                    </div>

                <tags:admin-footer/>
            </div>

        <!-- /.container-fluid -->
        </div>
    </div>
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


        <!-- DataTables JavaScript -->
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>

        <!-- Initialize DataTables -->
        <script>
            $(document).ready(function() {
                $('#dataTable').DataTable({
                    "pageLength": 10
                });
            });
        </script>

</body>

