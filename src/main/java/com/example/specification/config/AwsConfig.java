//package com.example.specification.config;
//
//import com.amazonaws.auth.AWSCredentials;
//import com.amazonaws.auth.AWSCredentialsProvider;
//import com.amazonaws.auth.BasicAWSCredentials;
//import com.amazonaws.regions.Regions;
//import com.amazonaws.services.rds.AmazonRDS;
//import com.amazonaws.services.rds.AmazonRDSClientBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class AwsConfig {
//
//    class CredProvider implements AWSCredentialsProvider {
//
//        AWSCredentials credentials = new BasicAWSCredentials(
//                "AKIAXGZUWJMJC4LXUJFU",
//                "o7PjFgPIRuxgLj6CtWZm4HMafvLc2m6O6yrcZz+L"
//        );
//
//        @Override
//        public AWSCredentials getCredentials() {
//            return credentials;
//        }
//
//        @Override
//        public void refresh() {
//
//        }
//    }
//
//
//
//    @Bean
//    public AmazonRDS build() {
//        return AmazonRDSClientBuilder.standard()
//                .withCredentials(new CredProvider())
//                .withRegion(Regions.US_EAST_1)
//                .build();
//    }
//
//
//}
