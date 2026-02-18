package webTours

import io.gatling.core.Predef._

object Feeders {
  val users = csv("test_data/users.csv").random
}
