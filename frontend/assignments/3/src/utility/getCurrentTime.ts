export const getCurrentTime = () => {
  const date = new Date();
  let hours = date.getHours();
  const minutes = date.getMinutes();
  const ampm = hours >= 12 ? 'PM' : 'AM';
  hours = hours % 12;
  hours = hours ? hours : 12; // Handle midnight (0 hours)
  const formattedTime = `${hours}:${minutes < 10 ? '0' : ''}${minutes} ${ampm}`;
  return formattedTime;
};