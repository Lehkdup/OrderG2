package com.example.order;
import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;



public class BookstoreGatlingTest extends Simulation {

    // Add the HttpProtocolBuilder:
    HttpProtocolBuilder httpProtocol =
            http.baseUrl("http://localhost:8080")
                    // set the "accept" header to a value suited for the expected response
                    .acceptHeader("application/json");


    // Add the ScenarioBuilder:
    ScenarioBuilder myScenario = scenario("My Test Szenario")
            .exec(http("Request 1").get("/books/search/Simon"));

    // Add the setUp block:
    {
        setUp(
                myScenario.injectOpen(constantUsersPerSec(100).during(60))
                //myScenario.injectOpen(constantUsersPerSec(2).during(60))
        ).protocols(httpProtocol);
    }
}
