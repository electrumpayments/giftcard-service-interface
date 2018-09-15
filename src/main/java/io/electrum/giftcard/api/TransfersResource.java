package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.*;
import io.electrum.vas.model.BasicAdviceResponse;
import io.swagger.annotations.*;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.*;

import static io.electrum.giftcard.api.GiftcardApi.Paths.TransferPaths.TRANSFER_BASE_PATH;

@Path(TRANSFER_BASE_PATH)
@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
@Api(description = "the transfers API")
public abstract class TransfersResource {

   protected abstract ITransfersResource getResourceImplementation();

   @POST
   @Path("/{transferId}/confirmations/{confirmationId}")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces({MediaType.APPLICATION_JSON})
   @ApiOperation(value = "Confirm a transfer from a source gift card to a target gift card.", notes = "The Transfer Confirmations endpoint registers the confirmation of a prior transfer of a "
         + "source gift card to a target gift card. Transfer confirmations are advice type messages and "
         + "should continue to be sent at suitable intervals until a response has been received. Multiple "
         + "confirmation advices may be sent which refer to the same transfer. The net result is that the "
         + "transfer is confirmed once.", authorizations = {
               @Authorization(value = GiftcardApi.HttpAuthorizations.HTTP_BASIC) }, tags = { "Confirmations", "Transfers", }, nickname = Operations.CONFIRM_TRANSFER)
   @ApiResponses(value = { @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdviceResponse.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void confirmTransfer(
         @ApiParam(value = "The randomly generated transferId UUID as sent in the original transfer request.", required = true) @PathParam("transferId") String transferId,
         @ApiParam(value = "The randomly generated UUID identifying this confirmation, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("confirmationId") String confirmationId,
         @ApiParam(value = "The transfer confirmation information.", required = true) TransferConfirmation transferConfirmation,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().confirmTransfer(
            transferId,
            confirmationId,
            transferConfirmation,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path("/{transferId}")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces({MediaType.APPLICATION_JSON})
   @ApiOperation(value = "Request a transfer from a source gift card to a target gift card.", notes = "The Transfers endpoint "
         + "allows funds to be transferred from one gift card to another. A transfer is not considered "
         + "complete until a transfer confirmation or transfer reversal has been sent and "
         + "acknowledged. A transfer request should only be sent once otherwise multiple "
         + "transfers may occur erroneously.", authorizations = {
               @Authorization(value = GiftcardApi.HttpAuthorizations.HTTP_BASIC) }, tags = { "Transfers", }, nickname = Operations.TRANSFER)
   @ApiResponses(value = {
         @ApiResponse(code = GiftcardApi.ResponseCodes.CREATED, message = GiftcardApi.ResponseMessages.CREATED, response = TransferResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the created load resource", response = String.class) }),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.INTERNAL_SERVER_ERROR, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void transfer(
         @ApiParam(value = "The randomly generated UUID identifying this transfer, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("transferId") String transferId,
         @ApiParam(value = "The transfer information.", required = true) TransferRequest transferRequest,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().transfer(
            transferId,
            transferRequest,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   @POST
   @Path("/{transferId}/reversals/{reversalId}")
   @Consumes({MediaType.APPLICATION_JSON})
   @Produces({MediaType.APPLICATION_JSON})
   @ApiOperation(value = "Simplistically, a transfer reversal undoes a transfer if the transfer "
         + "was successfully processed.", notes = "The Transfer Reversals endpoint allows "
               + "a transfer between gift cards to be reversed. If the sender of a transfer request "
               + "is uncertain of the state of a transfer request then the sender must send a "
               + "transfer reversal. Reversals should continue to be sent at suitable intervals "
               + "until a response has been received. Multiple reversals may be sent which refer to "
               + "the same transfer. The net result is that the transfer is reversed once.", authorizations = {
                     @Authorization(value = GiftcardApi.HttpAuthorizations.HTTP_BASIC) }, tags = { "Transfers", "Reversals", }, nickname = Operations.REVERSE_TRANSFER)
   @ApiResponses(value = { @ApiResponse(code = GiftcardApi.ResponseCodes.ACCEPTED, message = GiftcardApi.ResponseMessages.ACCEPTED, response = BasicAdviceResponse.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.BAD_REQUEST, message = GiftcardApi.ResponseMessages.BAD_REQUEST, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.NOT_FOUND, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.INTERNAL_SERVER_ERROR, message = GiftcardApi.ResponseMessages.NOT_FOUND, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.SERVICE_UNAVAILABLE, message = GiftcardApi.ResponseMessages.SERVICE_UNAVAILABLE, response = ErrorDetail.class),
         @ApiResponse(code = GiftcardApi.ResponseCodes.GATEWAY_TIMEOUT, message = GiftcardApi.ResponseMessages.GATEWAY_TIMEOUT, response = ErrorDetail.class) })
   public final void reverseTransfer(
         @ApiParam(value = "The randomly generated transferId UUID as sent in the original transfer.", required = true) @PathParam("transferId") String transferId,
         @ApiParam(value = "The randomly generated UUID identifying this reversal, as defined for a variant 4 UUID in [RFC 4122](https://tools.ietf.org/html/rfc4122).", required = true) @PathParam("reversalId") String reversalId,
         @ApiParam(value = "The transfer reversal information.", required = true) TransferReversal transferReversal,
         @Context SecurityContext securityContext,
         @Context Request request,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      getResourceImplementation().reverseTransfer(
            transferId,
            reversalId,
            transferReversal,
            securityContext,
            request,
            httpHeaders,
            asyncResponse,
            uriInfo,
            httpServletRequest);
   }

   public class Operations {
      public static final String CONFIRM_TRANSFER = "confirmTransfer";
      public static final String TRANSFER = "transfer";
      public static final String REVERSE_TRANSFER = "reverseTransfer";
   }

}
