interface IRecipe {
    image: string;
    name: string;
    rating: number;
    cuisine: string;
    ingredients: string[];
    difficulty: string;
    timeTaken: number;
    calorieCount: number;
}

interface IResponseRecipe {
    id: number;
    name: string;
    ingredients: string[];
    instructions: string[];
    prepTimeMinutes: number;
    cookTimeMinutes: number;
    servings: number;
    difficulty: string;
    cuisine: string;
    caloriesPerServing: number;
    tags: string[];
    userId: number;
    image: string;
    rating: number;
    reviewCount: number;
    mealType: string[];
}

class RecipeStore {
    recipes: IRecipe[];

    constructor() {
        this.recipes = [];
    }

    fetchRecipesFromAPI = async () => {
        console.log("Inside fetch recipes");
        return new Promise((resolve, reject) => {
            fetch("https://dummyjson.com/recipes")
            .then((response) => {
                console.log("fetched recipes non-json");
                return response.json();
            })
            .then(data => {
                // Assuming data is an object with a 'recipes' array
                const recipeData: IResponseRecipe[] = data.recipes;
                recipeData.map(recipe => {
                    // Map the response data to your IRecipe interface
                    const obj = {
                        image: recipe.image,
                        name: recipe.name,
                        rating: recipe.rating,
                        cuisine: recipe.cuisine,
                        ingredients: recipe.ingredients,
                        difficulty: recipe.difficulty,
                        timeTaken: recipe.cookTimeMinutes + recipe.prepTimeMinutes,
                        calorieCount: recipe.caloriesPerServing
                    };
                    this.recipes.push(obj);
                    console.log("pushed recipes");
                    resolve("");
                });
            })

        })
        
    }

    async searchRecipes(query: string) {
        console.log("inside search recipes");
        return new Promise<IRecipe[]>((resolve, reject) => {
            fetch("https://dummyjson.com/recipes/search?q=" + query)
            .then(response => {
                console.log("fetched  recipe from "+ query + " non-json");
                return response.json();
            })
            .then(data => {
                return data.recipes as IResponseRecipe[];
            })
            .then(data => {
                let result: IRecipe[] = data.map(recipe => {
                    // Map the response data to your IRecipe interface
                    return {
                        image: recipe.image,
                        name: recipe.name,
                        rating: recipe.rating,
                        cuisine: recipe.cuisine,
                        ingredients: recipe.ingredients,
                        difficulty: recipe.difficulty,
                        timeTaken: recipe.cookTimeMinutes + recipe.prepTimeMinutes,
                        calorieCount: recipe.caloriesPerServing
                    };
                });
                resolve(result);
            })
        })
    }

    printAllRecipes() {
        console.log(this.recipes);
    }
}

async function driverFunction(){
    let recipeStore :RecipeStore = new RecipeStore();
    await recipeStore.fetchRecipesFromAPI();
    await recipeStore.searchRecipes("Classic Margherita Pizza").then((result) => {
        console.log(result);
    });
    recipeStore.printAllRecipes();
}

driverFunction();

//////////////////   DOM    ////////////////////////

// Function to create the HTML structure for each recipe
function createRecipeElement(recipe: IRecipe) {
    // Create the main container div
    const eachRecipeDiv = document.createElement('div');
    eachRecipeDiv.classList.add('each-recipe');

    // Create the non-rating div
    const nonRatingDiv = document.createElement('div');
    nonRatingDiv.classList.add('non-rating');

    // Create the food image div
    const foodImgDiv = document.createElement('div');
    foodImgDiv.classList.add('food-img');
    const foodImg = document.createElement('img');
    foodImg.src = recipe.image;
    foodImg.alt = recipe.name;
    foodImgDiv.appendChild(foodImg);

    // Create the food name div
    const foodNameDiv = document.createElement('div');
    foodNameDiv.classList.add('food-name');
    foodNameDiv.textContent = recipe.name;

    // Create the ingredients div
    const ingredientsDiv = document.createElement('div');
    ingredientsDiv.classList.add('ingredients');
    const ingredientsHeadingDiv = document.createElement('div');
    ingredientsHeadingDiv.classList.add('ingredients-heading');
    ingredientsHeadingDiv.textContent = 'Ingredients';
    const ingredientsList = document.createElement('ul');
    recipe.ingredients.forEach(ingredient => {
        const li = document.createElement('li');
        li.textContent = ingredient;
        ingredientsList.appendChild(li);
    });
    ingredientsDiv.appendChild(ingredientsHeadingDiv);
    ingredientsDiv.appendChild(ingredientsList);

    // Create the cuisine, difficulty, time, calories div
    const cuisineDifficultyDiv = document.createElement('div');
    cuisineDifficultyDiv.classList.add('cuisine-difficulty');

    const cuisineDiv = document.createElement('div');
    cuisineDiv.textContent = recipe.cuisine;

    const difficultyDiv = document.createElement('div');
    difficultyDiv.textContent = recipe.difficulty;

    const timeDiv = document.createElement('div');
    timeDiv.textContent = recipe.timeTaken+" min";

    const caloriesDiv = document.createElement('div');
    caloriesDiv.textContent = recipe.calorieCount + " cal";
    
    cuisineDifficultyDiv.appendChild(cuisineDiv);
    cuisineDifficultyDiv.appendChild(difficultyDiv);
    cuisineDifficultyDiv.appendChild(timeDiv);
    cuisineDifficultyDiv.appendChild(caloriesDiv);

    // Create the rating div
    const ratingDiv = document.createElement('div');
    ratingDiv.classList.add('rating');
    ratingDiv.textContent = recipe.rating+"";

    // Append all elements to their respective parent elements
    nonRatingDiv.appendChild(foodImgDiv);
    nonRatingDiv.appendChild(foodNameDiv);
    nonRatingDiv.appendChild(ingredientsDiv);
    nonRatingDiv.appendChild(cuisineDifficultyDiv);
    nonRatingDiv.appendChild(ratingDiv);
    eachRecipeDiv.appendChild(nonRatingDiv);

    return eachRecipeDiv;
}

let recipeStore: RecipeStore = new RecipeStore();

const allRecipesContainer = document.getElementById("all-recipe-container");
const allRecipesDiv = document.getElementById("all-recipe");
const searchBtn = document.getElementById("search-btn");
const searchInput = document.getElementById("recipe-name-input");

window.onload = async () => {
    await recipeStore.fetchRecipesFromAPI();

    if (!allRecipesDiv) return;
    allRecipesDiv.innerHTML = '';

    recipeStore.recipes.forEach(recipe => {
        const eachRecipe = createRecipeElement(recipe);
        allRecipesDiv.appendChild(eachRecipe);
    });
}

if (searchBtn) {
    searchBtn.addEventListener("click", async () => {
        if (!searchInput) return;
        let query: string = searchInput.value;
        if (query.length === 0) return;

        if(allRecipesContainer) allRecipesContainer.style.display = "none";

        await recipeStore.searchRecipes(query).then((result: IRecipe[]) => {
            const searchResultsDiv = document.getElementById("search-results");
            if (!searchResultsDiv) return;
            searchResultsDiv.innerHTML = '';

            const h2Div = document.createElement("div");
            h2Div.id = "search-heading";
            h2Div.innerHTML = "Search Results...  ";

            const closeBtn = document.createElement("button");
            closeBtn.innerHTML = "close";
            closeBtn.id = "close-btn";
            closeBtn.addEventListener("click", () => {
                searchInput.value = "";
                searchResultsDiv.innerHTML = "";
                if(allRecipesContainer) allRecipesContainer.style.display = "grid";
            });

            h2Div.appendChild(closeBtn);
            searchResultsDiv.appendChild(h2Div);

            const recipeResultsDiv = document.createElement("div");
            recipeResultsDiv.id = "recipe-result";
            recipeResultsDiv.style.height = "80vh";

            result.forEach(recipe => {
                const eachRecipe = createRecipeElement(recipe);
                recipeResultsDiv.appendChild(eachRecipe);
            });

            searchResultsDiv.appendChild(recipeResultsDiv);

            const hr = document.createElement("hr");
            searchResultsDiv.appendChild(hr);
            searchResultsDiv.appendChild(hr);
        })
    });
}
