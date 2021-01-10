package com.codegym.music.formater;

import com.codegym.music.model.Blog;
import com.codegym.music.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Locale;
import java.util.Optional;

@Component
public class BlogFormatter implements Formatter<Optional<Blog>> {

    @Autowired
    private BlogService blogService;

    public BlogFormatter() {
    }

    @Override
    public String print(Optional<Blog> object, Locale locale) {
        return (object.isPresent() ? object.get().getId().toString() : "");
    }

    @Override
    public Optional<Blog> parse(String text, Locale locale) throws ParseException {
        final Long blogId = Long.valueOf(text);
        return this.blogService.findById(blogId);
    }
}
