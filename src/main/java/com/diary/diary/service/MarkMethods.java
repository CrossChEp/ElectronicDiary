package com.diary.diary.service;

import com.diary.diary.config.DateConfig;
import com.diary.diary.entity.MarkEntity;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
@Service
public class MarkMethods {
    public List<MarkEntity> filterMarksByDate(List<MarkEntity> marks, String date) throws ParseException {
        date = new SimpleDateFormat(DateConfig.DEFAULT_DATE_FORMAT_PATTERN).parse(date).toString();
        List<MarkEntity> filteredMarks = new ArrayList<>();
        for(var mark: marks) {
            if(new SimpleDateFormat(DateConfig.DEFAULT_DATE_FORMAT_PATTERN)
                    .parse(mark.getDate().toString()).toString().equals(date)) {
                filteredMarks.add(mark);
            }
        }
        return filteredMarks;
    }

    public List<MarkEntity> filterMarksBySubject(List<MarkEntity> marks, String subjectName) {
        return marks.stream().filter(mark -> mark.getSubject().getName().equals(subjectName)).toList();
    }
}
