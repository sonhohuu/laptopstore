<%-- 
    Document   : update
    Created on : July 6, 2023, 12:03:43 PM
    Author     : chi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TechShop</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link href="admin.css" rel="stylesheet" type="text/css">
    </head>
    <body>
            <div class="modal-dialog">
                <div class="modal-content">
                    <form action="EditProduct" method="POST">
                        <div class="modal-header">						
                            <h4 class="modal-title">Edit Product</h4>
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        </div>
                        <div class="modal-body">
                            <div class="form-group">
                                <label>Id</label>
                                <input readonly="" type="text" name="id" class="form-control" required value="${o.id}"> 
                            </div>
                            <div class="form-group">
                                <label>Name</label>
                                <input name="name" value="${o.name}" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Image Link</label>
                                <input name="image" value="${o.image}" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Price</label>
                                <input name="price" value="${o.price}" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Stock</label>
                                <input name="stock"  value="${o.quantity}" type="text" class="form-control" required>
                            </div>
                            <div class="form-group">
                                <label>Description</label>
                                <input name="description" value="${o.description}" type="text" class="form-control" required>
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
</body>
</html>
