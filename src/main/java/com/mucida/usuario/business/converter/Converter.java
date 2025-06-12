package com.mucida.usuario.business.converter;

import com.mucida.usuario.business.dto.EnderecoDTO;
import com.mucida.usuario.business.dto.TelefoneDTO;
import com.mucida.usuario.business.dto.UsuarioDTO;
import com.mucida.usuario.infrastructure.entity.Endereco;
import com.mucida.usuario.infrastructure.entity.Telefone;
import com.mucida.usuario.infrastructure.entity.Usuario;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Converter {

    public Usuario toUsuario(UsuarioDTO usuarioDTO) {
        return Usuario.builder()
                .nome(usuarioDTO.getNome())
                .email(usuarioDTO.getEmail())
                .senha(usuarioDTO.getSenha())
                .enderecos(usuarioDTO.getEnderecos() != null ? toEnderecoList(usuarioDTO.getEnderecos()) : null)
                .telefones(usuarioDTO.getTelefones() != null ? toTelefoneList(usuarioDTO.getTelefones()) : null)
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO dto, Usuario entity) {
        return Usuario.builder()
                .id(entity.getId())
                .nome(dto.getNome() != null ? dto.getNome() : entity.getNome())
                .email(dto.getEmail() != null ? dto.getEmail() : entity.getNome())
                .senha(dto.getSenha() != null ? dto.getSenha() : entity.getSenha())
                .enderecos(entity.getEnderecos())
                .telefones(entity.getTelefones())
                .build();
    }

    public List<Endereco> toEnderecoList(List<EnderecoDTO> enderecoDTOList) {
        return enderecoDTOList
                .stream()
                .map(this::toEndereco)
                .toList();
    }

    public Endereco toEndereco(EnderecoDTO enderecoDTO) {
        return Endereco.builder()
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .cidade(enderecoDTO.getCidade())
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .build();
    }

    public Endereco toEndereco(EnderecoDTO enderecoDTO, Long id) {
        return Endereco.builder()
                .usuarioId(id)
                .cep(enderecoDTO.getCep())
                .estado(enderecoDTO.getEstado())
                .cidade(enderecoDTO.getCidade())
                .rua(enderecoDTO.getRua())
                .numero(enderecoDTO.getNumero())
                .complemento(enderecoDTO.getComplemento())
                .build();
    }


    public Endereco updateEndereco(EnderecoDTO dto, Endereco entity) {
        return Endereco.builder()
                .id(entity.getId())
                .cep(dto.getCep() != null ? dto.getCep() : entity.getCep())
                .estado(dto.getEstado() != null ? dto.getEstado() : entity.getEstado())
                .cidade(dto.getCidade() != null ? dto.getCidade() : entity.getCidade())
                .rua(dto.getRua() != null ? dto.getRua() : entity.getRua())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .complemento(dto.getComplemento() != null ? dto.getComplemento() : entity.getComplemento())
                .build();
    }

    public List<Telefone> toTelefoneList(List<TelefoneDTO> telefoneDTOList) {
        return telefoneDTOList
                .stream()
                .map(this::toTelefone)
                .toList();
    }

    public Telefone toTelefone(TelefoneDTO telefoneDTO) {
        return Telefone.builder()
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }

    public Telefone toTelefone(TelefoneDTO telefoneDTO, Long id) {
        return Telefone.builder()
                .usuarioId(id)
                .ddd(telefoneDTO.getDdd())
                .numero(telefoneDTO.getNumero())
                .build();
    }

    public Telefone updateTelefone(TelefoneDTO dto, Telefone entity) {
        return Telefone.builder()
                .id(entity.getId())
                .ddd(dto.getDdd() != null ? dto.getDdd() : entity.getDdd())
                .numero(dto.getNumero() != null ? dto.getNumero() : entity.getNumero())
                .build();
    }

    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(usuario.getEnderecos() != null ? toEnderecoDTOList(usuario.getEnderecos()) : null)
                .telefones(usuario.getTelefones() != null ? toTelefoneDTOList(usuario.getTelefones()) : null)
                .build();
    }

    public List<EnderecoDTO> toEnderecoDTOList(List<Endereco> enderecoList) {
        return enderecoList
                .stream()
                .map(this::toEnderecoDTO)
                .toList();
    }

    public EnderecoDTO toEnderecoDTO(Endereco endereco) {
        return EnderecoDTO.builder()
                .id(endereco.getId())
                .cep(endereco.getCep())
                .estado(endereco.getEstado())
                .cidade(endereco.getCidade())
                .rua(endereco.getRua())
                .numero(endereco.getNumero())
                .complemento(endereco.getComplemento())
                .build();
    }

    public List<TelefoneDTO> toTelefoneDTOList(List<Telefone> telefoneList) {
        return telefoneList
                .stream()
                .map(this::toTelefoneDTO)
                .toList();
    }

    public TelefoneDTO toTelefoneDTO(Telefone telefone) {
        return TelefoneDTO.builder()
                .id(telefone.getId())
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

}
