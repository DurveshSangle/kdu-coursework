import { QuoteProp } from "../../types/quotes.types"
import "./Quote.scss";

export function Quote({ quoteProp }: { quoteProp: QuoteProp } ) {
    const quote = quoteProp.quote;
    const filters = quoteProp.filters;
    const setFilters = quoteProp.setFilters;

    const addFilterTags = (tag:string) => {
        if (filters.includes(tag)) return;
        setFilters([...filters,tag])
    }

    return (
        <div className="quote">
            <h1>{quote.content}</h1>
            <p>~{quote.author}</p>
            <p>{quote.dateAdded}</p>

            <div>
                {
                    quote.tags.map((q) => {
                        return <button key={q} onClick={()=> addFilterTags(q)}>{q}</button>
                    })
                }
            </div>
        </div>
    )
}
