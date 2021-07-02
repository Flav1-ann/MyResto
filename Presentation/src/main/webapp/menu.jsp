<%@ page import="eu.ensup.myresto.Product" %>
<%@ page import="java.util.Set" %>
<%@ page import="eu.ensup.myresto.ProductDto" %>
<%@ page import="eu.ensup.myresto.CategoryService" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  eu.ensup.myresto.User: A
  Date: 28/06/2021
  Time: 14:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp" %>
<div class="wrapper">
    <div class="main-container">
        <div class="container">
            <div class="row inner-menu-box">
                <div class="col-3">
                    <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">

                        <a class="nav-link active" id="v-pills-All-tab" data-toggle="pill" href="#v-pills-All"
                           role="tab"
                           aria-controls="v-pills-All" aria-selected="true">Tout</a>
                        <a class="nav-link" id="v-pills-boisson-tab" data-toggle="pill" href="#v-pills-boisson"
                           role="tab"
                           aria-controls="v-pills-boisson" aria-selected="false">Boissons</a>
                        <a class="nav-link" id="v-pills-entrees-tab" data-toggle="pill" href="#v-pills-entrees"
                           role="tab"
                           aria-controls="v-pills-entrees" aria-selected="false">Entrées</a>
                        <a class="nav-link" id="v-pills-plats-tab" data-toggle="pill" href="#v-pills-plats" role="tab"
                           aria-controls="v-pills-plats" aria-selected="false">Plats</a>
                        <a class="nav-link" id="v-pills-desserts-tab" data-toggle="pill" href="#v-pills-desserts"
                           role="tab"
                           aria-controls="v-pills-desserts" aria-selected="false">Desserts</a>
                    </div>
                </div>

                <div class="col-9">
                    <div class="tab-content" id="v-pills-tabContent">
                        <div class="tab-pane fade show active" id="v-pills-All" role="tabpanel"
                             aria-labelledby="v-pills-All-tab">
                            <div class="row">
                                <%
                                    for (ProductDto p : (List<ProductDto>) session.getAttribute("listProducts")) {
                                %>
                                <div class="col-lg-4 col-md-6 special-grid " <%=new CategoryService().getById(p.getIdCategory()).getName()%>>
                                    <div class="gallery-single fix img-container">
                                        <img src="assets/images/<%=p.getPicture()%>" class="img-container-img img-product" 
                                             alt="<%= p.getName()%>" height="200">
                                        <div class="why-text">
                                            <h4><%= p.getName()%>
                                            </h4>
                                            <p><%= p.getDescription()%>
                                            </p>
                                            <h5>
                                                <div class="float-left"><%= p.getPrice()%>€</div>
                                                <% if (request.getSession().getAttribute("user") != null  && !((UserDto)request.getSession().getAttribute("user")).getRole().equals("ADMIN")) {%>
                                                <div class="float-right">
                                                    <form method="post" action="addToOrder">
                                                        <input type="hidden" id="productId" name="productId"
                                                               value="<%= p.getId() %>">
                                                        <button class="btn-add" type="submit"
                                                                formmethod="post" onclick="">
                                                            <span class="fa fa-shopping-cart"></span> Ajouter
                                                        </button>

                                                    </form>
                                                </div>
                                                <% }%>
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                                <%
                                    }
                                %>
                            </div>

                        </div>
                        <div class="tab-pane fade" id="v-pills-boisson" role="tabpanel"
                             aria-labelledby="v-pills-boisson-tab">
                            <div class="row">
                                <%
                                    for (ProductDto p : (List<ProductDto>) session.getAttribute("listBoisson")) {
                                %>

                                <div class="col-lg-4 col-md-6 special-grid <%=new CategoryService().getById(p.getIdCategory()).getName()%>">
                                    <div class="gallery-single fix img-container">
                                        <img src="assets/images/<%=p.getPicture()%>" class="img-container-img"
                                             alt="<%=p.getName()%>" height="200">
                                        <div class="why-text">
                                            <h4><%= p.getName()%>
                                            </h4>
                                            <p><%= p.getDescription()%>
                                            </p>
                                            <h5>
                                                <div class="float-left"><%= p.getPrice()%>€</div>
                                                <% if (request.getSession().getAttribute("user") != null  && !((UserDto)request.getSession().getAttribute("user")).getRole().equals("ADMIN")) {%>
                                                <div class="float-right">
                                                    <form method="post" action="addToOrder">
                                                        <input type="hidden" id="productId" name="productId"
                                                               value="<%= p.getId() %>">
                                                        <button class="btn-add" type="submit"
                                                                formmethod="post">
                                                            <span class="fa fa-shopping-cart"></span> Ajouter
                                                        </button>

                                                    </form>
                                                </div>
                                                <% }%>
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                                <%
                                    }
                                %>

                            </div>

                        </div>
                        <div class="tab-pane fade" id="v-pills-entrees" role="tabpanel"
                             aria-labelledby="v-pills-entrees-tab">
                            <div class="row">
                                <%
                                    for (ProductDto p : (List<ProductDto>) session.getAttribute("listEntree")) {
                                %>
                                <div class="col-lg-4 col-md-6 special-grid "<%=new CategoryService().getById(p.getIdCategory()).getName()%>>
                                    <div class="gallery-single fix img-container">
                                        <img src="assets/images/<%=p.getPicture()%>" class="img-container-img"
                                             alt="<%= p.getName()%>" height="200">
                                        <div class="why-text">
                                            <h4><%= p.getName()%>
                                            </h4>
                                            <p><%= p.getDescription()%>
                                            </p>
                                            <h5>
                                                <div class="float-left"><%= p.getPrice()%>€</div>
                                                <% if (request.getSession().getAttribute("user") != null  && !((UserDto)request.getSession().getAttribute("user")).getRole().equals("ADMIN")) {%>
                                                <div class="float-right">
                                                    <form method="post" action="addToOrder">
                                                        <input type="hidden" id="productId" name="productId"
                                                               value="<%= p.getId() %>">
                                                        <button class="btn-add" type="submit"
                                                                formmethod="post">
                                                            <span class="fa fa-shopping-cart"></span> Ajouter
                                                        </button>

                                                    </form>
                                                </div>
                                                <% }%>
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                                <%
                                    }
                                %>

                            </div>
                        </div>
                        <div class="tab-pane fade" id="v-pills-plats" role="tabpanel"
                             aria-labelledby="v-pills-plats-tab">
                            <div class="row">
                                <%
                                    for (ProductDto p : (List<ProductDto>) session.getAttribute("listPlat")) {
                                %>
                                <div class="col-lg-4 col-md-6 special-grid img-container"<%=new CategoryService().getById(p.getIdCategory()).getName()%>>
                                    <div class="gallery-single fix">
                                        <img src="assets/images/<%=p.getPicture()%>" class="img-container-img"
                                             alt="<%= p.getName()%>" height="200">
                                        <div class="why-text">
                                            <h4><%= p.getName()%>
                                            </h4>
                                            <p><%= p.getDescription()%>
                                            </p>
                                            <h5>
                                                <div class="float-left"><%= p.getPrice()%>€</div>
                                                <% if (request.getSession().getAttribute("user") != null  && !((UserDto)request.getSession().getAttribute("user")).getRole().equals("ADMIN")) {%>
                                                <div class="float-right">
                                                    <form method="post" action="addToOrder">
                                                        <input type="hidden" id="productId" name="productId"
                                                               value="<%= p.getId() %>">
                                                        <button class="btn-add" type="submit"
                                                                formmethod="post">
                                                            <span class="fa fa-shopping-cart"></span> Ajouter
                                                        </button>

                                                    </form>
                                                </div>
                                                <% }%>
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                                <%
                                    }
                                %>

                            </div>
                        </div>
                        <div class="tab-pane fade" id="v-pills-desserts" role="tabpanel"
                             aria-labelledby="v-pills-desserts-tab">
                            <div class="row">
                                <%
                                    for (ProductDto p : (List<ProductDto>) session.getAttribute("listDessert")) {
                                %>
                                <div class="col-lg-4 col-md-6 special-grid "<%=new CategoryService().getById(p.getIdCategory()).getName()%>>
                                    <div class="gallery-single fix img-container">
                                        <img src="assets/images/<%=p.getPicture()%>" class="img-container-img"
                                             alt="<%= p.getName()%>" height="200">
                                        <div class="why-text">
                                            <h4><%= p.getName()%>
                                            </h4>
                                            <p><%= p.getDescription()%>
                                            </p>
                                            <h5>
                                                <div class="float-left"><%= p.getPrice()%>€</div>
                                                <% if (request.getSession().getAttribute("user") != null && !((UserDto)request.getSession().getAttribute("user")).getRole().equals("ADMIN")) {%>
                                                <div class="float-right">
                                                    <form method="post" action="addToOrder">
                                                        <input type="hidden" id="productId" name="productId"
                                                               value="<%= p.getId() %>">
                                                        <button class="btn-add" type="submit"
                                                                formmethod="post">
                                                            <span class="fa fa-shopping-cart"></span> Ajouter
                                                        </button>

                                                    </form>
                                                </div>
                                                <% }%>
                                            </h5>
                                        </div>
                                    </div>
                                </div>
                                <%
                                    }
                                %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
    <div class="push"></div>
</div>
<!-- End Menu -->

<%@include file="footer.jsp" %>
</body>
</html>
