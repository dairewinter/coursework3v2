package com.skypro.coursework3v2.model;

import java.util.Objects;

public class Sock {
    private final Colour colour;
    private final Size size;
    private final int cotton;

    public Sock(Colour colour, Size size, int cotton) {
        this.colour = colour;
        this.size = size;
        this.cotton = cotton;
    }

    public Colour getColour() {
        return colour;
    }

    public Size getSize() {
        return size;
    }

    public int getCotton() {
        return cotton;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sock sock = (Sock) o;
        return cotton == sock.cotton && colour == sock.colour && size == sock.size;
    }

    @Override
    public int hashCode() {
        return Objects.hash(colour, size, cotton);
    }
}
