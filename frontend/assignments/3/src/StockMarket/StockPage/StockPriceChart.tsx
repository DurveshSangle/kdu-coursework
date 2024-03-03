import { useEffect, useRef, useState } from "react";
import { PriceStateProp } from "../../type/stokcMarket.type";

export function StockPriceChart({ priceStateProp }: Readonly<{ priceStateProp:PriceStateProp }>) {
  // Pre-populate data array with 30 items with price 0
  const initialData = Array.from({ length: 30 }, (_, index) => ({
    time: index * 5, // Assuming each time interval is 5 seconds
    price: 0,
  }));
  const basePrice = useRef(priceStateProp?.basePrice);
  const [data, setData] = useState(initialData);
  const [previousPrices, setPreviousPrices] = useState<number[]>([]);
  const [currentIndex, setCurrentIndex] = useState(0);

  useEffect(() => {
    const interval = setInterval(() => {
      // Update the price of the current index
      const newData = [...data];
      const newPreviousPrices = [...previousPrices];
      const isPositiveChange = Math.random() < 0.5;

      // Generate a random price change (+/- 1000)
      const randomChange = Math.round(Math.random() * 1000);
      let newPrice = isPositiveChange
        ? basePrice.current + randomChange 
        : basePrice.current - randomChange;
      newPrice = Math.max(parseFloat(newPrice.toFixed(2)), 0);

      newData[currentIndex].price = newPrice;
      if (newPreviousPrices[currentIndex] === undefined) {
        newPreviousPrices[currentIndex] = basePrice.current;
      }
      basePrice.current = newPrice;
      setData(newData);
      setPreviousPrices(newPreviousPrices);
      priceStateProp?.setBasePrice((prevState: number) => {
        priceStateProp?.setPrevStatePrice(prevState);
        return basePrice.current;
      });
      // Move to the next index, looping back to 0 when reaching the end
      setCurrentIndex((prevIndex) => {
        if ((prevIndex + 1) % data.length === 0) {
          setData(initialData);
          setPreviousPrices([]);
          setCurrentIndex(0);
        }
        return (prevIndex + 1) % data.length;
      })
    }, 2000);

    // Cleanup function to clear the interval when component unmounts
    return () => clearInterval(interval);
  }, [data, previousPrices, currentIndex, initialData, priceStateProp]); 

  return (
    <div style={{ width: '1000px', height: '500px', margin: '100px auto' }}>
      <svg viewBox="0 0 800 500" style={{ width: '100%', height: '100%', padding:"30px" }}>
        {/* Dotted grid lines */}
        {Array.from({ length: 5 }, (_, rowIndex) => (
          <>
            <line
              key={`horizontal_${rowIndex}`}
              x1="0"
              y1={rowIndex * 100}
              x2="800"
              y2={rowIndex * 100}
              stroke="black"
              strokeDasharray="2 2"
            />
            {Array.from({ length: 7 }, (_, colIndex) => (
              <line
                key={`vertical_${rowIndex}_${colIndex}`}
                x1={(colIndex + 1) * 100}
                y1={rowIndex * 100}
                x2={(colIndex + 1) * 100}
                y2={(rowIndex + 1) * 100}
                stroke="black"
                strokeDasharray="2 2"
              />
            ))}
          </>
        ))}
        {/* Y-axis label */}
        {Array.from({ length: 6 }, (_, index) => (
          <text key={`ylabel_${index}`} x="0" y={500 - index * 100} textAnchor="end" fontSize="20" fill="black">
            {index * 4000} Rs
          </text>
        ))}
        {/* X-axis */}
        <line x1="0" y1="500" x2="800" y2="500" stroke="black" />
        {/* Y-axis */}
        <line x1="0" y1="0" x2="0" y2="500" stroke="black" />
        {/* Bars */}
        {data.map((entry, index) => (
          <rect
            key={entry.price}
            x={index * 25}
            y={500 - (entry.price/40)}
            width="20"
            height={entry.price/40}
            fill={entry.price > previousPrices[index] ? '#b2f2bb' : '#ffc9c9'}
          />
        ))}
      </svg>
    </div>
  );
}
