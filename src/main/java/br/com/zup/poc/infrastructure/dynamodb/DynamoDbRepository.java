package br.com.zup.poc.infrastructure.dynamodb;

import br.com.zup.poc.domain.model.Message;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DynamoDbRepository {

    private final DynamoDbClient dynamoDbClient;

    public DynamoDbRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void saveMessage(Message message) {
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("messageId", AttributeValue.builder().s(message.getId()).build());
        itemValues.put("content", AttributeValue.builder().s(message.getContent()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName("TesteDB")
                .item(itemValues)
                .build();

        dynamoDbClient.putItem(request);
    }
}
