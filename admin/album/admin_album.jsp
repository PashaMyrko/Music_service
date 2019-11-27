<%@ page import="dao.AlbumDao" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Album" %>
<%@ page import="org.hibernate.SessionFactory" %>
<%@ page import="utils.HibernateUtils" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="org.hibernate.resource.transaction.spi.TransactionStatus" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Albums</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <a class="navbar-brand" href="/">Music_service</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="<c:url value='/admin_album'/>">Albums</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/admin_composition'/>">Compositions</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Action
                    </a>
                    <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" href="<c:url value='/add_album'/>">Add new album</a>
                        <a class="dropdown-item" href="#">Sort by name</a>
                    </div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value='/logout' />">Logout</a>
                </li>
            </ul>
            <form class="form-inline my-2 my-lg-0">
                <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
            </form>
        </div>
    </nav>
    <div class="container">
        <c:if test="${not empty deleteError}">
            <div class="alert alert-danger" role="alert">
                    ${deleteError}
            </div>
        </c:if>
        <h1>List of albums:</h1>
        <table class="table">
            <%
                SessionFactory factory = HibernateUtils.getSessionFactory();
                Session hibernateSession = factory.getCurrentSession();
                try {
                    if(hibernateSession.getTransaction().getStatus().equals(TransactionStatus.NOT_ACTIVE))
                        hibernateSession.getTransaction().begin();
                    List<Album> albums = AlbumDao.findAll(hibernateSession);
                    if(albums != null) {
                        out.println("<tr><th scope=\"col\">#</th><th scope=\"col\">Name</th><th scope=\"col\">Action</th></tr>");
                        for(int i = 0; i < albums.size(); i++) {
                            %>
                            <tr>
                                <td><%=(i+1)%></td>
                                <td><%=albums.get(i).getName()%></td>
                                <td>
                                    <form action="admin_album_action" method="POST">
                                        <input type="hidden" name="id" value="<%=albums.get(i).getId()%>">
                                        <input type="hidden" name="name" value="<%=albums.get(i).getName()%>">
                                        <button class="btn btn-outline-secondary" name="list">Show composition list</button>
                                        <button class="btn btn-outline-warning" name="update">Edit</button>
                                        <button class="btn btn-outline-danger" onclick="return confirm('Do you want to delete?')" name="delete">Delete</button>
                                    </form>
                                </td>
                            </tr>
                <%
                        }
                    }
                    else {
                        out.println("<h1>Empty table</h1>");
                    }
                    hibernateSession.getTransaction().commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    hibernateSession.getTransaction().rollback();
                }
            %>
        </table>
    </div>
    <a class="btn-link" href=""></a>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
