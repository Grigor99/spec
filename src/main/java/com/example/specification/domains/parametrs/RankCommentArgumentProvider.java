package com.example.specification.domains.parametrs;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class RankCommentArgumentProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(1D, "good"),
                Arguments.of(2D, "awesome"),
                Arguments.of(3D, "wonderful"),
                Arguments.of(4D, "good")

        );
    }
}