package io.electrum.giftcard.api;

import io.electrum.giftcard.api.model.ActivationConfirmation;
import io.electrum.giftcard.api.model.ActivationRequest;
import io.electrum.giftcard.api.model.ActivationReversal;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;

public interface IActivationsResource {
   Response confirmActivation(
         String activationId,
         String confirmationId,
         ActivationConfirmation body,
         SecurityContext securityContext,
         Request request,
         HttpHeaders httpHeaders,
         AsyncResponse asyncResponse,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   Response activate(
         String activationId,
         ActivationRequest body,
         SecurityContext securityContext,
         Request request,
         HttpHeaders httpHeaders,
         AsyncResponse asyncResponse,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);

   Response reverseActivation(
         String activationId,
         String reversalId,
         ActivationReversal body,
         SecurityContext securityContext,
         Request request,
         HttpHeaders httpHeaders,
         AsyncResponse asyncResponse,
         UriInfo uriInfo,
         HttpServletRequest httpServletRequest);
}
