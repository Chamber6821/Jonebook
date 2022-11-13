<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Jonebook</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.min.js" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.3/dist/umd/popper.min.js"
            integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.1.3/dist/js/bootstrap.min.js"
            integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
            crossorigin="anonymous"></script>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/css/bootstrap-select.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.14/dist/js/bootstrap-select.min.js"></script>

    <script src="index.js"></script>
    <script src="internal.js"></script>
</head>
<body>
<div class="card" style="position: fixed; bottom: 0; right: 0">
    <div class="card-body">
        <h5 class="card-title">Search</h5>
        <form id="search-form">
            <input id="nameFragment" class="form-group form-control" type="search" placeholder="Enter name fragment">
            <input id="emailFragment" class="form-group form-control" type="search" placeholder="Enter email fragment">
            <input id="phonePrefix" class="form-group form-control" type="search" placeholder="Enter phone starts">
            <input id="internalPhonePrefix" class="form-group form-control" type="search"
                   placeholder="Enter internal phone starts">
            <div class="form-group">
                <select id="departments"
                        class="form-control dropup"
                        data-dropup-auto="false"
                        data-live-search="true"
                        multiple data-actions-box="true"
                        title="Choose department variants">
                </select>
            </div>
            <div class="form-group">
                <select id="posts"
                        class="form-control dropup"
                        data-dropup-auto="false"
                        data-live-search="true"
                        multiple data-actions-box="true"
                        title="Choose positions held">
                </select>
            </div>
            <button class="btn btn-primary" type="submit">Search</button>
        </form>
    </div>
</div>
<div class="container">
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Jonebook</a>

        <ul class="pagination" style="margin: 0">
            <li id="previous-button" class="page-item">
                <a class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                    <span class="sr-only">Previous</span>
                </a>
            </li>
            <li id="current-page" class="page-item">
                <a class="page-link" href="#">
                    <span class="sr-only">(current)</span>
                </a>
            </li>
            <li id="next-button" class="page-item">
                <a class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                    <span class="sr-only">Next</span>
                </a>
            </li>
        </ul>

        <div>
            <sec:authorize access="hasRole('ADMIN')">
                <a class="btn btn-primary" href="$/edit">Edit</a>
            </sec:authorize>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </nav>

    <table id="employees" class="table table-hover table-bordered bg-light">
        <thead>
        <tr>
            <th>Id</th>
            <th>Full name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Internal phone</th>
            <th>Department</th>
            <th>Posts</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
</body>
</html>
