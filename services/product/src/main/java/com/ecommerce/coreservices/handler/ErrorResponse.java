package com.ecommerce.coreservices.handler;
import java.util.Map;
public record ErrorResponse(
        Map<String, String> errors //attribute and message
) {
}

