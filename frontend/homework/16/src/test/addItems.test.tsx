
import {it,expect, describe} from  'vitest'
import { fireEvent, screen} from '@testing-library/react';
import { renderWithProviders } from './utils.test'; 
import '@testing-library/jest-dom';
import { AddItem } from '../todoList/AddItem';

describe('AddItem component', () => {
  it('renders the addItem correctly', () => {
    const { getByRole } = renderWithProviders(<AddItem />);
    const element = screen.getByText('Add Items')
    expect(element).toBeInTheDocument();
    expect(getByRole('textbox')).toBeInTheDocument();
  });

  it('calls setItemListHandler with correct input when search value changes', () => {
    const { getByRole } = renderWithProviders(<AddItem />);
    const searchInput = getByRole('textbox')
    fireEvent.change(searchInput, { target: { value: 'test input' } });
    expect(searchInput).toHaveValue('test input');
  });
});