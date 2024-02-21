
import { QuoteAppProp, QuoteProp } from "../../types/quotes.types";
import { Quote } from "../Quote/Quote";
import "./ListQuotes.scss";

export function ListQuotes({ quoteAppProp }: { quoteAppProp: QuoteAppProp }) {
    const quotes = quoteAppProp.quotes.quotes;
    const filters = quoteAppProp.filter.filters;
    const setFilters = quoteAppProp.filter.setFilters;
    
    return (
        <div className="quotes-list">
            {
                quotes.map((quote) => {
                    const quoteProp: QuoteProp = {
                        quote: quote,
                        filters: filters,
                        setFilters:setFilters
                    }
                    return (
                        <Quote key={quote._id} quoteProp={quoteProp}/>
                    )
                })
            }
        </div>
    )
}
