import { QuoteAppProp } from "../../types/quotes.types"
import "./Filter.scss";

export function Filter({ quoteAppProp }: { quoteAppProp: QuoteAppProp }) {
  const filters = quoteAppProp.filter.filters;
  const setFilters = quoteAppProp.filter.setFilters;

  const removeFilter = (tag:string) => {
    const updatedFilter = filters.filter((filterTag) => filterTag !== tag)
    setFilters(updatedFilter);
  }

  return (
    <div className="filter">
      <h2>Filter</h2>
      <div className="filter-tags">
        {
          filters.map((tag) => {
            return (
              <div key={tag} className="tag">
                <span>{tag}</span>
                <button onClick={()=> removeFilter(tag)}>X</button>
              </div>
            )
          })
        }
      </div>
      <hr />
    </div>
  )
}
