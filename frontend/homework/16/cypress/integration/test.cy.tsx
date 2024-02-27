import React from 'react';
import { mount } from '@cypress/react';
import { AddItem } from '../../src/todoList/AddItem'; // Update the path

describe('AddItem Component', () => {
  beforeEach(() => {
    const list = [];
    cy.stub(window, 'fetch').withArgs('path_to_your_api').resolves({ json: () => ({ list }) });
    mount(<AddItem />);
  });

  it('should add an item to the list when "Submit" button is clicked', () => {
    const newItemText = 'Test Item';

    cy.get('#item-add-input').type(newItemText);
    cy.get('button').contains('Submit').click();

    cy.contains(newItemText).should('exist');
  });

  it('should clear completed items when "Clear" button is clicked', () => {
    const newItemText = 'Test Item';

    cy.get('#item-add-input').type(newItemText);
    cy.get('button').contains('Submit').click();

    // Mark the item as completed
    cy.contains(newItemText).parent().find('input[type="checkbox"]').check();

    cy.get('button').contains('Clear').click();

    cy.contains(newItemText).should('not.exist');
  });
});
