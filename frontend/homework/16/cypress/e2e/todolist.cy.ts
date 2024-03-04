// test suite name
describe('Todo List Tests', function () {
  beforeEach(function() {
    // Assuming your application is served at localhost:3000
    cy.visit('http://localhost:5173');

    cy.get("#item-add-input").type("Todo One");
    cy.contains("Submit").click();

    cy.get("#item-add-input").type("Todo Two");
    cy.contains("Submit").click();
  });
  // Test case
  it('Add Todo Item To List', function (){
    cy.get("#item-add-input").type("Todo Three");

    cy.contains("Submit").click();
    cy.get("ul").contains("Todo Three").should('exist');
  });

  it('Deletes an item from the list', function () {
    // Find the item you want to delete by its text content
    cy.contains('Todo One').parent('div').parent('div').within(() => {
      // Click the delete button
      cy.get('button').click();
    });
    // Verify that the item has been deleted
    cy.contains('Todo One').should('not.exist');
  });

  it('Mark an item complete from the list', function () {
    // Find the item you want to delete by its text content
    cy.contains('Todo One').parent('div').within(() => {
      // Click the delete button
      cy.get('input[type="checkbox"]').check();
    });
    // Verify that the item has been deleted
    cy.contains('Todo One').should('have.css', 'text-decoration', 'line-through solid rgb(0, 0, 0)');
  });

  it('Check the working of clear button', function () {
    // Find the item you want to delete by its text content
    cy.contains('Todo One').parent('div').within(() => {
      // Click the delete button
      cy.get('input[type="checkbox"]').check();
    });
    // Verify that the item has been deleted
    cy.contains('Clear').click();

    cy.contains('Todo One').should('not.exist');
  });

  it('Check the search box functionality', function () {
    cy.contains("Item Lister").next().type('Todo One');
    cy.get("ul").contains("Todo One").should("exist");
    cy.get("ul").contains("Todo Two").should("not.exist");
  });
});