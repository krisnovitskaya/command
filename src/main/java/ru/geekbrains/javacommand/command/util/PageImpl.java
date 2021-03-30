package ru.geekbrains.javacommand.command.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
public class PageImpl<T> {

    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;

    public PageImpl() {

    }

    public PageImpl(Page<T> page)  {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.size = page.getSize();
        this.number = page.getNumber();
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content =  content;
    }

    public boolean hasContent() {
        return content.isEmpty();
    }

}
