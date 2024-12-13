package com.example.order.scala

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

class BookstoreSimulation extends Simulation {

  // HTTP Protokoll Definition
  val httpProtocol: HttpProtocolBuilder = http
    .baseUrl("http://localhost:8080/") // Basis-URL für die API
    .acceptHeader("application/json")

  // Szenario Definition
  val scn = scenario("Bookstore API Test")
    .exec(
      http("Get Books with Keywords")
        .get("/books/search/Simon") // API-Endpunkt für die Buchsuche
        //.get("/books?keywords=Simon") // API-Endpunkt für die Buchsuche
        .check(status.is(200))
    )

  // Setup der Simulation
  setUp(
    scn.inject(atOnceUsers(100)) // 100 Benutzer gleichzeitig
  ).protocols(httpProtocol)
}