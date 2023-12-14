package com.nashss.se.translationtracker.metrics;

/**
 * Constant values for use with metrics.
 */
public class MetricsConstants {
    public static final String CREATETRANSLATIONCASE_DUPLICATETRANSLATIONCASE_COUNT =
            "CreateTranslationCase.DuplicateTranslationCaseException.Count";

    public static final String GETTRANSLATIONCASE_TRANSLATIONCASENOTFOUND_COUNT =
        "GetTranslationCase.TranslationCaseNotFoundException.Count";
    public static final String GETTRANSLATIONCASE_SECURITY_COUNT =
        "GetTranslationCase.SecurityException.Count";

    public static final String ARCHIVETRANSLATIONCASE_TRANSLATIONCASENOTFOUND_COUNT =
            "ArchiveTranslationCase.TranslationCaseNotFoundException.Count";
    public static final String ARCHIVETRANSLATIONCASE_SECURITY_COUNT =
            "ArchiveTranslationCase.SecurityException.Count";

    public static final String ADDPROGRESSUPDATE_TRANSLATIONCASENOTFOUND_COUNT =
            "AddProgressUpdate.TranslationCaseNotFoundException.Count";
    public static final String ADDPROGRESSUPDATE_SECURITY_COUNT =
            "AddProgressUpdate.SecurityException.Count";
    public static final String ADDPROGRESSUPDATE_DUPLICATEPROGRESSUPDATE_COUNT =
            "AddProgressUpdate.DuplicateProgressUpdateException.Count";

    public static final String CREATETRANSLATIONCLIENT_DUPLICATETRANSLATIONCLIENT_COUNT =
            "CreateTranslationClient.DuplicateTranslationClientException.Count";

    public static final String GETTRANSLATIONCLIENT_TRANSLATIONCLIENTNOTFOUND_COUNT =
            "GetTranslationClient.TranslationClientNotFoundException.Count";
    public static final String GETTRANSLATIONCLIENT_SECURITY_COUNT =
            "GetTranslationClient.SecurityException.Count";

    public static final String ARCHIVETRANSLATIONCLIENT_TRANSLATIONCLIENTNOTFOUND_COUNT =
            "ArchiveTranslationClient.TranslationClientNotFoundException.Count";
    public static final String ARCHIVETRANSLATIONCLIENT_SECURITY_COUNT =
            "ArchiveTranslationClient.SecurityException.Count";

    public static final String CREATEPAYMENTRECORD_DUPLICATEPAYMENTRECORD_COUNT =
            "CreatePaymentRecord.DuplicatePaymentRecordException.Count";

    public static final String GETPAYMENTRECORD_PAYMENTRECORDNOTFOUND_COUNT =
            "GetPaymentRecord.PaymentRecordNotFoundException.Count";
    public static final String GETPAYMENTRECORD_SECURITY_COUNT =
            "GetPaymentRecord.SecurityException.Count";

    public static final String ARCHIVEPAYMENTRECORD_PAYMENTRECORDNOTFOUND_COUNT =
            "ArchivePaymentRecord.PaymentRecordNotFoundException.Count";
    public static final String ARCHIVEPAYMENTRECORD_SECURITY_COUNT =
            "ArchivePaymentRecord.SecurityException.Count";


    public static final String SERVICE = "Service";
    public static final String SERVICE_NAME = "TranslationTrackerService";
    public static final String NAMESPACE_NAME = "U7/TranslationTrackerService";
}
