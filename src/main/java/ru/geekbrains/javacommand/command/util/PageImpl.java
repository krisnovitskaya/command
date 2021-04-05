package ru.geekbrains.javacommand.command.util;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PageImpl<T> {

    private List<T> content;
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;


    public PageImpl(Page<T> page)  {
        this.content = page.getContent();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.size = page.getSize();
        this.number = page.getNumber();
    }

    public boolean hasContent() {
        return content.isEmpty();
    }

}
