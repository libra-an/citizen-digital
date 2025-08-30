package com.tekseries.server.core.admin.dashboardUser.user.model.request;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
public class PageableObject<T> {
    private List<T> items;
    private Integer page;
    private Integer size;

    public PageableObject(Page<T> p) {
        this.items = p.getContent();
        this.page = p.getTotalPages();
        this.size = p.getNumber();
    }
}
