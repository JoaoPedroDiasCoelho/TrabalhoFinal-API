package br.com.serratec.TrabalhoFinal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.serratec.TrabalhoFinal.dto.PedidoDTO;
import br.com.serratec.TrabalhoFinal.service.PedidoService;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService service;

    @GetMapping
    public ResponseEntity<Page<PedidoDTO>> findAll(Pageable pageable) {
        Page<PedidoDTO> page = service.findAllPaged(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable Long id) {
        PedidoDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> insert(@RequestBody PedidoDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PatchMapping(value = "/{id}/status")
    public ResponseEntity<PedidoDTO> updateStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String novoStatus = body.get("status");
        PedidoDTO dto = service.updateStatus(id, novoStatus);
        return ResponseEntity.ok().body(dto);
    }
}
