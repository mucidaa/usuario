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
                .enderecos(toEnderecoList(usuarioDTO.getEnderecos()))
                .telefones(toTelefoneList(usuarioDTO.getTelefones()))
                .build();
    }

    public Usuario updateUsuario(UsuarioDTO usuarioDTO, Usuario usuario) {
        return Usuario.builder()
                .id(usuario.getId())
                .nome(usuarioDTO.getNome() != null ? usuarioDTO.getNome() : usuario.getNome())
                .email(usuarioDTO.getEmail() != null ? usuarioDTO.getEmail() : usuario.getNome())
                .senha(usuarioDTO.getSenha() != null ? usuarioDTO.getSenha() : usuario.getSenha())
                .enderecos(usuario.getEnderecos())
                .telefones(usuario.getTelefones())
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

    public UsuarioDTO toUsuarioDTO(Usuario usuario) {
        return UsuarioDTO.builder()
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .senha(usuario.getSenha())
                .enderecos(toEnderecoDTOList(usuario.getEnderecos()))
                .telefones(toTelefoneDTOList(usuario.getTelefones()))
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
                .ddd(telefone.getDdd())
                .numero(telefone.getNumero())
                .build();
    }

}
