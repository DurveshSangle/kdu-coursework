export const getCurrentTimestamp = () => {
  const months = ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'];
  const days = ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'];

  const date = new Date();
  const dayOfWeek = days[date.getUTCDay()];
  const dayOfMonth = date.getUTCDate();
  const month = months[date.getUTCMonth()];
  const year = date.getUTCFullYear();
  const hours = date.getUTCHours();
  const minutes = date.getUTCMinutes();
  const seconds = date.getUTCSeconds();

  // Pad single-digit values with leading zero
  const pad = (num: number) => (num < 10 ? '0' : '') + num;

  return `${dayOfWeek}, ${dayOfMonth} ${month} ${year} ${pad(hours)}:${pad(minutes)}:${pad(seconds)} GMT`;
}