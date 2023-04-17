# E-commerce-SpringBoot-React
<h2> Tutorial <h2>

![tutorial](https://github.com/canbolatkaan/E-commerce-SpringBoot-React/blob/main/tutorial.gif)

** Backend Endpoints **
```
	/categories
	/products/{categoryid}
	/product/{id}
  /cart/get/{id} 
	/cart/add/{cartid}/{productid}
	/cart/remove/{cartid}/{productid}
	/cart/checkout
```  
** SQL Database **
```
  Category: categoryId categoryName
	Product: productId productName salesPrice categoryId
  Cart: cartId customerName cardNumber cartStatus (Enum: NEW, COMPLETED)
	CartProduct: cartProductId cartId productId salesQuantity
```
