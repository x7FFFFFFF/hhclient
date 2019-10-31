package org.noname.entities;

import org.jsoup.nodes.Element;

public class ResumeLink {
    private final String jobTitle;
    private  final String link;

    public ResumeLink(String jobTitle, String link) {
        this.jobTitle = jobTitle;
        this.link = link;
    }

    public ResumeLink(Element node) {
        jobTitle = node.text();
        link = node.attr("href");
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public String getLink() {
        return link;
    }

    @Override
    public String toString() {
        return  jobTitle;
    }
}
