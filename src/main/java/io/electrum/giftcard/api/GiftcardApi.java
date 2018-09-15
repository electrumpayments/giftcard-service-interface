package io.electrum.giftcard.api;

public class GiftcardApi {
   public class PathParams {
      // The general path parameters
      public static final String REVERSAL_ID = "reversalId";
      public static final String CONFIRMATION_ID = "confirmationId";

      // Operation specific path parameters
      public static final String ACTIVATION_ID = "activationId";
      public static final String LOAD_ID = "loadId";
      public static final String LOOKUP_ID = "lookupId";
      public static final String REDEMPTION_ID = "redemptionId";
      public static final String REPLACE_ID = "replaceId";
      public static final String TRANSFER_ID = "transferId";
      public static final String VOID_ID = "voidId";

   }

   public class Paths {
      public static final String VERSION_NO = "/3";
      public static final String BASE_PATH = "/giftcard" + VERSION_NO;
      public static final String REVERSALS = "/reversals";
      public static final String CONFIRMATIONS = "/confirmations";

      public class ActivationPaths {
         public static final String ACTIVATION_BASE_PATH = "/activations";
         public static final String ACTIVATION_REQUEST = " ";
         public static final String ACTIVATION_CONFIRMATION = " ";
         public static final String ACTIVATION_REVERSAL = " ";
      }

      public class LoadPaths {
         public static final String LOAD_BASE_PATH = "/loads";
         public static final String LOAD_REQUEST = " ";
         public static final String LOAD_CONFIRMATION = " ";
         public static final String LOAD_REVERSAL = " ";
      }

      public class LookupPaths {
         public static final String LOOKUP_BASE_PATH = "/lookupGiftcard";
         public static final String LOOKUP_REQUEST = " ";
      }

      public class RedemptionPaths {
         public static final String REDEMPTION_BASE_PATH = "/redemptions";
         public static final String REDEMPTION_REQUEST = " ";
         public static final String REDEMPTION_CONFIRMATION = " ";
         public static final String REDEMPTION_REVERSAL = " ";
      }

      public class ReplacePaths {
         public static final String REPLACE_BASE_PATH = "/replaces";
         public static final String REPLACE_REQUEST = " ";
         public static final String REPLACE_CONFIRMATION = " ";
         public static final String REPLACE_REVERSAL = " ";
      }

      public class TransferPaths {
         public static final String TRANSFER_BASE_PATH = "/transfers";
         public static final String TRANSFER_REQUEST = " ";
         public static final String TRANSFER_CONFIRMATION = " ";
         public static final String TRANSFER_REVERSAL = " ";
      }

      public class VoidPaths {
         public static final String VOID_BASE_PATH = "/voids";
         public static final String VOID_REQUEST = " ";
         public static final String VOID_CONFIRMATION = " ";
         public static final String VOID_REVERSAL = " ";
      }
   }

   public class HttpAuthorizations {
      public static final String HTTP_BASIC = "httpBasic";
   }

   public class ResponseCodes {
      public static final int CREATED = 201;
      public static final int ACCEPTED = 202;
      public static final int BAD_REQUEST = 400;
      public static final int NOT_FOUND = 404;
      public static final int INTERNAL_SERVER_ERROR = 500;
      public static final int SERVICE_UNAVAILABLE = 503;
      public static final int GATEWAY_TIMEOUT = 504;
   }

   public class ResponseMessages {
      public static final String CREATED = "Created";
      public static final String ACCEPTED = "Accepted";
      public static final String BAD_REQUEST = "Bad Request";
      public static final String NOT_FOUND = "Not Found";
      public static final String INTERNAL_SERVER_ERROR = "Internal Server Error";
      public static final String SERVICE_UNAVAILABLE = "Service Unavailable";
      public static final String GATEWAY_TIMEOUT = "Gateway Timeout";
   }
}
