import React from "react";

export interface QuoteAppProp {
    allQuotes: QuotesStateProp;
    quotes: QuotesStateProp;
    filter: FiltersStateProp;
}

export interface QuotesStateProp {
    quotes: ApiQuote[];
    setQuotes: React.Dispatch<React.SetStateAction<ApiQuote[]>>
}

export interface FiltersStateProp {
    filters: string[];
    setFilters: React.Dispatch<React.SetStateAction<string[]>>
}

export interface ApiQuote {
    _id: string;
    content: string;
    author: string;
    tags: string[];
    authorSlug: string;
    length: number;
    dateAdded: string;
    dateModified: string;
}
export interface QuoteProp {
    quote: ApiQuote;
    filters: string[];
    setFilters: React.Dispatch<React.SetStateAction<string[]>>;
}