<%@ page import="eu.ensup.myresto.UserDto" %><%--
  Created by IntelliJ IDEA.
  eu.ensup.myresto.User: A
  Date: 28/06/2021
  Time: 14:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  eu.ensup.myresto.User: A
  Date: 28/06/2021
  Time: 11:38
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html class="head" lang="en"><!-- Basic -->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Mobile Metas -->
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Site Metas -->
    <title>My Resto</title>
    <meta name="keywords" content="">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Site Icons -->
    <link rel="shortcut icon" href="assets/images/favicon.ico" type="image/x-icon">
    <link rel="apple-touch-icon" href="assets/images/fa fa-shopping-cart">

    <!-- all CSS -->
    <link href="assets/css/orders.css" rel="stylesheet" type="text/css">
    <link href="assets/css/animate.css" rel="stylesheet" type="text/css">
    <link href="assets/css/baguetteBox.min.css" rel="stylesheet" type="text/css">
    <link href="assets/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link href="assets/css/classic.css" rel="stylesheet" type="text/css">
    <link href="assets/css/classic.date.css" rel="stylesheet" type="text/css">
    <link href="assets/css/classic.time.css" rel="stylesheet" type="text/css">
    <link href="assets/css/custom.css" rel="stylesheet" type="text/css">
    <link href="assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="assets/css/responsive.css" rel="stylesheet" type="text/css">
    <link href="assets/css/style.css" rel="stylesheet" type="text/css">
    <link href="assets/css/superslides.css" rel="stylesheet" type="text/css">
</head>
<body class="body">

<!-- Start header -->
<header class="top-navbar">
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="accueil">
                <img id="logo" src="assets/images/logo.png" alt="logo MyResto" height="100" width="100"/>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbars-rs-food"
                    aria-controls="navbars-rs-food" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbars-rs-food">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item "><a class="nav-link" href="accueil">Accueil</a></li>
                    <li class="nav-item "><a class="nav-link" href="menu">Carte</a></li>
                    <% if (request.getSession().getAttribute("user") != null) {
                        if (((UserDto) request.getSession().getAttribute("user")).getRole().equals("ADMIN")) {
                    %>
                        <li class="nav-item"><a class="nav-link" href="summary">Récapitulatif des commandes clients</a></li>
                    <% } else { %>
                        <li class="nav-item"><a class="nav-link" href="panier"> <icon src="assets"></icon> Panier</a></li>
                        <li class="nav-item"><a class="nav-link" href="orders">Commandes</a></li>
                        <% }%>
                    <li class="nav-item"><a class="nav-link" href="disconnect">Déconnexion</a></li>

                    <% } else { %>
                    <li class="nav-item "><a class="nav-link" href="login">Connexion</a></li>
                    <% } %>
                </ul>
            </div>
        </div>
    </nav>
</header>
<!-- End header -->