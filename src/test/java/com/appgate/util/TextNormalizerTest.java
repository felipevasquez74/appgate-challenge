package com.appgate.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TextNormalizerTest {

    @Test
    void shouldNormalizeTextCorrectly() {
        String input = "   HEllo WOrld    ";
        String normalized = TextNormalizer.normalize(input);

        assertEquals("hello world", normalized);
    }

    @Test
    void shouldHandleNullInput() {
        String normalized = TextNormalizer.normalize(null);

        assertEquals("", normalized);
    }
}