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
<div class="container">
    <div class="panel-body table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Prénom</th>
                <th scope="col">Nom</th>
                <th scope="col">E-mail</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>ter</td>
                <td>tre</td>
                <td>rez</td>
                <td>ezr</td>
                <td>ezr</td>
                <td>
                    <ul class="action-list">
                        <li><a href="delete?id=1"><i class="fa fa-trash"></i></a></li>
                    </ul>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<!-- End Panier -->
<%@include file="footer.jsp" %>
