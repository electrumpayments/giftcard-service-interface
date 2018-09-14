package io.electrum.giftcard.api;

public class GiftcardApi {
   public class Paths {
      public static final String VERSION_NO = "/3";
      public static final String BASE_PATH = "/giftcard" + VERSION_NO;

      public class ActivationPaths {
         public static final String ACTIVATION_BASE_PATH = "/activations";
      }

      public class LoadPaths {
         public static final String LOAD_BASE_PATH = "/loads";
      }

      public class LookupPaths {
        public static final String LOOKUP_BASE_PATH = "/lookupGiftcard";
      }

      public class RedemptionPaths {
        public static final String REDEMPTIONS_BASE_PATH = "/redemptions";
      }

      public class ReplacePaths {
        public static final String REPLACE_BASE_PATH = "/replaces";
      }

      public class TransferPaths {
        public static final String TRANSFER_BASE_PATH = "/transfers";
      }

      public class VoidPaths {
        public static final String VOID_BASE_PATH = "/voids";
      }
   }

   public class HttpAuthorizations {
       public static final String HTTP_BASIC = "httpBasic";
   }
}
