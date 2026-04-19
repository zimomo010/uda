package com.bu.admin.business.uda.utils;

import com.bu.admin.business.uda.bo.PublishTimeCount;
import com.bu.admin.business.uda.bo.TagCount;
import com.bu.admin.business.uda.bo.TendCount;
import com.bu.admin.business.uda.po.UdaVideoEntity;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class UdaUtil {

    public static List<TagCount> countTags(List<UdaVideoEntity> videoList) {
        if (videoList == null || videoList.isEmpty()) {
            return Collections.emptyList();
        }

        return videoList.stream()
                .map(UdaVideoEntity::getTags)
                .filter(Objects::nonNull)
                .flatMap(tags -> Arrays.stream(tags.split("\\|")))
                .map(String::trim)
                .filter(tag -> !tag.isEmpty())
                .filter(tag -> !"[none]".equalsIgnoreCase(tag))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new TagCount(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(TagCount::getCount).reversed())
                .collect(Collectors.toList());
    }

    public static List<PublishTimeCount> countPublishHour(List<UdaVideoEntity> videoList) {
        if (videoList == null || videoList.isEmpty()) {
            return Collections.emptyList();
        }

        return videoList.stream()
                .map(UdaVideoEntity::getPublishTime)
                .filter(Objects::nonNull)
                .map(publishTime -> Instant.parse(publishTime)
                        .atZone(ZoneId.systemDefault())
                        .getHour())
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new PublishTimeCount(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(PublishTimeCount::getHour))
                .collect(Collectors.toList());
    }

    private static final DateTimeFormatter TRENDING_DATE_FORMATTER =
            DateTimeFormatter.ofPattern("yy.dd.MM");

    public static List<TendCount> countPublishTrendingDayDiff(List<UdaVideoEntity> videoList) {
        if (videoList == null || videoList.isEmpty()) {
            return Collections.emptyList();
        }

        return videoList.stream()
                .filter(Objects::nonNull)
                .filter(v -> v.getPublishTime() != null && v.getTrendingDate() != null)
                .map(v -> {
                    LocalDate publishDate = Instant.parse(v.getPublishTime())
                            .atOffset(ZoneOffset.UTC)
                            .toLocalDate();

                    LocalDate trendingDate = LocalDate.parse(v.getTrendingDate(), TRENDING_DATE_FORMATTER);

                    long dayDiff = ChronoUnit.DAYS.between(publishDate, trendingDate);
                    return dayDiff;
                })
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new TendCount(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(TendCount::getDayDiff))
                .collect(Collectors.toList());
    }
}