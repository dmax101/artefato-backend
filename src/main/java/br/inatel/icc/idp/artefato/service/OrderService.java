package br.inatel.icc.idp.artefato.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.inatel.icc.idp.artefato.model.ProductEntity;
import br.inatel.icc.idp.artefato.model.UserEntity;
import br.inatel.icc.idp.artefato.model.DTO.BasicMessageDTO;
import br.inatel.icc.idp.artefato.model.DTO.PaymentDTO;
import br.inatel.icc.idp.artefato.model.DTO.error.ErrorDTO;
import br.inatel.icc.idp.artefato.repository.ProductRepository;
import br.inatel.icc.idp.artefato.repository.UserRepository;

@Service
public class OrderService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    enum ERRORS_STATUS {
        FOUND, NOT_FOUND, AVAILABLE, NOT_AVAILABLE
    }

    public Pair<Boolean, BasicMessageDTO> execute(@Valid PaymentDTO paymentDTO) {

        Optional<UserEntity> buyer = userRepository.getUserById(paymentDTO.getBuyerId());
        Optional<UserEntity> seller = userRepository.getUserCrafterOfProduct(paymentDTO.getProductId());
        Optional<ProductEntity> product = productRepository.getProductById(paymentDTO.getProductId());

        if (buyer.isEmpty() || seller.isEmpty() || product.isEmpty()
                || Boolean.TRUE.equals(!product.get().getIsAvailable())) {
            Map<String, String> error = new HashMap<>();

            String buyerError = buyer.isEmpty() ? ERRORS_STATUS.NOT_FOUND.toString() : ERRORS_STATUS.FOUND.toString();
            String sellerError = seller.isEmpty() ? ERRORS_STATUS.NOT_FOUND.toString() : ERRORS_STATUS.FOUND.toString();
            String productError = product.isEmpty() ? ERRORS_STATUS.NOT_FOUND.toString()
                    : ERRORS_STATUS.FOUND.toString();
            if (product.isPresent()) {
                String productAvailability = Boolean.FALSE.equals(product.get().getIsAvailable())
                        ? ERRORS_STATUS.NOT_AVAILABLE.toString()
                        : ERRORS_STATUS.AVAILABLE.toString();

                error.put("product.isAvailable", productAvailability);
            }

            error.put("buyer", buyerError);
            error.put("seller", sellerError);
            error.put("product", productError);

            return Pair.of(false,
                    new ErrorDTO(HttpStatus.NOT_FOUND.toString(), "Não foi encontrado", Arrays.asList(error)));
        }

        BigDecimal ticketValue = product.get().getPrice();
        BigDecimal sellerWallet = seller.get().getWallet();
        BigDecimal buyerWallet = buyer.get().getWallet();

        BigDecimal newSellerWallet = sellerWallet.add(ticketValue);
        BigDecimal newBuyerWallet = buyerWallet.subtract(ticketValue);
        if (newBuyerWallet.compareTo(BigDecimal.ZERO) < 0) {
            return Pair.of(false, new BasicMessageDTO(HttpStatus.BAD_REQUEST.toString(), "Saldo insuficiente!"));
        }

        Optional<BigDecimal> sellerWalletUpdated = userRepository.setNewWallet(seller.get().getId(), newSellerWallet);
        Optional<BigDecimal> buyerWalletUpdated = userRepository.setNewWallet(buyer.get().getId(), newBuyerWallet);
        Optional<Boolean> productAvailbility = productRepository.setProductAvaiability(product.get().getId(), false);
        Optional<Boolean> isBuyerPurchaded = userRepository.setUserBuyedProduct(buyer.get().getId(),
                product.get().getId());

        if (sellerWalletUpdated.isPresent() && buyerWalletUpdated.isPresent() && productAvailbility.isPresent()
                && isBuyerPurchaded.isPresent() && newSellerWallet.equals(sellerWalletUpdated.get())
                && newBuyerWallet.equals(buyerWalletUpdated.get())
                && Boolean.FALSE.equals(productAvailbility.get() && isBuyerPurchaded.get())) {

            return Pair.of(true, new BasicMessageDTO(HttpStatus.OK.toString(), "Operação realizada com sucesso!"));
        }

        return Pair.of(false, new BasicMessageDTO(HttpStatus.NOT_FOUND.toString(), "Operação não realizada!"));
    }

}
