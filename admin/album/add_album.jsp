<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script
            src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>


    <script src="resources/registration/js/bootstrap-datepicker.js"></script>
    <link rel="stylesheet" href="daterangepicker/daterangepicker.css">
    <script src="daterangepicker/moment.js"></script>
    <script src="daterangepicker/daterangepicker.js"></script>

    <script type="text/javascript"
            src="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.js"></script>
    <link rel="stylesheet" type="text/css"
          href="//cdn.jsdelivr.net/bootstrap.daterangepicker/2/daterangepicker.css"/>

    <meta charset="UTF-8">
    <title>Create event</title>
</head>

<body>
<div class="row h-100 justify-content-center align-items-center">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="container h-100">
            <h3>Add album</h3>
            <div class="row h-100 justify-content-center align-items-center">
                <div class="form-group">
                    <table class="table">
                        <form method="POST">
                            <tr>
                                <td>Name</td>
                                <td><input required placeholder="enter the name"
                                           title="only letters A-Z are available" pattern="^[a-zA-Z\s]+$"
                                           type="text" name="name"/>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><input name="submitedCreate"
                                                       class="btn btn-outline-dark btn-block" type="submit"
                                                       value="Add album"/></td>
                            </tr>
                            <tr>
                                <td colspan="2"><a class="btn btn-outline-dark btn-block" href="admin_album">Cancel</a></td>
                            </tr>
                        </form>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>