<%-- 
    Document   : index
    Created on : Apr 10, 2020, 11:38:43 PM
    Author     : Roshan Withanage
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Code Complexity Measuring Tool</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


    </head>
    <body>
        <div class="content">
            <div class="container-fluid">
                <div class="col-md-12">


                    <a href="index.jsp" type="button">Home</a>
                        <h3>-----Size-----</h3>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Line No</th>
                                    <th scope="col">Program Statements</th>
                                    <th scope="col">Nkw</th>
                                    <th scope="col">Nid</th>
                                    <th scope="col">Nop</th>
                                    <th scope="col">Nnv</th>
                                    <th scope="col">Nsl</th>
                                    <th scope="col">Cs</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="sizeModelList" items="${SIZE_MODEL_LIST}">
                                    <tr>
                                        <td>${sizeModelList.getIndex()}</td>
                                        <td>${sizeModelList.getProgramStatements()}</td>
                                        <td>${sizeModelList.getNkw()}</td>
                                        <td>${sizeModelList.getNid()}</td>
                                        <td>${sizeModelList.getNop()}</td>
                                        <td>${sizeModelList.getNnv()}</td>
                                        <td>${sizeModelList.getNsl()}</td>
                                        <td>${sizeModelList.getCs()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>

                        <h3>-----Variable-----</h3>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Line No</th>
                                    <th scope="col">Program Statements</th>
                                    <th scope="col">Wvs</th>
                                    <th scope="col">Npdtv</th>
                                    <th scope="col">Ncdtv</th>
                                    <th scope="col">Cv</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="variableList" items="${VARIABLE_LIST}">
                                    <tr>
                                        <td>${variableList.getIndex()}</td>
                                        <td>${variableList.getProgramStatements()}</td>
                                        <td>${variableList.getWvs()}</td>
                                        <td>${variableList.getNpdtv()}</td>
                                        <td>${variableList.getNcdtv()}</td>
                                        <td>${variableList.getCv()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <h3>-----Method-----</h3>
                        <table class="table table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">Line No</th>
                                    <th scope="col">Program Statements</th>
                                    <th scope="col">Wmrt</th>
                                    <th scope="col">Npdtp</th>
                                    <th scope="col">Ncdtp</th>
                                    <th scope="col">Cm</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="methodList" items="${METHOD_LIST}">
                                    <tr>
                                        <td>${methodList.getIndex()}</td>
                                        <td>${methodList.getProgramStatements()}</td>
                                        <td>${methodList.getWmrt()}</td>
                                        <td>${methodList.getNpdtp()}</td>
                                        <td>${methodList.getNcdtp()}</td>
                                        <td>${methodList.getCm()}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                </div>
            </div>
        </div>

    </body>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js" type="text/javascript"></script>
</html>
