package com.example.demo.views;

import com.example.demo.base.Command;
import com.example.demo.models.Object3D;

/*
 * Deze interface is de beschrijving van een view binnen het systeem.
 * Ze de andere classes voor meer uitleg.
 */
public interface View {
    void update(String event, Object3D data);
    void onViewClose(Command command);
}