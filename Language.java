package Model;

public class Language {
    private String languageName;
    private boolean isOriginal;  // Optional: to check if it's the original language of the movie
    private String region;       // Optional: region where the language is spoken

    public Language(String languageName, boolean isOriginal, String region) {
        this.languageName = languageName;
        this.isOriginal = isOriginal;
        this.region = region;
    }

    public String getLanguageName() {
        return languageName;
    }

    public boolean isOriginalLanguage() {
        return isOriginal;
    }

    public String getRegion() {
        return region;
    }

    @Override
    public String toString() {
        return "This movie is avilable by these Languages: " + languageName + (isOriginal ? " (Original)" : "") + ", Region: " + region;
    }
}

