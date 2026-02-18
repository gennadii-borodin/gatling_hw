package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import webTours.configs.SimulationConfig._

class Stability extends Simulation  {

  setUp(
    CommonScenario().inject(
      // Плавное увеличение нагрузки до целевого значения
      rampUsersPerSec(0) to stabilibyIntensity.toInt during rampDuration,
      // Длительность полки
      constantUsersPerSec(stabilibyIntensity.toInt) during stabilityDuration
    )
  ).protocols(httpProtocol)
    // Общая длительность теста
    .maxDuration(stabilityDuration + 1.minute)

}