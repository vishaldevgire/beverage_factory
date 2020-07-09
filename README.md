# beverage_factory

This simple app takes orders and returns their result

Below are the Menu Items with their respective ingredients and prices:
```
- Menu Items
Coffee: "Coffee, milk, sugar, water" Price: 5$
Chai: "Tea, milk, sugar, water" Price: 4$
Banana Smoothie: "banana, milk, sugar, water" Price: 6$
Strawberry Shake: "Strawberries, sugar, milk, water" Price: 7 $
Mojito: "Lemon, sugar, water, soda, mint" Price 7.5 $

- Ingredients prices:
Milk: 1 $
Sugar: 0.5 $
Soda: 0.5 $
mint: 0.5 $
water: 0.5 $

```


Example, for following orders as input:

```
    "Coffee",    
    "Chai, -sugar, -milk",
    "Chai, -Ghee, -milk, -sugar, -water",
    "Random",
    "Chai, -Tea, -milk, -sugar, -water"
```

It will return following result:

```
 // success
 { 'order' 'Coffee', 'price': 5.0 }
 { 'order' 'Chai, -sugar, -milk', 'price': 2.5 }
 
 // failure scenarios
 { 'order: 'Chai, -Ghee, -milk, -sugar, -water', error: 'Order must not contain invalid ingredients' }
 { 'order: 'Random', error: 'Order must contain at least one valid menu item' }
 { 'order: 'Chai, -Tea, -milk, -sugar, -water', error: 'Order must not exclude all ingredients of menu item' }
```

