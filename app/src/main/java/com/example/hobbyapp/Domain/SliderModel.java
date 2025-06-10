package com.example.hobbyapp.Domain;

public class SliderModel {

    // поля для заголовка, описания и изображения из ресурсов
    private String title;
    private String heading;
    private int imageResId; // это ID локального ресурса

    public SliderModel() {
        // пустой конструктор для Firebase (если потребуется)
    }

    // геттеры и сеттеры
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    // новый конструктор с локальным ресурсом
    public SliderModel(String title, String heading, int imageResId) {
        this.title = title;
        this.heading = heading;
        this.imageResId = imageResId;
    }
}
