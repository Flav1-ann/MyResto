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
                        <div class="col-lg-14">
                            <div class="text-center text-marginTOP h4">


                               <!-- <form method="get" action="summary" class="form-inline">
                                    <input class="form-control mr-sm-2" type="search" placeholder="N°Commande" name="searchid" aria-label="Search">
                                    <input type="hidden" name="search" value="1">
                                    <button class="btn btn-outline-info my-2 my-sm-0   btn btn-lg btn-circle btn-outline-new-white" type="submit">Rechercher</button>
                                </form> -->

                                    <form  method="get" action="summary"  class="form text-left">
                                        <label for="new">New</label>
                                        <input  type="radio" id="new" name="status" value="NEW">
                                        <label for="send">Send</label>
                                        <input type="radio" id="send" name="status" value="SEND">
                                        <label for="close">Close</label>
                                        <input type="radio" id="close" name="status" value="CLOSE">
                                        <input class="btn btn-outline-info my-2 my-sm-0   btn btn-lg btn-circle btn-outline-new-white" type="submit" value="Filter">
                                    </form>

                            </div>
                        </div>
                    </div>
                </div>
                <!-- List group-->
                <ul class="list-group shadow">
                    <%
                        UserDto user = ((UserDto) session.getAttribute("user"));
                        int i = 1;
                        String status = new String("");
                        try {
                            status = request.getParameter("status");
                        }catch (Exception e){}
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        for (OrderProductDto o : (List<OrderProductDto>) session.getAttribute("listOrders")) {
                            float price = 0.0f;

                        if ((status != null && status.equals(o.getStatus())) || status == null){
                    %>
                    <!-- list group item-->
                    <li class="list-group-item">
                        <!-- Custom content-->
                        <div class="media align-items-lg-center flex-column flex-lg-row p-3">
                            <div class="media-body order-2 order-lg-1">
                                <h5 class="mt-0 font-weight-bold mb-2 p-3 mb-2 bg-secondary text-white">Commande n°<%= i%>
                                        <%
                                 if (user.getRole().equals("ADMIN"))
                                     {
                            %>
                                    <h6 class="mt-0 font-weight-bold"><strong>Nom du client</strong> : <%= new UserService().getById(o.getIdUser()).getFirstName() +" "+new UserService().getById(o.getIdUser()).getLastName() %>

                                        <%
                                 }
                            %>

                                        <h6 class="mt-0 font-weight-bold mb-2"><strong>Status</strong> : <%= o.getStatus()%>
                                            <h6 class="mt-0 font-weight-bold mb-2"><strong>Date de la commande</strong>
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
                                            <h6 class="font-weight-bold my-2"><strong>Prix de la commande:</strong> <%= price%>€ </h6>
                                        </div>
                            <% }
                                 if (user.getRole().equals("ADMIN"))
                                     {%>
                                        <form class="col-14" action="updateOrder">
                                            <label for="status">Etat de la commande :</label>
                                            <select name="drop" id="status">
                                                <option value="" selected disabled> ---- </option>
                                                <% for(OrderProductDto.Status e :  OrderProductDto.Status.values()){ %>
                                                    <option  value="<%= e %>,<%= o.getId()%>"><%= e %></option>
                                                <% } %>
                                            </select>
                                            <div class="form_input col-14 ">
                                                <button href="updateOrder" class="btn btn-outline-info my-2 my-sm-0 btn-circle btn-outline-new-white" formmethod="POST" type="submit" id="button" name="button" value="">Modifier la commande</button>
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

