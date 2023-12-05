<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : admin
    Created on : Jul 6, 2023, 2:40:00 PM
    Author     : chi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Manage Admin</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <style>
            body {
                color: #404E67;
                background: #F5F7FA;
                font-family: 'Open Sans', sans-serif;
            }
            .table-wrapper {
                width: 700px;
                margin: 30px auto;
                background: #fff;
                padding: 20px;	
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title {
                padding-bottom: 10px;
                margin: 0 0 10px;
            }
            .table-title h2 {
                margin: 6px 0 0;
                font-size: 22px;
            }
            .table-title .add-new {
                float: right;
                height: 30px;
                font-weight: bold;
                font-size: 12px;
                text-shadow: none;
                min-width: 100px;
                border-radius: 50px;
                line-height: 13px;
            }
            .table-title .add-new i {
                margin-right: 4px;
            }
            table.table {
                table-layout: fixed;
            }
            table.table tr th, table.table tr td {
                border-color: #e9e9e9;
            }
            table.table th i {
                font-size: 13px;
                margin: 0 5px;
                cursor: pointer;
            }
            table.table th:last-child {
                width: 100px;
            }
            table.table td a {
                cursor: pointer;
                display: inline-block;
                margin: 0 5px;
                min-width: 24px;
            }    
            table.table td a.add {
                color: #27C46B;
            }
            table.table td a.edit {
                color: #FFC107;
            }
            table.table td a.delete {
                color: #E34724;
            }
            table.table td i {
                font-size: 19px;
            }
            table.table td a.add i {
                font-size: 24px;
                margin-right: -1px;
                position: relative;
                top: 3px;
            }    
            table.table .form-control {
                height: 32px;
                line-height: 32px;
                box-shadow: none;
                border-radius: 2px;
            }
            table.table .form-control.error {
                border-color: #f50000;
            }
            table.table td .add {
                display: none;
            }
        </style>

    </head>
    <body >
        <div class="container-lg">
            <div class="table-responsive" >
                <div class="table-wrapper" style="width: 55rem;">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-8"><h2>Category <b>Details</b></h2></div>
                            <div class="col-sm-4">
                                <a href="#AddNewCategory" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Category</span></a>
                            </div>
                            <a class="nav-link"  href="MainController?action=Home">Back To Home</a>
                        </div>
                    </div>
                    <table class="table table-bordered" style="width: 50rem;">
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Name</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="o" items="${list}">
                                <tr>
                                    <td>${o.id}</td>
                                    <td>${o.name}</td>
                                    <td>
                                        <a class="add" title="Add" data-toggle="tooltip"><i class="material-icons">&#xE03B;</i></a>
                                        <a href="EditCategory?id=${o.id}" class="edit" title="Edit" data-toggle="tooltip"><i class="material-icons">&#xE254;</i></a>
                                        <a  href="DeleteCategory?pid=${o.id}" class="delete" title="Delete" data-toggle="tooltip"><i class="material-icons">&#xE872;</i></a>
                                    </td>
                                </tr>

                            </c:forEach>


                        </tbody>
                    </table>
                </div>
            </div>
            <div id="AddNewCategory" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="AddNewCategory" method="GET">
                            <div class="modal-header">						
                                <h4 class="modal-title">Add Category</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Name</label>
                                    <input name="name" type="text" class="form-control" required>
                                </div>
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-success" value="Add">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div id="updateSchedule" class="modal fade">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="updateSchedule" method="POST">
                            <div class="modal-header">						
                                <h4 class="modal-title">Edit Schedule</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                            </div>
                            <div class="modal-body">					
                                <div class="form-group">
                                    <label>Id</label>
                                    <input type="text" class="form-control" required value="${id}"> 
                                </div>
                                <div class="form-group">
                                    <label>Course</label>
                                    <select name="course">
                                        <c:forEach var="o" items="${listCourse}">
                                            <option value="${o.id}" ${course == o.id ?"selected":""}>${o.id}</option>
                                        </c:forEach>

                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Class</label>
                                    <select name="class">
                                        <c:forEach var="o" items="${listClass}">
                                            <option value="${o.classId}" >${o.className}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Day Of Week</label>
                                    <input name="day" value="${day} "type="date" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Slot</label>
                                    <select name="slot">
                                        <c:forEach var="o" items="${listSlot}">
                                            <option value="${o.id}" ${slot == o.name?"selected":""}>Slot ${o.name} (${o.time})</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label>Location</label>
                                    <input value="${location}" name="location" type="text" class="form-control" required>
                                </div>
                                <div class="form-group">
                                    <label>Instructor</label>
                                    <input name="instructor" value="${teacher}" type="text" class="form-control" required>
                                </div>					
                            </div>
                            <div class="modal-footer">
                                <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                                <input type="submit" class="btn btn-info" value="Save">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>     
    </body>
</html>
