<%--
  Created by IntelliJ IDEA.
  User: IRKAN
  Date: 10/9/13
  Time: 8:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge, chrome=1" />
    <meta name="description" content="JavaScript desktop environment built with jQuery." />
    <title>jQuery Desktop</title>
    <style>
        #bar_top{
            height: 36px !important;
            padding-top: 10px !important;
        }
        #desktop{
            top: 46px !important;
        }

        #bar_top form { margin: 0 20px; position: relative }
        #bar_top form input {
            -webkit-border-radius: 3px;
            -moz-border-radius: 3px;
            -ms-border-radius: 3px;
            -o-border-radius: 3px;
            border-radius: 3px;
            -webkit-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;
            -moz-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;
            -ms-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;
            -o-box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;
            box-shadow: 0 1px 0 #fff, 0 -2px 5px rgba(0,0,0,0.08) inset;
            -webkit-transition: all 0.5s ease;
            -moz-transition: all 0.5s ease;
            -ms-transition: all 0.5s ease;
            -o-transition: all 0.5s ease;
            transition: all 0.5s ease;
            border: 1px solid #c8c8c8;
            color: #777;
            font: 13px Helvetica, Arial, sans-serif;
            margin: 0 0 10px;
            padding: 3px 2px 3px 25px;
            width: 125px;
        }
        #bar_top form input[type="text"] {
            background: #eae7e7 url('assets/images/icons/1_1.png') no-repeat;
        }
        #bar_top form input[type="password"] {
            background: #eae7e7 url('assets/images/icons/2_2.png') no-repeat;
        }
        #bar_top form input:focus {
            -webkit-box-shadow: 0 0 2px #0078de inset;
            -moz-box-shadow: 0 0 2px #0078de inset;
            -ms-box-shadow: 0 0 2px #0078de inset;
            -o-box-shadow: 0 0 2px #0078de inset;
            box-shadow: 0 0 2px #0078de inset;
            background-color: #fff;
            border: 1px solid #0078de;
            outline: none;
        }
        #bar_top form input[type="text"]:focus {
            background: #eae7e7 url('assets/images/icons/1.png') no-repeat;
        }
        #bar_top form input[type="password"]:focus {
            background: #eae7e7 url('assets/images/icons/2.png') no-repeat;
        }
    </style>
    <!--[if lt IE 7]>
    <script>
        window.top.location = 'http://desktop.sonspring.com/ie.html';
    </script>
    <![endif]-->
    <link rel="stylesheet" href="assets/css/reset.css" />
    <link rel="stylesheet" href="assets/css/desktop.css" />
    <!--[if lt IE 9]>
    <link rel="stylesheet" href="assets/css/ie.css" />
    <![endif]-->
</head>
<body>
<div class="abs" id="wrapper">
    <div class="abs" id="desktop">
    </div>
    <div class="abs" id="bar_top">
        <form method="post" action="ls?action=login" class="float_right">
            <input type="text" id="username" name="username" placeholder="İstifadəçi adı">
            <input type="password" id="password" name="password" placeholder="Şifrə">
            <input type="submit" value="Daxil Ol" style="padding: 2px; width: 76px;">
        </form>
        <ul>
            <li>
                <a class="menu_trigger" href="#">Haqqımızda</a>
                <ul class="menu">
                    <li>
                        <a href="http://diveintohtml5.info/">Dive Into HTML5</a>
                    </li>
                    <li>
                        <a href="http://www.alistapart.com/articles/get-ready-for-html-5/">Get Ready for HTML5</a>
                    </li>
                    <li>
                        <a href="http://html5boilerplate.com/">HTML5 Boilerplate</a>
                    </li>
                    <li>
                        <a href="http://html5doctor.com/">HTML5 Doctor</a>
                    </li>
                    <li>
                        <a href="http://html5.org/">HTML5 Intro</a>
                    </li>
                    <li>
                        <a href="http://www.zeldman.com/superfriends/">HTML5 Super Friends</a>
                    </li>
                </ul>
            </li>
            <li>
                <a class="menu_trigger" href="#">Kömək</a>
            </li>
            <li>
                <a class="menu_trigger" href="#">Şifrənizi unutmusunuz?</a>
            </li>
            <li>
                <a class="menu_trigger" href="#">Məni xatırla</a>
            </li>
            <li>
                <input type="checkbox">
            </li>
        </ul>
    </div>
</div>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.16/jquery-ui.min.js"></script>
<script>
    !window.jQuery && document.write(unescape('%3Cscript src="assets/js/jquery.js"%3E%3C/script%3E'));
    !window.jQuery.ui && document.write(unescape('%3Cscript src="assets/js/jquery.ui.js"%3E%3C/script%3E'));
</script>
<script src="assets/js/jquery.desktop.js"></script>
<script>
    var _gaq = [['_setAccount', 'UA-166674-8'], ['_trackPageview']];

    (function(d, t) {
        var g = d.createElement(t),
                s = d.getElementsByTagName(t)[0];
        g.async = true;
        g.src = '//www.google-analytics.com/ga.js';
        s.parentNode.insertBefore(g, s);
    })(this.document, 'script');
</script>
</body>
</html>