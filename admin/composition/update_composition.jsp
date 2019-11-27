<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="utils.HibernateUtils" %>
<%@ page import="org.hibernate.resource.transaction.spi.TransactionStatus" %>
<%@ page import="model.Album" %>
<%@ page import="java.util.List" %>
<%@ page import="dao.AlbumDao" %>
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
    <title>Update composition</title>
</head>

<body>
<div class="row h-100 justify-content-center align-items-center">
    <div class="row h-100 justify-content-center align-items-center">
        <div class="container h-100">
            <h3>Update composition</h3>
            <div class="row h-100 justify-content-center align-items-center">
                <div class="form-group">
                    <table class="table">
                        <form method="POST">
                            <tr>
                                <td>Name</td>
                                <td><input required placeholder="enter the name"
                                           title="only letters A-Z are available" pattern="^[a-zA-Z\s]+$"
                                           type="text" name="name" value="<%=request.getParameter("name")%>"/>
                                    <input type="hidden" name="id" value="<%=request.getParameter("id")%>">
                                </td>
                            </tr>
                            <tr>
                                <td>Album:</td>
                                <td>
                                    <select name="album">
                                        <option disabled>Choose Album</option>
                                        <%
                                            SessionFactory factory = HibernateUtils.getSessionFactory();
                                            Session hibernateSession = factory.getCurrentSession();
                                            try {
                                                if(hibernateSession.getTransaction().getStatus().equals(TransactionStatus.NOT_ACTIVE))
                                                    hibernateSession.getTransaction().begin();
                                                List<Album> albums = AlbumDao.findAll(hibernateSession);
                                                for(Album a: albums) {
                                        %>
                                        <option><%=a.getName()%></option>
                                        <%
                                                }
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                hibernateSession.getTransaction().rollback();
                                            }
                                        %>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2"><input name="submitedUpdate"
                                                       class="btn btn-outline-dark btn-block" type="submit"
                                                       value="Update composition"/></td>
                            </tr>
                            <tr>
                                <td colspan="2"><a class="btn btn-outline-dark btn-block" href="admin_composition">Cancel</a></td>
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