package com.example.order;

import com.example.order.scala.BookstoreSimulation;
import io.gatling.app.Gatling;
import io.gatling.core.config.GatlingPropertiesBuilder;
import org.junit.jupiter.api.Test;

public class GatlingTest {

    @Test
    void runGatlingSimulation() {
        GatlingPropertiesBuilder props = new GatlingPropertiesBuilder();
        props.simulationClass(BookstoreSimulation.class.getName());

        Gatling.fromMap(props.build());
    }
}