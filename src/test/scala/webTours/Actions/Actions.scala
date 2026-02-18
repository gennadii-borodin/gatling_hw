package webTours

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.request.builder.HttpRequestBuilder


object Actions {

  val getHomePage: HttpRequestBuilder = http("getHomePage")
    .get("/cgi-bin/welcome.pl?page=menus")
    .check(status is 200)

  val getWelcomePage: HttpRequestBuilder = http("getMainPage")
    .get("/cgi-bin/welcome.pl?signOff=true")
     .queryParam("signOff", "true")
     .check(status is 200)
    

  val getLoginPage: HttpRequestBuilder = http("getLognPage")
    .get("/cgi-bin/nav.pl")
    .queryParam("in", "home")
    .check(status is 200)
    .check(
        css("input[name='userSession']", "value").saveAs("userSession"))

  val postLoginData: HttpRequestBuilder = http("postLoginData")
    .post("/cgi-bin/login.pl")
    .formParam("userSession", "#{userSession}")
    .formParam("username", "#{user}")
    .formParam("password", "#{password}")
    .formParam("login.x", "49")
    .formParam("login.y", "12")
    
    .formParam("JSFormSubmit", "off")
    .check(status is 200)

  val getFlightsPage: HttpRequestBuilder =  http("getFlightsPage")
    .get("/cgi-bin/reservations.pl?page=welcome")
    .check(css("select[name='depart'] option", "value").findRandom.saveAs("departCity"))
    .check(css("select[name='arrive'] option", "value").findRandom.saveAs("arriveCity"))
    .check(css("input[name='departDate']", "value").optional.saveAs("departDate"))
    .check(css("input[name='returnDate']", "value").optional.saveAs("returnDate"))
    .check(css("input[name='numPassengers']", "value").optional.saveAs("numPassengers"))
    .check(css("input[name='seatPref']:checked", "value").optional.saveAs("seatPref"))
    .check(css("input[name='advanceDiscount']", "value").optional.saveAs("advanceDiscount"))  
    .check(css("input[name='seatType']:checked", "value").optional.saveAs("seatType"))
    .check(status is 200)

  val postReservations: HttpRequestBuilder = http("postReservationsPage")
    .post("/cgi-bin/reservations.pl")
    .header("Referer", "http://webtours.load-test.ru:1080/cgi-bin/reservations.pl?page=welcome") 
    .formParam("depart", "#{departCity}")
    .formParam("arrive", "#{arriveCity}")
    .formParam("advanceDiscount", "0")
    .formParam("departDate", "#{departDate}")
    .formParam("returnDate", "#{returnDate}")
    .formParam("numPassengers", "#{numPassengers}")
    .formParam("seatPref", "#{seatPref}")
    .formParam("seatType", "#{seatType}")
    .formParam("findFlights.x", "78")
    .formParam("findFlights.y", "16")
    .formParam(".cgifields", "roundtrip")
    .formParam(".cgifields", "seatType")
    .formParam(".cgifields", "seatPref")
    .check(status is 200)
    .check(css("input[name='outboundFlight']", "value").findRandom.saveAs("outboundFlight_value"))

  val postOutboundFlight: HttpRequestBuilder = http("postOutboundFlight")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight", "#{outboundFlight_value}")
    .formParam("numPassengers", "#{numPassengers}")
    .formParam("advanceDiscount", "#{advanceDiscount}")
    .formParam("seatType", "#{seatType}")
    .formParam("seatPref", "#{seatPref}")
    .formParam("reserveFlights.x", "78")  // Координаты кнопки
    .formParam("reserveFlights.y", "16")

  val postPayment: HttpRequestBuilder = http("postPayment")
    .post("/cgi-bin/reservations.pl")
    .formParam("outboundFlight", "#{outboundFlight_value}")
    .formParam("firstName", "Jeffrey")
    .formParam("lastName", "Lebowski")
    .formParam("address1", "LA")
    .formParam("address2", "11111")
    .formParam("pass1", "Jeffrey Lebowski")
    .formParam("creditCard", "123456")
    .formParam("expDate", "33")
    .formParam("oldCCOption", "")
    .formParam("numPassengers", "#{numPassengers}")
    .formParam("advanceDiscount", "#{advanceDiscount}")
    .formParam("seatType", "#{seatType}")
    .formParam("seatPref", "#{seatPref}")
    .formParam("returnFlight", "")
    .formParam("JSFormSubmit", "off")
    .formParam("buyFlights.x", "30")
    .formParam("buyFlights.y", "5")
    .formParam(".cgifields", "saveCC")
    .check(status is 200)
}