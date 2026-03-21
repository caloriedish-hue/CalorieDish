package uk.ac.tees.mad.caloriedish.utils

val foodSuggestions = listOf(
    "Apple",
    "Banana",
    "Orange",
    "Mango",
    "Pineapple",
    "Grapes",
    "Strawberry",
    "Blueberry",
    "Watermelon",
    "Papaya",

    "Pizza",
    "Burger",
    "Pasta",
    "Sandwich",
    "Hot Dog",
    "French Fries",
    "Nachos",
    "Tacos",
    "Burrito",
    "Wrap",

    "Fried Rice",
    "Chicken Biryani",
    "Veg Biryani",
    "Paneer Butter Masala",
    "Dal Tadka",
    "Rajma",
    "Chole",
    "Butter Chicken",
    "Chicken Curry",
    "Fish Curry",

    "Grilled Chicken",
    "Roasted Chicken",
    "Chicken Wings",
    "Chicken Nuggets",
    "Chicken Sandwich",
    "Chicken Salad",
    "Chicken Soup",
    "Chicken Pasta",
    "Chicken Pizza",
    "Chicken Burger",

    "Egg Omelette",
    "Boiled Egg",
    "Scrambled Eggs",
    "Egg Sandwich",
    "Egg Fried Rice",
    "Egg Curry",
    "Egg Roll",
    "Egg Noodles",
    "Egg Toast",
    "Egg Salad",

    "Salad",
    "Caesar Salad",
    "Greek Salad",
    "Fruit Salad",
    "Chicken Salad",
    "Pasta Salad",
    "Potato Salad",
    "Coleslaw",
    "Avocado Salad",
    "Quinoa Salad",

    "Noodles",
    "Hakka Noodles",
    "Chow Mein",
    "Ramen",
    "Udon",
    "Soba",
    "Pad Thai",
    "Glass Noodles",
    "Egg Noodles",
    "Rice Noodles",

    "Soup",
    "Tomato Soup",
    "Chicken Soup",
    "Vegetable Soup",
    "Mushroom Soup",
    "Corn Soup",
    "Hot and Sour Soup",
    "Lentil Soup",
    "Pumpkin Soup",
    "Noodle Soup",

    "Cake",
    "Chocolate Cake",
    "Vanilla Cake",
    "Strawberry Cake",
    "Cheesecake",
    "Cupcake",
    "Brownie",
    "Muffin",
    "Donut",
    "Ice Cream",

    "Milk",
    "Yogurt",
    "Butter",
    "Cheese",
    "Paneer",
    "Tofu",
    "Bread",
    "Toast",
    "Bagel",
    "Croissant"
)

fun getSuggestions(query: String): List<String> {

    if (query.isBlank()) return emptyList()

    return foodSuggestions
        .asSequence()
        .filter {
            it.startsWith(query, ignoreCase = true)
        }
        .take(10)
        .toList()
}