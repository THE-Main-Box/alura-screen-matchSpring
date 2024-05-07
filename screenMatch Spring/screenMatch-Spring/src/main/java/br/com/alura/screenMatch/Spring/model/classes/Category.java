package br.com.alura.screenMatch.Spring.model.classes;

public enum Category {

    ACTION("Action"),
    ROMANCE("Romance"),
    CRIME("Crime"),
    COMEDY("Comedy"),
    DRAMA("Drama"),
    ADVENTURE("Adventure"),
    FICTION("Fiction")
    ;

    private String omdbCategory;

    Category(String omdbCategory){
        this.omdbCategory = omdbCategory;
    }

    public static Category fromString(String text) {
        for (Category category : Category.values()) {
            if (category.omdbCategory.equalsIgnoreCase(text)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a string fornecida: " + text);
    }


}
