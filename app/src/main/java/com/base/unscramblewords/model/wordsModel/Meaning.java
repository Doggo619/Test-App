package com.base.unscramblewords.model.wordsModel;

import java.util.List;

public class Meaning {
    private List<Definition> definitions;
    private List<String> synonyms;
    private List<String> antonyms;

    public Meaning(List<Definition> definitions, List<String> synonyms, List<String> antonyms) {
        this.definitions = definitions;
        this.synonyms = synonyms;
        this.antonyms = antonyms;
    }

    public List<Definition> getDefinitions() {
        return definitions;
    }

    public void setDefinitions(List<Definition> definitions) {
        this.definitions = definitions;
    }

    public List<String> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(List<String> synonyms) {
        this.synonyms = synonyms;
    }

    public List<String> getAntonyms() {
        return antonyms;
    }

    public void setAntonyms(List<String> antonyms) {
        this.antonyms = antonyms;
    }
}
