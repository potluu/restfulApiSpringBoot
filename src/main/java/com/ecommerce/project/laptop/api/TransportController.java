package com.ecommerce.project.laptop.api;


import com.ecommerce.project.laptop.dto.TranspostDto;
import com.ecommerce.project.laptop.entitty.Transpost;
import com.ecommerce.project.laptop.services.TransportServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class TransportController {

    private final TransportServiceImpl transpostService;

    @GetMapping("/transposts")
    public ResponseEntity<?> getAllTranspost() {
        List<Transpost> transports=transpostService.getALlTranspost();
        if(!transports.isEmpty()){
            List<TranspostDto> transportDos=new ArrayList<>();
            transports.forEach(transport -> transportDos.add(new TranspostDto(transport)));
            return ResponseEntity.status(HttpStatus.OK).body(transportDos);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found data ");
        }
    }

    /*

     */
    @GetMapping("/transpost/{id}")
    public ResponseEntity<?> getTranspostById(@PathVariable long id) {

        Transpost transpost = transpostService.getTranspostById(id);
        if (transpost != null) {
            return new ResponseEntity<>(new TranspostDto(transpost), HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("not found data by request url");
        }
    }

    /*

     */
    @DeleteMapping("/transpost/{id}")
    public ResponseEntity<?> deleteTranspostById(@PathVariable long id) {

        if (transpostService.deleteById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("delete success data by request url");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("not found data");
        }
    }

    /*

     */
    @PostMapping(value = "/transpost")
    public ResponseEntity<?> saveTranspost( @ModelAttribute("TranspostDto") TranspostDto transpostDto) {
        if(transpostDto!=null){
           Transpost transpost=Transpost.builder()
                   .checks(false)
                   .build();
           transpostService.saveTranspost(transpost);
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("request url invalid data ");
    }
    /*

     */
    @PutMapping(value = "/transpost")
    public ResponseEntity<?> updateTranspost(@ModelAttribute("TranspostDto") TranspostDto transpostDto){
        if(transpostDto!=null&& transpostService.existById(transpostDto.getId())){
            Transpost transpost=Transpost.builder()
                    .checks(false)
                    .build();
            transpostService.updateTranspost(transpost);
            return ResponseEntity.status(HttpStatus.CREATED).body("success");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("request url invalid data ");
    }
}
