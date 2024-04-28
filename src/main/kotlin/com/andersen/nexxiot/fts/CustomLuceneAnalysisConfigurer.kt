package com.andersen.nexxiot.fts

import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurationContext
import org.hibernate.search.backend.lucene.analysis.LuceneAnalysisConfigurer

class CustomLuceneAnalysisConfigurer : LuceneAnalysisConfigurer {
    override fun configure(context: LuceneAnalysisConfigurationContext) {
        println(context.availableTokenFilters())
        context.analyzer("name").custom()
            .tokenizer("standard")
            .tokenFilter("lowercase")
            .tokenFilter("snowballPorter")
            .tokenFilter("nGram")
            .param("minGramSize", "3")
            .param("maxGramSize","10")
            .tokenFilter("asciiFolding")

        context.normalizer("custom_search_normalizer").custom()
            .tokenFilter("lowercase")
            .tokenFilter("asciiFolding")
    }
}
