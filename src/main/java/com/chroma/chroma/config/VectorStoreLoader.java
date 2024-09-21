package com.chroma.chroma.config;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class VectorStoreLoader {

    @Value("classpath:/doc1.pdf")
    Resource pdfResource;

//    @Value("classpath:/story.txt")
//    Resource txtResource;
//
//    @Value("classpath:/story.md")
//    Resource mdResource;

    @Autowired
    VectorStore vectorStore;

    @PostConstruct
    public void loadData(){
        List<Document> documents = new ArrayList<>();

        TikaDocumentReader reader = new TikaDocumentReader(pdfResource);
        documents.addAll(reader.get());
        vectorStore.add(documents);
        vectorStore.add(new TokenTextSplitter(300, 300, 5, 1000, true).split(documents));

        System.out.println("Added documents to vector store");

    }


}
