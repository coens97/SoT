package com.coen.Dto;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ScoreBoardResults {
    private ScoreBoardResult[] results;

    public ScoreBoardResults(ScoreBoardResult[] results) {
        this.results = results;
    }

    public ScoreBoardResults() {

    }

    public ScoreBoardResult[] getResults() {
        return results;
    }

    public void setResults(ScoreBoardResult[] results) {
        this.results = results;
    }
}
