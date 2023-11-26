package com.sknwl.shareknowledge.domain.usecase;

import com.sknwl.shareknowledge.domain.entity.Content;
import com.sknwl.shareknowledge.domain.entity.ContentRating;
import com.sknwl.shareknowledge.domain.entity.Language;
import com.sknwl.shareknowledge.domain.entity.enums.ContentType;
import com.sknwl.shareknowledge.domain.entity.enums.CostType;
import com.sknwl.shareknowledge.domain.entity.enums.SortType;
import com.sknwl.shareknowledge.repositories.ContentRepository;
import com.sknwl.shareknowledge.repositories.CoverImageRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentUseCase {

    private final ContentRepository contentRepository;
    private final CoverImageRepository coverImageRepository;
    private final CoreUseCase coreUseCase;

    public ContentUseCase(ContentRepository contentRepository, CoverImageRepository coverImageRepository, CoreUseCase coreUseCase) {
        this.contentRepository = contentRepository;
        this.coverImageRepository = coverImageRepository;
        this.coreUseCase = coreUseCase;
    }

    public Content register(Content content) {
        content.setPublishedDateTime(LocalDateTime.now());
        var coverImage = coverImageRepository.getRandomPhoto(content);
        content.setCoverImage(coverImage);
        return contentRepository.register(content);
    }

    public Content update(Content content) {
        return contentRepository.update(content);
    }

    public Page<Content> list(Integer pageNumber, Integer pageSize) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        return contentRepository.list(pageable);
    }

    public Page<Content> list(Integer pageNumber, Integer pageSize, SortType sort, String keyphrase, Integer minRatings, List<ContentType> contentTypes, List<CostType> costTypes, Long sourceId, List<Long> languageIds, Integer minDuration, Integer maxDuration, List<String> fields) {
        var pageable = PageRequest.of(pageNumber, pageSize);
        if (contentTypes == null) {
            contentTypes = List.of(ContentType.values());
        }
        if (fields != null) {
            fields = fields.stream()
                    .map(String::toLowerCase)
                    .collect(Collectors.toList());
        }
        if (languageIds == null) {
            languageIds = coreUseCase.listLanguage(null)
                    .stream()
                    .map(Language::getId)
                    .toList();
        }
        Boolean isFree = null;
        if (costTypes!= null && costTypes.size() == 1) {
            CostType type = costTypes.get(0);
            isFree = CostType.FREE.equals(type);
        }
        return contentRepository.list(pageable, sort, keyphrase, minRatings, contentTypes, isFree,  sourceId, languageIds, minDuration, maxDuration, fields);
    }

    public void delete(Long id) {
        contentRepository.hardDelete(id);
    }

    public Content get(Long id) {
        return contentRepository.get(id);
    }

    public ContentRating registerRating(ContentRating contentRating) {
        contentRating.setRatingDateTime(LocalDateTime.now());
        return contentRepository.registerRating(contentRating);
    }

    public ContentRating updateRating(ContentRating contentRating) {
        return contentRepository.updateRating(contentRating);
    }

    public void deleteRating(Long id) {
        contentRepository.deleteRating(id);
    }

    public ContentRating getRating(Long id) {
        return contentRepository.getRating(id);
    }

    public List<ContentRating> listRating() {
        return contentRepository.listRating();
    }
}
