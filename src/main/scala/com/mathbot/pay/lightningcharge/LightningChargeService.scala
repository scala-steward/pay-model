package com.mathbot.pay.lightningcharge

import play.api.libs.json.{JsError, Json}
import sttp.client.playJson.asJson
import sttp.client.{SttpBackend, _}
import sttp.model.{MediaType, StatusCode}

import scala.concurrent.duration._
import scala.concurrent.{ExecutionContext, Future}

/**
 * https://github.com/ElementsProject/lightning-charge
 * @param config
 * @param backend
 * @param ec
 */
class LightningChargeService(
    config: LightningChargeConfig,
    backend: SttpBackend[Future, Nothing, NothingT],
    ec: ExecutionContext
) {

  private implicit val e: ExecutionContext = ec
  private implicit val be: SttpBackend[Future, Nothing, NothingT] = backend

  private val base = basicRequest.auth
    .basic(config.username, config.password)
    .acceptEncoding(MediaType.ApplicationJson.toString())

  def invoice(invoice: InvoiceRequest): Future[Response[Either[ResponseError[JsError], LightningChargeInvoice]]] = {
    val req = base
      .post(uri"${config.baseUrl}/invoice")
      .contentType(MediaType.ApplicationJson)
      .body(Json.toJson(invoice).toString)
      .response(asJson[LightningChargeInvoice])
    req.send()
  }

  /*
    https://github.com/ElementsProject/lightning-charge#get-invoiceidwaittimeoutsec
    GET /invoice/:id/wait?timeout=[sec]
   */
  def longPoll(
      id: String,
      timeout: FiniteDuration = 20.seconds
  ): Future[Response[Either[ResponseError[JsError], LightningChargeInvoice]]] = {
    val params = Map("timeout" -> timeout.toSeconds)
    val req = base
      .get(uri"${config.baseUrl}/invoice/$id/wait?$params")
      .readTimeout(timeout.plus(1.second))
      .response(asJson[LightningChargeInvoice])
    req.send()

  }

  def get(id: String): Future[Response[Either[ResponseError[JsError], LightningChargeInvoice]]] = {
    val req = base
      .get(uri"${config.baseUrl}/invoice/$id")
      .acceptEncoding(MediaType.ApplicationJson.toString())
      .response(asJson[LightningChargeInvoice])
    req.send()
  }

  def getAll(): Future[Response[Either[ResponseError[JsError], Seq[LightningChargeInvoice]]]] = {
    val req = base
      .get(uri"${config.baseUrl}/invoices")
      .acceptEncoding(MediaType.ApplicationJson.toString())
      .response(asJson[Seq[LightningChargeInvoice]])
    req.send()
  }

}

object LightningChargeService {
  case class InvoiceResponseError(status: StatusCode)
}