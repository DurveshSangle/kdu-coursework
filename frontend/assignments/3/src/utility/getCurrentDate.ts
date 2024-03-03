export function getCurrentDateFormatted() {
  const currentDate = new Date();
  // Format date with abbreviated month name
  const options = { day: 'numeric', month: 'short', year: 'numeric' };
  return currentDate.toLocaleDateString('en-US', options);
}