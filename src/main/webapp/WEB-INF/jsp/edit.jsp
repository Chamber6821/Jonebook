<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jonebook: Edit</title>
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
    <script src="edit.js"></script>
</head>
<body>
<div class="container">
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Jonebook</a>

        <div>
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/">To search</a>
            <a class="btn btn-secondary" href="${pageContext.request.contextPath}/logout">Logout</a>
        </div>
    </nav>

    <div class="card">
        <div class="card-body">
            <h5 class="card-title">
                Edit employee with ID:
            </h5>
            <div>
                <input id="employee-id" type="number">
            </div>
            <form id="search-form">
                <button id="save" type="submit" class="btn btn-primary">Save</button>
                <div class="form-group">
                    <label for="name">Name</label>
                    <input id="name" class="form-control" type="text">
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input id="email" class="form-control" type="email">
                </div>
                <div class="form-group">
                    <label for="phone">Phone</label>
                    <input id="phone" class="form-control" type="text">
                </div>
                <div class="form-group">
                    <label for="internalPhone">Internal phone</label>
                    <input id="internalPhone" class="form-control" type="text">
                </div>
                <div class="form-group">
                    <label for="department">Department</label>
                    <select id="department"
                            class="form-control"
                            data-live-search="true">
                    </select>
                </div>
                <div class="form-group">
                    <label for="posts">Work posts</label>
                    <select id="posts"
                            class="form-control"
                            data-live-search="true"
                            multiple data-actions-box="true">
                    </select>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
