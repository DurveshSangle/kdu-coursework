var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __generator = (this && this.__generator) || function (thisArg, body) {
    var _ = { label: 0, sent: function() { if (t[0] & 1) throw t[1]; return t[1]; }, trys: [], ops: [] }, f, y, t, g;
    return g = { next: verb(0), "throw": verb(1), "return": verb(2) }, typeof Symbol === "function" && (g[Symbol.iterator] = function() { return this; }), g;
    function verb(n) { return function (v) { return step([n, v]); }; }
    function step(op) {
        if (f) throw new TypeError("Generator is already executing.");
        while (g && (g = 0, op[0] && (_ = 0)), _) try {
            if (f = 1, y && (t = op[0] & 2 ? y["return"] : op[0] ? y["throw"] || ((t = y["return"]) && t.call(y), 0) : y.next) && !(t = t.call(y, op[1])).done) return t;
            if (y = 0, t) op = [op[0] & 2, t.value];
            switch (op[0]) {
                case 0: case 1: t = op; break;
                case 4: _.label++; return { value: op[1], done: false };
                case 5: _.label++; y = op[1]; op = [0]; continue;
                case 7: op = _.ops.pop(); _.trys.pop(); continue;
                default:
                    if (!(t = _.trys, t = t.length > 0 && t[t.length - 1]) && (op[0] === 6 || op[0] === 2)) { _ = 0; continue; }
                    if (op[0] === 3 && (!t || (op[1] > t[0] && op[1] < t[3]))) { _.label = op[1]; break; }
                    if (op[0] === 6 && _.label < t[1]) { _.label = t[1]; t = op; break; }
                    if (t && _.label < t[2]) { _.label = t[2]; _.ops.push(op); break; }
                    if (t[2]) _.ops.pop();
                    _.trys.pop(); continue;
            }
            op = body.call(thisArg, _);
        } catch (e) { op = [6, e]; y = 0; } finally { f = t = 0; }
        if (op[0] & 5) throw op[1]; return { value: op[0] ? op[1] : void 0, done: true };
    }
};
var _this = this;
var RecipeStore = /** @class */ (function () {
    function RecipeStore() {
        var _this = this;
        this.fetchRecipesFromAPI = function () { return __awaiter(_this, void 0, void 0, function () {
            var _this = this;
            return __generator(this, function (_a) {
                console.log("Inside fetch recipes");
                return [2 /*return*/, new Promise(function (resolve, reject) {
                        fetch("https://dummyjson.com/recipes")
                            .then(function (response) {
                            console.log("fetched recipes non-json");
                            return response.json();
                        })
                            .then(function (data) {
                            // Assuming data is an object with a 'recipes' array
                            var recipeData = data.recipes;
                            recipeData.map(function (recipe) {
                                // Map the response data to your IRecipe interface
                                var obj = {
                                    image: recipe.image,
                                    name: recipe.name,
                                    rating: recipe.rating,
                                    cuisine: recipe.cuisine,
                                    ingredients: recipe.ingredients,
                                    difficulty: recipe.difficulty,
                                    timeTaken: recipe.cookTimeMinutes + recipe.prepTimeMinutes,
                                    calorieCount: recipe.caloriesPerServing
                                };
                                _this.recipes.push(obj);
                                console.log("pushed recipes");
                                resolve("");
                            });
                        });
                    })];
            });
        }); };
        this.recipes = [];
    }
    RecipeStore.prototype.searchRecipes = function (query) {
        return __awaiter(this, void 0, void 0, function () {
            return __generator(this, function (_a) {
                console.log("inside search recipes");
                return [2 /*return*/, new Promise(function (resolve, reject) {
                        fetch("https://dummyjson.com/recipes/search?q=" + query)
                            .then(function (response) {
                            console.log("fetched  recipe from " + query + " non-json");
                            return response.json();
                        })
                            .then(function (data) {
                            return data.recipes;
                        })
                            .then(function (data) {
                            var result = data.map(function (recipe) {
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
                        });
                    })];
            });
        });
    };
    RecipeStore.prototype.printAllRecipes = function () {
        console.log(this.recipes);
    };
    return RecipeStore;
}());
function driverFunction() {
    return __awaiter(this, void 0, void 0, function () {
        var recipeStore;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    recipeStore = new RecipeStore();
                    return [4 /*yield*/, recipeStore.fetchRecipesFromAPI()];
                case 1:
                    _a.sent();
                    return [4 /*yield*/, recipeStore.searchRecipes("Classic Margherita Pizza").then(function (result) {
                            console.log(result);
                        })];
                case 2:
                    _a.sent();
                    recipeStore.printAllRecipes();
                    return [2 /*return*/];
            }
        });
    });
}
driverFunction();
//////////////////   DOM    ////////////////////////
// Function to create the HTML structure for each recipe
function createRecipeElement(recipe) {
    // Create the main container div
    var eachRecipeDiv = document.createElement('div');
    eachRecipeDiv.classList.add('each-recipe');
    // Create the non-rating div
    var nonRatingDiv = document.createElement('div');
    nonRatingDiv.classList.add('non-rating');
    // Create the food image div
    var foodImgDiv = document.createElement('div');
    foodImgDiv.classList.add('food-img');
    var foodImg = document.createElement('img');
    foodImg.src = recipe.image;
    foodImg.alt = recipe.name;
    foodImgDiv.appendChild(foodImg);
    // Create the food name div
    var foodNameDiv = document.createElement('div');
    foodNameDiv.classList.add('food-name');
    foodNameDiv.textContent = recipe.name;
    // Create the ingredients div
    var ingredientsDiv = document.createElement('div');
    ingredientsDiv.classList.add('ingredients');
    var ingredientsHeadingDiv = document.createElement('div');
    ingredientsHeadingDiv.classList.add('ingredients-heading');
    ingredientsHeadingDiv.textContent = 'Ingredients';
    var ingredientsList = document.createElement('ul');
    recipe.ingredients.forEach(function (ingredient) {
        var li = document.createElement('li');
        li.textContent = ingredient;
        ingredientsList.appendChild(li);
    });
    ingredientsDiv.appendChild(ingredientsHeadingDiv);
    ingredientsDiv.appendChild(ingredientsList);
    // Create the cuisine, difficulty, time, calories div
    var cuisineDifficultyDiv = document.createElement('div');
    cuisineDifficultyDiv.classList.add('cuisine-difficulty');
    var cuisineDiv = document.createElement('div');
    cuisineDiv.textContent = recipe.cuisine;
    var difficultyDiv = document.createElement('div');
    difficultyDiv.textContent = recipe.difficulty;
    var timeDiv = document.createElement('div');
    timeDiv.textContent = recipe.timeTaken + " min";
    var caloriesDiv = document.createElement('div');
    caloriesDiv.textContent = recipe.calorieCount + " cal";
    cuisineDifficultyDiv.appendChild(cuisineDiv);
    cuisineDifficultyDiv.appendChild(difficultyDiv);
    cuisineDifficultyDiv.appendChild(timeDiv);
    cuisineDifficultyDiv.appendChild(caloriesDiv);
    // Create the rating div
    var ratingDiv = document.createElement('div');
    ratingDiv.classList.add('rating');
    ratingDiv.textContent = recipe.rating + "";
    // Append all elements to their respective parent elements
    nonRatingDiv.appendChild(foodImgDiv);
    nonRatingDiv.appendChild(foodNameDiv);
    nonRatingDiv.appendChild(ingredientsDiv);
    nonRatingDiv.appendChild(cuisineDifficultyDiv);
    nonRatingDiv.appendChild(ratingDiv);
    eachRecipeDiv.appendChild(nonRatingDiv);
    return eachRecipeDiv;
}
var recipeStore = new RecipeStore();
var allRecipesContainer = document.getElementById("all-recipe-container");
var allRecipesDiv = document.getElementById("all-recipe");
var searchBtn = document.getElementById("search-btn");
var searchInput = document.getElementById("recipe-name-input");
window.onload = function () { return __awaiter(_this, void 0, void 0, function () {
    return __generator(this, function (_a) {
        switch (_a.label) {
            case 0: return [4 /*yield*/, recipeStore.fetchRecipesFromAPI()];
            case 1:
                _a.sent();
                if (!allRecipesDiv)
                    return [2 /*return*/];
                allRecipesDiv.innerHTML = '';
                recipeStore.recipes.forEach(function (recipe) {
                    var eachRecipe = createRecipeElement(recipe);
                    allRecipesDiv.appendChild(eachRecipe);
                });
                return [2 /*return*/];
        }
    });
}); };
if (searchBtn) {
    searchBtn.addEventListener("click", function () { return __awaiter(_this, void 0, void 0, function () {
        var query;
        return __generator(this, function (_a) {
            switch (_a.label) {
                case 0:
                    if (!searchInput)
                        return [2 /*return*/];
                    query = searchInput.value;
                    if (query.length === 0)
                        return [2 /*return*/];
                    if (allRecipesContainer)
                        allRecipesContainer.style.display = "none";
                    return [4 /*yield*/, recipeStore.searchRecipes(query).then(function (result) {
                            var searchResultsDiv = document.getElementById("search-results");
                            if (!searchResultsDiv)
                                return;
                            searchResultsDiv.innerHTML = '';
                            var h2Div = document.createElement("div");
                            h2Div.id = "search-heading";
                            h2Div.innerHTML = "Search Results...  ";
                            var closeBtn = document.createElement("button");
                            closeBtn.innerHTML = "close";
                            closeBtn.id = "close-btn";
                            closeBtn.addEventListener("click", function () {
                                searchInput.value = "";
                                searchResultsDiv.innerHTML = "";
                                if (allRecipesContainer)
                                    allRecipesContainer.style.display = "grid";
                            });
                            h2Div.appendChild(closeBtn);
                            searchResultsDiv.appendChild(h2Div);
                            var recipeResultsDiv = document.createElement("div");
                            recipeResultsDiv.id = "recipe-result";
                            recipeResultsDiv.style.height = "80vh";
                            result.forEach(function (recipe) {
                                var eachRecipe = createRecipeElement(recipe);
                                recipeResultsDiv.appendChild(eachRecipe);
                            });
                            searchResultsDiv.appendChild(recipeResultsDiv);
                            var hr = document.createElement("hr");
                            searchResultsDiv.appendChild(hr);
                            searchResultsDiv.appendChild(hr);
                        })];
                case 1:
                    _a.sent();
                    return [2 /*return*/];
            }
        });
    }); });
}
