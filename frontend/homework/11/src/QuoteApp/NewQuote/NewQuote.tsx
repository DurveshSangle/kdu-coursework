import { useState } from "react";
import { QuoteAppProp } from "../../types/quotes.types";
import "./NewQuote.scss";
import { TailSpin } from "react-loader-spinner";

export function NewQuote({ quoteAppProp }: { quoteAppProp: QuoteAppProp }) {
    const setAllQuotes = quoteAppProp.allQuotes.setQuotes;
    const allQuotes = quoteAppProp.allQuotes.quotes;

    const [loading, setLoading] = useState(false);

    const addNewQuote = () => {
        setLoading(true);
        fetch("https://api.quotable.io/quotes/random")
            .then((response) => response.json())
            .then((data) => {
                setAllQuotes([...data, ...allQuotes]);
                setLoading(false);
            })
            .catch((error) => {
                console.error('Error fetching new quote:', error);
                setLoading(false);
            });
    }

    return (
        <div className="new-quote">
            <button onClick={addNewQuote} disabled={loading}>New Quote</button>
            {loading && (
                <div className="loading-icon">
                    <TailSpin color="red" radius={"2"} />
                </div>
            )}
        </div>
    )
}
