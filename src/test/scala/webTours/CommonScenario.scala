package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.core.structure.ScenarioBuilder
import webTours.Feeders._


object CommonScenario{
    def apply(): ScenarioBuilder = new CommonScenario().scn
}

class CommonScenario {
    val scn: ScenarioBuilder = scenario("Common Scenario")
    .feed(users)
    .exec(Actions.getWelcomePage)
    .exec(Actions.getLoginPage)
    .exec(Actions.postLoginData)
    .exec(Actions.getFlightsPage)
    .exec(Actions.postReservations)
    .exec(Actions.postOutboundFlight)
    .exec(Actions.postPayment)
    .exec(Actions.getHomePage)
}