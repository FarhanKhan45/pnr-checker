<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Indian Railway PNR Status</title>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
rel="stylesheet">

<link rel="stylesheet"
href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css">

<style>

body{
    background:linear-gradient(135deg,#dbeafe,#eff6ff,#bfdbfe);
    background-attachment:fixed;
    font-family:Arial,Helvetica,sans-serif;
    min-height:100vh;
}

.container{
    max-width:650px;
    margin:auto;
    padding:15px;
}

.train-card{
    background:linear-gradient(135deg,#1565C0,#0D47A1);
    color:white;
    border-radius:20px;
    padding:18px;
    box-shadow:0 8px 20px rgba(0,0,0,.18);
}

.train-title{
    font-size:24px;
    font-weight:bold;
}

.station{
    font-size:18px;
    font-weight:bold;
}

.section-title{
    color:#0D47A1;
    font-weight:bold;
    margin-top:18px;
    margin-bottom:10px;
}

.passenger-card{
    margin-top:12px;
    border:none;
    border-radius:16px;
    background:white;
    box-shadow:0 5px 15px rgba(0,0,0,.12);
    transition:.3s;
}

.passenger-card:hover{
    transform:translateY(-2px);
}

.card-body{
    padding:14px;
}

.seat{
    display:inline-block;
    background:#E8F5E9;
    color:#2E7D32;
    padding:4px 8px;
    border-radius:8px;
    font-size:13px;
    font-weight:bold;
}

.confirmed{
    background:#2E7D32;
    color:white;
    padding:4px 10px;
    border-radius:15px;
    font-size:13px;
    font-weight:bold;
}

.rac{
    background:#0288D1;
    color:white;
    padding:4px 10px;
    border-radius:15px;
    font-size:13px;
    font-weight:bold;
}

.waiting{
    background:#D32F2F;
    color:white;
    padding:4px 10px;
    border-radius:15px;
    font-size:13px;
    font-weight:bold;
}

.footer{
    text-align:center;
    margin-top:20px;
    color:#555;
}

.btn-primary{
    border-radius:12px;
    font-weight:bold;
    padding:12px;
}

@media(max-width:576px){

.container{
    padding:10px;
}

.train-title{
    font-size:20px;
}

.station{
    font-size:15px;
}

.section-title{
    font-size:22px;
}

}

</style>

</head>

<body>

<div class="container">
<!-- Train Header -->

<div class="train-card">

    <div class="train-title">
        <i class="fa-solid fa-train"></i>
        ${response.trainNo} - ${response.trainName}
    </div>

    <hr style="border-color:white;">

    <!-- Source & Destination -->

    <div class="row text-center">

        <div class="col-5">

            <div class="station">
                ${response.source}
            </div>

            <small>From</small>

        </div>

        <div class="col-2 d-flex align-items-center justify-content-center">

            <h3>
                <i class="fa-solid fa-arrow-right"></i>
            </h3>

        </div>

        <div class="col-5">

            <div class="station">
                ${response.destination}
            </div>

            <small>To</small>

        </div>

    </div>

    <hr style="border-color:white;">

    <!-- Journey & Class -->

    <div class="row text-center">

        <div class="col-6">

            <b>
                <i class="fa-regular fa-calendar"></i>
                Journey
            </b>

            <br>

            ${response.journeyDate}

        </div>

        <div class="col-6">

            <b>
                <i class="fa-solid fa-ticket"></i>
                Class
            </b>

            <br>

            ${response.travelClass}

        </div>

    </div>

    <!-- PNR & Quota -->

    <div class="row text-center mt-3">

        <div class="col-6">

            <b>
                <i class="fa-solid fa-barcode"></i>
                PNR
            </b>

            <br>

            ${response.pnr}

        </div>

        <div class="col-6">

            <b>
                <i class="fa-solid fa-list"></i>
                Quota
            </b>

            <br>

            ${response.quota}

        </div>

    </div>

    <!-- Boarding & Chart -->

    <div class="row text-center mt-3">

        <div class="col-6">

            <b>
                <i class="fa-solid fa-location-dot"></i>
                Boarding
            </b>

            <br>

            ${response.boardingPoint}

        </div>

        <div class="col-6">

            <b>
                <i class="fa-solid fa-chart-line"></i>
                Chart
            </b>

            <br>

            <c:choose>

                <c:when test="${response.chartPrepared=='Yes'}">

                    <span class="confirmed">
                        Prepared
                    </span>

                </c:when>

                <c:otherwise>

                    <span class="waiting">
                        Not Prepared
                    </span>

                </c:otherwise>

            </c:choose>

        </div>

    </div>

</div>

<!-- Passenger Details -->

<h4 class="section-title">
    <i class="fa-solid fa-users"></i>
    Passenger Details
</h4>

<div class="card info-card">

    <div class="table-responsive">

        <table class="table table-bordered table-hover mb-0 text-center align-middle">

            <thead style="background:#1565C0;color:white;">

                <tr>
                    <th>S. No.</th>
                    <th>Booking Status</th>
                    <th>Current Status</th>
                    <th>Seat / Berth</th>
                    <th>Position</th>
                </tr>

            </thead>

            <tbody>

                <c:forEach var="p" items="${response.passengers}">

                    <tr>

                        <td>Passenger ${p.passengerNo}</td>

                        <td>${p.bookingStatus}</td>

                        <td>
                            <c:choose>
                                <c:when test="${p.currentStatus.startsWith('CNF')}">
                                    <span class="confirmed">Confirmed</span>
                                </c:when>
                                <c:when test="${p.currentStatus.startsWith('RAC')}">
                                    <span class="rac">RAC</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="waiting">${p.currentStatus}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
<td>

<c:choose>

    <c:when test="${p.currentStatus.startsWith('CNF')}">
        <span class="seat">${p.berth}</span>
    </c:when>

    <c:otherwise>
        <span style="color:red;font-weight:bold;">
            Not Allotted
        </span>
    </c:otherwise>

</c:choose>

</td>

                        <td>
                            -
                        </td>

                    </tr>

                </c:forEach>

            </tbody>

        </table>

    </div>

</div>

<!-- Journey Information -->

<h4 class="section-title">
    <i class="fa-solid fa-circle-info"></i>
    Journey Information
</h4>

<div class="card passenger-card">

    <div class="card-body">

        <div class="row text-center">

            <div class="col-6">

                <b>
                    <i class="fa-regular fa-calendar"></i>
                    Booking Date
                </b>

                <br>

                ${response.bookingDate}

            </div>

            <div class="col-6">

                <b>
                    <i class="fa-solid fa-indian-rupee-sign"></i>
                    Booking Fare
                </b>

                <br>

                ₹ ${response.bookingFare}

            </div>

        </div>

        <hr>

        <div class="row text-center">

            <div class="col-6">

                <b>
                    <i class="fa-solid fa-users"></i>
                    Passengers
                </b>

                <br>

                ${response.passengerCount}

            </div>

            <div class="col-6">

                <b>
                    <i class="fa-solid fa-road"></i>
                    Distance
                </b>

                <br>

                ${response.distance} km

            </div>

        </div>

    </div>

</div>
<!-- Check Another PNR -->

<div class="d-grid mt-4 mb-3">

    <a href="/" class="btn btn-primary btn-lg">

        <i class="fa-solid fa-magnifying-glass"></i>

        Check Another PNR

    </a>

</div>

<!-- Footer -->

<div class="footer">

    <hr>

    <h5 style="color:#0D47A1;font-weight:bold;">

        <i class="fa-solid fa-train"></i>

        Indian Railway PNR Checker

    </h5>

    <p style="margin-bottom:5px;">

        Designed & Developed by

        <b style="color:#1565C0;">Farhan Khan</b>

    </p>

    <small style="color:#666;">

        Spring Boot • JSP • Bootstrap • RapidAPI

    </small>

</div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>