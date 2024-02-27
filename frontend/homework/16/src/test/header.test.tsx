
import {it,expect, describe} from  'vitest'
import { fireEvent ,screen} from '@testing-library/react';
import { Header } from '../todoList/Header';
import { renderWithProviders } from './utils.test'; 
import '@testing-library/jest-dom';
describe('Header component', () => {
  it('renders the header correctly', () => {
    const { getByPlaceholderText } = renderWithProviders(<Header />);
        const element = screen.getByText('Item Lister')
        expect(element).toBeInTheDocument();
    expect(getByPlaceholderText('Search Items')).toBeInTheDocument();
  });

  it('add item with correct input when search value changes', () => {
    const { getByPlaceholderText } = renderWithProviders(<Header />);
    const searchInput = getByPlaceholderText('Search Items');
    fireEvent.change(searchInput, { target: { value: 'test input' } });
    expect(searchInput).toHaveValue('test input');
  });
});