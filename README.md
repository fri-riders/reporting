# Reporting microservice

Microservice to provide PDF reports for data from database for administrative purposes.

## Endpoints
* Swagger `GET /swagger-ui.html`
* Health: `GET /health`
* Environment: `GET /env`
* Metrics (all): `GET /metrics`

## Get report endpoints
* Accommodation list PDF `GET /v1/reporting/accommodations`
* List bookings for accommodation PDF `GET /v1/reporting/accommodationBookings/{id}`