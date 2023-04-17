package com.godoro.marketapp.business.service;

import com.godoro.marketapp.business.dto.CartDto;
import com.godoro.marketapp.business.dto.CartProductDto;
import com.godoro.marketapp.business.dto.ProductDto;
import com.godoro.marketapp.data.entity.Cart;
import com.godoro.marketapp.data.entity.CartProduct;
import com.godoro.marketapp.data.entity.Product;
import com.godoro.marketapp.data.repository.CartProductRepository;
import com.godoro.marketapp.data.repository.CartRepository;
import com.godoro.marketapp.data.repository.ProductRepository;
import com.godoro.marketapp.enums.CartStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImp implements CartService{
    CartRepository cartRepository;

    CartProductRepository cartProductRepository;

    ProductService productService;


    public CartServiceImp(CartRepository cartRepository, ProductService productService, CartProductRepository cartProductRepository){
        this.cartRepository = cartRepository;
        this.productService = productService;
        this.cartProductRepository = cartProductRepository;

    }
    @Override
    public void remove(long cartId, long productId) {
        CartProduct cartProduct= cartProductRepository.findCartProductByCart_CartIdAndProduct_ProductId(cartId,productId);
        if(cartProduct!=null){ //decrease quantity
            if(cartProduct.getSalesQuantity()>1) {
                cartProduct.setSalesQuantity(cartProduct.getSalesQuantity() - 1);
                cartProductRepository.save(cartProduct);

            }
            else
                cartProductRepository.deleteById(cartProduct.getCartProductId());

        }
    }

    @Override
    public CartDto getCart(long cartId) {

        Optional<Cart> optional = cartRepository.findById(cartId);
        if(optional.isPresent()){
            return convertToDto(optional.get());
        }
        else{
            CartDto cartDto = new CartDto();
            cartDto.setCartStatus(CartStatus.NEW);

            return convertToDto(cartRepository.save(convertToEntity(cartDto)));

        }
    }

    @Override
    public void add(long cartId, long productId) {
        if(productService.find(productId) != null){
            CartProduct cartProduct= cartProductRepository.findCartProductByCart_CartIdAndProduct_ProductId(cartId,productId);
            if(cartProduct!=null){ //update quantity
                cartProduct.setSalesQuantity(cartProduct.getSalesQuantity()+1);
                cartProductRepository.save(cartProduct);
            }
            else {//create new cartProduct

                CartProduct cartProduct1 = new CartProduct();
                cartProduct1.setCart(convertToEntity(getCart(cartId)));
                cartProduct1.setProduct(convertToEntity(productService.find(productId)));
                cartProduct1.setSalesQuantity(1);
                cartProductRepository.save(cartProduct1);
            }
        }
        else{
            System.out.println("Hatalı ekleme");
        }

    }

    @Override
    public List<CartProductDto> getProductListByCartId(long cartId) {

        List<CartProduct> list = cartProductRepository.findCartProductsByCart_CartId(cartId);

        return convertToDto(list);
    }

    @Override
    public CartDto checkout(CartDto cartDto) {
        CartDto cartDto1=getCart(cartDto.getCartId());
        cartDto1.setCartStatus(CartStatus.COMPLETED);
        cartDto1.setCardNumber(cartDto.getCardNumber());
        cartDto1.setCustomerName(cartDto.getCustomerName());

        cartRepository.save(convertToEntity(cartDto1));
        return convertToDto(cartRepository.save(convertToEntity(cartDto1)));
    }

    private static Cart convertToEntity(CartDto cartDto) {
        Cart cart = new Cart();

        cart.setCartId(cartDto.getCartId());
        cart.setCustomerName(cartDto.getCustomerName());
        cart.setCardNumber(cartDto.getCardNumber());
        cart.setCartStatus(cartDto.getCartStatus());


        return cart;
    }
    /*
    private void setCartProductList(long cartId, Iterable<CartProduct> cartProducts){
        List<CartProduct> list = new ArrayList<>();
        for (CartProduct cartProduct : cartProducts){
            list.add(cartProduct);
        }
        Optional<Cart> optional = cartRepository.findById(cartId);
        if(optional.isPresent()){
             optional.get().setProductList(list);
             cartRepository.save(optional.get());
        }

    }
*/
    private static CartProduct convertToEntity(CartProductDto cartProductDto) {
        CartProduct cartProduct = new CartProduct();

        cartProduct.setCart(convertToEntity(cartProductDto.getCart()));
        cartProduct.setCartProductId(cartProductDto.getCartProductId());
        cartProduct.setProduct(convertToEntity(cartProductDto.getProduct()));
        cartProduct.setSalesQuantity(cartProductDto.getSalesQuantity());


        return cartProduct;
    }
    private static List<CartProduct> convertToEntity(List<CartProductDto> cartProductDtoList) {
        List<CartProduct> cartProductList = new ArrayList<>();
        if(cartProductDtoList != null) {
            for (CartProductDto cartProductDto : cartProductDtoList) {
                cartProductList.add(convertToEntity(cartProductDto));
            }


            return cartProductList;
        }
        else
            return cartProductList;
    }

    private static List<CartProductDto> convertToDto(List<CartProduct> cartProductList) {
        List<CartProductDto> cartProductDtoList = new ArrayList<>();

        for(CartProduct cartProduct: cartProductList){
            cartProductDtoList.add(convertToDto(cartProduct));
        }


        return cartProductDtoList;
    }

    private static CartDto convertToDto(Cart cart) {
        CartDto cartDto = new CartDto();

        cartDto.setCartId(cart.getCartId());
        cartDto.setCustomerName(cart.getCustomerName());
        cartDto.setCardNumber(cart.getCardNumber());
        cartDto.setCartStatus(cart.getCartStatus());


        return cartDto;
    }
    private static CartProductDto convertToDto(CartProduct cartProduct) {
        CartProductDto cartProductDto = new CartProductDto();

        cartProductDto.setSalesQuantity(cartProduct.getSalesQuantity());
        cartProductDto.setCartProductId(cartProduct.getCartProductId());
        cartProductDto.setCart(convertToDto(cartProduct.getCart()));
        cartProductDto.setProduct(convertToDto(cartProduct.getProduct()));


        return cartProductDto;
    }


    private static Product convertToEntity(ProductDto productDto) {
        Product product = new Product();

        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setSalesPrice(productDto.getSalesPrice());
        product.setCategory(productDto.getCategory());


        return product;
    }
    private static ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();

        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setSalesPrice(product.getSalesPrice());
        productDto.setCategory(product.getCategory());


        return productDto;
    }


}
