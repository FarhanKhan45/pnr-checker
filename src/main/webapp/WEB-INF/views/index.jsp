<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Indian Railway PNR Checker</title>

<style>

*{
    margin:0;
    padding:0;
    box-sizing:border-box;
}

body{

    font-family:Arial,sans-serif;

    background-image:url("https://images.unsplash.com/photo-1601999007938-f584b47324ac");
    background-size:cover;
    background-position:center;
    background-repeat:no-repeat;
    background-attachment:fixed;

    height:100vh;

    display:flex;
    justify-content:center;
    align-items:center;
}

.container{

    width:420px;

    background:rgba(255,255,255,0.25);

    backdrop-filter:blur(10px);

    border-radius:20px;

    padding:40px;

    text-align:center;

    box-shadow:0px 0px 25px black;
}

h1{

    color:white;

    margin-bottom:30px;

    text-shadow:2px 2px 5px black;
}

input{

    width:100%;

    padding:14px;

    font-size:18px;

    border:none;

    border-radius:8px;

    margin-bottom:20px;
}

button{

    width:100%;

    padding:14px;

    font-size:18px;

    background:#0b5394;

    color:white;

    border:none;

    border-radius:8px;

    cursor:pointer;

    transition:.3s;
}

button:hover{

    background:#073763;
}

.footer{

    margin-top:25px;

    color:white;

    font-weight:bold;

    text-shadow:1px 1px 3px black;
}

</style>

</head>

<body>

<div class="container">

<h1>🚆 Indian Railway PNR Checker</h1>

<form action="check-pnr" method="post">

<input
type="text"
name="pnr"
placeholder="Enter 10 Digit PNR Number"
maxlength="10"
required>

<button type="submit">

Check PNR

</button>

</form>

<div class="footer">

Created by Farhan Khan

</div>

</div>

</body>

</html>