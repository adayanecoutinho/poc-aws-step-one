package br.com.zup.poc_aws_step_one.infrastructure.dynamodb;

import br.com.zup.poc_aws_step_one.domain.model.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;

import java.util.HashMap;
import java.util.Map;

@Repository
public class DynamoDbRepository {

    @Value("${aws.dynamodb.table-name}")
    private String tableName;

    private final DynamoDbClient dynamoDbClient;

    public DynamoDbRepository(DynamoDbClient dynamoDbClient) {
        this.dynamoDbClient = dynamoDbClient;
    }

    public void saveMessage(Message message) {
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("messageId", AttributeValue.builder().s(message.getId()).build());
        itemValues.put("content", AttributeValue.builder().s(message.getContent()).build());

        PutItemRequest request = PutItemRequest.builder()
                .tableName(tableName)
                .item(itemValues)
                .build();

        dynamoDbClient.putItem(request);
    }
}
