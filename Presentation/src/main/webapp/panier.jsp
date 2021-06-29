<%@ page import="eu.ensup.myresto.Product" %>
<%@ page import="java.util.Set" %>
<%--
  Created by IntelliJ IDEA.
  User: cherif
  Date: 29/06/2021
  Time: 09:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="header.jsp" %>
<!-- Start Panier -->
<div class="panel-body table-responsive">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Prénom</th>
            <th scope="col">Image</th>
            <th scope="col">Nom</th>
            <th scope="col">Description</th>
            <th scope="col">Prix</th>
        </tr>
        </thead>
        <tbody>
        <%
            for(Product p : (Set<Product>) session.getAttribute("listProduct")){
        %>
        <tr>
            <td><%= p.getId()%></td>
            <td><%= p.getPicture()%></td>
            <td><%= p.getName()%></td>
            <td><%= p.getDescription()%></td>
            <td><%=p.getPrice()%></td>
            <td>
                <ul class="action-list">
                    <%--<li><a href="EditerEtudiants.jsp" data-tip="edit"><i class="fa fa-edit"></i></a></li>--%>
                    <li><a href="delete?id=<%=p.getId()%>"><i class="fa fa-trash"></i></a></li>
                </ul>
            </td>
        </tr>
        <%
            }
        %>

        </tbody>
    </table>
</div>
<!-- End Panier -->
<%@include file="footer.jsp" %>
