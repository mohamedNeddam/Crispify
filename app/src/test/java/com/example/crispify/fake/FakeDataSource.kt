package com.example.crispify.fake

import com.example.crispify.Recipe

object FakeDataSource {
    val recipesList = listOf(
        Recipe(
            cookTime = 10,
            description= "This buttery lemon-garlic salmon is easy to throw together with a few simple ingredients. For best results, use center-cut fillets that are about 1-inch thick. Eight minutes cook time will produce salmon that is medium-well. Add 1 to 2 more minutes for well done, but take care not to overcook. Cook time may vary with different model air fryers.",
            directions= listOf(
            "Preheat the air fryer to 390 degrees F (200 degrees C).",
            "Combine melted butter and minced garlic in a small bowl.",
            "Rinse salmon fillets and dry with a paper towel. Brush with butter mixture and sprinkle with lemon-pepper seasoning and parsley.",
            "Spray the basket of the air fryer with cooking spray. Place salmon fillets in the basket, skin-side down, and top each with 3 lemon halves.",
            "Cook in the preheated air fryer for 8 to 10 minutes. Remove from the air fryer and let rest for 2 minutes before serving."
            ),
            id= 1,
            imageUrl = "https://imagesvc.meredithcorp.io/v3/mm/image?url=https%3A%2F%2Fimages.media-allrecipes.com%2Fuserphotos%2F7577661.jpg&q=60&c=sc&orient=true&poi=auto&h=512",
            ingredients= listOf(
            "1 tablespoon melted butter",
            "½ teaspoon minced garlic",
            "2 (6 ounce) fillets center-cut salmon fillets with skin",
            "¼ teaspoon lemon-pepper seasoning",
            "⅛ teaspoon dried parsley",
            "cooking spray",
            "3 thin slices lemon, cut in half"
            ),
            owner="France C",
            prepTime= 10,
            rating= 4.8,
            serving= 2,
            title= "Lemon-Garlic Air Fryer Salmon",
            yield= "2 salmon fillets"
        )
    )
}