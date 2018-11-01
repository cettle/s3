(ns s3.core
  (:require
            [clojure.spec.test.alpha :as stest]
            [clojure.spec.alpha :as s]
            [s3.core.specs])
  (:import
    (software.amazon.awssdk.services.s3
      S3AsyncClient)
    (software.amazon.awssdk.services.s3.model
      AbortMultipartUploadRequest
      CompleteMultipartUploadRequest
      CopyObjectRequest
      PutObjectRequest
      PutObjectAclRequest
      ObjectCannedACL
      RequestPayer
      PutObjectTaggingRequest
      Tagging
      PutBucketAccelerateConfigurationRequest
      AccelerateConfiguration
      PutBucketAclRequest
      AccessControlPolicy
      BucketCannedACL
      PutBucketAnalyticsConfigurationRequest
      AnalyticsConfiguration
      PutBucketCorsRequest
      CORSConfiguration
      MetadataDirective
      ServerSideEncryption
      PutBucketInventoryConfigurationRequest
      InventoryConfiguration
      PutBucketLifecycleConfigurationRequest
      BucketLifecycleConfiguration
      PutBucketLoggingRequest
      BucketLoggingStatus
      PutBucketMetricsConfigurationRequest
      MetricsConfiguration
      PutBucketNotificationConfigurationRequest
      NotificationConfiguration
      PutBucketNotificationRequest
      NotificationConfigurationDeprecated
      PutBucketLifecycleRequest
      LifecycleConfiguration
      PutBucketPolicyRequest
      PutBucketReplicationRequest
      ReplicationConfiguration
      PutBucketRequestPaymentRequest
      RequestPaymentConfiguration
      PutBucketTaggingRequest
      PutBucketVersioningRequest
      VersioningConfiguration
      PutBucketWebsiteRequest
      WebsiteConfiguration)
    (software.amazon.awssdk.auth.credentials
      ProfileCredentialsProvider)
    (software.amazon.awssdk.core.async
      AsyncRequestBody)
    (java.time
      Instant)
    (java.io
      File)))

(defmulti put-bucket :Type)
(defmulti put-object :Type)

(defn- creds
  [^String name]
  (.build
    (-> (ProfileCredentialsProvider/builder)
        (.profileName name))))

(defn- client
  ([]
   (.build
     (S3AsyncClient/builder)))
  ([^String name]
   (.build
     (-> (S3AsyncClient/builder)
         (.credentialsProvider
           (creds name))))))

(defn abort-multipart-upload
  [{:keys
    [^String Bucket
     ^String Key
     ^String UploadId
     ^String RequesterPayer
     ^String Profile]
    :or
    {^RequestPayer RequesterPayer (RequestPayer/REQUESTER)
     ^String Profile              "default"}}]
  (.abortMultipartUpload
    (client Profile)
    (.build
      (->
        (AbortMultipartUploadRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.uploadId UploadId)
        (.requestPayer RequesterPayer)))))

(defn complete-multipart-upload
  [{:keys
    [^String Bucket
     ^String Key
     ^String UploadId
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.completeMultipartUpload
    (client Profile)
    (.build
      (->
        (CompleteMultipartUploadRequest/builder)
        (.bucket Bucket)
        (.key Key)
        (.uploadId UploadId)
        (.requestPayer RequestPayer)))))

(defn copy-object
  [{:keys
    [^String CopySource
     ^String Bucket
     ^String CopySourceIfMatch
     ^String CopySourceIfNoneMatch
     ^String Key
     ^Instant CopySourceIfModifiedSince
     ^Instant CopySourceIfUnmodifiedSince
     ^String CopySourceSseCustomerAlgorithm
     ^String CopySourceSseCustomerKey
     ^String CopySourceSseCustomerKeyMd5
     ^String GrantWriteAcp
     ^String GrantReadAcp
     ^String GrantRead
     ^String GrantFullControl
     ^String CacheControl
     ^String ContentDisposition
     ^String ContentEncoding
     ^String ContentLanguage
     ^Instant Expires
     ^Instant Metadata
     ^MetadataDirective MetadataDirective
     ^ServerSideEncryption ServerSideEncryption
     ^String SseCustomerKey
     ^String SseKmsKeyId
     ^String StorageClass
     ^String Tagging
     ^String TaggingDirective
     ^String WebsiteRedirectionLocation
     ^String ContentType
     ^ObjectCannedACL Acl
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.copyObject
    (client Profile)
    (.build
      (->
        (CopyObjectRequest/builder)
        (.copySource CopySource)
        (.copySourceIfMatch CopySourceIfMatch)
        (.copySourceSSECustomerAlgorithm CopySourceSseCustomerAlgorithm)
        (.copySourceSSECustomerKey CopySourceSseCustomerKey)
        (.copySourceSSECustomerKeyMD5 CopySourceSseCustomerKeyMd5)
        (.copySourceIfModifiedSince CopySourceIfModifiedSince)
        (.copySourceIfNoneMatch CopySourceIfNoneMatch)
        (.copySourceIfUnmodifiedSince CopySourceIfUnmodifiedSince)
        (.grantWriteACP GrantWriteAcp)
        (.grantReadACP GrantReadAcp)
        (.grantRead GrantRead)
        (.grantFullControl GrantFullControl)
        (.acl Acl)
        (.cacheControl CacheControl)
        (.contentDisposition ContentDisposition)
        (.contentEncoding ContentEncoding)
        (.contentLanguage ContentLanguage)
        (.contentType ContentType)
        (.expires Expires)
        (.metadata Metadata)
        (.metadataDirective MetadataDirective)
        (.serverSideEncryption ServerSideEncryption)
        (.sseCustomerKey SseCustomerKey)
        (.ssekmsKeyId SseKmsKeyId)
        (.storageClass StorageClass)
        (.tagging Tagging)
        (.taggingDirective TaggingDirective)
        (.websiteRedirectLocation WebsiteRedirectionLocation)
        (.key Key)
        (.bucket Bucket)
        (.requestPayer RequestPayer)))))

(defmethod put-object
  :Request
  [{:keys
    [^File FromFile
     ^String Bucket
     ^ObjectCannedACL ObjectCannedAcl
     ^String CacheControl
     ^String ContentDisposition
     ^String ContentEncoding
     ^String ContentLanguage
     ^String ContentMD5
     ^String ContentType
     ^Instant Expires
     ^String GrantFullControl
     ^String GrantRead
     ^String GrantReadAcp
     ^String GrantWriteAcp
     ^String StorageClass
     ^String WebsiteRedirectLocation
     ^String SseCustomerAlgorithm
     ^String SseCustomerKey
     ^String ServerSideEncryption
     ^String SseKmsKeyId
     ^String Key
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putObject
    (client Profile)
    (.build
      (->
        (PutObjectRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.acl ObjectCannedAcl)
        (.cacheControl CacheControl)
        (.contentDisposition ContentDisposition)
        (.contentEncoding ContentEncoding)
        (.contentLanguage ContentLanguage)
        (.contentMD5 ContentMD5)
        (.contentType ContentType)
        (.expires Expires)
        (.grantFullControl GrantFullControl)
        (.grantRead GrantRead)
        (.grantReadACP GrantReadAcp)
        (.grantWriteACP GrantWriteAcp)
        (.serverSideEncryption ServerSideEncryption)
        (.storageClass StorageClass)
        (.websiteRedirectLocation WebsiteRedirectLocation)
        (.requestPayer RequestPayer)
        (.sseCustomerAlgorithm SseCustomerAlgorithm)
        (.sseCustomerKey SseCustomerKey)
        (.ssekmsKeyId SseKmsKeyId)))
    (AsyncRequestBody/fromFile FromFile)))

(defmethod put-object
  AclRequest
  [{:keys
    [^String Bucket
     ^ObjectCannedACL Acl
     ^String Key
     ^String GrantWriteAcp
     ^String GrantWrite
     ^String GrantReadAcp
     ^String GrantFullControl
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putObjectAcl
    (client Profile)
    (.build
      (->
        (PutObjectAclRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.acl Acl)
        (.grantWriteACP GrantWriteAcp)
        (.grantWrite GrantWrite)
        (.grantReadACP GrantReadAcp)
        (.grantFullControl GrantFullControl)
        (.contentMD5 ContentMd5)))))

(defmethod put-object
  :TaggingRequest
  [{:keys
    [^String Bucket
     ^String Key
     ^Tagging Tagging
     ^String ContentMd5
     ^String VersionId
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putObjectTagging
    (client Profile)
    (.build
      (->
        (PutObjectTaggingRequest/builder)
        (.key Key)
        (.bucket Bucket)
        (.tagging Tagging)
        (.contentMD5 ContentMd5)
        (.versionId VersionId)))))

(defmethod put-bucket
  :AccelerateConfigurationRequest
  [{:keys
    [^String Bucket
     ^AccelerateConfiguration AccelerateConfiguration
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketAccelerateConfiguration
    (client Profile)
    (.build
      (->
        (PutBucketAccelerateConfigurationRequest/builder)
        (.bucket Bucket)
        (.accelerateConfiguration AccelerateConfiguration)))))

(defmethod put-bucket
  :AclRequest
  [{:keys
    [^String Bucket
     ^AccessControlPolicy AccessControlPolicy
     ^BucketCannedACL BucketCannedAcl
     ^String ContentMd5
     ^String GrantFullControl
     ^String GrantRead
     ^String GrantReadAcp
     ^String GrantWrite
     ^String GrantWriteAcp
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketAcl
    (client Profile)
    (.build
      (->
        (PutBucketAclRequest/builder)
        (.bucket Bucket)
        (.accessControlPolicy AccessControlPolicy)
        (.acl BucketCannedAcl)
        (.contentMD5 ContentMd5)
        (.grantFullControl GrantFullControl)
        (.grantRead GrantRead)
        (.grantReadACP GrantReadAcp)
        (.grantWrite GrantWrite)
        (.grantWriteACP GrantWriteAcp)))))

(defmethod put-bucket
  :AnalyticsConfigurationRequest
  [{:keys
    [^String Bucket
     ^AnalyticsConfiguration AnalyticsConfiguration
     ^String Id
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketAnalyticsConfiguration
    (client Profile)
    (.build
      (->
        (PutBucketAnalyticsConfigurationRequest/builder)
        (.bucket Bucket)
        (.analyticsConfiguration AnalyticsConfiguration)
        (.id Id)))))

(defmethod put-bucket
  :CorsRequest
  [{:keys
    [^String Bucket
     ^CORSConfiguration CorsConfiguration
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketCors
    (client Profile)
    (.build
      (->
        (PutBucketCorsRequest/builder)
        (.bucket Bucket)
        (.contentMD5 ContentMd5)
        (.corsConfiguration CorsConfiguration)))))

(defmethod put-bucket
  :InventoryConfigurationRequest
  [{:keys
    [^String Bucket
     ^String Id
     ^InventoryConfiguration InventoryConfiguration
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketInventoryConfiguration
    (client Profile)
    (.build
      (->
        (PutBucketInventoryConfigurationRequest/builder)
        (.bucket Bucket)
        (.id Id)
        (.inventoryConfiguration InventoryConfiguration)))))

(defmethod put-bucket
  :LifecycleRequest
  [{:keys
    [^String Bucket
     ^LifecycleConfiguration LifecycleConfiguration
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketLifecycle
    (client Profile)
    (.build
      (->
        (PutBucketLifecycleRequest/builder)
        (.bucket Bucket)
        (.lifecycleConfiguration LifecycleConfiguration)
        (.contentMD5 ContentMd5)))))

(defmethod put-bucket
  :LifecycleConfigurationRequest
  [{:keys
    [^String Bucket
     ^BucketLifecycleConfiguration BucketLifecycleConfiguration
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketLifecycleConfiguration
    (client Profile)
    (.build
      (->
        (PutBucketLifecycleConfigurationRequest/builder)
        (.bucket Bucket)
        (.lifecycleConfiguration BucketLifecycleConfiguration)))))

(defmethod put-bucket
  :LoggingRequest
  [{:keys
    [^String Bucket
     ^BucketLoggingStatus BucketLoggingStatus
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketLogging
    (client Profile)
    (.build
      (->
        (PutBucketLoggingRequest/builder)
        (.bucket Bucket)
        (.bucketLoggingStatus BucketLoggingStatus)))))

(defmethod put-bucket
  :MetricsConfigurationRequest
  [{:keys
    [^String Bucket
     ^MetricsConfiguration MetricsConfiguration
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketMetricsConfiguration
    (client Profile)
    (.build
      (->
        (PutBucketMetricsConfigurationRequest/builder)
        (.bucket Bucket)
        (.metricsConfiguration MetricsConfiguration)))))

(defmethod put-bucket
  :NotificationRequest
  [{:keys
    [^String Bucket
     ^NotificationConfigurationDeprecated NotificationConfigurationDeprecated
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketNotification
    (client Profile)
    (.build
      (->
        (PutBucketNotificationRequest/builder)
        (.bucket Bucket)
        (.contentMD5 ContentMd5)
        (.notificationConfiguration NotificationConfigurationDeprecated)))))

(defmethod put-bucket
  :NotificationConfigurationRequest
  [{:keys
    [^String Bucket
     ^NotificationConfiguration NotificationConfiguration
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketNotificationConfiguration
    (client Profile)
    (.build
      (->
        (PutBucketNotificationConfigurationRequest/builder)
        (.bucket Bucket)
        (.notificationConfiguration NotificationConfiguration)))))

(defmethod put-bucket
  PolicyRequest
  [{:keys
    [^String Bucket
     ^String Policy
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketPolicy
    (client Profile)
    (.build
      (->
        (PutBucketPolicyRequest/builder)
        (.bucket Bucket)
        (.policy Policy)
        (.contentMD5 ContentMd5)))))

(defmethod put-bucket
  :ReplicationRequest
  [{:keys
    [^String Bucket
     ^ReplicationConfiguration ReplicationConfiguration
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketReplication
    (client Profile)
    (.build
      (->
        (PutBucketReplicationRequest/builder)
        (.bucket Bucket)
        (.replicationConfiguration ReplicationConfiguration)
        (.contentMD5 ContentMd5)))))

(defmethod put-bucket
  :PaymentRequest
  [{:keys
    [^String Bucket
     ^RequestPaymentConfiguration RequestPaymentConfiguration
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketRequestPayment
    (client Profile)
    (.build
      (->
        (PutBucketRequestPaymentRequest/builder)
        (.bucket Bucket)
        (.requestPaymentConfiguration RequestPaymentConfiguration)
        (.contentMD5 ContentMd5)))))

(defmethod put-bucket
  :TaggingRequest
  [{:keys
    [^String Bucket
     ^Tagging Tagging
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketTagging
    (client Profile)
    (.build
      (->
        (PutBucketTaggingRequest/builder)
        (.bucket Bucket)
        (.tagging Tagging)
        (.contentMD5 ContentMd5)))))

(defmethod put-bucket
  :VersioningRequest
  [{:keys
    [^String Bucket
     ^VersioningConfiguration VersioningConfiguration
     ^String ContentMd5
     ^String Mfa
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketVersioning
    (client Profile)
    (.build
      (->
        (PutBucketVersioningRequest/builder)
        (.bucket Bucket)
        (.versioningConfiguration VersioningConfiguration)
        (.mfa Mfa)
        (.contentMD5 ContentMd5)))))

(defmethod put-bucket
  :WebsiteRequest
  [{:keys
    [^String Bucket
     ^WebsiteConfiguration WebsiteConfiguration
     ^String ContentMd5
     ^String RequestPayer
     ^String Profile]
    :or
    {^RequestPayer RequestPayer (RequestPayer/REQUESTER)
     ^String Profile            "default"}}]
  (.putBucketWebsite
    (client Profile)
    (.build
      (->
        (PutBucketWebsiteRequest/builder)
        (.bucket Bucket)
        (.websiteConfiguration WebsiteConfiguration)
        (.contentMD5 ContentMd5)))))


