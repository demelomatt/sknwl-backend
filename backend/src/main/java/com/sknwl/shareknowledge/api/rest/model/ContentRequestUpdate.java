package com.sknwl.shareknowledge.api.rest.model;

import com.sknwl.shareknowledge.domain.entity.enums.ContentType;

import java.util.List;

public record ContentRequestUpdate(
        Long id,
        String name,
        String description,
        ContentType contentType,
        String url,
        SourcePayload source,
        List<String> authors,
        List<String> subjects,
        StudyFieldPayload studyField,
        LanguagePayload language,
        Long durationMinutes,
        String coverUri,
        MoneyPayload price) {
}