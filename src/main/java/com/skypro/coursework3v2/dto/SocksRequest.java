package com.skypro.coursework3v2.dto;

import com.skypro.coursework3v2.model.Colour;
import com.skypro.coursework3v2.model.Size;

public class SocksRequest {
    private Colour colour;
    private Size size;
    private int cotton;
    private int quantity;

    public Colour getColour() {
        return colour;
    }

    public void setColour(Colour colour) {
        this.colour = colour;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public int getCotton() {
        return cotton;
    }

    public void setCotton(int cotton) {
        this.cotton = cotton;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}