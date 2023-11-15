package com.gws.api.apigws.controllers;

import com.gws.api.apigws.DTOs.ClientesDTOs;
import com.gws.api.apigws.DTOs.DemandasDTOs;
import com.gws.api.apigws.DTOs.UsuariosDTOs;
import com.gws.api.apigws.models.ClientesModel;
import com.gws.api.apigws.models.DemandasModel;
import com.gws.api.apigws.repositories.ClientesRepository;
import com.gws.api.apigws.repositories.DemandasRepository;
import com.gws.api.apigws.repositories.UsuariosRepository;
import com.gws.api.apigws.services.ConverterDataTime;
import com.gws.api.apigws.services.FileUploadService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/demandas")
public class DemandasController {
    @Autowired
    DemandasRepository demandasRepository;
    @Autowired
    FileUploadService fileUploadService;
    @Autowired
    ConverterDataTime converterDataTime;
    @Autowired
    UsuariosRepository usuariosRepository;

    @GetMapping
    public ResponseEntity<List<DemandasModel>> ListarDemandas(){
        return ResponseEntity.status(HttpStatus.OK).body(demandasRepository.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> BuscarDemandas(@PathVariable(value = "id") UUID id){
        Optional<DemandasModel> buscandoDemandas = demandasRepository.findById(id);

        if (buscandoDemandas.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Demanda não encontrado");
        }


        return ResponseEntity.status(HttpStatus.OK).body(buscandoDemandas.get());
    }

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> criarCliente(@ModelAttribute @Valid DemandasDTOs demandasDTOs, UsuariosDTOs usuariosDTOs){
        if (demandasRepository.findByTitulo(demandasDTOs.titulo()) != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Demanda já cadastrado");
        }
//        if (usuariosRepository.findById(demandasDTOs.foreign_usuario()) == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Chave usuario incorreta");
//        }

        DemandasModel novaDemanda = new DemandasModel();
        BeanUtils.copyProperties(demandasDTOs, novaDemanda);

        String urlArquivo;

        try{
            urlArquivo = fileUploadService.fazerUpload(demandasDTOs.anexo());
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        LocalDate data1;
        LocalDate data2;

        try{
            data1 = converterDataTime.StringToDate(demandasDTOs.data_final());
            data2 = converterDataTime.StringToDate(demandasDTOs.data_inicio());
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        novaDemanda.setAnexo(urlArquivo);
        novaDemanda.setData_final(data1);
        novaDemanda.setData_inicio(data2);


        return ResponseEntity.status(HttpStatus.CREATED).body(demandasRepository.save(novaDemanda));
    }

    @PutMapping(name = "/{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Object> criarCliente(@PathVariable(value = "id") UUID id, @ModelAttribute @Valid DemandasDTOs demandasDTOs){
        Optional<DemandasModel> buscandoDemanda = demandasRepository.findById(id);

        if (buscandoDemanda.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Demanda não encontrado");
        }

        DemandasModel demandaEditado = new DemandasModel();
        BeanUtils.copyProperties(demandasDTOs, demandaEditado);

        String urlArquivo;

        try{
            urlArquivo = fileUploadService.fazerUpload(demandasDTOs.anexo());
        }catch (IOException e){
            throw new RuntimeException(e);
        }

        LocalDate data1;
        LocalDate data2;

        try{
            data1 = converterDataTime.StringToDate(demandasDTOs.data_final());
            data2 = converterDataTime.StringToDate(demandasDTOs.data_inicio());
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        demandaEditado.setAnexo(urlArquivo);
        demandaEditado.setData_final(data1);
        demandaEditado.setData_inicio(data2);



        return ResponseEntity.status(HttpStatus.CREATED).body(demandasRepository.save(demandaEditado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarDemanda(@PathVariable(value = "id") UUID id){

        Optional<DemandasModel> demandaBuscado = demandasRepository.findById(id);

        if (demandaBuscado.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não encontrado");
        }

        demandasRepository.delete(demandaBuscado.get());
        return ResponseEntity.status(HttpStatus.OK).body("Cliente deletado com sucesso!");

    }
}
