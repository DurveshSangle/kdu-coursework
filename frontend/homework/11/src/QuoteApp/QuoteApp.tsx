import { useEffect, useState } from "react";
import { Filter } from "./Filter/Filter";
import { ListQuotes } from "./ListQuotes/ListQuotes";
import { NewQuote } from "./NewQuote/NewQuote";
import { ApiQuote, QuoteAppProp } from "../types/quotes.types";

import "./QuoteApp.scss";

export function QuoteApp() {
    const [allQuotes, setAllQuotes] = useState<ApiQuote[]>([]);
    const [quotes, setQuotes] = useState<ApiQuote[]>([]);
    const [filters, setFilters] = useState<string[]>([]);
    
    const quoteAppProp:QuoteAppProp = {
        allQuotes: {
            quotes: allQuotes,
            setQuotes: setAllQuotes
        },
        quotes: {
            quotes: quotes,
            setQuotes: setQuotes
        },
        filter: {
            filters: filters,
            setFilters: setFilters
        }
    }

    const fetchOnLoad = () => {
        fetch("https://api.quotable.io/quotes/random?limit=3")
            .then((response) => response.json())
            .then((data) => {
                console.log("data:", data);
                setAllQuotes(data);
            })
    }

    useEffect(fetchOnLoad, []);

    useEffect(() => {
        setQuotes(allQuotes.filter((quote) => {
            let valid = true;
            filters.forEach((tag) => {
                if (!quote.tags.includes(tag)) {
                    valid = false;
                }
            })
            return valid;
        }));
    },[filters,allQuotes])

    return (
        <div className="quotes-app">
            <NewQuote quoteAppProp={quoteAppProp} /> 
            <Filter quoteAppProp={quoteAppProp} />
            <ListQuotes quoteAppProp={quoteAppProp}/>
        </div>
    )
}
