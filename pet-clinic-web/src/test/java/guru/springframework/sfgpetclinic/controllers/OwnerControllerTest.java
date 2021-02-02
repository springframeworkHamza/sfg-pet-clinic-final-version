package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.model.Owner;
import guru.springframework.sfgpetclinic.model.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController controller;

    Set<Owner> owners;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        owners = new HashSet<>();
        owners.add(Owner.builder().id(1l).build());
        owners.add(Owner.builder().id(2l).build());

        mockMvc = MockMvcBuilders
                  .standaloneSetup(controller)
                  .build();
    }

    @Test
    void listOwners() throws  Exception{
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/index"))
               .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void listOwnersByIndex() throws  Exception{
        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwners() throws Exception {
        mockMvc.perform(get("/owners/find"))
               .andExpect(status().isOk())
               .andExpect(view().name("notimplemented"));

        verifyZeroInteractions(ownerService);
    }

    @Test
    void displayOwner() throws Exception {
        when(ownerService.findById(anyLong())).thenReturn(Owner.builder().id(1l).build());

        mockMvc.perform(get("/owners/123"))
               .andExpect(status().isOk())
               .andExpect(view().name("owners/ownerDetails"))
               .andExpect(model().attribute("owner", hasProperty("id", is(1l))));
    }
}