package io.electrum.giftcard.api;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

import io.electrum.giftcard.api.model.ErrorDetail;
import io.electrum.giftcard.api.model.LookupResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Authorization;
import io.swagger.annotations.ResponseHeader;

@Path("/lookupGiftcard")
@Consumes({ "application/json" })
@Produces({ "application/json" })
@Api(description = "the giftcard API")
public abstract class LookupGiftcardsResource {

   protected abstract ILookupGiftcardsResource getResourceImplementation();

   @GET
   @Path("/{giftcardNumber}")
   @Produces({ "application/json" })
   @ApiOperation(value = "Request gift card information.", notes = "The Lookup Gift Cards endpoint "
         + "allows information about a gift card to be retrieved. This operation has no financial impact and may "
         + "be submitted repeatedly without financial consequece. Thus there is no confirmation or reversal "
         + "process for gift card lookup requests.", authorizations = {
               @Authorization(value = "httpBasic") }, tags = { "Giftcard Information", })
   @ApiResponses(value = {
         @ApiResponse(code = 200, message = "OK", response = LookupResponse.class, responseHeaders = {
               @ResponseHeader(name = "Location", description = "The location of the gift card resource", response = String.class) }),
         @ApiResponse(code = 400, message = "Bad Request", response = ErrorDetail.class),
         @ApiResponse(code = 404, message = "Not Found", response = ErrorDetail.class),
         @ApiResponse(code = 500, message = "Internal Server Error", response = ErrorDetail.class),
         @ApiResponse(code = 503, message = "Service Unavailable", response = ErrorDetail.class),
         @ApiResponse(code = 504, message = "Gateway Timeout", response = ErrorDetail.class) })
   public final void lookupGiftcard(
         @ApiParam(value = "The card number of the gift card to be looked up.", required = true) @PathParam("giftcardNumber") String giftcardNumber,
         @Context SecurityContext securityContext,
         @Suspended AsyncResponse asyncResponse,
         @Context HttpHeaders httpHeaders,
         @Context UriInfo uriInfo,
         @Context HttpServletRequest httpServletRequest) {
      asyncResponse.resume(
            getResourceImplementation()
                  .lookupGiftcard(giftcardNumber, securityContext, httpHeaders, uriInfo, httpServletRequest));
   }
}