
import {it,expect, describe} from  'vitest'
import { renderWithProviders } from '../utils.'; 
import '@testing-library/jest-dom';
import { ItemList } from '../todoList/ItemList';
describe('ItemList component', () => {
  it('renders the itemList correctly', () => {
    const { getByText } = renderWithProviders(<ItemList />);
    const element = getByText('Items')
    expect(element).toBeInTheDocument();
  });
});