package Nhom08_AppleStore.service;

import Nhom08_AppleStore.model.CartItem;
import Nhom08_AppleStore.model.Product;
import Nhom08_AppleStore.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.ArrayList;
import java.util.List;
@Service
@SessionScope
public class CartService {
    private List<CartItem> cartItems = new ArrayList<>();
    @Autowired
    private ProductRepository productRepository;
    public void addToCart(Long productId, int quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found: " + productId));

        boolean found = false;
        for(CartItem item : cartItems){
            if(item.getProduct().getId().equals(productId)){
                item.setQuantity(item.getQuantity() + quantity);
                found = true;
                break;
            }
        }
        if(!found){
            cartItems.add(new CartItem(product, quantity));
        }
    }
    public List<CartItem> getCartItems() {
        return cartItems;
    }
    public void removeFromCart(Long productId) {
        cartItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    //tăng giảm số lg sản phẩm trong giỏ hàng và tính tổng tiền trong giỏ hàng hiện có
    public void increaseQuantity(Long productId) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + 1);
                break;
            }
        }
    }

    public void decreaseQuantity(Long productId) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                if (item.getQuantity() > 1) {
                    item.setQuantity(item.getQuantity() - 1);
                }
                break;
            }
        }
    }
    public double getTotalPrice() {
        return cartItems.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void clearCart() {
        cartItems.clear();
    }
}
