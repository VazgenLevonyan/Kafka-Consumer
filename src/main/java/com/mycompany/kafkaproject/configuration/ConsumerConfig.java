package com.mycompany.kafkaproject.configuration;

import com.mycompany.kafkaproject.model.dto.CreateNotificationDto;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConsumerConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.activity.groupId}")
    private String groupId;

    @Value("${kafka.activity.topic.name}")
    private String notificationTopic;

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, CreateNotificationDto> kafkaListenerContainerFactory() {
        var container = new ConcurrentKafkaListenerContainerFactory<String, CreateNotificationDto>();
        container.setConsumerFactory(consumerFactory());
        return container;
    }

    @Bean
    public ConsumerFactory<String, CreateNotificationDto> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                getConsumerProps(),
                new StringDeserializer(),
                new JsonDeserializer<>(CreateNotificationDto.class));
    }

    private Map<String, Object> getConsumerProps() {
        var props = new HashMap<String, Object>();
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(org.apache.kafka.clients.consumer.ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        props.put(JsonDeserializer.KEY_DEFAULT_TYPE, String.class);
        props.put(JsonDeserializer.VALUE_DEFAULT_TYPE, CreateNotificationDto.class);
        props.put(JsonDeserializer.TRUSTED_PACKAGES, "*");
        props.put(JsonDeserializer.TYPE_MAPPINGS, notificationTopic + ":com.mycompany.kafkaproject.model.dto.CreateNotificationDto");
        props.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        props.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
        return props;
    }
}
