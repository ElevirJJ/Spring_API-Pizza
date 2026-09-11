package pizaaria.utils;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConverter {

    @Bean
   public ModelMapper modelMapper(){
       return new ModelMapper();
   }
}
