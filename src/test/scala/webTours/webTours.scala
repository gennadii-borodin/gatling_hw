
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder

package object webTours {
val httpProtocol: HttpProtocolBuilder = http
  .baseUrl("http://webtours.load-test.ru:1080")
  .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7")
  .header("Cache-Control", "no-cache")
  .header("Connnection", "keep-alive")
  .acceptLanguageHeader("en,ru-RU;q=0.9,ru;q=0.8,en-US;q=0.7")
  .acceptEncodingHeader("gzip, deflate")
  .userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/144.0.0.0 Safari/537.36")
  .header("Connection", "keep-alive")
}