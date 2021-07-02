<%@ page import="java.util.*" %>
<%@ page import="java.util.List" %>
<%@ page import="eu.ensup.myresto.ProductService" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="eu.ensup.myresto.*" %><%--
<%--
  Created by IntelliJ IDEA.
  User: A
  Date: 29/06/2021
  Time: 11:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@include file="/header.jsp" %>
<div class="wrapper">
    <div class="reservation-box">

    </div>

    <div class="container">

        <div class="row">

            <div class="col-lg-8 mx-auto">
                <div class="container">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="text-center text-marginTOP">
                                <h1>Mes commandes</h1>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- List group-->
                <ul class="list-group shadow">
                    <%
                        UserDto user = ((UserDto) session.getAttribute("user"));
                        int i = 1;
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        for (OrderProductDto o : (List<OrderProductDto>) session.getAttribute("listOrders")) {
                            float price = 0.0f;

                    %>
                    <!-- list group item-->
                    <li class="list-group-item">
                        <!-- Custom content-->
                        <div class="media align-items-lg-center flex-column flex-lg-row p-3">
                            <div class="media-body order-2 order-lg-1">
                                <h5 class="mt-0 font-weight-bold mb-2">Commande n°<%= i%>
                                        <%
                                 if (user.getRole().equals("ADMIN"))
                                     {
                            %>
                                    <h6 class="mt-0 font-weight-bold mb-2">Nom du client : <%= new UserService().getById(o.getIdUser()).getFirstName() +" "+new UserService().getById(o.getIdUser()).getLastName() %>

                                        <%
                                 }
                            %>

                                    <h6 class="mt-0 font-weight-bold mb-2">Status : <%= o.getStatus()%>
                                        <h6 class="mt-0 font-weight-bold mb-2">Date de la commande
                                            : <%= o.getDateCreated().toLocalDate().format(formatter)%>
                                        </h6>
                                            <%
                              List<ProductDto> productDtos = new ArrayList<ProductDto>();
                            Map<Integer,Integer> productList = new HashMap<Integer,Integer>();
                                for (Integer p : (List<Integer>) o.getIdProduct()) {
                                    ProductDto productDto = new ProductService().getOneProduct(p);
                                    int currId = productDto.getId();
                                    if(productList.get(currId) == null){
                                        productList.put(currId,1);
                                    } else {
                                        productList.put(productDto.getId(),productList.get(currId)+1);
                                    }
                                  productDtos.add(productDto);
                                }
                                 for(Map.Entry<Integer,Integer> entry : productList.entrySet()){

                                     ProductDto productDto = new ProductDto();

                                     for(ProductDto productDto1 : productDtos){
                                         if( productDto1.getId() == entry.getKey()) {
                                             productDto = productDto1;
                                         }
                                     }

                                          price += productDto.getPrice() * entry.getValue();
                            %>
                                        <h5><%= entry.getValue() %>x - <%= productDto.getName() %>
                                        </h5>
                                        <p class="font-italic text-muted  small"><%= productDto.getDescription() %>
                                        </p>


                                        <div class="d-flex align-items-center justify-content-between mt-1">
                                            <h6 class="font-weight-bold my-2">Prix de la commande: <%= price%>€ </h6>
                                        <h6> role <% user.getRole();%></h6>
                                        </div>
                            <% }
                                 if (user.getRole().equals("ADMIN"))
                                     {%>
                                        <form class="col-12" action="updateOrder">
                                            <label for="status">Etat de la commande:</label>
                                            <select name="drop" id="status">
                                                <option value="" selected disabled> ---- </option>
                                                <% for(OrderProductDto.Status e :  OrderProductDto.Status.values()){ %>
                                                    <option  value="<%= e %>,<%= o.getId()%>"><%= e %></option>
                                                <% } %>
                                            </select>
                                            <div class="form_input col-12 ">
                                                <button href="updateOrder" class="btn btn-primary submit-btn" formmethod="POST" type="submit" id="button" name="button" value="">Modifier la commande</button>
                                            </div>
                                        </form>

                                    <% }%>
                            </div>

                        </div>
                        <!-- End -->
                    </li>
                    <%
                            i++;
                        }
                    %>

                </ul>
                <!-- End -->
            </div>
        </div>
    </div>
    <div class="push"></div>
</div>


<%@include file="/footer.jsp" %>

