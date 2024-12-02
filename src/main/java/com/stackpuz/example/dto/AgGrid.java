package com.stackpuz.example.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgGrid<T> {
    private long count;
    private List<T> data;

    public AgGrid(long count, List<T> data) {
        this.count = count;
        this.data = data;
    }
}