<%-- 
    Document   : index
    Created on : Apr 10, 2020, 11:38:43 PM
    Author     : Roshan Withanage
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Code Complexity Measuring Tool</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <style>
            @import url(https://fonts.googleapis.com/css?family=Open+Sans:700,300);

            .frame {
                position: absolute;
                top: 50%;
                left: 50%;
                width: 400px;
                height: 400px;
                margin-top: -200px;
                margin-left: -200px;
                border-radius: 2px;
                box-shadow: 4px 8px 16px 0 rgba(0, 0, 0, 0.1);
                overflow: hidden;
                background: linear-gradient(to top right, darkmagenta 0%, hotpink 100%);
                color: #333;
                font-family: "Open Sans", Helvetica, sans-serif;
            }

            .center {
                position: absolute;
                top: 50%;
                left: 50%;
                transform: translate(-50%, -50%);
                width: 300px;
                height: 260px;
                border-radius: 3px;
                box-shadow: 8px 10px 15px 0 rgba(0, 0, 0, 0.2);
                background: #fff;
                display: flex;
                align-items: center;
                justify-content: space-evenly;
                flex-direction: column;
            }

            .title {
                width: 100%;
                height: 50px;
                border-bottom: 1px solid #999;
                text-align: center;
            }

            h1 {
                font-size: 16px;
                font-weight: 300;
                color: #666;
            }

            .dropzone {
                width: 100px;
                height: 80px;
                border: 1px dashed #999;
                border-radius: 3px;
                text-align: center;
            }

            .upload-icon {
                margin: 25px 2px 2px 2px;
            }

            .upload-input {
                position: relative;
                top: -62px;
                left: 0;
                width: 100%;
                height: 100%;
                opacity: 0;
            }

            .btn {
                display: block;
                width: 140px;
                height: 40px;
                background: darkmagenta;
                color: #fff;
                border-radius: 3px;
                border: 0;
                box-shadow: 0 3px 0 0 hotpink;
                transition: all 0.3s ease-in-out;
                font-size: 14px;
            }

            .btn:hover {
                background: rebeccapurple;
                box-shadow: 0 3px 0 0 deeppink;
            }

        </style>

    </head>
    <body>
        <div class="frame">
            <div class="center">
                <form action="MainServlet" method="post" enctype="multipart/form-data">                    
                    <div class="title">
                        <h1>Drop file to upload</h1>
                    </div>

                    <div class="dropzone">
                        <img src="http://100dayscss.com/codepen/upload.svg" class="upload-icon" />
                        <input type="file" name="filePath" id="filePath" class="upload-input">
                    </div>

                    <button type="submit" class="btn" name="uploadbutton">Upload file</button>
                </form>

            </div>
        </div>
        <!-- original pen: https://codepen.io/roydigerhund/pen/ZQdbeN  -->

        <!-- NO JS ADDED YET -->
    </body>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.1.0/jquery.min.js" type="text/javascript"></script>
    <script src="js.js" type="text/javascript"></script>
</html>
