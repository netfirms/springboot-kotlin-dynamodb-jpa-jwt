package com.exp.uss.configuration

import com.amazonaws.auth.AWSCredentials
import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.client.builder.AwsClientBuilder
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.datamodeling.ConversionSchemas
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverterFactory
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.util.StringUtils


/**
 * User: taweechaimaklay
 * Date: 28/12/2019 AD
 * Time: 13:34
 */
//@Profile("dev")
@Configuration
@EnableDynamoDBRepositories(basePackages = ["com.exp.uss.repository"])
class DynamoDBConfigDev {
    @Value("\${amazon.access.key}")
    private val awsAccessKey: String? = null

    @Value("\${amazon.access.secret-key}")
    private val awsSecretKey: String? = null

    @Value("\${amazon.region}")
    private val awsRegion: String? = null

    @Value("\${amazon.end-point.url}")
    private val awsDynamoDBEndPoint: String? = null

    @Bean
    fun amazonDynamoDB(): AmazonDynamoDB? {
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(AWSStaticCredentialsProvider(BasicAWSCredentials(awsAccessKey, awsSecretKey)))
                .withEndpointConfiguration(AwsClientBuilder.EndpointConfiguration(this.awsDynamoDBEndPoint, this.awsRegion))
                .build()
    }

    @Bean
    fun dynamoDBMapperConfig(): DynamoDBMapperConfig? {
        val builder = DynamoDBMapperConfig.Builder()
        builder.paginationLoadingStrategy = DynamoDBMapperConfig.PaginationLoadingStrategy.LAZY_LOADING
        builder.typeConverterFactory = DynamoDBTypeConverterFactory.standard()
        builder.conversionSchema = ConversionSchemas.V2
        return builder.build()
    }
}