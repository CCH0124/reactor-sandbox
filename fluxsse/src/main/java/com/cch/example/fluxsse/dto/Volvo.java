package com.cch.example.fluxsse.dto;

import java.util.Map;

public record Volvo(String event, String id, int retry, Map<String, Object> data) {
}