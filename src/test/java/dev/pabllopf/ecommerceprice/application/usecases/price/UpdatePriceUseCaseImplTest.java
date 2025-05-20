package dev.pabllopf.ecommerceprice.application.usecases.price;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UpdatePriceUseCaseImplTest {

    @Mock
    @InjectMocks
    private UpdatePriceUseCaseImpl updatePriceUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }


}