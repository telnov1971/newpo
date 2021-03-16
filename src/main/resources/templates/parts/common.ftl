<#macro page>

<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8" />
    <title>Личный кабинет</title>
    <!-- base href="/newpo/"-->
    <base href="/">
    <link rel="stylesheet" href="static/css/style.css" />

    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />

    <link rel="stylesheet" href="static/css/all.css">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="static/css/bootstrap.min.css">
    <link rel="stylesheet" href="static/css/hiden_show.css">
    <script src="static/js/jquery.js"></script>
    <style>
        .turbolinks-progress-bar {
            height: 2px;
            background-color: #000080;
        }
    </style>
</head>
<body>
<#include "navbar.ftl">
<div class="container mt-5">
<#nested>
</div>
<script src="static/js/bootstrap.min.js" ></script>
</body>
</html>
</#macro>
