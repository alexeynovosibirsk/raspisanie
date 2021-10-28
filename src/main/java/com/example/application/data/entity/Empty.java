package com.example.application.data.entity;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.provider.DataProviderListener;
import com.vaadin.flow.data.provider.ListDataProvider;

import java.util.ArrayList;
import java.util.List;

public class Empty extends Teachers {

    private Teachers empty = getEmpty();

    public Empty() {}

    public Empty(Teachers empty) {
        this.empty = empty;
    }

    public Teachers getEmpty() {
        return empty;
    }

    public void setEmpty(Teachers empty) {
        this.empty = empty;
    }
}
