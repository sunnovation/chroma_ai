package com.chroma.chroma.controller;

import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/rag/")
public class ChromaController {
    @Autowired
    VectorStore vectorStore;


    @GetMapping("/")
    public String search(@RequestParam("question")String question){
//        "investigation"

        StringBuilder bul=new StringBuilder();
        List<Document> documents = vectorStore.similaritySearch(question);
        documents.stream().forEach(doc->{
            bul.append(doc.getContent());
        });
       return bul.toString();
    }


}
