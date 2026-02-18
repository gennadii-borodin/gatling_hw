package webTours


import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._
import webTours.configs.SimulationConfig._


class MaxPerformanceTest extends Simulation {
    
    setUp(
        CommonScenario().inject(
            // Интенсивность на ступень
            incrementUsersPerSec((intensity / stagesCount).toInt) 
                // Количество ступеней
                .times(stagesCount)
                // Длительность полки
                .eachLevelLasting(stageDuration)    
                // Длительность разгона
                .separatedByRampsLasting(rampDuration)  
                // Начало нагрузки с 0 rps
                .startingFrom(0),                                    
            ),
            ).protocols(httpProtocol)
            .maxDuration(stageDuration * stagesCount + 1.minute)
}